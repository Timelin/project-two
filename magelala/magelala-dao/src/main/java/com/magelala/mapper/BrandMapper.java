package com.magelala.mapper;

import com.magelala.pojo.TbBrand;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BrandMapper extends Mapper<TbBrand>{

    public List<TbBrand> queryAll();



}
