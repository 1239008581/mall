package com.worm.user.service;

import com.worm.service.BaseService;
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
     * 修改订单商品数量
     * @param order
     * @return
     */
    int updateOrderCommodityNum(Order order);
}
