/*
 * $Log: RoleResourceTreeController.java,v $
 * Revision 1.1  2012/05/23 09:27:56  guor
 * 初次提交
 *
 */
package cn.hs.module.role.ui.controller;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hs.core.servicetemplate.treetemplate.jsonbean.JsonTreeBean;
import cn.hs.module.modules.domain.ResourceTreeCondition;
import cn.hs.module.modules.service.IResourceTreeService;
import cn.hs.module.role.ui.commond.RoleResourceTreeCommond;

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
@RequestMapping(value = RoleResourceTreeController.modulePath)
public class RoleResourceTreeController {
	public static final String modulePath = "/module/role";

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
	@RequestMapping(value = "/findRuthorityRoleTree")
	public @ResponseBody
	List<JsonTreeBean> findResourceTree(RoleResourceTreeCommond roleResourceTreeCommond) throws Exception {
		ResourceTreeCondition condition = new ResourceTreeCondition();
		BeanUtils.copyProperties(condition, roleResourceTreeCommond);
		condition.setFindLeapNode(false);
		return resourceTreeService.doProcess(condition);
	}
}
