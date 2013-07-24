package cn.hs.module.department.ui.commond;

import cn.hs.core.servicetemplate.treetemplate.commond.BaseTreeCommond;

/**
 * 
 * Title: DepartmentTreeCommond<br>
 * Description: 部门树commond<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author Mill
 * @createDate 2012-8-2
 * @version $Revision:$
 */
public class DepartmentTreeCommond extends BaseTreeCommond {

	private String parentID;
	/**
	 * 项目管理查询条件：默认选中与参加成员公司
	 */
	private String searchProjectID;

	/**
	 * 岗位管理查询条件：某个项目所参加的公司
	 */
	private String projectId;

	/**
	 * 功能编码（功能URL）
	 */
	private String functionCode;

	public String getSearchProjectID() {
		return searchProjectID;
	}

	public void setSearchProjectID(String searchProjectID) {
		this.searchProjectID = searchProjectID;
	}

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	/**
	 * 获取 功能编码（功能URL）。
	 * 
	 * @return
	 */
	public String getFunctionCode() {
		return functionCode;
	}

	/**
	 * 设置 功能编码（功能URL）。
	 * 
	 * @param functionCode
	 */
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}
}
