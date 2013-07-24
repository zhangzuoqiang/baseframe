/*
 * $Log: SystemParameterCacheManager.java,v $
 * Revision 1.3  2012/06/01 09:19:58  guor
 * 增加根据系统参数KEY获取系统参数VALUE的方法
 *
 * Revision 1.2  2012/06/01 09:15:50  guor
 * 增加根据系统参数KEY获取系统参数VALUE的方法
 *
 * Revision 1.1  2012/05/31 04:28:19  ronglt
 * 迁移系统参数到资源展现平台
 *
 * Revision 1.2  2012/05/25 09:02:01  ronglt
 * 注释掉清楚系统缓存中的无用的参数，考虑清楚逻辑之后再重写
 *
 * Revision 1.1  2012/05/25 06:07:21  ronglt
 * 增加系统参数缓存
 *
 * Revision 1.1  2012/04/13 08:41:49  guor
 * *** empty log message ***
 *
 */
package cn.hs.module.systemparameter.cache;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.reflect.Field;

import cn.hs.core.cache.CacheFactory;
import cn.hs.core.cache.IBaseCache;
import cn.hs.module.systemparameter.SystemParameterConstant;
import cn.hs.module.systemparameter.domain.SystemParameter;

/**
 * Title: SystemParameterCacheManager<br>
 * Description: 系统参数缓存管理<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author MengW
 * @createDate 2011-10-12
 * @version $Revision: 11 $
 */
public class SystemParameterCacheManager {
	// modify by RongLT 2011-10-17
	// 增加服务器缓存,同步用户从不同服务器访问应用时的数据
	private IBaseCache baseCache;

	public IBaseCache getBaseCache() {
		return baseCache;
	}

	public void setBaseCache(IBaseCache baseCache) {
		this.baseCache = baseCache;
	}

	private static SystemParameterCacheManager systemParameterCacheManager = null;

	private SystemParameterCacheManager() {

	}

	/**
	 * 获得系统参数缓存管理唯一实例
	 * 
	 * @return 获得系统参数缓存管理实例对象
	 */
	public static SystemParameterCacheManager getInstance() {
		if (systemParameterCacheManager == null) {
			systemParameterCacheManager = new SystemParameterCacheManager();
		}
		return systemParameterCacheManager;
	}

	/**
	 * 获得缓存中系统参数值
	 * 
	 * @param systemParameterName
	 *            系统参数名
	 * @return 获得缓存中系统参数值
	 * @throws Exception 
	 */
	public String getSystemParameterValue(String systemParameterName) {
		if (baseCache.containsKey(CacheFactory.PREFIX_CACHE_KEY
				+ systemParameterName)) {
			return (String) baseCache.get(CacheFactory.PREFIX_CACHE_KEY
					+ systemParameterName);
		}
		return null;
	}

	/**
	 * 根据传入系统参数实体对象集合更新缓存
	 * 
	 * @param systemParameterList
	 * @throws Exception
	 */
	public synchronized void synchronizeCache(
			List<SystemParameter> systemParameterList) throws Exception {
		if (systemParameterList != null && systemParameterList.size() > 0) {
			// 保存参数到缓存
			svaeSystemParameterToCache(systemParameterList);
			// 清除缓存中无用的系统参数
			//TODO 需要好好想想这地方的逻辑
			//clearSystemParameterInCache(systemParameterList);
		} else {
			baseCache.removeAll();
		}
	}

	/**
	 * 保存系统参数到缓存
	 * 
	 * @param systemParameterList
	 *            系统参数实体对象集合
	 */
	private void svaeSystemParameterToCache(
			List<SystemParameter> systemParameterList) {
		for (SystemParameter systemParameter : systemParameterList) {
			baseCache.put(CacheFactory.PREFIX_CACHE_KEY
					+ systemParameter.getSystemParameterName(), systemParameter
					.getSystemParameterValue());
		}
	}

	/**
	 * 清除缓存中无用的系统参数
	 * 
	 * @param systemParameterList
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void clearSystemParameterInCache(
			List<SystemParameter> systemParameterList) throws Exception {
		// modify by RongLT 2011-10-13
		// 获取系统参数常量List
		// -------start
		Class c = SystemParameterConstant.class;
		Field[] fields = c.getDeclaredFields();
		List<String> keys = new ArrayList<String>();
		for (int i = 0; i < fields.length; i++) {
			keys.add((String) fields[i].get(c));
		}
		List<String> removeKeyList = new ArrayList<String>();
		Iterator<String> keyIterator = keys.iterator();
		// -------end
		String key = null;
		label1: while (keyIterator.hasNext()) {
			key = keyIterator.next();
			for (SystemParameter systemParameter : systemParameterList) {
				if (systemParameter.getSystemParameterName().equals(key)) {
					continue label1;
				}
			}
			removeKeyList.add(CacheFactory.PREFIX_CACHE_KEY + key);
		}
		for (String removeKey : removeKeyList) {
			baseCache.remove(removeKey);
		}
	}

	// public static void main(String[] args) {
	// systemParameterCache.put("a", "a");
	// systemParameterCache.put("b", "b");
	//		
	// List <SystemParameter> systemParameterList = new
	// ArrayList<SystemParameter>();
	// SystemParameter s1 = new SystemParameter();
	// s1.setSystemParameterName("a");
	// s1.setSystemParameterValue("a1");
	// SystemParameter s2 = new SystemParameter();
	// s2.setSystemParameterName("c");
	// s2.setSystemParameterValue("c");
	// systemParameterList.add(s1);
	// systemParameterList.add(s2);
	// SystemParameterCacheManager.getInstance().synchronizeCache(systemParameterList);
	// }

}
