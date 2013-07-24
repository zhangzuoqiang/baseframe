package cn.hs.module.online.ui.commond;

import java.util.List;

import cn.hs.module.online.domain.OnlineUserQB;

/**
 * Title: OnlineUserCommond<br>
 * Description: 在线用户Commond<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * 
 * @author ZhangKW
 * @createDate 2012-5-29
 * @version $Revision: 11 $
 */
public class OnlineUserCommond {

	private List<OnlineUserQB> onlineUserList; // 在线用户集合

	/**
	 * @return the onlineUserList
	 */
	public List<OnlineUserQB> getOnlineUserList() {
		return onlineUserList;
	}

	/**
	 * @param onlineUserList
	 *            the onlineUserList to set
	 */
	public void setOnlineUserList(List<OnlineUserQB> onlineUserList) {
		this.onlineUserList = onlineUserList;
	}

}
