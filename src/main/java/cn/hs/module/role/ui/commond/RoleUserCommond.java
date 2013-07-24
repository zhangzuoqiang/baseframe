package cn.hs.module.role.ui.commond;

import cn.hs.core.page.commond.BasePageCommond;

/**
 * 
 * Title: RoleCommond<br>
 * Description: 角色用户Commond<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author XiangB
 * @createDate Sep 5, 2011
 * @version $Revision: 90 $
 */
public class RoleUserCommond extends BasePageCommond {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -6090498197097651259L;

	private String searchRoleName;// 查询的角色名称
	private String searchRoleId;// 查询的角色ID
	private String[] searchRoleIds;// 选择的角色ID
	private String searchUserId;// 选择的用户
	private Integer activeStatus;// 活动状态 1、启用 2、作废
	private String[] userIds; // 用户id数组

	public String getSearchRoleName() {
		return searchRoleName;
	}

	public void setSearchRoleName(String searchRoleName) {
		this.searchRoleName = searchRoleName;
	}

	public String getSearchRoleId() {
		return searchRoleId;
	}

	public void setSearchRoleId(String searchRoleId) {
		this.searchRoleId = searchRoleId;
	}

	public String[] getSearchRoleIds() {
		return searchRoleIds;
	}

	public void setSearchRoleIds(String[] searchRoleIds) {
		this.searchRoleIds = searchRoleIds;
	}

	public String getSearchUserId() {
		return searchUserId;
	}

	public void setSearchUserId(String searchUserId) {
		this.searchUserId = searchUserId;
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
