/*
 *$Log:$
 */
package cn.hs.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Title: CodeFilter<br>
 * Description:登录验证码过滤器 <br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author GuoR
 * @createDate 2012-10-13
 * @version $Revision:$
 */
public class CodeFilter implements Filter {
	private String errorPage = "";

	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String checkCode = request.getParameter("checkCode");
		String loginName = request.getParameter("j_username");
		String[] type = null;
		if (loginName != null && !"".equals(loginName)) {
			type = loginName.split("#");
			if (type != null && type[0].equals("inner")) {
				// 内网登陆放行
				filterChain.doFilter(servletRequest, servletResponse);
				return;
			}
		}
		String checkValue = (String) request.getSession().getAttribute("checkValue");
		if (checkCode != null && !"".equals(checkCode)) {
			if (checkValue.equals(checkCode)) {
				filterChain.doFilter(servletRequest, servletResponse);
			} else {
				String errorCode = "?error=code";
				if (errorPage.indexOf("?") != -1) {
					errorCode = "&error=code";
				}
				response.sendRedirect(request.getContextPath() + errorPage + errorCode);
			}
		} else {
			String errorCode = "?error=code";
			if (errorPage.indexOf("?") != -1) {
				errorCode = "&error=code";
			}
			response.sendRedirect(request.getContextPath() + errorPage + errorCode);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		setErrorPage(arg0.getInitParameter("errorPage"));
	}

	/**
	 * @param errorPage
	 *            the errorPage to set
	 */
	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

}
