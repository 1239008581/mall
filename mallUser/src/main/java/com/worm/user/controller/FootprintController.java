package com.worm.user.controller;

import com.github.pagehelper.PageInfo;
import com.worm.constant.UserConstant;
import com.worm.user.domain.dto.FootprintDTO;
import com.worm.user.domain.entity.Footprint;
import com.worm.user.service.FootprintService;
import com.worm.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(tags = "用户足迹管理api")
@RestController
@RequestMapping("/footprint")
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class FootprintController {

    private final FootprintService footprintService;

    @GetMapping("/findAllFootprint")
    @ApiOperation("分页查询用户足迹API")
    @ApiImplicitParam(name = "page", value = "从前端传来的页码，默认为1", dataType = "int", paramType = "path")
    public JsonResult findAllFootprint(@RequestAttribute("userId") Integer userId, @RequestParam(required = false, defaultValue = "1") Integer page) {
        Footprint footprint = Footprint.builder()
                .userId(userId)
                .build();
        PageInfo<FootprintDTO> pageInfo = footprintService.findAllFootprint(page, UserConstant.FootprintPageSize, footprint);
        return JsonResult.ok(pageInfo);
    }

    @PostMapping("/deleteFootprint")
    @ApiOperation("批量删除用户足迹API")
    @ApiImplicitParam(name = "FootprintIds", value = "需要的删除的足迹列表", dataType = "int", paramType = "body")
    public JsonResult deleteFootprint(@RequestBody List<Integer> FootprintIds) {
        int result = footprintService.batchDeleteById(FootprintIds);
        return JsonResult.ok(result);
    }

    @GetMapping("/clearFootprint")
    @ApiOperation("清空用户足迹API")
    public JsonResult clearFootprint(@RequestAttribute("userId") Integer userId) {
        Footprint footprint = Footprint.builder()
                .userId(userId)
                .build();
        int result = footprintService.delete(footprint);
        return JsonResult.ok(result);
    }

    @PostMapping("/addFootprint")
    @ApiOperation("添加用户足迹API")
    @ApiImplicitParam(name = "footprint", value = "添加足迹的信息，商品id不能为空", dataType = "Footprint", paramType = "body")
    public Integer addFootprint(@RequestAttribute("userId") Integer userId, @RequestBody Footprint footprint) {
        if (footprint.getCommodityId() == null) {
            throw new IllegalArgumentException("缺少必要参数！");
        }
        footprint.setUserId(userId);
        footprint.setCreateTime(new Date());
        return footprintService.insert(footprint);
    }

}
