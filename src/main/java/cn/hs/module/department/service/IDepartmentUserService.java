package cn.hs.module.department.service;

import java.util.List;

import cn.hs.core.servicetemplate.pagetemplate.IBasePageTemplate;
import cn.hs.module.department.domain.DepartmentUser;
import cn.hs.module.department.domain.DepartmentUserCondition;

/**
 * Title: IDepartmentUserService<br>
 * Description: 部门用户管理业务接口<br>
 * Company: GoldGov<br>
 * Copyright @ 2012 GoldGov .All rights reserved.<br>
 * @author Limk
 * @createDate 2012-08-13
 * @version $Revision$
 */
public interface IDepartmentUserService extends IBasePageTemplate {
	/**
	 * 新增部门用户
	 * 
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-13
	 */
	public void addDepartmentUser(DepartmentUser departmentUser) throws Exception;

	/**
	 * 根据ID查询部门用户
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-13
	 */
	public DepartmentUser getDepartmentUser(DepartmentUserCondition condition) throws Exception;

	/**
	 * 更新部门用户
	 * 
	 * @param departmentUser
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-13
	 */
	public void updateDepartmentUser(DepartmentUser departmentUser) throws Exception;

	/**
	 * 根据ID数组批量作废或启用部门用户
	 * 
	 * @param departmentUser
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-13
	 */
	public void discardOrReuseDepartmentUser(DepartmentUserCondition condition) throws Exception;

	/**
	 * 根据ID数组获得部门用户 批量操作时记录业务日志必须实现该方法，该方法将返回批量操作前数据内容
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-13
	 */
	public List<DepartmentUser> getDepartmentUserListByIDArray(String[] idArray) throws Exception;

	/**
	 * 根据ID获得部门用户 更新操作时记录业务日志必须实现该方法，该方法将返回更新操作前数据内容
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-13
	 */
	public DepartmentUser getDepartmentUserByID(String id) throws Exception;

	/**
	 * count查询基础数据类型集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-13
	 */
	public Long listDepartmentUserCount(DepartmentUserCondition condition) throws Exception;
}
