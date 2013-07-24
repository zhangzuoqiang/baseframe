package cn.hs.module.role.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.hs.core.basedao.base.IBaseDao;
import cn.hs.module.role.dao.IRoleUserDao;
import cn.hs.module.role.dao.require.RoleUserRequire;
import cn.hs.module.role.domain.Role;
import cn.hs.module.role.domain.RoleUser;
import cn.hs.module.role.domain.RoleUserCondition;
import cn.hs.module.role.domain.jsonbean.UserInfoJsonBean;

/**
 * Title: RoleResourceDaoImpl<br>
 * Description: 角色-资源DAO实现 <br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Sep 13, 2011
 * @version $Revision: 62 $
 */
@Repository(value = "cn.hs.module.role.dao.impl.RoleUserDaoImpl")
public class RoleUserDaoImpl implements IRoleUserDao {

	@Autowired
	private IBaseDao<RoleUser> baseDao;

	@Autowired
	private RoleUserRequire require;

	/**
	 * 根据用户ID得到相应的角色用户
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Xiangb
	 * @date Sep 13, 2011
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRoleUserByUserId(RoleUserCondition condition) throws Exception {
		require.queryRoleUserByUserIDHQL(condition);
		return (List<Role>) baseDao.pagedQuery(condition);
	}

	/**
	 * 添加得到相应的角色-用户
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @date Aug 1, 2012
	 */
	public void addRoleUser(RoleUser roleUser) throws Exception {
		baseDao.addEntity(roleUser);
	}

	/**
	 * 根据condition删除角色-用户
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @date Aug 1, 2012
	 */
	@Override
	public void deleteRoleUser(RoleUserCondition condition) throws Exception {
		require.deleteRoleUserHQL(condition);
		baseDao.executeUpdate(condition);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoleUser> listRoleUserByRoleId(RoleUserCondition condition) throws Exception {
		condition = require.listUserForRole(condition);
		List<RoleUser> list = (List<RoleUser>) baseDao.pagedQuery(condition);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfoJsonBean> listRoleUserByRoleIdJDBC(RoleUserCondition condition) throws Exception {
		condition = require.listUserInfoJDBC(condition);
		condition.setBasebean(UserInfoJsonBean.class);
		List<UserInfoJsonBean> list = (List<UserInfoJsonBean>) baseDao.pagedQueryJDBC(condition);
		return list;
	}

	@Override
	public Long countRoleUser(RoleUserCondition condition) throws Exception {
		require.countUserForRole(condition);
		condition.setBasebean(UserInfoJsonBean.class);
		return baseDao.countQueryJDBC(condition);
	}

	@Override
	public void discardOrReuseBaseUser(RoleUserCondition condition) throws Exception {
		baseDao.executeUpdate(require.discardOrReuseBaseUserHQL(condition));
	}
}
