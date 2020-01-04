package com.worm.user.service;

import com.github.pagehelper.PageInfo;
import com.worm.service.BaseService;
import com.worm.user.domain.dto.FootprintDTO;
import com.worm.user.domain.entity.Footprint;

public interface FootprintService extends BaseService<Footprint> {
    PageInfo<FootprintDTO> findAllFootprint(Integer page, Integer footprintPageSize, Footprint footprint);
}
