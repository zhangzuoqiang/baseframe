package cn.hs.core.springext.security.source;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import cn.hs.core.log4j.ILog4jManager;

/**
 * Title: BaseSecurityMetadataSource<br>
 * Description: 资源数据定义管理器，装载所有受权限控制的资源 <br>
 * Company: EORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * 
 * @author ZhangKW
 * @createDate 2011-5-23
 * @version $Revision: 1.1 $
 */

public class BaseSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	@Autowired
	private ILog4jManager log4jManager;

	// 资源映射器
	private IResourceCacheService resourceCatceService;

	public BaseSecurityMetadataSource(IResourceCacheService resourceCatceService) {
		this.resourceCatceService = resourceCatceService;
	}

	public BaseSecurityMetadataSource() {
		resourceCatceService.loadResource();
	}

	/**
	 * 加载所有受权限控制的资源
	 * 
	 * @author ZhangKW
	 * @date 2011-5-23
	 */
	public void loadResourceDefine() {
		getResourceCatceService().loadResource();
	}

	/*
	 * 返回所请求资源标识()
	 * 
	 * @see org.springframework.security.access.SecurityMetadataSource#getAttributes
	 *      (java.lang.Object)
	 */
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		log4jManager.debugCustomLog(getClass().getName(), "getAttributes", "requestUrl is " + requestUrl);
		return getResourceCatceService().getConfigAttribute(requestUrl);
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public IResourceCacheService getResourceCatceService() {
		return resourceCatceService;
	}

	public void setResourceCatceService(IResourceCacheService resourceCatceService) {
		this.resourceCatceService = resourceCatceService;
	}

}
