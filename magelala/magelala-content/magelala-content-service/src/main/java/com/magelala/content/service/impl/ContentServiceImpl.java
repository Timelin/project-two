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
}


