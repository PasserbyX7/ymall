package com.yinn.ymall.order.constant;

public class RedisConstant {
    /**
     * 订单防重令牌前缀
     */
    public static final String ORDER_TOKEN_PRE="order:token:";
    /**
     * 订单防重令牌存活时间（默认为分钟）
     */
    public static final long ORDER_TOKEN_EXPIRE_TIME = 10L;
    /**
     * lua脚本
     */
    public static final String LUA_SCRIPT="""
    if redis.call("get",KEYS[1]) == ARGV[1]
    then
        return redis.call("del",KEYS[1])
    else
        return 0
    end
    """;
}