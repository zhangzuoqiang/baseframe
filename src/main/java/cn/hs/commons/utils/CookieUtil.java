package cn.hs.commons.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

	/**
	 * 
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static String getCookie(HttpServletRequest request, String cookieName) {
		String value = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals(cookieName)) {
					value = cookie.getValue().trim();
				}
			}
		}
		return value;
	}

	/**
	 * 
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static Cookie getCookieObj(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals(cookieName)) {
					return cookie;
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @param response
	 * @param cookieName
	 * @param value
	 * @param timeout
	 * @param path
	 * @param domain
	 */
	public static void addCookie(HttpServletResponse response, String cookieName, String value, int timeout, String path, String domain) {
		Cookie c = new Cookie(cookieName, value);
		c.setMaxAge(timeout);
		c.setPath(path);
		c.setDomain(domain);
		response.addCookie(c);
	}

}
