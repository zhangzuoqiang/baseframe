package cn.hs.module.role.ui.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hs.core.page.commond.JSONObject;
import cn.hs.core.util.token.IToken;
import cn.hs.module.basedata.domain.BaseData;
import cn.hs.module.basedata.domain.BaseDataCondition;
import cn.hs.module.basedata.service.IBaseDataService;
import cn.hs.module.role.domain.Role;
import cn.hs.module.role.domain.RoleCondition;
import cn.hs.module.role.service.IRoleService;
import cn.hs.module.role.ui.commond.RoleCommond;
import cn.hs.module.role.ui.commond.RoleValidCommond;

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
@RequestMapping(value = RoleController.modulePath)
public class RoleController {

	// 模块页面根路径
	public static final String modulePath = "/module/role";

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IBaseDataService baseDataService;

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
	@RequestMapping(value = "/listRole")
	public @ResponseBody
	JSONObject listRole(Model model, HttpServletRequest request, RoleCommond roleCommond) throws Exception {
		RoleCondition condition = new RoleCondition();
		BeanUtils.copyProperties(condition, roleCommond);
		JSONObject result = roleService.doProcess(roleCommond, condition);
		return result;
	}

	/**
	 * 添加角色
	 * 
	 * @param model
	 * @param request
	 * @param roleCommond
	 * @param role
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 5, 2011
	 */
	@RequestMapping(value = "/addRole")
	public @ResponseBody
	JSONObject addRole(HttpServletRequest request, RoleCommond roleCommond, @Valid
	RoleValidCommond role) throws Exception {
		JSONObject result = new JSONObject();
		if (token.isTokenValid(request, true)) {
			// 添加的角色
			Role addRole = new Role();
			// 设置活动状态为活动
			// role.setActiveState(Role.IS_ACTIVE_Y);
			BeanUtils.copyProperties(addRole, role);
			BaseData roleType = new BaseData();
			roleType.setDataCode(role.getType());
			// 设置角色类型
			addRole.setRoleType(roleType);
			roleService.addRole(addRole);
			result.setData(addRole);
			result.setMsg("添加角色成功!");
			result.setSuccess(true);
		} else {
			result.setMsg("添加角色失败!");
			result.setSuccess(false);
		}
		return result;
	}

	/**
	 * 预更新角色
	 * 
	 * @param model
	 * @param request
	 * @param roleCommond
	 * @param role
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 5, 2011
	 */
	@RequestMapping(value = "/preUpdateRole")
	public @ResponseBody
	JSONObject preUpdateRole(RoleCommond roleCommond, RoleValidCommond role) throws Exception {
		JSONObject result = new JSONObject();
		RoleCondition condition = new RoleCondition();
		Role roleTemp = new Role();
		if (roleCommond.getRoleIds() != null && roleCommond.getRoleIds().length > 0) {
			condition.setQueryRoleID(roleCommond.getRoleIds()[0]);
		}
		roleTemp = roleService.getRole(condition);
		BeanUtils.copyProperties(role, roleTemp);
		if (roleTemp.getRoleType() != null) {
			role.setType(roleTemp.getRoleType().getDataCode());
		}
		// 得到角色信息集合
		// List<BaseData> baseDataList = getRoleTypeList();
		// model.addAttribute("baseDataList", baseDataList);
		// model.addAttribute("role", role);
		// model.addAttribute(roleCommond);
		result.setData(role);
		result.setSuccess(true);
		return result;
	}

	/**
	 * 更新角色
	 * 
	 * @param model
	 * @param request
	 * @param roleCommond
	 * @param role
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 5, 2011
	 */
	@RequestMapping(value = "/updateRole")
	public @ResponseBody
	JSONObject updateRole(HttpServletRequest request, RoleCommond roleCommond, @Valid
	RoleValidCommond role) throws Exception {
		JSONObject result = new JSONObject();
		if (token.isTokenValid(request, true)) {
			// 添加的角色
			Role updateRole = new Role();
			BeanUtils.copyProperties(updateRole, role);
			BaseData roleType = new BaseData();
			roleType.setDataCode(role.getType());
			// 设置角色类型
			updateRole.setRoleType(roleType);
			roleService.updateRole(updateRole);
			result.setData(updateRole);
			result.setMsg("更新角色成功！");
			result.setSuccess(true);
		} else {
			result.setMsg("更新角色失败！");
			result.setSuccess(false);
		}
		return result;
	}

