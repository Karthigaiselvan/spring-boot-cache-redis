package com.bezkoder.spring.jpa.postgresql;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class CacheConfiguration {

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() throws IOException {

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        return Redisson.create(config);
    }
    @Bean
    public CacheManager cacheManager(RedissonClient redissonClient) throws IOException {
        Map<String, CacheConfig> config = new HashMap<String, CacheConfig>();
        config.put("bootCache", new CacheConfig(24 * 60 * 1000, 15 * 60 * 1000));
        return new RedissonSpringCacheManager(redissonClient, config);
    }
}
