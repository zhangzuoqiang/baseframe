package cn.hs.module.online.ui.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hs.module.online.domain.OnlineUserCondition;
import cn.hs.module.online.service.IOnlineUserService;
import cn.hs.module.online.ui.commond.OnlineUserCommond;

/**
 * Title: OnlineUserController<br>
 * Description: 在线学员Controller<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * 
 * @author ZhangKW
 * @createDate 2012-5-29
 * @version $Revision: 11 $
 */
@Controller
@RequestMapping(value = OnlineUserController.modulePath)
public class OnlineUserController {

	// 模块根路径
	public static final String modulePath = "/module/online";

	@Autowired
	private IOnlineUserService onlineUserService;

	/**
	 * 列表查询在线人员信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author ZhangKW
	 * @date 2012-5-29
	 */
	@RequestMapping(value = "/listOnlineUser")
	public String listOnlineUser(Model model, HttpServletRequest request, OnlineUserCommond commond) throws Exception {

		// 查询在线人员
		commond.setOnlineUserList(onlineUserService.listOnlineUser(new OnlineUserCondition()));
		model.addAttribute("onlineUserCommond", commond);
		return "/module/portal/homepage/onlineUser.jsp";
	}
}
