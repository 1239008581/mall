package com.worm.commodity.controller;

import com.worm.commodity.domain.entity.Brand;
import com.worm.commodity.service.BrandService;
import com.worm.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("商品品牌相关接口")
@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class BrandController {

    private final BrandService brandService;

    @GetMapping("/findBrandByClassify/{classifyId}")
    @ApiOperation("根据商品分类查询品牌API")
    @ApiImplicitParam(name = "classifyId",value = "商品分类的id",dataType = "int",paramType = "path")
    public JsonResult findBrandByClassify(@PathVariable("classifyId") Integer classifyId){
        List<Brand> brandList = brandService.findBrandByClassify(classifyId);
        return JsonResult.ok(brandList);
    }

    /*以下API受到限制无法使用
    @PostMapping("/addBrand")
    @ApiOperation("添加商品品牌API")
    @ApiImplicitParam(name = "brand", value = "商品品牌的数据",dataType = "Brand",paramType = "body")
    public JsonResult addBrand(@RequestBody Brand brand){
        int result = brandService.insert(brand);
        return JsonResult.ok(result);
    }

    @PostMapping("/updateBrand")
    @ApiOperation("修改商品品牌API")
    @ApiImplicitParam(name = "Brand",value = "修改后的商品品牌的数据，id不能修改",dataType = "Brand",paramType = "body")
    public JsonResult updateBrand(@RequestBody Brand Brand){
        int result = brandService.updateById(Brand);
        return JsonResult.ok(result);
    }

    @PostMapping("/deleteBrand")
    @ApiOperation("批量删除商品品牌API")
    @ApiImplicitParam(name = "brandIds", value = "需要的删除的商品品牌id列表",dataType = "int",paramType = "body")
    public JsonResult deleteBrand(@RequestBody List<Integer> brandIds){
        int result = brandService.batchDeleteById(brandIds);
        return JsonResult.ok(result);
    }
     */

}
