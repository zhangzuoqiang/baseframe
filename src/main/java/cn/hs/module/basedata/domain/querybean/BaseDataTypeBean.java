/*
 * $Log: BaseDataTypeBean.java,v $
 * Revision 1.1  2012/05/23 09:27:52  guor
 * 初次提交
 *
 */
package cn.hs.module.basedata.domain.querybean;

import cn.hs.core.basedao.base.querybean.BaseBean;

/**
 * Title: BaseDataTypeBean<br>
 * Description: <br>
 * Company: GOLDGOV<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Nov 11, 2011
 * @version $Revision: 72 $
 */
public class BaseDataTypeBean extends BaseBean {
	private static final long serialVersionUID = 1L;
	private String typeID;
	private String typeName;
	private String typeCode;

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeID() {
		return typeID;
	}

	public void setTypeID(String typeID) {
		this.typeID = typeID;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
