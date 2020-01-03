package com.worm.commodity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.worm.commodity.dao")
@EnableSwagger2
@EnableFeignClients
public class CommodityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommodityApplication.class, args);
    }

}
