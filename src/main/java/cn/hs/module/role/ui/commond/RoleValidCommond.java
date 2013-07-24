package cn.hs.module.role.ui.commond;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * Title: AddOrUpdateRoleCommond<br>
 * Description: 添加或修改时用来与页面传递数据的commond<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Sep 6, 2011
 * @version $Revision: 11 $
 */
public class RoleValidCommond {

	private String roleID;// 角色ID

	@NotBlank(message = "角色编码不能为空")
	private String roleCode;// 角色编码

	@NotBlank(message = "角色名称不能为空")
	private String roleName;// 角色名称

	private String description;// 角色描述

	private String type;// 角色类型

	private Integer activeState;// 活动状态

	private Integer orderNum;// 排序序号

	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

}
