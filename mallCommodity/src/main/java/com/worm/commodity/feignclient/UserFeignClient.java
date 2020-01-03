package com.worm.commodity.feignclient;

import com.worm.commodity.domain.dto.FootprintDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("mall-user")
public interface UserFeignClient {

    @GetMapping("/footprint/addFootprint")
    Integer addFootprint(@RequestBody FootprintDTO footprintDTO);

}
