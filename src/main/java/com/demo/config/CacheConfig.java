package com.demo.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.CacheFactoryBean;
import org.springframework.data.gemfire.LocalRegionFactoryBean;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.data.gemfire.support.GemfireCacheManager;

import com.gemstone.gemfire.cache.Cache;

@EnableCaching
@EnableGemfireRepositories
@Configuration
public class CacheConfig {

	@Bean
	public CacheFactoryBean cacheFactoryBean() {
		return new CacheFactoryBean();
	}

	@Bean
	public LocalRegionFactoryBean<Integer, Integer> localRegionFactoryBean(final Cache cache) {
		return new LocalRegionFactoryBean<Integer, Integer>() {
			{
				setCache(cache);
				setName("games");
			}
		};
	}
	    
	@Bean
	public GemfireCacheManager cacheManager(final Cache gemfireCache) {
		return new GemfireCacheManager() {
			{
				setCache(gemfireCache);
			}
		};
	}

}
