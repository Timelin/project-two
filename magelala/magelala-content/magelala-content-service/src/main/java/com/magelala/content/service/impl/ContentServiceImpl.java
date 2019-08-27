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
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ClassName:ContentServiceImpl
 * @Author:Timelin
 **/
@Service(interfaceClass = ContentService.class)
public class ContentServiceImpl extends BaseServiceImpl<TbContent>implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

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

        Example example = new Example(TbContent.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("categoryId",categoryId);
        // 启用状态的
        criteria.andEqualTo("status","1");
        // 降序排序
        example.orderBy("sortOrder").desc();
        List<TbContent> list = contentMapper.selectByExample(example);

        return list;
    }


}


