package com.magelala.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.magelala.mapper.SellerMapper;
import com.magelala.pojo.TbSeller;
import com.magelala.sellergoods.service.SellerService;
import com.magelala.service.impl.BaseServiceImpl;
import com.magelala.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service(interfaceClass = SellerService.class)
public class SellerServiceImpl extends BaseServiceImpl<TbSeller> implements SellerService {

    @Autowired
    private SellerMapper sellerMapper;
    @Override
    public PageResult search(TbSeller seller, Integer page, Integer rows) {

        // 设置分页
        PageHelper.startPage(page,rows);
        // 设置查询对象
        Example example = new Example(TbSeller.class);
        // 设置条件对象
        Example.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(seller.getStatus())){
            criteria.andEqualTo("status",seller.getStatus());
        }
        if(!StringUtils.isEmpty(seller.getNickName())){
           criteria.andLike("nickName","%"+seller.getNickName()+"%");
        }
        if(!StringUtils.isEmpty(seller.getName())){
            criteria.andLike("name","%"+seller.getName()+"%");
        }
        List<TbSeller> list = sellerMapper.selectByExample(example);
        PageInfo<TbSeller> pageInfo = new PageInfo<>(list);
        return new PageResult(pageInfo.getTotal(),pageInfo.getList());


    }
}
