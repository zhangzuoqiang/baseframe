package cn.hs.module.user.ui.commond;

import cn.hs.core.page.commond.BasePageCommond;

/**
 * Title: BaseUserCommond<br>
 * Description: BaseUserServiceCommond<br>
 * Company: GoldGov<br>
 * Copyright @ 2012 GoldGov .All rights reserved.<br>
 * @author Limk
 * @createDate 2012-08-01
 * @version $Revision$
 */
public class UserInfoCommond extends BasePageCommond {

	private static final long serialVersionUID = 1L;

	private String[] searchUserIds;

	private String searchUserId;// 查询条件：主键

	private String searchIsEnabled;// 查询条件：启用/作废
	private String searchLoginID;// 登录ID
	/**
	 * 查询条件： 部门的ID
	 */
	private String searchDepID;

	public String[] getSearchUserIds() {
		return searchUserIds;
	}

	public void setSearchUserIds(String[] searchUserIds) {
		this.searchUserIds = searchUserIds;
	}

	public String getSearchUserId() {
		return searchUserId;
	}

	public void setSearchUserId(String searchUserId) {
		this.searchUserId = searchUserId;
	}

	public String getSearchIsEnabled() {
		return searchIsEnabled;
	}

	public void setSearchIsEnabled(String searchIsEnabled) {
		this.searchIsEnabled = searchIsEnabled;
	}

	public String getSearchDepID() {
		return searchDepID;
	}

	public void setSearchDepID(String searchDepID) {
		this.searchDepID = searchDepID;
	}

	public String getSearchLoginID() {
		return searchLoginID;
	}

	public void setSearchLoginID(String searchLoginID) {
		this.searchLoginID = searchLoginID;
	}
}
