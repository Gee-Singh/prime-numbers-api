package com.prime.demo.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class implements simple ConcurrentMapCacheManager and creates a new in memory cache [prime-number]
 *
 * @See @Cacheable annotation in PrimeNumberService class
 */

@Configuration
@EnableCaching
public class CachingConfig {
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("prime-number");
    }
}