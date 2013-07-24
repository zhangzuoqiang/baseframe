package cn.hs.module.department.dao.require;

import org.springframework.stereotype.Component;

import cn.hs.core.servicetemplate.treetemplate.jsonbean.jsonnodeparameters.UpdateOrderParameter;
import cn.hs.module.department.domain.Department;
import cn.hs.module.department.domain.DepartmentTreeCondition;

/**
 * 
 * Title: DepartmentTreeRequire<br>
 * Description:部门树查询sql拼写对象 <br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author Mill
 * @createDate 2012-8-2
 * @version $Revision:$
 */
@Component(value = "cn.hs.module.department.dao.require.DepartmentTreeRequire")
public class DepartmentTreeRequire {

	/**
	 * 查询是否为叶子节点HQL
	 * 
	 * @param condition
	 * @return
	 * @author Mill
	 * @createDate 2012-8-1
	 */
	public DepartmentTreeCondition isLeapNode(DepartmentTreeCondition condition) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("select count(d.deptID) from Department d where d.activeState= ?");
		condition.addParameter(Department.IS_ACTIVE_Y);
		if (condition.getNode() != null && !"".equals(condition.getNode())) {
			hqlBuffer.append(" and d.parentID= ?");
			condition.addParameter(condition.getNode());
		}
		condition.setSql(hqlBuffer.toString());
		return condition;
	}

	/**
	 * 更新节点排序HQL
	 * 
	 * @param updateOrderParameter
	 * @return
	 * @author Mill
	 * @createDate 2012-8-1
	 */
	public DepartmentTreeCondition updateTreeOrderNumHQL(UpdateOrderParameter updateOrderParameter) {
		DepartmentTreeCondition condition = new DepartmentTreeCondition();
		String hql = "";
		if (updateOrderParameter.getUpObjID() != null && !"".equals(updateOrderParameter.getUpObjID()) && updateOrderParameter.getUpOrderNumValue() != null && !"".equals(updateOrderParameter.getUpOrderNumValue())) {
			hql = "update Department d set d.orderNum=? where d.deptID=?";
			condition.addParameter(new Integer(updateOrderParameter.getUpOrderNumValue()));
			condition.addParameter(new Integer(updateOrderParameter.getUpObjID()));
		}
		condition.setSql(hql);
		return condition;
	}

	/**
	 * 更新节点父级ID的HQL
	 * 
	 * @param condition
	 * @return
	 * @author Mill
	 * @createDate 2012-8-1
	 */
	public DepartmentTreeCondition updateTreeParentIDHQL(DepartmentTreeCondition condition) {
		String hql = "";
		if (condition.getNode() != null && !"".equals(condition.getNode()) && condition.getParentNodeID() != null && !"".equals(condition.getParentNodeID())) {
			hql = "update Department d set d.parentID=? where d.deptID=?";
			condition.addParameter(new Integer(condition.getParentNodeID()));
			condition.addParameter(new Integer(condition.getNode()));
		}
		condition.setSql(hql);
		return condition;
	}

	/**
	 * 查询所有机构
	 * 
	 * @param condition
	 * @return
	 * @author Mill
	 * @createDate 2012-8-2
	 */
	public DepartmentTreeCondition listDepartmentTreeHQL(DepartmentTreeCondition condition) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select t from Department t where t.activeState = ?");
		condition.addParameter(Department.IS_ACTIVE_Y);
		if (condition.getNode() != null && !"".equals(condition.getNode())) {
			sqlBuffer.append(" and t.parentID= ?");
			condition.addParameter(condition.getNode());
		}
		sqlBuffer.append("  order by t.orderNum");
		condition.setSql(sqlBuffer.toString());
		return condition;
	}

	/**
	 * 查询所有机构
	 * 
	 * @param condition
	 * @return
	 * @author Mill
	 * @createDate 2012-8-2
	 */
	public DepartmentTreeCondition listDepartmentCheckedTreeHQL(DepartmentTreeCondition condition) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select t.deptID as deptID,t.deptName as deptName,t.belongToRegion as belongToRegion,t.deptCode as deptCode,t.deptAddress as deptAddress,");
		sqlBuffer.append(" t.deptPostNum as deptPostNum,t.deptPhone as deptPhone,t.orderNum as orderNum,t.deptKindCode as deptKindCode,t.treepath as treepath,");
		sqlBuffer.append(" t.parentID as parentID,t.reportDeptID as reportDeptID,t.activeState as activeState");
		sqlBuffer.append(" from Department t where t.activeState = ?");
		condition.addParameter(Department.IS_ACTIVE_Y);
		if (condition.getNode() != null && !"".equals(condition.getNode())) {
			sqlBuffer.append(" and t.parentID= ?");
			condition.addParameter(condition.getNode());
		}
		sqlBuffer.append(" order by t.orderNum");
		condition.setSql(sqlBuffer.toString());
		return condition;
	}

	/**
	 * 根据条件查询所有机构
	 * 
	 * @param condition
	 * @return
	 * @author XiangBin
	 * @createDate 2012-8-2
	 */
	public DepartmentTreeCondition listDepartmentProjectTreeHQL(DepartmentTreeCondition condition) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select t from Department t where t.activeState = ?");
		condition.addParameter(Department.IS_ACTIVE_Y);

		if (condition.getSearchTreepath() != null && !"".equals(condition.getSearchTreepath())) {
			sqlBuffer.append(" and t.treepath like ?");
			condition.addParameter(condition.getSearchTreepath() + "%");
		}

		sqlBuffer.append(" order by t.orderNum");
		condition.setSql(sqlBuffer.toString());
		return condition;
	}
}
