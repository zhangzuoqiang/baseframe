package cn.hs.module.role.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.hs.core.basedao.base.IBaseDao;
import cn.hs.module.role.dao.IRoleDao;
import cn.hs.module.role.dao.require.RoleRequire;
import cn.hs.module.role.domain.Role;
import cn.hs.module.role.domain.RoleCondition;
import cn.hs.module.role.domain.querybena.RoleTypeDataBean;

/**
 * Title: RoleDaoImpl<br>
 * Description: 角色DAO实现 <br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Sep 5, 2011
 * @version $Revision: 62 $
 */
@Repository(value = "cn.hs.module.role.dao.impl.RoleDaoImpl")
public class RoleDaoImpl implements IRoleDao {

	@Autowired
	private IBaseDao<Role> baseDao;

	@Autowired
	private RoleRequire require;

	/**
	 * 查询角色总数
	 * 
	 * @param condition
	 * @return
	 * @author HuangS
	 * @date Sep 5, 2011
	 */
	public Long countRole(RoleCondition condition) throws Exception {
		require.countRoleHQL(condition);
		return baseDao.countQuery(condition);
	}

	/**
	 * 查询角色list
	 * 
	 * @param condition
	 * @return
	 * @author HuangS
	 * @date Sep 5, 2011
	 */
	@SuppressWarnings("unchecked")
	public List<RoleTypeDataBean> listRole(RoleCondition condition) throws Exception {
		require.listRoleHQL(condition);
		condition.setBasebean(RoleTypeDataBean.class);
		return (List<RoleTypeDataBean>) baseDao.pagedQuery(condition);
	}

	/**
	 * 根据ID查询角色
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 5, 2011
	 */
	public Role getRole(RoleCondition condition) throws Exception {
		return (Role) baseDao.findObject(Role.class, condition.getQueryRoleID());
	}

	/**
	 * 添加角色
	 * 
	 * @param role
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 5, 2011
	 */
	public void addRole(Role role) throws Exception {
		baseDao.addEntity(role);
	}

	/**
	 * 更新角色
	 * 
	 * @param role
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 5, 2011
	 */
	public void updateRole(Role role) throws Exception {
		baseDao.updateEntityByPK(role);
	}

	/**
	 * 根据角色编码查询角色信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 9, 2011
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRoleByRoleCode(RoleCondition condition) throws Exception {
		require.queryRoleByRoleCodeHQL(condition);
		// 不翻页
		condition.setStart(-1);
		condition.setRows(-1);
		return (List<Role>) baseDao.pagedQuery(condition);
	}

}
