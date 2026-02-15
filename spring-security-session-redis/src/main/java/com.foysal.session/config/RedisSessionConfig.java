//package com.foysal.session.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.security.jackson2.SecurityJackson2Modules;
//
//import java.util.List;
//// This is an optional config just to store the data in redis as a json
//@Configuration
//public class RedisSessionConfig {
//
//    @Bean
//    public ObjectMapper redisObjectMapper() {
//        // Create Jackson ObjectMapper for Redis session serialization
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
//
//        // Register Spring Security modules to handle Security objects (Authentication, etc.)
//        List<com.fasterxml.jackson.databind.Module> modules =
//                SecurityJackson2Modules.getModules(getClass().getClassLoader());
//        mapper.registerModules(modules);
//
//        return mapper;
//    }
//
//    @Bean
//    public RedisSerializer<Object> springSessionDefaultRedisSerializer(ObjectMapper redisObjectMapper) {
//        // Use GenericJackson2JsonRedisSerializer to save session data as JSON in Redis
//        // This bean name is automatically picked up by Spring Session
//        return new GenericJackson2JsonRedisSerializer(redisObjectMapper);
//    }
//}
