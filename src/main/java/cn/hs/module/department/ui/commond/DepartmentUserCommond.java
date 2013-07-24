package cn.hs.module.department.ui.commond;

import cn.hs.core.page.commond.BasePageCommond;

/**
 * Title: DepartmentUserCommond<br>
 * Description: 部门用户Commond<br>
 * Company: GoldGov<br>
 * Copyright @ 2012 GoldGov .All rights reserved.<br>
 * @author Limk
 * @createDate 2012-08-13
 * @version $Revision$
 */
public class DepartmentUserCommond extends BasePageCommond {

	private static final long serialVersionUID = 1L;

	private String[] searchDeptUserIDs;
	/**
	 * 查询条件：部门人员关系编号
	 */
	private String searchDeptUserID;// 查询条件：主键

	private String searchIsEnabled;// 查询条件：启用/作废

	public String[] getSearchDeptUserIDs() {
		return searchDeptUserIDs;
	}

	public void setSearchDeptUserIDs(String[] searchDeptUserIDs) {
		this.searchDeptUserIDs = searchDeptUserIDs;
	}

	public String getSearchDeptUserID() {
		return searchDeptUserID;
	}

	public void setSearchDeptUserID(String searchDeptUserID) {
		this.searchDeptUserID = searchDeptUserID;
	}

	public String getSearchIsEnabled() {
		return searchIsEnabled;
	}

	public void setSearchIsEnabled(String searchIsEnabled) {
		this.searchIsEnabled = searchIsEnabled;
	}

}
