package com.example.springbootswagger.config;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("标题：springboot+swagger构建api文档")
                        .description("描述：restfun风格")
                        .termsOfServiceUrl("URL服务的术语")
                        .version("版本：1.0")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.springbootswagger"))
                .paths(PathSelectors.any())
                .build();
    }


}
