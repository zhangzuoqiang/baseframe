package cn.hs.module.role.domain.jsonbean;

import java.io.Serializable;

import cn.hs.core.basedao.base.querybean.BaseBean;

public class UserInfoJsonBean extends BaseBean implements Serializable {

	private String deptName; // 公司名称
	private String userName; // 姓名
	private String accountNum; // 账号
	private String phoneNum; // 电话
	private String activeState; // 活动状态
	private String userId; // 用户id

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getActiveState() {
		return activeState;
	}

	public void setActiveState(String activeState) {
		this.activeState = activeState;
	}

}
