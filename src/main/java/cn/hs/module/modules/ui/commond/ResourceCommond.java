package cn.hs.module.modules.ui.commond;

import cn.hs.core.page.commond.BasePageCommond;

/**
 * 
 * Title: ResourceCommond<br>
 * Description: 菜单维护commond<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author FanKY
 * @createDate Sep 13, 2011
 * @version $Revision: 90 $
 */
public class ResourceCommond extends BasePageCommond {
	/**
	 * 系列号
	 */
	private static final long serialVersionUID = 8055016940168725561L;
	/**
	 * 基础数据Id
	 */
	private String queryResourceId;
	/**
	 * 活动状态
	 */
	private String searchActiveState;
	/**
	 * 菜单id集合
	 */
	private String resourceIds[];
	/**
	 * 功能模块名称
	 */
	private String searchResourceName;
	private String queryRoleID;// 查询的角色ID
	private String roleResourceID;// 主键
	private String queryResourceParentID;

	public String getQueryResourceParentID() {
		return queryResourceParentID;
	}

	public void setQueryResourceParentID(String queryResourceParentID) {
		this.queryResourceParentID = queryResourceParentID;
	}

	public String getRoleResourceID() {
		return roleResourceID;
	}

	public void setRoleResourceID(String roleResourceID) {
		this.roleResourceID = roleResourceID;
	}

	public String getQueryRoleID() {
		return queryRoleID;
	}

	public void setQueryRoleID(String queryRoleID) {
		this.queryRoleID = queryRoleID;
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
