package cn.hs.module.department.ui.commond;

import cn.hs.core.servicetemplate.treetemplate.commond.BaseTreeCommond;

/**
 * 
 * Title: DepartmentTreePCommond<br>
 * Description: 部门树commond<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author Mill
 * @createDate 2012-8-3
 * @version $Revision:$
 */
public class DepartmentTreePCommond extends BaseTreeCommond {

	private String depID;// 部门编号
	private String depName;// 部门名称
	private String deptAddress;// 地址
	private String deptPostNum;// 邮编
	private String deptPhone;// 电话
	private Integer orderNum;// 排序序号
	private String depkindCode;// 部门性质类别编码
	private String treepath;// 树路径
	private String parentID;// 父级ID
	private String reportDepID;// 统计归属部门
	private Integer activeState;// 活动状态

	private Integer companyLevel;// //公司等级
	private String remark;// REMARK

	public String getDepID() {
		return depID;
	}

	public void setDepID(String depID) {
		this.depID = depID;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getDeptAddress() {
		return deptAddress;
	}

	public void setDeptAddress(String deptAddress) {
		this.deptAddress = deptAddress;
	}

	public String getDeptPostNum() {
		return deptPostNum;
	}

	public void setDeptPostNum(String deptPostNum) {
		this.deptPostNum = deptPostNum;
	}

	public String getDeptPhone() {
		return deptPhone;
	}

	public void setDeptPhone(String deptPhone) {
		this.deptPhone = deptPhone;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getDepkindCode() {
		return depkindCode;
	}

	public void setDepkindCode(String depkindCode) {
		this.depkindCode = depkindCode;
	}

	public String getTreepath() {
		return treepath;
	}

	public void setTreepath(String treepath) {
		this.treepath = treepath;
	}

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public String getReportDepID() {
		return reportDepID;
	}

	public void setReportDepID(String reportDepID) {
		this.reportDepID = reportDepID;
	}

	public Integer getActiveState() {
		return activeState;
	}

	public void setActiveState(Integer activeState) {
		this.activeState = activeState;
	}

	public Integer getCompanyLevel() {
		return companyLevel;
	}

	public void setCompanyLevel(Integer companyLevel) {
		this.companyLevel = companyLevel;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
