package com.spittr.tools;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;

public class CacheUtils {
	
	public static Cache getCache(String cacheName){
		CacheManager cacheManager = CacheManager.getInstance();
		Cache cache = cacheManager.getCache(cacheName);

		if (cache == null) {
			CacheConfiguration cacheConfiguration = cacheManager.getConfiguration().getDefaultCacheConfiguration();
			cacheConfiguration.setName(cacheName);
			cache = new Cache(cacheConfiguration);
			cacheManager.addCache(cache);
		}
		
		return cache;
	}
}
