package com.worm.service;

import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BaseService<T> {

    /**
     * 增加一个对象
     *
     * @param t 需要增加的对象
     * @return
     */
    int insert(T t);

    /**
     * 通过主键修改一个对象
     *
     * @param t 需要修改的对象
     * @return
     */
    int updateById(T t);

    /**
     * 通过主键删除一个对象
     *
     * @param id
     * @return
     */
    int deleteById(Object id);

    /**
     * 根据要求批量删除
     * @param t 删除的标准
     * @return
     */
    int delete(T t);

    /**
     * 通过主键查询一个对象
     *
     * @param id
     * @return
     */
    T findById(Object id);

    /**
     * 通过主键列表批量删除对象
     *
     * @param objects 需要的删除对象列表的主键列表
     * @return
     */
    int batchDeleteById(List<Integer> objects);

    /**
     * 通过分页查询符合特定要求的对象列表
     *
     * @param page     当前页码
     * @param pageSize 每一页设置的大小
     * @param t        符合要求的评判标准
     * @return
     */
    PageInfo<T> findPage(Integer page, Integer pageSize, T t);

    /**
     * 通过分页查询所有对象
     *
     * @param page
     * @param pageSize
     * @return
     */
    PageInfo<T> findAllPage(Integer page, Integer pageSize);

}
