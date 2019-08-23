package com.magelala.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;

import com.magelala.pojo.TbSeller;
import com.magelala.sellergoods.service.SellerService;
import com.magelala.vo.PageResult;
import com.magelala.vo.Result;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/seller")
@RestController
public class SellerController {

    @Reference
    private SellerService sellerService;


    /*分页插件分页查询*/
    @GetMapping("/testPage")
    public List<TbSeller> testPage(Integer page, Integer rows){

        return (List<TbSeller>) sellerService.findPage(page,rows).getRows();
    }



    /*
     *条件搜索
     * */
    @PostMapping("/search")
    public PageResult search(@RequestBody TbSeller seller,
                             @RequestParam(value = "page",defaultValue = "1")Integer page,
                             @RequestParam(value = "rows",defaultValue = "10")Integer rows){
        return sellerService.search(seller,page,rows);
    }

    /*批量删除*/
    @GetMapping("/delete")
    public Result delete(String[] ids){

        try {
            sellerService.deleteByIds(ids);
            return Result.ok("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("删除失败！");
    }

    /*更新品牌*/
    @PostMapping("/update")
    public Result update(@RequestBody TbSeller seller){
        try {
            sellerService.update(seller);
            return Result.ok("更新成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("更新失败！");
    }

    /*根据id查询品牌*/
    @GetMapping("/findOne")
    public  TbSeller findOne(String id){
        return sellerService.findOne(id);
    }


    /*新增品牌*/
    @PostMapping("/add")
    public Result add(@RequestBody TbSeller seller){

        try {
            seller.setStatus("0");// 未审核
            // 密码加密
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            seller.setPassword(passwordEncoder.encode(seller.getPassword()));
            sellerService.add(seller);
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

        return  sellerService.findPage(page,rows);
    }


    /*查询全部品牌*/
    @GetMapping("/findAll")
    public List<TbSeller> findAll(){
        return sellerService.findAll();
    }



}
