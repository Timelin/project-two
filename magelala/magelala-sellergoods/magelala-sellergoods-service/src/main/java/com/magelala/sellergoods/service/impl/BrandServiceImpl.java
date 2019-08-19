package com.magelala.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.magelala.mapper.BrandMapper;
import com.magelala.pojo.TbBrand;
import com.magelala.sellergoods.service.BrandService;
import com.magelala.service.impl.BaseServiceImpl;
import com.magelala.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;


import java.util.List;
import java.util.Map;

@Service(interfaceClass = BrandService.class)
public class BrandServiceImpl  extends BaseServiceImpl<TbBrand> implements BrandService {

    @Autowired
    private BrandMapper brandMapper;


    /*查询全部品牌*/
    @Override
    public List<TbBrand> queryAll() {

        return brandMapper.queryAll();
    }

    /*分页插件查询*/
    @Override
    public List<TbBrand> testPage(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        return brandMapper.selectAll();
    }

    /*条件搜索*/
    @Override
    public PageResult search(TbBrand brand, Integer page, Integer rows) {

        // 设置分页
        PageHelper.startPage(page,rows);

        // 设置查询条件
        // 创建一个查询对象
        Example example = new Example(TbBrand.class);
        // 创建一个查询条件对象
        Example.Criteria criteria = example.createCriteria();

        // 根据首字母查询
        if(!StringUtils.isEmpty(brand.getFirstChar())){
            criteria.andEqualTo("firstChar",brand.getFirstChar());
        }

        //根据品牌名称模糊查询
        if(!StringUtils.isEmpty(brand.getName())){
            criteria.andLike("name","%"+brand.getName()+"%");

        }

        // 根据条件查询
        List<TbBrand> list = brandMapper.selectByExample(example);
        // 转换为分页信息对象
        PageInfo<TbBrand> pageInfo = new PageInfo<>(list);
        // 创建返回结果
        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public List<Map<String, Object>> selectOptionList() {
        return brandMapper.selectOptionList();
    }
}
