package cn.hs.module.httpserver.userserver;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hs.commons.utils.JSONUtils;
import cn.hs.commons.utils.Md5Util;
import cn.hs.commons.utils.PropertyUtil;
import cn.hs.core.page.commond.JSONObject;
import cn.hs.module.httpserver.userserver.domain.UserHttpServerCommond;
import cn.hs.module.user.domain.User;
import cn.hs.module.user.domain.UserInfoCondition;
import cn.hs.module.user.service.IUserInfoService;

/**
 * 
 * Title: UserHttpServer<br>
 * Description: 用户httpServer<br>
 * Company: HsS<br>
 * Copyright @ 2013 HsS .All rights reserved.<br>
 * @author HuangS
 * @createDate May 13, 2013
 * @version $Revision:$
 */
@Controller
@RequestMapping(value = UserHttpServer.modulePath)
public class UserHttpServer {

	protected static final String modulePath = "/httpserver/userserver";

	@Autowired
	private IUserInfoService userInfoService;

	/**
	 * 同步用户
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date May 13, 2013
	 */
	@RequestMapping(value = "/syncUserInfo")
	public @ResponseBody
	JSONObject syncUserInfo(UserHttpServerCommond commond) throws Exception {
		JSONObject object = new JSONObject();
		String time = commond.getTime();
		String md5 = commond.getMd5();
		String sid = commond.getSid();
		if (!PropertyUtil.objectNotEmpty(time) || !PropertyUtil.objectNotEmpty(md5) || !PropertyUtil.objectNotEmpty(sid)) {
			object.setSuccess(false);
			object.setMsg("验证信息为空！");
		} else {
			String userStr = commond.getUserStr();
			userStr = URLDecoder.decode(userStr, "UTF-8");
			User user = JSONUtils.jsonToObj(userStr, User.class);
			String tempMD5 = Md5Util.getMd5(user.toString() + sid + time);
			if (tempMD5.equals(md5)) {
				UserInfoCondition condition = new UserInfoCondition();
				condition.setSearchUserId(user.getUserID());
				User dbUser = userInfoService.getUser(condition);
				if (dbUser != null) {
					userInfoService.updateUser(user);
				} else {
					List<User> list = new ArrayList<User>();
					list.add(user);
					userInfoService.sysUserWithSys(list);
				}
				object.setSuccess(true);
				object.setMsg("同步用户成功！");
			} else {
				object.setSuccess(false);
				object.setMsg("验证失败！");
			}
		}
		return object;
	}
}
