package com.yinn.ymall.product.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yinn.ymall.product.dao.CommentReplayDao;
import com.yinn.ymall.product.entity.CommentReplay;
import com.yinn.ymall.product.service.CommentReplayService;


@Service
public class CommentReplayServiceImpl extends ServiceImpl<CommentReplayDao, CommentReplay> implements CommentReplayService {
}