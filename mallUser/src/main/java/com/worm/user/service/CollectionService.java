package com.worm.user.service;

import com.github.pagehelper.PageInfo;
import com.worm.service.BaseService;
import com.worm.user.domain.dto.CommodityCollectionDTO;
import com.worm.user.domain.entity.Collection;

public interface CollectionService extends BaseService<Collection> {
    PageInfo<CommodityCollectionDTO> findAllCommodityCollection(Integer page, Integer collectionPageSize, Collection collection);
}
