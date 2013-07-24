package cn.hs.module.email.sendlog.ui.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hs.core.page.commond.JSONObject;
import cn.hs.core.util.token.IToken;
import cn.hs.module.email.sendlog.domain.MailSendLog;
import cn.hs.module.email.sendlog.domain.MailSendLogCondition;
import cn.hs.module.email.sendlog.service.IMailSendLogService;
import cn.hs.module.email.sendlog.ui.commond.MailSendLogCommond;
import cn.hs.module.email.sendlog.ui.commond.MailSendLogValidCommond;

/**
 * Title: MailSendLogController<br>
 * Description: 邮件发送日志管理控制器<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author LiuHG
 * @createDate 2012-08-02
 * @version $Revision$
 */
@Controller
@RequestMapping(value = MailSendLogController.modulePath)
public class MailSendLogController {
	// 模块页面根路径
	public static final String modulePath = "/module/mailsendlog";
	// 邮件发送日志接口
	@Autowired
	private IMailSendLogService iMailSendLogService;

	// token接口
	@Autowired
	private IToken token;

	/**
	 * 列表查询邮件发送日志
	 * 
	 * @param model
	 * @param request
	 * @param mailSendLogCommond
	 * @return
	 * @author LiuHG
	 * @throws Exception
	 * @date 2012-08-02
	 */
	@RequestMapping(value = "/listMailSendLog")
	public @ResponseBody
	JSONObject listMailSendLog(MailSendLogCommond mailSendLogCommond) throws Exception {
		MailSendLogCondition condition = new MailSendLogCondition();
		BeanUtils.copyProperties(mailSendLogCommond, condition);
		if (condition.getSearchIsEnabled() == null || "".equals(condition.getSearchIsEnabled())) {
			// 列表默认查询启用数据
			// TODO condition.setSearchIsEnabled("1");
		}
		JSONObject result = iMailSendLogService.doProcess(mailSendLogCommond, condition);
		return result;
	}

	/**
	 * 预添加邮件发送日志
	 * 
	 * @param model
	 * @param request
	 * @param mailSendLogValidCommond
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	@RequestMapping(value = "/preAddMailSendLog")
	public String preAddMailSendLog(HttpServletRequest request, MailSendLogValidCommond mailSendLogValidCommond) throws Exception {
		token.saveToken(request);
		return MailSendLogController.modulePath + "/addMailSendLog.jsp";
	}

	/**
	 * 加入token令牌
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	@RequestMapping(value = "/saveToken")
	public @ResponseBody
	String preAddMailSendLog(HttpServletRequest request) throws Exception {
		String tokenStr = token.saveToken(request);
		return tokenStr;
	}

	/**
	 * 添加邮件发送日志
	 * 
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	@RequestMapping(value = "/addMailSendLog")
	public @ResponseBody
	JSONObject addMailSendLog(HttpServletRequest request, MailSendLogValidCommond mailSendLogValidCommond) throws Exception {
		MailSendLog mailSendLog = null;
		JSONObject result = new JSONObject();
		// 用来判断是否为重复提交
		if (token.isTokenValid(request, true)) {
			mailSendLog = new MailSendLog();
			BeanUtils.copyProperties(mailSendLogValidCommond, mailSendLog);
			iMailSendLogService.addMailSendLog(mailSendLog);
			result.setSuccess(true);
			result.setMsg("添加成功！");

		}
		return result;
	}

	/**
	 * 预更新邮件发送日志
	 * 
	 * @param model
	 * @param request
	 * @param mailSendLogCommond
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	@RequestMapping(value = "/preUpdateMailSendLog")
	public @ResponseBody
	JSONObject preUpdateMailSendLog(MailSendLogValidCommond mailSendLogValidCommond) throws Exception {
		MailSendLogCondition mailSendLogCondition = null;
		MailSendLog mailSendLog = null;
		JSONObject object = new JSONObject();
		// 邮件发送日志Id是否为空
		if (mailSendLogValidCommond.getSearchLogID() != null && !"".equals(mailSendLogValidCommond.getSearchLogID())) {
			mailSendLogCondition = new MailSendLogCondition();
			mailSendLogCondition.setSearchLogID(mailSendLogValidCommond.getSearchLogID());
			mailSendLog = iMailSendLogService.getMailSendLog(mailSendLogCondition);
			BeanUtils.copyProperties(mailSendLog, mailSendLogValidCommond);
			// 如果逻辑正确，必须加success为true
			object.setSuccess(true);
			object.setData(mailSendLogValidCommond);
		}
		return object;
	}

	/**
	 * 更新邮件发送日志
	 * 
	 * @param model
	 * @param request
	 * @param mailSendLogCommond
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	@RequestMapping(value = "/updataMailSendLog")
	public @ResponseBody
	JSONObject updataMailSendLog(HttpServletRequest request, MailSendLogValidCommond mailSendLogValidCommond) throws Exception {
		MailSendLog mailSendLog = null;
		JSONObject object = new JSONObject();
		// 用来判断是否为重复提交
		if (token.isTokenValid(request, true)) {
			mailSendLog = new MailSendLog();
			BeanUtils.copyProperties(mailSendLogValidCommond, mailSendLog);
			iMailSendLogService.updateMailSendLog(mailSendLog);
			object.setSuccess(true);
			object.setMsg("修改成功！");
		}
		return object;
	}

	/**
	 * 根据ID数组批量作废或启用邮件发送日志
	 * 
	 * @param model
	 * @param request
	 * @param mailSendLogCommond
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	@RequestMapping(value = "/discardOrReuseMailSendLog")
	public @ResponseBody
	JSONObject discardOrReuseMailSendLog(HttpServletRequest request, MailSendLogCommond mailSendLogCommond) throws Exception {
		JSONObject object = new JSONObject();
		if (mailSendLogCommond.getSearchIsEnabled() != null && !"".equals(mailSendLogCommond.getSearchIsEnabled()) && mailSendLogCommond.getSearchLogIDs() != null && mailSendLogCommond.getSearchLogIDs().length > 0) {
			MailSendLogCondition mailSendLogCondition = new MailSendLogCondition();
			mailSendLogCondition.setSearchLogIDs(mailSendLogCommond.getSearchLogIDs());
			mailSendLogCondition.setSearchIsEnabled(mailSendLogCommond.getSearchIsEnabled());
			iMailSendLogService.discardOrReuseMailSendLog(mailSendLogCondition);
			object.setSuccess(true);
			object.setMsg("修改成功！");
		} else {
			object.setSuccess(false);
			object.setMsg("为空");
		}
		return object;
	}
}
