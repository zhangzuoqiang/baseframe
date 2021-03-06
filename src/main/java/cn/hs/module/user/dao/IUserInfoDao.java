package cn.hs.module.user.dao;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import cn.hs.module.department.domain.Department;
import cn.hs.module.user.domain.User;
import cn.hs.module.user.domain.UserInfoCondition;

/**
 * 
 * Title: RoleInfoDao<br>
 * Description: <br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author XiangBin
 * @createDate Jul 30, 2012
 * @version $Revision:$
 */
public interface IUserInfoDao {

	/**
	 * 查询用户的信息
	 * 
	 * @param loginID
	 * @param password
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @date Jul 31, 2012
	 */
	public List<User> getUserInfo(UserInfoCondition condition) throws UsernameNotFoundException;

	/**
	 * 获取指定用户所在部门
	 * 
	 * @param userID
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 */
	public List<Department> getCurrentDepartment(UserInfoCondition condition) throws Exception;

	/**
	 * 获取当前用户管理的部门范围
	 * 
	 * @param userID
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 */
	public List<Department> getCurrentManageScope(UserInfoCondition condition) throws Exception;

	/**
	 * 新增UserService
	 * 
	 * @param user
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	public void addUser(User user) throws Exception;

	/**
	 * 根据ID数组批量作废或启用UserService
	 * 
	 * @param condition
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	public void discardOrReuseUser(UserInfoCondition condition) throws Exception;

	/**
	 * 根据Id查询UserService
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	public User getUser(UserInfoCondition condition) throws Exception;

	/**
	 * 查询UserService集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	public List<User> getUserList(UserInfoCondition condition) throws Exception;

	/**
	 * 更新UserService
	 * 
	 * @param user
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	public void updateUser(User user) throws Exception;

	/**
	 * 根据UserService编码查询UserService信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	public User viewUser(UserInfoCondition condition) throws Exception;

	/**
	 * 查询UserService集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	public List<User> listUser(UserInfoCondition condition) throws Exception;

	/**
	 * count查询UserService集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	public Long listUserCount(UserInfoCondition condition) throws Exception;

	/**
	 * 根据ID数组获得UserService 批量操作时记录业务日志必须实现该方法，该方法将返回批量操作前数据内容
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	public List<User> getUserListByIDArray(String[] idArray) throws Exception;

	/**
	 * 根据ID获得UserService 更新操作时记录业务日志必须实现该方法，该方法将返回更新操作前数据内容
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	public User getUserByID(String id) throws Exception;

	/**
	 * 同步用户
	 * 
	 * @param userList
	 * @throws Exception
	 * @author HuangS
	 * @date May 19, 2013
	 */
	public void sysUserWithSys(List<User> userList) throws Exception;

}
