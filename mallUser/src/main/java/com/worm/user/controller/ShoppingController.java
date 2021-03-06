package com.worm.user.controller;

import com.worm.user.domain.dto.OrderDTO;
import com.worm.user.domain.dto.ShoppingCartDTO;
import com.worm.user.domain.dto.ShoppingDTO;
import com.worm.user.domain.entity.Order;
import com.worm.user.domain.entity.Shopping;
import com.worm.user.service.ShoppingService;
import com.worm.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "购物车相关功能接口")
@RestController
@RequestMapping("/shopping")
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class ShoppingController {

    private final ShoppingService shoppingService;

    @PostMapping("/addShopping")
    @ApiOperation("用户往购物车中增加一条商品")
    @ApiImplicitParam(name = "shopping", value = "增加的购物信息", dataType = "Shopping", paramType = "body")
    public ShoppingDTO addShopping(@RequestAttribute("userId") Integer userId, @RequestBody Shopping shopping) {
        if (shopping.getCommodityId() == null) {
            throw new IllegalArgumentException("缺少必要参数！");
        }
        shopping.setUserId(userId);
        return shoppingService.addShopping(shopping);
    }

    @GetMapping("findAllShopping")
    @ApiOperation("查询用户购物车")
    public List<ShoppingDTO> findAllShopping(@RequestAttribute("userId") Integer userId) {
        return shoppingService.findALlShopping(userId);
    }

    @PostMapping("/removeShopping")
    @ApiOperation("用户删除购物信息")
    @ApiImplicitParam(name = "shoppingIds", value = "删除购物信息id", dataType = "int", paramType = "query")
    public Integer removeShopping(@RequestParam Integer shoppingId) {
        return shoppingService.deleteById(shoppingId);
    }

    @PostMapping("/updateCommodityNum")
    @ApiOperation("修改购物商品数量API")
    @ApiImplicitParam(name = "shopping", value = "购物对象，必须包含订单id和数量两项", dataType = "Shopping", paramType = "body")
    public ShoppingDTO updateCommodityNum(@RequestBody Shopping shopping) {
        if (shopping.getId() == null || shopping.getCommodityNum() == null) {
            throw new IllegalArgumentException("缺少必要参数！");
        }
        return shoppingService.updateCommodityNum(shopping);
    }

    @PostMapping("/payShoppingCar")
    @ApiOperation("用户结算购物车API")
    @ApiImplicitParam(name = "shoppingIds", value = "购物信息列表", dataType = "int", paramType = "body")
    public List<OrderDTO> payShoppingCar(@RequestBody List<Integer> shoppingIds) {
        if (shoppingIds == null) {
            throw new IllegalArgumentException("缺少必要参数！");
        }
        return shoppingService.payShoppingCar(shoppingIds);
    }

}
