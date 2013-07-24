/*
 * $Log: BaseDataTypeCondition.java,v $
 * Revision 1.1  2012/05/23 09:27:52  guor
 * 初次提交
 *
 */
package cn.hs.module.basedata.domain;

import cn.hs.core.basedao.condition.BaseTreeCondition;

/**
 * Title: BaseTypeCondition<br>
 * Description: 基础数据类别condtion<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 22, 2011
 * @version $Revision: 11 $
 */
public class BaseDataTypeCondition extends BaseTreeCondition {

	private String queueTreePath; // 管理范围

	private String searchTypeID; // 基础数据类别ID modify by HuangS at 2011-09-08

	private String queueTypeCode; // 类别代码 modify by HuangS at 2011-09-07

	private String searchParentTypeCode; // 父级类别代码

	public String getSearchParentTypeCode() {
		return searchParentTypeCode;
	}

	public void setSearchParentTypeCode(String searchParentTypeCode) {
		this.searchParentTypeCode = searchParentTypeCode;
	}

	public String getTypeCode() {
		return queueTypeCode;
	}

	public void setTypeCode(String typeCode) {
		this.queueTypeCode = typeCode;
	}

	public String getTreePath() {
		return queueTreePath;
	}

	public void setTreePath(String treePath) {
		this.queueTreePath = treePath;
	}

	public String getSearchTypeID() {
		return searchTypeID;
	}

	public void setSearchTypeID(String searchTypeID) {
		this.searchTypeID = searchTypeID;
	}

}
