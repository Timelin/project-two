package com.magelala.content.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.magelala.content.service.ContentCategoryService;
import com.magelala.mapper.ContentCategoryMapper;
import com.magelala.pojo.TbContentCategory;
import com.magelala.pojo.TbSeller;
import com.magelala.service.impl.BaseServiceImpl;
import com.magelala.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ClassName:ContentCategoryServiceImpl
 * @Author:Timelin
 **/
@Service(interfaceClass = ContentCategoryService.class)
public class ContentCategoryServiceImpl extends BaseServiceImpl<TbContentCategory> implements ContentCategoryService {

    @Autowired
    private ContentCategoryMapper contentCategoryMapper;

    @Override
    public PageResult search(TbContentCategory contentCategory, Integer page, Integer rows) {

        // 设置分页
        PageHelper.startPage(page,rows);

        // 设置分页对象
        Example example = new Example(TbContentCategory.class);
        Example.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(contentCategory.getName())) {
            criteria.andLike("name","%"+contentCategory.getName()+"%");

        }
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        PageInfo<TbContentCategory> pageInfo = new PageInfo<>(list);

        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }
}
