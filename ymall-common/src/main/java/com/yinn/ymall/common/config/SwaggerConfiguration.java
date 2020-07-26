package com.yinn.ymall.common.config;

import com.yinn.ymall.common.config.property.SwaggerProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableConfigurationProperties(SwaggerProperties.class)
@ConditionalOnProperty(prefix = "common.swagger", name = "enabled", matchIfMissing = true, havingValue = "true")
public class SwaggerConfiguration {

    @Autowired
    private SwaggerProperties swaggerProperties;

    @Bean
    public Docket createRestApi() {
        // @formatter:off
        return new Docket(DocumentationType.SWAGGER_2)
                                .apiInfo(apiInfo())
                                .select()
                                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                                .paths(PathSelectors.any())
                                .build();
        // @formatter:on
    }

    private ApiInfo apiInfo(){
        // @formatter:off
        return new ApiInfoBuilder()
                            .title(swaggerProperties.getTitle())
                            .description(swaggerProperties.getDescription())
                            .version(swaggerProperties.getVersion())
                            .license(swaggerProperties.getLicense())
                            .licenseUrl(swaggerProperties.getLicenseUrl())
                            .build();
        // @formatter:on
    }
}