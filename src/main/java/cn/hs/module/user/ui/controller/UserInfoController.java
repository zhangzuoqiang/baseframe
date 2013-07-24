package cn.hs.module.user.ui.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hs.commons.DateUtil;
import cn.hs.commons.utils.HttpUtil;
import cn.hs.commons.utils.JSONUtils;
import cn.hs.commons.utils.Md5Util;
import cn.hs.commons.utils.PropertysUtil;
import cn.hs.core.page.commond.JSONObject;
import cn.hs.core.util.token.IToken;
import cn.hs.module.Constants;
import cn.hs.module.department.domain.Department;
import cn.hs.module.department.domain.DepartmentUser;
import cn.hs.module.department.service.IDepartmentUserService;
import cn.hs.module.user.domain.User;
import cn.hs.module.user.domain.UserInfoCondition;
import cn.hs.module.user.service.IUserInfoService;
import cn.hs.module.user.ui.commond.UserInfoCommond;
import cn.hs.module.user.ui.commond.UserInfoValidCommond;

/**
 * Title: UserController<br>
 * Description: UserService管理控制器<br>
 * Company: GoldGov<br>
 * Copyright @ 2012 GoldGov .All rights reserved.<br>
 * 
 * @author Limk
 * @createDate 2012-08-01
 * @version $Revision$
 */
@Controller
@RequestMapping(value = UserInfoController.modulePath)
public class UserInfoController {
	// 模块页面根路径
	public static final String modulePath = "/module/user";
	// UserService接口
	@Autowired
	private IUserInfoService iUserInfoService;
	@Autowired
	private IDepartmentUserService departmentUserService;

	// token接口
	@Autowired
	private IToken token;

	/**
	 * 列表查询UserService
	 * 
	 * @param model
	 * @param request
	 * @param userCommond
	 * @return
	 * @author Limk
	 * @throws Exception
	 * @date 2012-08-01
	 */
	@RequestMapping(value = "/listUser")
	public @ResponseBody
	JSONObject listUser(UserInfoCommond userCommond) throws Exception {
		UserInfoCondition condition = new UserInfoCondition();
		BeanUtils.copyProperties(userCommond, condition);
		if (condition.getSearchIsEnabled() == null || "".equals(condition.getSearchIsEnabled())) {
			// 列表默认查询启用数据
			// TODO condition.setSearchIsEnabled("1");
		}
		JSONObject result = iUserInfoService.doProcess(userCommond, condition);
		return result;
	}

