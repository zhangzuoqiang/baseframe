package cn.hs.module.modules.ui.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hs.commons.utils.PropertyUtil;
import cn.hs.core.log4j.ILog4jManager;
import cn.hs.core.page.commond.JSONObject;
import cn.hs.core.springext.mvc.SessionConstant;
import cn.hs.core.springext.security.casclient.domain.json.CasRole;
import cn.hs.core.springext.security.strategy.casclient.bo.BaseResourceImpl;
import cn.hs.module.modules.domain.Resource;
import cn.hs.module.modules.domain.ResourceCondition;
import cn.hs.module.modules.domain.json.HTMLTag;
import cn.hs.module.modules.domain.returnobj.ResourceObject;
import cn.hs.module.modules.service.IResourceService;
import cn.hs.module.role.domain.Role;
import cn.hs.module.role.domain.RoleCondition;
import cn.hs.module.role.domain.RoleResourceCondition;
import cn.hs.module.role.domain.querybena.RoleResourceMenuBean;
import cn.hs.module.role.service.IRoleResourceService;
import cn.hs.module.role.service.IRoleService;

/**
 * TopController Title: TopController<br>
 * Description: top页面controller<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Sep 13, 2011
 * @version $Revision: 62 $
 */
@Controller
@RequestMapping(value = TopController.modulePath)
public class TopController {

	// 模块根路径
	public static final String modulePath = "";

	@Autowired
	private IRoleResourceService roleResourceService;

	@Autowired
	private IRoleService roleService;

	// 初始化日志接口
	@Autowired
	private ILog4jManager log4jManager;

	@Autowired
	private IResourceService resourceService;

