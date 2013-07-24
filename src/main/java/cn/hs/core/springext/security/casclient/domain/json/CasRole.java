package cn.hs.core.springext.security.casclient.domain.json;

import java.io.Serializable;

/**
 * 
 * Title: Role<br>
 * Description: 角色json实体<br>
 * Company: HsS<br>
 * Copyright @ 2013 HsS .All rights reserved.<br>
 * @author HuangS
 * @createDate Apr 30, 2013
 * @version $Revision:$
 */
public class CasRole implements Serializable {

	/**
	 * @author HuangS
	 * @date Apr 30, 2013
	 */
	private static final long serialVersionUID = 1L;

	private String roleID; // 角色ID

	private String roleName; // 角色名称

	private String roleCode; // 角色编码

	private Integer orderNum; // 排序

	private Integer activeState; // 活动状�??

	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getActiveState() {
		return activeState;
	}

	public void setActiveState(Integer activeState) {
		this.activeState = activeState;
	}

}
