package com.magelala.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.magelala.service.BaseService;
import com.magelala.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.io.Serializable;
import java.util.List;

public abstract class BaseServiceImpl<T>  implements BaseService<T>{

    // spring 4.x版本之后引入的泛型依赖注入

    @Autowired
    private Mapper<T> mapper;

    /*根据主键查询*/
    @Override
    public T findOne(Serializable id) {
        return mapper.selectByPrimaryKey(id);
    }

    /*查询全部*/
    @Override
    public List<T> findAll() {
        return mapper.selectAll();
    }

    /*根据条件查询*/
    @Override
    public List<T> findByWhere(T t) {
        return mapper.select(t);
    }

    /*分页查询*/
    @Override
    public PageResult findPage(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<T> list = mapper.selectAll();
        PageInfo<T> pageInfo = new PageInfo<>(list);
        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /*根据条件查询*/
    @Override
    public PageResult findPage(Integer page, Integer row, T t) {
        PageHelper.startPage(page,row);
        List<T> list = mapper.select(t);
        PageInfo<T> pageInfo = new PageInfo<>(list);

        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /*新增*/
    @Override
    public void add(T t) {
        mapper.insertSelective(t);

    }


     /*更新*/
    @Override
    public void update(T t) {
        mapper.updateByPrimaryKeySelective(t);

    }

    /*批量删除*/
    @Override
    public void deleteByIds(Serializable[] ids) {
        if(ids != null&& ids.length>0){
            for (Serializable id :ids){
                mapper.deleteByPrimaryKey(id);
            }
        }

    }
}
