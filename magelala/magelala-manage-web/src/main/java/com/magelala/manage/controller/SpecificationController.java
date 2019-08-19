package com.magelala.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.magelala.pojo.TbBrand;
import com.magelala.pojo.TbSpecification;
import com.magelala.sellergoods.service.SpecificationService;
import com.magelala.vo.PageResult;
import com.magelala.vo.Result;
import com.magelala.vo.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/specification")
@RestController
public class SpecificationController {

    @Reference
    private SpecificationService specificationService;


    /*
     * 查询规格列表，返回的数据符合select2格式*/
    @GetMapping("/selectOptionList")
    public List<Map<String,Object>> selectOptionList(){
        return specificationService.selectOptionList();
    }

    //批量删除
    @GetMapping("/delete")
    public Result delete(Long[] ids){
        try {
            specificationService.deleteSpecificationByIds(ids);
            return Result.ok("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("修改失败");
    }

    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Specification specification) {
        try {
            specificationService.update(specification);
            return Result.ok("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("修改失败");
    }
    /*根据id查询*/
    @GetMapping("/findOne")
    public Specification findOne(Long id) {
        return specificationService.findOne(id);
    }


    /*新增规格*/
    @PostMapping("/add")
    public Result add(@RequestBody Specification specification) {
        try {
            specificationService.add(specification);
            return Result.ok("增加成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("增加失败");
    }

    //条件搜索
    @PostMapping("/search")
    public PageResult search(@RequestBody TbSpecification specification,
                             @RequestParam(value = "page",defaultValue = "1")Integer page,
                             @RequestParam(value = "rows",defaultValue = "10")Integer rows){

        return specificationService.search(specification,page,rows);
    }

    // 查询所有列表
    @GetMapping("/findAll")
    public List<TbSpecification> findAll(){

        return specificationService.findAll();
    }
}
