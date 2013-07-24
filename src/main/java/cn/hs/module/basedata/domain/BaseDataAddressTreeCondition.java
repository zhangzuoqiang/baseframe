/*
 * $Log: BaseDataCondition.java,v $
 * Revision 1.1  2012/05/23 09:27:51  guor
 * 初次提交
 *
 */
package cn.hs.module.basedata.domain;

import java.util.List;

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
public class BaseDataAddressTreeCondition extends BasePageCondition {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -2895818118745166764L;

	/**
	 * 查询条件：类型编码
	 */
	private String searchTypeCode;

	private List<BaseData> existAddressList;

	private Integer searchStatus;
	/**
	 * 查询条件：树的类型
	 */
	private String searchTreeType;

	public String getSearchTreeType() {
		return searchTreeType;
	}

	public void setSearchTreeType(String searchTreeType) {
		this.searchTreeType = searchTreeType;
	}

	public Integer getSearchStatus() {
		return searchStatus;
	}

	public List<BaseData> getExistAddressList() {
		return existAddressList;
	}

	public void setExistAddressList(List<BaseData> existAddressList) {
		this.existAddressList = existAddressList;
	}

	public String getSearchTypeCode() {
		return searchTypeCode;
	}

	public void setSearchTypeCode(String searchTypeCode) {
		this.searchTypeCode = searchTypeCode;
	}

	public void setSearchStatus(Integer searchStatus) {
		this.searchStatus = searchStatus;
	}
}
