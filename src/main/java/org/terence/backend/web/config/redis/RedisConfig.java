package org.terence.backend.web.config.redis;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

/**
 * @author terence
 * @since 2019/3/19 11:42
 */
@Configuration
@EnableCaching
public class RedisConfig {

    /*@Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        return RedisCacheManager.create(connectionFactory);
    }*/

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        // initialize a RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        // jackson to json serializer will cause StackOverFlow exception
        // RedisSerializer<Object> jsonSerializer = new GenericJackson2JsonRedisSerializer();

        // use fastJson serializer
        RedisSerializer<Object> jsonSerializer = new GenericFastJsonRedisSerializer();
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair
                .fromSerializer(jsonSerializer);
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(pair);
        // this config does not work
        // defaultCacheConfig.entryTtl(Duration.ofSeconds(30));

        // set default expire time 1800s
        RedisCacheConfiguration configuration = defaultCacheConfig.entryTtl(Duration.ofSeconds(1800));
        // initialize RedisCacheManager
        return new RedisCacheManager(redisCacheWriter, configuration);
    }
}
