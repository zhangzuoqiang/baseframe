package cn.hs.module.email.template.ui.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hs.core.page.commond.JSONObject;
import cn.hs.core.util.token.IToken;
import cn.hs.module.basedata.domain.BaseData;
import cn.hs.module.clob.domain.BaseClob;
import cn.hs.module.email.template.domain.MailTemplate;
import cn.hs.module.email.template.domain.MailTemplateCondition;
import cn.hs.module.email.template.service.IMailTemplateService;
import cn.hs.module.email.template.ui.commond.MailTemplateCommond;
import cn.hs.module.email.template.ui.commond.MailTemplateValidCommond;

/**
 * Title: MailTemplateController<br>
 * Description: 邮件模板管理控制器<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author LiuHG
 * @createDate 2012-08-01
 * @version $Revision$
 */
@Controller
@RequestMapping(value = MailTemplateController.modulePath)
public class MailTemplateController {
	// 模块页面根路径
	public static final String modulePath = "/module/mailtemplate";
	// 邮件模板接口
	@Autowired
	private IMailTemplateService iMailTemplateService;

	// token接口
	@Autowired
	private IToken token;

	/**
	 * 列表查询邮件模板
	 * 
	 * @param model
	 * @param request
	 * @param mailTemplateCommond
	 * @return
	 * @author LiuHG
	 * @throws Exception
	 * @date 2012-08-01
	 */
	@RequestMapping(value = "/listMailTemplate")
	public @ResponseBody
	JSONObject listMailTemplate(MailTemplateCommond mailTemplateCommond) throws Exception {
		MailTemplateCondition condition = new MailTemplateCondition();
		BeanUtils.copyProperties(mailTemplateCommond, condition);
		if (condition.getSearchIsEnabled() == null || "".equals(condition.getSearchIsEnabled())) {
			// 列表默认查询启用数据
			// TODO condition.setSearchIsEnabled("1");
		}
		JSONObject result = iMailTemplateService.doProcess(mailTemplateCommond, condition);
		return result;
	}

