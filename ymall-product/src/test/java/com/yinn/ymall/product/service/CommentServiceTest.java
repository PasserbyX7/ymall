package com.yinn.ymall.product.service;

import com.yinn.ymall.product.entity.Comment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Test
    void test(){
        var c=new Comment();
        c.setId(1L);
        commentService.save(c);
    }

}