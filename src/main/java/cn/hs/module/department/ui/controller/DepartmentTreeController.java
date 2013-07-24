package cn.hs.module.department.ui.controller;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hs.core.servicetemplate.treetemplate.jsonbean.JsonTreeBean;
import cn.hs.module.department.domain.DepartmentTreeCondition;
import cn.hs.module.department.service.IDepartmentTreeService;
import cn.hs.module.department.ui.commond.DepartmentTreeCommond;

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
@RequestMapping(value = DepartmentTreeController.modulePath)
public class DepartmentTreeController {

	public static final String modulePath = "/module/department";

	@Autowired
	private IDepartmentTreeService departmentTreeService;

	/**
	 * 查询部门tree下一级节点集合
	 * 
	 * @param commond
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @createDate 2012-8-2
	 */
	@RequestMapping(value = "/findDepartmentTree")
	public @ResponseBody
	List<JsonTreeBean> findDepartmentTree(DepartmentTreeCommond commond) throws Exception {
		DepartmentTreeCondition condition = new DepartmentTreeCondition();
		BeanUtils.copyProperties(condition, commond);
		condition.setFindLeapNode(true);
		List<JsonTreeBean> list = departmentTreeService.doProcess(condition);
		return list;
	}

}