	/**
	 * 预添加邮件模板
	 * 
	 * @param model
	 * @param request
	 * @param mailTemplateValidCommond
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@RequestMapping(value = "/preAddMailTemplate")
	public String preAddMailTemplate(HttpServletRequest request, MailTemplateValidCommond mailTemplateValidCommond) throws Exception {
		token.saveToken(request);
		return MailTemplateController.modulePath + "/addMailTemplate.jsp";
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
	String preAddMailTemplate(HttpServletRequest request) throws Exception {
		String tokenStr = token.saveToken(request);
		return tokenStr;
	}

	/**
	 * 添加邮件模板
	 * 
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@RequestMapping(value = "/addMailTemplate")
	public @ResponseBody
	JSONObject addMailTemplate(HttpServletRequest request, MailTemplateValidCommond mailTemplateValidCommond) throws Exception {
		MailTemplate mailTemplate = null;
		MailTemplateCondition mailTemplateCondition = null;
		JSONObject result = new JSONObject();
		// 用来判断是否为重复提交
		if (token.isTokenValid(request, true)) {
			// 判断添加的邮件模版别名是否相同
			mailTemplateCondition = new MailTemplateCondition();
			mailTemplateCondition.setSearchTemplateAlias(mailTemplateValidCommond.getTemplateAlias());
			if (iMailTemplateService.checkTemplateAliasIsRepeat(mailTemplateCondition)) {
				mailTemplate = new MailTemplate();
				// 将mailTemplateValidCommond里的属性值赋给mailTemplate
				commondToMailTemplate(mailTemplateValidCommond, mailTemplate);
				iMailTemplateService.addMailTemplate(mailTemplate);
				result.setSuccess(true);
				result.setMsg("添加成功！");
			} else {
				result.setSuccess(false);
				result.setMsg("添加的模版别名不能相同！");
			}

		}
		return result;
	}

	/**
	 * 预更新邮件模板
	 * 
	 * @param model
	 * @param request
	 * @param mailTemplateCommond
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@RequestMapping(value = "/preUpdateMailTemplate")
	public @ResponseBody
	JSONObject preUpdateMailTemplate(MailTemplateValidCommond mailTemplateValidCommond) throws Exception {
		MailTemplateCondition mailTemplateCondition = null;
		MailTemplate mailTemplate = null;
		JSONObject object = new JSONObject();
		// 邮件模板Id是否为空
		if (mailTemplateValidCommond.getSearchTemplateID() != null && !"".equals(mailTemplateValidCommond.getSearchTemplateID())) {
			mailTemplateCondition = new MailTemplateCondition();
			mailTemplateCondition.setSearchTemplateID(mailTemplateValidCommond.getSearchTemplateID());
			mailTemplate = iMailTemplateService.getMailTemplate(mailTemplateCondition);
			if (mailTemplate.getContent() != null) {
				mailTemplateValidCommond.setClobID(mailTemplate.getContent().getClobID());
				mailTemplateValidCommond.setContent(mailTemplate.getContent().getContentClob());
			}
			if (mailTemplate.getTemplateType() != null) {
				mailTemplateValidCommond.setTemplateType(mailTemplate.getTemplateType().getDataCode());
			}
			mailTemplateValidCommond.setTemplateID(mailTemplate.getTemplateID());
			mailTemplateValidCommond.setTemplateName(mailTemplate.getTemplateName());
			mailTemplateValidCommond.setTemplateAlias(mailTemplate.getTemplateAlias());
			mailTemplateValidCommond.setSubject(mailTemplate.getSubject());
			mailTemplateValidCommond.setIsEnabled(mailTemplate.getActiveState());
			// 如果逻辑正确，必须加success为true
			object.setSuccess(true);
			object.setData(mailTemplateValidCommond);
		}
		return object;
	}

	/**
	 * 更新邮件模板
	 * 
	 * @param model
	 * @param request
	 * @param mailTemplateCommond
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@RequestMapping(value = "/updataMailTemplate")
	public @ResponseBody
	JSONObject updataMailTemplate(HttpServletRequest request, MailTemplateValidCommond mailTemplateValidCommond) throws Exception {
		MailTemplate mailTemplate = null;
		MailTemplateCondition mailTemplateCondition = null;
		JSONObject object = new JSONObject();
		// 用来判断是否为重复提交
		if (token.isTokenValid(request, true)) {
			mailTemplate = new MailTemplate();
			mailTemplateCondition = new MailTemplateCondition();
			mailTemplateCondition.setSearchTemplateAlias(mailTemplateValidCommond.getTemplateAlias());
			// modify by HuangS at 2012-09-04
			// 别名不允许更改，不需要判断是否已存在。
			// 另原判断逻辑存在bug，如果未修改别名但是会提示已存在

			// 判断更新的别名是否已存在
			// if
			// (iMailTemplateService.checkTemplateAliasIsRepeat(mailTemplateCondition))
			// {
			this.commondToMailTemplate(mailTemplateValidCommond, mailTemplate);
			iMailTemplateService.updateMailTemplate(mailTemplate);
			object.setSuccess(true);
			object.setMsg("修改成功！");
			// } else {
			// object.setSuccess(false);
			// object.setMsg("修改的模版别名不能相同！");
			// }
		}
		return object;
	}

	/**
	 * 根据ID数组批量作废或启用邮件模板
	 * 
	 * @param model
	 * @param request
	 * @param mailTemplateCommond
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@RequestMapping(value = "/discardOrReuseMailTemplate")
	public @ResponseBody
	JSONObject discardOrReuseMailTemplate(HttpServletRequest request, MailTemplateCommond mailTemplateCommond) throws Exception {
		JSONObject object = new JSONObject();
		if (mailTemplateCommond.getSearchIsEnabled() != null && !"".equals(mailTemplateCommond.getSearchIsEnabled()) && mailTemplateCommond.getSearchTemplateIDs() != null && mailTemplateCommond.getSearchTemplateIDs().length > 0) {
			MailTemplateCondition mailTemplateCondition = new MailTemplateCondition();
			mailTemplateCondition.setSearchTemplateIDs(mailTemplateCommond.getSearchTemplateIDs());
			mailTemplateCondition.setSearchIsEnabled(mailTemplateCommond.getSearchIsEnabled());
			iMailTemplateService.discardOrReuseMailTemplate(mailTemplateCondition);
			object.setSuccess(true);
			object.setMsg("修改成功！");
		} else {
			object.setSuccess(false);
			object.setMsg("为空");
		}
		return object;
	}

	/**
	 * 将command对象转换成实例对象domain
	 * 
	 * @param command
	 * @param domain
	 * @return
	 * @author XiangBin
	 * @date Aug 3, 2012
	 */
	private MailTemplate commondToMailTemplate(MailTemplateValidCommond command, MailTemplate mailTemplate) {

		// 设置clob内容
		if (command.getContent() != null && !"".equals(command.getContent())) {
			BaseClob clob = new BaseClob();
			clob.setClobID(command.getClobID());
			clob.setContentClob(command.getContent());
			mailTemplate.setContent(clob);
		}
		// 设置邮件模版的标题
		if (command.getSubject() != null && !"".equals(command.getSubject())) {
			mailTemplate.setSubject(command.getSubject());
		}

		// 设置邮件模版是否有效
		if (command.getIsEnabled() != null && command.getIsEnabled() != 0) {
			mailTemplate.setActiveState(command.getIsEnabled());
		}

		// 设置邮件模板类型
		if (command.getTemplateType() != null && !"".equals(command.getTemplateType())) {
			// 修改为数据字典 modify by HuangS at 2012-09-04
			BaseData baseData = new BaseData();
			baseData.setDataCode(command.getTemplateType());
			mailTemplate.setTemplateType(baseData);
		}
		if (command.getTemplateName() != null && !"".equals(command.getTemplateName())) {
			mailTemplate.setTemplateName(command.getTemplateName());
		}

		// 设置邮件模板的别名
		if (command.getTemplateAlias() != null && !"".equals(command.getTemplateAlias())) {
			mailTemplate.setTemplateAlias(command.getTemplateAlias());
		}
		// 设置邮件模板的ID
		if (command.getTemplateID() != null && !"".equals(command.getTemplateID())) {
			mailTemplate.setTemplateID(command.getTemplateID());
		}
		return mailTemplate;

	}
}
