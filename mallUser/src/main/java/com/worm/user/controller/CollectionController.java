package com.worm.user.controller;

import com.github.pagehelper.PageInfo;
import com.worm.constant.UserConstant;
import com.worm.user.domain.entity.Collection;
import com.worm.user.service.CollectionService;
import com.worm.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(tags = "用户收藏API")
@RestController
@RequestMapping("/collection")
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class CollectionController {

    private final CollectionService collectionService;

    @PostMapping("/addCollection")
    @ApiOperation("用户添加收藏API")
    @ApiImplicitParam(name = "collection", value = "收藏商品信息的数据，收藏事物的id和收藏事物的类型不能为空", dataType = "Collection", paramType = "body")
    public JsonResult addCollection(@RequestAttribute("userId") Integer userId, @RequestBody Collection collection) {
        if (collection.getObjectId() == null || collection.getType() == null) {
            throw new IllegalArgumentException("缺少必要参数！");
        }
        collection.setUserId(userId);
        collection.setCreateTime(new Date());
        int result = collectionService.insert(collection);
        return JsonResult.ok(result);
    }

    @PostMapping("/deleteCollection")
    @ApiOperation("用户批量删除收藏API")
    @ApiImplicitParam(name = "collectionIds", value = "需要删除的收藏id列表", dataType = "int", paramType = "body")
    public JsonResult deleteCollection(@RequestBody List<Integer> collectionIds) {
        int result = collectionService.batchDeleteById(collectionIds);
        return JsonResult.ok(result);
    }

    @GetMapping("/getAllCollection/{type}")
    @ApiOperation("分页查询用户收藏API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "收藏事物的类型", dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "page", value = "分页查询的页码，默认为1", dataType = "int", paramType = "query")
    })
    public JsonResult getAllCollection(@RequestAttribute("userId") Integer userId, @PathVariable("type") String type, @RequestParam(required = false, defaultValue = "1") Integer page) {
        Collection collection = Collection.builder()
                .userId(userId)
                .type(type)
                .build();
        PageInfo<Collection> pageInfo = collectionService.findPage(page, UserConstant.CollectionPageSize, collection);
        return JsonResult.ok(pageInfo);
    }
}
