package cn.hs.core.springext.security.strategy.sample.bo;

import java.util.Date;

/**
 * Title: SampleUser<br>
 * Description:样例用户<br>
 * Company: EORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * 
 * @author ZhangKW
 * @createDate 2011-5-26
 * @version $Revision: 1.1 $
 */
public class SampleUser {

	public static final Integer ACTIVE_YES = new Integer(1); // 1:有效
	public static final Integer ACTIVE_NO = new Integer(2); // 2:无效

	private String id; // 主键

	private String userName; // 姓名

	private String loginName; // 登录名

	private String password; // 密码

	private Date birthday; // 生日

	private Integer active; // 状态

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @param loginName
	 *            the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the active
	 */
	public Integer getActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(Integer active) {
		this.active = active;
	}

}
