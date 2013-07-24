package cn.hs.module.department.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import cn.hs.commons.DateUtil;

/**
 * Title: Department<br>
 * Description:单位 <br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author XiangBin
 * @createDate Jul 31, 2012
 * @version $Revision:$
 */
@Entity
@Table(name = "BASE_DEPARTMENT")
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 公司等级：集团。
	 */
	public final static Integer COMPANY_LEVEL_GROUP = new Integer(1);
	/**
	 * 公司等级：总部。
	 */
	public final static Integer COMPANY_LEVEL_HEADQUARTERS = new Integer(2);
	/**
	 * 公司等级：省级。
	 */
	public final static Integer COMPANY_LEVEL_PROVINCE = new Integer(3);
	/**
	 * 公司等级：市级。
	 */
	public final static Integer COMPANY_LEVEL_CITY = new Integer(4);

	/**
	 * 活动状态，启用
	 */
	public final static Integer IS_ACTIVE_Y = new Integer(1);
	/**
	 * 活动状态，作废
	 */
	public final static Integer IS_ACTIVE_N = new Integer(2);
	/**
	 * 机构类别：公司
	 */
	public final static Integer DEPT_KIND_TYPE_COMPANY = new Integer(1);
	/**
	 * 机构类别：部门
	 */
	public final static Integer DEPT_KIND_TYPE_DEPARTMENT = new Integer(2);
	/**
	 * 根节点id
	 */
	public final static String ROOT_DEPARTMENT_ID = "-1";

	private String deptID;// 主键
	private String deptName;// 单位名称
	private String deptCode;// 单位编码
	private Integer companyLevel;// 公司等级
	private String deptAddress;// 单位地址
	private String deptPhone;// 单位电话号码
	private String linkMan;// 联系人
	private String treepath;// 树路径
	private String parentID;// 父级单位ID
	private String remark;// REMARK
	private Integer orderNum;// 排序序号
	private Integer activeState;// 活动状态 1 - 启用 2 - 作废
	private Long createTime; // 创建时间
	private Long lastUpdateTime; // 最后修改时间

	@Id
	@Column(name = "DEPID")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	public String getDeptID() {
		return deptID;
	}

	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	@Column(name = "DEPNAME")
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "DEPT_ADDRESS")
	public String getDeptAddress() {
		return deptAddress;
	}

	public void setDeptAddress(String deptAddress) {
		this.deptAddress = deptAddress;
	}

	@Column(name = "DEPT_PHONE")
	public String getDeptPhone() {
		return deptPhone;
	}

	public void setDeptPhone(String deptPhone) {
		this.deptPhone = deptPhone;
	}

	@Column(name = "ORDER_NUM")
	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	@Column(name = "TREEPATH")
	public String getTreepath() {
		return treepath;
	}

	public void setTreepath(String treepath) {
		this.treepath = treepath;
	}

	@Column(name = "PARENT_ID")
	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	@Column(name = "ACTIVE_STATE")
	public Integer getActiveState() {
		return activeState;
	}

	public void setActiveState(Integer activeState) {
		this.activeState = activeState;
	}

	@Column(name = "DEPT_CODE")
	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "COMPANY_LEVEL")
	public Integer getCompanyLevel() {
		return companyLevel;
	}

	public void setCompanyLevel(Integer companyLevel) {
		this.companyLevel = companyLevel;
	}

	@Column(name = "LINK_MAN")
	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	@Column(name = "CREATE_TIME")
	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Column(name = "LAST_UPDATE_TIME")
	public Long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	/**
	 * 记录日志用
	 * 
	 * @return
	 * @author HuangS
	 * @date Sep 6, 2012
	 */
	@Override
	public String toString() {
		StringBuffer stb = new StringBuffer();
		stb.append("主键======>");
		stb.append(getDeptID());
		stb.append("\n公司名称======>");
		stb.append(getDeptName());
		stb.append("\n公司编码======>");
		stb.append(getDeptCode());
		stb.append("\n单位电话号码======>");
		stb.append(getDeptPhone());
		stb.append("\n树路径======>");
		stb.append(getTreepath());
		stb.append("\n父级单位ID======>");
		stb.append(getParentID());
		stb.append("\n备注======>");
		stb.append(getRemark());
		stb.append("\n活动状态======>");
		stb.append(getActiveState());
		stb.append("\n排序编号======>");
		stb.append(getOrderNum());
		stb.append("\n创建时间======>");
		stb.append(DateUtil.getDateString(getCreateTime()));
		stb.append("\n最后修改时间======>");
		stb.append(DateUtil.getDateString(getLastUpdateTime()));
		return stb.toString();
	}
}
