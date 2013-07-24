package cn.hs.module.department.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hs.core.basedao.condition.BaseTreeCondition;
import cn.hs.core.servicetemplate.treetemplate.TreeTemplate;
import cn.hs.core.servicetemplate.treetemplate.jsonbean.JsonTreeBean;
import cn.hs.module.department.dao.IDepartmentTreeDao;
import cn.hs.module.department.domain.Department;
import cn.hs.module.department.domain.DepartmentTreeCondition;
import cn.hs.module.department.service.IDepartmentTreeService;

/**
 * 
 * Title: DepartmentTreeServiceImpl<br>
 * Description: 部门树service实现类<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author Mill
 * @createDate 2012-8-2
 * @version $Revision:$
 */
@Service(value = "cn.hs.module.department.service.impl.DepartmentTreeServiceImpl")
public class DepartmentTreeServiceImpl extends TreeTemplate implements IDepartmentTreeService {

	@Autowired
	private IDepartmentTreeDao departmentTreeDao;

	@Override
	public List<JsonTreeBean> doProcess(BaseTreeCondition condition) throws Exception {
		return process(condition);
	}

	@Override
	protected List<?> findNextNodeList(BaseTreeCondition condition) throws Exception {
		return departmentTreeDao.listDepartmentTree((DepartmentTreeCondition) condition);
	}

	@Override
	protected boolean isLeapNode(BaseTreeCondition condition) throws Exception {
		if (condition.getBasebean() != null) {
			condition.setBasebean(null);
		}
		return departmentTreeDao.isLeapNode((DepartmentTreeCondition) condition);
	}

	@Override
	protected List<JsonTreeBean> objListToJsonList(List<?> treeNodeList) throws Exception {
		List<JsonTreeBean> resultList = new ArrayList<JsonTreeBean>();
		for (Object obj : treeNodeList) {
			Department department = (Department) obj;
			JsonTreeBean jtBean = new JsonTreeBean();
			jtBean.setId(department.getDeptID().toString());
			jtBean.setText(department.getDeptName());
			resultList.add(jtBean);
		}
		return resultList;
	}

	@Override
	public Department getDepartment(DepartmentTreeCondition condition) throws Exception {
		return departmentTreeDao.getDepartment(condition);
	}

}
