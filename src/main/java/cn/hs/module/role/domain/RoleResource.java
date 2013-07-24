/*
 * $Log: RoleResource.java,v $
 * Revision 1.1  2012/05/23 09:27:56  guor
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

import cn.hs.module.modules.domain.Resource;

/**
 * Title: RoleResourse<br>
 * Description: 角色资源关系<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 23, 2011
 * @version $Revision: 90 $
 */
@Entity
@Table(name = "BASE_ROLE_RESOURCE")
public class RoleResource implements Serializable {

	private static final long serialVersionUID = 1L;

	private String roleResourceID;// 主键

	private Role role;// 角色

	private Resource resource;// 资源

	@Id
	@Column(name = "ROLE_RESOURCE_ID")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	public String getRoleResourceID() {
		return roleResourceID;
	}

	public void setRoleResourceID(String roleResourceID) {
		this.roleResourceID = roleResourceID;
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ROLE_ID")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "RESOURCE_ID")
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

}