	/**
	 * 根据ID集合批量启用角色
	 * 
	 * @param model
	 * @param request
	 * @param roleCommond
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 5, 2011
	 */
	@RequestMapping(value = "/reuseRole")
	public @ResponseBody
	JSONObject reuseRole(RoleCommond roleCommond) throws Exception {
		RoleCondition condition = new RoleCondition();
		JSONObject result = new JSONObject();
		String[] roleIds = roleCommond.getRoleIds();
		if (roleIds != null && roleIds.length > 0) {
			condition.setSearchActiveState(Role.IS_ACTIVE_N);
			condition.setRoleIds(roleIds);
			roleService.discardOrReuseRole(condition);
			result.setMsg("启用角色成功!");
			result.setSuccess(true);
		} else {
			result.setMsg("启用角色失败！");
			result.setSuccess(false);
		}
		return result;
	}

	/**
	 * 根据ID集合批量作废角色
	 * 
	 * @param model
	 * @param request
	 * @param roleCommond
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 6, 2011
	 */
	@RequestMapping(value = "/discardRole")
	public @ResponseBody
	JSONObject discardRole(RoleCommond roleCommond) throws Exception {
		JSONObject result = new JSONObject();
		RoleCondition condition = new RoleCondition();
		String[] roleIds = roleCommond.getRoleIds();
		if (roleIds != null && roleIds.length > 0) {
			condition.setSearchActiveState(Role.IS_ACTIVE_Y);
			condition.setRoleIds(roleIds);
			roleService.discardOrReuseRole(condition);
			result.setMsg("作废角色成功!");
			result.setSuccess(true);
		} else {
			result.setMsg("作废角色失败！");
			result.setSuccess(false);
		}
		return result;
	}

	/**
	 * 得到角色信息集合
	 * 
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 9, 2011
	 */
	private List<BaseData> getRoleTypeList() throws Exception {
		BaseDataCondition baseDataCondition = new BaseDataCondition();
		// 设置基础数据类别编码
		baseDataCondition.setSearchBaseTypeCode(BaseData.BASE_DATA_TYPE_CODE_ROLE);
		// 设置活动
		baseDataCondition.setSearchActiveState(BaseData.IS_ACTIVE_Y.toString());
		// 得到角色信息集合
		List<BaseData> baseDataList = baseDataService.getBaseDataList(baseDataCondition);
		return baseDataList;
	}

	/**
	 * 添加和修改前判断角色编码是否重复
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 9, 2011
	 */
	@RequestMapping(value = "/checkRoleCodeIsRepeat")
	public @ResponseBody
	JSONObject checkRoleCodeIsRepeat(RoleValidCommond roleValidCommond) throws Exception {
		JSONObject result = new JSONObject();
		Role role = new Role();
		// 设置角色编码
		if (roleValidCommond.getRoleCode() != null && !"".equals(roleValidCommond.getRoleCode())) {
			role.setRoleCode(roleValidCommond.getRoleCode());
			// 设置角色ID
			if (roleValidCommond.getRoleID() != null) {
				role.setRoleID(roleValidCommond.getRoleID());
			}
			if (roleService.checkRoleCodeIsRepeat(role)) {
				// 不含重复角色编码
				result.setMsg("角色编码不重复！");
				result.setSuccess(true);
			} else {
				result.setMsg("角色编码重复！");
				result.setSuccess(false);
			}
		} else {
			result.setMsg("判断角色编码不能为空！");
			result.setSuccess(false);
		}

		return result;
	}
}
