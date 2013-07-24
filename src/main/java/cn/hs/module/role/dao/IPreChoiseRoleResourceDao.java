package cn.hs.module.role.dao;

import java.util.List;

import cn.hs.module.role.domain.RoleResource;
import cn.hs.module.role.domain.RoleResourceCondition;

/**
 * 角色DAO接口 Title: IRoleDao<br>
 * Description: <br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Sep 5, 2011
 * @version $Revision: 62 $
 */
public interface IPreChoiseRoleResourceDao {

	/**
	 * 查询角色总数
	 * 
	 * @param condition
	 * @return
	 * @author HuangS
	 * @date Sep 5, 2011
	 */
	public Long countAuthorityRole(RoleResourceCondition condition) throws Exception;

	/**
	 * 查询角色list
	 * 
	 * @param condition
	 * @return
	 * @author HuangS
	 * @date Sep 5, 2011
	 */
	public List<RoleResource> listAuthorityRole(RoleResourceCondition condition) throws Exception;

	public void addAuthorityRoleResource(RoleResourceCondition condition) throws Exception;

	/**
	 * 查询ID是否存在
	 * 
	 * @param condition
	 * @return
	 * @author YaoSC
	 * @throws Exception
	 * @date Oct 31, 2011
	 */
	public List<RoleResource> getResource(RoleResourceCondition condition) throws Exception;

}
