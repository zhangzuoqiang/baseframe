package cn.hs.module.email.template.service.impl;

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
import cn.hs.module.clob.domain.BaseClob;
import cn.hs.module.clob.service.IClobService;
import cn.hs.module.email.template.dao.IMailTemplateDao;
import cn.hs.module.email.template.domain.MailTemplate;
import cn.hs.module.email.template.domain.MailTemplateCondition;
import cn.hs.module.email.template.domain.jsonbean.MailTemplateJsonBean;
import cn.hs.module.email.template.service.IMailTemplateService;

/**
 * Title: MailTemplateServiceImpl<br>
 * Description: 邮件模板管理业务接口实现<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author LiuHG
 * @createDate 2012-08-01
 * @version $Revision$
 */
// 声明本类为Server实现类，声明bean名称
@Service(value = "cn.hs.module.email.template.service.impl.MailTemplateServiceImpl")
public class MailTemplateServiceImpl extends PageTemplate implements IMailTemplateService {

	// 邮件模板DAO
	@Autowired
	private IMailTemplateDao mailTemplateDao;

	@Autowired
	private IClobService clobService;

	/**
	 * 新增邮件模板
	 * 
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@Override
	@BusinessLogForAdd(operationModule = "邮件模板")
	@CreateInfo(createTimeMethod = "setCreateTime", creatorMethod = "setCreator")
	public void addMailTemplate(MailTemplate mailTemplate) throws Exception {

		// 添加clob内容
		if (mailTemplate.getContent() != null) {
			BaseClob clob = new BaseClob();
			String clobId = clobService.addClob(mailTemplate.getContent());
			clob.setClobID(clobId);
			mailTemplate.setContent(clob);
		}

		mailTemplateDao.addMailTemplate(mailTemplate);
	}

	/**
	 * 根据ID数组批量作废或启用邮件模板
	 * 
	 * @param mailTemplate
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@Override
	@BusinessLogForBatchUpdateState(entityClass = MailTemplate.class, getEntityPKMethodName = "getTemplateID", getObjectsByIDArrayMethodName = "getMailTemplateListByIDArray", getPKArrayMethodName = "getTemplateIDs", operationModule = "邮件模板", pkArrayClass = String[].class)
	public void discardOrReuseMailTemplate(MailTemplateCondition condition) throws Exception {
		mailTemplateDao.discardOrReuseMailTemplate(condition);
	}

	/**
	 * 根据ID查询邮件模板
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@Override
	public MailTemplate getMailTemplate(MailTemplateCondition condition) throws Exception {
		return mailTemplateDao.getMailTemplate(condition);
	}

	/**
	 * 更新邮件模板
	 * 
	 * @param mailTemplate
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@Override
	@BusinessLogForUpdate(getEntityPKMethodName = "getTemplateID", getObjectByIDMethodName = "getMailTemplateByID", operationModule = "邮件模板", pkClass = String.class)
	@CreateInfo(createTimeMethod = "setCreateTime", creatorMethod = "setCreator")
	public void updateMailTemplate(MailTemplate mailTemplate) throws Exception {

		// 更新clob内容
		if (mailTemplate.getContent() != null) {
			clobService.updateClob(mailTemplate.getContent());
		}
		mailTemplateDao.updateMailTemplate(mailTemplate);
	}

	/**
	 * 根据邮件模板编码查询邮件模板信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@Override
	public MailTemplate viewMailTemplate(MailTemplateCondition condition) throws Exception {
		return mailTemplateDao.viewMailTemplate(condition);
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
	 * count查询邮件模板集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date Sep 9, 2011
	 */
	@Override
	protected Long findCount(BaseCondition condition) throws Exception {
		return mailTemplateDao.listMailTemplateCount((MailTemplateCondition) condition);
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
	protected List<MailTemplate> findList(BaseCondition condition) throws Exception {
		return mailTemplateDao.listMailTemplate((MailTemplateCondition) condition);
	}

	/**
	 * 根据ID获得邮件模板 更新操作时记录业务日志必须实现该方法，该方法将返回更新操作前数据内容
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@Override
	public MailTemplate getMailTemplateByID(String id) throws Exception {
		return mailTemplateDao.getMailTemplateByID(id);
	}

	/**
	 * 根据ID数组获得邮件模板 批量操作时记录业务日志必须实现该方法，该方法将返回批量操作前数据内容
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@Override
	public List<MailTemplate> getMailTemplateListByIDArray(String[] idArray) throws Exception {
		return mailTemplateDao.getMailTemplateListByIDArray(idArray);
	}

	@Override
	protected List<?> objListToJsonList(List<?> pageResultList) throws Exception {
		List<MailTemplateJsonBean> result = new ArrayList<MailTemplateJsonBean>();
		for (Object object : pageResultList) {
			MailTemplate mailTemplate = (MailTemplate) object;
			MailTemplateJsonBean bean = new MailTemplateJsonBean();
			BeanUtils.copyProperties(bean, mailTemplate);
			if (mailTemplate.getTemplateType() != null) {
				bean.setTemplateType(mailTemplate.getTemplateType().getDataName());
			}
			result.add(bean);
		}
		return result;
	}

	/**
	 * 新增和更新邮件模版前，查看邮件模版别名是否有重复
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @date Aug 3, 2012
	 */
	@Override
	public boolean checkTemplateAliasIsRepeat(MailTemplateCondition condition) throws Exception {
		List<MailTemplate> list = mailTemplateDao.getMailTemplateList(condition);
		if (list != null && list.size() > 0) {
			return false;
		}
		return true;
	}
}
