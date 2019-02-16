/*
 * This class holds a single instance of ehcache that can be used
 * by the Application.
 */

package com.sks.ehcache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;

public final class CacheHolder {
    private final String preconfigured = "preConfigured";
    private final String cache = "cacheonwork";
    private Cache<Integer, String> myCache;
    private static class InstanceHolder {
        public static CacheHolder instance = new CacheHolder();
    }
    
    private CacheHolder() {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().withCache(preconfigured,
                     CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, String.class,
                                                    ResourcePoolsBuilder.heap(100))
                     .build())
                .build(true);

        myCache = cacheManager.createCache(cache,
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class,
                        String.class,
                        ResourcePoolsBuilder.newResourcePoolsBuilder()
                        .heap(1, EntryUnit.ENTRIES)
                        .offheap(2, MemoryUnit.MB))
                        .build());
    }
    
    public static CacheHolder getInstance() {
        return  InstanceHolder.instance;
    }
    
    public Cache<Integer, String> getCache() {
        return myCache;
    }    
}
