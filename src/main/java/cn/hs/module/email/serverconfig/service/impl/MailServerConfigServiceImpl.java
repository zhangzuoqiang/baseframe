package cn.hs.module.email.serverconfig.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hs.core.annotation.BusinessLogForAdd;
import cn.hs.core.annotation.BusinessLogForBatchUpdateState;
import cn.hs.core.annotation.BusinessLogForUpdate;
import cn.hs.core.annotation.CreateInfo;
import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.core.page.commond.BasePageCommond;
import cn.hs.core.page.commond.JSONObject;
import cn.hs.core.servicetemplate.pagetemplate.PageTemplate;
import cn.hs.module.email.serverconfig.dao.IMailServerConfigDao;
import cn.hs.module.email.serverconfig.domain.MailServerConfig;
import cn.hs.module.email.serverconfig.domain.MailServerConfigCondition;
import cn.hs.module.email.serverconfig.domain.jsonbean.MailServerConfigJsonBean;
import cn.hs.module.email.serverconfig.service.IMailServerConfigService;

/**
 * Title: MailServerConfigServiceImpl<br>
 * Description: 邮件服务器配置管理业务接口实现<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author LiuHG
 * @createDate 2012-08-01
 * @version $Revision$
 */
// 声明本类为Server实现类，声明bean名称
@Service(value = "cn.hs.module.email.serverconfig.service.impl.MailServerConfigServiceImpl")
public class MailServerConfigServiceImpl extends PageTemplate implements IMailServerConfigService {

	// 邮件服务器配置DAO
	@Autowired
	private IMailServerConfigDao mailServerConfigDao;

	/**
	 * 新增邮件服务器配置
	 * 
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@Override
	@BusinessLogForAdd(operationModule = "邮件服务器配置")
	@CreateInfo(createTimeMethod = "setCreateTime", creatorMethod = "setCreator")
	public void addMailServerConfig(MailServerConfig mailServerConfig) throws Exception {
		mailServerConfigDao.addMailServerConfig(mailServerConfig);
	}

	/**
	 * 根据ID数组批量作废或启用邮件服务器配置
	 * 
	 * @param mailServerConfig
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@Override
	@BusinessLogForBatchUpdateState(entityClass = MailServerConfig.class, getEntityPKMethodName = "getConfigID", getObjectsByIDArrayMethodName = "getMailServerConfigListByIDArray", getPKArrayMethodName = "getConfigIDs", operationModule = "邮件服务器配置", pkArrayClass = String[].class)
	public void discardOrReuseMailServerConfig(MailServerConfigCondition condition) throws Exception {
		mailServerConfigDao.discardOrReuseMailServerConfig(condition);
	}

	/**
	 * 根据ID查询邮件服务器配置
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@Override
	public MailServerConfig getMailServerConfig(MailServerConfigCondition condition) throws Exception {
		return mailServerConfigDao.getMailServerConfig(condition);
	}

	/**
	 * 更新邮件服务器配置
	 * 
	 * @param mailServerConfig
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@Override
	@BusinessLogForUpdate(getEntityPKMethodName = "getConfigID", getObjectByIDMethodName = "getMailServerConfigByID", operationModule = "邮件服务器配置", pkClass = String.class)
	@CreateInfo(createTimeMethod = "setCreateTime", creatorMethod = "setCreator")
	public void updateMailServerConfig(MailServerConfig mailServerConfig) throws Exception {
		mailServerConfigDao.updateMailServerConfig(mailServerConfig);
	}

	/**
	 * 根据邮件服务器配置编码查询邮件服务器配置信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@Override
	public MailServerConfig viewMailServerConfig(MailServerConfigCondition condition) throws Exception {
		return mailServerConfigDao.viewMailServerConfig(condition);
	}

	/**
	 * 调用PageTemplate模板process方法
	 * 
	 * @param pageCommond
	 * @param condition
	 * @throws Exception
	 * @author LiuHG
	 * @date Aug 31, 2011
	 */
	@Override
	public JSONObject doProcess(BasePageCommond pageCommond, BaseCondition condition) throws Exception {
		return process(pageCommond, condition);
	}

	/**
	 * count查询邮件服务器配置集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date Sep 9, 2011
	 */
	@Override
	protected Long findCount(BaseCondition condition) throws Exception {
		return mailServerConfigDao.listMailServerConfigCount((MailServerConfigCondition) condition);
	}

	/**
	 * 查询基础数据类型集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date Sep 9, 2011
	 */
	@Override
	protected List<MailServerConfig> findList(BaseCondition condition) throws Exception {
		return mailServerConfigDao.listMailServerConfig((MailServerConfigCondition) condition);
	}

	/**
	 * 根据ID获得邮件服务器配置 更新操作时记录业务日志必须实现该方法，该方法将返回更新操作前数据内容
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@Override
	public MailServerConfig getMailServerConfigByID(String id) throws Exception {
		return mailServerConfigDao.getMailServerConfigByID(id);
	}

	/**
	 * 根据ID数组获得邮件服务器配置 批量操作时记录业务日志必须实现该方法，该方法将返回批量操作前数据内容
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@Override
	public List<MailServerConfig> getMailServerConfigListByIDArray(String[] idArray) throws Exception {
		return mailServerConfigDao.getMailServerConfigListByIDArray(idArray);
	}

	@Override
	protected List<?> objListToJsonList(List<?> pageResultList) throws Exception {
		List<MailServerConfigJsonBean> result = new ArrayList<MailServerConfigJsonBean>();
		for (Object object : pageResultList) {
			MailServerConfig mailServerConfig = (MailServerConfig) object;
			MailServerConfigJsonBean bean = new MailServerConfigJsonBean();
			BeanUtils.copyProperties(bean, mailServerConfig);
			result.add(bean);
		}
		return result;
	}
}
