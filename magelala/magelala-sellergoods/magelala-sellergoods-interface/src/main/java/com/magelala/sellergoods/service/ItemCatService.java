package com.magelala.sellergoods.service;

import com.magelala.pojo.TbItemCat;
import com.magelala.service.BaseService;
import com.magelala.vo.PageResult;

public interface ItemCatService extends BaseService<TbItemCat>{

    PageResult search(TbItemCat itemCat, Integer page, Integer rows);
}
