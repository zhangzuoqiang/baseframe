package cn.hs.module.department.domain;

import cn.hs.core.basedao.condition.BasePageCondition;

/**
 * Title: DepartmentUserCondition<br>
 * Description: 部门用户查询条件对象<br>
 * Company: GoldGov<br>
 * Copyright @ 2012 GoldGov .All rights reserved.<br>
 * @author Limk
 * @createDate 2012-08-13
 * @version $Revision$
 */
public class DepartmentUserCondition extends BasePageCondition {

	/**
	 * 查询条件：部门用户主键数据
	 */
	private String[] searchDeptUserIDs;

	/**
	 * 查询条件：启用/作废
	 */
	private String searchIsEnabled;

	/**
	 * 查询条件：部门人员关系编号
	 */
	private String searchDeptUserID;

	/**
	 * 查询条件：用户编号
	 */
	private String searchUserId;

	/**
	 * 查询条件：部门IDs
	 */
	private String[] searchDeptIDs;

	/**
	 * 查询条件：用户的活动状态
	 */
	private Integer searchUserActiveState;

	public Integer getSearchUserActiveState() {
		return searchUserActiveState;
	}

	public void setSearchUserActiveState(Integer searchUserActiveState) {
		this.searchUserActiveState = searchUserActiveState;
	}

	public String[] getSearchDeptUserIDs() {
		return searchDeptUserIDs;
	}

	public void setSearchDeptUserIDs(String[] searchDeptUserIDs) {
		this.searchDeptUserIDs = searchDeptUserIDs;
	}

	public String getSearchIsEnabled() {
		return searchIsEnabled;
	}

	public void setSearchIsEnabled(String searchIsEnabled) {
		this.searchIsEnabled = searchIsEnabled;
	}

	public String getSearchDeptUserID() {
		return searchDeptUserID;
	}

	public void setSearchDeptUserID(String searchDeptUserID) {
		this.searchDeptUserID = searchDeptUserID;
	}

	public String getSearchUserId() {
		return searchUserId;
	}

	public void setSearchUserId(String searchUserId) {
		this.searchUserId = searchUserId;
	}

	public String[] getSearchDeptIDs() {
		return searchDeptIDs;
	}

	public void setSearchDeptIDs(String[] searchDeptIDs) {
		this.searchDeptIDs = searchDeptIDs;
	}

}
