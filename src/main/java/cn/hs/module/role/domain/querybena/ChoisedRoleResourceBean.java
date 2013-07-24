package cn.hs.module.role.domain.querybena;

import cn.hs.core.basedao.base.querybean.BaseBean;

/**
 * 页面显示列表Bean Title: ChoisedRoleResourceBean<br>
 * Description: <br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author YaoSC
 * @createDate Sep 9, 2011
 * @version $Revision: 62 $
 */
public class ChoisedRoleResourceBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private String roleID;// 角色ID
	private String description;// 角色描述
	private String resourceName;// 数据编码
	private String roleResourceID;
	private String parentID;
	private String url;// 访问路径
	private Integer activeState;

	public Integer getActiveState() {
		return activeState;
	}

	public void setActiveState(Integer activeState) {
		this.activeState = activeState;
	}

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRoleResourceID() {
		return roleResourceID;
	}

	public void setRoleResourceID(String roleResourceID) {
		this.roleResourceID = roleResourceID;
	}

	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

}
