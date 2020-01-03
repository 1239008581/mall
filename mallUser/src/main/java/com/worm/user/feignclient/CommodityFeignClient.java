package com.worm.user.feignclient;

import com.worm.user.domain.dto.CommodityDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "mall-commodity")
public interface CommodityFeignClient {

    @GetMapping("/commodity/findCommodity/{id}")
    CommodityDTO findCommodity(@PathVariable("id") Integer id);

}
