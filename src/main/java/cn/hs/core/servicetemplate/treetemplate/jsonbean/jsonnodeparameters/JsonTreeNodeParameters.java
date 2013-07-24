/*
 * $Log: JsonTreeNodeParameters.java,v $
 * Revision 1.1  2012/05/23 09:27:49  guor
 * 初次提交
 *
 */
package cn.hs.core.servicetemplate.treetemplate.jsonbean.jsonnodeparameters;

/**
 * Title: JsonTreePanelPoint<br>
 * Description: 左侧树节点修改所需参数类<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author FanKY
 * @createDate Oct 12, 2011
 * @version $Revision: 1.1 $
 */
public class JsonTreeNodeParameters {
	/**
	 * 左侧树节点Id
	 */
	private String addTreeId;
	/**
	 * 用于拼装其它参数
	 */
	private String parameter;
	/**
	 * 用于判断添加数据是否成功
	 */
	private String succeed;

	public String getAddTreeId() {
		return addTreeId;
	}

	public void setAddTreeId(String addTreeId) {
		this.addTreeId = addTreeId;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getSucceed() {
		return succeed;
	}

	public void setSucceed(String succeed) {
		this.succeed = succeed;
	}
}
