package cn.hs.module.role.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.hs.core.basedao.base.IBaseDao;
import cn.hs.module.role.dao.IPreChoiseRoleResourceDao;
import cn.hs.module.role.dao.require.ChoisedRoleResourceRequire;
import cn.hs.module.role.domain.RoleResource;
import cn.hs.module.role.domain.RoleResourceCondition;
import cn.hs.module.role.domain.querybena.ChoisedRoleResourceBean;

/**
 * 角色DAO实现 Title: PreChoiseRoleResourceDaoImpl<br>
 * Description: <br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author YaoSC
 * @createDate Sep 5, 2011
 * @version $Revision: 90 $
 */
@Repository(value = "cn.hs.module.role.dao.impl.PreChoiseRoleResourceDaoImpl")
public class PreChoiseRoleResourceDaoImpl implements IPreChoiseRoleResourceDao {

	@Autowired
	private IBaseDao<RoleResource> baseDao;

	@Autowired
	private ChoisedRoleResourceRequire require;

	/**
	 * 查询角色总数
	 * 
	 * @param condition
	 * @return
	 * @author YaoSC
	 * @date Sep 5, 2011
	 */
	public Long countAuthorityRole(RoleResourceCondition condition) throws Exception {
		require.countAuthorityRoleHQL(condition);
		return baseDao.countQuery(condition);
	}

	/**
	 * 查询角色list
	 * 
	 * @param condition
	 * @return
	 * @author YaoSC
	 * @date Sep 5, 2011
	 */
	@SuppressWarnings("unchecked")
	public List<RoleResource> listAuthorityRole(RoleResourceCondition condition) throws Exception {
		require.listAuthorityRoleHQL(condition);
		condition.setBasebean(ChoisedRoleResourceBean.class);
		return (List<RoleResource>) baseDao.pagedQuery(condition);
	}

	/**
	 * 批量添加
	 * 
	 * @param condition
	 * @throws Exception
	 * @author YaoSC
	 * @date Oct 31, 2011
	 */
	@Override
	public void addAuthorityRoleResource(RoleResourceCondition condition) throws Exception {
		baseDao.batchAddEntities(condition.getRoleResoure());
	}

	/**
	 * 得到中间表中资源集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author YaoSC
	 * @date Oct 31, 2011
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RoleResource> getResource(RoleResourceCondition condition) throws Exception {
		require.listResourceHQL(condition);
		condition.setRows(-1);
		condition.setStart(-1);
		return (List<RoleResource>) baseDao.pagedQuery(condition);
	}

}
