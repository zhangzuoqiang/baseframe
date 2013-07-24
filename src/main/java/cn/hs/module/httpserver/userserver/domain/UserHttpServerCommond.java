package cn.hs.module.httpserver.userserver.domain;

import cn.hs.module.httpserver.HttpServerCommond;

/**
 * 
 * Title: UserHttpServerCommond<br>
 * Description: 用户httpserverCommond实体<br>
 * Company: HsS<br>
 * Copyright @ 2013 HsS .All rights reserved.<br>
 * @author HuangS
 * @createDate May 19, 2013
 * @version $Revision:$
 */
public class UserHttpServerCommond extends HttpServerCommond {

	private String userStr;

	public String getUserStr() {
		return userStr;
	}

	public void setUserStr(String userStr) {
		this.userStr = userStr;
	}

}
