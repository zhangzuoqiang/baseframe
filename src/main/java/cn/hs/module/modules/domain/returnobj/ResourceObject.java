/*
 *$Log:$
 */
package cn.hs.module.modules.domain.returnobj;

import java.util.List;

/**
 * Title: ResourceBojce<br>
 * Description: 菜单json对象<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author GuoR
 * @createDate 2012-7-19
 * @version $Revision:$
 */
public class ResourceObject {
	private String resourceID;// 主键

	private String url;// 访问路径

	private String resourceName;// 资源名称

	private String description;// 资源描述

	private String treepath;// 树路径

	private Integer isContainLeapnode;// 是否包含子节点 1 - 包含 2 - 不包含

	private String parentID;// 父级ID

	private Integer activeState;// 活动状态 1 - 启用 2 - 作废

	private Integer orderNum;// 排序序号

	/**
	 * @return the resourceID
	 */
	public String getResourceID() {
		return resourceID;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return the resourceName
	 */
	public String getResourceName() {
		return resourceName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the treepath
	 */
	public String getTreepath() {
		return treepath;
	}

	/**
	 * @return the isContainLeapnode
	 */
	public Integer getIsContainLeapnode() {
		return isContainLeapnode;
	}

	/**
	 * @return the parentID
	 */
	public String getParentID() {
		return parentID;
	}

	/**
	 * @return the activeState
	 */
	public Integer getActiveState() {
		return activeState;
	}

	/**
	 * @return the orderNum
	 */
	public Integer getOrderNum() {
		return orderNum;
	}

	/**
	 * @param resourceID
	 *            the resourceID to set
	 */
	public void setResourceID(String resourceID) {
		this.resourceID = resourceID;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param resourceName
	 *            the resourceName to set
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param treepath
	 *            the treepath to set
	 */
	public void setTreepath(String treepath) {
		this.treepath = treepath;
	}

	/**
	 * @param isContainLeapnode
	 *            the isContainLeapnode to set
	 */
	public void setIsContainLeapnode(Integer isContainLeapnode) {
		this.isContainLeapnode = isContainLeapnode;
	}

	/**
	 * @param parentID
	 *            the parentID to set
	 */
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	/**
	 * @param activeState
	 *            the activeState to set
	 */
	public void setActiveState(Integer activeState) {
		this.activeState = activeState;
	}

	/**
	 * @param orderNum
	 *            the orderNum to set
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	private List<ResourceObject> children;

	/**
	 * @return the children
	 */
	public List<ResourceObject> getChildren() {
		return children;
	}

	/**
	 * @param children
	 *            the children to set
	 */
	public void setChildren(List<ResourceObject> children) {
		this.children = children;
	}
}
