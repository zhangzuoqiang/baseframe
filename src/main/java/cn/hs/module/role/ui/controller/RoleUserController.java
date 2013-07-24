package cn.hs.module.role.ui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hs.core.log4j.impl.Log4jManager;
import cn.hs.core.page.commond.JSONObject;
import cn.hs.core.util.token.IToken;
import cn.hs.module.role.domain.Role;
import cn.hs.module.role.domain.RoleUser;
import cn.hs.module.role.domain.RoleUserCondition;
import cn.hs.module.role.service.IRoleUserService;
import cn.hs.module.role.ui.commond.RoleUserCommond;
import cn.hs.module.user.domain.User;

/**
 * Title: RoleController<br>
 * Description: 角色controller <br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Sep 5, 2011
 * @version $Revision: 62 $
 */
@Controller
@RequestMapping(value = RoleUserController.modulePath)
public class RoleUserController {

	// 模块页面根路径
	public static final String modulePath = "/module/roleuser";

	@Autowired
	private IRoleUserService roleUserService;

	// 日志管理
	@Autowired
	private Log4jManager log4jManager;

	// token接口
	@Autowired
	private IToken token;

	/**
	 * 查询角色列表
	 * 
	 * @param model
	 * @param request
	 * @param roleCommond
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 5, 2011
	 */
	@RequestMapping(value = "/addRoleUser")
	public @ResponseBody
	JSONObject addRoleUser(RoleUserCommond roleUserCommond) throws Exception {
		JSONObject result = new JSONObject();
		RoleUserCondition condition = new RoleUserCondition();
		User user = new User();
		RoleUser roleUser;
		Role role;
		user.setUserID(roleUserCommond.getSearchUserId());

		if (roleUserCommond.getSearchUserId() != null && !"".equals(roleUserCommond.getSearchUserId())) {
			if (roleUserCommond.getSearchRoleIds() != null && roleUserCommond.getSearchRoleIds().length > 0) {

				// 已经存在的角色
				condition.setQueryUserID(roleUserCommond.getSearchUserId());
				List<Role> existRoleList = roleUserService.getRoleUserByUserId(condition);
				List<String> existRoles = new ArrayList<String>();
				for (Role roles : existRoleList) {
					existRoles.add(roles.getRoleID());
				}
				// 操作的添加的角色
				List<String> addRoleLists = new ArrayList<String>();
				for (String addRoles : roleUserCommond.getSearchRoleIds()) {
					addRoleLists.add(addRoles);
				}
				// 需要添加的角色
				List<String> addRoleList = new ArrayList<String>();
				for (String addRole : addRoleLists) {
					if (!existRoles.contains(addRole)) {
						addRoleList.add(addRole);
					}
				}
				// 需要删除的角色数据
				List<String> deleteRoleList = new ArrayList<String>();
				for (String str : existRoles) {
					if (!addRoleLists.contains(str)) {
						deleteRoleList.add(str);
					}
				}
				// 清空角色
				if (deleteRoleList != null && deleteRoleList.size() > 0) {
					condition.setSearchRoleIDs(deleteRoleList.toArray(new String[deleteRoleList.size()]));
					roleUserService.deleteRoleUser(condition);
				}

				// 向数据库重新添加角色
				for (String addRoleID : addRoleList) {
					role = new Role();
					roleUser = new RoleUser();
					role.setRoleID(addRoleID);
					roleUser.setUser(user);
					roleUser.setRole(role);
					roleUserService.addRoleUser(roleUser);
				}
				result.setMsg("角色分配成功");
				result.setSuccess(true);
			} else {
				condition.setQueryUserID(roleUserCommond.getSearchUserId());
				roleUserService.deleteRoleUser(condition);
				result.setMsg("撤除角色成功");
				result.setSuccess(true);
			}
		} else {
			result.setMsg("角色分配失败");
			result.setSuccess(false);
		}
		return result;
	}

	/**
	 * 根据角色id查询用户信息列表
	 * 
	 * @param roleUserCommond
	 * @return
	 * @author MaBin
	 * @date 2012-9-18 17:13:18
	 */
	@RequestMapping(value = "/listUserInfoByRoleId")
	public @ResponseBody
	JSONObject listUserForRole(RoleUserCommond roleUserCommond) {
		JSONObject result = null;
		// 根据角色id查询用户列表
		try {
			// 用户列表
			RoleUserCondition pageCondition = new RoleUserCondition();
			BeanUtils.copyProperties(roleUserCommond, pageCondition);
			result = roleUserService.doProcess(roleUserCommond, pageCondition);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			log4jManager.saveExceptionLog(this.getClass().getName(), "init()", "查询用户列表时出错。", e);
		}
		return result;
	}

	/**
	 * 批量启用或作废用户
	 * 
	 * @param roleUserCommond
	 * @return
	 * @author MaBin
	 * @date 2012-10-14 16:13:59
	 */
	@RequestMapping(value = "/discardOrReuseBaseUser")
	public @ResponseBody
	JSONObject discardOrReuseBaseUser(HttpServletRequest request, RoleUserCommond roleUserCommond) {
		JSONObject result = new JSONObject();
		try {
			if (token.isTokenValid(request, true)) {
				RoleUserCondition userCondition = new RoleUserCondition();
				BeanUtils.copyProperties(roleUserCommond, userCondition);
				roleUserService.discardOrReuseBaseUser(userCondition);
				result.setMsg("修改成功");
				result.setSuccess(true);
			} else {
				result.setMsg("重复提交");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log4jManager.saveExceptionLog(this.getClass().getName(), "init()", "修改用户状态时出错。", e);
		}
		return result;
	}

}
