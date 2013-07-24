package cn.hs.module.email.template.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.hs.core.basedao.base.IBaseDao;
import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.module.email.template.dao.IMailTemplateDao;
import cn.hs.module.email.template.dao.require.MailTemplateRequire;
import cn.hs.module.email.template.domain.MailTemplate;
import cn.hs.module.email.template.domain.MailTemplateCondition;

/**
 * Title: MailTemplateDaoImpl<br>
 * Description: 邮件模板数据接口实现<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author LiuHG
 * @createDate 2012-08-01
 * @version $Revision$
 */
@Repository(value = "cn.hs.module.email.template.dao.impl.MailTemplateDaoImpl")
public class MailTemplateDaoImpl implements IMailTemplateDao {
	// 初始化baseDao
	@Autowired
	private IBaseDao<MailTemplate> baseDao;
	// 初始化mailTemplateRequire
	@Autowired
	private MailTemplateRequire mailTemplateRequire;

	/**
	 * 根据ID数组批量作废或启用邮件模板
	 * 
	 * @param condition
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@Override
	public void discardOrReuseMailTemplate(MailTemplateCondition condition) throws Exception {
		baseDao.executeUpdate(mailTemplateRequire.discardOrReuseMailTemplateHQL(condition));
	}

	/**
	 * 根据Id查询邮件模板
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@Override
	public MailTemplate getMailTemplate(MailTemplateCondition condition) throws Exception {
		return baseDao.findObject(MailTemplate.class, condition.getSearchTemplateID());
	}

	/**
	 * 查询邮件模板集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MailTemplate> getMailTemplateList(MailTemplateCondition condition) throws Exception {
		mailTemplateRequire.listMailTemplate(condition);
		// 设置不分页查询
		condition.setStart(-1);
		condition.setRows(-1);
		return (List<MailTemplate>) baseDao.pagedQuery(condition);
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
	public void updateMailTemplate(MailTemplate mailTemplate) throws Exception {
		baseDao.updateEntityByPK(mailTemplate);
	}

	/**
	 * 添加邮件模板
	 * 
	 * @param mailTemplate
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@Override
	public void addMailTemplate(MailTemplate mailTemplate) throws Exception {
		baseDao.addEntity(mailTemplate);
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
		mailTemplateRequire.listMailTemplate(condition);
		List<?> result = baseDao.find(condition.getSql(), condition.getParameterList().toArray());
		if (result != null && result.size() > 0) {
			return (MailTemplate) result.get(0);
		}
		return null;
	}

	/**
	 * 查询邮件模板集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MailTemplate> listMailTemplate(MailTemplateCondition condition) throws Exception {
		mailTemplateRequire.listMailTemplate(condition);
		return (List<MailTemplate>) baseDao.pagedQuery(condition);
	}

	/**
	 * count邮件模板类型集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@Override
	public Long listMailTemplateCount(MailTemplateCondition condition) throws Exception {
		mailTemplateRequire.countMailTemplateHql(condition);
		return baseDao.countQuery(condition);
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
		MailTemplate mailTemplate = baseDao.findObject(MailTemplate.class, id);
		baseDao.getHibernateSession().clear();
		return mailTemplate == null ? new MailTemplate() : mailTemplate;
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
	@SuppressWarnings("unchecked")
	@Override
	public List<MailTemplate> getMailTemplateListByIDArray(String[] idArray) throws Exception {
		baseDao.getHibernateSession().clear();
		BaseCondition condition = mailTemplateRequire.getMailTemplateListByIDArrayHQL(idArray);
		return (List<MailTemplate>) baseDao.find(condition.getSql(), condition.getParameterList().toArray());
	}

}
