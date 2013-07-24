package cn.hs.module.email.template.ui.commond;

/**
 * Title: MailTemplateValidCommond<br>
 * Description: 邮件模板ValidCommond<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author LiuHG
 * @createDate 2012-08-01
 * @version $Revision$
 */
public class MailTemplateValidCommond extends MailTemplateCommond {

	private String templateID;// 模板ID
	private String templateType;// 模板类型
	private String templateName;// 模板名称
	private String templateAlias;// 模板别名
	private String subject;// 模板标题
	private String content;// 模板内容
	private String clobID;// 模板BaseClob的ID modify by HuangS at 2012-09-04
	private Integer isEnabled;// 是否有效

	/**
	 * 获取模板ID
	 */
	public String getTemplateID() {
		return templateID;
	}

	/**
	 * 设置模板ID
	 */
	public void setTemplateID(String templateID) {
		this.templateID = templateID;
	}

	/**
	 * 获取模板类型
	 */
	public String getTemplateType() {
		return templateType;
	}

	/**
	 * 设置模板类型
	 */
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	/**
	 * 获取模板名称
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * 设置模板名称
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	/**
	 * 获取模板别名
	 */
	public String getTemplateAlias() {
		return templateAlias;
	}

	/**
	 * 设置模板别名
	 */
	public void setTemplateAlias(String templateAlias) {
		this.templateAlias = templateAlias;
	}

	/**
	 * 获取标题
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * 设置标题
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 获取模板内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置模板内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取是否有效
	 */
	public Integer getIsEnabled() {
		return isEnabled;
	}

	/**
	 * 设置是否有效
	 */
	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getClobID() {
		return clobID;
	}

	public void setClobID(String clobID) {
		this.clobID = clobID;
	}
}
