package cn.hs.core.springext.security.decision;

import java.util.Collection;
import java.util.Iterator;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import cn.hs.core.log4j.ILog4jManager;
import cn.hs.core.springext.mvc.SessionConstant;

/**
 * Title: BaseAccessDecisionManager<br>
 * Description: 访问决策器,用户是否拥有所请求资源的权限<br>
 * Company: EORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * 
 * @author ZhangKW
 * @createDate 2011-5-23
 * @version $Revision: 1.1 $
 */
public class BaseAccessDecisionManager implements AccessDecisionManager {
	@Autowired
	private ILog4jManager log4jManager;

	/*
	 * @see org.springframework.security.access.AccessDecisionManager#decide(org.
	 *      springframework.security.core.Authentication, java.lang.Object,
	 *      java.util.Collection)
	 */
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		if (configAttributes == null) {
			return;
		}
		// 如果是厂商用户已登录
		if (object instanceof FilterInvocation) {
			FilterInvocation filterInvocation = (FilterInvocation) object;
			if (filterInvocation.getHttpRequest().getSession().getAttribute(SessionConstant.VENDOR_USER_INFO) != null) {
				return;
			}

		}
		// 所请求的资源拥有的权限(一个资源对多个权限)
		Iterator<ConfigAttribute> iterator = configAttributes.iterator();
		while (iterator.hasNext()) {
			ConfigAttribute configAttribute = iterator.next();
			// 访问所请求资源所需要的权限
			String needPermission = configAttribute.getAttribute();
			log4jManager.debugCustomLog(getClass().getName(), "decide", "needPermission is " + needPermission);
			// 用户所拥有的权限authentication
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				if (needPermission.equals(ga.getAuthority())) {
					return;
				}
			}
		}
		// 没有权限
		throw new AccessDeniedException(" 没有权限访问！ ");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
}
