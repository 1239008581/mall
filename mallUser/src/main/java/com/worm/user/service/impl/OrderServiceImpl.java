package com.worm.user.service.impl;

import com.worm.service.impl.BaseServiceImpl;
import com.worm.user.dao.OrderMapper;
import com.worm.user.dao.PayInfoMapper;
import com.worm.user.dao.UserMapper;
import com.worm.user.domain.dto.ShoppingCartDTO;
import com.worm.user.domain.entity.Order;
import com.worm.user.domain.entity.PayInfo;
import com.worm.user.domain.entity.User;
import com.worm.user.handler.exception.PayException;
import com.worm.user.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class OrderServiceImpl extends BaseServiceImpl<Order, OrderMapper> implements OrderService {

    private final OrderMapper orderMapper;
    private final UserMapper userMapper;
    private final PayInfoMapper payInfoMapper;

    @Override
    public Boolean payOrders(ShoppingCartDTO shoppingCartDTO) throws PayException {
        //获取购物车信息
        Integer userId = shoppingCartDTO.getUserId();
        List<Integer> orderIds = shoppingCartDTO.getOrderIds();
        Integer totalPrice = shoppingCartDTO.getTotalPrice();
        //获取支付用户初始信息,判断用户余额是否足够完成支付
        User user = userMapper.selectByPrimaryKey(userId);
        Float money = user.getMoney();
        if (money < totalPrice) {
            throw new PayException("用户余额不足！");
        }
        //循环为所有订单付款
        for (Integer orderId : orderIds) {
            //获取订单信息并判断订单是否已经被支付了
            Order order = orderMapper.selectByPrimaryKey(orderId);
            if (order.getStatus() == 1) {
                throw new PayException("订单已支付，无法重复支付！");
            }
            //根据订单总价格扣减用户余额
            userMapper.updateByPrimaryKeySelective(
                    User.builder()
                            .id(userId)
                            .money(money - order.getPrice())
                            .build()
            );
            //改变订单支付状态
            orderMapper.updateByPrimaryKeySelective(
                    Order.builder()
                    .id(orderId)
                    .status(1)
                    .build()
            );
            //记录支付信息
            payInfoMapper.insertSelective(
                    PayInfo.builder()
                    .userId(userId)
                    .orderId(orderId)
                    .createTime(new Date())
                    .build()
            );
        }
        return true;
    }

    @Override
    public int updateOrderCommodityNum(Order order) {
        //获取订单信息
        Order realOrder = orderMapper.selectByPrimaryKey(order.getId());
        //判断是否已支付，已支付则无法修改
        if (realOrder.getStatus() == 1) {
            throw new PayException("订单已支付，无法修改！");
        }
        //修改数量和金额
        float unitPrice = realOrder.getPrice() / realOrder.getCommodityNum();
        order.setPrice(unitPrice * order.getCommodityNum());
        int result = orderMapper.updateByPrimaryKeySelective(order);
        return result;
    }
}
