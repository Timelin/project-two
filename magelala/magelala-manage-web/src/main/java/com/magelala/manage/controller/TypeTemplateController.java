package com.magelala.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.magelala.pojo.TbSpecification;
import com.magelala.pojo.TbTypeTemplate;
import com.magelala.sellergoods.service.TypeTemplateService;
import com.magelala.vo.PageResult;
import com.magelala.vo.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/typeTemplate")
@RestController
public class TypeTemplateController {

    @Reference
    private TypeTemplateService typeTemplateService;



    /*批量删除*/
    @GetMapping("/delete")
    public Result delete(Long[] ids){

        try {
            typeTemplateService.deleteByIds(ids);
            return Result.ok("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("删除失败！");
    }
    /*更新*/
    @PostMapping("/update")
    public Result update(@RequestBody TbTypeTemplate typeTemplate){
        try {
            typeTemplateService.update(typeTemplate);
            return Result.ok("更新成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("更新失败！");
    }

    /*根据id查询*/
    @GetMapping("/findOne")
    public  TbTypeTemplate findOne(Long id){
        return typeTemplateService.findOne(id);
    }


    /*新增*/
    @PostMapping("/add")
    public Result add(@RequestBody TbTypeTemplate typeTemplate) {
        try {
            typeTemplateService.add(typeTemplate);
            return Result.ok("增加成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("增加失败");
    }

    //条件搜索
    @PostMapping("/search")
    public PageResult search(@RequestBody TbTypeTemplate typeTemplate,
                             @RequestParam(value = "page",defaultValue = "1")Integer page,
                             @RequestParam(value = "rows",defaultValue = "10")Integer rows){

        return typeTemplateService.search(typeTemplate,page,rows);
    }


    @RequestMapping("/findAll")
    public List<TbTypeTemplate> findAll() {
        return typeTemplateService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult findPage(@RequestParam(value = "page", defaultValue = "1")Integer page,
                               @RequestParam(value = "rows", defaultValue = "10")Integer rows) {
        return typeTemplateService.findPage(page, rows);
    }


}

