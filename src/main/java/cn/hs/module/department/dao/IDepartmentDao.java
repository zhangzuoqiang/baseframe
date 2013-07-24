package cn.hs.module.department.dao;

import java.util.List;

import cn.hs.module.department.domain.Department;
import cn.hs.module.department.domain.DepartmentCondition;

/**
 * Title: IDepartmentDao<br>
 * Description: 部门数据接口<br>
 * Company: goldgov<br>
 * Copyright @ 2012 goldgov .All rights reserved.<br>
 * 
 * @author Mill
 * @createDate 2012-08-01
 * @version $Revision$
 */
public interface IDepartmentDao {
	/**
	 * 新增部门
	 * 
	 * @param department
	 * @throws Exception
	 * @author Mill
	 * @date 2012-08-01
	 */
	public void addDepartment(Department department) throws Exception;

	/**
	 * 根据ID数组批量作废或启用部门
	 * 
	 * @param condition
	 * @throws Exception
	 * @author Mill
	 * @date 2012-08-01
	 */
	public void discardOrReuseDepartment(DepartmentCondition condition) throws Exception;

	/**
	 * 根据Id查询部门
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @date 2012-08-01
	 */
	public Department getDepartment(DepartmentCondition condition) throws Exception;

	/**
	 * 查询部门集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @date 2012-08-01
	 */
	public List<Department> getDepartmentList(DepartmentCondition condition) throws Exception;

	/**
	 * 更新部门
	 * 
	 * @param department
	 * @throws Exception
	 * @author Mill
	 * @date 2012-08-01
	 */
	public void updateDepartment(Department department) throws Exception;

	/**
	 * 根据部门编码查询部门信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @date 2012-08-01
	 */
	public Department viewDepartment(DepartmentCondition condition) throws Exception;

	/**
	 * 查询部门集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @date 2012-08-01
	 */
	public List<Department> listDepartment(DepartmentCondition condition) throws Exception;

	/**
	 * count查询部门集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @date 2012-08-01
	 */
	public Long listDepartmentCount(DepartmentCondition condition) throws Exception;

	/**
	 * 根据ID数组获得部门 批量操作时记录业务日志必须实现该方法，该方法将返回批量操作前数据内容
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @date 2012-08-01
	 */
	public List<Department> getDepartmentListByIDArray(String[] idArray) throws Exception;

	/**
	 * 根据ID获得部门 更新操作时记录业务日志必须实现该方法，该方法将返回更新操作前数据内容
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @date 2012-08-01
	 */
	public Department getDepartmentByID(String id) throws Exception;

	/**
	 * 根据deptID查询该ID下的所有子机构
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @createDate 2012-8-2
	 */
	public List<Department> getAllDepartmentByDeptID(DepartmentCondition condition) throws Exception;

	/**
	 * 根据Code查询机构信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @createDate 2012-8-2
	 */
	public List<Department> getDepartmentTreeByCode(DepartmentCondition condition) throws Exception;

	/**
	 * 同步部门
	 * 
	 * @param deptList
	 * @throws Exception
	 * @author HuangS
	 * @date May 19, 2013
	 */
	public void sysDeptWithSys(List<Department> deptList) throws Exception;

	/**
	 * 根据部门名称数组查询部门
	 * 
	 * @param deptNames
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date 2013-7-21
	 */
	public List<Department> getDeptByDeptNames(String[] deptNames) throws Exception;

}
