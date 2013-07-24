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

import cn.hs.commons.utils.RequestContextUtil;

public class ChangeRequestCacheFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain fChain) throws IOException, ServletException {

		// request设置
		RequestContextUtil.clearRequest();
		RequestContextUtil.setRequest((HttpServletRequest) request);
		String reqStr = ((HttpServletRequest) request).getRequestURI();
		int indexLast = reqStr.lastIndexOf("/");
		String reqDo = reqStr.substring(indexLast + 1);
		if (reqDo.startsWith("list")) {
			((HttpServletResponse) response).setHeader("Cache-Control",
					"no-cache");
			((HttpServletResponse) response).setHeader("Pragma", "no-cache");
			((HttpServletResponse) response).setHeader("Expires", "-1");
		}
		fChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
