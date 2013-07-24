package cn.hs.module.department.domain.jsonbean;

import java.io.Serializable;

/**
 * Title: DepartmentJsonBean<br>
 * Description: 部门Json传输对象<br>
 * Company: goldgov<br>
 * Copyright @ 2012 goldgov .All rights reserved.<br>
 * @author Mill
 * @createDate 2012-08-01
 * @version $Revision$
 */
public class DepartmentJsonBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String deptID;// 部门编号
	private String deptName;// 部门名称
	private String deptCode;// 单位编码
	private Integer type;// 类型
	private String deptAddress;// 地址
	private String deptPostNum;// 邮编
	private String deptPhone;// 电话
	private Integer orderNum;// 排序序号
	private String deptkindCode;// 部门性质类别编码
	private String treepath;// 树路径
	private String parentID;// 父级ID
	private String reportDepID;// 统计归属部门
	private Integer activeState;// 活动状态

	/**
	 * 获取部门编号
	 */
	public String getDeptID() {
		return deptID;
	}

	/**
	 * 设置部门编号
	 */
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	/**
	 * 获取部门名称
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * 设置部门名称
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * 获取类型
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 设置类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 获取地址
	 */
	public String getDeptAddress() {
		return deptAddress;
	}

	/**
	 * 设置地址
	 */
	public void setDeptAddress(String deptAddress) {
		this.deptAddress = deptAddress;
	}

	/**
	 * 获取邮编
	 */
	public String getDeptPostNum() {
		return deptPostNum;
	}

	/**
	 * 设置邮编
	 */
	public void setDeptPostNum(String deptPostNum) {
		this.deptPostNum = deptPostNum;
	}

	/**
	 * 获取电话
	 */
	public String getDeptPhone() {
		return deptPhone;
	}

	/**
	 * 设置电话
	 */
	public void setDeptPhone(String deptPhone) {
		this.deptPhone = deptPhone;
	}

	/**
	 * 获取排序序号
	 */
	public Integer getOrderNum() {
		return orderNum;
	}

	/**
	 * 设置排序序号
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * 获取部门性质类别编码
	 */
	public String getDeptkindCode() {
		return deptkindCode;
	}

	/**
	 * 设置部门性质类别编码
	 */
	public void setDeptkindCode(String deptkindCode) {
		this.deptkindCode = deptkindCode;
	}

	/**
	 * 获取树路径
	 */
	public String getTreepath() {
		return treepath;
	}

	/**
	 * 设置树路径
	 */
	public void setTreepath(String treepath) {
		this.treepath = treepath;
	}

	/**
	 * 获取父级ID
	 */
	public String getParentID() {
		return parentID;
	}

	/**
	 * 设置父级ID
	 */
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	/**
	 * 获取统计归属部门
	 */
	public String getReportDepID() {
		return reportDepID;
	}

	/**
	 * 设置统计归属部门
	 */
	public void setReportDepID(String reportDepID) {
		this.reportDepID = reportDepID;
	}

	/**
	 * 获取活动状态
	 */
	public Integer getActiveState() {
		return activeState;
	}

	/**
	 * 设置活动状态
	 */
	public void setActiveState(Integer activeState) {
		this.activeState = activeState;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

}
