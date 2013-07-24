package cn.hs.module.email.template.domain;

import cn.hs.core.basedao.condition.BasePageCondition;

/**
 * Title: MailTemplateCondition<br>
 * Description: 邮件模板查询条件对象<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author LiuHG
 * @createDate 2012-08-01
 * @version $Revision$
 */
public class MailTemplateCondition extends BasePageCondition {

	/**
	 * 查询条件：邮件模板主键数据
	 */
	private String[] searchTemplateIDs;

	/**
	 * 查询条件：启用/作废
	 */
	private String searchIsEnabled;

	/**
	 * 查询条件：模板ID
	 */
	private String searchTemplateID;

	/*
	 * 查询条件：模版的别名
	 */
	private String searchTemplateAlias;

	private String searchNoteTempName;// 查询条件：模板名称

	private String searchPhaseTypeCode;// 查询条件：模版类型

	public String getSearchNoteTempName() {
		return searchNoteTempName;
	}

	public void setSearchNoteTempName(String searchNoteTempName) {
		this.searchNoteTempName = searchNoteTempName;
	}

	public String getSearchPhaseTypeCode() {
		return searchPhaseTypeCode;
	}

	public void setSearchPhaseTypeCode(String searchPhaseTypeCode) {
		this.searchPhaseTypeCode = searchPhaseTypeCode;
	}

	public String[] getSearchTemplateIDs() {
		return searchTemplateIDs;
	}

	public void setSearchTemplateIDs(String[] searchTemplateIDs) {
		this.searchTemplateIDs = searchTemplateIDs;
	}

	public String getSearchIsEnabled() {
		return searchIsEnabled;
	}

	public void setSearchIsEnabled(String searchIsEnabled) {
		this.searchIsEnabled = searchIsEnabled;
	}

	public String getSearchTemplateID() {
		return searchTemplateID;
	}

	public void setSearchTemplateID(String searchTemplateID) {
		this.searchTemplateID = searchTemplateID;
	}

	public String getSearchTemplateAlias() {
		return searchTemplateAlias;
	}

	public void setSearchTemplateAlias(String searchTemplateAlias) {
		this.searchTemplateAlias = searchTemplateAlias;
	}

}
