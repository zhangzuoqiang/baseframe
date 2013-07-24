package cn.hs.module.email.serverconfig.ui.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hs.core.page.commond.JSONObject;
import cn.hs.core.util.token.IToken;
import cn.hs.module.email.serverconfig.domain.MailServerConfig;
import cn.hs.module.email.serverconfig.domain.MailServerConfigCondition;
import cn.hs.module.email.serverconfig.service.IMailServerConfigService;
import cn.hs.module.email.serverconfig.ui.commond.MailServerConfigCommond;
import cn.hs.module.email.serverconfig.ui.commond.MailServerConfigValidCommond;

/**
 * Title: MailServerConfigController<br>
 * Description: 邮件服务器配置管理控制器<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author LiuHG
 * @createDate 2012-08-01
 * @version $Revision$
 */
@Controller
@RequestMapping(value = MailServerConfigController.modulePath)
public class MailServerConfigController {
	// 模块页面根路径
	public static final String modulePath = "/module/mailserverconfig";
	// 邮件服务器配置接口
	@Autowired
	private IMailServerConfigService iMailServerConfigService;

	// token接口
	@Autowired
	private IToken token;

	/**
	 * 列表查询邮件服务器配置
	 * 
	 * @param model
	 * @param request
	 * @param mailServerConfigCommond
	 * @return
	 * @author LiuHG
	 * @throws Exception
	 * @date 2012-08-01
	 */
	@RequestMapping(value = "/listMailServerConfig")
	public @ResponseBody
	JSONObject listMailServerConfig(MailServerConfigCommond mailServerConfigCommond) throws Exception {
		MailServerConfigCondition condition = new MailServerConfigCondition();
		BeanUtils.copyProperties(mailServerConfigCommond, condition);
		if (condition.getSearchIsEnabled() == null || "".equals(condition.getSearchIsEnabled())) {
			// 列表默认查询启用数据
			// TODO condition.setSearchIsEnabled("1");
		}
		JSONObject result = iMailServerConfigService.doProcess(mailServerConfigCommond, condition);
		return result;
	}

	/**
	 * 预添加邮件服务器配置
	 * 
	 * @param model
	 * @param request
	 * @param mailServerConfigValidCommond
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@RequestMapping(value = "/preAddMailServerConfig")
	public String preAddMailServerConfig(HttpServletRequest request, MailServerConfigValidCommond mailServerConfigValidCommond) throws Exception {
		token.saveToken(request);
		return MailServerConfigController.modulePath + "/addMailServerConfig.jsp";
	}

	/**
	 * 加入token令牌
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@RequestMapping(value = "/saveToken")
	public @ResponseBody
	String preAddMailServerConfig(HttpServletRequest request) throws Exception {
		String tokenStr = token.saveToken(request);
		return tokenStr;
	}

	/**
	 * 添加邮件服务器配置
	 * 
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@RequestMapping(value = "/addMailServerConfig")
	public @ResponseBody
	JSONObject addMailServerConfig(HttpServletRequest request, MailServerConfigValidCommond mailServerConfigValidCommond) throws Exception {
		MailServerConfig mailServerConfig = null;
		JSONObject result = new JSONObject();
		// 用来判断是否为重复提交
		if (token.isTokenValid(request, true)) {
			mailServerConfig = new MailServerConfig();
			BeanUtils.copyProperties(mailServerConfigValidCommond, mailServerConfig);
			iMailServerConfigService.addMailServerConfig(mailServerConfig);
			result.setSuccess(true);
			result.setMsg("添加成功！");

		}
		return result;
	}

	/**
	 * 预更新邮件服务器配置
	 * 
	 * @param model
	 * @param request
	 * @param mailServerConfigCommond
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@RequestMapping(value = "/preUpdateMailServerConfig")
	public @ResponseBody
	JSONObject preUpdateMailServerConfig(MailServerConfigValidCommond mailServerConfigValidCommond) throws Exception {
		MailServerConfigCondition mailServerConfigCondition = null;
		MailServerConfig mailServerConfig = null;
		JSONObject object = new JSONObject();
		// 邮件服务器配置Id是否为空
		if (mailServerConfigValidCommond.getSearchConfigID() != null && !"".equals(mailServerConfigValidCommond.getSearchConfigID())) {
			mailServerConfigCondition = new MailServerConfigCondition();
			mailServerConfigCondition.setSearchConfigID(mailServerConfigValidCommond.getSearchConfigID());
			mailServerConfig = iMailServerConfigService.getMailServerConfig(mailServerConfigCondition);
			BeanUtils.copyProperties(mailServerConfig, mailServerConfigValidCommond);
			// 如果逻辑正确，必须加success为true
			object.setSuccess(true);
			object.setData(mailServerConfigValidCommond);
		}
		return object;
	}

	/**
	 * 更新邮件服务器配置
	 * 
	 * @param model
	 * @param request
	 * @param mailServerConfigCommond
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@RequestMapping(value = "/updataMailServerConfig")
	public @ResponseBody
	JSONObject updataMailServerConfig(HttpServletRequest request, MailServerConfigValidCommond mailServerConfigValidCommond) throws Exception {
		MailServerConfig mailServerConfig = null;
		JSONObject object = new JSONObject();
		// 用来判断是否为重复提交
		if (token.isTokenValid(request, true)) {
			mailServerConfig = new MailServerConfig();
			BeanUtils.copyProperties(mailServerConfigValidCommond, mailServerConfig);
			iMailServerConfigService.updateMailServerConfig(mailServerConfig);
			object.setSuccess(true);
			object.setMsg("修改成功！");
		}
		return object;
	}

	/**
	 * 根据ID数组批量作废或启用邮件服务器配置
	 * 
	 * @param model
	 * @param request
	 * @param mailServerConfigCommond
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@RequestMapping(value = "/discardOrReuseMailServerConfig")
	public @ResponseBody
	JSONObject discardOrReuseMailServerConfig(HttpServletRequest request, MailServerConfigCommond mailServerConfigCommond) throws Exception {
		JSONObject object = new JSONObject();
		if (mailServerConfigCommond.getSearchIsEnabled() != null && !"".equals(mailServerConfigCommond.getSearchIsEnabled()) && mailServerConfigCommond.getSearchConfigIDs() != null && mailServerConfigCommond.getSearchConfigIDs().length > 0) {
			MailServerConfigCondition mailServerConfigCondition = new MailServerConfigCondition();
			mailServerConfigCondition.setSearchConfigIDs(mailServerConfigCommond.getSearchConfigIDs());
			mailServerConfigCondition.setSearchIsEnabled(mailServerConfigCommond.getSearchIsEnabled());
			iMailServerConfigService.discardOrReuseMailServerConfig(mailServerConfigCondition);
			object.setSuccess(true);
			object.setMsg("修改成功！");
		} else {
			object.setSuccess(false);
			object.setMsg("为空");
		}
		return object;
	}
}
