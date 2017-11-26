package com.cobble.takeaway.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheUtil {
	private static CacheUtil instance = null;
	public static String CACHE_NAME = "taCahce";
	
	private CacheUtil() {
		
	}
	
	public static CacheUtil getInstance() {
		if (instance != null) {
			return instance;
		}
		synchronized (CacheUtil.class) {
            if (instance == null) {
            		instance = new CacheUtil();
            }
            return instance;
        }
	}
	
	public void put(Object key, Object value) {
		CacheManager cacheManager = CacheManager.getInstance();
		Cache cache = cacheManager.getCache(CACHE_NAME);
		cache.put(new Element(key, value));
	}
	
	public Object get(Object key) {
		Object ret = null;
		
		CacheManager cacheManager = CacheManager.getInstance();
		Cache cache = cacheManager.getCache(CACHE_NAME);
		if (cache == null) {
			return null;
		}
		
		Element ele = cache.get(key);
		
		if (ele == null) {
			return null;
		}
		
		Object value = ele.getObjectValue();
		
		ret = value;

		return ret;
	}
	
	public void put(String key, String value) {
		CacheManager cacheManager = CacheManager.getInstance();
		Cache cache = cacheManager.getCache(CACHE_NAME);
		cache.put(new Element(key, value));
	}
	
	public String get(String key) {
		String ret = null;
		
		CacheManager cacheManager = CacheManager.getInstance();
		Cache cache = cacheManager.getCache(CACHE_NAME);
		if (cache == null) {
			return null;
		}
		
		Element ele = cache.get(key);
		
		if (ele == null) {
			return null;
		}
		
		Object value = ele.getObjectValue();
		
		ret = (String) value;

		return ret;
	}
	
}
