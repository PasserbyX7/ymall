package com.yinn.ymall.common.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.Data;

@Data
public class PageDTO<T> {
    /**
     * 每页显示条数，默认 10
     */
    private Long size = 10L;

    /**
     * 当前页
     */
    private Long current = 1L;

    public Page<T> page() {
        return new Page<T>(current,size);
    }

}