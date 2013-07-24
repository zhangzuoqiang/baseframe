package cn.hs.module.online.domain;

/**
 * Title: OnlineUserQB<br>
 * Description: 在线用户查询对象<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * 
 * @author ZhangKW
 * @createDate 2012-5-24
 * @version $Revision: 11 $
 */
public class OnlineUserQB {

	private String loginID; // 登录名

	private String userName; // 用户姓名

	private String userDeptName; // 用户所在部门名称

	private String loginTime; // 用户登录时间

	private String onlineDay; // 用户在线时间（天）

	private String onlineHour; // 用户在线时间（小时）

	private String onlineMin; // 用户在线时间（分钟）

	private String loginIP; // 登录IP

	/**
	 * @return the loginIP
	 */
	public String getLoginIP() {
		return loginIP;
	}

	/**
	 * @param loginIP
	 *            the loginIP to set
	 */
	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}

	/**
	 * @return the loginID
	 */
	public String getLoginID() {
		return loginID;
	}

	/**
	 * @param loginID
	 *            the loginID to set
	 */
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userDeptName
	 */
	public String getUserDeptName() {
		return userDeptName;
	}

	/**
	 * @param userDeptName
	 *            the userDeptName to set
	 */
	public void setUserDeptName(String userDeptName) {
		this.userDeptName = userDeptName;
	}

	/**
	 * @return the loginTime
	 */
	public String getLoginTime() {
		return loginTime;
	}

	/**
	 * @param loginTime
	 *            the loginTime to set
	 */
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	/**
	 * @return the onlineDay
	 */
	public String getOnlineDay() {
		return onlineDay;
	}

	/**
	 * @param onlineDay
	 *            the onlineDay to set
	 */
	public void setOnlineDay(String onlineDay) {
		this.onlineDay = onlineDay;
	}

	/**
	 * @return the onlineHour
	 */
	public String getOnlineHour() {
		return onlineHour;
	}

	/**
	 * @param onlineHour
	 *            the onlineHour to set
	 */
	public void setOnlineHour(String onlineHour) {
		this.onlineHour = onlineHour;
	}

	/**
	 * @return the onlineMin
	 */
	public String getOnlineMin() {
		return onlineMin;
	}

	/**
	 * @param onlineMin
	 *            the onlineMin to set
	 */
	public void setOnlineMin(String onlineMin) {
		this.onlineMin = onlineMin;
	}

}
