package com.yinn.ymall.seckill.schedule;

import java.util.concurrent.TimeUnit;

import com.yinn.ymall.seckill.constant.RedisConstant;
import com.yinn.ymall.seckill.constant.SeckillConstant;
import com.yinn.ymall.seckill.service.SeckillService;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 每晚3点上架秒杀商品
 *
 * @author Passerby
 * @since 1.0
 */
@Component
public class SeckillScheduled {

    @Autowired
    private SeckillService seckillService;

    @Autowired
    private RedissonClient redissonClient;

    @Scheduled(cron = "0 0 3 * * ?")
    public void up(){
        var lock=redissonClient.getLock(RedisConstant.UP_LOCK_PRE);
        lock.lock(10,TimeUnit.SECONDS);
        try{
            seckillService.up(SeckillConstant.day);
        }finally{
            lock.unlock();
        }
    }
}