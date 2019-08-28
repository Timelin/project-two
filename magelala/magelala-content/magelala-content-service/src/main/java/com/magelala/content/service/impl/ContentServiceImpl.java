package com.magelala.content.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.magelala.content.service.ContentService;
import com.magelala.mapper.ContentMapper;
import com.magelala.pojo.TbContent;
import com.magelala.service.impl.BaseServiceImpl;
import com.magelala.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName:ContentServiceImpl
 * @Author:Timelin
 **/
@Service(interfaceClass = ContentService.class)
public class ContentServiceImpl extends BaseServiceImpl<TbContent>implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private RedisTemplate redisTemplate;
    private static final String REDIS_CONTENT="content";


    //条件搜索
    @Override
    public PageResult search(TbContent content, Integer page, Integer rows) {
        PageHelper.startPage(page,rows);

        Example example = new Example(TbContent.class);
        Example.Criteria criteria = example.createCriteria();

        List<TbContent> list = contentMapper.selectByExample(example);
        PageInfo<TbContent> pageInfo = new PageInfo<>(list);

        return new PageResult(pageInfo.getTotal(),pageInfo.getList());

    }

    /**
    *根据内容分类id查询启动的内容列表并降序排序
    * @Author: Timelin
    * @Param : categoryId
    * @Return : List<TbContent>
    **/
    @Override
    public List<TbContent> findContentListByCategoryId(Long categoryId) {
        List<TbContent> list = null;

        // 先从Redis缓存中查找
        try {
            list =(List<TbContent>) redisTemplate.boundHashOps(REDIS_CONTENT).get(categoryId);
            if(list !=null){
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Example example = new Example(TbContent.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("categoryId",categoryId);
        // 启用状态的
        criteria.andEqualTo("status","1");
        // 降序排序
        example.orderBy("sortOrder").desc();
         list = contentMapper.selectByExample(example);

         // 设置某个分类对应的广告内容列表到缓存中

        try {
            redisTemplate.boundHashOps(REDIS_CONTENT).put(categoryId,list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
    *新增广告清除缓存
    * @Author: Timelin
    * @Param : TbContent
    * @Return : void
    **/
    @Override
    public void add(TbContent content) {

        super.add(content);
        //更新内容分类对应在redis中的内容列表缓存
        updateContentInRedisByCategoryId(content.getCategoryId());

    }



    /**
    *修改广告后清除Redis缓存
    * @Author: Timelin
    * @Param : TbContent
    * @Return : void
    **/
    @Override
    public void update(TbContent tbContent) {
        TbContent oldContent = super.findOne(tbContent.getId());

        super.update(tbContent);

        // 是否修改了内容分类，如果修改了内容分类则需要将新旧分类对应的内容列表都更新
        if(!oldContent.getCategoryId().equals(tbContent.getCategoryId())){
            updateContentInRedisByCategoryId(oldContent.getCategoryId());
        }
        updateContentInRedisByCategoryId(tbContent.getCategoryId());

    }



    /**
    *批量删除广告后清除缓存
    * @Author: Timelin
    * @Param : Serializable[] ids
    * @Return : void
    **/
    @Override
    public void deleteByIds(Serializable[] ids) {

        // 1 根据内容id集合查询内容列表，然后再更新该内容分类对应的内容列表缓存
        Example example = new Example(TbContent.class);
        example.createCriteria().andIn("id", Arrays.asList(ids));
        List<TbContent> contentList = contentMapper.selectByExample(example);

        if(contentList != null && contentList.size()>0){
            for (TbContent content :contentList){
                updateContentInRedisByCategoryId(content.getCategoryId());
            }
        }

        // 2. 删除内容
        super.deleteByIds(ids);


    }

    /**
    *新旧分类对应的内容列表都更新
    * @Author: Timelin
    * @Param : Long categoryId
    * @Return : void
    **/
    private void updateContentInRedisByCategoryId(Long categoryId) {

        try {
            redisTemplate.boundHashOps(REDIS_CONTENT).delete(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


