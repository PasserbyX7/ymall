package com.yinn.ymall.common.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("common.date-format")
public class DateFormatProperties {

    /**
     * DateTime Format Pattern
     */
    private String dateTimePattern = "yyyy-MM-dd HH:mm:ss";

    /**
     * Date Format Pattern
     */
    private String datePattern = "yyyy-MM-dd";

    /**
     * Time Format Pattern
     */
    private String timePattern = "HH:mm:ss";

}