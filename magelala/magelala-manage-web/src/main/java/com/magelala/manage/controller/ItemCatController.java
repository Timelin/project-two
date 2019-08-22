package com.magelala.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.magelala.pojo.TbBrand;
import com.magelala.pojo.TbItemCat;
import com.magelala.sellergoods.service.ItemCatService;
import com.magelala.vo.PageResult;
import com.magelala.vo.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassNameItemCatController
 * @Author:Timelin
 **/
@RequestMapping("/itemCat")
@RestController
public class ItemCatController {

    @Reference
    private ItemCatService itemCatService;


    /*根据父类id查询商品分类信息*/
    @GetMapping("/findByParentId")
    public List<TbItemCat> findByParentId(Long parentId ){
        TbItemCat itemCat = new TbItemCat();
        itemCat.setParentId(parentId);
        // 条件查询
        return  itemCatService.findByWhere(itemCat);
    }

    /*
     *条件搜索
     * */
    @PostMapping("/search")
    public PageResult search(@RequestBody TbItemCat itemCat,
                             @RequestParam(value = "page",defaultValue = "1")Integer page,
                             @RequestParam(value = "rows",defaultValue = "10")Integer rows){

        return itemCatService.search(itemCat,page,rows);
    }
    /*批量删除*/
    @GetMapping("/delete")
    public Result delete(Long[] ids){

        try {
            itemCatService.deleteByIds(ids);
            return Result.ok("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("删除失败！");
    }

    /*更新*/
    @PostMapping("/update")
    public Result update(@RequestBody TbItemCat itemCat){
        try {
            itemCatService.update(itemCat);
            return Result.ok("更新成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("更新失败！");
    }

    /*根据id查询*/
    @GetMapping("/findOne")
    public  TbItemCat findOne(Long id){
        return itemCatService.findOne(id);
    }


    /*新增*/
    @PostMapping("/add")
    public Result add(@RequestBody TbItemCat itemCat){

        try {
            itemCatService.add(itemCat);
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

        return  itemCatService.findPage(page,rows);
    }


    /*查询全部*/
    @GetMapping("/findAll")
    public List<TbItemCat> findAll(){
        return itemCatService.findAll();
    }



}
