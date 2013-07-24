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

import cn.hs.module.user.domain.User;

/**
 * Title: RoleResourse<br>
 * Description: 角色用户关系<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 23, 2011
 * @version $Revision: 90 $
 */
@Entity
@Table(name = "BASE_ROLE_USER")
public class RoleUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private String roleUserID;// 主键

	private Role role;// 角色

	private User user;// 资源

	@Id
	@Column(name = "ROLE_USER_ID")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	public String getRoleUserID() {
		return roleUserID;
	}

	public void setRoleUserID(String roleUserID) {
		this.roleUserID = roleUserID;
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
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
