package cn.hs.module.role.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.core.page.commond.BasePageCommond;
import cn.hs.core.page.commond.JSONObject;
import cn.hs.core.servicetemplate.pagetemplate.PageTemplate;
import cn.hs.module.role.dao.IRoleDao;
import cn.hs.module.role.domain.Role;
import cn.hs.module.role.domain.RoleCondition;
import cn.hs.module.role.service.IRoleService;

/**
 * Title: RoleServiceImpl<br>
 * Description: 角色service实现 <br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Sep 5, 2011
 * @version $Revision: 74 $
 */
@Service(value = "cn.hs.module.role.service.impl.RoleServiceImpl")
public class RoleServiceImpl extends PageTemplate implements IRoleService {

	@Autowired
	private IRoleDao roleDao;

	/**
	 * 调用模板process方法
	 * 
	 * @param pageCommond
	 * @param condition
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 5, 2011
	 */
	public JSONObject doProcess(BasePageCommond pageCommond, BaseCondition condition) throws Exception {
		return process(pageCommond, condition);
	}

	/**
	 * 实现查询count
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 5, 2011
	 */
	@Override
	protected Long findCount(BaseCondition condition) throws Exception {
		return roleDao.countRole((RoleCondition) condition);
	}

	/**
	 * 实现查询list
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 5, 2011
	 */
	@Override
	protected List<?> findList(BaseCondition condition) throws Exception {
		return roleDao.listRole((RoleCondition) condition);
	}

	/**
	 * 新增角色
	 * 
	 * @param role
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 5, 2011
	 */
	public void addRole(Role role) throws Exception {
		roleDao.addRole(role);
	}

	/**
	 * 根据ID集合批量作废或启用角色
	 * 
	 * @param condition
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 5, 2011
	 */
	public void discardOrReuseRole(RoleCondition condition) throws Exception {
		// 得到需要启用或作废的IDs
		String[] roleIds = condition.getRoleIds();
		if (condition.getSearchActiveState() == Role.IS_ACTIVE_Y) {
			// 活动状态为启用，需要作废
			if (roleIds != null) {
				for (String id : roleIds) {
					// 把主键为Integer类型修改为Long类型 modify by xiangbin at 2012-07-25
					condition.setQueryRoleID(id);
					Role role = this.getRole(condition);
					role.setActiveState(Role.IS_ACTIVE_N);
					this.updateRole(role);
				}
			}
		} else if (condition.getSearchActiveState() == Role.IS_ACTIVE_N) {
			// 活动状态为作废，需要启用
			if (roleIds != null) {
				for (String id : roleIds) {
					condition.setQueryRoleID(id);
					Role role = this.getRole(condition);
					role.setActiveState(Role.IS_ACTIVE_Y);
					this.updateRole(role);
				}
			}
		}
	}

	/**
	 * 根据ID查询角色信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 5, 2011
	 */
	public Role getRole(RoleCondition condition) throws Exception {
		return roleDao.getRole(condition);
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
		roleDao.updateRole(role);
	}

	/**
	 * 新增和更新角色前，查看角色编码是否有重复
	 * 
	 * @param role
	 * @return 不含重复角色编码返回true
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 9, 2011
	 */
	@Override
	public boolean checkRoleCodeIsRepeat(Role role) throws Exception {
		RoleCondition condition = new RoleCondition();
		if (role.getRoleID() != null) {
			// 把主键为Integer类型修改为Long类型 modify by xiangbin at 2012-07-25
			condition.setQueryRoleID(role.getRoleID());
		}
		condition.setQueryRoleCode(role.getRoleCode());
		List<Role> list = roleDao.getRoleByRoleCode(condition);
		if (list != null && list.size() > 0) {
			// 含有重复的值
			return false;
		}
		return true;
	}

	/**
	 * 根据角色编码查询角色信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 13, 2011
	 */
	@Override
	public List<Role> getRoleByRoleCode(RoleCondition condition) throws Exception {
		return roleDao.getRoleByRoleCode(condition);
	}

}
