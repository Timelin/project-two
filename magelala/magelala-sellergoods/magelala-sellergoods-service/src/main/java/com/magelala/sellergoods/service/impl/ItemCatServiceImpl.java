package com.magelala.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.magelala.mapper.ItemCatMapper;
import com.magelala.pojo.TbItemCat;
import com.magelala.sellergoods.service.ItemCatService;
import com.magelala.service.impl.BaseServiceImpl;
import com.magelala.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ClassNameItemCatServiceImpl
 * @Author:Timelin
 **/
@Service
public class ItemCatServiceImpl extends BaseServiceImpl<TbItemCat> implements ItemCatService {

    @Autowired
    private ItemCatMapper itemCatMapper;


    @Override
    public PageResult search(TbItemCat itemCat, Integer page, Integer rows) {
        //  设置分页
        PageHelper.startPage(page,rows);
        // 设置查询对象
        Example example = new Example(TbItemCat.class);
        // 设置条件对象
        Example.Criteria criteria = example.createCriteria();
       /* if(!StringUtils.isEmpty(itemCat.getId())){
            criteria.andEqualTo("id",itemCat.getId());

        }*/

        if (!StringUtils.isEmpty(itemCat.getParentId())){
            criteria.andEqualTo("parentId",itemCat.getParentId());
        }
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        PageInfo<TbItemCat> pageInfo = new PageInfo<>(list);

        return  new PageResult(pageInfo.getTotal(),pageInfo.getList());

    }
}
