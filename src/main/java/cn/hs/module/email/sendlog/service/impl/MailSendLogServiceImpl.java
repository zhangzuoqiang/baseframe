package cn.hs.module.email.sendlog.service.impl;

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
import cn.hs.module.email.sendlog.dao.IMailSendLogDao;
import cn.hs.module.email.sendlog.domain.MailSendLog;
import cn.hs.module.email.sendlog.domain.MailSendLogCondition;
import cn.hs.module.email.sendlog.domain.jsonbean.MailSendLogJsonBean;
import cn.hs.module.email.sendlog.service.IMailSendLogService;

/**
 * Title: MailSendLogServiceImpl<br>
 * Description: 邮件发送日志管理业务接口实现<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author LiuHG
 * @createDate 2012-08-02
 * @version $Revision$
 */
// 声明本类为Server实现类，声明bean名称
@Service(value = "cn.hs.module.email.sendlog.service.impl.MailSendLogServiceImpl")
public class MailSendLogServiceImpl extends PageTemplate implements IMailSendLogService {

	// 邮件发送日志DAO
	@Autowired
	private IMailSendLogDao mailSendLogDao;

	/**
	 * 新增邮件发送日志
	 * 
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	@Override
	@BusinessLogForAdd(operationModule = "邮件发送日志")
	@CreateInfo(createTimeMethod = "setCreateTime", creatorMethod = "setCreator")
	public void addMailSendLog(MailSendLog mailSendLog) throws Exception {
		mailSendLogDao.addMailSendLog(mailSendLog);
	}

	/**
	 * 根据ID数组批量作废或启用邮件发送日志
	 * 
	 * @param mailSendLog
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	@Override
	@BusinessLogForBatchUpdateState(entityClass = MailSendLog.class, getEntityPKMethodName = "getLogID", getObjectsByIDArrayMethodName = "getMailSendLogListByIDArray", getPKArrayMethodName = "getLogIDs", operationModule = "邮件发送日志", pkArrayClass = String[].class)
	public void discardOrReuseMailSendLog(MailSendLogCondition condition) throws Exception {
		mailSendLogDao.discardOrReuseMailSendLog(condition);
	}

	/**
	 * 根据ID查询邮件发送日志
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	@Override
	public MailSendLog getMailSendLog(MailSendLogCondition condition) throws Exception {
		return mailSendLogDao.getMailSendLog(condition);
	}

	/**
	 * 更新邮件发送日志
	 * 
	 * @param mailSendLog
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	@Override
	@BusinessLogForUpdate(getEntityPKMethodName = "getLogID", getObjectByIDMethodName = "getMailSendLogByID", operationModule = "邮件发送日志", pkClass = String.class)
	@CreateInfo(createTimeMethod = "setCreateTime", creatorMethod = "setCreator")
	public void updateMailSendLog(MailSendLog mailSendLog) throws Exception {
		mailSendLogDao.updateMailSendLog(mailSendLog);
	}

	/**
	 * 根据邮件发送日志编码查询邮件发送日志信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	@Override
	public MailSendLog viewMailSendLog(MailSendLogCondition condition) throws Exception {
		return mailSendLogDao.viewMailSendLog(condition);
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
	 * count查询邮件发送日志集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date Sep 9, 2011
	 */
	@Override
	protected Long findCount(BaseCondition condition) throws Exception {
		return mailSendLogDao.listMailSendLogCount((MailSendLogCondition) condition);
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
	protected List<MailSendLog> findList(BaseCondition condition) throws Exception {
		return mailSendLogDao.listMailSendLog((MailSendLogCondition) condition);
	}

	/**
	 * 根据ID获得邮件发送日志 更新操作时记录业务日志必须实现该方法，该方法将返回更新操作前数据内容
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	@Override
	public MailSendLog getMailSendLogByID(String id) throws Exception {
		return mailSendLogDao.getMailSendLogByID(id);
	}

	/**
	 * 根据ID数组获得邮件发送日志 批量操作时记录业务日志必须实现该方法，该方法将返回批量操作前数据内容
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	@Override
	public List<MailSendLog> getMailSendLogListByIDArray(String[] idArray) throws Exception {
		return mailSendLogDao.getMailSendLogListByIDArray(idArray);
	}

	@Override
	protected List<?> objListToJsonList(List<?> pageResultList) throws Exception {
		List<MailSendLogJsonBean> result = new ArrayList<MailSendLogJsonBean>();
		for (Object object : pageResultList) {
			MailSendLog mailSendLog = (MailSendLog) object;
			MailSendLogJsonBean bean = new MailSendLogJsonBean();
			BeanUtils.copyProperties(bean, mailSendLog);
			result.add(bean);
		}
		return result;
	}
}
