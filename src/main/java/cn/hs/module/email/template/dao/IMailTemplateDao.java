package cn.hs.module.email.template.dao;

import java.util.List;

import cn.hs.module.email.template.domain.MailTemplate;
import cn.hs.module.email.template.domain.MailTemplateCondition;

/**
 * Title: IMailTemplateDao<br>
 * Description: 邮件模板数据接口<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author LiuHG
 * @createDate 2012-08-01
 * @version $Revision$
 */
public interface IMailTemplateDao {
	/**
	 * 新增邮件模板
	 * 
	 * @param mailTemplate
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	public void addMailTemplate(MailTemplate mailTemplate) throws Exception;

	/**
	 * 根据ID数组批量作废或启用邮件模板
	 * 
	 * @param condition
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	public void discardOrReuseMailTemplate(MailTemplateCondition condition) throws Exception;

	/**
	 * 根据Id查询邮件模板
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	public MailTemplate getMailTemplate(MailTemplateCondition condition) throws Exception;

	/**
	 * 查询邮件模板集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	public List<MailTemplate> getMailTemplateList(MailTemplateCondition condition) throws Exception;

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
	 * 查询邮件模板集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	public List<MailTemplate> listMailTemplate(MailTemplateCondition condition) throws Exception;

	/**
	 * count查询邮件模板集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	public Long listMailTemplateCount(MailTemplateCondition condition) throws Exception;

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

}
