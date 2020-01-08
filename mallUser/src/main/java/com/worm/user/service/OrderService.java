package com.worm.user.service;

import com.github.pagehelper.PageInfo;
import com.worm.service.BaseService;
import com.worm.user.domain.dto.OrderDTO;
import com.worm.user.domain.dto.ShoppingCartDTO;
import com.worm.user.domain.entity.Order;
import com.worm.user.handler.exception.PayException;

import java.util.List;

public interface OrderService extends BaseService<Order> {

    /**
     * 支付一次购物车
     * @param shoppingCartDTO 购物车信息
     * @return
     * @throws Exception
     */
    Boolean payOrders(ShoppingCartDTO shoppingCartDTO) throws PayException;

    /**
     * 用户增加一条订单
     * @param order 订单信息
     * @return
     */
    OrderDTO addOrder(Order order);

    /**
     * 分页查询订单
     * @param page
     * @param orderPageSize
     * @param order
     * @return
     */
    PageInfo<OrderDTO> findAllOrder(Integer page, Integer orderPageSize, Order order);
}
