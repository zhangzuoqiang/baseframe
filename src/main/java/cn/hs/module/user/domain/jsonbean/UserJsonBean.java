package cn.hs.module.user.domain.jsonbean;

import cn.hs.core.basedao.base.querybean.BaseBean;

/**
 * Title: BaseUserJsonBean<br>
 * Description: BaseUserServiceJson传输对象<br>
 * Company: GoldGov<br>
 * Copyright @ 2012 GoldGov .All rights reserved.<br>
 * @author Limk
 * @createDate 2012-08-01
 * @version $Revision$
 */
public class UserJsonBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private String userId;// 用户编号
	private String loginID;// 登录ID
	private String password;// 密码
	private String userName;// 用户名
	private String sexCode;// 性别
	private Long birthday;// 出生日期
	private String phone;// 固定电话
	private String mobileTelephone;// 手机
	private String email;// 电子邮箱
	private String paperTypeCode;// 证件类型
	private String paperNum;// 证件号码
	private Integer activeState;// 活动状态
	private Integer orderNum;// 排序序号
	private String roleNames;// 用户角色名称列表
	private String roleIds;// 用户角色ids
	private String remark;// 备注
	private String deptID;// 公司Id

	private String deptName;// 公司名称

	public String getDeptID() {
		return deptID;
	}

	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	/**
	 * 获取用户编号
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 设置用户编号
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 获取登录ID
	 */
	public String getLoginID() {
		return loginID;
	}

	/**
	 * 设置登录ID
	 */
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	/**
	 * 获取密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取用户名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 获取性别
	 */
	public String getSexCode() {
		return sexCode;
	}

	/**
	 * 设置性别
	 */
	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}

	/**
	 * 获取出生日期
	 */
	public Long getBirthday() {
		return birthday;
	}

	/**
	 * 设置出生日期
	 */
	public void setBirthday(Long birthday) {
		this.birthday = birthday;
	}

	/**
	 * 获取固定电话
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置固定电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取手机
	 */
	public String getMobileTelephone() {
		return mobileTelephone;
	}

	/**
	 * 设置手机
	 */
	public void setMobileTelephone(String mobileTelephone) {
		this.mobileTelephone = mobileTelephone;
	}

	/**
	 * 获取电子邮箱
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置电子邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取证件类型
	 */
	public String getPaperTypeCode() {
		return paperTypeCode;
	}

	/**
	 * 设置证件类型
	 */
	public void setPaperTypeCode(String paperTypeCode) {
		this.paperTypeCode = paperTypeCode;
	}

	/**
	 * 获取证件号码
	 */
	public String getPaperNum() {
		return paperNum;
	}

	/**
	 * 设置证件号码
	 */
	public void setPaperNum(String paperNum) {
		this.paperNum = paperNum;
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
	 * 获取备注
	 * 
	 * @return 备注信息
	 * @author Mill
	 * @createDate 2012-9-15
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置备注
	 * 
	 * @param remark
	 * @author Mill
	 * @createDate 2012-9-15
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}
