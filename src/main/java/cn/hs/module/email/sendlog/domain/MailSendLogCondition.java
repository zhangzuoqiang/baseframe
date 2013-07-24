package cn.hs.module.email.sendlog.domain;

import cn.hs.core.basedao.condition.BasePageCondition;

/**
 * Title: MailSendLogCondition<br>
 * Description: 邮件发送日志查询条件对象<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author LiuHG
 * @createDate 2012-08-02
 * @version $Revision$
 */
public class MailSendLogCondition extends BasePageCondition {

	/**
	 * 查询条件：邮件发送日志主键数据
	 */
	private String[] searchLogIDs;

	/**
	 * 查询条件：启用/作废
	 */
	private String searchIsEnabled;

	/**
	 * 查询条件：日志ID
	 */
	private String searchLogID;

	/**
	 * 查询条件：发送时间开始日期
	 */
	private String searchSendDateStart;

	/**
	 * 查询条件：发送时间结束日期
	 */
	private String searchSendDateEnd;

	/**
	 * 查询条件：发送标题
	 */
	private String searchMailSubject;

	/**
	 * 查询条件：发送状态
	 */
	private String searchSendState;

	public String[] getSearchLogIDs() {
		return searchLogIDs;
	}

	public void setSearchLogIDs(String[] searchLogIDs) {
		this.searchLogIDs = searchLogIDs;
	}

	public String getSearchIsEnabled() {
		return searchIsEnabled;
	}

	public void setSearchIsEnabled(String searchIsEnabled) {
		this.searchIsEnabled = searchIsEnabled;
	}

	public String getSearchLogID() {
		return searchLogID;
	}

	public void setSearchLogID(String searchLogID) {
		this.searchLogID = searchLogID;
	}

	public String getSearchSendDateStart() {
		return searchSendDateStart;
	}

	public void setSearchSendDateStart(String searchSendDateStart) {
		this.searchSendDateStart = searchSendDateStart;
	}

	public String getSearchSendDateEnd() {
		return searchSendDateEnd;
	}

	public void setSearchSendDateEnd(String searchSendDateEnd) {
		this.searchSendDateEnd = searchSendDateEnd;
	}

	public String getSearchMailSubject() {
		return searchMailSubject;
	}

	public void setSearchMailSubject(String searchMailSubject) {
		this.searchMailSubject = searchMailSubject;
	}

	public String getSearchSendState() {
		return searchSendState;
	}

	public void setSearchSendState(String searchSendState) {
		this.searchSendState = searchSendState;
	}

}
