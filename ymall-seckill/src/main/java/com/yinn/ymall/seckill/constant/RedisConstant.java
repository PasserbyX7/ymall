package com.yinn.ymall.seckill.constant;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class RedisConstant {
    /**
     * 活动前缀
     */
    public static final String SESSION_PRE = "seckill:session:";
    /**
     * Sku前缀
     */
    public static final String SKU_PRE = "seckill:sku:";
    /**
     * 分布式信号量前缀
     */
    public static final String STORE_TOKEN_PRE = "seckill:token:";
    /**
     * 定时任务分布式锁前缀
     */
    public static final String UP_LOCK_PRE = "seckill:upLock:";

    public static String generateSessionPreKey(LocalDateTime startTime,LocalDateTime endTime){
        var start=startTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        var end=endTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        return String.format("%s%d-%d", SESSION_PRE,start,end);
    }

    public static String generateSkusPreKey(Long sessionId,Long skuId){
        return String.format("%s-%s", sessionId,skuId);
    }

}