package cn.hs.module.role.service;

import java.util.List;

import cn.hs.core.servicetemplate.pagetemplate.IBasePageTemplate;
import cn.hs.module.role.domain.Role;
import cn.hs.module.role.domain.RoleUser;
import cn.hs.module.role.domain.RoleUserCondition;
import cn.hs.module.user.domain.User;

/**
 * Title: IRoleUserService<br>
 * Description: 角色-用户service接口<br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author XiangBin
 * @createDate Aug 1, 2012
 * @version $Revision:$
 */
public interface IRoleUserService  extends IBasePageTemplate{

	/**
	 * 根据用户ID得到相应的角色-用户
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @date Aug 1, 2012
	 */
	public List<Role> getRoleUserByUserId(RoleUserCondition condition) throws Exception;
	
	/**
	 * 添加得到相应的角色-用户
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @date Aug 1, 2012
	 */
	public void addRoleUser(RoleUser roleUser) throws Exception;
	
	/**
	 * 根据condition删除角色-用户
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @date Aug 1, 2012
	 */
	public void deleteRoleUser(RoleUserCondition condition) throws Exception;
	
	/**
	 * 根据角色id查询人员列表
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 * @author MaBin
	 * @date 2012-9-18 16:12:41
	 */
	public List<User> listUserByRoleId(RoleUserCondition condition) throws Exception;
	
	/**
	 * 批量启用或作废用户
	 * 
	 * @param condition
	 * @throws Exception
	 * @author MaBin
	 * @date 2012-10-14 16:05:19
	 */
	public void discardOrReuseBaseUser(RoleUserCondition condition) throws Exception;
}
