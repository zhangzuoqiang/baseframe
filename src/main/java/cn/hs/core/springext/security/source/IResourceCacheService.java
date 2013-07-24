/*
 * $Log: IResourceCacheService.java,v $
 * Revision 1.1  2012/05/23 09:27:50  guor
 * 初次提交
 *
 */
package cn.hs.core.springext.security.source;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;

/**
 * Title: IResourceCacheService<br>
 * Description: 加载系统<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author GuoR
 * @createDate 2011-10-12
 * @version $Revision: 1.1 $
 */
public interface IResourceCacheService {
	/**
	 * 加载系统资源
	 * @return 系统资源MAP
	 * @throws Exception
	 * @author GuoR
	 * @date 2011-10-12
	 */
	public Map<String, Collection<ConfigAttribute>> loadResource();
	/**
	 * 增加系统资源
	 * @param resouceIdentity 资源唯一标识
	 * @param resource 资源对象Collection
	 * @throws Exception
	 * @author GuoR
	 * @date 2011-10-12
	 */
	public void putConfigAttribute(String resouceIdentity, Collection<ConfigAttribute> resource);
	/**
	 * 获取系统资源
	 * @param resouceIdentity 资源唯一标识
	 * @return 资源对象Collection
	 * @throws Exception
	 * @author GuoR
	 * @date 2011-10-12
	 */
	public Collection<ConfigAttribute> getConfigAttribute(String resouceIdentity);
	/**
	 * 清空系统资源缓存
	 * @throws Exception
	 * @author GuoR
	 * @date 2011-10-12
	 */
	public void clearMap();
	
	/**
	 * 
	 * @return
	 * @author RongLT
	 * @date 2011-10-12
	 */
	public Map<String, Collection<ConfigAttribute>> reLoadResource();
	
}
