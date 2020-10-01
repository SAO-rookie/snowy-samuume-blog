package com.snowy_samuume.tool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author snowy
 * @date 2020/10/1 18:00
 */
@Component
@Slf4j
public final class RedisBean {

    @Autowired
    private RedisTemplate redisTemplate;

    public  static RedisTemplate redis;

    @PostConstruct
    public void getRedisTemplate(){
        redis=this.redisTemplate;
        log.info("初始化-------redisTemplate----");
    }

}