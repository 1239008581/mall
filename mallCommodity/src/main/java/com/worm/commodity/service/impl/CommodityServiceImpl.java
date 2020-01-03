package com.worm.commodity.service.impl;

import com.worm.commodity.dao.CommodityMapper;
import com.worm.commodity.domain.entity.Commodity;
import com.worm.commodity.service.CommodityService;
import com.worm.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CommodityServiceImpl extends BaseServiceImpl<Commodity,CommodityMapper> implements CommodityService {
}
