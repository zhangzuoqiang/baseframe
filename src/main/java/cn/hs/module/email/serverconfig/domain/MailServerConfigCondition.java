package cn.hs.module.email.serverconfig.domain;

import cn.hs.core.basedao.condition.BasePageCondition;

/**
 * Title: MailServerConfigCondition<br>
 * Description: 邮件服务器配置查询条件对象<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author LiuHG
 * @createDate 2012-08-01
 * @version $Revision$
 */
public class MailServerConfigCondition extends BasePageCondition {

	/**
	 * 查询条件：邮件服务器配置主键数据
	 */
	private String[] searchConfigIDs;

	/**
	 * 查询条件：启用/作废
	 */
	private String searchIsEnabled;

	/**
	 * 查询条件：服务器配置ID
	 */
	private String searchConfigID;

	/**
	 * 查询条件：配置名称
	 */
	private String searchConfigName;

	/**
	 * 查询条件：配置别名
	 */
	private String searchConfigAlias;

	public String[] getSearchConfigIDs() {
		return searchConfigIDs;
	}

	public void setSearchConfigIDs(String[] searchConfigIDs) {
		this.searchConfigIDs = searchConfigIDs;
	}

	public String getSearchIsEnabled() {
		return searchIsEnabled;
	}

	public void setSearchIsEnabled(String searchIsEnabled) {
		this.searchIsEnabled = searchIsEnabled;
	}

	public String getSearchConfigID() {
		return searchConfigID;
	}

	public void setSearchConfigID(String searchConfigID) {
		this.searchConfigID = searchConfigID;
	}

	public String getSearchConfigName() {
		return searchConfigName;
	}

	public void setSearchConfigName(String searchConfigName) {
		this.searchConfigName = searchConfigName;
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

}
