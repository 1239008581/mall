package com.worm.user.feignclient;

import com.worm.user.domain.dto.CommodityDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "mall-commodity")
public interface CommodityFeignClient {

    @GetMapping("/commodity/findCommodity/{id}")
    CommodityDTO findCommodity(@PathVariable("id") Integer id);

    @PostMapping("/commodity/findCommodityList")
    List<CommodityDTO> findCommodityList(@RequestBody List<Integer> idList);

}
