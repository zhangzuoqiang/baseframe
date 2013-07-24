package cn.hs.module.department.domain;

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
 * Title: DepartmentUser<br>
 * Description:单位学员 <br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author XiangBin
 * @createDate Jul 31, 2012
 * @version $Revision:$
 */
@Entity
@Table(name = "BASE_DEPARTMENT_USER")
public class DepartmentUser implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 活动状态，启用
	 */
	public final static Integer IS_ACTIVE_Y = new Integer(1);
	/**
	 * 活动状态，作废
	 */
	public final static Integer IS_ACTIVE_N = new Integer(2);

	private String deptUserID;// 主键
	private User user;// 学员
	private Department department;// 所属部门
	private Department oldDepartment;// 原部门

	@Id
	@Column(name = "DEPUSER_ID")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	public String getDeptUserID() {
		return deptUserID;
	}

	public void setDeptUserID(String deptUserID) {
		this.deptUserID = deptUserID;
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "DEPID")
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "OLD_DEPTID")
	public Department getOldDepartment() {
		return oldDepartment;
	}

	public void setOldDepartment(Department oldDepartment) {
		this.oldDepartment = oldDepartment;
	}

}
