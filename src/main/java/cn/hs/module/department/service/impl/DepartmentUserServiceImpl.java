package cn.hs.module.department.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hs.core.annotation.BusinessLogForAdd;
import cn.hs.core.annotation.BusinessLogForBatchUpdateState;
import cn.hs.core.annotation.BusinessLogForUpdate;
import cn.hs.core.annotation.CreateInfo;
import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.core.page.commond.BasePageCommond;
import cn.hs.core.page.commond.JSONObject;
import cn.hs.core.servicetemplate.pagetemplate.PageTemplate;
import cn.hs.module.department.dao.IDepartmentUserDao;
import cn.hs.module.department.domain.DepartmentUser;
import cn.hs.module.department.domain.DepartmentUserCondition;
import cn.hs.module.department.domain.jsonbean.DepartmentUserJsonBean;
import cn.hs.module.department.service.IDepartmentUserService;

/**
 * Title: DepartmentUserServiceImpl<br>
 * Description: 部门用户管理业务接口实现<br>
 * Company: GoldGov<br>
 * Copyright @ 2012 GoldGov .All rights reserved.<br>
 * @author Limk
 * @createDate 2012-08-13
 * @version $Revision$
 */
// 声明本类为Server实现类，声明bean名称
@Service(value = "cn.hs.module.department.service.impl.DepartmentUserServiceImpl")
public class DepartmentUserServiceImpl extends PageTemplate implements IDepartmentUserService {

	// 部门用户DAO
	@Autowired
	private IDepartmentUserDao departmentUserDao;

	/**
	 * 新增部门用户
	 * 
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-13
	 */
	@Override
	@BusinessLogForAdd(operationModule = "部门用户")
	@CreateInfo(createTimeMethod = "setCreateTime", creatorMethod = "setCreator")
	public void addDepartmentUser(DepartmentUser departmentUser) throws Exception {
		departmentUserDao.addDepartmentUser(departmentUser);
	}

	/**
	 * 根据ID数组批量作废或启用部门用户
	 * 
	 * @param departmentUser
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-13
	 */
	@Override
	@BusinessLogForBatchUpdateState(entityClass = DepartmentUser.class, getEntityPKMethodName = "getDeptUserID", getObjectsByIDArrayMethodName = "getDepartmentUserListByIDArray", getPKArrayMethodName = "getDeptUserIDs", operationModule = "部门用户", pkArrayClass = String[].class)
	public void discardOrReuseDepartmentUser(DepartmentUserCondition condition) throws Exception {
		departmentUserDao.discardOrReuseDepartmentUser(condition);
	}

	/**
	 * 根据ID查询部门用户
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-13
	 */
	@Override
	public DepartmentUser getDepartmentUser(DepartmentUserCondition condition) throws Exception {
		return departmentUserDao.getDepartmentUser(condition);
	}

	/**
	 * 更新部门用户
	 * 
	 * @param departmentUser
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-13
	 */
	@Override
	@BusinessLogForUpdate(getEntityPKMethodName = "getDeptUserID", getObjectByIDMethodName = "getDepartmentUserByID", operationModule = "部门用户", pkClass = String.class)
	@CreateInfo(createTimeMethod = "setCreateTime", creatorMethod = "setCreator")
	public void updateDepartmentUser(DepartmentUser departmentUser) throws Exception {
		departmentUserDao.updateDepartmentUser(departmentUser);
	}

	/**
	 * 调用PageTemplate模板process方法
	 * 
	 * @param pageCommond
	 * @param condition
	 * @throws Exception
	 * @author Limk
	 * @date Aug 31, 2011
	 */
	@Override
	public JSONObject doProcess(BasePageCommond pageCommond, BaseCondition condition) throws Exception {
		return process(pageCommond, condition);
	}

	/**
	 * count查询部门用户集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date Sep 9, 2011
	 */
	@Override
	protected Long findCount(BaseCondition condition) throws Exception {
		return departmentUserDao.listDepartmentUserCount((DepartmentUserCondition) condition);
	}

	/**
	 * 查询基础数据类型集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date Sep 9, 2011
	 */
	@Override
	protected List<DepartmentUser> findList(BaseCondition condition) throws Exception {
		return departmentUserDao.listDepartmentUser((DepartmentUserCondition) condition);
	}

	/**
	 * 根据ID获得部门用户 更新操作时记录业务日志必须实现该方法，该方法将返回更新操作前数据内容
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-13
	 */
	@Override
	public DepartmentUser getDepartmentUserByID(String id) throws Exception {
		return departmentUserDao.getDepartmentUserByID(id);
	}

	/**
	 * 根据ID数组获得部门用户 批量操作时记录业务日志必须实现该方法，该方法将返回批量操作前数据内容
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-13
	 */
	@Override
	public List<DepartmentUser> getDepartmentUserListByIDArray(String[] idArray) throws Exception {
		return departmentUserDao.getDepartmentUserListByIDArray(idArray);
	}

	@Override
	protected List<?> objListToJsonList(List<?> pageResultList) throws Exception {
		List<DepartmentUserJsonBean> result = new ArrayList<DepartmentUserJsonBean>();
		for (Object object : pageResultList) {
			DepartmentUser departmentUser = (DepartmentUser) object;
			DepartmentUserJsonBean bean = new DepartmentUserJsonBean();
			BeanUtils.copyProperties(bean, departmentUser);
			result.add(bean);
		}
		return result;
	}

	@Override
	public Long listDepartmentUserCount(DepartmentUserCondition condition) throws Exception {
		return departmentUserDao.listDepartmentUserCount(condition);
	}
}
