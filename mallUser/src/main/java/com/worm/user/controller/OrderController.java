package com.worm.user.controller;

import com.github.pagehelper.PageInfo;
import com.worm.constant.UserConstant;
import com.worm.user.domain.dto.CommodityDTO;
import com.worm.user.domain.dto.OrderDTO;
import com.worm.user.domain.dto.ShoppingCartDTO;
import com.worm.user.domain.entity.Order;
import com.worm.user.feignclient.CommodityFeignClient;
import com.worm.user.handler.exception.PayException;
import com.worm.user.service.OrderService;
import com.worm.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(tags = "订单相关功能API")
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/payOrders")
    @ApiOperation("用户购物车支付API")
    @ApiImplicitParam(name = "shoppingCartDTO", value = "需要支付的购物车信息，订单id列表和总价不能为空", dataType = "ShoppingCartDTO", paramType = "body")
    public JsonResult payOrders(@RequestAttribute("userId") Integer userId, @RequestBody ShoppingCartDTO shoppingCartDTO) throws PayException {
//        if (shoppingCartDTO.getTotalPrice() == null || shoppingCartDTO.getOrderIds() == null) {
//            throw new IllegalArgumentException("缺少必要参数！");
//        }
//        shoppingCartDTO.setUserId(userId);
//        Boolean result = orderService.payOrders(shoppingCartDTO);
//        return JsonResult.ok(result);
        return null;
    }

    @PostMapping("/addOrder")
    @ApiOperation("用户增加订单API")
    @ApiImplicitParam(name = "order", value = "增加订单的信息，商品id不能为空", dataType = "Order", paramType = "body")
    public OrderDTO addOrder(@RequestAttribute("userId") Integer userId, @RequestBody Order order) {
        if (order.getCommodityId() == null) {
            throw new IllegalArgumentException("缺少必要参数！");
        }
        order.setUserId(userId);
        return orderService.addOrder(order);
    }

    @PostMapping("/removeOrder")
    @ApiOperation("用户批量删除订单API")
    @ApiImplicitParam(name = "orderIds", value = "需要删除的订单列表", dataType = "int", paramType = "body")
    public JsonResult removeOrder(@RequestBody List<Integer> orderIds) {
        int result = orderService.batchDeleteById(orderIds);
        return JsonResult.ok(result);
    }

    @GetMapping("/findOrder/{orderId}")
    @ApiOperation("用户查询订单API")
    @ApiImplicitParam(name = "orderId", value = "需要查询商品的的id", dataType = "int", paramType = "path")
    public JsonResult findOrder(@PathVariable("orderId") Integer orderId) {
        Order order = orderService.findById(orderId);
        return JsonResult.ok(order);
    }

    @GetMapping("/findAllOrder/{status}")
    @ApiOperation("分页查询所有订单API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "订单的支付状态：0：未支付 1：已支付 2：所有订单", dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "page", value = "分页查询的页码", dataType = "int", paramType = "query")
    })
    public JsonResult findAllOrder(@RequestAttribute("userId") Integer userId, @PathVariable("status") Integer status, @RequestParam(required = false, defaultValue = "1") Integer page) {
        PageInfo<OrderDTO> pageInfo;
        if (status != 2) {
            Order order = Order.builder()
                    .userId(userId)
                    .status(status)
                    .build();
            pageInfo = orderService.findAllOrder(page, UserConstant.OrderPageSize, order);
        } else {
            Order order = Order.builder()
                    .userId(userId)
                    .build();
            pageInfo = orderService.findAllOrder(page, UserConstant.OrderPageSize,order);
        }
        return JsonResult.ok(pageInfo);
    }




}
