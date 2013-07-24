package cn.hs.module.department.domain.jsonbean;

import java.io.Serializable;

/**
 * Title: DepartmentUserJsonBean<br>
 * Description: 部门用户Json传输对象<br>
 * Company: GoldGov<br>
 * Copyright @ 2012 GoldGov .All rights reserved.<br>
 * @author Limk
 * @createDate 2012-08-13
 * @version $Revision$
 */
public class DepartmentUserJsonBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String deptUserID;// 部门人员关系编号
	private String department;// 部门编号
	private String oldDepartment;// 原部门编号
	private Integer activeState;// 活动状态
	private Integer orderNum;// 排序序号
	private String user;// 用户编号

	/**
	 * 获取部门人员关系编号
	 */
	public String getDeptUserID() {
		return deptUserID;
	}

	/**
	 * 设置部门人员关系编号
	 */
	public void setDeptUserID(String deptUserID) {
		this.deptUserID = deptUserID;
	}

	/**
	 * 获取部门编号
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * 设置部门编号
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * 获取原部门编号
	 */
	public String getOldDepartment() {
		return oldDepartment;
	}

	/**
	 * 设置原部门编号
	 */
	public void setOldDepartment(String oldDepartment) {
		this.oldDepartment = oldDepartment;
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
	 * 获取用户编号
	 */
	public String getUser() {
		return user;
	}

	/**
	 * 设置用户编号
	 */
	public void setUser(String user) {
		this.user = user;
	}

}
