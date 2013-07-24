package cn.hs.core.springext.security.casclient.domain.json;

import java.io.Serializable;

/**
 * 
 * Title: Department<br>
 * Description: 部门json实体<br>
 * Company: HsS<br>
 * Copyright @ 2013 HsS .All rights reserved.<br>
 * @author HuangS
 * @createDate Apr 30, 2013
 * @version $Revision:$
 */
public class CasDepartment implements Serializable {

	/**
	 * @author HuangS
	 * @date Apr 30, 2013
	 */
	private static final long serialVersionUID = 1L;

	private String deptID; // 部门ID

	private String depName; // 部门名称

	private String depCode; // 部门编码

	private String parentID; // 父级ID

	private String treePath; // 树路�?

	private String countiesCoding; // 平台编码

	private Integer orderNum; // 排序

	private Integer activeState; // 活动状�??

	public String getDeptID() {
		return deptID;
	}

	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getDepCode() {
		return depCode;
	}

	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public String getTreePath() {
		return treePath;
	}

	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}

	public String getCountiesCoding() {
		return countiesCoding;
	}

	public void setCountiesCoding(String countiesCoding) {
		this.countiesCoding = countiesCoding;
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
