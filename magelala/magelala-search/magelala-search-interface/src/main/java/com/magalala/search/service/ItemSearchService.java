package com.magalala.search.service;

import com.magelala.pojo.TbItem;
import com.magelala.service.BaseService;

import java.util.Map;

public interface ItemSearchService {
    Map<String,Object> search(Map<String, Object> searchMap);

}
