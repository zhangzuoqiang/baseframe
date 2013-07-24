package cn.hs.module.modules.ui.commond;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * Title: ResourceValidCommond<br>
 * Description: 菜单维护验证commond<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author FanKY
 * @createDate Sep 13, 2011
 * @version $Revision: 90 $
 */
public class ResourceValidCommond extends ResourceCommond {
	/**
	 * 系列号
	 */
	private static final long serialVersionUID = 5209771588158211071L;

	private String resourceID;// 主键

	@NotBlank(message = "访问路径不能为空")
	private String url;// 访问路径

	@NotBlank(message = "资源名称不能为空")
	private String resourceName;// 资源名称

	private String description;// 资源描述

	private String treepath;// 树路径

	private Integer isContainLeapnode;// 是否包含子节点 1 - 包含 2 - 不包含

	private String parentID;// 父级ID

	private Integer activeState;// 活动状态 1 - 启用 2 - 作废

	private Integer orderNum;// 排序序号

	private String nodeParameter;// 修改左侧树所需用途参数

	// create by HuangS at 2012-08-23
	private Integer isMenu;// 是否为菜单资源 1 - 菜单 2 - 功能点

	public Integer getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(Integer isMenu) {
		this.isMenu = isMenu;
	}

	public String getResourceID() {
		return resourceID;
	}

	public void setResourceID(String resourceID) {
		this.resourceID = resourceID;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTreepath() {
		return treepath;
	}

	public void setTreepath(String treepath) {
		this.treepath = treepath;
	}

	public Integer getIsContainLeapnode() {
		return isContainLeapnode;
	}

	public void setIsContainLeapnode(Integer isContainLeapnode) {
		this.isContainLeapnode = isContainLeapnode;
	}

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public Integer getActiveState() {
		return activeState;
	}

	public void setActiveState(Integer activeState) {
		this.activeState = activeState;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getNodeParameter() {
		return nodeParameter;
	}

	public void setNodeParameter(String nodeParameter) {
		this.nodeParameter = nodeParameter;
	}

}
