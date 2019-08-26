package com.magelala.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.magelala.mapper.SpecificationOptionMapper;
import com.magelala.mapper.TypeTemplateMapper;
import com.magelala.pojo.TbSpecificationOption;
import com.magelala.pojo.TbTypeTemplate;
import com.magelala.sellergoods.service.TypeTemplateService;
import com.magelala.service.impl.BaseServiceImpl;
import com.magelala.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service(interfaceClass = TypeTemplateService.class)
public class TypeTemplateServiceImpl extends BaseServiceImpl<TbTypeTemplate> implements TypeTemplateService {


    @Autowired
    private TypeTemplateMapper typeTemplateMapper;
    @Autowired
    private SpecificationOptionMapper specificationOptionMapper;
    //条件搜索
    @Override
    public PageResult search(TbTypeTemplate typeTemplate, Integer page, Integer rows) {

        // 设置分页对象
        PageHelper.startPage(page,rows);
        //设置查询对象
        Example example = new Example(TbTypeTemplate.class);
        // 设置条件对象
        Example.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(typeTemplate.getName())){
            criteria.andLike("name","%"+typeTemplate.getName()+"%");

        }
        List<TbTypeTemplate> list = typeTemplateMapper.selectByExample(example);
        PageInfo<TbTypeTemplate> pageInfo = new PageInfo<>(list);

        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public List<Map> findSpecList(Long id) {
        //查询规格选项
        TbTypeTemplate typeTemplate = findOne(id);

        // 获取规格模板并转换为List
        List<Map> specList = JSONArray.parseArray(typeTemplate.getSpecIds(), Map.class);
        for (Map map : specList){

            // 查询规格对应的选项
            TbSpecificationOption param = new TbSpecificationOption();
            param.setSpecId(Long.parseLong(map.get("id").toString()));
            List<TbSpecificationOption> options = specificationOptionMapper.select(param);

            map.put("options",options);

        }

        return specList;
    }
}