	/**
	 * 预添加UserService
	 * 
	 * @param model
	 * @param request
	 * @param userValidCommond
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	@RequestMapping(value = "/preAddUser")
	public @ResponseBody
	JSONObject preAddUser(HttpServletRequest request, UserInfoValidCommond userValidCommond) throws Exception {
		JSONObject object = new JSONObject();
		object.setSuccess(true);
		object.setData(userValidCommond);
		return object;
	}

	/**
	 * 加入token令牌
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	@RequestMapping(value = "/saveToken")
	public @ResponseBody
	String preAddUser(HttpServletRequest request) throws Exception {
		String tokenStr = token.saveToken(request);
		return tokenStr;
	}

	/**
	 * 添加UserService
	 * 
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	@RequestMapping(value = "/addUser")
	public @ResponseBody
	JSONObject addUser(HttpServletRequest request, UserInfoValidCommond userValidCommond) throws Exception {
		JSONObject result = new JSONObject();
		// 用来判断是否为重复提交
		if (token.isTokenValid(request, true)) {
			final User user = new User();
			if (userValidCommond.getBirthday() != null) {
				user.setBirthday(userValidCommond.getBirthday().getTime());
			}
			long currentTime = DateUtil.getCurrentTimeLong();
			user.setLoginID(userValidCommond.getLoginID());
			user.setUserName(userValidCommond.getUserName());
			user.setCreateTime(currentTime);
			user.setLastUpdateTime(currentTime);
			user.setOrderNum(userValidCommond.getOrderNum());
			user.setEmail(userValidCommond.getEmail());
			user.setMobileTelephone(userValidCommond.getMobileTelephone());
			user.setRemark(userValidCommond.getRemark());
			if (userValidCommond.getSexCode() != null) {
				user.setSexCode(userValidCommond.getSexCode());
			}
			// 密码存为md5加密密文 加密格式==密码+创建时间
			user.setPassWord(Md5Util.getMd5(userValidCommond.getPassWord() + currentTime));
			Department department = new Department();
			department.setDeptID(userValidCommond.getDepartmentId());
			user.setActiveState(User.IS_ACTIVE_Y);
			iUserInfoService.addUser(user);

			DepartmentUser departmentUser = new DepartmentUser();
			departmentUser.setUser(user);
			departmentUser.setDepartment(department);
			departmentUserService.addDepartmentUser(departmentUser);

			result.setSuccess(true);
			result.setMsg("添加成功！");

			int serverPort = request.getServerPort();
			String port = PropertysUtil.getContextProperty("serverPort");
			if (port != null) {
				port = port.trim();
				if (!"".equals(port)) {
					List<String> list = new ArrayList<String>(Arrays.asList(port.split(",")));
					if (list.contains(serverPort + "")) {
						serverPort = 80;
					}
				}
			}
			final String requestURL = request.getServerName() + ":" + serverPort + request.getContextPath();

			final String systemPath = PropertysUtil.getContextProperty(Constants.SYSTEM_PATH);
			// 开启线程同步
			new Thread() {
				@Override
				public void run() {
					String[] sysPaths = systemPath.split(",");
					for (String string : sysPaths) {
						if (string.indexOf(requestURL) == -1) {
							String serverUrl = string + "/httpserver/userserver/syncUserInfo.json?";
							String sid = "1234";
							String time = DateUtil.getCurrentTimeLong().toString();
							String md5 = Md5Util.getMd5(user.toString() + sid + time);
							String userStr = null;
							try {
								userStr = JSONUtils.objToJson(user);
								userStr = URLEncoder.encode(userStr, "UTF-8");
								serverUrl += "userStr=" + userStr;
								serverUrl += "&md5=" + md5;
								serverUrl += "&time=" + time;
								serverUrl += "&sid=" + sid;
								System.out.println(serverUrl);
								String json = HttpUtil.getHttpUrlContent(serverUrl, "utf8", true, "");
								System.out.println(json);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}.start();

		}
		return result;
	}

	/**
	 * 预更新UserService
	 * 
	 * @param model
	 * @param request
	 * @param userCommond
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	@RequestMapping(value = "/preUpdateUser")
	public @ResponseBody
	JSONObject preUpdateUser(UserInfoValidCommond userValidCommond) throws Exception {
		UserInfoCondition userCondition = null;
		User user = null;
		JSONObject object = new JSONObject();
		// UserServiceId是否为空
		if (userValidCommond.getSearchUserId() != null && !"".equals(userValidCommond.getSearchUserId())) {
			userCondition = new UserInfoCondition();
			userCondition.setSearchUserId(userValidCommond.getSearchUserId());
			user = iUserInfoService.getUser(userCondition);
			userValidCommond.setUserID(user.getUserID());
			userValidCommond.setActiveState(user.getActiveState());
			userValidCommond.setLoginID(user.getLoginID());
			userValidCommond.setUserName(user.getUserName());
			userValidCommond.setOrderNum(user.getOrderNum());
			userValidCommond.setEmail(user.getEmail());
			userValidCommond.setMobileTelephone(user.getMobileTelephone());
			userValidCommond.setRemark(user.getRemark());
			userValidCommond.setPassWord(user.getPassWord());
			if (user.getBirthday() != null) {
				userValidCommond.setBirthday(new Date(user.getBirthday()));
			}
			if (user.getSexCode() != null) {
				userValidCommond.setSexCode(user.getSexCode());
			}
			object.setSuccess(true);
			object.setData(userValidCommond);
		} else {
			object.setSuccess(false);
		}
		return object;
	}

	/**
	 * 更新UserService
	 * 
	 * @param model
	 * @param request
	 * @param userCommond
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	@RequestMapping(value = "/updataUser")
	public @ResponseBody
	JSONObject updataUser(HttpServletRequest request, UserInfoValidCommond userValidCommond) throws Exception {
		JSONObject object = new JSONObject();
		// 用来判断是否为重复提交
		if (token.isTokenValid(request, true)) {
			UserInfoCondition userCondition = new UserInfoCondition();
			userCondition.setSearchUserId(userValidCommond.getUserID());
			final User user = iUserInfoService.getUser(userCondition);

			long currentTime = DateUtil.getCurrentTimeLong();
			user.setLoginID(userValidCommond.getLoginID());
			user.setUserName(userValidCommond.getUserName());
			user.setLastUpdateTime(currentTime);
			user.setOrderNum(userValidCommond.getOrderNum());
			user.setEmail(userValidCommond.getEmail());
			user.setMobileTelephone(userValidCommond.getMobileTelephone());
			user.setRemark(userValidCommond.getRemark());
			if (userValidCommond.getSexCode() != null) {
				user.setSexCode(userValidCommond.getSexCode());
			}
			if (userValidCommond.getActiveState() != null) {
				user.setActiveState(userValidCommond.getActiveState());
			} else if (userValidCommond.getActiveState() == null) {
				user.setActiveState(User.IS_ACTIVE_Y);
			}
			iUserInfoService.updateUser(user);
			object.setSuccess(true);
			object.setMsg("修改成功！");

			int serverPort = request.getServerPort();
			String port = PropertysUtil.getContextProperty("serverPort");
			if (port != null) {
				port = port.trim();
				if (!"".equals(port)) {
					List<String> list = new ArrayList<String>(Arrays.asList(port.split(",")));
					if (list.contains(serverPort + "")) {
						serverPort = 80;
					}
				}
			}
			final String requestURL = request.getServerName() + ":" + serverPort + request.getContextPath();

			final String systemPath = PropertysUtil.getContextProperty(Constants.SYSTEM_PATH);
			// 开启线程同步
			new Thread() {
				@Override
				public void run() {
					String[] sysPaths = systemPath.split(",");
					for (String string : sysPaths) {
						if (string.indexOf(requestURL) == -1) {
							String serverUrl = string + "/httpserver/userserver/syncUserInfo.json?";
							String sid = "1234";
							String time = DateUtil.getCurrentTimeLong().toString();
							String md5 = Md5Util.getMd5(user.toString() + sid + time);
							String userStr = null;
							try {
								userStr = JSONUtils.objToJson(user);
								userStr = URLEncoder.encode(userStr, "UTF-8");
								serverUrl += "userStr=" + userStr;
								serverUrl += "&md5=" + md5;
								serverUrl += "&time=" + time;
								serverUrl += "&sid=" + sid;
								System.out.println(serverUrl);
								String json = HttpUtil.getHttpUrlContent(serverUrl, "utf8", true, "");
								System.out.println(json);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}.start();
		}
		return object;
	}

	/**
	 * 根据ID数组批量作废或启用UserService
	 * 
	 * @param model
	 * @param request
	 * @param userCommond
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date 2012-08-01
	 */
	@RequestMapping(value = "/discardOrReuseUser")
	public @ResponseBody
	JSONObject discardOrReuseUser(HttpServletRequest request, UserInfoCommond userCommond) throws Exception {
		JSONObject object = new JSONObject();
		if (userCommond.getSearchIsEnabled() != null && !"".equals(userCommond.getSearchIsEnabled()) && userCommond.getSearchUserIds() != null && userCommond.getSearchUserIds().length > 0) {
			UserInfoCondition userCondition = new UserInfoCondition();
			userCondition.setSearchUserIds(userCommond.getSearchUserIds());
			userCondition.setSearchIsEnabled(userCommond.getSearchIsEnabled());
			// 人员停用验证
			// if
			// (User.IS_ACTIVE_N.toString().equals(userCommond.getSearchIsEnabled()))
			// {
			// }
			iUserInfoService.discardOrReuseUser(userCondition);
			object.setSuccess(true);
			object.setMsg("修改成功！");
		} else {
			object.setSuccess(false);
			object.setMsg("操作数据为空");
		}
		return object;
	}

