package cn.hs.module.department.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.hs.core.basedao.base.IBaseDao;
import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.module.department.dao.IDepartmentUserDao;
import cn.hs.module.department.dao.require.DepartmentUserRequire;
import cn.hs.module.department.domain.DepartmentUser;
import cn.hs.module.department.domain.DepartmentUserCondition;

/**
 * Title: DepartmentUserDaoImpl<br>
 * Description: 部门用户数据接口实现<br>
 * Company: GoldGov<br>
 * Copyright @ 2012 GoldGov .All rights reserved.<br>
 * @author Limk
 * @createDate 2012-08-13
 * @version $Revision$
 */
@Repository(value = "cn.hs.module.department.dao.impl.DepartmentUserDaoImpl")
public class DepartmentUserDaoImpl implements IDepartmentUserDao {
	// 初始化baseDao
	@Autowired
	private IBaseDao<DepartmentUser> baseDao;
	// 初始化departmentUserRequire
	@Autowired
	private DepartmentUserRequire departmentUserRequire;

	/**
	 * 根据ID数组批量作废或启用部门用户
	 * 
	 * @param condition
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-13
	 */
	@Override
	public void discardOrReuseDepartmentUser(DepartmentUserCondition condition) throws Exception {
		baseDao.executeUpdate(departmentUserRequire.discardOrReuseDepartmentUserHQL(condition));
	}

	/**
	 * 根据部门用户查询部门用户
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-13
	 */
	@Override
	public DepartmentUser getDepartmentUser(DepartmentUserCondition condition) throws Exception {
		departmentUserRequire.listDepartmentUser(condition);
		List<?> result = baseDao.find(condition.getSql(), condition.getParameterList().toArray());
		if (result != null && result.size() > 0) {
			return (DepartmentUser) result.get(0);
		}
		return null;
	}

	/**
	 * 查询部门用户集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-13
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DepartmentUser> getDepartmentUserList(DepartmentUserCondition condition) throws Exception {
		departmentUserRequire.listDepartmentUser(condition);
		// 设置不分页查询
		condition.setStart(-1);
		condition.setRows(-1);
		return (List<DepartmentUser>) baseDao.pagedQuery(condition);
	}

	/**
	 * 更新部门用户
	 * 
	 * @param departmentUser
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-13
	 */
	@Override
	public void updateDepartmentUser(DepartmentUser departmentUser) throws Exception {
		baseDao.updateEntityByPK(departmentUser);
	}

	/**
	 * 添加部门用户
	 * 
	 * @param departmentUser
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-13
	 */
	@Override
	public void addDepartmentUser(DepartmentUser departmentUser) throws Exception {
		baseDao.addEntity(departmentUser);
	}

	/**
	 * 查询部门用户集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-13
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DepartmentUser> listDepartmentUser(DepartmentUserCondition condition) throws Exception {
		departmentUserRequire.listDepartmentUser(condition);
		return (List<DepartmentUser>) baseDao.pagedQuery(condition);
	}

	/**
	 * count部门用户类型集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-13
	 */
	@Override
	public Long listDepartmentUserCount(DepartmentUserCondition condition) throws Exception {
		departmentUserRequire.countDepartmentUserHql(condition);
		return baseDao.countQuery(condition);
	}

	/**
	 * 根据ID获得部门用户 更新操作时记录业务日志必须实现该方法，该方法将返回更新操作前数据内容
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-13
	 */
	@Override
	public DepartmentUser getDepartmentUserByID(String id) throws Exception {
		DepartmentUser departmentUser = baseDao.findObject(DepartmentUser.class, id);
		baseDao.getHibernateSession().clear();
		return departmentUser == null ? new DepartmentUser() : departmentUser;
	}

	/**
	 * 根据ID数组获得部门用户 批量操作时记录业务日志必须实现该方法，该方法将返回批量操作前数据内容
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-13
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DepartmentUser> getDepartmentUserListByIDArray(String[] idArray) throws Exception {
		baseDao.getHibernateSession().clear();
		BaseCondition condition = departmentUserRequire.getDepartmentUserListByIDArrayHQL(idArray);
		return (List<DepartmentUser>) baseDao.find(condition.getSql(), condition.getParameterList().toArray());
	}

}
