package cn.hs.core.springext.security.strategy.casclient.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;

import cn.hs.core.cache.CacheFactory;
import cn.hs.core.cache.IBaseCache;
import cn.hs.core.log4j.ILog4jManager;
import cn.hs.core.springext.security.source.BaseResource;
import cn.hs.core.springext.security.source.IBaseResourceDao;
import cn.hs.core.springext.security.source.IResourceCacheService;
import cn.hs.core.springext.security.source.LoadResourceDefineEvent;

/**
 * 装载所有受权限控制的资源并加入事件(增加提交、修改提交、启用作废等)监听 Title: ResourceCacheServiceImpl<br>
 * Description: <br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * 
 * @author RongLT
 * @createDate 2011-10-13
 * @version $Revision: 1.1 $
 */
public class ResourceCacheServiceImpl implements IResourceCacheService,
		ApplicationListener {

	private final static String MAP_NAME = CacheFactory.PREFIX_CACHE_KEY
			+ "SYSTEM_RESOURCE";
	private IBaseCache baseCache;
	private IBaseResourceDao resourceDao;

	/**
	 * 清空系统资源缓存
	 * 
	 * @author RongLT
	 * @date 2011-10-13
	 */
	@Override
	public void clearMap() {
		baseCache.remove(MAP_NAME);
	}

	/**
	 * 获取系统资源
	 * 
	 * @param resouceIdentity
	 *            资源唯一标识
	 * @return 资源对象Collection
	 * @author RongLT
	 * @date 2011-10-13
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<ConfigAttribute> getConfigAttribute(String resouceIdentity) {
		Map<String, Collection<ConfigAttribute>> resourceMap = null;
		try {
			resourceMap = (Map<String, Collection<ConfigAttribute>>) baseCache
					.get(MAP_NAME);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("获取系统资源异常");
		}
		// modify by HuangS at 2012-1-10
		if (resourceMap == null) {
			resourceMap = loadResource();
		}
		return resourceMap.get(resouceIdentity);
	}

	/**
	 * 加载系统资源
	 * 
	 * @return 系统资源MAP
	 * @author RongLT
	 * @date 2011-10-13
	 */
	@Override
	public Map<String, Collection<ConfigAttribute>> loadResource() {
		Map<String, Collection<ConfigAttribute>> resourceMap = this
				.getResourceMap();
		baseCache.put(MAP_NAME, resourceMap);
		return resourceMap;
	}

	/**
	 * 增加系统资源
	 * 
	 * @param resouceIdentity
	 *            资源唯一标识
	 * @param resource
	 *            资源对象Collection
	 * @author RongLT
	 * @date 2011-10-13
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void putConfigAttribute(String resouceIdentity,
			Collection<ConfigAttribute> resource) {
		// modify by HuangS at 2012-1-10
		Map<String, Collection<ConfigAttribute>> resourceMap = null;
			resourceMap = (Map<String, Collection<ConfigAttribute>>) baseCache
					.get(MAP_NAME);
		if (resourceMap == null) {
			resourceMap = this.getResourceMap();
		}
		resourceMap.put(resouceIdentity, resource);
		baseCache.put(MAP_NAME, resourceMap);
	}

	/**
	 * @return the resourceDao
	 */
	public IBaseResourceDao getResourceDao() {
		return resourceDao;
	}

	/**
	 * @param resourceDao
	 *            the resourceDao to set
	 */
	public void setResourceDao(IBaseResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	/**
	 * 重新加载系统资源
	 * 
	 * @return 系统资源MAP
	 * @author RongLT
	 * @date 2011-10-13
	 */
	@Override
	public Map<String, Collection<ConfigAttribute>> reLoadResource() {
		Map<String, Collection<ConfigAttribute>> resourceMap = this
				.getResourceMap();
		baseCache.put(MAP_NAME, resourceMap);
		return resourceMap;
	}

	public IBaseCache getBaseCache() {
		return baseCache;
	}

	public void setBaseCache(IBaseCache baseCache) {
		this.baseCache = baseCache;
	}

	/**
	 * 监听新增、启用、作废等操作事件,重新加载系统资源
	 * 
	 * @param event
	 * @author RongLT
	 * @date 2011-10-13
	 */
	public Map<String, Collection<ConfigAttribute>> getResourceMap() {
		Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		List<BaseResource> resourceList = null;
		try {
			resourceList = resourceDao.listSecurityResource();

			for (BaseResource resource : resourceList) {

				Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
				// 以权限唯一标识封装为Spring的security Object
				ConfigAttribute configAttribute = new SecurityConfig(resource
						.getIdentity());
				configAttributes.add(configAttribute);
				resourceMap.put(resource.getUrl(), configAttributes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resourceMap;
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof LoadResourceDefineEvent) {
			reLoadResource();
		}
	}
}
