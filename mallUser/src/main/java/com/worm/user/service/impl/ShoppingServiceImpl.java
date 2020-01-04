package com.worm.user.service.impl;

import com.worm.service.impl.BaseServiceImpl;
import com.worm.user.dao.ShoppingMapper;
import com.worm.user.domain.dto.CommodityDTO;
import com.worm.user.domain.dto.ShoppingDTO;
import com.worm.user.domain.entity.Shopping;
import com.worm.user.feignclient.CommodityFeignClient;
import com.worm.user.service.ShoppingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class ShoppingServiceImpl extends BaseServiceImpl<Shopping, ShoppingMapper> implements ShoppingService {

    private final ShoppingMapper shoppingMapper;
    private final CommodityFeignClient commodityFeignClient;

    @Override
    public Integer addShopping(Shopping shopping) throws IllegalArgumentException {
        CommodityDTO commodity = commodityFeignClient.findCommodity(shopping.getCommodityId());
        if (commodity.getId() == null) {
            throw new IllegalArgumentException("参数错误，此商品不存在！");
        }
        Integer commodityNum = (shopping.getCommodityNum() == null ? 1 : shopping.getCommodityNum());
        shopping.setTotalPrice(commodity.getPrice() * commodityNum);
        return shoppingMapper.insert(shopping);
    }

    @Override
    public int updateCommodityNum(Shopping shopping) {
        //获取订单信息
        Shopping realShopping = shoppingMapper.selectByPrimaryKey(shopping.getId());
        //修改数量和金额
        float unitPrice = realShopping.getTotalPrice() / realShopping.getCommodityNum();
        shopping.setTotalPrice(unitPrice * shopping.getCommodityNum());
        int result = shoppingMapper.updateByPrimaryKeySelective(shopping);
        return result;
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
            BeanUtils.copyProperties(shoppingList.get(i), shoppingDTO);
            BeanUtils.copyProperties(commodityList.get(i), shoppingDTO);
            shoppingDTO.setId(shoppingList.get(i).getId());
            shoppingDTOList.add(shoppingDTO);
        }
        return shoppingDTOList;
    }
}
