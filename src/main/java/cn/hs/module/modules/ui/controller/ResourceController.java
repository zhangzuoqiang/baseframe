/*
 * $Log: ResourceController.java,v $
 * Revision 1.1  2012/05/23 09:27:54  guor
 * 初次提交
 *
 */
package cn.hs.module.modules.ui.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hs.core.page.commond.JSONObject;
import cn.hs.core.util.token.IToken;
import cn.hs.module.basedata.domain.BaseData;
import cn.hs.module.modules.domain.Resource;
import cn.hs.module.modules.domain.ResourceCondition;
import cn.hs.module.modules.service.IResourceService;
import cn.hs.module.modules.ui.commond.ResourceCommond;
import cn.hs.module.modules.ui.commond.ResourceValidCommond;

/**
 * Title: ResourceController<br>
 * Description: 菜单维护Action<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author FanKY
 * @createDate Sep 13, 2011
 * @version $Revision: 61 $
 */
@Controller
@RequestMapping(value = ResourceController.modulePath)
public class ResourceController {
	// 模块页面根路径
	public static final String modulePath = "/module/modules";
	// token接口
	@Autowired
	private IToken token;
	@Autowired
	private IResourceService resourceService;

	/**
	 * 查询菜单集合
	 * 
	 * @param model
	 * @param request
	 * @param baseDataCommond
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 13, 2011
	 */
	@RequestMapping(value = "/listResource")
	public @ResponseBody
	JSONObject listResource(ResourceCommond resourceCommond) throws Exception {
		ResourceCondition condition = new ResourceCondition();
		BeanUtils.copyProperties(resourceCommond, condition);
		if (condition.getSearchActiveState() == null || "".equals(condition.getSearchActiveState())) {
			// 列表默认查询启用数据
			condition.setSearchActiveState(BaseData.IS_ACTIVE_Y.toString());
		}
		JSONObject result = resourceService.doProcess(resourceCommond, condition);
		return result;
	}

	/**
	 * 添加菜单
	 * 
	 * @param model
	 * @param request
	 * @param resourceValidCommond
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 13, 2011
	 */
	@RequestMapping(value = "/addResource")
	public @ResponseBody
	JSONObject addResource(HttpServletRequest request, ResourceValidCommond resourceValidCommond) throws Exception {
		Resource resource = null;
		JSONObject result = new JSONObject();
		if (token.isTokenValid(request, true)) {
			resource = new Resource();
			BeanUtils.copyProperties(resourceValidCommond, resource);
			// 设置默认排序为0
			if (resource.getOrderNum() == null) {
				resource.setOrderNum(0);
			}
			resourceService.addResource(resource);
			result.setSuccess(true);
			result.setMsg("添加成功！");
			result.setData(resource);
		} else {
			result.setSuccess(false);
			result.setMsg("添加失败！");
		}
		return result;
	}

	/**
	 * 预更新菜单
	 * 
	 * @param model
	 * @param request
	 * @param resourceValidCommond
	 * @return
	 * @author FanKY
	 * @throws Exception
	 * @date Sep 13, 2011
	 */
	@RequestMapping(value = "/preUpdateResource")
	public @ResponseBody
	JSONObject preUpdateResource(HttpServletRequest request, ResourceValidCommond resourceValidCommond) throws Exception {
		ResourceCondition resourceCondition = null;
		Resource resource = null;
		JSONObject object = new JSONObject();
		if (resourceValidCommond.getResourceIds() != null && resourceValidCommond.getResourceIds().length > 0) {
			String[] resouInteger = resourceValidCommond.getResourceIds();
			resourceCondition = new ResourceCondition();
			resourceCondition.setQueryResourceId(resouInteger[0].toString());
			resource = resourceService.getResource(resourceCondition);
			BeanUtils.copyProperties(resource, resourceValidCommond);
			// 如果逻辑正确，必须加success为true
			object.setSuccess(true);
			object.setData(resourceValidCommond);
		}
		return object;
	}

	/**
	 * 更新菜单
	 * 
	 * @param model
	 * @param request
	 * @param resourceValidCommond
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 13, 2011
	 */
	@RequestMapping(value = "/updateResource")
	public @ResponseBody
	JSONObject updateResource(HttpServletRequest request, ResourceValidCommond resourceValidCommond) throws Exception {
		Resource resource = null;
		JSONObject object = new JSONObject();
		if (token.isTokenValid(request, true)) {
			resource = new Resource();
			BeanUtils.copyProperties(resourceValidCommond, resource);
			resourceService.updateResource(resource);
			object.setSuccess(true);
			object.setMsg("修改成功！");
			object.setData(resource);
		} else {
			object.setSuccess(false);
			object.setMsg("更新菜单失败！");
		}
		return object;
	}

	/**
	 * 根据ID数组批量作废或启用菜单
	 * 
	 * @param model
	 * @param request
	 * @param resourceCommond
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 13, 2011
	 */
	@RequestMapping(value = "/discardOrReuseResource")
	public @ResponseBody
	JSONObject discardOrReuseResource(HttpServletRequest request, ResourceCommond resourceCommond) throws Exception {
		JSONObject object = new JSONObject();
		if (resourceCommond.getSearchActiveState() != null && !"".equals(resourceCommond.getSearchActiveState()) && resourceCommond.getResourceIds() != null && resourceCommond.getResourceIds().length > 0) {
			ResourceCondition resourceCondition = new ResourceCondition();
			BeanUtils.copyProperties(resourceCommond, resourceCondition);
			resourceService.discardOrReuseResource(resourceCondition);
			object.setSuccess(true);
			object.setMsg("修改成功！");
		} else {
			object.setSuccess(false);
			object.setMsg("修改失败！");
		}
		return object;
	}

}
