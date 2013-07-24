/*
 * $Log: ChoisedRoleResourceDaoImpl.java,v $
 * Revision 1.1  2012/05/23 09:27:55  guor
 * 初次提交
 *
 */
package cn.hs.module.role.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.hs.core.basedao.base.IBaseDao;
import cn.hs.module.role.dao.IChoisedRoleResourceDao;
import cn.hs.module.role.domain.RoleResourceCondition;

/**
 * Title: ChoisedRoleResourceDaoImpl<br>
 * Description: <br>
 * Company: GOLDGOV<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author YaoSC
 * @createDate Sep 16, 2011
 * @version $Revision: 90 $
 */
@Repository(value = "cn.hs.module.role.dao.impl.ChoisedRoleResourceDaoImpl")
public class ChoisedRoleResourceDaoImpl implements IChoisedRoleResourceDao {
	@SuppressWarnings("unchecked")
	@Autowired
	private IBaseDao baseDao;

	/**
	 * @param condition
	 * @throws Exception
	 * @author YaoSC
	 * @date Sep 16, 2011
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void delAllroleResource(RoleResourceCondition condition) throws Exception {
		baseDao.batchDeleteEntities(condition.getRoleResoure());
	}

}
