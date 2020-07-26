package com.yinn.ymall.product.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yinn.ymall.product.entity.Category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;

    @Test
    void test(){
        var w=Wrappers.<Category>lambdaQuery();
        w.eq(Category::getId,1L);
        var c=categoryService.getOne(w);
        System.out.println(c);
    }
}