/*
 * $Log: RoleResourceCondition.java,v $
 * Revision 1.1  2012/05/23 09:27:56  guor
 * 初次提交
 *
 */
package cn.hs.module.role.domain;

import java.util.List;

import cn.hs.core.basedao.condition.BasePageCondition;

/**
 * Title: RoleResourceCondition<br>
 * Description: 角色资源condition<br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author XiangBin
 * @createDate Aug 1, 2012
 * @version $Revision:$
 */
public class RoleResourceCondition extends BasePageCondition {

	private String searchRoleName;// 查询的角色名
	private String queryRoleID;// 查询角色ID
	private String[] roleIds;// 查询角色IDs
	private String searchRoleType;// 查询的角色类型
	private Integer searchActiveState;// 查询的角色状态
	private String queryRoleCode;// 查询角色编码
	private String searchResourceName;// 资源名称
	private String searchDescription;// 资源描述
	private List<RoleResource> roleResoure;
	private String[] roleResourceIds;
	private String resourceID;
	private String[] roleCodes;

	public String[] getRoleCodes() {
		return roleCodes;
	}

	public void setRoleCodes(String[] roleCodes) {
		this.roleCodes = roleCodes;
	}

	public String getResourceID() {
		return resourceID;
	}

	public void setResourceID(String resourceID) {
		this.resourceID = resourceID;
	}

	public String[] getRoleResourceIds() {
		return roleResourceIds;
	}

	public void setRoleResourceIds(String[] roleResourceIds) {
		this.roleResourceIds = roleResourceIds;
	}

	public List<RoleResource> getRoleResoure() {
		return roleResoure;
	}

	public void setRoleResoure(List<RoleResource> roleResoure) {
		this.roleResoure = roleResoure;
	}

	public String getSearchResourceName() {
		return searchResourceName;
	}

	public void setSearchResourceName(String searchResourceName) {
		this.searchResourceName = searchResourceName;
	}

	public String getSearchDescription() {
		return searchDescription;
	}

	public void setSearchDescription(String searchDescription) {
		this.searchDescription = searchDescription;
	}

	public String getQueryRoleID() {
		return queryRoleID;
	}

	public void setQueryRoleID(String queryRoleID) {
		this.queryRoleID = queryRoleID;
	}

	public String getSearchRoleName() {
		return searchRoleName;
	}

	public void setSearchRoleName(String searchRoleName) {
		this.searchRoleName = searchRoleName;
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
