package cn.hs.module.email.serverconfig.service;

import java.util.List;

import cn.hs.core.servicetemplate.pagetemplate.IBasePageTemplate;
import cn.hs.module.email.serverconfig.domain.MailServerConfig;
import cn.hs.module.email.serverconfig.domain.MailServerConfigCondition;

/**
 * Title: IMailServerConfigService<br>
 * Description: 邮件服务器配置管理业务接口<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author LiuHG
 * @createDate 2012-08-01
 * @version $Revision$
 */
public interface IMailServerConfigService extends IBasePageTemplate {
	/**
	 * 新增邮件服务器配置
	 * 
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	public void addMailServerConfig(MailServerConfig mailServerConfig) throws Exception;

	/**
	 * 根据ID查询邮件服务器配置
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	public MailServerConfig getMailServerConfig(MailServerConfigCondition condition) throws Exception;

	/**
	 * 更新邮件服务器配置
	 * 
	 * @param mailServerConfig
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	public void updateMailServerConfig(MailServerConfig mailServerConfig) throws Exception;

	/**
	 * 根据ID数组批量作废或启用邮件服务器配置
	 * 
	 * @param mailServerConfig
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	public void discardOrReuseMailServerConfig(MailServerConfigCondition condition) throws Exception;

	/**
	 * 根据邮件服务器配置编码查询邮件服务器配置信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	public MailServerConfig viewMailServerConfig(MailServerConfigCondition condition) throws Exception;

	/**
	 * 根据ID数组获得邮件服务器配置 批量操作时记录业务日志必须实现该方法，该方法将返回批量操作前数据内容
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	public List<MailServerConfig> getMailServerConfigListByIDArray(String[] idArray) throws Exception;

	/**
	 * 根据ID获得邮件服务器配置 更新操作时记录业务日志必须实现该方法，该方法将返回更新操作前数据内容
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	public MailServerConfig getMailServerConfigByID(String id) throws Exception;
}
