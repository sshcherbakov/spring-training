package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.data.gemfire.support.GemfireCacheManager;

import com.demo.game.model.Game;
import com.gemstone.gemfire.cache.Cache;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientRegionShortcut;

// TODO: 4. Add "cloud" profile to make use of Cloud Foundry caching service in PCF environment
@Profile("cloud")
@EnableGemfireRepositories
@Configuration
public class CacheConfig {

	@Bean
	public ClientRegionFactoryBean<Integer, Game> gameRegion(final ClientCache cache) {
		ClientRegionFactoryBean<Integer, Game> regionFactory =  new ClientRegionFactoryBean<>();
		regionFactory.setCache(cache);
		regionFactory.setName("games");
		regionFactory.setShortcut(ClientRegionShortcut.PROXY);
		return regionFactory;
	}
	    
	@Bean
	public GemfireCacheManager cacheManager(final Cache gemfireCache) {
		GemfireCacheManager cacheMgr =  new GemfireCacheManager();
		cacheMgr.setCache(gemfireCache);
		return cacheMgr;
	}

}
