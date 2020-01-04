package com.worm.user.controller;

import com.github.pagehelper.PageInfo;
import com.worm.constant.UserConstant;
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
    @ApiImplicitParam(name = "shopping",value = "增加的购物信息",dataType = "Shopping",paramType = "body")
    public Integer addShopping(@RequestAttribute("userId") Integer userId, @RequestBody Shopping shopping){
        shopping.setUserId(userId);
        return shoppingService.addShopping(shopping);
    }

    @GetMapping("findAllShopping")
    @ApiOperation("查询用户购物车")
    @ApiImplicitParam(name = "page",value = "分页查询的页码",dataType = "int",paramType = "query")
    public PageInfo<Shopping> findAllShopping(@RequestAttribute("userId") Integer userId, @RequestParam(required = false,defaultValue = "1") Integer page){
        Shopping shopping = Shopping.builder()
                .userId(userId)
                .build();
        return shoppingService.findPage(page, UserConstant.ShoppingPageSize, shopping);
    }

    @PostMapping("/removeShopping")
    @ApiOperation("用户批量删除购物信息")
    @ApiImplicitParam(name = "shoppingIds",value = "删除购物信息id列表",dataType = "int",paramType = "query")
    public Integer removeShopping(@RequestParam List<Integer> shoppingIds){
        return shoppingService.batchDeleteById(shoppingIds);
    }

    @PostMapping("/updateCommodityNum")
    @ApiOperation("修改订单商品数量API")
    @ApiImplicitParam(name = "order", value = "订单对象，必须包含订单id和数量两项", dataType = "Order", paramType = "body")
    public JsonResult updateCommodityNum(@RequestBody Shopping shopping) {
        if (shopping.getId() == null || shopping.getCommodityNum() == null) {
            throw new IllegalArgumentException("缺少必要参数！");
        }
        int result = shoppingService.updateCommodityNum(shopping);
        return JsonResult.ok(result);
    }

}
