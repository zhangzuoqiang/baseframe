package cn.hs.module.department.ui.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hs.core.page.commond.JSONObject;
import cn.hs.core.util.token.IToken;
import cn.hs.module.department.domain.DepartmentUser;
import cn.hs.module.department.domain.DepartmentUserCondition;
import cn.hs.module.department.service.IDepartmentUserService;
import cn.hs.module.department.ui.commond.DepartmentUserCommond;
import cn.hs.module.department.ui.commond.DepartmentUserValidCommond;

/**
 * Title: DepartmentUserController<br>
 * Description: 部门用户管理控制器<br>
 * Company: GoldGov<br>
 * Copyright @ 2012 GoldGov .All rights reserved.<br>
 * @author Limk
 * @createDate 2012-08-13
 * @version $Revision$
 */
@Controller
@RequestMapping(value = DepartmentUserController.modulePath)
public class DepartmentUserController {
	// 模块页面根路径
	public static final String modulePath = "/module/departmentuser";
	// 部门用户接口
	@Autowired
	private IDepartmentUserService iDepartmentUserService;

	// token接口
	@Autowired
	private IToken token;

	/**
	 * 列表查询部门用户
	 * 
	 * @param model
	 * @param request
	 * @param departmentUserCommond
	 * @return
	 * @author Limk
	 * @throws Exception
	 * @date 2012-08-13
	 */
	@RequestMapping(value = "/listDepartmentUser")
	public @ResponseBody
	JSONObject listDepartmentUser(DepartmentUserCommond departmentUserCommond) throws Exception {
		DepartmentUserCondition condition = new DepartmentUserCondition();
		BeanUtils.copyProperties(departmentUserCommond, condition);
		if (condition.getSearchIsEnabled() == null || "".equals(condition.getSearchIsEnabled())) {
			// 列表默认查询启用数据
			// TODO condition.setSearchIsEnabled("1");
		}
		JSONObject result = iDepartmentUserService.doProcess(departmentUserCommond, condition);
		return result;
	}

	/**
	 * 加入token令牌
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-13
	 */
	@RequestMapping(value = "/saveToken")
	public @ResponseBody
	String preAddDepartmentUser(HttpServletRequest request) throws Exception {
		String tokenStr = token.saveToken(request);
		return tokenStr;
	}

	/**
	 * 添加部门用户
	 * 
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-13
	 */
	@RequestMapping(value = "/addDepartmentUser")
	public @ResponseBody
	JSONObject addDepartmentUser(HttpServletRequest request, DepartmentUserValidCommond departmentUserValidCommond) throws Exception {
		DepartmentUser departmentUser = null;
		JSONObject result = new JSONObject();
		// 用来判断是否为重复提交
		if (token.isTokenValid(request, true)) {
			departmentUser = new DepartmentUser();
			BeanUtils.copyProperties(departmentUserValidCommond, departmentUser);
			iDepartmentUserService.addDepartmentUser(departmentUser);
			result.setSuccess(true);
			result.setMsg("添加成功！");

		}
		return result;
	}

	/**
	 * 预更新部门用户
	 * 
	 * @param model
	 * @param request
	 * @param departmentUserCommond
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-13
	 */
	@RequestMapping(value = "/preUpdateDepartmentUser")
	public @ResponseBody
	JSONObject preUpdateDepartmentUser(DepartmentUserValidCommond departmentUserValidCommond) throws Exception {
		DepartmentUserCondition departmentUserCondition = null;
		DepartmentUser departmentUser = null;
		JSONObject object = new JSONObject();
		// 部门用户Id是否为空
		if (departmentUserValidCommond.getSearchDeptUserID() != null && !"".equals(departmentUserValidCommond.getSearchDeptUserID())) {
			departmentUserCondition = new DepartmentUserCondition();
			departmentUserCondition.setSearchDeptUserID(departmentUserValidCommond.getSearchDeptUserID());
			departmentUser = iDepartmentUserService.getDepartmentUser(departmentUserCondition);
			BeanUtils.copyProperties(departmentUser, departmentUserValidCommond);
			// 如果逻辑正确，必须加success为true
			object.setSuccess(true);
			object.setData(departmentUserValidCommond);
		}
		return object;
	}

	/**
	 * 更新部门用户
	 * 
	 * @param model
	 * @param request
	 * @param departmentUserCommond
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-13
	 */
	@RequestMapping(value = "/updataDepartmentUser")
	public @ResponseBody
	JSONObject updataDepartmentUser(HttpServletRequest request, DepartmentUserValidCommond departmentUserValidCommond) throws Exception {
		DepartmentUser departmentUser = null;
		JSONObject object = new JSONObject();
		// 用来判断是否为重复提交
		if (token.isTokenValid(request, true)) {
			departmentUser = new DepartmentUser();
			BeanUtils.copyProperties(departmentUserValidCommond, departmentUser);
			iDepartmentUserService.updateDepartmentUser(departmentUser);
			object.setSuccess(true);
			object.setMsg("修改成功！");
		}
		return object;
	}

	/**
	 * 根据ID数组批量作废或启用部门用户
	 * 
	 * @param model
	 * @param request
	 * @param departmentUserCommond
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-13
	 */
	@RequestMapping(value = "/discardOrReuseDepartmentUser")
	public @ResponseBody
	JSONObject discardOrReuseDepartmentUser(HttpServletRequest request, DepartmentUserCommond departmentUserCommond) throws Exception {
		JSONObject object = new JSONObject();
		if (departmentUserCommond.getSearchIsEnabled() != null && !"".equals(departmentUserCommond.getSearchIsEnabled()) && departmentUserCommond.getSearchDeptUserIDs() != null && departmentUserCommond.getSearchDeptUserIDs().length > 0) {
			DepartmentUserCondition departmentUserCondition = new DepartmentUserCondition();
			departmentUserCondition.setSearchDeptUserIDs(departmentUserCommond.getSearchDeptUserIDs());
			departmentUserCondition.setSearchIsEnabled(departmentUserCommond.getSearchIsEnabled());
			iDepartmentUserService.discardOrReuseDepartmentUser(departmentUserCondition);
			object.setSuccess(true);
			object.setMsg("修改成功！");
		} else {
			object.setSuccess(false);
			object.setMsg("为空");
		}
		return object;
	}
}
