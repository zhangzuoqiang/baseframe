package cn.hs.module.department.ui.commond;

/**
 * Title: DepartmentValidCommond<br>
 * Description: 部门ValidCommond<br>
 * Company: goldgov<br>
 * Copyright @ 2012 goldgov .All rights reserved.<br>
 * @author Mill
 * @createDate 2012-08-01
 * @version $Revision$
 */
public class DepartmentValidCommond extends DepartmentCommond {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String deptID;// 主键
	private String deptName;// 单位名称
	private String deptCode;// 单位编码
	private Integer companyLevel;// 公司等级
	private String deptAddress;// 单位地址
	private String deptPostNum;// 单位邮编
	private String deptPhone;// 单位电话号码
	private String linkMan;// 联系人
	private Integer orderNum;// 排序序号
	private String treepath;// 树路径
	private String parentID;// 父级单位ID
	private Integer activeState;// 活动状态 1 - 启用 2 - 作废
	private String belongToRegion;// 所属区域（在数据字典中的编码）
	private String remark;// REMARK
	private String belongToRegionName;// 所属区域（在数据字典中的名称）

	public String getBelongToRegion() {
		return belongToRegion;
	}

	public void setBelongToRegion(String belongToRegion) {
		this.belongToRegion = belongToRegion;
	}

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
	 * 获取活动状态
	 */
	public Integer getActiveState() {
		return activeState;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	/**
	 * 设置活动状态
	 */
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

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getBelongToRegionName() {
		return belongToRegionName;
	}

	public void setBelongToRegionName(String belongToRegionName) {
		this.belongToRegionName = belongToRegionName;
	}
}
