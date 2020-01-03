package com.worm.commodity.controller;

import com.github.pagehelper.PageInfo;
import com.worm.commodity.domain.dto.FootprintDTO;
import com.worm.constant.CommodityConstant;
import com.worm.commodity.domain.entity.Commodity;
import com.worm.commodity.service.CommodityService;
import com.worm.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(tags = "商品相关功能")
@RestController
@RequestMapping("/commodity")
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class CommodityController {

    private final CommodityService commodityService;

    @GetMapping("/findCommodity/{id}")
    @ApiOperation("查询商品API")
    @ApiImplicitParam(name = "id", value = "查询商品的id", dataType = "int", paramType = "path")
    public Commodity findCommodity(@RequestAttribute("id") Integer userId,@PathVariable("id") Integer id) {
        if (userId != null){
            FootprintDTO.builder()
                    .userId(userId)
                    .commondityId(id)
                    .time(new Date())
                    .build();
        }
        return commodityService.findById(id);
    }

    @GetMapping("/findAllCommodity")
    @ApiOperation("分页查询所有商品")
    @ApiImplicitParam(name = "page", value = "分页查询的页码", dataType = "int", paramType = "path")
    public JsonResult findAllCommodity(@RequestParam(required = false, defaultValue = "1") Integer page) {
        PageInfo<Commodity> pageInfo = commodityService.findAllPage(page, CommodityConstant.CommodityPageSize);
        return JsonResult.ok(pageInfo);
    }

    /*以下API受到限制无法使用
    @GetMapping("/addCommodity")
    @ApiOperation("新增一个商品")
    @ApiImplicitParam(name = "commodity", value = "增加商品的数据体", dataType = "commodity", paramType = "body")
    public JsonResult addCommodity(@RequestBody Commodity commodity) {
        int result = commodityService.insert(commodity);
        return JsonResult.ok(result);
    }

    @PostMapping("/updateCommodity")
    @ApiOperation("修改商品API")
    @ApiImplicitParam(name = "commodity",value = "修改后的商品的数据，id不能修改",dataType = "Commodity",paramType = "body")
    public JsonResult updateCommodity(@RequestBody Commodity commodity){
        int result = commodityService.updateById(commodity);
        return JsonResult.ok(result);
    }

    @PostMapping("/deleteCommodity")
    @ApiOperation("批量删除商品API")
    @ApiImplicitParam(name = "commodityIds", value = "需要的删除的商品id列表",dataType = "int",paramType = "body")
    public JsonResult deleteCommodity(@RequestBody List<Integer> commodityIds){
        int result = commodityService.batchDeleteById(commodityIds);
        return JsonResult.ok(result);
    }
    */
}
