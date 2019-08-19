package com.magelala.sellergoods.service;

import com.magelala.pojo.TbTypeTemplate;
import com.magelala.service.BaseService;
import com.magelala.vo.PageResult;

public interface TypeTemplateService extends BaseService<TbTypeTemplate> {


    // 条件搜索
    PageResult search(TbTypeTemplate typeTemplate, Integer page, Integer rows);
}
