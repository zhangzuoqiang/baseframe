package cn.hs.module.email.sender.service.bo;

import java.util.Properties;

public class MailMessage {

	private String subject;
	private String content;
	private String[] to;
	private String[] cc;
	private String[] bcc;

	private String templateAlias;
	private Properties properties;

	private String serverConfigAlias;

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the to
	 */
	public String[] getTo() {
		return to;
	}

	/**
	 * @param to
	 *            the to to set
	 */
	public void setTo(String[] to) {
		this.to = to;
	}

	/**
	 * @return the cc
	 */
	public String[] getCc() {
		return cc;
	}

	/**
	 * @param cc
	 *            the cc to set
	 */
	public void setCc(String[] cc) {
		this.cc = cc;
	}

	/**
	 * @return the bcc
	 */
	public String[] getBcc() {
		return bcc;
	}

	/**
	 * @param bcc
	 *            the bcc to set
	 */
	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the templateAlias
	 */
	public String getTemplateAlias() {
		return templateAlias;
	}

	/**
	 * @param templateAlias
	 *            the templateAlias to set
	 */
	public void setTemplateAlias(String templateAlias) {
		this.templateAlias = templateAlias;
	}

	/**
	 * @return the serverConfigAlias
	 */
	public String getServerConfigAlias() {
		return serverConfigAlias;
	}

	/**
	 * @param serverConfigAlias
	 *            the serverConfigAlias to set
	 */
	public void setServerConfigAlias(String serverConfigAlias) {
		this.serverConfigAlias = serverConfigAlias;
	}

	/**
	 * @return the properties
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * @param properties
	 *            the properties to set
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}
