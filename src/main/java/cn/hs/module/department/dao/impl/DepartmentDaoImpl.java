package cn.hs.module.department.dao.impl;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.hs.core.basedao.base.IBaseDao;
import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.module.department.dao.IDepartmentDao;
import cn.hs.module.department.dao.require.DepartmentRequire;
import cn.hs.module.department.domain.Department;
import cn.hs.module.department.domain.DepartmentCondition;

/**
 * Title: DepartmentDaoImpl<br>
 * Description: 部门数据接口实现<br>
 * Company: goldgov<br>
 * Copyright @ 2012 goldgov .All rights reserved.<br>
 * 
 * @author Mill
 * @createDate 2012-08-01
 * @version $Revision$
 */
@Repository(value = "cn.hs.module.department.dao.impl.DepartmentDaoImpl")
public class DepartmentDaoImpl implements IDepartmentDao {
	// 初始化baseDao
	@Autowired
	private IBaseDao<Department> baseDao;
	// 初始化departmentRequire
	@Autowired
	private DepartmentRequire departmentRequire;

	/**
	 * 根据ID数组批量作废或启用部门
	 * 
	 * @param condition
	 * @throws Exception
	 * @author Mill
	 * @date 2012-08-01
	 */
	@Override
	public void discardOrReuseDepartment(DepartmentCondition condition) throws Exception {
		baseDao.executeUpdate(departmentRequire.discardOrReuseDepartmentHQL(condition));
	}

	/**
	 * 根据Id查询部门
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @date 2012-08-01
	 */
	@Override
	public Department getDepartment(DepartmentCondition condition) throws Exception {
		return baseDao.findObject(Department.class, condition.getSearchDepID());
	}

	/**
	 * 查询部门集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @date 2012-08-01
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Department> getDepartmentList(DepartmentCondition condition) throws Exception {
		departmentRequire.listDepartment(condition);

		return (List<Department>) baseDao.pagedQuery(condition);
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
	public void updateDepartment(Department department) throws Exception {
		baseDao.updateEntityByPK(department);
	}

	/**
	 * 添加部门
	 * 
	 * @param department
	 * @throws Exception
	 * @author Mill
	 * @date 2012-08-01
	 */
	@Override
	public void addDepartment(Department department) throws Exception {
		baseDao.addEntity(department);
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
		departmentRequire.listDepartment(condition);
		List<?> result = baseDao.find(condition);
		if (result != null && result.size() > 0) {
			return (Department) result.get(0);
		}
		return null;
	}

	/**
	 * 查询部门集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @date 2012-08-01
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Department> listDepartment(DepartmentCondition condition) throws Exception {
		departmentRequire.listDepartment(condition);
		List<Department> list = (List<Department>) baseDao.pagedQuery(condition);
		return list;
	}

	/**
	 * count部门类型集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @date 2012-08-01
	 */
	@Override
	public Long listDepartmentCount(DepartmentCondition condition) throws Exception {
		departmentRequire.countDepartmentHql(condition);
		return baseDao.countQuery(condition);
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
		Department department = baseDao.findObject(Department.class, id);
		baseDao.getHibernateSession().clear();
		return department == null ? new Department() : department;
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
	@SuppressWarnings("unchecked")
	@Override
	public List<Department> getDepartmentListByIDArray(String[] idArray) throws Exception {
		baseDao.getHibernateSession().clear();
		BaseCondition condition = departmentRequire.getDepartmentListByIDArrayHQL(idArray);
		return (List<Department>) baseDao.find(condition);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> getAllDepartmentByDeptID(DepartmentCondition condition) throws Exception {
		departmentRequire.queryDepartmentByDeptID(condition);
		return (List<Department>) baseDao.find(condition);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> getDepartmentTreeByCode(DepartmentCondition condition) throws Exception {
		departmentRequire.queryByDeptKindCode(condition);
		return (List<Department>) baseDao.find(condition);
	}

	@Override
	public void sysDeptWithSys(List<Department> deptList) throws Exception {
		Connection conn = null;
		Statement stat = null;
		try {
			conn = baseDao.getJDBCConnection();
			stat = conn.createStatement();
			for (Department department : deptList) {
				String sql = departmentRequire.insertDeptSQL(department);
				stat.executeUpdate(sql);
			}
		} finally {
			baseDao.releaseJDBCConnection(null, stat, conn);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> getDeptByDeptNames(String[] deptNames) throws Exception {
		DepartmentCondition condition = departmentRequire.getDeptByDeptNames(deptNames);
		return (List<Department>) baseDao.find(condition);
	}

}
