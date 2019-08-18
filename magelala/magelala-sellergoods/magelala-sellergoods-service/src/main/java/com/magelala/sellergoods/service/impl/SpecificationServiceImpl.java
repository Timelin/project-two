package com.magelala.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.magelala.mapper.SpecificationMapper;
import com.magelala.mapper.SpecificationOptionMapper;
import com.magelala.pojo.TbBrand;
import com.magelala.pojo.TbSpecification;
import com.magelala.pojo.TbSpecificationOption;
import com.magelala.sellergoods.service.SpecificationService;
import com.magelala.service.impl.BaseServiceImpl;
import com.magelala.vo.PageResult;
import com.magelala.vo.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;

@Service(interfaceClass = SpecificationService.class)
public class SpecificationServiceImpl extends BaseServiceImpl<TbSpecification> implements SpecificationService {

    @Autowired
    private SpecificationMapper specificationMapper;
    @Autowired
    private SpecificationOptionMapper specificationOptionMapper;

    // 批量删除
    @Override
    public void deleteSpecificationByIds(Long[] ids) {

        // 批量删除规格
        deleteByIds(ids);

        // 批量删除规格选项
        Example example = new Example(TbSpecificationOption.class);
        example.createCriteria().andIn("specId", Arrays.asList(ids));
        specificationOptionMapper.deleteByExample(example);

    }


    // 更新
    @Override
    public void update(Specification specification) {
        //1、更新规格
        update(specification.getSpecification());

        //2、根据规格id删除对应的所有选项
        TbSpecificationOption param = new TbSpecificationOption();
        param.setSpecId(specification.getSpecification().getId());
        specificationOptionMapper.delete(param);

        //3、保存规格选项列表
        if (specification.getSpecificationOptionList() != null && specification.getSpecificationOptionList().size() > 0) {
            for (TbSpecificationOption specificationOption : specification.getSpecificationOptionList()) {
                specificationOption.setSpecId(specification.getSpecification().getId());
                specificationOptionMapper.insertSelective(specificationOption);
            }
        }
    }


    /*根据id查询实体类组合*/
    @Override
    public Specification findOne(Long id) {
        Specification specification = new Specification();
        //1、规格
        TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);
        specification.setSpecification(tbSpecification);

        //2、根据规格id查询规格选项
        TbSpecificationOption param = new TbSpecificationOption();
        param.setSpecId(id);
        List<TbSpecificationOption> specificationOptionList = specificationOptionMapper.select(param);
        specification.setSpecificationOptionList(specificationOptionList);

        return specification;
    }


    /*新增*/
    @Override
    public void add(Specification specification) {
        //1、保存规格；通用mapper在执行完新增方法之后会回填主键到对象中
        add(specification.getSpecification());

        //2、保存规格选项列表
        if (specification.getSpecificationOptionList() != null && specification.getSpecificationOptionList().size() > 0) {
            for (TbSpecificationOption specificationOption : specification.getSpecificationOptionList()) {
                specificationOption.setSpecId(specification.getSpecification().getId());
                specificationOptionMapper.insertSelective(specificationOption);
            }
        }
    }

    /*条件搜索*/
    @Override
    public PageResult search(TbSpecification specification, Integer page, Integer rows) {
        // 设置分页
        PageHelper.startPage(page,rows);

        // 设置查询条件
        //创建查询对象
        Example example = new Example(TbSpecification.class);
        // 创建查询条件对象
        Example.Criteria criteria = example.createCriteria();
        //根据规格名称模糊查询
        if(!StringUtils.isEmpty(specification.getSpecName())){
            criteria.andLike("specName","%"+specification.getSpecName()+"%");

        }
        // 根据条件查询
        List<TbSpecification> list = specificationMapper.selectByExample(example);
        // 转换为分页对象
        PageInfo<TbSpecification> pageInfo = new PageInfo<>(list);

        // 返回结果
        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }



}
