package com.magelala.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.magelala.content.service.ContentService;
import com.magelala.pojo.TbContent;
import com.magelala.pojo.TbContentCategory;
import com.magelala.vo.PageResult;
import com.magelala.vo.Result;
import org.springframework.web.bind.annotation.*;

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

    /*
     *条件搜索
     * */
    @PostMapping("/search")
    public PageResult search(@RequestBody TbContent content,
                             @RequestParam(value = "page",defaultValue = "1")Integer page,
                             @RequestParam(value = "rows",defaultValue = "10")Integer rows){
        return contentService.search(content,page,rows);
    }

    /*批量删除*/
    @GetMapping("/delete")
    public Result delete(Long[] ids){

        try {
            contentService.deleteByIds(ids);
            return Result.ok("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("删除失败！");
    }

    /*更新*/
    @PostMapping("/update")
    public Result update(@RequestBody TbContent content){
        try {
            contentService.update(content);
            return Result.ok("更新成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("更新失败！");
    }

    /*根据id查询*/
    @GetMapping("/findOne")
    public  TbContent findOne(long id){
        return contentService.findOne(id);
    }


    /*新增*/
    @PostMapping("/add")
    public Result add(@RequestBody TbContent content){

        try {

            contentService.add(content);
            return Result.ok("新增成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("新增失败");
    }
    /*分页查询
     **/
    @GetMapping("/findPage")
    public PageResult findPage(@RequestParam(value ="page",defaultValue = "1")Integer page,
                               @RequestParam(value = "rows",defaultValue ="10" )Integer rows) {

        return  contentService.findPage(page,rows);
    }


    /*查询全部*/
    @GetMapping("/findAll")
    public List<TbContent> findAll(){
        return contentService.findAll();
    }


}

