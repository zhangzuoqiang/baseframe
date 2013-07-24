/*
 * $Log: IRoleService.java,v $
 * Revision 1.1  2012/05/23 09:27:55  guor
 * 初次提交
 *
 */
package cn.hs.module.role.service;

import java.util.List;

import cn.hs.core.servicetemplate.pagetemplate.IBasePageTemplate;
import cn.hs.module.role.domain.Role;
import cn.hs.module.role.domain.RoleCondition;

/**
 * Title: IRoleService<br>
 * Description: 角色service接口<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 23, 2011
 * @version $Revision: 62 $
 */
public interface IRoleService extends IBasePageTemplate {

	/**
	 * 新增角色
	 * 
	 * @param role
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 23, 2011
	 */
	public void addRole(Role role) throws Exception;

	/**
	 * 根据ID查询角色信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 23, 2011
	 */
	public Role getRole(RoleCondition condition) throws Exception;

	/**
	 * 更新角色
	 * 
	 * @param role
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 23, 2011
	 */
	public void updateRole(Role role) throws Exception;

	/**
	 * 根据ID集合批量作废或启用角色
	 * 
	 * @param condition
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 23, 2011
	 */
	public void discardOrReuseRole(RoleCondition condition) throws Exception;

	/**
	 * 新增和更新数据类别前，查看类别代码是否有重复
	 * 
	 * @param role
	 * @return 不含重复角色编码返回true
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 9, 2011
	 */
	public boolean checkRoleCodeIsRepeat(Role role) throws Exception;
	
	/**
	 * 根据角色编码查询角色信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 13, 2011
	 */
	public List<Role> getRoleByRoleCode(RoleCondition condition) throws Exception;
}
