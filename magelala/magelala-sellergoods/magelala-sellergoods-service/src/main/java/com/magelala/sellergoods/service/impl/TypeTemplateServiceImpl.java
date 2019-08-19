package com.magelala.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.magelala.mapper.TypeTemplateMapper;
import com.magelala.pojo.TbTypeTemplate;
import com.magelala.sellergoods.service.TypeTemplateService;
import com.magelala.service.impl.BaseServiceImpl;
import com.magelala.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service(interfaceClass = TypeTemplateService.class)
public class TypeTemplateServiceImpl extends BaseServiceImpl<TbTypeTemplate> implements TypeTemplateService {


    @Autowired
    private TypeTemplateMapper typeTemplateMapper;
    //条件搜索
    @Override
    public PageResult search(TbTypeTemplate typeTemplate, Integer page, Integer rows) {

        // 设置分页对象
        PageHelper.startPage(page,rows);
        //设置查询对象
        Example example = new Example(TbTypeTemplate.class);
        // 设置条件对象
        Example.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(typeTemplate.getName())){
            criteria.andLike("name","%"+typeTemplate.getName()+"%");

        }
        List<TbTypeTemplate> list = typeTemplateMapper.selectByExample(example);
        PageInfo<TbTypeTemplate> pageInfo = new PageInfo<>(list);


        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }
}
