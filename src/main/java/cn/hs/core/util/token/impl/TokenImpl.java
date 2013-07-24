/*
 * $Log: TokenImpl.java,v $
 * Revision 1.1  2012/05/23 09:27:51  guor
 * 初次提交
 *
 */
package cn.hs.core.util.token.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.hs.commons.utils.Md5Util;
import cn.hs.core.log4j.ILog4jManager;
import cn.hs.core.util.token.IToken;

/**
 * Title: TokenImpl<br>
 * Description: 令牌实现类，防止2次提交表单<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 7, 2011
 * @version $Revision: 1.1 $
 */
@Repository(value = "cn.hs.core.util.token.impl.TokenImpl")
public class TokenImpl implements IToken {
	// 初始化日志接口
	@Autowired
	private ILog4jManager log4jManager;

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
	@Override
	public boolean isTokenValid(HttpServletRequest request, boolean resetToken) throws Exception {
		HttpSession session = request.getSession();
		if (session != null) {
			// 获取上一次存放在session中的tonken
			String savedSessionToken = (String) session.getAttribute(IToken.TOKEN_STRING);
			if (savedSessionToken != null) {
				if (resetToken) {
					// 重置session中的token
					session.removeAttribute(IToken.TOKEN_STRING);
				}
				// 获取本次request中的token
				String requestToken = request.getParameter(IToken.TOKEN_STRING);
				if (requestToken != null) {
					// 返回本次request中tonken与上一次存放在session中的token的equals值
					return savedSessionToken.equals(requestToken);
				} else {
					// 如果本次request中不存在token，则验证失败
					return false;
				}
			} else {
				log4jManager.errorCustomLog(getClass().getName(), "isTokenValid", "session中存放的token为空");
			}
		} else {
			log4jManager.errorCustomLog(getClass().getName(), "isTokenValid", "session为空");
		}
		return false;
	}

	/**
	 * 设置令牌
	 * 
	 * @param request
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 7, 2011
	 */
	@Override
	public String saveToken(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		if (session != null) {
			// 利用本次访问地址生成MD5加密token
			String token = generateToken(request);
			if (token != null) {
				// 将token放置session中，如果上一次session中存在token，则替换之
				session.setAttribute(IToken.TOKEN_STRING, token);
			}
			return token;
		} else {
			log4jManager.errorCustomLog(getClass().getName(), "saveToken", "session为空");
			return null;
		}
	}

	/**
	 * 生成令牌
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 7, 2011
	 */
	private String generateToken(HttpServletRequest request) throws Exception {
		// 获取本次请求地址
		String token = request.getRequestURL().toString();
		// 返回MD5加密地址
		return Md5Util.getMd5(token);
	}
}
