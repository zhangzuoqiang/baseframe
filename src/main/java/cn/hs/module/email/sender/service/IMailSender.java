package cn.hs.module.email.sender.service;

import javax.activation.DataSource;

import cn.hs.module.email.sender.service.bo.MailMessage;

public interface IMailSender {

	public int send(MailMessage mailMessage);
	/**
	 * 是否要异步发邮件。
	 * @param mailMessage
	 * @param async 为true时为异步发邮件。
	 * @return
	 */
	public int send(MailMessage mailMessage, boolean async);
	public void addAttachment(DataSource dataSource);
}
