package cn.hs.module.department.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hs.commons.utils.StringTool;
import cn.hs.core.basedao.condition.BaseTreeCondition;
import cn.hs.core.servicetemplate.treetemplate.TreeTemplate;
import cn.hs.core.servicetemplate.treetemplate.jsonbean.JsonTreeBean;
import cn.hs.module.department.dao.IDepartmentTreeDao;
import cn.hs.module.department.domain.Department;
import cn.hs.module.department.domain.DepartmentTreeCondition;
import cn.hs.module.department.domain.jsonbean.DepartmentTreeJsonBean;
import cn.hs.module.department.service.IDepartmentCheckedTreeService;
import cn.hs.module.department.service.IDepartmentService;

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
@Service(value = "cn.hs.module.department.service.impl.DepartmentCheckedTreeServiceImpl")
public class DepartmentCheckedTreeServiceImpl extends TreeTemplate implements IDepartmentCheckedTreeService {

	@Autowired
	private IDepartmentTreeDao departmentTreeDao;

	@Autowired
	private IDepartmentService departmentService;

	@Override
	public List<JsonTreeBean> doProcess(BaseTreeCondition condition) throws Exception {
		return process(condition);
	}

	@Override
	protected List<?> findNextNodeList(BaseTreeCondition condition) throws Exception {
		return departmentTreeDao.listDepartmentCheckedTree((DepartmentTreeCondition) condition);
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
			DepartmentTreeJsonBean department = (DepartmentTreeJsonBean) obj;
			JsonTreeBean jtBean = new JsonTreeBean();
			jtBean.setId(department.getDeptID().toString());
			jtBean.setText(department.getDeptName());
			if (department.getIsChecked() != null && "1".equals(department.getIsChecked())) {
				jtBean.setChecked(true);
			} else {
				jtBean.setChecked(false);
			}
			if (JsonTreeBean.ROOT_NODE_PARENT_ID.equals(department.getParentID().toString())) {
				jtBean.setChecked(null);
			}
			resultList.add(jtBean);
		}
		return resultList;
	}

	@Override
	public Department getDepartment(DepartmentTreeCondition condition) throws Exception {
		return departmentTreeDao.getDepartment(condition);
	}

	/**
	 * 项目参加的部门树
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @createDate 2012-8-2
	 */
	public List<JsonTreeBean> listDepartmentProjectTree(DepartmentTreeCondition condition) throws Exception {

		List<Department> departemtList = departmentTreeDao.listDepartmentProjectTree(condition);
		if (departemtList == null || departemtList.size() == 0) {
			return null;
		}
		List<JsonTreeBean> resultList = new ArrayList<JsonTreeBean>();

		String departmentIds = "";

		List<String> departemtIds = new ArrayList<String>(); // 用来判断是否显示check框
		Set<String> tempIds = new HashSet<String>();
		for (Department obj : departemtList) {
			departemtIds.add(obj.getDeptID());
			departmentIds = obj.getTreepath().substring(0, obj.getTreepath().length() - 1);

			if (obj.getTreepath().indexOf("/") != -1) {
				String[] deptIds = StringTool.delimitedListToStringArray(departmentIds, "/");
				tempIds.addAll(Arrays.asList(deptIds));
			}
		}
		List<Department> tempDeptList = departmentService.getDepartmentListByIDArray(tempIds.toArray(new String[tempIds.size()]));

		tempDeptList.addAll(departemtList);

		Collections.sort(tempDeptList, new Comparator<Department>() {
			@Override
			public int compare(Department o1, Department o2) {
				return (int) (o1.getDeptID().compareTo(o2.getDeptID()));
			}

		});

		Map<String, JsonTreeBean> tempDeptMap = new HashMap<String, JsonTreeBean>();
		for (Department department : tempDeptList) {
			if (!tempDeptMap.containsKey(department.getDeptID())) {
				tempDeptMap.put(department.getDeptID(), this.deptToJsonBean(department, departemtIds));
				if (tempDeptMap.containsKey(department.getParentID())) {
					JsonTreeBean parentBean = tempDeptMap.get(department.getParentID());
					parentBean.setExpandable(true);
					parentBean.setLeaf(JsonTreeBean.IS_NOT_LEAP_NODE_EXT);
					List<JsonTreeBean> childrenList = parentBean.getChildren();
					if (childrenList == null) {
						childrenList = new ArrayList<JsonTreeBean>();
					}
					childrenList.add(tempDeptMap.get(department.getDeptID()));
					parentBean.setChildren(childrenList);
				}
			}
		}

		resultList.add(tempDeptMap.get(-1L));
		return resultList;
	}

	/**
	 * 部门转为jsonbean
	 * 
	 * @param department
	 * @param tempList
	 * @return
	 * @author HuangS
	 * @date Aug 29, 2012
	 */
	private JsonTreeBean deptToJsonBean(Department department, List<String> tempList) {
		JsonTreeBean bean = new JsonTreeBean();
		bean.setId(department.getDeptID().toString());
		bean.setText(department.getDeptName());
		bean.setParentID(department.getParentID().toString());
		if (tempList.contains(department.getDeptID())) {
			bean.setChecked(false);
		}
		bean.setLeaf(JsonTreeBean.IS_LEAP_NODE_EXT);
		return bean;
	}

	@Override
	public List<JsonTreeBean> listCompanyTree(DepartmentTreeCondition condition) throws Exception {
		List<Department> departemtList = departmentTreeDao.listDepartmentProjectTree(condition);
		if (departemtList == null || departemtList.size() == 0) {
			return null;
		}
		List<JsonTreeBean> resultList = new ArrayList<JsonTreeBean>();

		String departmentIds = "";

		List<String> departemtIds = new ArrayList<String>(); // 用来判断是否显示check框
		Set<String> tempIds = new HashSet<String>();
		for (Department obj : departemtList) {
			departemtIds.add(obj.getDeptID());
			departmentIds = obj.getTreepath().substring(0, obj.getTreepath().length() - 1);

			if (obj.getTreepath().indexOf("/") != -1) {
				String[] deptIds = StringTool.delimitedListToStringArray(departmentIds, "/");
				tempIds.addAll(Arrays.asList(deptIds));
			}
		}
		List<Department> tempDeptList = departmentService.getDepartmentListByIDArray(tempIds.toArray(new String[tempIds.size()]));

		tempDeptList.addAll(departemtList);

		Collections.sort(tempDeptList, new Comparator<Department>() {
			@Override
			public int compare(Department o1, Department o2) {
				return o1.getDeptID().compareTo(o2.getDeptID());
			}

		});

		Map<String, JsonTreeBean> tempDeptMap = new HashMap<String, JsonTreeBean>();
		for (Department department : tempDeptList) {
			if (!tempDeptMap.containsKey(department.getDeptID())) {
				tempDeptMap.put(department.getDeptID(), this.deptToJsonBean(department, departemtIds));
				if (tempDeptMap.containsKey(department.getParentID())) {
					JsonTreeBean parentBean = tempDeptMap.get(department.getParentID());
					parentBean.setExpandable(true);
					parentBean.setLeaf(JsonTreeBean.IS_NOT_LEAP_NODE_EXT);
					List<JsonTreeBean> childrenList = parentBean.getChildren();
					if (childrenList == null) {
						childrenList = new ArrayList<JsonTreeBean>();
					}
					childrenList.add(tempDeptMap.get(department.getDeptID()));
					parentBean.setChildren(childrenList);
				}
			}
		}

		resultList.add(tempDeptMap.get(-1L));
		return resultList;
	}

}
