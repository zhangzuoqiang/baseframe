package cn.hs.module.role.domain.querybena;

import cn.hs.core.basedao.base.querybean.BaseBean;

/**
 * 
 * Title: RoleResourceMenuBean<br>
 * Description: 资源menuBean<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Sep 14, 2011
 * @version $Revision: 62 $
 */
public class RoleResourceMenuBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private String roleID;// 角色ID

	private String roleCode;// 角色编码

	private Integer activeState;// 角色-资源活动状态

	private String resourceID;// 资源主键

	private String url;// 访问路径

	private String resourceName;// 资源名称

	private String parentID;// 资源父级ID

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

	public Integer getActiveState() {
		return activeState;
	}

	public void setActiveState(Integer activeState) {
		this.activeState = activeState;
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

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

}
