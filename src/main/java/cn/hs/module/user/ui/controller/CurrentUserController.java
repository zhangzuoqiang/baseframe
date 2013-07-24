package cn.hs.module.user.ui.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import cn.hs.core.springext.mvc.SessionConstant;
import cn.hs.core.springext.security.casclient.domain.json.CasUser;
import cn.hs.core.util.token.IToken;
import cn.hs.module.Constants;
import cn.hs.module.user.domain.User;
import cn.hs.module.user.domain.UserInfoCondition;
import cn.hs.module.user.service.IUserInfoService;
import cn.hs.module.user.ui.commond.UserInfoValidCommond;

/**
 * 
 * Title: CurrentUserController<br>
 * Description: 查询当前登录人信息<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * 
 * @author Mill
 * @createDate 2012-9-13
 * @version $Revision:$
 */
@Controller
@RequestMapping(value = UserInfoController.modulePath)
public class CurrentUserController {

	// 模块页面根路径
	public static final String modulePath = "/module/user";

	@Autowired
	private IUserInfoService iUserInfoService;

	// token接口
	@Autowired
	private IToken token;

	/**
	 * 获取当前登陆人信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date 2013-5-26
	 */
	@RequestMapping(value = "/getCurrentUser")
	public @ResponseBody
	JSONObject getCurrentUser(HttpServletRequest request) throws Exception {
		JSONObject result = new JSONObject();
		CasUser user = (CasUser) request.getSession().getAttribute(SessionConstant.USER_INFO);
		user.setPassWord(null);
		result.setData(user);
		result.setSuccess(true);
		return result;
	}

	/**
	 * 修改登陆人密码
	 * 
	 * @param request
	 * @param userValidCommond
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date 2013-5-26
	 */
	@RequestMapping(value = "/updataCurrentPassWord")
	public @ResponseBody
	JSONObject updataPassWord(HttpServletRequest request, UserInfoValidCommond userValidCommond) throws Exception {
		JSONObject object = new JSONObject();
		if (token.isTokenValid(request, true)) {
			UserInfoCondition userCondition = new UserInfoCondition();
			userCondition.setSearchUserId(userValidCommond.getUserID());
			final User user = iUserInfoService.getUser(userCondition);

			long currentTime = DateUtil.getCurrentTimeLong();
			user.setLastUpdateTime(currentTime);
			// 修改密码
			// 密码存为md5加密密文 加密格式==密码+创建时间
			user.setPassWord(Md5Util.getMd5(userValidCommond.getPassWord() + user.getCreateTime()));
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

}
