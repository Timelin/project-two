package com.magelala.sellergoods.service;

import com.magelala.pojo.TbTypeTemplate;
import com.magelala.service.BaseService;
import com.magelala.vo.PageResult;

import java.util.List;
import java.util.Map;

public interface TypeTemplateService extends BaseService<TbTypeTemplate> {


    // 条件搜索
    PageResult search(TbTypeTemplate typeTemplate, Integer page, Integer rows);

    //根据分类模板id查询其对应的规格及其规格的选项
    List<Map> findSpecList(Long id);
}
