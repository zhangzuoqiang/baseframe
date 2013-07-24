/*
 * $Log: ResourceTreeController.java,v $
 * Revision 1.1  2012/05/23 09:27:54  guor
 * 初次提交
 *
 */
package cn.hs.module.modules.ui.controller;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hs.core.servicetemplate.treetemplate.jsonbean.JsonTreeBean;
import cn.hs.module.modules.domain.ResourceTreeCondition;
import cn.hs.module.modules.service.IResourceTreeService;
import cn.hs.module.modules.ui.commond.ResourceTreeCommond;

/**
 * Title: ResourceTreeController<br>
 * Description: 菜单维护树 Action<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author FanKY
 * @createDate Sep 9, 2011
 * @version $Revision: 61 $
 */
@Controller
@RequestMapping(value = ResourceTreeController.modulePath)
public class ResourceTreeController {
	public static final String modulePath = "/module/modules";

	@Autowired
	private IResourceTreeService resourceTreeService;

	/**
	 * 查询菜单tree集合
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 14, 2011
	 */
	@RequestMapping(value = "/findResourceTree")
	public @ResponseBody
	List<JsonTreeBean> findResourceTree(ResourceTreeCommond resourceTreeCommond) throws Exception {
		ResourceTreeCondition condition = new ResourceTreeCondition();
		BeanUtils.copyProperties(condition, resourceTreeCommond);
		condition.setFindLeapNode(false);
		return resourceTreeService.doProcess(condition);
	}
}
