package com.worm.user.service;

import com.worm.service.BaseService;
import com.worm.user.domain.entity.Order;
import com.worm.user.domain.entity.Shopping;

public interface ShoppingService extends BaseService<Shopping> {

    /**
     * 用户向购物车中添加一条信息
     * @param shopping 购物信息
     * @return
     */
    Integer addShopping(Shopping shopping);


    /**
     * 修改购物商品数量
     * @param Shopping
     * @return
     */
    int updateCommodityNum(Shopping Shopping);
}