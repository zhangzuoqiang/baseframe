package cn.hs.module.email.serverconfig.ui.commond;

/**
 * Title: MailServerConfigValidCommond<br>
 * Description: 邮件服务器配置ValidCommond<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author LiuHG
 * @createDate 2012-08-01
 * @version $Revision$
 */
public class MailServerConfigValidCommond extends MailServerConfigCommond {

	private String configID;// 服务器配置ID
	private String configName;// 配置名称
	private String configAlias;// 配置别名
	private String smtpHost;// SMTP地址
	private Integer smtpPort;// SMTP端口
	private Integer needAuth;// 是否需要服务器验证
	private String email;// email地址
	private String displayName;// 发送显示名
	private String user;// 用户名

	/**
	 * 获取服务器配置ID
	 */
	public String getConfigID() {
		return configID;
	}

	/**
	 * 设置服务器配置ID
	 */
	public void setConfigID(String configID) {
		this.configID = configID;
	}

	/**
	 * 获取配置名称
	 */
	public String getConfigName() {
		return configName;
	}

	/**
	 * 设置配置名称
	 */
	public void setConfigName(String configName) {
		this.configName = configName;
	}

	/**
	 * 获取配置别名
	 */
	public String getConfigAlias() {
		return configAlias;
	}

	/**
	 * 设置配置别名
	 */
	public void setConfigAlias(String configAlias) {
		this.configAlias = configAlias;
	}

	/**
	 * 获取SMTP地址
	 */
	public String getSmtpHost() {
		return smtpHost;
	}

	/**
	 * 设置SMTP地址
	 */
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	/**
	 * 获取SMTP端口
	 */
	public Integer getSmtpPort() {
		return smtpPort;
	}

	/**
	 * 设置SMTP端口
	 */
	public void setSmtpPort(Integer smtpPort) {
		this.smtpPort = smtpPort;
	}

	/**
	 * 获取是否需要服务器验证
	 */
	public Integer getNeedAuth() {
		return needAuth;
	}

	/**
	 * 设置是否需要服务器验证
	 */
	public void setNeedAuth(Integer needAuth) {
		this.needAuth = needAuth;
	}

	/**
	 * 获取email地址
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置email地址
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取发送显示名
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * 设置发送显示名
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * 获取用户名
	 */
	public String getUser() {
		return user;
	}

	/**
	 * 设置用户名
	 */
	public void setUser(String user) {
		this.user = user;
	}
}
