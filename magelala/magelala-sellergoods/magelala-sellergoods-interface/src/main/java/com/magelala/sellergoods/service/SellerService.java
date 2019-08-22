package com.magelala.sellergoods.service;

import com.magelala.pojo.TbSeller;
import com.magelala.service.BaseService;
import com.magelala.vo.PageResult;

public interface SellerService extends BaseService<TbSeller> {
    PageResult search(TbSeller seller, Integer page, Integer rows);
}
