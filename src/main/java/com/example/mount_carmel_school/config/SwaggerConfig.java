package com.example.mount_carmel_school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;


@EnableSwagger2
@Configuration
public class SwaggerConfig {


    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.student_attendance_management"))
                .paths(regex("/api/v1.*"))
                .build()
                .apiInfo(metaInfo());
    }
    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "MOUNT CARMEL  MANAGEMENT SYSTEM APIs",
                "all apis here",
                "1.0",
                "Terms of Service",
                new Contact("Dush-sam group", "https://github.com/dushimsam",
                        "dushsam100@gmail.com"),
                "",
                ""
        );

        return apiInfo;
    }
}
