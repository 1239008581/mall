package com.worm.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.worm.service.impl.BaseServiceImpl;
import com.worm.user.dao.OrderMapper;
import com.worm.user.dao.PayInfoMapper;
import com.worm.user.dao.UserMapper;
import com.worm.user.domain.dto.CommodityCollectionDTO;
import com.worm.user.domain.dto.CommodityDTO;
import com.worm.user.domain.dto.OrderDTO;
import com.worm.user.domain.dto.ShoppingCartDTO;
import com.worm.user.domain.entity.Collection;
import com.worm.user.domain.entity.Order;
import com.worm.user.domain.entity.PayInfo;
import com.worm.user.domain.entity.User;
import com.worm.user.feignclient.CommodityFeignClient;
import com.worm.user.handler.exception.PayException;
import com.worm.user.service.OrderService;
import com.worm.utils.JsonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class OrderServiceImpl extends BaseServiceImpl<Order, OrderMapper> implements OrderService {

    private final OrderMapper orderMapper;
    private final UserMapper userMapper;
    private final PayInfoMapper payInfoMapper;
    private final CommodityFeignClient commodityFeignClient;

    @Override
    public Boolean payOrders(ShoppingCartDTO shoppingCartDTO) throws PayException {
//        //获取购物车信息
//        Integer userId = shoppingCartDTO.getUserId();
//        List<Integer> orderIds = shoppingCartDTO.getOrderIds();
//        Integer totalPrice = shoppingCartDTO.getTotalPrice();
//        //获取支付用户初始信息,判断用户余额是否足够完成支付
//        User user = userMapper.selectByPrimaryKey(userId);
//        Float money = user.getMoney();
//        if (money < totalPrice) {
//            throw new PayException("用户余额不足！");
//        }
//        //循环为所有订单付款
//        for (Integer orderId : orderIds) {
//            //获取订单信息并判断订单是否已经被支付了
//            Order order = orderMapper.selectByPrimaryKey(orderId);
//            if (order.getStatus() == 1) {
//                throw new PayException("订单已支付，无法重复支付！");
//            }
//            //根据订单总价格扣减用户余额
//            userMapper.updateByPrimaryKeySelective(
//                    User.builder()
//                            .id(userId)
//                            .money(money - order.getPrice())
//                            .build()
//            );
//            //改变订单支付状态
//            orderMapper.updateByPrimaryKeySelective(
//                    Order.builder()
//                    .id(orderId)
//                    .status(1)
//                    .build()
//            );
//            //记录支付信息
//            payInfoMapper.insertSelective(
//                    PayInfo.builder()
//                    .userId(userId)
//                    .orderId(orderId)
//                    .createTime(new Date())
//                    .build()
//            );
//        }
        return true;
    }

    @Override
    public OrderDTO addOrder(Order order) {
        order.setUserId(order.getUserId());
        order.setCreateTime(new Date());
        orderMapper.insert(order);
        CommodityDTO commodityDTO = commodityFeignClient.findCommodity(order.getCommodityId());
        return getOrderDTO(order,commodityDTO);
    }

    @Override
    public PageInfo<OrderDTO> findAllOrder(Integer page, Integer orderPageSize, Order order) {
        PageHelper.startPage(page, orderPageSize);
        List<Order> orderList = orderMapper.select(order);
        List<CommodityDTO> commodityList = commodityFeignClient.findCommodityList(
                orderList.stream()
                        .map(Order::getCommodityId)
                        .collect(Collectors.toList())
        );
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (int i = 0; i < orderList.size(); i++) {
            OrderDTO orderDTO = new OrderDTO();
            Order realOrder = orderList.get(i);
            CommodityDTO commodityDTO = commodityList.get(i);
            BeanUtils.copyProperties(realOrder, orderDTO);
            BeanUtils.copyProperties(commodityDTO, orderDTO);
            orderDTO.setId(realOrder.getId());
            orderDTO.setCommodityId(realOrder.getCommodityId());
            orderDTO.setTotalPrice(realOrder.getCommodityNum() * commodityDTO.getPrice());
            orderDTOList.add(orderDTO);
        }
        return new PageInfo<>(orderDTOList);
    }

    public static OrderDTO getOrderDTO(Order order, CommodityDTO commodityDTO){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(order, orderDTO);
        BeanUtils.copyProperties(commodityDTO,orderDTO);
        orderDTO.setId(order.getId());
        orderDTO.setCommodityId(commodityDTO.getId());
        orderDTO.setTotalPrice(order.getCommodityNum() * commodityDTO.getPrice());
        return orderDTO;
    }


}
