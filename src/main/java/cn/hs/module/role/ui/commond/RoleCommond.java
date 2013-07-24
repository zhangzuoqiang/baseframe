package cn.hs.module.role.ui.commond;

import cn.hs.core.page.commond.BasePageCommond;

/**
 * 
 * Title: RoleCommond<br>
 * Description: 角色Commond<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Sep 5, 2011
 * @version $Revision: 90 $
 */
public class RoleCommond extends BasePageCommond {

	/**
	 * 系列号
	 */
	private static final long serialVersionUID = -5613731068475543463L;

	private String searchRoleName;// 查询的角色名称
	private String queryRoleID;// 查询的角色ID
	private String searchRoleType;// 查询的角色类型
	private String searchActiveState;// 查询的角色状态
	private String[] roleIds;// 选择的角色ID

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

	public String getSearchActiveState() {
		return searchActiveState;
	}

	public void setSearchActiveState(String searchActiveState) {
		this.searchActiveState = searchActiveState;
	}

}
