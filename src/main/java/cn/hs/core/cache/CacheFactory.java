package cn.hs.core.cache;

import cn.hs.core.cache.impl.MapCachedBaseCache;
import cn.hs.core.cache.impl.MemCachedBaseCache;

public class CacheFactory {
	public final static String PREFIX_CACHE_KEY = "CHINALIFE_";

	private CacheFactory() {
	}

	public static IBaseCache getMemCache() {
		return MemCachedBaseCache.getInstance();
	}

	public static IBaseCache getMapCache() {
		return MapCachedBaseCache.getInstance();
	}
}
