/*
 * $Log: BaseTreeCondition.java,v $
 * Revision 1.1  2012/05/23 09:27:47  guor
 * 初次提交
 *
 */
package cn.hs.core.basedao.condition;

import java.util.List;

import cn.hs.core.servicetemplate.treetemplate.jsonbean.JsonTreeBean;
import cn.hs.core.servicetemplate.treetemplate.jsonbean.jsonnodeparameters.UpdateOrderParameter;

/**
 * Title: BaseTreeCondition<br>
 * Description: 树形结构condition<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 12, 2011
 * @version $Revision: 1.1 $
 */
public class BaseTreeCondition extends BaseCondition {

	/**
	 * 当前节点ID
	 */
	private String treeNodeID;
	private String parentNodeID;
	private String childNodeIds;
	private String node; // EXT 默认当前节点
	/**
	 * 排序字段更新集合
	 */
	private List<UpdateOrderParameter> updateOrderParameter;
	/**
	 * 根据业务逻辑设置是否查询叶子节点开关
	 */
	private boolean isFindLeapNode;
	/**
	 * 树类型,默认为default_tree
	 */
	private String treeType = JsonTreeBean.TREE_TYPE_DEFAULT;
	/**
	 * 默认选中值，下拉复选树使用
	 */
	private String defaultCheckedValue;

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	/**
	 * 方法作废，使用getNode()方法
	 * 
	 * @return
	 * @author HuangS
	 * @date Jul 24, 2012
	 */
	@Deprecated
	public String getTreeNodeID() {
		return treeNodeID;
	}

	/**
	 * 方法作废，使用setNode()方法
	 * 
	 * @return
	 * @author HuangS
	 * @date Jul 24, 2012
	 */
	@Deprecated
	public void setTreeNodeID(String treeNodeID) {
		this.treeNodeID = treeNodeID;
	}

	public boolean isFindLeapNode() {
		return isFindLeapNode;
	}

	public void setFindLeapNode(boolean isFindLeapNode) {
		this.isFindLeapNode = isFindLeapNode;
	}

	public String getDefaultCheckedValue() {
		return defaultCheckedValue;
	}

	public void setDefaultCheckedValue(String defaultCheckedValue) {
		this.defaultCheckedValue = defaultCheckedValue;
	}

	public String getParentNodeID() {
		return parentNodeID;
	}

	public void setParentNodeID(String parentNodeID) {
		this.parentNodeID = parentNodeID;
	}

	public String getChildNodeIds() {
		return childNodeIds;
	}

	public void setChildNodeIds(String childNodeIds) {
		this.childNodeIds = childNodeIds;
	}

	public List<UpdateOrderParameter> getUpdateOrderParameter() {
		return updateOrderParameter;
	}

	public void setUpdateOrderParameter(List<UpdateOrderParameter> updateOrderParameter) {
		this.updateOrderParameter = updateOrderParameter;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

}
