package com.worm.user.dao;

import com.worm.user.domain.entity.Order;
import com.worm.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper extends MyMapper<Order> {
    List<Integer> getALLCommodityId(@Param("userId") Integer userId);
}