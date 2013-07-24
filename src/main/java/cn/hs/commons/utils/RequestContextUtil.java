/*
 * $Log: RequestContextUtil.java,v $
 * Revision 1.1  2012/05/23 09:27:46  guor
 * 初次提交
 *
 */
package cn.hs.commons.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Title: SystemContextUtil<br>
 * Description: <br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author MengW
 * @createDate 2011-9-20
 * @version $Revision: 1.1 $
 */
public class RequestContextUtil {

	public static final ThreadLocal<HttpServletRequest> session = new ThreadLocal<HttpServletRequest>();
	
	/**
	 * 设置Request
	 * @param request
	 */
	public static void setRequest(HttpServletRequest request){
		session.set(request);
	}
	
	/**
	 * 获取Request
	 * @return
	 */
	public static HttpServletRequest getRequest(){
		return (HttpServletRequest)session.get();
	}
	
	/**
	 * 清除设置Request
	 */
	public static void clearRequest(){
		session.set(null);
	}
}
