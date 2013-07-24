package cn.hs.module.email.template.ui.commond;

import cn.hs.core.page.commond.BasePageCommond;

/**
 * Title: MailTemplateCommond<br>
 * Description: 邮件模板Commond<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author LiuHG
 * @createDate 2012-08-01
 * @version $Revision$
 */
public class MailTemplateCommond extends BasePageCommond {

	private static final long serialVersionUID = 1L;

	private String[] searchTemplateIDs;

	private String searchTemplateID;// 查询条件：主键

	private String searchIsEnabled;// 查询条件：启用/作废

	private String searchNoteTempName;// 查询条件：模板名称

	private String searchTemplateAlias;// 查询条件 ：模板别名

	private String searchPhaseTypeCode;// 查询条件：模版类型

	public String[] getSearchTemplateIDs() {
		return searchTemplateIDs;
	}

	public void setSearchTemplateIDs(String[] searchTemplateIDs) {
		this.searchTemplateIDs = searchTemplateIDs;
	}

	public String getSearchTemplateID() {
		return searchTemplateID;
	}

	public void setSearchTemplateID(String searchTemplateID) {
		this.searchTemplateID = searchTemplateID;
	}

	public String getSearchIsEnabled() {
		return searchIsEnabled;
	}

	public void setSearchIsEnabled(String searchIsEnabled) {
		this.searchIsEnabled = searchIsEnabled;
	}

	public String getSearchNoteTempName() {
		return searchNoteTempName;
	}

	public void setSearchNoteTempName(String searchNoteTempName) {
		this.searchNoteTempName = searchNoteTempName;
	}

	public String getSearchTemplateAlias() {
		return searchTemplateAlias;
	}

	public void setSearchTemplateAlias(String searchTemplateAlias) {
		this.searchTemplateAlias = searchTemplateAlias;
	}

	public String getSearchPhaseTypeCode() {
		return searchPhaseTypeCode;
	}

	public void setSearchPhaseTypeCode(String searchPhaseTypeCode) {
		this.searchPhaseTypeCode = searchPhaseTypeCode;
	}

}