	/**
	 * 获取登陆人的角色集合
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Apr 29, 2013
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/findRoleList")
	public @ResponseBody
	JSONObject findRoleList(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		JSONObject result = new JSONObject();
		// 获取用户角色信息
		List<CasRole> roles = (List<CasRole>) session.getAttribute(SessionConstant.ROLES);
		List<HTMLTag> list = new ArrayList<HTMLTag>();
		if (roles != null && roles.size() > 0) {
			for (CasRole role : roles) {
				HTMLTag tag = new HTMLTag();
				tag.setTag(HTMLTag.HTML_TAG_OPTION);
				tag.setValue(role.getRoleCode());
				tag.setHtml(role.getRoleName());
				list.add(tag);
			}
		}
		result.setData(list);
		result.setSuccess(true);
		return result;
	}

	/**
	 * 得到menu菜单
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 14, 2011
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/findMenu")
	public @ResponseBody
	List<ResourceObject> findMenu(Model model, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		List<ResourceObject> list = new ArrayList<ResourceObject>();
		try {
			// 选择的角色
			String roleCode = request.getParameter("roleCode");
			// 选择角色,清除session中的当前角色和角色资源集合
			session.removeAttribute(SessionConstant.CURRENT_ROLE);
			session.removeAttribute(SessionConstant.CURRENT_PERMISSIONS);
			session.removeAttribute(SessionConstant.RESOURCE_SCOPE);
			if (!PropertyUtil.objectNotEmpty(roleCode)) {
				return list;
			}
			// 当前用户在本系统中的全部角色集合
			List<CasRole> userRoleList = (List<CasRole>) session.getAttribute(SessionConstant.ROLES);
			for (CasRole jsonRole : userRoleList) {
				// 查找当前选中的角色 并放入session中
				if (roleCode.equals(jsonRole.getRoleCode())) {
					session.setAttribute(SessionConstant.CURRENT_ROLE, jsonRole);
				}
			}
			// 根据角色编码查询角色信息
			RoleCondition roleCondition = new RoleCondition();
			roleCondition.setQueryRoleCode(roleCode);
			roleCondition.setSearchActiveState(Role.IS_ACTIVE_Y);
			List<Role> roleList = roleService.getRoleByRoleCode(roleCondition);
			// 根据角色ID查询相应的角色-资源信息
			RoleResourceCondition condition = new RoleResourceCondition();
			// 根据角色查询一级资源集合
			ResourceCondition resCondition = new ResourceCondition();
			if (roleList != null && roleList.size() > 0) {
				String roleID = roleList.get(0).getRoleID();
				if (roleID != null && !"".equals(roleID)) {
					condition.setQueryRoleID(roleID);
					resCondition.setRoleIds(new String[] { roleID });
				}
			} else {
				return list;
			}
			List<RoleResourceMenuBean> subResource = roleResourceService.getRoleResourceByRoleId(condition);

			// 查询属于当前角色的1级菜单
			resCondition.setQueryResourceParentID("-1");
			resCondition.setRows(-1);
			resCondition.setStart(-1);
			resCondition.setSearchActiveState(Resource.IS_ACTIVE_Y.toString());
			List<Resource> parentList = resourceService.findRoleList(resCondition);
			Map<String, List<ResourceObject>> tempMap = new HashMap<String, List<ResourceObject>>();
			List<Resource> resourceList = new ArrayList<Resource>(); // 当前角色资源集合
			for (RoleResourceMenuBean sonResource : subResource) {
				List<ResourceObject> children = tempMap.get(sonResource.getParentID().toString());
				if (children == null) {
					children = new ArrayList<ResourceObject>();
				}
				ResourceObject sonObject = new ResourceObject();
				sonObject.setResourceName(sonResource.getResourceName());
				sonObject.setResourceID(sonResource.getResourceID());
				sonObject.setUrl(sonResource.getUrl());
				children.add(sonObject);
				tempMap.put(sonResource.getParentID().toString(), children);
				Resource resource = new Resource();
				resource.setResourceID(sonResource.getResourceID());
				resource.setResourceName(sonResource.getResourceName());
				resource.setUrl(sonResource.getUrl());
				resourceList.add(resource);
			}
			// 拼装资源菜单(1-2级菜单)
			for (Resource resource : parentList) {
				ResourceObject object = new ResourceObject();
				object.setResourceName(resource.getResourceName());
				object.setResourceID(resource.getResourceID());
				object.setUrl(resource.getUrl());
				List<ResourceObject> children = tempMap.get(resource.getResourceID().toString());
				object.setChildren(children);
				list.add(object);
				resourceList.add(resource);
			}
			// 当前角色资源集合存入session
			session.setAttribute(SessionConstant.RESOURCE_SCOPE, resourceList);
			return list;
		} catch (Exception e) {
			log4jManager.saveExceptionLog(getClass().getName(), "findMenu", "获取菜单时出错", e);
			session.removeAttribute(SessionConstant.CURRENT_ROLE);
			return list;
		}
	}

	/**
	 * 根据用户的角色获取用户资源(不选择,获取全部角色资源)
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @date Jul 31, 2012
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/findRoleMenu")
	public @ResponseBody
	List<ResourceObject> findRolesMenu(HttpServletRequest request) throws Exception {
		// JSON结果集封装
		HttpSession session = request.getSession();
		List<ResourceObject> resultList = new ArrayList<ResourceObject>();
		try {
			// 获取角色所对应的资源范围
			List<Resource> allResourceList = (List<Resource>) session.getAttribute("resourceScopes");
			// 获取用户角色信息
			List<Role> roles = (List<Role>) session.getAttribute("roles");
			if (roles != null && roles.size() > 0) {
				// 获取用户角色对应拥有的资源
				String[] roleIds = new String[roles.size()];
				if (roles != null && roles.size() > 0) {
					for (int i = 0; i < roles.size(); i++) {
						Role role = roles.get(i);
						roleIds[i] = role.getRoleID();
					}
				}
				// 根据角色查询一级资源集合
				ResourceCondition condition = new ResourceCondition();
				condition.setQueryResourceParentID("-1");
				condition.setRoleIds(roleIds);
				condition.setRows(-1);
				condition.setStart(-1);
				condition.setSearchActiveState(Resource.IS_ACTIVE_Y.toString());
				List<Resource> parentList = resourceService.findRoleList(condition);
				for (Resource parentResource : parentList) {
					ResourceObject rObject = new ResourceObject();
					rObject.setResourceName(parentResource.getResourceName());
					rObject.setResourceID(parentResource.getResourceID());
					rObject.setUrl(parentResource.getUrl());
					List<ResourceObject> sonList = new ArrayList<ResourceObject>();

					for (Resource sonResource : allResourceList) {
						if (parentResource.getResourceID().equals(sonResource.getParentID())) {
							ResourceObject sonObject = new ResourceObject();
							sonObject.setResourceName(sonResource.getResourceName());
							sonObject.setResourceID(sonResource.getResourceID());
							sonObject.setUrl(sonResource.getUrl());
							sonList.add(sonObject);
						}
					}
					rObject.setChildren(sonList);
					resultList.add(rObject);
				}
			}
		} catch (Exception e) {
			log4jManager.saveExceptionLog(getClass().getName(), "findMenu", "获取菜单时出错", e);
		}
		return resultList;
	}

	/**
	 * 获取当前登陆人的功能权限
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Aug 23, 2012
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/findUserPermissions")
	public @ResponseBody
	JSONObject findUserPermissions(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		JSONObject result = (JSONObject) session.getAttribute(SessionConstant.CURRENT_PERMISSIONS);
		if (result == null) {
			result = new JSONObject();
			// 获取角色所对应的资源范围
			List<Resource> userResourceList = (List<Resource>) session.getAttribute(SessionConstant.RESOURCE_SCOPE);

			ResourceCondition resourceCondition = new ResourceCondition();
			resourceCondition.setSearchActiveState(BaseResourceImpl.getIsActiveState());
			// 不翻页查询
			resourceCondition.setRows(-1);
			resourceCondition.setStart(-1);
			// 查询全部的启用资源
			List<Resource> allResourceList = resourceService.findList(resourceCondition);
			// 临时map变量，方便快速查找
			Map<String, Resource> tempMap = new HashMap<String, Resource>();
			List<Resource> tempList = new ArrayList<Resource>();
			for (Resource resource : allResourceList) {
				// 去掉根及1级目录
				if (!"-1".equals(resource.getParentID()) && !"-2".equals(resource.getParentID())) {
					tempMap.put(resource.getResourceID(), resource);
				}
			}
			// 循环获取用户已选资源,从全部资源中删除
			if (userResourceList != null) {
				for (Resource userResource : userResourceList) {
					tempList.add(tempMap.get(userResource.getResourceID()));
				}
			}
			// 去除用户已选资源
			allResourceList.removeAll(tempList);

			Map<String, List<String>> resultData = new HashMap<String, List<String>>();
			// 循环未设置权限的资源，拼装结果
			for (Resource resource : allResourceList) {
				// 功能点
				if (resource.getIsMenu().equals(Resource.IS_MENU_N)) {
					Resource parentResource = tempMap.get(resource.getParentID());
					if (parentResource != null) {
						List<String> funcList = resultData.get(parentResource.getUrl());
						if (funcList == null) {
							funcList = new ArrayList<String>();
						}
						funcList.add(resource.getUrl());
						resultData.put(parentResource.getUrl(), funcList);
					}
				}
			}

			result.setData(resultData);
			result.setSuccess(true);
			session.setAttribute(SessionConstant.CURRENT_PERMISSIONS, result);
		}

		return result;
	}

}
