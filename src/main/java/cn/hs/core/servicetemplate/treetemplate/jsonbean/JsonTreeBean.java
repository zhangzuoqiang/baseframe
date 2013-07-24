/*
 * $Log: JsonTreeBean.java,v $
 * Revision 1.1  2012/05/23 09:27:49  guor
 * 初次提交
 *
 */
package cn.hs.core.servicetemplate.treetemplate.jsonbean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title: JsonDepartment<br>
 * Description: 树形结构节点集合JSON格式<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 11, 2011
 * @version $Revision: 1.1 $
 */
public class JsonTreeBean implements Serializable {
	/**
	 * @author WangWB
	 * @date Aug 16, 2011
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 根节点的parentID
	 */
	public static final String ROOT_NODE_PARENT_ID = "-2";
	/**
	 * 根节点的ID
	 */
	public static final String ROOT_NODE_ID = "-1";
	/**
	 * 叶子节点(EXT)
	 */
	public static final boolean IS_LEAP_NODE_EXT = true;
	/**
	 * 非叶子节点(EXT)
	 */
	public static final boolean IS_NOT_LEAP_NODE_EXT = false;
	/**
	 * 叶子节点(easyui)
	 */
	public static final String IS_LEAP_NODE_UI = "open";
	/**
	 * 非叶子节点(easyui)
	 */
	public static final String IS_NOT_LEAP_NODE_UI = "closed";
	/**
	 * Model中设置的树节点集合属性名称
	 */
	public static final String TREE_NODE_LIST_ATTRIBUTE_NAME = "treeNodeList";
	/**
	 * 树形结构类型：默认树
	 */
	public static final String TREE_TYPE_DEFAULT = "default_tree";
	/**
	 * 树形结构类型：下拉复选树
	 */
	public static final String TREE_TYPE_SELECT = "select_tree";
	/**
	 * 树形结构类型：拖拽树
	 */
	public static final String TREE_TYPE_DRAG = "drag_tree";

	/**
	 * 节点ID
	 */
	private String id;
	/**
	 * 父节点ID
	 */
	private String parentID;
	/**
	 * 节点名称
	 */
	private String text;
	/**
	 * 节点是否为叶子节点(false为非叶子节点 true为叶子节点)EXT使用
	 */
	private boolean leaf;
	/**
	 * 节点是否为叶子节点(closed为非叶子节点 open为叶子节点)easyui使用
	 */
	private String state;
	/**
	 * 下拉复选树使用：是否选中
	 */
	private Boolean checked;
	/**
	 * 下拉复选树使用：？
	 */
	private Map<String, Object> attributes = new HashMap<String, Object>();
	/**
	 * 下拉复选树使用：子节点集合
	 */
	private List<JsonTreeBean> children;
	/**
	 * 节点图标地址
	 */
	private String icon;
	/**
	 * 超链接地址
	 */
	private String href;

	private String hrefTarget;// 链接target
	private Boolean expanded;// 如果节点展开则为true
	private Boolean allowDrag;// true可以拖动，false不可以拖动
	private Boolean allowDrop;
	private Boolean expandable;// 节点展开或关闭
	private String iconCls;// 图标样式
	private String cls;// css样式
	private String qtip;// 节点上的提示文字
	private String qtitle;// 提示标题

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<JsonTreeBean> getChildren() {
		return children;
	}

	public void setChildren(List<JsonTreeBean> children) {
		this.children = children;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getHrefTarget() {
		return hrefTarget;
	}

	public void setHrefTarget(String hrefTarget) {
		this.hrefTarget = hrefTarget;
	}

	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	public Boolean getAllowDrag() {
		return allowDrag;
	}

	public void setAllowDrag(Boolean allowDrag) {
		this.allowDrag = allowDrag;
	}

	public Boolean getAllowDrop() {
		return allowDrop;
	}

	public void setAllowDrop(Boolean allowDrop) {
		this.allowDrop = allowDrop;
	}

	public Boolean getExpandable() {
		return expandable;
	}

	public void setExpandable(Boolean expandable) {
		this.expandable = expandable;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public String getQtip() {
		return qtip;
	}

	public void setQtip(String qtip) {
		this.qtip = qtip;
	}

	public String getQtitle() {
		return qtitle;
	}

	public void setQtitle(String qtitle) {
		this.qtitle = qtitle;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
