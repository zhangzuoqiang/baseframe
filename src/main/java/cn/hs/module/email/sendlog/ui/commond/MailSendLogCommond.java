package cn.hs.module.email.sendlog.ui.commond;

import cn.hs.core.page.commond.BasePageCommond;

/**
 * Title: MailSendLogCommond<br>
 * Description: 邮件发送日志Commond<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author LiuHG
 * @createDate 2012-08-02
 * @version $Revision$
 */
public class MailSendLogCommond extends BasePageCommond {

	private static final long serialVersionUID = 1L;

	private String[] searchLogIDs;

	private String searchLogID;// 查询条件：主键

	private String searchIsEnabled;// 查询条件：启用/作废

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

	public String getSearchLogID() {
		return searchLogID;
	}

	public void setSearchLogID(String searchLogID) {
		this.searchLogID = searchLogID;
	}

	public String getSearchIsEnabled() {
		return searchIsEnabled;
	}

	public void setSearchIsEnabled(String searchIsEnabled) {
		this.searchIsEnabled = searchIsEnabled;
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
