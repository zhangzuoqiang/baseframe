package cn.hs.module.basedata.ui.commond;

import cn.hs.core.servicetemplate.treetemplate.commond.BaseTreeCommond;

/**
 * 
 * Title: BaseDataTypeCommond<br>
 * Description: 基础数据类别commond<br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Jul 24, 2012
 * @version $Revision:$
 */
public class BaseDataTypeCommond extends BaseTreeCommond {

	private String typeCode;

	private String typeName;

	private String typeID;

	private String parentID;

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeID() {
		return typeID;
	}

	public void setTypeID(String typeID) {
		this.typeID = typeID;
	}

}
