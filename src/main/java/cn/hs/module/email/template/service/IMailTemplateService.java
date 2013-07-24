package cn.hs.module.email.template.service;

import java.util.List;

import cn.hs.core.servicetemplate.pagetemplate.IBasePageTemplate;
import cn.hs.module.email.template.domain.MailTemplate;
import cn.hs.module.email.template.domain.MailTemplateCondition;

/**
 * Title: IMailTemplateService<br>
 * Description: 邮件模板管理业务接口<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author LiuHG
 * @createDate 2012-08-01
 * @version $Revision$
 */
public interface IMailTemplateService extends IBasePageTemplate {
	/**
	 * 新增邮件模板
	 * 
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	public void addMailTemplate(MailTemplate mailTemplate) throws Exception;

	/**
	 * 根据ID查询邮件模板
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	public MailTemplate getMailTemplate(MailTemplateCondition condition) throws Exception;

	/**
	 * 更新邮件模板
	 * 
	 * @param mailTemplate
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	public void updateMailTemplate(MailTemplate mailTemplate) throws Exception;

	/**
	 * 根据ID数组批量作废或启用邮件模板
	 * 
	 * @param mailTemplate
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	public void discardOrReuseMailTemplate(MailTemplateCondition condition) throws Exception;

	/**
	 * 根据邮件模板编码查询邮件模板信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	public MailTemplate viewMailTemplate(MailTemplateCondition condition) throws Exception;

	/**
	 * 根据ID数组获得邮件模板 批量操作时记录业务日志必须实现该方法，该方法将返回批量操作前数据内容
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	public List<MailTemplate> getMailTemplateListByIDArray(String[] idArray) throws Exception;

	/**
	 * 根据ID获得邮件模板 更新操作时记录业务日志必须实现该方法，该方法将返回更新操作前数据内容
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	public MailTemplate getMailTemplateByID(String id) throws Exception;

	/**
	 * 新增和更新邮件模版前，查看邮件模版别名是否有重复
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @date Aug 3, 2012
	 */
	public boolean checkTemplateAliasIsRepeat(MailTemplateCondition condition) throws Exception;
}
