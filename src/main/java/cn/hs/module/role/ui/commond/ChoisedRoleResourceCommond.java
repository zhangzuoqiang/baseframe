package cn.hs.module.role.ui.commond;

import cn.hs.core.page.commond.BasePageCommond;
import cn.hs.module.basedata.domain.BaseData;

/**
 * 角色资源Commond Title: ChoisedRoleResourceCommond<br>
 * Description: <br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author YaoSC
 * @createDate Sep 5, 2011
 * @version $Revision: 90 $
 */
public class ChoisedRoleResourceCommond extends BasePageCommond {

	/**
	 * 系列号
	 */
	private static final long serialVersionUID = -4306910376813718701L;

	private String searchRoleName;// 查询的角色名称
	private String queryRoleID;// 查询的角色ID
	private String searchRoleType;// 查询的角色类型
	private String searchActiveState;// 查询的角色状态
	private BaseData roleType;// 角色类型
	private String[] roleIds;// 选择的角色ID
	private String resourceName;// 资源名称
	private String description;// 资源描述
	private Integer activeState;// 活动状态 1 - 启用 2 - 作废
	private String roleResourceID;// 主键
	private String[] roleResourceIds;
	private String url;// 访问路径
	private String queryResourceId;
	private String parentID;
	private String resourceIds[];

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public String[] getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String[] resourceIds) {
		this.resourceIds = resourceIds;
	}

	public String getQueryResourceId() {
		return queryResourceId;
	}

	public void setQueryResourceId(String queryResourceId) {
		this.queryResourceId = queryResourceId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String[] getRoleResourceIds() {
		return roleResourceIds;
	}

	public void setRoleResourceIds(String[] roleResourceIds) {
		this.roleResourceIds = roleResourceIds;
	}

	public String getRoleResourceID() {
		return roleResourceID;
	}

	public void setRoleResourceID(String roleResourceID) {
		this.roleResourceID = roleResourceID;
	}

	public Integer getActiveState() {
		return activeState;
	}

	public void setActiveState(Integer activeState) {
		this.activeState = activeState;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

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

	public BaseData getRoleType() {
		return roleType;
	}

	public void setRoleType(BaseData roleType) {
		this.roleType = roleType;
	}

}
