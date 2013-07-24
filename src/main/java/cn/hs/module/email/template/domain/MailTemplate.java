package cn.hs.module.email.template.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import cn.hs.module.basedata.domain.BaseData;
import cn.hs.module.clob.domain.BaseClob;

/**
 * Title: MailTemplate<br>
 * Description: 邮件模板实体对象<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author LiuHG
 * @createDate 2012-08-01
 * @version $Revision$
 */
@Entity
@Table(name = "BASE_NOTICE_TEMPLATE")
public class MailTemplate implements Serializable {

	private static final long serialVersionUID = 1L;

	// 通知模板

	private String templateID;// 模板ID
	private BaseData templateType;// 模板类型
	private String templateName;// 模板名称
	private String templateAlias;// 模板别名
	private String subject;// 模板标题
	private BaseClob content;// 模板内容
	private Integer activeState;// 活动状态 1 - 启用 2 - 作废
	private Integer orederNum; // 排序

	/**
	 * 设置模板ID
	 */
	@Id
	@Column(name = "TEMPLATE_ID")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	public String getTemplateID() {
		return templateID;
	}

	/**
	 * 获取模板ID
	 */
	public void setTemplateID(String templateID) {
		this.templateID = templateID;
	}

	// 修改为数据字典 modify by HuangS at 2012-09-04
	/**
	 * 设置模板类型
	 */
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "TEMPLATE_TYPE", referencedColumnName = "DATA_CODE")
	public BaseData getTemplateType() {
		return templateType;
	}

	/**
	 * 获取模板类型
	 */
	public void setTemplateType(BaseData templateType) {
		this.templateType = templateType;
	}

	@Column(name = "TEMPLATE_NAME")
	/**
	 * 设置模板名称
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * 获取模板名称
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	@Column(name = "TEMPLATE_ALIAS")
	/**
	 * 设置模板别名
	 */
	public String getTemplateAlias() {
		return templateAlias;
	}

	/**
	 * 获取模板别名
	 */
	public void setTemplateAlias(String templateAlias) {
		this.templateAlias = templateAlias;
	}

	@Column(name = "SUBJECT")
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
	 * 设置模板内容
	 */
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "CONTENT_CLOB_ID")
	public BaseClob getContent() {
		return content;
	}

	/**
	 * 获取模板内容
	 */
	public void setContent(BaseClob content) {
		this.content = content;
	}

	@Column(name = "ACTIVE_STATE")
	public Integer getActiveState() {
		return activeState;
	}

	public void setActiveState(Integer activeState) {
		this.activeState = activeState;
	}

	@Column(name = "ORDER_NUM")
	public Integer getOrederNum() {
		return orederNum;
	}

	public void setOrederNum(Integer orederNum) {
		this.orederNum = orederNum;
	}

	/**
	 * 记录日志使用
	 */
	public String toString() {
		StringBuffer stb = new StringBuffer();
		stb.append("模板ID======>");
		stb.append(getTemplateID());
		stb.append("\n模板类型======>");
		stb.append(getTemplateType());
		stb.append("\n模板名称======>");
		stb.append(getTemplateName());
		stb.append("\n模板别名======>");
		stb.append(getTemplateAlias());
		stb.append("\n模板标题======>");
		stb.append(getSubject());
		stb.append("\n模板内容======>");
		stb.append(getContent());
		stb.append("\n活动状态======>");
		stb.append(getActiveState());
		stb.append("\n排序======>");
		stb.append(getOrederNum());
		return stb.toString();
	}

}
