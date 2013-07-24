/*
 * $Log: Resource.java,v $
 * Revision 1.1  2012/05/23 09:27:54  guor
 * 初次提交
 *
 */
package cn.hs.module.modules.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Title: Resource<br>
 * Description: 功能模块<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 23, 2011
 * @version $Revision: 61 $
 */
@Entity
@Table(name = "BASE_RESOURCE")
public class Resource implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 启用
	 */
	public final static Integer IS_ACTIVE_Y = new Integer(1);
	/**
	 * 作废
	 */
	public final static Integer IS_ACTIVE_N = new Integer(2);
	/**
	 * 是否包含子节点：包含
	 */
	public static final Integer IS_CONTAIN_LEAPNODE_Y = new Integer(1);
	/**
	 * 是否包含子节点：不包含
	 */
	public static final Integer IS_CONTAIN_LEAPNODE_N = new Integer(2);

	/**
	 * 是否菜单资源：菜单
	 */
	public static final Integer IS_MENU_Y = new Integer(1);

	/**
	 * 是否菜单资源：功能点
	 */
	public static final Integer IS_MENU_N = new Integer(2);

	private String resourceID;// 主键

	private String url;// 访问路径

	private String resourceName;// 资源名称

	private String description;// 资源描述

	private String icon; // 菜单图标样式

	private String treepath;// 树路径

	private Integer isContainLeapnode;// 是否包含子节点 1 - 包含 2 - 不包含

	private String parentID;// 父级ID

	private Integer activeState;// 活动状态 1 - 启用 2 - 作废

	private Integer orderNum;// 排序序号

	private Integer isMenu;// 是否为菜单资源 1 - 菜单 2 - 功能点

	@Id
	@Column(name = "RESOURCE_ID")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	public String getResourceID() {
		return resourceID;
	}

	public void setResourceID(String resourceID) {
		this.resourceID = resourceID;
	}

	@Column(name = "URL")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "RESOURCE_NAME")
	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "TREEPATH")
	public String getTreepath() {
		return treepath;
	}

	public void setTreepath(String treepath) {
		this.treepath = treepath;
	}

	@Column(name = "PARENT_ID")
	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	@Column(name = "ACTIVE_STATE")
	public Integer getActiveState() {
		return activeState;
	}

	public void setActiveState(Integer activeState) {
		this.activeState = activeState;
	}

	@Column(name = "ORDER_NUM")
	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	@Column(name = "IS_CONTAIN_LEAPNODE")
	public Integer getIsContainLeapnode() {
		return isContainLeapnode;
	}

	public void setIsContainLeapnode(Integer isContainLeapnode) {
		this.isContainLeapnode = isContainLeapnode;
	}

	@Column(name = "IS_MENU")
	public Integer getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(Integer isMenu) {
		this.isMenu = isMenu;
	}

	@Column(name = "ICON")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
