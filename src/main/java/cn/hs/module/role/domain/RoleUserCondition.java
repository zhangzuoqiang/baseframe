/*
 * $Log: RoleCondition.java,v $
 * Revision 1.1  2012/05/23 09:27:55  guor
 * 初次提交
 *
 */
package cn.hs.module.role.domain;

import cn.hs.core.basedao.condition.BasePageCondition;

/**
 * Title: RoleUserCondition<br>
 * Description:角色人员condition <br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author XiangBin
 * @createDate Aug 1, 2012
 * @version $Revision:$
 */
public class RoleUserCondition extends BasePageCondition {

	private String queryRoleID;// 查询角色ID
	private String queryUserID;// 查询用户的ID
	private String[] searchRoleIDs;// 根据角色Ids查询
	private String searchRoleId; // 角色id
	private Integer activeStatus;// 活动状态 1、启用 2、作废
	private String[] userIds; // 用户id数组

	public String[] getSearchRoleIDs() {
		return searchRoleIDs;
	}

	public void setSearchRoleIDs(String[] searchRoleIDs) {
		this.searchRoleIDs = searchRoleIDs;
	}

	public String getQueryRoleID() {
		return queryRoleID;
	}

	public void setQueryRoleID(String queryRoleID) {
		this.queryRoleID = queryRoleID;
	}

	public String getQueryUserID() {
		return queryUserID;
	}

	public void setQueryUserID(String queryUserID) {
		this.queryUserID = queryUserID;
	}

	public String getSearchRoleId() {
		return searchRoleId;
	}

	public void setSearchRoleId(String searchRoleId) {
		this.searchRoleId = searchRoleId;
	}

	public Integer getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(Integer activeStatus) {
		this.activeStatus = activeStatus;
	}

	public String[] getUserIds() {
		return userIds;
	}

	public void setUserIds(String[] userIds) {
		this.userIds = userIds;
	}

}
