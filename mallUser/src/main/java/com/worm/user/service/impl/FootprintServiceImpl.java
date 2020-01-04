package com.worm.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.worm.service.impl.BaseServiceImpl;
import com.worm.user.dao.FootprintMapper;
import com.worm.user.domain.dto.CommodityCollectionDTO;
import com.worm.user.domain.dto.CommodityDTO;
import com.worm.user.domain.dto.FootprintDTO;
import com.worm.user.domain.entity.Collection;
import com.worm.user.domain.entity.Footprint;
import com.worm.user.feignclient.CommodityFeignClient;
import com.worm.user.service.FootprintService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class FootprintServiceImpl extends BaseServiceImpl<Footprint, FootprintMapper> implements FootprintService {

    private final FootprintMapper footprintMapper;
    private final CommodityFeignClient commodityFeignClient;

    @Override
    public PageInfo<FootprintDTO> findAllFootprint(Integer page, Integer footprintPageSize, Footprint footprint) {
        PageHelper.startPage(page,footprintPageSize);
        List<Footprint> footprintList = footprintMapper.select(footprint);
        List<CommodityDTO> commodityList = commodityFeignClient.findCommodityList(
                footprintList.stream()
                        .map(Footprint::getCommodityId)
                        .collect(Collectors.toList())
        );
        List<FootprintDTO> footprintDTOList = new ArrayList<>();
        for (int i = 0; i < footprintList.size(); i++) {
            FootprintDTO footprintDTO = new FootprintDTO();
            BeanUtils.copyProperties(footprintList.get(i), footprintDTO);
            BeanUtils.copyProperties(commodityList.get(i), footprintDTO);
            footprintDTO.setId(footprintList.get(i).getId());
            footprintDTOList.add(footprintDTO);
        }
        return new PageInfo<>(footprintDTOList);
    }
}
