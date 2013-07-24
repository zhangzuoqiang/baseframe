package cn.hs.module.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hs.commons.utils.Md5Util;
import cn.hs.commons.utils.PropertysUtil;
import cn.hs.core.annotation.BusinessLogForAdd;
import cn.hs.core.annotation.BusinessLogForBatchUpdateState;
import cn.hs.core.annotation.CreateInfo;
import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.core.page.commond.BasePageCommond;
import cn.hs.core.page.commond.JSONObject;
import cn.hs.core.servicetemplate.pagetemplate.PageTemplate;
import cn.hs.module.department.domain.Department;
import cn.hs.module.user.dao.IUserInfoDao;
import cn.hs.module.user.domain.User;
import cn.hs.module.user.domain.UserInfoCondition;
import cn.hs.module.user.service.IUserInfoService;

/**
 * 
 * Title: UserInfoServiceImpl<br>
 * Description: <br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * 
 * @author XiangBin
 * @createDate Jul 31, 2012
 * @version $Revision:$
 */
@Service(value = "cn.hs.module.user.service.impl.UserInfoServiceImpl")
public class UserInfoServiceImpl extends PageTemplate implements IUserInfoService {

	@Autowired
	private IUserInfoDao userInfoDao;

	/**
	 * 获取当前用户所在部门
	 * 
	 * @param userID
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 */
	@Override
	public List<Department> getCurrentDepartment(UserInfoCondition condition) throws Exception {
		return userInfoDao.getCurrentDepartment(condition);
	}

	/**
	 * 根据登入ID查询用户信息
	 * 
	 * @param loginID
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @date Jul 30, 2012
	 */
	@Override
	public List<User> getUserInfo(UserInfoCondition condition) throws UsernameNotFoundException {
		return userInfoDao.getUserInfo(condition);
	}

	/**
	 * 获取当前用户管理的部门范围
	 * 
	 * @param userID
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 */
	@Override
	public List<Department> getCurrentManageScope(UserInfoCondition condition) throws Exception {
		return userInfoDao.getCurrentManageScope(condition);
	}

	/**
	 * 新增UserService
	 * 
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	@Override
	@BusinessLogForAdd(operationModule = "人员信息")
	@CreateInfo(createTimeMethod = "setCreateTime", creatorMethod = "setCreator")
	public void addUser(User user) throws Exception {
		userInfoDao.addUser(user);
	}

	@Transactional(readOnly = false)
	public void sysUserWithSys(List<User> userList) throws Exception {
		userInfoDao.sysUserWithSys(userList);
	}

	/**
	 * 根据ID数组批量作废或启用UserService
	 * 
	 * @param user
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	@Override
	@BusinessLogForBatchUpdateState(entityClass = User.class, getEntityPKMethodName = "getUserId", getObjectsByIDArrayMethodName = "getUserListByIDArray", getPKArrayMethodName = "getSearchUserIds", operationModule = "人员信息", pkArrayClass = String[].class)
	public void discardOrReuseUser(UserInfoCondition condition) throws Exception {
		userInfoDao.discardOrReuseUser(condition);
	}

	/**
	 * 根据ID查询UserService
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	@Override
	public User getUser(UserInfoCondition condition) throws Exception {
		return userInfoDao.getUser(condition);
	}

	/**
	 * 更新UserService
	 * 
	 * @param user
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	@Override
	// @BusinessLogForUpdate(getEntityPKMethodName = "getUserID",
	// getObjectByIDMethodName = "getUserByID", operationModule = "人员信息",
	// pkClass = String.class)
	// @CreateInfo(createTimeMethod = "setCreateTime", creatorMethod =
	// "setCreator")
	public void updateUser(User user) throws Exception {
		userInfoDao.updateUser(user);
	}

	/**
	 * 根据UserService编码查询UserService信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	@Override
	public User viewUser(UserInfoCondition condition) throws Exception {
		return userInfoDao.viewUser(condition);
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
	 * count查询UserService集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date Sep 9, 2011
	 */
	@Override
	protected Long findCount(BaseCondition condition) throws Exception {
		return userInfoDao.listUserCount((UserInfoCondition) condition);
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
	protected List<User> findList(BaseCondition condition) throws Exception {
		return userInfoDao.listUser((UserInfoCondition) condition);
	}

	/**
	 * 根据ID获得UserService 更新操作时记录业务日志必须实现该方法，该方法将返回更新操作前数据内容
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	@Override
	public User getUserByID(String id) throws Exception {
		return userInfoDao.getUserByID(id);
	}

	/**
	 * 根据ID数组获得UserService 批量操作时记录业务日志必须实现该方法，该方法将返回批量操作前数据内容
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	@Override
	public List<User> getUserListByIDArray(String[] idArray) throws Exception {
		return userInfoDao.getUserListByIDArray(idArray);
	}

	@Override
	public boolean checkIsRepeatUser(String loginID, String userID) throws Exception {
		UserInfoCondition condition = new UserInfoCondition();
		condition.setSearchLoginID(loginID);

		List<User> list = userInfoDao.getUserInfo(condition);
		if (list != null && !list.isEmpty()) {
			if (userID != null) {
				if (list.get(0).getUserID().equals(userID)) {
					return true;
				}
			}
			return false;// 如果list不为空，则说明有重复的编码
		}
		return true;// 否则返回真，说明没有重复的编码
	}

	@Override
	@Transactional(readOnly = false)
	public void passWordInit(UserInfoCondition condition) throws Exception {
		List<User> userList = userInfoDao.getUserListByIDArray(condition.getSearchUserIds());
		String initPassWord = PropertysUtil.getContextProperty("initPassWord");
		for (User user : userList) {
			user.setPassWord(Md5Util.getMd5(initPassWord + user.getCreateTime()));
		}
	}

}
