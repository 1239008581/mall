package com.worm.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.worm.service.impl.BaseServiceImpl;
import com.worm.user.dao.CollectionMapper;
import com.worm.user.domain.dto.CommodityCollectionDTO;
import com.worm.user.domain.dto.CommodityDTO;
import com.worm.user.domain.dto.ShoppingDTO;
import com.worm.user.domain.entity.Collection;
import com.worm.user.domain.entity.Shopping;
import com.worm.user.feignclient.CommodityFeignClient;
import com.worm.user.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class CollectionServiceImpl extends BaseServiceImpl<Collection, CollectionMapper> implements CollectionService {

    private final CollectionMapper collectionMapper;
    private final CommodityFeignClient commodityFeignClient;

    @Override
    public PageInfo<CommodityCollectionDTO> findAllCommodityCollection(Integer page, Integer collectionPageSize, Collection collection) {
        PageHelper.startPage(page, collectionPageSize);
        List<Collection> collectionList = collectionMapper.select(collection);
        List<CommodityDTO> commodityList = commodityFeignClient.findCommodityList(
                collectionList.stream()
                        .map(Collection::getObjectId)
                        .collect(Collectors.toList())
        );
        List<CommodityCollectionDTO> commodityCollectionDTOList = new ArrayList<>();
        for (int i = 0; i < collectionList.size(); i++) {
            CommodityCollectionDTO commodityCollectionDTO = new CommodityCollectionDTO();
            BeanUtils.copyProperties(collectionList.get(i), commodityCollectionDTO);
            BeanUtils.copyProperties(commodityList.get(i), commodityCollectionDTO);
            commodityCollectionDTO.setId(collectionList.get(i).getId());
            commodityCollectionDTO.setCommodityId(collectionList.get(i).getObjectId());
            commodityCollectionDTOList.add(commodityCollectionDTO);
        }
        return new PageInfo<>(commodityCollectionDTOList);
    }
}
