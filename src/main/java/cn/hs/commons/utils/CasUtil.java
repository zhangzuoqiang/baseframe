/**
 * 
 */
package cn.hs.commons.utils;

import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Title: CasUtil<br>
 * Description: <br>
 * Company: ORCHIS<br>
 * Copyright @ 2010 ORCHIS .All rights reserved.<br>
 * @author MengW
 * @version 2010-1-26 1.0
 */
public class CasUtil {

	public static String getService(HttpServletRequest httpservletrequest,
			String s) throws ServletException {
		if (s == null)
			throw new IllegalArgumentException("name of server is required");
		StringBuffer stringbuffer = new StringBuffer();
		if (httpservletrequest.isSecure())
			stringbuffer.append("https://");
		else
			stringbuffer.append("http://");
		stringbuffer.append(s);
		stringbuffer.append(httpservletrequest.getRequestURI());
		if (httpservletrequest.getQueryString() != null) {
			int i = httpservletrequest.getQueryString().indexOf("ticket=");
			if (i == -1)
				stringbuffer.append("?" + httpservletrequest.getQueryString());
			else if (i > 0) {
				int j = httpservletrequest.getQueryString().indexOf("&ticket=");
				if (j == -1)
					stringbuffer.append("?"
							+ httpservletrequest.getQueryString());
				else if (j > 0)
					stringbuffer.append("?"
							+ httpservletrequest.getQueryString().substring(0,
									j));
			}
		}
		return URLEncoder.encode(stringbuffer.toString());
	}

}
