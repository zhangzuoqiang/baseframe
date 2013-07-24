package cn.hs.module.email.sendlog.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import cn.hs.commons.DateUtil;

/**
 * Title: MailSendLog<br>
 * Description: 邮件发送日志实体对象<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author LiuHG
 * @createDate 2012-08-02
 * @version $Revision$
 */
@Entity
@Table(name = "BASE_MAIL_SEND_LOG")
public class MailSendLog implements Serializable {

	private static final long serialVersionUID = 1L;

	private String logID;// 日志ID
	private String sendAddress;// 发送地址
	private String receiveAddress;// 接收地址
	private Long sendDate;// 发送时间
	private String mailSubject;// 发送标题
	private String mailContent;// 邮件内容
	private Integer sendState;// 发送状态

	/**
	 * 设置日志ID
	 */
	@Id
	@Column(name = "LOG_ID")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	public String getLogID() {
		return logID;
	}

	/**
	 * 获取日志ID
	 */
	public void setLogID(String logID) {
		this.logID = logID;
	}

	@Column(name = "SEND_ADDRESS")
	/**
	 * 设置发送地址
	 */
	public String getSendAddress() {
		return sendAddress;
	}

	/**
	 * 获取发送地址
	 */
	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}

	@Column(name = "RECEIVE_ADDRESS")
	/**
	 * 设置接收地址
	 */
	public String getReceiveAddress() {
		return receiveAddress;
	}

	/**
	 * 获取接收地址
	 */
	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	@Column(name = "SEND_DATE")
	/**
	 * 设置发送时间
	 */
	public Long getSendDate() {
		return sendDate;
	}

	/**
	 * 获取发送时间
	 */
	public void setSendDate(Long sendDate) {
		this.sendDate = sendDate;
	}

	@Column(name = "MAIL_SUBJECT")
	/**
	 * 设置发送标题
	 */
	public String getMailSubject() {
		return mailSubject;
	}

	/**
	 * 获取发送标题
	 */
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	@Column(name = "MAIL_CONTENT")
	/**
	 * 设置邮件内容
	 */
	public String getMailContent() {
		return mailContent;
	}

	/**
	 * 获取邮件内容
	 */
	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	@Column(name = "SEND_STATE")
	/**
	 * 设置发送状态
	 */
	public Integer getSendState() {
		return sendState;
	}

	/**
	 * 获取发送状态
	 */
	public void setSendState(Integer sendState) {
		this.sendState = sendState;
	}

	/**
	 * 记录日志使用
	 */
	public String toString() {
		StringBuffer stb = new StringBuffer();
		stb.append("日志ID======>");
		stb.append(getLogID());
		stb.append("\n发送地址======>");
		stb.append(getSendAddress());
		stb.append("\n接收地址======>");
		stb.append(getReceiveAddress());
		stb.append("\n发送时间======>");
		stb.append(DateUtil.getDateString(getSendDate()));
		stb.append("\n发送标题======>");
		stb.append(getMailSubject());
		stb.append("\n邮件内容======>");
		stb.append(getMailContent());
		stb.append("\n发送状态======>");
		stb.append(getSendState());
		return stb.toString();
	}
}
