package com.worm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.worm.service.BaseService;
import com.worm.utils.MyMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BaseServiceImpl<T, M extends MyMapper<T>> implements BaseService<T> {

    @Autowired
    private M m;

    @Override
    public int insert(T t) {
        if (t == null) {
            return 0;
        }
        return m.insertSelective(t);
    }

    @Override
    public int updateById(T t) {
        if (t == null) {
            return 0;
        }
        return m.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Object id) {
        return m.deleteByPrimaryKey(id);
    }

    @Override
    public int delete(T t) {
        if (t == null) {
            return 0;
        }
        return delete(t);
    }

    @Override
    public T findById(Object id) {
        return m.selectByPrimaryKey(id);
    }

    @Override
    public int batchDeleteById(List<Integer> objects) {
        int result = 0;
        for (Object object:objects) {
            m.deleteByPrimaryKey(object);
            result++;
        }
        return result;
    }

    @Override
    public PageInfo<T> findPage(Integer page, Integer pageSize, T t) {
        PageHelper.startPage(page,pageSize);
        List<T> list = m.select(t);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<T> findAllPage(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<T> list = m.selectAll();
        return new PageInfo<>(list);
    }
}
