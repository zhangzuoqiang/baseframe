package cn.hs.module.user.ui.commond;

import java.util.Date;

/**
 * Title: BaseUserValidCommond<br>
 * Description: BaseUserServiceValidCommond<br>
 * Company: GoldGov<br>
 * Copyright @ 2012 GoldGov .All rights reserved.<br>
 * @author Limk
 * @createDate 2012-08-01
 * @version $Revision$
 */
public class UserInfoValidCommond extends UserInfoCommond {

	private static final long serialVersionUID = -5509998398046067210L;

	private String userID;// 用户编号
	private String loginID;// 登录ID
	private String passWord;// 密码
	private String userName;// 用户名
	private String sexCode;// 性别
	private Date birthday;// 出生日期
	private String phone;// 固定电话
	private String mobileTelephone;// 手机
	private String email;// 电子邮箱
	private String folkCode;// 民族编号
	private String paperTypeCode;// 证件类型
	private String paperNum;// 证件号码
	private String politicesCode;// 政治面貌编码
	private String degreeCode;// 学历编码
	private String dutyLevelCode;// 职级编码
	private Integer hierarchy;// 级别分类
	private String dutyRemark;// 职务备注
	private Integer zuGong;// 组工干部
	private Integer examState;// 考核状态
	private Integer isBureauReserve;// 局级后备
	private String currentDutyTime;// 任现职时间
	private Integer shiGuan;// 市管干部
	private Integer activeState;// 活动状态
	private Integer orderNum;// 排序序号
	private Integer availabilityState;// 报名状态
	private Long officeholdingDate;// 任职时间
	private Integer dangWu;// 党务干部
	private Integer tongZhan;// 统战干部
	private Integer viewState;// 显示状态
	private String guid;// GUID
	private String remark;// 备注

	private String departmentId;

	/**
	 * 获取用户编号
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * 设置用户编号
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * 获取密码
	 */
	public String getPassWord() {
		return passWord;
	}

	/**
	 * 设置密码
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
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
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * 设置出生日期
	 */
	public void setBirthday(Date birthday) {
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
	 * 获取民族编号
	 */
	public String getFolkCode() {
		return folkCode;
	}

	/**
	 * 设置民族编号
	 */
	public void setFolkCode(String folkCode) {
		this.folkCode = folkCode;
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
	 * 获取政治面貌编码
	 */
	public String getPoliticesCode() {
		return politicesCode;
	}

	/**
	 * 设置政治面貌编码
	 */
	public void setPoliticesCode(String politicesCode) {
		this.politicesCode = politicesCode;
	}

	/**
	 * 获取学历编码
	 */
	public String getDegreeCode() {
		return degreeCode;
	}

	/**
	 * 设置学历编码
	 */
	public void setDegreeCode(String degreeCode) {
		this.degreeCode = degreeCode;
	}

	/**
	 * 获取职级编码
	 */
	public String getDutyLevelCode() {
		return dutyLevelCode;
	}

	/**
	 * 设置职级编码
	 */
	public void setDutyLevelCode(String dutyLevelCode) {
		this.dutyLevelCode = dutyLevelCode;
	}

	/**
	 * 获取级别分类
	 */
	public Integer getHierarchy() {
		return hierarchy;
	}

	/**
	 * 设置级别分类
	 */
	public void setHierarchy(Integer hierarchy) {
		this.hierarchy = hierarchy;
	}

	/**
	 * 获取职务备注
	 */
	public String getDutyRemark() {
		return dutyRemark;
	}

	/**
	 * 设置职务备注
	 */
	public void setDutyRemark(String dutyRemark) {
		this.dutyRemark = dutyRemark;
	}

	/**
	 * 获取组工干部
	 */
	public Integer getZuGong() {
		return zuGong;
	}

	/**
	 * 设置组工干部
	 */
	public void setZuGong(Integer zuGong) {
		this.zuGong = zuGong;
	}

	/**
	 * 获取考核状态
	 */
	public Integer getExamState() {
		return examState;
	}

	/**
	 * 设置考核状态
	 */
	public void setExamState(Integer examState) {
		this.examState = examState;
	}

	/**
	 * 获取局级后备
	 */
	public Integer getIsBureauReserve() {
		return isBureauReserve;
	}

	/**
	 * 设置局级后备
	 */
	public void setIsBureauReserve(Integer isBureauReserve) {
		this.isBureauReserve = isBureauReserve;
	}

	/**
	 * 获取任现职时间
	 */
	public String getCurrentDutyTime() {
		return currentDutyTime;
	}

	/**
	 * 设置任现职时间
	 */
	public void setCurrentDutyTime(String currentDutyTime) {
		this.currentDutyTime = currentDutyTime;
	}

	/**
	 * 获取市管干部
	 */
	public Integer getShiGuan() {
		return shiGuan;
	}

	/**
	 * 设置市管干部
	 */
	public void setShiGuan(Integer shiGuan) {
		this.shiGuan = shiGuan;
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
	 * 获取报名状态
	 */
	public Integer getAvailabilityState() {
		return availabilityState;
	}

	/**
	 * 设置报名状态
	 */
	public void setAvailabilityState(Integer availabilityState) {
		this.availabilityState = availabilityState;
	}

	/**
	 * 获取任职时间
	 */
	public Long getOfficeholdingDate() {
		return officeholdingDate;
	}

	/**
	 * 设置任职时间
	 */
	public void setOfficeholdingDate(Long officeholdingDate) {
		this.officeholdingDate = officeholdingDate;
	}

	/**
	 * 获取党务干部
	 */
	public Integer getDangWu() {
		return dangWu;
	}

	/**
	 * 设置党务干部
	 */
	public void setDangWu(Integer dangWu) {
		this.dangWu = dangWu;
	}

	/**
	 * 获取统战干部
	 */
	public Integer getTongZhan() {
		return tongZhan;
	}

	/**
	 * 设置统战干部
	 */
	public void setTongZhan(Integer tongZhan) {
		this.tongZhan = tongZhan;
	}

	/**
	 * 获取显示状态
	 */
	public Integer getViewState() {
		return viewState;
	}

	/**
	 * 设置显示状态
	 */
	public void setViewState(Integer viewState) {
		this.viewState = viewState;
	}

	/**
	 * 获取GUID
	 */
	public String getGuid() {
		return guid;
	}

	/**
	 * 设置GUID
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}

	/**
	 * 设置备注
	 * 
	 * @return 备注信息
	 * @author Mill
	 * @createDate 2012-9-15
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 获取备注
	 * 
	 * @param remark
	 * @author Mill
	 * @createDate 2012-9-15
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
}
