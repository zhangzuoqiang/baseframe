/*
 * $Log: RoleCondition.java,v $
 * Revision 1.1  2012/05/23 09:27:55  guor
 * 初次提交
 *
 */
package cn.hs.module.role.domain;

import cn.hs.core.basedao.condition.BasePageCondition;

/**
 * Title: RoleCondition<br>
 * Description: 角色condition<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 23, 2011
 * @version $Revision: 62 $
 */
public class RoleCondition extends BasePageCondition {

	private String searchRoleName;// 查询的角色名
	private String queryRoleID;// 查询角色ID
	private String[] roleIds;// 查询角色IDs
	private String searchRoleType;// 查询的角色类型
	private Integer searchActiveState;// 查询的角色状态
	private String queryRoleCode;// 查询角色编码

	public String getSearchRoleName() {
		return searchRoleName;
	}

	public void setSearchRoleName(String searchRoleName) {
		this.searchRoleName = searchRoleName;
	}

	public String getQueryRoleID() {
		return queryRoleID;
	}

	public void setQueryRoleID(String queryRoleID) {
		this.queryRoleID = queryRoleID;
	}

	public String[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	public String getSearchRoleType() {
		return searchRoleType;
	}

	public void setSearchRoleType(String searchRoleType) {
		this.searchRoleType = searchRoleType;
	}

	public Integer getSearchActiveState() {
		return searchActiveState;
	}

	public void setSearchActiveState(Integer searchActiveState) {
		this.searchActiveState = searchActiveState;
	}

	public String getQueryRoleCode() {
		return queryRoleCode;
	}

	public void setQueryRoleCode(String queryRoleCode) {
		this.queryRoleCode = queryRoleCode;
	}

}
