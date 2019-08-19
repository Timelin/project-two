package com.magelala.sellergoods.service;

import com.magelala.pojo.TbBrand;
import com.magelala.pojo.TbSpecification;
import com.magelala.service.BaseService;
import com.magelala.vo.PageResult;
import com.magelala.vo.Specification;

import java.util.List;
import java.util.Map;

public interface SpecificationService extends BaseService<TbSpecification> {

    //根据id查询实体类组合
    Specification findOne(Long id);

    //新增
    void add(Specification specification );

    //条件搜索
    PageResult search(TbSpecification specification , Integer page, Integer rows);

    // 更新
    void update(Specification specification);

    void deleteSpecificationByIds(Long[] ids);

    List<Map<String,Object>> selectOptionList();

}
