package cn.hs.module.role.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.core.page.commond.BasePageCommond;
import cn.hs.core.page.commond.JSONObject;
import cn.hs.core.servicetemplate.pagetemplate.PageTemplate;
import cn.hs.module.role.dao.IRoleUserDao;
import cn.hs.module.role.domain.Role;
import cn.hs.module.role.domain.RoleUser;
import cn.hs.module.role.domain.RoleUserCondition;
import cn.hs.module.role.domain.jsonbean.UserInfoJsonBean;
import cn.hs.module.role.service.IRoleUserService;
import cn.hs.module.user.domain.User;

/**
 * Title: RoleUserServiceImpl<br>
 * Description: 角色-用户service实现<br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author XiangBin
 * @createDate Aug 1, 2012
 * @version $Revision:$
 */
@Service(value = "cn.hs.module.role.service.impl.RoleUserServiceImpl")
public class RoleUserServiceImpl extends PageTemplate implements IRoleUserService {

	@Autowired
	private IRoleUserDao roleUserDao;

	/**
	 * 根据用户ID得到相应的用户角色
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @date Aug 1, 2012
	 */
	@Override
	public List<Role> getRoleUserByUserId(RoleUserCondition condition) throws Exception {
		// 不执行翻页
		condition.setRows(-1);
		condition.setStart(-1);
		return roleUserDao.getRoleUserByUserId(condition);
	}

	/**
	 * 添加用户角色
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @date Aug 1, 2012
	 */
	@Override
	public void addRoleUser(RoleUser roleUser) throws Exception {
		roleUserDao.addRoleUser(roleUser);
	}

	/**
	 * 根据condition删除角色-用户
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @date Aug 1, 2012
	 */
	@Override
	public void deleteRoleUser(RoleUserCondition condition) throws Exception {
		roleUserDao.deleteRoleUser(condition);
	}

	/**
	 * 根据角色id查询人员列表
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 * @author MaBin
	 * @date 2012-9-18 16:26:47
	 */
	@Override
	public List<User> listUserByRoleId(RoleUserCondition condition) throws Exception {
		List<RoleUser> roleUserList = roleUserDao.listRoleUserByRoleId(condition);
		List<User> userList = new ArrayList<User>();
		for (RoleUser roleUser : roleUserList) {
			userList.add(roleUser.getUser());
		}
		return userList;
	}

	@Override
	public JSONObject doProcess(BasePageCommond pageCommond, BaseCondition condition) throws Exception {
		return process(pageCommond, condition);
	}

	@Override
	protected Long findCount(BaseCondition condition) throws Exception {
		return roleUserDao.countRoleUser((RoleUserCondition) condition);
	}

	@Override
	protected List<UserInfoJsonBean> findList(BaseCondition condition) throws Exception {
		List<UserInfoJsonBean> roleUserList = roleUserDao.listRoleUserByRoleIdJDBC((RoleUserCondition) condition);
		for (UserInfoJsonBean jsonBean : roleUserList) {
			// 用户状态：启用
			if (User.IS_ACTIVE_Y.toString().equals(jsonBean.getActiveState()))
				jsonBean.setActiveState("启用");
			// 用户状态：停用
			if (User.IS_ACTIVE_N.toString().equals(jsonBean.getActiveState()))
				jsonBean.setActiveState("作废");
		}
		return roleUserList;
	}

	@Override
	public void discardOrReuseBaseUser(RoleUserCondition condition) throws Exception {
		roleUserDao.discardOrReuseBaseUser(condition);
	}

}