	@RequestMapping(value = "/checkIsRepeatUser")
	public @ResponseBody
	JSONObject checkIsRepeatUser(UserInfoValidCommond userValidCommond) throws Exception {
		JSONObject object = new JSONObject();
		if (userValidCommond.getLoginID() != null) {
			if (iUserInfoService.checkIsRepeatUser(userValidCommond.getLoginID(), userValidCommond.getUserID())) {
				object.setData("true");// 没有重复 的登陆id可以提交
				object.setSuccess(true);
				return object;
			}
		}
		object.setMsg("登陆名不能重复");
		object.setSuccess(false);
		return object;
	}

	/**
	 * 密码重置
	 * 
	 * @param userCommond
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date 2013-5-26
	 */
	@RequestMapping(value = "/passWordInit")
	public @ResponseBody
	JSONObject passWordInit(UserInfoCommond userCommond) throws Exception {
		JSONObject object = new JSONObject();
		if (userCommond.getSearchUserIds() != null && userCommond.getSearchUserIds().length > 0) {
			UserInfoCondition condition = new UserInfoCondition();
			condition.setSearchUserIds(userCommond.getSearchUserIds());
			iUserInfoService.passWordInit(condition);
			object.setMsg("重置密码成功！");
			object.setSuccess(true);
		} else {
			object.setMsg("操作数据为空！");
			object.setSuccess(false);
		}
		return object;
	}
}
