/*
 * $Log: IOnlineUserService.java,v $
 * Revision 1.1  2012/05/30 02:33:08  zhangkw
 * 在线人员注销功能与列表展现
 *
 * Revision 1.1  2012/05/23 09:27:51  guor
 * 初次提交
 *
 */
package cn.hs.module.online.service;

import java.util.List;

import cn.hs.module.online.domain.OnlineUserCondition;
import cn.hs.module.online.domain.OnlineUserQB;

/**
 * Title: IOnlineService<br>
 * Description: 在线用户接口<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * 
 * @author ZhangKW
 * @createDate 2012-5-29
 * @version $Revision: 11 $
 */
public interface IOnlineUserService {

	/**
	 * 查询在线人员列表
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author ZhangKW
	 * @date 2012-5-29
	 */
	List<OnlineUserQB> listOnlineUser(OnlineUserCondition condition)
			throws Exception;
}
