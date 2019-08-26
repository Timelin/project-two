package com.magelala.sellergoods.service;

import com.magelala.pojo.TbGoods;
import com.magelala.service.BaseService;
import com.magelala.vo.Goods;
import com.magelala.vo.PageResult;

public interface GoodsService extends BaseService<TbGoods> {
    PageResult search(TbGoods goods, Integer page, Integer rows);

    void addGoods(Goods goods);

    Goods findGoodsById(Long id);

    void updateGoods(Goods goods);

    void updateStatus(Long[] ids, String status);

    void deleteGoodsByIds(Long[] ids);

    void updateMarketable(Long[] ids, String status);
}
