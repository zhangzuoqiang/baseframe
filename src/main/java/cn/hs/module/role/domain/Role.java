/*
 * $Log: Role.java,v $
 * Revision 1.1  2012/05/23 09:27:55  guor
 * 初次提交
 *
 */
package cn.hs.module.role.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import cn.hs.module.basedata.domain.BaseData;

/**
 * Title: Role<br>
 * Description: 角色<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 23, 2011
 * @version $Revision: 90 $
 */
@Entity
@Table(name = "BASE_ROLE")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 启用
	 */
	public final static Integer IS_ACTIVE_Y = new Integer(1);
	/**
	 * 作废
	 */
	public final static Integer IS_ACTIVE_N = new Integer(2);

	private String roleID;// 角色ID

	private String roleCode;// 角色编码

	private String roleName;// 角色名称

	private String description;// 角色描述

	private BaseData roleType;// 角色类型

	private Integer activeState;// 活动状态

	private Integer orderNum;// 排序序号

	@Id
	@Column(name = "ROLE_ID")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	@Column(name = "ROLE_CODE")
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	@Column(name = "ROLE_NAME")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ROLE_TYPE", referencedColumnName = "DATA_CODE")
	public BaseData getRoleType() {
		return roleType;
	}

	public void setRoleType(BaseData roleType) {
		this.roleType = roleType;
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
}
