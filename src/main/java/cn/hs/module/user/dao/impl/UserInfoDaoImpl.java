package cn.hs.module.user.dao.impl;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import cn.hs.core.basedao.base.IBaseDao;
import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.module.department.domain.Department;
import cn.hs.module.user.dao.IUserInfoDao;
import cn.hs.module.user.dao.require.UserInfoRequire;
import cn.hs.module.user.domain.User;
import cn.hs.module.user.domain.UserInfoCondition;

/*******************************************************************************
 * file: RoleInfo.java project: service date: 2007-9-7
 * 
 * @author wangwei description:
 ******************************************************************************/
@Repository(value = "cn.hs.module.user.dao.impl.UserInfoDaoImpl")
public class UserInfoDaoImpl implements IUserInfoDao {

	@Autowired
	private IBaseDao<User> baseDao;

	@Autowired
	private UserInfoRequire require;

	/**
	 * 获取登入的用户信息
	 * 
	 * @param userID
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 */
	@SuppressWarnings("unchecked")
	public List<User> getUserInfo(UserInfoCondition condition) throws UsernameNotFoundException {
		require.queryCheckUserLoginHQL(condition);
		// 不执行翻页
		condition.setStart(-1);
		condition.setRows(-1);
		return (List<User>) baseDao.pagedQuery(condition);
	}

	/**
	 * 获取指定用户所在部门
	 * 
	 * @param userID
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 */
	@SuppressWarnings("unchecked")
	public List<Department> getCurrentDepartment(UserInfoCondition condition) throws Exception {
		require.currentDepartmentHQL(condition);
		// 不执行翻页
		condition.setStart(-1);
		condition.setRows(-1);
		return (List<Department>) baseDao.pagedQuery(condition);
	}

	/**
	 * 用户的管理范围
	 * 
	 * @param userID
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Department> getCurrentManageScope(UserInfoCondition condition) throws Exception {
		require.currentManageScopeHQL(condition);
		// 不执行翻页
		condition.setStart(-1);
		condition.setRows(-1);
		return (List<Department>) baseDao.pagedQuery(condition);
	}

	/**
	 * 根据ID数组批量作废或启用UserService
	 * 
	 * @param condition
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	@Override
	public void discardOrReuseUser(UserInfoCondition condition) throws Exception {
		baseDao.executeUpdate(require.discardOrReuseUserHQL(condition));
	}

	/**
	 * 根据Id查询UserService
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	@Override
	public User getUser(UserInfoCondition condition) throws Exception {
		return (User) baseDao.findObject(User.class, condition.getSearchUserId());
	}

	/**
	 * 查询UserService集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserList(UserInfoCondition condition) throws Exception {
		require.listUser(condition);
		// 设置不分页查询
		// condition.setStart(-1);
		// condition.setRows(-1);
		return (List<User>) baseDao.pagedQuery(condition);

	}

	/**
	 * 更新UserService
	 * 
	 * @param baseUser
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	@Override
	public void updateUser(User baseUser) throws Exception {
		baseDao.updateEntityByPK(baseUser);
	}

	/**
	 * 添加UserService
	 * 
	 * @param baseUser
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	@Override
	public void addUser(User baseUser) throws Exception {
		baseDao.addEntity(baseUser);
	}

	public void sysUserWithSys(List<User> userList) throws Exception {
		Connection conn = null;
		Statement stat = null;
		try {
			conn = baseDao.getJDBCConnection();
			stat = conn.createStatement();
			for (User user : userList) {
				String sql = require.insertUserSQL(user);
				stat.executeUpdate(sql);
			}
		} finally {
			baseDao.releaseJDBCConnection(null, stat, conn);
		}
	}

	/**
	 * 根据UserService编码查询UserService信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	@Override
	public User viewUser(UserInfoCondition condition) throws Exception {
		require.listUser(condition);
		List<?> result = baseDao.find(condition.getSql(), condition.getParameterList().toArray());
		if (result != null && result.size() > 0) {
			return (User) result.get(0);
		}
		return null;
	}

	/**
	 * 查询UserService集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUser(UserInfoCondition condition) throws Exception {
		require.listUser(condition);
		// condition.addParameter(condition.getRows());
		// condition.addParameter(condition.getStart());
		// condition.setBasebean(UserJsonBean.class);
		return (List<User>) baseDao.pagedQuery(condition);
		// return (List<UserJsonBean>) baseDao.pagedQueryJDBC(condition);
	}

	/**
	 * countUserService类型集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	@Override
	public Long listUserCount(UserInfoCondition condition) throws Exception {
		require.countUserHql(condition);
		return baseDao.countQuery(condition);
	}

	/**
	 * 根据ID获得UserService 更新操作时记录业务日志必须实现该方法，该方法将返回更新操作前数据内容
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	@Override
	public User getUserByID(String id) throws Exception {
		User baseUser = baseDao.findObject(User.class, id);
		baseDao.getHibernateSession().clear();
		return baseUser == null ? new User() : baseUser;
	}

	/**
	 * 根据ID数组获得UserService 批量操作时记录业务日志必须实现该方法，该方法将返回批量操作前数据内容
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserListByIDArray(String[] idArray) throws Exception {
		baseDao.getHibernateSession().clear();
		BaseCondition condition = require.getUserListByIDArrayHQL(idArray);
		return (List<User>) baseDao.find(condition.getSql(), condition.getParameterList().toArray());
	}

}
