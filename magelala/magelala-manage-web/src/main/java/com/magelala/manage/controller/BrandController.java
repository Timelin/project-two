package com.magelala.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.magelala.pojo.TbBrand;
import com.magelala.sellergoods.service.BrandService;
import com.magelala.vo.PageResult;
import com.magelala.vo.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@RequestMapping("/brand")
@RestController
public class BrandController {

    @Reference
    private BrandService brandService;


    /*分页插件分页查询*/
    @GetMapping("/testPage")
    public List<TbBrand> testPage(Integer page,Integer rows){

        return (List<TbBrand>) brandService.findPage(page,rows).getRows();
    }

    /*
    * 查询品牌列表，返回的数据符合select2格式*/
    @GetMapping("/selectOptionList")
    public List<Map<String,Object>> selectOptionList(){
        return brandService.selectOptionList();
    }

    /*
    *条件搜索
    * */
    @PostMapping("/search")
    public PageResult search(@RequestBody TbBrand brand,
                             @RequestParam(value = "page",defaultValue = "1")Integer page,
                             @RequestParam(value = "rows",defaultValue = "10")Integer rows){
        return brandService.search(brand,page,rows);
    }

    /*批量删除*/
    @GetMapping("/delete")
    public Result delete(Long[] ids){

        try {
            brandService.deleteByIds(ids);
            return Result.ok("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("删除失败！");
    }

    /*更新品牌*/
    @PostMapping("/update")
    public Result update(@RequestBody TbBrand brand){
        try {
            brandService.update(brand);
            return Result.ok("更新成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("更新失败！");
    }

    /*根据id查询品牌*/
    @GetMapping("/findOne")
    public  TbBrand findOne(Long id){
        return brandService.findOne(id);
    }


    /*新增品牌*/
    @PostMapping("/add")
    public Result add(@RequestBody TbBrand brand){

        try {
            brandService.add(brand);
            return Result.ok("新增成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Result.fail("新增失败");
    }
    /*分页查询品牌
    **/
    @GetMapping("/findPage")
    public PageResult findPage(@RequestParam(value ="page",defaultValue = "1")Integer page,
                               @RequestParam(value = "rows",defaultValue ="10" )Integer rows) {

        return  brandService.findPage(page,rows);
    }


    /*查询全部品牌*/
    @GetMapping("/findAll")
    public List<TbBrand> findAll(){
        return brandService.findAll();
    }


}
