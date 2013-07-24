package cn.hs.core.springext.security.casclient.domain.json;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * Title: System<br>
 * Description: 系统json实体<br>
 * Company: HsS<br>
 * Copyright @ 2013 HsS .All rights reserved.<br>
 * @author HuangS
 * @createDate Apr 30, 2013
 * @version $Revision:$
 */
public class CasSystems implements Serializable {

	/**
	 * @author HuangS
	 * @date Apr 30, 2013
	 */
	private static final long serialVersionUID = 1L;

	private String systemID; // 系统ID

	private String systemName; // 系统名称

	private String systemURL; // 系统路径

	private Integer activeState; // 系统状�??

	private List<CasRole> roleList; // 角色集合

	public String getSystemID() {
		return systemID;
	}

	public void setSystemID(String systemID) {
		this.systemID = systemID;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getSystemURL() {
		return systemURL;
	}

	public void setSystemURL(String systemURL) {
		this.systemURL = systemURL;
	}

	public Integer getActiveState() {
		return activeState;
	}

	public void setActiveState(Integer activeState) {
		this.activeState = activeState;
	}

	public List<CasRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<CasRole> roleList) {
		this.roleList = roleList;
	}

}
