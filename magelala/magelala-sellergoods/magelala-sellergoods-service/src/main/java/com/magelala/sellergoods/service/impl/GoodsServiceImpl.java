package com.magelala.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.magelala.pojo.TbGoods;
import com.magelala.sellergoods.service.GoodsService;
import com.magelala.service.impl.BaseServiceImpl;

@Service(interfaceClass = GoodsService.class)
public class GoodsServiceImpl extends BaseServiceImpl<TbGoods> implements GoodsService {


}
