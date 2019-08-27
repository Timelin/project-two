package com.magelala.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.magelala.content.service.ContentService;
import com.magelala.pojo.TbContent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName:ContentController
 * @Author:Timelin
 **/
@RequestMapping("/content")
@RestController
public class ContentController {

    @Reference
    private ContentService contentService;

    /**
    *根据内容分类id查询启动的内容列表并降序排序
    * @Author: Timelin
    * @Param : categoryId
    * @Return : List<TbContent>
    **/
    @GetMapping("/findContentListByCategoryId")
    public List<TbContent> findContentListByCategoryId(Long categoryId){
     return    contentService.findContentListByCategoryId(categoryId);
    }
}
