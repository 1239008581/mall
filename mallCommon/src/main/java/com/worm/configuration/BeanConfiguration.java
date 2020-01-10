package com.worm.configuration;

import com.worm.utils.JwtOperator;
import com.worm.utils.RedisOperator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public JwtOperator jwtOperator(){
        return new JwtOperator();
    }

    @Bean
    public RedisOperator redisOperator(){
        return new RedisOperator();
    }

}
