package cn.hs.core.springext.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.StringUtils;

import cn.hs.commons.utils.DynamicGetUrl;
import cn.hs.commons.utils.JSONUtils;
import cn.hs.core.springext.mvc.SessionConstant;
import cn.hs.core.springext.security.casclient.domain.json.CasDepartment;
import cn.hs.core.springext.security.casclient.domain.json.CasRole;
import cn.hs.core.springext.security.casclient.domain.json.CasSystems;
import cn.hs.core.springext.security.casclient.domain.json.CasUser;

/**
 * Title: BaseSecurityFilter<br>
 * Description: 资源权限过滤器扩展,优先执行此类进行自定义权限的过滤<br>
 * Company: EORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * 
 * @author ZhangKW
 * @createDate 2011-5-23
 * @version $Revision: 1.1 $
 */
public class BaseSecurityFilter extends AbstractSecurityInterceptor implements Filter {

	// 资源数据定义管理器
	private FilterInvocationSecurityMetadataSource securityMetadataSource;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		invoke(fi);
	}

	/**
	 * 调用扩展类进行权限验证
	 * 
	 * 1.获取请求资源的权限 执行BaseSecurityMetadataSource.getAttributes();
	 * 
	 * 2.判断是否拥有权限 执行BaseAccessDecisionManager.decide();
	 * 
	 * @param fi
	 *            FilterInvocation对象
	 * @throws IOException
	 * @throws ServletException
	 * @author ZhangKW
	 * @date 2011-5-26
	 */
	private void invoke(FilterInvocation fi) throws IOException, ServletException {
		// 取得登录用户
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication auth = ctx.getAuthentication();
		HttpServletRequest request = fi.getRequest();
		HttpSession session = request.getSession();
		// 获取当前session中的用户
		CasUser userInfo = (CasUser) session.getAttribute("userInfo");
		// 若当前用户为空，则进行cas服务端json解析，并存放置session中
		if (userInfo == null) {
			String casServerJson = null;
			if (auth.getPrincipal() instanceof UserDetails) {
				User userdetail = (User) auth.getPrincipal();
				casServerJson = userdetail.getUsername();
				// 解析Cas服务端返回json
				try {
					resolveServerJson(casServerJson, request);
					// 清除context
					SecurityContextHolder.clearContext();
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
		}
		InterceptorStatusToken token = super.beforeInvocation(fi);
		try {
			fi.getChain().doFilter(request, fi.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
	}

	/**
	 * 解析Cas服务端返回JSON
	 * 
	 * @param serverJson
	 * @param request
	 * @throws Exception
	 * @author WangWB
	 * @date Sep 6, 2011
	 */
	private void resolveServerJson(String serverJson, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		CasUser userInfo = (CasUser) session.getAttribute(SessionConstant.USER_INFO);

		boolean hasSystem = false;

		if (userInfo == null) {
			CasUser jsonUser = JSONUtils.jsonToObj(serverJson, new TypeReference<CasUser>() {
			});

			String flag = getResourceType(request);
			if (flag != null && flag.equals("/user/user/modifyPassword.do")) {
				hasSystem = true;
			}

			String requestURL = request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

			List<CasSystems> sysList = new ArrayList<CasSystems>();
			List<CasSystems> tempSysList = jsonUser.getSystemList();
			List<CasRole> roleList = new ArrayList<CasRole>();
			for (CasSystems system : tempSysList) {
				String tempSystemURL = system.getSystemURL();
				String tempSystemURLChanged = DynamicGetUrl.getUrl(tempSystemURL, request);
				if (tempSystemURLChanged.indexOf(requestURL) == -1) {
					continue;
				} else {
					hasSystem = true;
				}

				List<CasRole> tempRoleList = system.getRoleList();
				roleList.addAll(tempRoleList);
				sysList.add(system);
			}

			List<CasDepartment> deptList = jsonUser.getDepartmentList();

			if (hasSystem) {

				session.setAttribute(SessionConstant.CURRENT_DEPARTMENT, deptList);
				session.setAttribute(SessionConstant.SYSTEMS, sysList);
				session.setAttribute(SessionConstant.ROLES, roleList);
				if (roleList.size() == 1) {
					session.setAttribute(SessionConstant.CURRENT_ROLE, roleList.get(0));
				}
			}
			session.setAttribute(SessionConstant.USER_INFO, jsonUser);
			// FIXME 固定系统平台编码，以后动态获取或从SSO中获取
			session.setAttribute(SessionConstant.CURRENT_PLATFORM_CODE, "01001");

		}
	}

	private String getResourceType(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		String requestURI = request.getRequestURI();
		String pathInfo = StringUtils.replace(requestURI, contextPath, "");
		return pathInfo;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

	@Override
	public Class<? extends Object> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/**
	 * @return the securityMetadataSource
	 */
	public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
		return securityMetadataSource;
	}

	/**
	 * @param securityMetadataSource
	 *            the securityMetadataSource to set
	 */
	public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource) {
		this.securityMetadataSource = securityMetadataSource;
	}

}