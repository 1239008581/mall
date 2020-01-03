package com.worm.user.service.impl;

import com.worm.service.impl.BaseServiceImpl;
import com.worm.user.dao.FootprintMapper;
import com.worm.user.domain.entity.Footprint;
import com.worm.user.service.FootprintService;
import org.springframework.stereotype.Service;

@Service
public class FootprintServiceImpl extends BaseServiceImpl<Footprint, FootprintMapper> implements FootprintService {
}
