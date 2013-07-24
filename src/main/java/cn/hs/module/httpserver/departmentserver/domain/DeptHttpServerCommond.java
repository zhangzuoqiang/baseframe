package cn.hs.module.httpserver.departmentserver.domain;

import cn.hs.module.httpserver.HttpServerCommond;

/**
 * 
 * Title: DeptHttpServerCommond<br>
 * Description: 部门httpserverCommond实体<br>
 * Company: HsS<br>
 * Copyright @ 2013 HsS .All rights reserved.<br>
 * @author HuangS
 * @createDate May 19, 2013
 * @version $Revision:$
 */
public class DeptHttpServerCommond extends HttpServerCommond {

	private String deptStr;

	private Integer searchActiveState;

	private String[] searchDeptIDs;

	public String getDeptStr() {
		return deptStr;
	}

	public void setDeptStr(String deptStr) {
		this.deptStr = deptStr;
	}

	public void setSearchActiveState(Integer searchActiveState) {
		this.searchActiveState = searchActiveState;
	}

	public Integer getSearchActiveState() {
		return searchActiveState;
	}

	public String[] getSearchDeptIDs() {
		return searchDeptIDs;
	}

	public void setSearchDeptIDs(String[] searchDeptIDs) {
		this.searchDeptIDs = searchDeptIDs;
	}

}
