package com.yinn.ymall.common.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public abstract class QueryDTO<T> {
    private PageDTO<T> page;

    public Page<T> page() {
        return page.page();
    }
}