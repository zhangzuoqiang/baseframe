/*
 * $Log: IToken.java,v $
 * Revision 1.1  2012/05/23 09:27:51  guor
 * 初次提交
 *
 */
package cn.hs.core.util.token;

import javax.servlet.http.HttpServletRequest;

/**
 * Title: IToken<br>
 * Description: 令牌接口，防止2次提交表单<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 7, 2011
 * @version $Revision: 1.1 $
 */
public interface IToken {
	/**
	 * 令牌attribute名称
	 */
	public static final String TOKEN_STRING = "springmvc.TOKEN";

	/**
	 * 设置令牌
	 * 
	 * @param request
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 7, 2011
	 */
	public String saveToken(HttpServletRequest request) throws Exception;

	/**
	 * 验证令牌
	 * 
	 * @param request
	 * @param resetToken
	 *            是否删除session中上次生成的令牌
	 * @return
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 7, 2011
	 */
	public boolean isTokenValid(HttpServletRequest request, boolean resetToken)
			throws Exception;
}
