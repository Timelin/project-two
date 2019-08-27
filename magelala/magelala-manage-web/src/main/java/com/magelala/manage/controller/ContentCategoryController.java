package com.magelala.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.magelala.content.service.ContentCategoryService;
import com.magelala.pojo.TbContentCategory;
import com.magelala.pojo.TbSeller;
import com.magelala.vo.PageResult;
import com.magelala.vo.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName:ContentCategoryController
 * @Author:Timelin
 **/
@RequestMapping("/contentCategory")
@RestController
public class ContentCategoryController {

    @Reference
    private ContentCategoryService contentCategoryService;



    /*
     *条件搜索
     * */
    @PostMapping("/search")
    public PageResult search(@RequestBody TbContentCategory contentCategory,
                             @RequestParam(value = "page",defaultValue = "1")Integer page,
                             @RequestParam(value = "rows",defaultValue = "10")Integer rows){
        return contentCategoryService.search(contentCategory,page,rows);
    }

    /*批量删除*/
    @GetMapping("/delete")
    public Result delete(Long[] ids){

        try {
            contentCategoryService.deleteByIds(ids);
            return Result.ok("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("删除失败！");
    }

    /*更新*/
    @PostMapping("/update")
    public Result update(@RequestBody TbContentCategory contentCategory){
        try {
            contentCategoryService.update(contentCategory);
            return Result.ok("更新成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("更新失败！");
    }

    /*根据id查询*/
    @GetMapping("/findOne")
    public  TbContentCategory findOne(long id){
        return contentCategoryService.findOne(id);
    }


    /*新增*/
    @PostMapping("/add")
    public Result add(@RequestBody TbContentCategory contentCategory){

        try {

            contentCategoryService.add(contentCategory);
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

        return  contentCategoryService.findPage(page,rows);
    }


    /*查询全部*/
    @GetMapping("/findAll")
    public List<TbContentCategory> findAll(){
        return contentCategoryService.findAll();
    }


}
