package org.apdoer.signature.service.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApikeyCache {
	

	private Map<String, String> ApikeyMap = new ConcurrentHashMap<>();
	
	private ApikeyCache() {}


	private static class innerApiKeyCache{
		private static final ApikeyCache INSTANCE = new ApikeyCache();
	}


	public static ApikeyCache getInstant() {
		return ApikeyCache.getInstant();
	}

	public boolean containsKey(String key) {
		return this.ApikeyMap.containsKey(key);
	}

	public String get(String key) {
		return this.ApikeyMap.get(key);
	}
	
	public void put(String key, String value) {
		this.ApikeyMap.put(key, value);
	}
	
	public void remove(String key) {
		this.ApikeyMap.remove(key);
	}
}
