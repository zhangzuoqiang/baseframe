/*
 * $Log: IChoisedRoleResourceDao.java,v $
 * Revision 1.1  2012/05/23 09:27:54  guor
 * 初次提交
 *
 */
package cn.hs.module.role.dao;

import cn.hs.module.role.domain.RoleResourceCondition;

/**
 * Title: IChoisedRoleResourceDao<br>
 * Description: <br>
 * Company: GOLDGOV<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author YaoSC
 * @createDate Sep 16, 2011
 * @version $Revision: 62 $
 */

public interface IChoisedRoleResourceDao {
	/**
	 * 删除角色资源
	 * 
	 * @param condition
	 * @throws Exception
	 * @author YaoSC
	 * @date Aug 23, 2011
	 */
	public void delAllroleResource(RoleResourceCondition condition)
			throws Exception;

}
