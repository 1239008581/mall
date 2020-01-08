package com.worm.user.service;

import com.worm.service.BaseService;
import com.worm.user.domain.dto.OrderDTO;
import com.worm.user.domain.dto.ShoppingCartDTO;
import com.worm.user.domain.dto.ShoppingDTO;
import com.worm.user.domain.entity.Order;
import com.worm.user.domain.entity.Shopping;

import java.util.List;

public interface ShoppingService extends BaseService<Shopping> {

    /**
     * 用户向购物车中添加一条信息
     * @param shopping 购物信息
     * @return
     */
    ShoppingDTO addShopping(Shopping shopping);


    /**
     * 修改购物商品数量
     * @param Shopping
     * @return
     */
    ShoppingDTO updateCommodityNum(Shopping Shopping);

    /**
     * 查询用户购物车
     * @param userId 用户id
     * @return
     */
    List<ShoppingDTO> findALlShopping(Integer userId);

    /**
     * 结算购物车
     * @param shoppingIds 购物信息列表
     * @return
     */
    List<OrderDTO> payShoppingCar(List<Integer> shoppingIds);
}
