package com.worm.user.service.impl;

import com.worm.service.impl.BaseServiceImpl;
import com.worm.user.dao.OrderMapper;
import com.worm.user.dao.ShoppingMapper;
import com.worm.user.domain.dto.CommodityDTO;
import com.worm.user.domain.dto.OrderDTO;
import com.worm.user.domain.dto.ShoppingDTO;
import com.worm.user.domain.entity.Order;
import com.worm.user.domain.entity.Shopping;
import com.worm.user.feignclient.CommodityFeignClient;
import com.worm.user.service.OrderService;
import com.worm.user.service.ShoppingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class ShoppingServiceImpl extends BaseServiceImpl<Shopping, ShoppingMapper> implements ShoppingService {

    private final ShoppingMapper shoppingMapper;
    private final OrderMapper orderMapper;
    private final CommodityFeignClient commodityFeignClient;

    @Override
    public ShoppingDTO addShopping(Shopping shopping) {
        //获取用户购物车,判断用户是否已添加该商品
        List<Shopping> shoppingList = shoppingMapper.select(
                Shopping.builder()
                        .userId(shopping.getUserId())
                        .build()
        );
        for (Shopping realShopping : shoppingList) {
            if (realShopping.getCommodityId().equals(shopping.getCommodityId())){
                shopping.setId(realShopping.getId());
                shopping.setCommodityNum(shopping.getCommodityNum() + realShopping.getCommodityNum());
                return updateCommodityNum(shopping);
            }
        }
        //如果没有，新增一条购物信息
        shoppingMapper.insert(shopping);
        return getShoppingDTO(shopping.getId());
    }

    @Override
    public ShoppingDTO updateCommodityNum(Shopping shopping) {
        //修改购物信息商品数量
        shoppingMapper.updateByPrimaryKeySelective(shopping);
        //返回修改后的购物信息实体
        return getShoppingDTO(shopping.getId());
    }

    @Override
    public List<ShoppingDTO> findALlShopping(Integer userId) {
        List<Shopping> shoppingList = shoppingMapper.select(
                Shopping.builder()
                        .userId(userId)
                        .build()
        );
        List<CommodityDTO> commodityList = commodityFeignClient.findCommodityList(
                shoppingList.stream()
                        .map(Shopping::getCommodityId)
                        .collect(Collectors.toList())
        );
        List<ShoppingDTO> shoppingDTOList = new ArrayList<>();
        for (int i = 0; i < shoppingList.size(); i++) {
            ShoppingDTO shoppingDTO = new ShoppingDTO();
            Shopping shopping = shoppingList.get(i);
            CommodityDTO commodityDTO = commodityList.get(i);
            if (commodityDTO.getId() == null) {
                break;
            }
            BeanUtils.copyProperties(shopping, shoppingDTO);
            BeanUtils.copyProperties(commodityDTO, shoppingDTO);
            shoppingDTO.setId(shopping.getId());
            shoppingDTO.setTotalPrice(commodityDTO.getPrice() * shopping.getCommodityNum());
            shoppingDTOList.add(shoppingDTO);
        }
        return shoppingDTOList;
    }

    @Override
    public List<OrderDTO> payShoppingCar(List<Integer> shoppingIds) {
        List<OrderDTO> orderList = new ArrayList<>();
        //循环处理每一个购物信息
        for (Integer shoppingId : shoppingIds) {
            //添加一条订单
            Shopping shopping = shoppingMapper.selectByPrimaryKey(shoppingId);
            Order order = new Order();
            BeanUtils.copyProperties(shopping, order);
            order.setId(null);
            order.setCreateTime(new Date());
            orderMapper.insertSelective(order);
            CommodityDTO commodityDTO = commodityFeignClient.findCommodity(order.getCommodityId());
            OrderDTO orderDTO = OrderServiceImpl.getOrderDTO(order, commodityDTO);
            orderList.add(orderDTO);
            //删除购物信息
            shoppingMapper.deleteByPrimaryKey(shoppingId);
        }
        return orderList;
    }

    private ShoppingDTO getShoppingDTO(Integer shoppingId) {
        Shopping shopping = shoppingMapper.selectByPrimaryKey(shoppingId);
        CommodityDTO commodity = commodityFeignClient.findCommodity(shopping.getCommodityId());
        if (commodity.getId() == null) {
            throw new IllegalArgumentException("此商品不存在！");
        }
        ShoppingDTO shoppingDTO = new ShoppingDTO();
        BeanUtils.copyProperties(shopping, shoppingDTO);
        BeanUtils.copyProperties(commodity, shoppingDTO);
        shoppingDTO.setId(shopping.getId());
        shoppingDTO.setTotalPrice(commodity.getPrice() * shopping.getCommodityNum());
        return shoppingDTO;
    }
}
