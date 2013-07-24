package cn.hs.core.springext.security.casclient.domain.json;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * Title: User<br>
 * Description: 用户json实体<br>
 * Company: HsS<br>
 * Copyright @ 2013 HsS .All rights reserved.<br>
 * @author HuangS
 * @createDate Apr 30, 2013
 * @version $Revision:$
 */
public class CasUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userID;// 主键
	private String loginID;// 登录ID
	private String passWord;// 密码
	private String userName;// 用户姓名
	private String sexCode;// 性别编码
	private Date birthday;// 出生日期
	private String mobileTelephone;// 手机号码
	private String email;// 电子邮箱地址
	private String paperNum;// 证件号码
	private Integer activeState;// 活动状�态 1 - 启用 2 - 作废
	private Integer orderNum;// 排序序号
	private String guid;// 学员的GUID
	private String remark;// 备注
	private List<CasSystems> systemList; // 系统集合
	private List<CasDepartment> departmentList; // 用户所属部门集合

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSexCode() {
		return sexCode;
	}

	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getMobileTelephone() {
		return mobileTelephone;
	}

	public void setMobileTelephone(String mobileTelephone) {
		this.mobileTelephone = mobileTelephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPaperNum() {
		return paperNum;
	}

	public void setPaperNum(String paperNum) {
		this.paperNum = paperNum;
	}

	public Integer getActiveState() {
		return activeState;
	}

	public void setActiveState(Integer activeState) {
		this.activeState = activeState;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<CasSystems> getSystemList() {
		return systemList;
	}

	public void setSystemList(List<CasSystems> systemList) {
		this.systemList = systemList;
	}

	public List<CasDepartment> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<CasDepartment> departmentList) {
		this.departmentList = departmentList;
	}

}
