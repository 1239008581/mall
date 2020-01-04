package com.worm.user.service.impl;

import com.worm.service.impl.BaseServiceImpl;
import com.worm.user.dao.ShoppingMapper;
import com.worm.user.domain.dto.CommodityDTO;
import com.worm.user.domain.entity.Order;
import com.worm.user.domain.entity.Shopping;
import com.worm.user.feignclient.CommodityFeignClient;
import com.worm.user.handler.exception.PayException;
import com.worm.user.service.ShoppingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class ShoppingServiceImpl extends BaseServiceImpl<Shopping, ShoppingMapper> implements ShoppingService {

    private final ShoppingMapper shoppingMapper;
    private final CommodityFeignClient commodityFeignClient;

    @Override
    public Integer addShopping(Shopping shopping) {
        Integer commodityId = shopping.getCommodityId();
        if (commodityId == null) {
            throw new IllegalArgumentException("缺少必要参数！");
        }
        CommodityDTO commodity = commodityFeignClient.findCommodity(commodityId);
        if (commodity.getId() == null) {
            throw new IllegalArgumentException("参数错误，此商品不存在！");
        }
        Integer commodityNum = (shopping.getCommodityNum() == null ? 1 : shopping.getCommodityNum());
        shopping.setPrice(commodity.getPrice() * commodityNum);
        return shoppingMapper.insert(shopping);
    }

    @Override
    public int updateCommodityNum(Shopping shopping) {
        //获取订单信息
        Shopping realShopping = shoppingMapper.selectByPrimaryKey(shopping.getId());
        //修改数量和金额
        float unitPrice = realShopping.getPrice() / realShopping.getCommodityNum();
        shopping.setPrice(unitPrice * shopping.getCommodityNum());
        int result = shoppingMapper.updateByPrimaryKeySelective(shopping);
        return result;
    }
}
