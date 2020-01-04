package com.worm.user.controller;

import com.github.pagehelper.PageInfo;
import com.worm.constant.UserConstant;
import com.worm.user.domain.entity.ReceivingAddress;
import com.worm.user.service.ReceivingAddressService;
import com.worm.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "收货地址相关功能API")
@RestController
@RequestMapping("/receivingAddress")
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class ReceivingAddressController {

    private final ReceivingAddressService receivingAddressService;

    @PostMapping("/addReceivingAddress")
    @ApiOperation("用户添加一个收货地址API")
    @ApiImplicitParam(name = "receivingAddress", value = "收货地址的数据体", dataType = "ReceivingAddress", paramType = "body")
    public JsonResult addReceivingAddress(@RequestAttribute("userId") Integer userId, @RequestBody ReceivingAddress receivingAddress) {
        if (receivingAddress.getAddress() == null || receivingAddress.getProvince() == null || receivingAddress.getUserName() == null || receivingAddress.getUserTelephone() == null) {
            throw new IllegalArgumentException("缺少必要参数！");
        }
        receivingAddress.setUserId(userId);
        int result = receivingAddressService.insert(receivingAddress);
        return JsonResult.ok(result);
    }

    @PostMapping("/deleteReceivingAddress/{id}")
    @ApiOperation("用户删除收货地址API")
    @ApiImplicitParam(name = "id", value = "需要删除的收货地址的id", dataType = "int", paramType = "path")
    public JsonResult deleteReceivingAddress(@PathVariable("id") Integer id) {
        int result = receivingAddressService.deleteById(id);
        return JsonResult.ok(result);
    }

    @PostMapping("/updateReceivingAddress")
    @ApiOperation("用户修改收货地址API")
            @ApiImplicitParam(name = "receivingAddress", value = "需要修改的收货地址,id不能为空", dataType = "ReceivingAddress",paramType = "body")
    public JsonResult updateReceivingAddress(@RequestBody ReceivingAddress receivingAddress) {
        if (receivingAddress.getId() == null) {
            throw new IllegalArgumentException("缺少必要参数！");
        }
        int result = receivingAddressService.updateById(receivingAddress);
        return JsonResult.ok(result);
    }

    @GetMapping("/findReceivingAddress")
    @ApiOperation("分页查询所有收货地址")
    @ApiImplicitParam(name = "page", value = "分页查询的页码", dataType = "int", paramType = "path")
    public JsonResult findReceivingAddress(@RequestAttribute("userId") Integer userId, @RequestParam(required = false, defaultValue = "1") Integer page) {
        ReceivingAddress receivingAddress = ReceivingAddress.builder()
                .userId(userId)
                .build();
        PageInfo<ReceivingAddress> pageInfo = receivingAddressService.findPage(page, UserConstant.ReceivingAddressPageSize, receivingAddress);
        return JsonResult.ok(pageInfo);
    }

    @GetMapping("/getReceivingAddress/{id}")
    @ApiOperation("通过id查询收货地址")
    @ApiImplicitParam(name = "id",value = "收货地址的id",dataType = "int",paramType = "query")
    public ReceivingAddress getReceivingAddress(@PathVariable("id") Integer id){
        return receivingAddressService.findById(id);
    }


}
