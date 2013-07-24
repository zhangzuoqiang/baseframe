package cn.hs.module.role.ui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hs.core.page.commond.JSONObject;
import cn.hs.module.basedata.domain.BaseData;
import cn.hs.module.modules.domain.ResourceCondition;
import cn.hs.module.modules.service.IResourceService;
import cn.hs.module.modules.ui.commond.ResourceCommond;
import cn.hs.module.role.domain.Role;
import cn.hs.module.role.domain.RoleResource;
import cn.hs.module.role.domain.RoleResourceCondition;
import cn.hs.module.role.domain.querybena.RoleResourceMenuBean;
import cn.hs.module.role.service.IChoisedRoleResourceService;
import cn.hs.module.role.service.IPreChoiseRoleResourceService;
import cn.hs.module.role.service.IRoleResourceService;
import cn.hs.module.role.ui.commond.ChoisedRoleResourceCommond;

/**
 * 角色controller Title: ChoisedRoleResourceController<br>
 * Description: <br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author YaoSC
 * @createDate Sep 5, 2011
 * @version $Revision: 62 $
 */
@Controller
@RequestMapping(value = ChoisedRoleResourceController.modulePath)
public class ChoisedRoleResourceController {

	// 模块页面根路径
	public static final String modulePath = "/module/role";

	@Autowired
	private IPreChoiseRoleResourceService iPreChoiseRoleResourceService;

	@Autowired
	private IResourceService resourceService;

	@Autowired
	private IChoisedRoleResourceService iChoisedRoleResourceService;

	@Autowired
	private IRoleResourceService roleResourceServie;

	/**
	 * 查询菜单集合
	 * 
	 * @param model
	 * @param request
	 * @param baseDataCommond
	 * @return
	 * @throws Exception
	 * @author YaoSC
	 * @date Sep 13, 2011
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listResource")
	public @ResponseBody
	JSONObject listResource(ResourceCommond resourceCommond) throws Exception {
		JSONObject object = new JSONObject();
		ResourceCondition condition = new ResourceCondition();
		BeanUtils.copyProperties(resourceCommond, condition);
		if (condition.getSearchActiveState() == null || "".equals(condition.getSearchActiveState())) {
			// 列表默认查询启用数据
			condition.setSearchActiveState(BaseData.IS_ACTIVE_Y.toString());
		}
		resourceService.doProcess(resourceCommond, condition);

		RoleResourceCondition roleResourceCondition = new RoleResourceCondition();
		// 得到角色ID
		roleResourceCondition.setQueryRoleID(resourceCommond.getQueryRoleID());
		// 通过角色ID得到角色资源集合
		List<cn.hs.module.modules.domain.Resource> resourceList = (List<cn.hs.module.modules.domain.Resource>) resourceCommond.getResultCollection();
		// 得到查询后分页记录数
		int count = resourceCommond.getCount();
		// 通过角色资源中间表得到角色资源集合
		List<RoleResourceMenuBean> roleResourceList = roleResourceServie.getRoleResourceByRoleId(roleResourceCondition);
		List<cn.hs.module.modules.domain.Resource> removeTempList = new ArrayList<cn.hs.module.modules.domain.Resource>();
		// 资源表与中间表对比,如有相同资源则删除
		for (cn.hs.module.modules.domain.Resource resource : resourceList) {
			for (RoleResourceMenuBean roleResource : roleResourceList) {
				if (roleResource.getResourceID().equals(resource.getResourceID())) {
					removeTempList.add(resource);
				}
			}
		}
		count -= removeTempList.size();
		resourceCommond.getPageBean().setCount(count);
		resourceList.removeAll(removeTempList);
		object.setSuccess(true);
		object.setData(resourceCommond);
		return object;
	}

	/**
	 * 添加删除之后返回资源列表
	 * 
	 * @param model
	 * @param request
	 * @param ResourceCommond
	 * @return
	 * @throws Exception
	 * @author YaoSC
	 * @date Sep 13, 2011
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listResources")
	public @ResponseBody
	JSONObject listResources(ResourceCommond resourceCommond) throws Exception {
		JSONObject object = new JSONObject();
		ResourceCondition condition = new ResourceCondition();
		BeanUtils.copyProperties(resourceCommond, condition);
		if (condition.getSearchActiveState() == null || "".equals(condition.getSearchActiveState())) {
			// 列表默认查询启用数据
			condition.setSearchActiveState(BaseData.IS_ACTIVE_Y.toString());
		}
		resourceService.doProcess(resourceCommond, condition);

		RoleResourceCondition roleResourceCondition = new RoleResourceCondition();
		// 得到角色ID
		roleResourceCondition.setQueryRoleID(resourceCommond.getQueryRoleID());
		// 通过角色ID得到角色资源集合
		List<cn.hs.module.modules.domain.Resource> resourceList = (List<cn.hs.module.modules.domain.Resource>) resourceCommond.getResultCollection();
		int count = resourceCommond.getCount();
		List<RoleResourceMenuBean> roleResourceList = roleResourceServie.getRoleResourceByRoleId(roleResourceCondition);
		List<cn.hs.module.modules.domain.Resource> removeTempList = new ArrayList<cn.hs.module.modules.domain.Resource>();
		// 资源表与中间表对比,如有相同资源则删除
		for (cn.hs.module.modules.domain.Resource resource : resourceList) {
			for (RoleResourceMenuBean roleResource : roleResourceList) {
				if (roleResource.getResourceID().equals(resource.getResourceID())) {
					removeTempList.add(resource);
				}
			}
		}
		count -= removeTempList.size();
		resourceCommond.getPageBean().setCount(count);
		resourceList.removeAll(removeTempList);
		object.setSuccess(true);
		object.setData(resourceCommond);
		return object;
	}

	/**
	 * 查询角色对应的资源列表
	 * 
	 * @param model
	 * @param request
	 * @param authorityRoleCommond
	 * @return
	 * @throws Exception
	 * @author YaoSC
	 * @date Sep 16, 2011
	 */
	@RequestMapping(value = "/listAuthorityRole")
	public @ResponseBody
	JSONObject listAuthorityRole(ChoisedRoleResourceCommond authorityRoleCommond) throws Exception {
		RoleResourceCondition condition = new RoleResourceCondition();
		BeanUtils.copyProperties(authorityRoleCommond, condition);
		if (condition.getSearchActiveState() == null || "".equals(condition.getSearchActiveState())) {
			// 列表默认查询启用数据
			condition.setSearchActiveState(BaseData.IS_ACTIVE_Y);
		}
		JSONObject object = iPreChoiseRoleResourceService.doProcess(authorityRoleCommond, condition);
		return object;
	}

