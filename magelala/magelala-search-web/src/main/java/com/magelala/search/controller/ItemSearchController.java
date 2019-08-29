package com.magelala.search.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.magalala.search.service.ItemSearchService;
import com.magelala.pojo.TbItem;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName:ItemSearchController
 * @Author:Timelin
 **/
@RequestMapping("/itemSearch")
@RestController
public class ItemSearchController {


    @Reference
    private ItemSearchService itemSearchService;


    /**
    *根据搜索关键字搜索商品列表
    * @Author: Timelin
    * @Param : searchMap 搜索条件
    * @Return : Map<String,Object>
    **/
    @PostMapping("/search")
    public Map<String,Object> search(@RequestBody Map<String,Object> searchMap){
        return itemSearchService.search(searchMap);
    }



}
