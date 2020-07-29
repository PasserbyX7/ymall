package com.yinn.ymall.common.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("分页查询DTO")
public abstract class PageQueryDTO<T> {
    /**
     * 每页显示条数，默认 10
     */
    @ApiModelProperty(value = "每页显示条数，默认 10", example = "10")
    private Long size = 10L;

    /**
     * 当前页，默认1
     */
    @ApiModelProperty(value = "当前页，默认1", example = "1")
    private Long current = 1L;

    public Page<T> page() {
        return new Page<T>(current, size);
    }
}