package com.common.atmochat.config;


import com.google.common.base.Predicates;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@SwaggerDefinition(tags = {@Tag(name = "My Swagger Resource", description = "Meaningful stuff in here")})

public class SwaggerConfig {

//    @Value("${link.swagger.documentation}")
//    String swagger_documentation;

    @Bean
    public Docket api() {

        List<Parameter> parameters = new ArrayList<>();
//        parameters.add(new ParameterBuilder()
//                .name("accessToken")
//                .modelRef(new ModelRef("string"))
//                .parameterType("header")
//                .allowEmptyValue(true)
//                .defaultValue("")
//                .build());

        return (new Docket(DocumentationType.SWAGGER_2))
                .select()
                .apis(Predicates.not(RequestHandlerSelectors.withClassAnnotation(ApiIgnore.class)))
                .apis(RequestHandlerSelectors.basePackage("com.common.atmochat.controller.rest"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(apiKeys())
                .apiInfo(apiInfo())
                .globalOperationParameters(parameters);

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Rest API for Atmo-Chat App")
//                .description("DOCUMENTATION: " + swagger_documentation)
                .build();
    }

    private List<ApiKey> apiKeys() {
        List<ApiKey> apiKeys = new ArrayList<>();

//        ApiKey apiKey = new ApiKey("accessToken", "accessToken", "Header");

//        apiKeys.add(apiKey);

        return apiKeys;
    }
}
