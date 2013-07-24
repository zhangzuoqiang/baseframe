package cn.hs.module.department.ui.commond;

import cn.hs.core.page.commond.BasePageCommond;

/**
 * Title: DepartmentCommond<br>
 * Description: 部门Commond<br>
 * Company: goldgov<br>
 * Copyright @ 2012 goldgov .All rights reserved.<br>
 * @author Mill
 * @createDate 2012-08-01
 * @version $Revision$
 */
public class DepartmentCommond extends BasePageCommond {

	private static final long serialVersionUID = 1L;

	private String[] searchDepIDs;

	private String searchDepID;// 查询条件：主键

	private Integer searchActiveState;// 查询条件：启用/作废

	private String searchDeptCode;// 查询条件：部门编码

	public String getSearchDeptCode() {
		return searchDeptCode;
	}

	public void setSearchDeptCode(String searchDeptCode) {
		this.searchDeptCode = searchDeptCode;
	}

	public String[] getSearchDepIDs() {
		return searchDepIDs;
	}

	public void setSearchDepIDs(String[] searchDepIDs) {
		this.searchDepIDs = searchDepIDs;
	}

	public String getSearchDepID() {
		return searchDepID;
	}

	public void setSearchDepID(String searchDepID) {
		this.searchDepID = searchDepID;
	}

	public Integer getSearchActiveState() {
		return searchActiveState;
	}

	public void setSearchActiveState(Integer searchActiveState) {
		this.searchActiveState = searchActiveState;
	}

}
