package com.worm.commodity.service;

import com.worm.commodity.domain.entity.Brand;
import com.worm.service.BaseService;

import java.util.List;

public interface BrandService extends BaseService<Brand> {

    /**
     * 根据商品分类查询品牌
     * @param classifyId 商品类别id
     * @return
     */
    List<Brand> findBrandByClassify(Integer classifyId);
}
