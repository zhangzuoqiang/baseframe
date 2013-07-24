package cn.hs.module.department.ui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hs.core.servicetemplate.treetemplate.jsonbean.JsonTreeBean;
import cn.hs.core.springext.mvc.SessionConstant;
import cn.hs.module.Constants;
import cn.hs.module.department.domain.Department;
import cn.hs.module.department.domain.DepartmentTreeCondition;
import cn.hs.module.department.service.IDepartmentCheckedTreeService;
import cn.hs.module.department.ui.commond.DepartmentTreeCommond;
import cn.hs.module.role.domain.Role;

/**
 * 
 * Title: DepartmentTreeController<br>
 * Description: 部门树管理控制器<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author Mill
 * @createDate 2012-8-3
 * @version $Revision:$
 */
@Controller
@RequestMapping(value = DepartmentCheckedTreeController.modulePath)
public class DepartmentCheckedTreeController {

	public static final String modulePath = "/module/department";

	@Autowired
	private IDepartmentCheckedTreeService departmentCheckedTreeService;

	/**
	 * 查询部门tree下一级节点集合
	 * 
	 * @param commond
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @createDate 2012-8-2
	 */
	@RequestMapping(value = "/findCheckedDepartmentTree")
	public @ResponseBody
	List<JsonTreeBean> findCheckedDepartmentTree(DepartmentTreeCommond commond) throws Exception {
		DepartmentTreeCondition condition = new DepartmentTreeCondition();
		BeanUtils.copyProperties(condition, commond);
		condition.setFindLeapNode(true);
		List<JsonTreeBean> list = departmentCheckedTreeService.doProcess(condition);
		return list;
	}

	/**
	 * 查询项目穿件的公司集合
	 * 
	 * @param commond
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @createDate 2012-8-2
	 */
	@RequestMapping(value = "/findDepartmentProjectTree")
	public @ResponseBody
	List<JsonTreeBean> findDepartmentProjectTree(DepartmentTreeCommond commond) throws Exception {
		// 加入项目ID为空判断 modify by HuangS at 2012-09-13
		List<JsonTreeBean> list = new ArrayList<JsonTreeBean>();
		if (commond.getSearchProjectID() != null && !"".equals(commond.getSearchProjectID())) {
			DepartmentTreeCondition condition = new DepartmentTreeCondition();
			BeanUtils.copyProperties(condition, commond);
			list = departmentCheckedTreeService.listDepartmentProjectTree(condition);
		}

		return list;
	}

	/**
	 * 当前登录用户所属公司ID。
	 * 
	 * @param request
	 * @return
	 */
	private static String getCurrentCompanyID(HttpServletRequest request) {
		String currentCompanyID = null;
		Department d = DepartmentCheckedTreeController.getCurrentCompany(request);
		if (d != null) {
			currentCompanyID = d.getDeptID();
		}
		return currentCompanyID;
	}

	/**
	 * 获得当前登录人所属公司TreePath。
	 * 
	 * @param request
	 * @return
	 */
	public static String getCurrentCompanyTreePath(HttpServletRequest request) {
		String treePath = null;
		Department d = DepartmentCheckedTreeController.getCurrentCompany(request);
		if (d != null) {
			treePath = d.getTreepath();
		}
		return treePath;
	}

	/**
	 * 获得当前登录人所属公司。
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static Department getCurrentCompany(HttpServletRequest request) {
		Department d = null;
		List<Department> currentDepartment = (List<Department>) request.getSession().getAttribute(SessionConstant.CURRENT_DEPARTMENT);
		if (currentDepartment != null && !currentDepartment.isEmpty()) {
			d = currentDepartment.get(0);
		}
		return d;
	}

	/**
	 * 当前登录用户是否包含简历管理员角色。
	 * 
	 * @param roles
	 * @return
	 */
	private static boolean hasRole(List<Role> roles, String hasRoleCode) {
		boolean hasRoleResumeManager = false;
		if (roles != null && !roles.isEmpty()) {
			for (Role role : roles) {
				String roleCode = role.getRoleCode();
				if (hasRoleCode.equals(roleCode)) {
					hasRoleResumeManager = true;
					break;
				}
			}
		}
		return hasRoleResumeManager;
	}
}
