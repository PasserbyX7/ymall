package com.yinn.ymall.product.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yinn.ymall.product.dao.CommentDao;
import com.yinn.ymall.product.entity.Comment;
import com.yinn.ymall.product.service.CommentService;


@Service
public class CommentServiceImpl extends ServiceImpl<CommentDao, Comment> implements CommentService {
}