package com.lkyl.oceanframework.common.cache.limit;
// 使用redis + lua 限流

// example
/**
 * @RestController
 * public class RedisController {
 *
 *     //localhost:8081/redis/limit
 *     @GetMapping("/redis/limit")
 *     @RedisLimitAnnotation(key = "queryFromRedis",period = 1, count = 1)
 *     public String queryFromRedis(){
 *         return "success";
 *     }
 *
 * }
 */