/*
 * $Log: RoleCondition.java,v $
 * Revision 1.1  2012/05/23 09:27:55  guor
 * 初次提交
 *
 */
package cn.hs.module.user.domain;

import cn.hs.core.basedao.condition.BasePageCondition;

/**
 * Title: UserCondition<br>
 * Description:用户condition <br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author XiangBin
 * @createDate Jul 31, 2012
 * @version $Revision:$
 */
public class UserInfoCondition extends BasePageCondition {

	private String searchLoginID;// 登录ID
	private String searchPassWord;// 密码

	/**
	 * 查询条件：BaseUserService主键数据
	 */
	private String[] searchUserIds;

	/**
	 * 查询条件：启用/作废
	 */
	private String searchIsEnabled;

	/**
	 * 查询条件：用户编号
	 */
	private String searchUserId;

	/**
	 * 查询条件：用户名
	 */
	private String searchUserName;

	/**
	 * 查询条件：活动状态
	 */
	private String searchActiveState;

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

	public String getSearchIsEnabled() {
		return searchIsEnabled;
	}

	public void setSearchIsEnabled(String searchIsEnabled) {
		this.searchIsEnabled = searchIsEnabled;
	}

	public String getSearchUserId() {
		return searchUserId;
	}

	public void setSearchUserId(String searchUserId) {
		this.searchUserId = searchUserId;
	}

	public String getSearchLoginID() {
		return searchLoginID;
	}

	public void setSearchLoginID(String searchLoginID) {
		this.searchLoginID = searchLoginID;
	}

	public String getSearchUserName() {
		return searchUserName;
	}

	public void setSearchUserName(String searchUserName) {
		this.searchUserName = searchUserName;
	}

	public String getSearchActiveState() {
		return searchActiveState;
	}

	public void setSearchActiveState(String searchActiveState) {
		this.searchActiveState = searchActiveState;
	}

	public String getSearchDepID() {
		return searchDepID;
	}

	public void setSearchDepID(String searchDepID) {
		this.searchDepID = searchDepID;
	}

	public String getSearchPassWord() {
		return searchPassWord;
	}

	public void setSearchPassWord(String searchPassWord) {
		this.searchPassWord = searchPassWord;
	}
}
