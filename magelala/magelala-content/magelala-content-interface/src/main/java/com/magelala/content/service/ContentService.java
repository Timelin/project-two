package com.magelala.content.service;

import com.magelala.pojo.TbContent;
import com.magelala.service.BaseService;
import com.magelala.vo.PageResult;

import java.util.List;

public interface ContentService extends BaseService<TbContent>  {
    PageResult search(TbContent content, Integer page, Integer rows);


    List<TbContent> findContentListByCategoryId(Long categoryId);
}
