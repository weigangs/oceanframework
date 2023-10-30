//package com.lkyl.oceanframework.common.auth.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializer;
//
//@Configuration
//public class RedisConfig {
//    @Bean
//    public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory redisConnectionFactory){
//        //创建RedisTemplate对象
//        RedisTemplate<String, String> template = new RedisTemplate<>();
//        //设置连接工厂
//        template.setConnectionFactory(redisConnectionFactory);
//        //创建JSON序列化工具
//        GenericJackson2JsonRedisSerializer jsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
//        //设置KEY的序列化
//        template.setKeySerializer(RedisSerializer.string());
//        template.setHashKeySerializer(RedisSerializer.string());
//        //设置VALUE的序列化，不用jsonRedisSerializer
////        template.setValueSerializer(jsonRedisSerializer);
////        template.setHashValueSerializer(jsonRedisSerializer);
//        template.setValueSerializer(RedisSerializer.string());
//        template.setHashValueSerializer(RedisSerializer.string());
//        //返回
//        return template;
//    }
//}