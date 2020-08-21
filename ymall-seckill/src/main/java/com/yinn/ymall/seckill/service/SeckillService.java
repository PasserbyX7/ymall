package com.yinn.ymall.seckill.service;

public interface SeckillService {

    /**
     * 上架秒杀商品（保证幂等性）
     *
     * @param day 最近day天需要秒杀的商品
     * @Date: 2020-08-14 02:34:47
     */
    void up(int day);

}