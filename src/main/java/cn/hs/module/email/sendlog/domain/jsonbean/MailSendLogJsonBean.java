package cn.hs.module.email.sendlog.domain.jsonbean;

import java.io.Serializable;

/**
 * Title: MailSendLogJsonBean<br>
 * Description: 邮件发送日志Json传输对象<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author LiuHG
 * @createDate 2012-08-02
 * @version $Revision$
 */
public class MailSendLogJsonBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String logID;// 日志ID
	private String sendAddress;// 发送地址
	private String receiveAddress;// 接收地址
	private Long sendDate;// 发送时间
	private String mailSubject;// 发送标题
	private String mailContent;// 邮件内容
	private Integer sendState;// 发送状态

	/**
	 * 获取日志ID
	 */
	public String getLogID() {
		return logID;
	}

	/**
	 * 设置日志ID
	 */
	public void setLogID(String logID) {
		this.logID = logID;
	}

	/**
	 * 获取发送地址
	 */
	public String getSendAddress() {
		return sendAddress;
	}

	/**
	 * 设置发送地址
	 */
	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}

	/**
	 * 获取接收地址
	 */
	public String getReceiveAddress() {
		return receiveAddress;
	}

	/**
	 * 设置接收地址
	 */
	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	/**
	 * 获取发送时间
	 */
	public Long getSendDate() {
		return sendDate;
	}

	/**
	 * 设置发送时间
	 */
	public void setSendDate(Long sendDate) {
		this.sendDate = sendDate;
	}

	/**
	 * 获取发送标题
	 */
	public String getMailSubject() {
		return mailSubject;
	}

	/**
	 * 设置发送标题
	 */
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	/**
	 * 获取邮件内容
	 */
	public String getMailContent() {
		return mailContent;
	}

	/**
	 * 设置邮件内容
	 */
	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	/**
	 * 获取发送状态
	 */
	public Integer getSendState() {
		return sendState;
	}

	/**
	 * 设置发送状态
	 */
	public void setSendState(Integer sendState) {
		this.sendState = sendState;
	}

}
