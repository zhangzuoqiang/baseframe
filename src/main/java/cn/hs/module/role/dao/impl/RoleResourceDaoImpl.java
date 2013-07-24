package cn.hs.module.role.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.hs.core.basedao.base.IBaseDao;
import cn.hs.module.role.dao.IRoleResourceDao;
import cn.hs.module.role.dao.require.RoleResourceRequire;
import cn.hs.module.role.domain.RoleResourceCondition;
import cn.hs.module.role.domain.querybena.RoleResourceMenuBean;

/**
 * Title: RoleResourceDaoImpl<br>
 * Description: 角色-资源DAO实现 <br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Sep 13, 2011
 * @version $Revision: 62 $
 */
@Repository(value = "cn.hs.module.role.dao.impl.RoleResourceDaoImpl")
public class RoleResourceDaoImpl implements IRoleResourceDao {

	@Autowired
	private IBaseDao<?> baseDao;

	@Autowired
	private RoleResourceRequire require;

	/**
	 * 根据角色ID得到相应的角色-资源
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 13, 2011
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RoleResourceMenuBean> getRoleResourceByRoleId(RoleResourceCondition condition) throws Exception {
		require.queryRoleResourceByRoleIDHQL(condition);
		condition.setBasebean(RoleResourceMenuBean.class);
		// 不翻页
		condition.setRows(-1);
		condition.setStart(-1);
		return (List<RoleResourceMenuBean>) baseDao.pagedQuery(condition);
	}

}
