package com.worm.commodity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestAttribute;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger2Configuration {

	/**
	 * @Description:swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
	 */
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
				.ignoredParameterTypes(RequestAttribute.class)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.worm.commodity.controller"))
				.paths(PathSelectors.any()).build();
	}

	/**
	 * @Description: 构建 api文档的信息
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				// 设置页面标题
				.title("使用swagger2构建商城用户模块api接口文档")
				// 描述
				.description("欢迎访问商城用户模块接口文档，这里是描述信息")
				// 定义版本号
				.version("1.0").build();
	}

}
