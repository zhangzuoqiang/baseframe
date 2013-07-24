/*
 * $Log: BaseDataCondition.java,v $
 * Revision 1.1  2012/05/23 09:27:51  guor
 * 初次提交
 *
 */
package cn.hs.module.basedata.domain;

import cn.hs.core.basedao.condition.BasePageCondition;

/**
 * Title: BaseDataCondition<br>
 * Description: 基础数据condition<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 22, 2011
 * @version $Revision: 11 $
 */
public class BaseDataCondition extends BasePageCondition {
	/**
	 * 查询条件：基础数据姓名
	 */
	private String searchBaseName;
	/**
	 * 查询条件：基础数据类型活动状态查询
	 */
	private String searchActiveState;
	/**
	 * 查询条件：基础数据类型数据编码查询
	 */
	private String searchDataCode;
	/**
	 * 查询条件：基础数据Id
	 */
	private String searchDataId;
	/**
	 * 查询条件：基础数据类型Id查询
	 */
	private String baseDataIds[];
	/**
	 * 查询条件：基础数据类别编码
	 */
	private String searchBaseTypeCode;
	/**
	 * 查询条件：基础数据类别ID
	 */
	private String searchDataTypeID;
	/**
	 * 查询条件：启用状态
	 */
	private String searchActive;

	/**
	 * 查询条件：基础数据类别ID
	 */
	private String[] searchDataTypeIDs;

	public String getSearchBaseName() {
		return searchBaseName;
	}

	public void setSearchBaseName(String searchBaseName) {
		this.searchBaseName = searchBaseName;
	}

	public String getSearchActiveState() {
		return searchActiveState;
	}

	public void setSearchActiveState(String searchActiveState) {
		this.searchActiveState = searchActiveState;
	}

	public String getSearchDataCode() {
		return searchDataCode;
	}

	public void setSearchDataCode(String searchDataCode) {
		this.searchDataCode = searchDataCode;
	}

	public String getSearchDataId() {
		return searchDataId;
	}

	public void setSearchDataId(String searchDataId) {
		this.searchDataId = searchDataId;
	}

	public String getSearchBaseTypeCode() {
		return searchBaseTypeCode;
	}

	public void setSearchBaseTypeCode(String searchBaseTypeCode) {
		this.searchBaseTypeCode = searchBaseTypeCode;
	}

	public String getSearchDataTypeID() {
		return searchDataTypeID;
	}

	public void setSearchDataTypeID(String searchDataTypeID) {
		this.searchDataTypeID = searchDataTypeID;
	}

	public String getSearchActive() {
		return searchActive;
	}

	public void setSearchActive(String searchActive) {
		this.searchActive = searchActive;
	}

	public String[] getBaseDataIds() {
		return baseDataIds;
	}

	public void setBaseDataIds(String[] baseDataIds) {
		this.baseDataIds = baseDataIds;
	}

	public String[] getSearchDataTypeIDs() {
		return searchDataTypeIDs;
	}

	public void setSearchDataTypeIDs(String[] searchDataTypeIDs) {
		this.searchDataTypeIDs = searchDataTypeIDs;
	}
}
