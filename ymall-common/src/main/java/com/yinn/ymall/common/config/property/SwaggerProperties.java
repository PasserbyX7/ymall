package com.yinn.ymall.common.config.property;

import com.yinn.ymall.common.constant.PropertiesConstant;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(PropertiesConstant.SWAGGER)
public class SwaggerProperties {

    /**
     * swagger包扫描路径
     */
    private String basePackage="**.controller";
    /**
     * swagger文档标题
     */
    private String title="未命名模块API文档";
    /**
     * swagger文档描述
     */
    private String description="印迹商城HTTP对外开放接口";
    /**
     * swagger文档版本
     */
    private String version="1.0";
    /**
     * 代码开源协议
     */
    private String license="Apache 2.0";
    /**
     * 代码开源协议链接
     */
    private String licenseUrl="http://www.apache.org/licenses/LICENSE-2.0.html";
}