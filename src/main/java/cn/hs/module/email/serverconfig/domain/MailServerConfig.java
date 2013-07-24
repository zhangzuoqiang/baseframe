package cn.hs.module.email.serverconfig.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Title: MailServerConfig<br>
 * Description: 邮件服务器配置实体对象<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author LiuHG
 * @createDate 2012-08-01
 * @version $Revision$
 */
@Entity
@Table(name = "BASE_MAIL_SERVER_CONFIG")
public class MailServerConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";

	public static final int NEED_AUTH_YES = 1;
	public static final int NEED_AUTH_NO = 2;

	private String configID;// 服务器配置ID
	private String configName;// 配置名称
	private String configAlias;// 配置别名
	private String smtpHost;// SMTP地址
	private Integer smtpPort;// SMTP端口
	private Integer needAuth;// 是否需要服务器验证
	private String email;// email地址
	private String displayName;// 发送显示名
	private String user;// 用户名
	private String password;// 用户名

	/**
	 * 设置服务器配置ID
	 */
	@Id
	@Column(name = "CONFIG_ID")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	public String getConfigID() {
		return configID;
	}

	/**
	 * 获取服务器配置ID
	 */
	public void setConfigID(String configID) {
		this.configID = configID;
	}

	@Column(name = "CONFIG_NAME")
	/**
	 * 设置配置名称
	 */
	public String getConfigName() {
		return configName;
	}

	/**
	 * 获取配置名称
	 */
	public void setConfigName(String configName) {
		this.configName = configName;
	}

	@Column(name = "CONFIG_ALIAS")
	/**
	 * 设置配置别名
	 */
	public String getConfigAlias() {
		return configAlias;
	}

	/**
	 * 获取配置别名
	 */
	public void setConfigAlias(String configAlias) {
		this.configAlias = configAlias;
	}

	@Column(name = "SMTP_HOST")
	/**
	 * 设置SMTP地址
	 */
	public String getSmtpHost() {
		return smtpHost;
	}

	/**
	 * 获取SMTP地址
	 */
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	@Column(name = "SMTP_PORT")
	/**
	 * 设置SMTP端口
	 */
	public Integer getSmtpPort() {
		return smtpPort;
	}

	/**
	 * 获取SMTP端口
	 */
	public void setSmtpPort(Integer smtpPort) {
		this.smtpPort = smtpPort;
	}

	@Column(name = "NEED_AUTH")
	/**
	 * 设置是否需要服务器验证
	 */
	public Integer getNeedAuth() {
		return needAuth;
	}

	/**
	 * 获取是否需要服务器验证
	 */
	public void setNeedAuth(Integer needAuth) {
		this.needAuth = needAuth;
	}

	@Column(name = "EMAIL")
	/**
	 * 设置email地址
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 获取email地址
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "DISPLAY_NAME")
	/**
	 * 设置发送显示名
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * 获取发送显示名
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Column(name = "USER_NAME")
	/**
	 * 设置用户名
	 */
	public String getUser() {
		return user;
	}

	/**
	 * 获取用户名
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the password
	 */
	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 记录日志使用
	 */
	public String toString() {
		StringBuffer stb = new StringBuffer();
		stb.append("服务器配置ID======>");
		stb.append(getConfigID());
		stb.append("\n配置名称======>");
		stb.append(getConfigName());
		stb.append("\n配置别名======>");
		stb.append(getConfigAlias());
		stb.append("\nSMTP地址======>");
		stb.append(getSmtpHost());
		stb.append("\nSMTP端口======>");
		stb.append(getSmtpPort());
		stb.append("\n是否需要服务器验证======>");
		stb.append(getNeedAuth());
		stb.append("\nemail地址======>");
		stb.append(getEmail());
		stb.append("\n发送显示名======>");
		stb.append(getDisplayName());
		stb.append("\n用户名======>");
		stb.append(getUser());
		return stb.toString();
	}

}
