package cn.hs.module.department.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import cn.hs.core.basedao.base.IBaseDao;
import cn.hs.core.servicetemplate.treetemplate.jsonbean.jsonnodeparameters.UpdateOrderParameter;
import cn.hs.module.department.dao.IDepartmentTreeDao;
import cn.hs.module.department.dao.require.DepartmentTreeRequire;
import cn.hs.module.department.domain.Department;
import cn.hs.module.department.domain.DepartmentTreeCondition;
import cn.hs.module.department.domain.jsonbean.DepartmentTreeJsonBean;

/**
 * 
 * Title: DepartmentTreeDaoImpl<br>
 * Description: 部门树dao实现<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author Mill
 * @createDate 2012-8-2
 * @version $Revision:$
 */
@Repository(value = "cn.hs.module.department.dao.impl.DepartmentTreeDaoImpl")
public class DepartmentTreeDaoImpl implements IDepartmentTreeDao {

	@Autowired
	private DepartmentTreeRequire departmentTreeRequire;

	@Autowired
	private IBaseDao<Department> baseDao;

	@Override
	public boolean isLeapNode(DepartmentTreeCondition condition) throws Exception {
		boolean result = true;
		departmentTreeRequire.isLeapNode(condition);
		long countQuery = baseDao.countQuery(condition);
		if (countQuery > 0) {
			result = false;
		}
		return result;
	}

	@Override
	public void updateTreeOrderNum(UpdateOrderParameter updateOrderParameter) throws Exception {
		DepartmentTreeCondition condition = departmentTreeRequire.updateTreeOrderNumHQL(updateOrderParameter);
		baseDao.executeUpdate(condition);
	}

	@Override
	public void updateTreeParentID(DepartmentTreeCondition condition) throws Exception {
		departmentTreeRequire.updateTreeParentIDHQL(condition);
		baseDao.executeUpdate(condition);
	}

	@Override
	public List<Department> listDepartmentTree(DepartmentTreeCondition condition) throws Exception {
		departmentTreeRequire.listDepartmentTreeHQL(condition);
		List<Department> list = (List<Department>) baseDao.find(condition);
		return list;
	}

	@Override
	public List<DepartmentTreeJsonBean> listDepartmentCheckedTree(DepartmentTreeCondition condition) throws Exception {
		departmentTreeRequire.listDepartmentCheckedTreeHQL(condition);
		condition.setBasebean(DepartmentTreeJsonBean.class);
		Query query = createQuery(condition.getSql(), condition.getParameterList());
		if (condition.getBasebean() != null) {
			query.setResultTransformer(Transformers.aliasToBean(condition.getBasebean()));
		}
		List<DepartmentTreeJsonBean> list = query.list();
		return list;
	}

	public Query createQuery(String hql, List<Object> parameterList) throws Exception, Exception {
		Assert.hasText(hql);
		Query query = baseDao.getHibernateSession().createQuery(hql);
		if (!parameterList.isEmpty()) {
			for (int i = 0; i < parameterList.size(); i++) {
				query.setParameter(i, parameterList.get(i));
			}
		}
		return query;
	}

	@Override
	public Department getDepartment(DepartmentTreeCondition condition) throws Exception {
		return (Department) baseDao.loadObject(Department.class, condition.getSearchDepID());
	}

	@Override
	public void addDepartmentTree(Department department) throws Exception {
		baseDao.addEntity(department);
		baseDao.getHibernateSession().flush();
	}

	@Override
	public void updateDepartmentTree(Department department) throws Exception {
		baseDao.updateEntityByPK(department);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> listDepartmentProjectTree(DepartmentTreeCondition condition) throws Exception {
		departmentTreeRequire.listDepartmentProjectTreeHQL(condition);
		List<Department> list = (List<Department>) baseDao.find(condition);
		return list;
	}

}