	/**
	 * 添加资源
	 * 
	 * @param model
	 * @param request
	 * @param resourceValidCommond
	 * @return
	 * @throws Exception
	 * @author YaoSC
	 * @date Sep 13, 2011
	 */
	@RequestMapping(value = "/addAuthorityRoleResource")
	public @ResponseBody
	JSONObject addAuthorityRoleResource(HttpServletRequest request, ResourceCommond resourceCommond) throws Exception {
		List<RoleResource> list = new ArrayList<RoleResource>();
		JSONObject result = new JSONObject();
		try {
			// 根据角色ID添加资源
			for (int i = 0; i < resourceCommond.getResourceIds().length; i++) {
				RoleResource roleResource = new RoleResource();
				cn.hs.module.modules.domain.Resource resource = new cn.hs.module.modules.domain.Resource();
				resource.setResourceID(resourceCommond.getResourceIds()[i]);
				Role role = new Role();
				// 将主键Integer转换为Long类型
				role.setRoleID(resourceCommond.getQueryRoleID());
				roleResource.setResource(resource);
				roleResource.setRole(role);
				list.add(roleResource);
			}
			// 只增加当前选择的资源
			// ResourceTreeCondition condition = new ResourceTreeCondition();
			// //condition.setTreeNodeID(resourceCommond.getQueryResourceId());
			// condition.setNode(resourceCommond.getQueryResourceId());
			// condition.setFindLeapNode(false);
			// condition.setQueryParentIDs(resourceCommond.getResourceIds());
			// List<cn.hs.module.modules.domain.Resource> resources =
			// resourceTreeService
			// .listNextNodeList(condition);
			// // 根据角色ID添加父级下子级资源
			// for (int j = 0; j < resources.size(); j++) {
			// RoleResource roleResource = new RoleResource();
			// cn.hs.module.modules.domain.Resource resource = new
			// cn.hs.module.modules.domain.Resource();
			//
			// resource.setResourceID(resources.get(j).getResourceID());
			// Role role = new Role();
			// role.setRoleID(resourceCommond.getQueryRoleID());
			// roleResource.setResource(resource);
			// roleResource.setRole(role);
			// roleResource.setActiveState(RoleResource.IS_ACTIVE_Y);
			// list.add(roleResource);
			// }

			RoleResourceCondition roleResourceCondition = new RoleResourceCondition();
			// 得到角色ID
			if (resourceCommond.getQueryRoleID() != null) {
				roleResourceCondition.setQueryRoleID(resourceCommond.getQueryRoleID());
			}
			List<RoleResource> roleResourceList = iPreChoiseRoleResourceService.getResource(roleResourceCondition);
			// 删除中间表相同记录
			List<RoleResource> deleteList = new ArrayList<RoleResource>();
			for (RoleResource newRoleResource : roleResourceList) {
				for (RoleResource roleResource : list) {
					if (roleResource.getResource().getResourceID().equals(newRoleResource.getResource().getResourceID())) {
						deleteList.add(roleResource);
					}
				}
			}
			list.removeAll(deleteList);
			roleResourceCondition.setRoleResoure(list);
			iPreChoiseRoleResourceService.choiseRoleResource(roleResourceCondition);
			result.setSuccess(true);
			result.setMsg("添加资源成功！");
		} catch (RuntimeException e) {
			result.setSuccess(false);
			result.setMsg("添加资源失败！");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 删除资源
	 * 
	 * @param model
	 * @param request
	 * @param resourceValidCommond
	 * @return
	 * @throws Exception
	 * @author YaoSC
	 * @date Sep 13, 2011
	 */
	@RequestMapping(value = "/delAuthorityRoleResource")
	public @ResponseBody
	JSONObject delAuthorityRoleResource(ChoisedRoleResourceCommond authorityRoleCommond) throws Exception {
		List<RoleResource> list = new ArrayList<RoleResource>();
		JSONObject result = new JSONObject();
		RoleResourceCondition condition = new RoleResourceCondition();
		BeanUtils.copyProperties(authorityRoleCommond, condition);
		// 根据主键批量删除记录
		for (int i = 0; i < condition.getRoleResourceIds().length; i++) {
			RoleResource roleResource = new RoleResource();
			roleResource.setRoleResourceID(condition.getRoleResourceIds()[i]);
			list.add(roleResource);
		}
		condition.setRoleResoure(list);
		iChoisedRoleResourceService.deleteRoleResource(condition);
		result.setSuccess(true);
		result.setMsg("删除资源成功！");
		return result;
		// return ChoisedRoleResourceController.modulePath
		// + "/roleResourceFram.jsp?queryResourceId="
		// + authorityRoleCommond.getQueryResourceId() + "&queryRoleID="
		// + authorityRoleCommond.getQueryRoleID();
	}
}
