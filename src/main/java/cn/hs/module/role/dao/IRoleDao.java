package cn.hs.module.role.dao;

import java.util.List;

import cn.hs.module.role.domain.Role;
import cn.hs.module.role.domain.RoleCondition;
import cn.hs.module.role.domain.querybena.RoleTypeDataBean;

/**
 * Title: IRoleDao<br>
 * Description: 角色DAO接口 <br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Sep 5, 2011
 * @version $Revision: 62 $
 */
public interface IRoleDao {

	/**
	 * 查询角色总数
	 * 
	 * @param condition
	 * @return
	 * @author HuangS
	 * @date Sep 5, 2011
	 */
	public Long countRole(RoleCondition condition) throws Exception;

	/**
	 * 查询角色list
	 * 
	 * @param condition
	 * @return
	 * @author HuangS
	 * @date Sep 5, 2011
	 */
	public List<RoleTypeDataBean> listRole(RoleCondition condition) throws Exception;

	/**
	 * 根据ID查询角色
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 5, 2011
	 */
	public Role getRole(RoleCondition condition) throws Exception;

	/**
	 * 添加角色
	 * 
	 * @param role
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 5, 2011
	 */
	public void addRole(Role role) throws Exception;

	/**
	 * 更新角色
	 * 
	 * @param role
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 5, 2011
	 */
	public void updateRole(Role role) throws Exception;

	/**
	 * 根据角色编码查询角色信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 9, 2011
	 */
	public List<Role> getRoleByRoleCode(RoleCondition condition) throws Exception;

}
