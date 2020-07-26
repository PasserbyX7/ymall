package com.yinn.ymall.product.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@EnableCaching
@Configuration
@EnableConfigurationProperties(CacheProperties.class)
public class RedisConfiguration {
    @Bean
    RedisCacheConfiguration redisCacheConfiguration(CacheProperties cacheProperties){
        var config=RedisCacheConfiguration.defaultCacheConfig();
        //设置序列化
        var keySerializationPair=RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer());
        var valueSerializationPair=RedisSerializationContext.SerializationPair.fromSerializer(new GenericFastJsonRedisSerializer());
        config=config.serializeKeysWith(keySerializationPair);
        config=config.serializeValuesWith(valueSerializationPair);
        //设置配置文件中的配置
        var redisProperties=cacheProperties.getRedis();
        if(redisProperties.getTimeToLive()!=null)
            config=config.entryTtl(redisProperties.getTimeToLive());
        if(redisProperties.getKeyPrefix()!=null)
            config=config.prefixKeysWith(redisProperties.getKeyPrefix());
        if(!redisProperties.isCacheNullValues())
            config=config.disableCachingNullValues();
        if(!redisProperties.isUseKeyPrefix())
            config=config.disableKeyPrefix();
        return config;
    }
}