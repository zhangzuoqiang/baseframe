package cn.hs.core.cache.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.hs.core.cache.IBaseCache;

/**
 * 缓存的MapCached实现 Title: MapCachedBaseCache<br>
 * Description: <br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author RongLT
 * @createDate 2011-10-14
 * @version $Revision: 1.2 $
 */
public class MapCachedBaseCache implements IBaseCache {

	protected static Map<String, Object> baseMap = new HashMap<String, Object>();
	private static MapCachedBaseCache mapCacheResourceBaseCache = null;

	private MapCachedBaseCache() {

	}

	public static MapCachedBaseCache getInstance() {
		if (mapCacheResourceBaseCache == null) {
			mapCacheResourceBaseCache = new MapCachedBaseCache();
		}
		return mapCacheResourceBaseCache;
	}

	/**
	 * 读取缓存
	 * 
	 * @param key
	 * @return
	 * @author RongLT
	 * @date 2011-10-14
	 */
	@Override
	public Object get(String key) {
		Object result = baseMap.get(key);
		if (result != null) {
			return result;
		} 
		return null;
	}

	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @author RongLT
	 * @date 2011-10-14
	 */
	@Override
	public boolean put(String key, Object value) {
		baseMap.put(key, value);
		return true;
	}

	/**
	 * 写入缓存，包括过期时间
	 * 
	 * @param key
	 * @param value
	 * @param date
	 * @return
	 * @author RongLT
	 * @date 2011-10-14
	 */
	@Override
	public boolean put(String key, Object value, Date date) {
		baseMap.put(key, value);
		return true;
	}

	/**
	 * 删除缓存
	 * 
	 * @param key
	 * @return
	 * @author RongLT
	 * @date 2011-10-14
	 */
	@Override
	public boolean remove(String key) {
		baseMap.remove(key);
		return true;
	}

	/**
	 * 清空缓存
	 * 
	 * @return
	 * @author RongLT
	 * @date 2011-10-14
	 */
	@Override
	public boolean removeAll() {
		baseMap.clear();
		return true;
	}

	/**
	 * 判断对象是否包含key
	 * 
	 * @param key
	 * @return boolean
	 * @author RongLT
	 * @date 2011-10-14
	 */
	@Override
	public boolean containsKey(String key) {
		return baseMap.containsKey(key);
	}

	/**
	 * 获取缓存中的所有key
	 * 
	 * @return
	 * @author HuangS
	 * @date Jan 10, 2012
	 */
	@Override
	public Set<String> getAllKey() {
		return baseMap.keySet();
	}
}
