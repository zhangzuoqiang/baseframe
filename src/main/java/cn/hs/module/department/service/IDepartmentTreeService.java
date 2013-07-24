package cn.hs.module.department.service;

import cn.hs.core.servicetemplate.treetemplate.IBaseTreeTemplate;
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
public interface IDepartmentTreeService extends IBaseTreeTemplate {

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

}
