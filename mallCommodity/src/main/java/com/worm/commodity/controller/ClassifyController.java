package com.worm.commodity.controller;

import com.github.pagehelper.PageInfo;
import com.worm.commodity.domain.entity.Commodity;
import com.worm.constant.CommodityConstant;
import com.worm.commodity.domain.entity.Classify;
import com.worm.commodity.service.ClassifyService;
import com.worm.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("商品分类相关功能接口")
@RestController
@RequestMapping("/classify")
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class ClassifyController {

    private final ClassifyService classifyService;

    @GetMapping("/findAllClassify")
    @ApiOperation("分页查询所有商品分类API")
    public JsonResult findAllClassify(@RequestParam(required = false,defaultValue = "1") Integer page){
        PageInfo<Classify> pageInfo = classifyService.findAllPage(page, CommodityConstant.ClassifyPageSize);
        return JsonResult.ok(pageInfo);
    }

    /*以下API受到限制无法使用
    @PostMapping("/addClassify")
    @ApiOperation("添加商品分类API")
    @ApiImplicitParam(name = "classify", value = "商品分类的数据",dataType = "Classify",paramType = "body")
    public JsonResult addClassify(@RequestBody Classify classify){
        int result = classifyService.insert(classify);
        return JsonResult.ok(result);
    }

    @PostMapping("/updateClassify")
    @ApiOperation("修改商品分类API")
    @ApiImplicitParam(name = "classify",value = "修改后的商品分类的数据，id不能修改",dataType = "Classify",paramType = "body")
    public JsonResult updateClassify(@RequestBody Classify classify){
        int result = classifyService.updateById(classify);
        return JsonResult.ok(result);
    }

    @PostMapping("/deleteClassify")
    @ApiOperation("批量删除商品分类API")
    @ApiImplicitParam(name = "classifyIds", value = "需要的删除的商品分类id列表",dataType = "int",paramType = "body")
    public JsonResult deleteClassify(@RequestBody List<Integer> classifyIds){
        int result = classifyService.batchDeleteById(classifyIds);
        return JsonResult.ok(result);
    }
     */

}
