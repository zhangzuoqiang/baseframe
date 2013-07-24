/*
 * $Log: SaveRequestFilter.java,v $
 * Revision 1.1  2012/05/23 09:27:48  guor
 * 初次提交
 *
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

import cn.hs.commons.utils.RequestContextUtil;

/**
 * Title: SaveRequestFilter<br>
 * Description: <br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author MengW
 * @createDate 2011-9-20
 * @version $Revision: 1.1 $
 */
public class SaveRequestFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// request设置
		RequestContextUtil.clearRequest();
		RequestContextUtil.setRequest((HttpServletRequest) arg0);
		// TODO applicationContext设置
		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
