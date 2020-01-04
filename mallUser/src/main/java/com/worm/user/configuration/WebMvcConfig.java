package com.worm.user.configuration;

import com.worm.user.interceptor.HeaderTokenInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class WebMvcConfig implements WebMvcConfigurer {

    private final HeaderTokenInterceptor headerTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(headerTokenInterceptor)
                .addPathPatterns("/order/**")
                .addPathPatterns("/footprint/**")
                .addPathPatterns("/collection/**")
                .addPathPatterns("/receivingAddress/**")
                .addPathPatterns("/shopping/**");
    }

}
