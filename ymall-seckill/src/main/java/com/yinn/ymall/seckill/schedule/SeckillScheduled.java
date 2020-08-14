package com.yinn.ymall.seckill.schedule;

import com.yinn.ymall.seckill.constant.SeckillConstant;
import com.yinn.ymall.seckill.service.SeckillService;

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

    @Scheduled(cron = "0 0 3 * * ?")
    public void up(){
        seckillService.up(SeckillConstant.day);
    }
}