package com.cobble.takeaway.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheUtil {
	private final static Logger logger = LoggerFactory.getLogger(CacheUtil.class);
	private static CacheUtil instance = null;
	public static String CACHE_NAME = "taCache";
	
	private Cache cache = null;
	
	private CacheUtil() {
		init();
	}
	
	private void init() {
		CacheManager cacheManager = CacheManager.getInstance();
		cache = cacheManager.getCache(CACHE_NAME);
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
		cache.put(new Element(key, value));
	}
	
	public Object get(Object key) {
		Object ret = null;
		
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
		cache.put(new Element(key, value));
	}

	public void put(String key, String value, int timeToLiveSeconds) {
		Element element = new Element(key, value, false, timeToLiveSeconds, timeToLiveSeconds);
		cache.put(element);
	}
	
	public String get(String key) {
		String ret = null;
		
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
	
	public static void main(String[] argv) throws Exception {
		String key = "testKey";
		String value = "value----test";
		int timeToLiveSeconds = 10;
		CacheUtil.getInstance().put(key, value, timeToLiveSeconds);
		for (int i = 0; i < 20; i++) {
			logger.info("Key-value: {}", CacheUtil.getInstance().get(key));
			Thread.sleep(1000);
		}
	}
}
