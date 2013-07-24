package cn.hs.module.department.domain;

import cn.hs.core.basedao.condition.BaseTreeCondition;

/**
 * 
 * Title: DepartmentTreeCondition<br>
 * Description:部门查询类 <br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author Mill
 * @createDate 2012-8-2
 * @version $Revision:$
 */
public class DepartmentTreeCondition extends BaseTreeCondition {

	/**
	 * 查询条件：部门主键数据
	 */
	private String[] searchDepIDs;

	/**
	 * 查询条件：部门编号
	 */
	private String searchDepID;

	/**
	 * 查询条件：部门名称
	 */
	private String searchDepName;

	/**
	 * 查询条件：类型
	 */
	private String searchType;

	/**
	 * 查询条件：地址
	 */
	private String searchDeptAddress;

	/**
	 * 查询条件：邮编
	 */
	private String searchDeptPostNum;

	/**
	 * 查询条件：电话
	 */
	private String searchDeptPhone;

	/**
	 * 查询条件：排序序号
	 */
	private String searchOrderNum;

	/**
	 * 查询条件：部门编码
	 */
	private String searchDeptCode;

	/**
	 * 查询条件：树路径
	 */
	private String searchTreepath;

	/**
	 * 查询条件：父级ID
	 */
	private String searchParentID;

	/**
	 * 查询条件：活动状态
	 */
	private String searchActiveState;

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

	public String getSearchDepName() {
		return searchDepName;
	}

	public void setSearchDepName(String searchDepName) {
		this.searchDepName = searchDepName;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchDeptAddress() {
		return searchDeptAddress;
	}

	public void setSearchDeptAddress(String searchDeptAddress) {
		this.searchDeptAddress = searchDeptAddress;
	}

	public String getSearchDeptPostNum() {
		return searchDeptPostNum;
	}

	public void setSearchDeptPostNum(String searchDeptPostNum) {
		this.searchDeptPostNum = searchDeptPostNum;
	}

	public String getSearchDeptPhone() {
		return searchDeptPhone;
	}

	public void setSearchDeptPhone(String searchDeptPhone) {
		this.searchDeptPhone = searchDeptPhone;
	}

	public String getSearchOrderNum() {
		return searchOrderNum;
	}

	public void setSearchOrderNum(String searchOrderNum) {
		this.searchOrderNum = searchOrderNum;
	}

	public String getSearchDeptCode() {
		return searchDeptCode;
	}

	public void setSearchDeptCode(String searchDeptCode) {
		this.searchDeptCode = searchDeptCode;
	}

	public String getSearchTreepath() {
		return searchTreepath;
	}

	public void setSearchTreepath(String searchTreepath) {
		this.searchTreepath = searchTreepath;
	}

	public String getSearchParentID() {
		return searchParentID;
	}

	public void setSearchParentID(String searchParentID) {
		this.searchParentID = searchParentID;
	}

	public String getSearchActiveState() {
		return searchActiveState;
	}

	public void setSearchActiveState(String searchActiveState) {
		this.searchActiveState = searchActiveState;
	}

}