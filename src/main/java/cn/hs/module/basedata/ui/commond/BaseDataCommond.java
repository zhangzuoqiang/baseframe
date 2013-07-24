package cn.hs.module.basedata.ui.commond;

import cn.hs.core.page.commond.BasePageCommond;

/**
 * 
 * Title: BaseDataCommond<br>
 * Description: 基础数据Commond<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author FanKY
 * @createDate Aug 31, 2011
 * @version $Revision: 73 $
 */
public class BaseDataCommond extends BasePageCommond {
	/**
	 * @author HuangS
	 * @date Jul 20, 2012
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 基础数据名称
	 */
	private String searchBaseName;
	/**
	 * 基础数据类型活动状态查询
	 */
	private String searchActiveState;
	/**
	 * 基础数据类型数据编码查询
	 */
	private String searchDataCode;
	/**
	 * 基础数据id
	 */
	private String searchDataId;
	/**
	 * 基础数据类型Id查询
	 */
	private String baseDataIds[];

	private String searchDataTypeID;// 基础数据类别ID

	private String searchActive;
	/**
	 * 查询条件：基础数据类别编码
	 */
	private String searchBaseTypeCode;

	public String getSearchBaseTypeCode() {
		return searchBaseTypeCode;
	}

	public void setSearchBaseTypeCode(String searchBaseTypeCode) {
		this.searchBaseTypeCode = searchBaseTypeCode;
	}

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

	public String[] getBaseDataIds() {
		return baseDataIds;
	}

	public void setBaseDataIds(String[] baseDataIds) {
		this.baseDataIds = baseDataIds;
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

}
