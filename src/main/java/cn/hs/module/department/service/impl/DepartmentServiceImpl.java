package cn.hs.module.department.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hs.core.annotation.BusinessLogForAdd;
import cn.hs.core.annotation.BusinessLogForBatchUpdateState;
import cn.hs.core.annotation.CreateInfo;
import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.core.page.commond.BasePageCommond;
import cn.hs.core.page.commond.JSONObject;
import cn.hs.core.servicetemplate.pagetemplate.PageTemplate;
import cn.hs.module.department.dao.IDepartmentDao;
import cn.hs.module.department.domain.Department;
import cn.hs.module.department.domain.DepartmentCondition;
import cn.hs.module.department.service.IDepartmentService;

/**
 * Title: DepartmentServiceImpl<br>
 * Description: 部门管理业务接口实现<br>
 * Company: goldgov<br>
 * Copyright @ 2012 goldgov .All rights reserved.<br>
 * 
 * @author Mill
 * @createDate 2012-08-01
 * @version $Revision$
 */
// 声明本类为Server实现类，声明bean名称
@Service(value = "cn.hs.module.department.service.impl.DepartmentServiceImpl")
public class DepartmentServiceImpl extends PageTemplate implements IDepartmentService {

	@Autowired
	private IDepartmentDao departmentDao;

	/**
	 * 新增部门
	 * 
	 * @throws Exception
	 * @author Mill
	 * @date 2012-08-01
	 */
	@Override
	@BusinessLogForAdd(operationModule = "新增部门")
	@CreateInfo(createTimeMethod = "setCreateTime", creatorMethod = "setCreator")
	public void addDepartment(Department department) throws Exception {
		departmentDao.addDepartment(department);
		// 拼装treepath
		DepartmentCondition condition = new DepartmentCondition();
		condition.setSearchDepID(department.getParentID());
		Department parentDept = departmentDao.getDepartment(condition);
		String parentTreePath = parentDept.getTreepath();
		String treePath = parentTreePath + department.getDeptID() + "/";
		department.setTreepath(treePath);
	}

	/**
	 * 根据ID数组批量作废或启用部门
	 * 
	 * @param department
	 * @throws Exception
	 * @author Mill
	 * @date 2012-08-01
	 */
	@Override
	@BusinessLogForBatchUpdateState(entityClass = Department.class, getEntityPKMethodName = "getDeptID", getObjectsByIDArrayMethodName = "getDepartmentListByIDArray", getPKArrayMethodName = "getSearchDepIDs", operationModule = "启用作废部门", pkArrayClass = String[].class)
	public void discardOrReuseDepartment(DepartmentCondition condition) throws Exception {
		departmentDao.discardOrReuseDepartment(condition);
	}

	/**
	 * 根据ID查询部门
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @date 2012-08-01
	 */
	@Override
	public Department getDepartment(DepartmentCondition condition) throws Exception {
		return departmentDao.getDepartment(condition);
	}

	/**
	 * 更新部门
	 * 
	 * @param department
	 * @throws Exception
	 * @author Mill
	 * @date 2012-08-01
	 */
	@Override
	// @BusinessLogForUpdate(getEntityPKMethodName = "getDeptID",
	// getObjectByIDMethodName = "getDepartmentByID", operationModule = "更新部门",
	// pkClass = String.class)
	// @CreateInfo(createTimeMethod = "setCreateTime", creatorMethod =
	// "setCreator")
	public void updateDepartment(Department department) throws Exception {
		departmentDao.updateDepartment(department);
	}

	/**
	 * 根据部门编码查询部门信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @date 2012-08-01
	 */
	@Override
	public Department viewDepartment(DepartmentCondition condition) throws Exception {
		return departmentDao.viewDepartment(condition);
	}

	/**
	 * 调用PageTemplate模板process方法
	 * 
	 * @param pageCommond
	 * @param condition
	 * @throws Exception
	 * @author Mill
	 * @date Aug 31, 2011
	 */
	@Override
	public JSONObject doProcess(BasePageCommond pageCommond, BaseCondition condition) throws Exception {
		return process(pageCommond, condition);
	}

	/**
	 * count查询部门集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @date Sep 9, 2011
	 */
	@Override
	protected Long findCount(BaseCondition condition) throws Exception {
		return departmentDao.listDepartmentCount((DepartmentCondition) condition);
	}

	/**
	 * 查询基础数据类型集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @date Sep 9, 2011
	 */
	@Override
	protected List<Department> findList(BaseCondition condition) throws Exception {
		return departmentDao.listDepartment((DepartmentCondition) condition);
	}

	/**
	 * 根据ID获得部门 更新操作时记录业务日志必须实现该方法，该方法将返回更新操作前数据内容
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @date 2012-08-01
	 */
	@Override
	public Department getDepartmentByID(String id) throws Exception {
		return departmentDao.getDepartmentByID(id);
	}

	/**
	 * 根据ID数组获得部门 批量操作时记录业务日志必须实现该方法，该方法将返回批量操作前数据内容
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @date 2012-08-01
	 */
	@Override
	public List<Department> getDepartmentListByIDArray(String[] idArray) throws Exception {
		return departmentDao.getDepartmentListByIDArray(idArray);
	}

	@Override
	public List<Department> findList(DepartmentCondition condition) throws Exception {
		return departmentDao.listDepartment((DepartmentCondition) condition);
	}

	@Override
	public boolean checkIsNotExistsDepartment(DepartmentCondition condition) throws Exception {
		List<Department> departmentList = departmentDao.getAllDepartmentByDeptID(condition);
		if (departmentList != null && departmentList.size() > 0) {
			// 含有子部门
			return true;
		}
		return false;
	}

	@Override
	public boolean checkDeptKindCodeIsRepeat(Department department) throws Exception {
		DepartmentCondition condition = new DepartmentCondition();
		if (department.getDeptID() != null) {
			condition.setSearchDepID(department.getDeptID());
		}
		condition.setSearchDeptCode(department.getDeptCode());
		List<Department> list = departmentDao.getDepartmentTreeByCode(condition);
		if (list != null && list.size() > 0) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional(readOnly = false)
	public void sysDeptWithSys(List<Department> deptList) throws Exception {
		departmentDao.sysDeptWithSys(deptList);
	}

	@Override
	public List<Department> getDeptByDeptNames(String[] deptNames) throws Exception {
		return departmentDao.getDeptByDeptNames(deptNames);
	}

}
