package com.worm.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "com.worm.user.dao")
@SpringBootApplication
@EnableSwagger2
@EnableFeignClients
public class SmallUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmallUserApplication.class, args);
    }


}
