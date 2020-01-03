package com.worm.commodity.service.impl;

import com.worm.commodity.dao.ClassifyMapper;
import com.worm.commodity.domain.entity.Classify;
import com.worm.commodity.service.ClassifyService;
import com.worm.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ClassifyServiceImpl extends BaseServiceImpl<Classify,ClassifyMapper> implements ClassifyService {
}
