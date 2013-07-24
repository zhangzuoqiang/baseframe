package cn.hs.module.role.domain.querybena;

import cn.hs.core.basedao.base.querybean.BaseBean;

/**
 * Title: RoleTypeData<br>
 * Description: 页面显示列表Bean <br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Sep 9, 2011
 * @version $Revision: 62 $
 */
public class RoleTypeDataBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private String roleID;// 角色ID

	private String roleCode;// 角色编码

	private String roleName;// 角色名称

	private String description;// 角色描述

	private Integer activeState;// 活动状态

	private Integer orderNum;// 排序序号

	private String dataCode;// 数据编码

	private String dataName;// 数据名称

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

	public String getDataCode() {
		return dataCode;
	}

	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

}
