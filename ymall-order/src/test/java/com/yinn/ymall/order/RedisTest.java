package com.yinn.ymall.order;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import com.yinn.ymall.order.constant.RedisConstant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private String token="token";

    private Long memberId=1L;

    @Test
    void setOrderTokenTest() {
        String key = RedisConstant.ORDER_TOKEN_PRE + memberId;
        redisTemplate.opsForValue().set(key, token, RedisConstant.ORDER_TOKEN_EXPIRE_TIME, TimeUnit.MINUTES);
    }

    @Test
    void verifyTokenTest() {
        String key = RedisConstant.ORDER_TOKEN_PRE + memberId;
        var script = new DefaultRedisScript<Long>(RedisConstant.LUA_SCRIPT, Long.class);
        Long result = redisTemplate.execute(script, Collections.singletonList(key), token);
        assertEquals(1, result);
    }

}