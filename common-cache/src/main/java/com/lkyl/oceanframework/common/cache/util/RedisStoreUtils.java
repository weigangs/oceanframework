package com.lkyl.oceanframework.common.cache.util;

import com.lkyl.oceanframework.common.cache.constant.CacheConstant;
import com.lkyl.oceanframework.common.utils.utils.SpringContextUtil;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;

/**
 * @author nicholas
 * @date 2023/05/27 15:13
 */
public class RedisStoreUtils {

    public static boolean getLock(String key) {

        StringRedisTemplate redisTemplate = SpringContextUtil.getBean(StringRedisTemplate.class);

         boolean result = Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(key, CacheConstant.LOCK_VAL));

         if (result) {
             redisTemplate.expire(key, Duration.ofSeconds(10));
         }

        return result;
    }

    public static void releaseLock(String key) {
        SpringContextUtil.getBean(StringRedisTemplate.class).delete(key);
    }

}
