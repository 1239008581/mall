package com.worm.commodity.dao;

import com.worm.commodity.domain.entity.Brand;
import com.worm.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandMapper extends MyMapper<Brand> {
    List<Brand> findBrandByClassify(@Param("classifyId") Integer classifyId);
}