package com.yinn.ymall.seckill;

import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("local")//指定测试环境
@SpringBootTest
class RedissonTest {

    @Autowired
    RedissonClient redissonClient;

    @Test
    void test(){
        System.out.println(redissonClient);
    }

}
