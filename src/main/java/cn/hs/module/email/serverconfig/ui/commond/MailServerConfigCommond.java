package cn.hs.module.email.serverconfig.ui.commond;

import cn.hs.core.page.commond.BasePageCommond;

/**
 * Title: MailServerConfigCommond<br>
 * Description: 邮件服务器配置Commond<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author LiuHG
 * @createDate 2012-08-01
 * @version $Revision$
 */
public class MailServerConfigCommond extends BasePageCommond {

	private static final long serialVersionUID = 1L;

	private String[] searchConfigIDs;

	private String searchConfigID;// 查询条件：主键

	private String searchIsEnabled;// 查询条件：启用/作废

	private String searchConfigName;// 查询条件：配置名称

	private String searchConfigAlias;// 查询条件：查询条件：配置别名

	public String[] getSearchConfigIDs() {
		return searchConfigIDs;
	}

	public void setSearchConfigIDs(String[] searchConfigIDs) {
		this.searchConfigIDs = searchConfigIDs;
	}

	public String getSearchConfigID() {
		return searchConfigID;
	}

	public void setSearchConfigID(String searchConfigID) {
		this.searchConfigID = searchConfigID;
	}

	public String getSearchIsEnabled() {
		return searchIsEnabled;
	}

	public void setSearchIsEnabled(String searchIsEnabled) {
		this.searchIsEnabled = searchIsEnabled;
	}

	/**
	 * @return the searchConfigAlias
	 */
	public String getSearchConfigAlias() {
		return searchConfigAlias;
	}

	/**
	 * @param searchConfigAlias
	 *            the searchConfigAlias to set
	 */
	public void setSearchConfigAlias(String searchConfigAlias) {
		this.searchConfigAlias = searchConfigAlias;
	}

	/**
	 * @return the searchConfigName
	 */
	public String getSearchConfigName() {
		return searchConfigName;
	}

	/**
	 * @param searchConfigName
	 *            the searchConfigName to set
	 */
	public void setSearchConfigName(String searchConfigName) {
		this.searchConfigName = searchConfigName;
	}
}
