package com.worm.commodity.service.impl;

import com.worm.commodity.dao.BrandMapper;
import com.worm.commodity.domain.entity.Brand;
import com.worm.commodity.service.BrandService;
import com.worm.service.impl.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class BrandServiceImpl extends BaseServiceImpl<Brand, BrandMapper> implements BrandService {

    private final BrandMapper brandMapper;

    @Override
    public List<Brand> findBrandByClassify(Integer classifyId) {
        List<Brand> brandList = brandMapper.findBrandByClassify(classifyId);
        return brandList;
    }
}
