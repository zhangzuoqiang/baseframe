package cn.hs.core.springext.security.casclient.detail;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * Title: WebMappingAuthenticationDetailsSource<br>
 * Description: 验证细节扩展对象生成器<br>
 * Company: EORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * 
 * @author ZhangKW
 * @createDate 2011-6-28
 * @version $Revision: 1.1 $
 */
public class WebMappingAuthenticationDetailsSource
		implements
		AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {

	@Override
	public WebAuthenticationDetails buildDetails(HttpServletRequest context) {

		// 此处返回自己的扩展类
		return new WebMappingAuthenticationDetails(context);
	}

}
