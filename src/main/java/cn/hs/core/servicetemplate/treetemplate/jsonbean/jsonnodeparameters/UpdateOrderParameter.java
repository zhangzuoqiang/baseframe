/*
 * $Log: UpdateOrderParameter.java,v $
 * Revision 1.1  2012/05/23 09:27:49  guor
 * 初次提交
 *
 */
package cn.hs.core.servicetemplate.treetemplate.jsonbean.jsonnodeparameters;

/**
 * Title: UpdateOrderParameter<br>
 * Description: 拖拽后更新树形结构排序组成参数对象<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Nov 10, 2011
 * @version $Revision: 1.1 $
 */
public class UpdateOrderParameter {
	// 需要更新记录的主键
	private String upObjID;
	// 字段ordernum更新后的值
	private String upOrderNumValue;

	public String getUpObjID() {
		return upObjID;
	}

	public void setUpObjID(String upObjID) {
		this.upObjID = upObjID;
	}

	public String getUpOrderNumValue() {
		return upOrderNumValue;
	}

	public void setUpOrderNumValue(String upOrderNumValue) {
		this.upOrderNumValue = upOrderNumValue;
	}

}
