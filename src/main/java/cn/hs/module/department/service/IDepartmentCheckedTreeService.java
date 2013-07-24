package cn.hs.module.department.service;

import java.util.List;

import cn.hs.core.servicetemplate.treetemplate.IBaseTreeTemplate;
import cn.hs.core.servicetemplate.treetemplate.jsonbean.JsonTreeBean;
import cn.hs.module.department.domain.Department;
import cn.hs.module.department.domain.DepartmentTreeCondition;

/**
 * 
 * Title: IDepartmentTreeService<br>
 * Description: 部门树service接口<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author Mill
 * @createDate 2012-8-2
 * @version $Revision:$
 */
public interface IDepartmentCheckedTreeService extends IBaseTreeTemplate {

	/**
	 * 根据ID查询部门信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @createDate 2012-8-2
	 */
	public Department getDepartment(DepartmentTreeCondition condition) throws Exception;

	/**
	 * 项目参加的部门树
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @createDate 2012-8-2
	 */
	public List<JsonTreeBean> listDepartmentProjectTree(DepartmentTreeCondition condition) throws Exception;

	/**
	 * 公司树，目前只有在简历管理员的功能调用。
	 * @param condition
	 * @return
	 * @author ChenC
	 * @throws Exception 
	 * @date 2012-10-08
	 */
	public List<JsonTreeBean> listCompanyTree(DepartmentTreeCondition condition) throws Exception;
}
