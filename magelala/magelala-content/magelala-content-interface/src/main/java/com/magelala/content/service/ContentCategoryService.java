package com.magelala.content.service;

import com.magelala.pojo.TbContentCategory;
import com.magelala.pojo.TbSeller;
import com.magelala.service.BaseService;
import com.magelala.vo.PageResult;

public interface ContentCategoryService extends BaseService<TbContentCategory> {
    PageResult search(TbContentCategory contentCategory, Integer page, Integer rows);
}
