/*
 * $Log: ResourceCondition.java,v $
 * Revision 1.1  2012/05/23 09:27:54  guor
 * 初次提交
 *
 */
package cn.hs.module.modules.domain;

import cn.hs.core.basedao.condition.BasePageCondition;

/**
 * Title: ResourceCondition<br>
 * Description: 资源condition<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 23, 2011
 * @version $Revision: 61 $
 */
public class ResourceCondition extends BasePageCondition {
	/**
	 * 基础数据Id
	 */
	private String queryResourceId;
	/**
	 * 查询条件：活动状态
	 */
	private String searchActiveState;
	/**
	 * 菜单id集合
	 */
	private String resourceIds[];
	/**
	 * 查询条件：功能模块名称
	 */
	private String searchResourceName;

	private String queryResourceParentID;

	private String queryRoleID;// 角色选择资源时去掉已选资源
	private String roleIds[];// 根据角色集合查询菜单

	public String[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	public String getQueryRoleID() {
		return queryRoleID;
	}

	public void setQueryRoleID(String queryRoleID) {
		this.queryRoleID = queryRoleID;
	}

	public String getQueryResourceParentID() {
		return queryResourceParentID;
	}

	public void setQueryResourceParentID(String queryResourceParentID) {
		this.queryResourceParentID = queryResourceParentID;
	}

	public String getQueryResourceId() {
		return queryResourceId;
	}

	public void setQueryResourceId(String queryResourceId) {
		this.queryResourceId = queryResourceId;
	}

	public String getSearchActiveState() {
		return searchActiveState;
	}

	public void setSearchActiveState(String searchActiveState) {
		this.searchActiveState = searchActiveState;
	}

	public String[] getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String[] resourceIds) {
		this.resourceIds = resourceIds;
	}

	public String getSearchResourceName() {
		return searchResourceName;
	}

	public void setSearchResourceName(String searchResourceName) {
		this.searchResourceName = searchResourceName;
	}
}
