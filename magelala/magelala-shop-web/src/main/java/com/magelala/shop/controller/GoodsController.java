package com.magelala.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.magelala.pojo.TbGoods;
import com.magelala.pojo.TbItemCat;
import com.magelala.sellergoods.service.GoodsService;
import com.magelala.vo.Goods;
import com.magelala.vo.PageResult;
import com.magelala.vo.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/goods")
@RestController
public class GoodsController {

    @Reference
    private GoodsService goodsService;

    /*
     *条件搜索
     * */
    @PostMapping("/search")
    public PageResult search(@RequestBody TbGoods goods,
                             @RequestParam(value = "page",defaultValue = "1")Integer page,
                             @RequestParam(value = "rows",defaultValue = "10")Integer rows){

        // 从springsecurity获取登录者账号
        String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
        goods.setSellerId(sellerId);

        return goodsService.search(goods,page,rows);
    }

    /*
     * 提交审核*/
    @GetMapping("/updateMarketable")
    public Result  updateMarketable(Long[] ids,String status){

        try {
            goodsService.updateMarketable(ids,status);
            return Result.ok("提交成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("提交失败！");

    }
    /*
    * 提交审核*/
    @GetMapping("/updateStatus")
    public Result  updateStatus(Long[] ids,String status){

        try {
            goodsService.updateStatus(ids,status);
            return Result.ok("提交成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("提交失败！");

    }

    /*批量删除*/
    @GetMapping("/delete")
    public Result delete(Long[] ids){

        try {
            goodsService.deleteByIds(ids);
            return Result.ok("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("删除失败！");
    }

    /*更新*/
    @PostMapping("/update")
    public Result update(@RequestBody Goods goods){
        try {

            //校验商家,新旧都要检验
            TbGoods oldGoods = goodsService.findOne(goods.getGoods().getId());
            String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
            if(!sellerId.equals(oldGoods.getSellerId())|| !sellerId.equals(goods.getGoods().getSellerId())){

                return Result.fail("操作非法");
            }

            goodsService.updateGoods(goods);
            return Result.ok("更新成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("更新失败！");
    }

    /*根据id查询*/
    @GetMapping("/findOne")
    public  Goods findOne(Long id){
        return goodsService.findGoodsById(id);
    }


    /*新增*/
    @PostMapping("/add")
    public Result add(@RequestBody Goods goods){

        try {
            // 从springsecurity获取登录者的ID
            String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
            goods.getGoods().setSellerId(sellerId);
            goods.getGoods().setAuditStatus("0");// 未申请审核




            goodsService.addGoods(goods);
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

        return  goodsService.findPage(page,rows);
    }


    /*查询全部*/
    @GetMapping("/findAll")
    public List<TbGoods> findAll(){
        return goodsService.findAll();
    }

}
