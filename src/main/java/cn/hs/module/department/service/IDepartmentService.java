package cn.hs.module.department.service;

import java.util.List;

import cn.hs.core.servicetemplate.pagetemplate.IBasePageTemplate;
import cn.hs.module.department.domain.Department;
import cn.hs.module.department.domain.DepartmentCondition;

/**
 * Title: IDepartmentService<br>
 * Description: 部门管理业务接口<br>
 * Company: goldgov<br>
 * Copyright @ 2012 goldgov .All rights reserved.<br>
 * 
 * @author Mill
 * @createDate 2012-08-01
 * @version $Revision$
 */
public interface IDepartmentService extends IBasePageTemplate {
	/**
	 * 新增部门
	 * 
	 * @throws Exception
	 * @author Mill
	 * @date 2012-08-01
	 */
	public void addDepartment(Department department) throws Exception;

	/**
	 * 根据ID查询部门
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @date 2012-08-01
	 */
	public Department getDepartment(DepartmentCondition condition) throws Exception;

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
	 * 根据ID数组批量作废或启用部门
	 * 
	 * @param department
	 * @throws Exception
	 * @author Mill
	 * @date 2012-08-01
	 */
	public void discardOrReuseDepartment(DepartmentCondition condition) throws Exception;

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

	public List<Department> findList(DepartmentCondition condition) throws Exception;

	/**
	 * 检查是否包含活动的子部门
	 * 
	 * @param condition
	 * @return 包含：true 不包含：false
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 6, 2012
	 */
	public boolean checkIsNotExistsDepartment(DepartmentCondition condition) throws Exception;

	/**
	 * 判断部门性质类别编码是否重复
	 * 
	 * @param department
	 * @return 不重复:true 重复:false
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 6, 2012
	 */
	public boolean checkDeptKindCodeIsRepeat(Department department) throws Exception;

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
