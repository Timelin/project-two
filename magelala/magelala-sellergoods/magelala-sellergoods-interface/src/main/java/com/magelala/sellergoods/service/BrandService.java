package com.magelala.sellergoods.service;

import com.magelala.pojo.TbBrand;
import com.magelala.service.BaseService;
import com.magelala.vo.PageResult;

import java.util.List;
import java.util.Map;

public interface BrandService extends BaseService<TbBrand> {
    public List<TbBrand> queryAll();

    List<TbBrand> testPage(Integer page, Integer rows);

    PageResult search(TbBrand brand,Integer page,Integer rows);

    List<Map<String,Object>> selectOptionList();

}
