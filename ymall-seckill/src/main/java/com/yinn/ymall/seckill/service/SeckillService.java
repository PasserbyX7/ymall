package com.yinn.ymall.seckill.service;

public interface SeckillService {

    /**
     * 上架秒杀商品
     *
     * @param day 最近n天需要秒杀的商品
     * @Date: 2020-08-14 02:34:47
     */
    void up(int day);

}