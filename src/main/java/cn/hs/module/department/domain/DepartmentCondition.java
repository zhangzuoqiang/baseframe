package cn.hs.module.department.domain;

import cn.hs.core.basedao.condition.BasePageCondition;

/**
 * Title: DepartmentCondition<br>
 * Description: 部门查询条件对象<br>
 * Company: goldgov<br>
 * Copyright @ 2012 goldgov .All rights reserved.<br>
 * @author Mill
 * @createDate 2012-08-01
 * @version $Revision$
 */
public class DepartmentCondition extends BasePageCondition {

	private String searchDeptCode; // 查询条件：部门编码

	/**
	 * 查询条件：部门主键数据
	 */
	private String[] searchDepIDs;

	/**
	 * 查询条件：启用/作废
	 */
	// private String searchIsEnabled;
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
	 * 查询条件：部门性质类别编码
	 */
	private String searchDepkindCode;

	/**
	 * 查询条件：树路径
	 */
	private String searchTreepath;

	/**
	 * 查询条件：父级ID
	 */
	private String searchParentID;

	/**
	 * 查询条件：统计归属部门
	 */
	private String searchReportDepID;

	/**
	 * 查询条件：活动状态
	 */
	private Integer searchActiveState;

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

	public String getSearchDepkindCode() {
		return searchDepkindCode;
	}

	public void setSearchDepkindCode(String searchDepkindCode) {
		this.searchDepkindCode = searchDepkindCode;
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

	public String getSearchReportDepID() {
		return searchReportDepID;
	}

	public void setSearchReportDepID(String searchReportDepID) {
		this.searchReportDepID = searchReportDepID;
	}

	public Integer getSearchActiveState() {
		return searchActiveState;
	}

	public void setSearchActiveState(Integer searchActiveState) {
		this.searchActiveState = searchActiveState;
	}

	public String getSearchDeptCode() {
		return searchDeptCode;
	}

	public void setSearchDeptCode(String searchDeptCode) {
		this.searchDeptCode = searchDeptCode;
	}

}
