package com.yinn.ymall.seckill.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.yinn.ymall.seckill.constant.RedisConstant;
import com.yinn.ymall.seckill.dto.KillDTO;
import com.yinn.ymall.seckill.dto.SeckillOrder;
import com.yinn.ymall.seckill.dto.SeckillSessionDTO;
import com.yinn.ymall.seckill.dto.SeckillSkuRelationDTO;
import com.yinn.ymall.seckill.feign.CouponFeignService;
import com.yinn.ymall.seckill.service.SeckillService;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
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

    // TODO redis数据结构重设计
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

    @Override
    public String kill(KillDTO killDTO) {
        BoundHashOperations<String, String, String> ops = redisTemplate.boundHashOps(RedisConstant.SKU_PRE);
        // @formatter:off
        var sku=Optional.ofNullable(ops.get(killDTO.getId()))
                                    .map(e->JSON.parseObject(e,SeckillSkuRelationDTO.class))
                                    .orElseThrow(RuntimeException::new);
        // @formatter:on
        checkKillDTO(sku);
        var semaphore = redissonClient.getSemaphore(RedisConstant.STORE_TOKEN_PRE + killDTO.getToken());
        try {
            semaphore.tryAcquire(killDTO.getNum(), 100, TimeUnit.MILLISECONDS);
            var orderSn="?";//TODO IdWorker.getIdStr();
            var order=new SeckillOrder().setOrderSn(orderSn);//TODO 设置其他属性
            //TODO 将order发送至MQ
            return orderSn;
        } catch (InterruptedException e1) {
            //TODO
        }
        return null;
    }

    private void checkKillDTO(SeckillSkuRelationDTO sku) {
        // TODO
        //秒杀时间校验
        //随机码校验
        //购买数量校验（一个用户多次购买下不能超额）
    }
}