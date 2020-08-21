package com.yinn.ymall.seckill.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.yinn.ymall.seckill.constant.RedisConstant;
import com.yinn.ymall.seckill.dto.SeckillSessionDTO;
import com.yinn.ymall.seckill.dto.SeckillSkuRelationDTO;
import com.yinn.ymall.seckill.feign.CouponFeignService;
import com.yinn.ymall.seckill.service.SeckillService;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private CouponFeignService couponFeignService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public void up(int days) {
        var seckillSessionDTOList = couponFeignService.getByDays(days).getData();
        seckillSessionDTOList.forEach(e -> {
            saveSession(e);
            saveSkus(e);
        });
    }

    private void saveSession(SeckillSessionDTO seckillSessionDTO) {
        String key = RedisConstant.generateSessionPreKey(seckillSessionDTO.getStartTime(),
                seckillSessionDTO.getEndTime());
        if (redisTemplate.hasKey(key))
            return;
        // @formatter:off
        List<String> value=seckillSessionDTO
                                            .getSkus()
                                            .stream()
                                            .map(sku->RedisConstant.generateSkusPreKey(sku.getSessionId(), sku.getSkuId()))
                                            .collect(Collectors.toList());
        // @formatter:on
        redisTemplate.opsForList().leftPushAll(key, value);
    }

    private void saveSkus(SeckillSessionDTO seckillSessionDTO) {
        var ops = redisTemplate.boundHashOps(RedisConstant.SKU_PRE);
        seckillSessionDTO.getSkus().forEach(sku -> {
            var key = RedisConstant.generateSkusPreKey(sku.getSessionId(), sku.getSkuId());
            if (redisTemplate.hasKey(key))
                return;
            sku.setStartTime(seckillSessionDTO.getStartTime());
            sku.setEndTime(seckillSessionDTO.getEndTime());
            var token = UUID.randomUUID().toString().replace("-", "");
            sku.setToken(token);
            ops.put(key, JSON.toJSONString(sku));
            // 使用库存作为分布式信号量
            redissonClient.getSemaphore(RedisConstant.STORE_TOKEN_PRE + token).trySetPermits(sku.getCount());
        });
    }

}