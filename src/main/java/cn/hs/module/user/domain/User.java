package cn.hs.module.user.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import cn.hs.commons.DateUtil;

/**
 * Title: User<br>
 * Description:用户域对象 <br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author XiangBin
 * @createDate Jul 31, 2012
 * @version $Revision:$
 */
@Entity
@Table(name = "BASE_USER")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 活动状态，启用
	 */
	public final static Integer IS_ACTIVE_Y = new Integer(1);
	/**
	 * 活动状态，作废
	 */
	public final static Integer IS_ACTIVE_N = new Integer(2);

	private String userID;// 主键
	private String loginID;// 登录ID
	private String passWord;// 密码
	private String userName;// 用户姓名
	private String sexCode;// 性别编码
	private Long birthday;// 出生日期
	private String phone; // 固定电话
	private String mobileTelephone;// 手机号码
	private String email;// 电子邮箱地址
	private String paperTypeCode;// 证件类型编码
	private String paperNum;// 证件号码
	private Integer activeState;// 活动状态 1 - 启用 2 - 作废
	private Integer orderNum;// 排序序号
	private String remark;// 备注
	private Long createTime; // 创建时间
	private Long lastUpdateTime; // 最后修改时间

	@Id
	@Column(name = "USER_ID")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	@Column(name = "LOGIN_ID")
	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	@Column(name = "PASSWORD")
	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "SEX_CODE")
	public String getSexCode() {
		return sexCode;
	}

	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}

	@Column(name = "BIRTHDAY")
	public Long getBirthday() {
		return birthday;
	}

	public void setBirthday(Long birthday) {
		this.birthday = birthday;
	}

	@Column(name = "PHONE")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "MOBILE_PHONE")
	public String getMobileTelephone() {
		return mobileTelephone;
	}

	public void setMobileTelephone(String mobileTelephone) {
		this.mobileTelephone = mobileTelephone;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "PAPER_NUM")
	public String getPaperNum() {
		return paperNum;
	}

	public void setPaperNum(String paperNum) {
		this.paperNum = paperNum;
	}

	@Column(name = "ACTIVE_STATE")
	public Integer getActiveState() {
		return activeState;
	}

	public void setActiveState(Integer activeState) {
		this.activeState = activeState;
	}

	@Column(name = "ORDER_NUM")
	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "CREATE_TIME")
	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Column(name = "LAST_UPDATE_TIME")
	public Long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	@Column(name = "PAPER_TYPE_CODE")
	public String getPaperTypeCode() {
		return paperTypeCode;
	}

	public void setPaperTypeCode(String paperTypeCode) {
		this.paperTypeCode = paperTypeCode;
	}

	/**
	 * 记录日志用
	 * 
	 * @return
	 * @author HuangS
	 * @date Apr 30, 2013
	 */
	@Override
	public String toString() {
		StringBuffer stb = new StringBuffer();
		stb.append("主键======>");
		stb.append(getUserID());
		stb.append("\n人员姓名======>");
		stb.append(getUserName());
		stb.append("\n账号======>");
		stb.append(getLoginID());
		stb.append("\n密码======>");
		stb.append(getPassWord());
		stb.append("\n出生日期======>");
		stb.append(DateUtil.getDateStringNoTime(getBirthday()));
		stb.append("\n性别======>");
		stb.append(getSexCode());
		stb.append("\n电子邮件======>");
		stb.append(getEmail());
		stb.append("\n手机号码======>");
		stb.append(getMobileTelephone());
		stb.append("\n证件类型======>");
		stb.append(getPaperTypeCode());
		stb.append("\n证件号码======>");
		stb.append(getPaperNum());
		stb.append("\n活动状态======>");
		stb.append(getActiveState());
		stb.append("\n排序编号======>");
		stb.append(getOrderNum());
		stb.append("\n固定电话======>");
		stb.append(getPhone());
		stb.append("\n备注======>");
		stb.append(getRemark());
		return stb.toString();
	}

}
