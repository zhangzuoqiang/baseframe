package cn.hs.module.department.dao;

import java.util.List;

import cn.hs.core.servicetemplate.treetemplate.jsonbean.jsonnodeparameters.UpdateOrderParameter;
import cn.hs.module.department.domain.Department;
import cn.hs.module.department.domain.DepartmentTreeCondition;
import cn.hs.module.department.domain.jsonbean.DepartmentTreeJsonBean;

/**
 * 
 * Title: IDepartmentTreeDao<br>
 * Description: 部门树dao接口<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author Mill
 * @createDate 2012-8-2
 * @version $Revision:$
 */
public interface IDepartmentTreeDao {

	/**
	 * 是否为叶子节点
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @createDate 2012-8-1
	 */
	public boolean isLeapNode(DepartmentTreeCondition condition) throws Exception;

	/**
	 * 更新节点排序
	 * 
	 * @param updateOrderParameter
	 * @throws Exception
	 * @author Mill
	 * @createDate 2012-8-1
	 */
	public void updateTreeOrderNum(UpdateOrderParameter updateOrderParameter) throws Exception;

	/**
	 * 更新节点父ID
	 * 
	 * @param condition
	 * @throws Exception
	 * @author Mill
	 * @createDate 2012-8-1
	 */
	public void updateTreeParentID(DepartmentTreeCondition condition) throws Exception;

	/**
	 * 查询机构列表
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @createDate 2012-8-2
	 */
	public List<Department> listDepartmentTree(DepartmentTreeCondition condition) throws Exception;

	/**
	 * 查询机构列表
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @createDate 2012-8-2
	 */
	public List<DepartmentTreeJsonBean> listDepartmentCheckedTree(DepartmentTreeCondition condition) throws Exception;

	/**
	 * 根据ID查询部门信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @createDate 2012-8-2
	 */
	public Department getDepartment(DepartmentTreeCondition condition) throws Exception;

	/**
	 * 添加部门信息
	 * 
	 * @param department
	 * @throws Exception
	 * @author Mill
	 * @createDate 2012-8-2
	 */
	public void addDepartmentTree(Department department) throws Exception;

	/**
	 * 修改部门信息
	 * 
	 * @param department
	 * @throws Exception
	 * @author Mill
	 * @createDate 2012-8-2
	 */
	public void updateDepartmentTree(Department department) throws Exception;

	/**
	 * 项目参加的部门树
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @createDate 2012-8-2
	 */
	public List<Department> listDepartmentProjectTree(DepartmentTreeCondition condition) throws Exception;
}
