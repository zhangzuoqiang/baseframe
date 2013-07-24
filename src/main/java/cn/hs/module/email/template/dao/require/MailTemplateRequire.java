package cn.hs.module.email.template.dao.require;

import org.springframework.stereotype.Component;

import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.core.page.commond.SortInfoBean;
import cn.hs.module.email.template.domain.MailTemplateCondition;

/**
 * Title: MailTemplateRequire<br>
 * Description: 邮件模板查询SQL拼写对象<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author LiuHG
 * @createDate 2012-08-01
 * @version $Revision$
 */
@Component(value = "cn.hs.module.email.template.dao.require.MailTemplateRequire")
public class MailTemplateRequire {
	/**
	 * list邮件模板集合HQL
	 * 
	 * @param condition
	 * @return
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	public MailTemplateCondition listMailTemplate(MailTemplateCondition condition) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append(" SELECT t FROM MailTemplate t WHERE 1=1");

		if (condition.getSearchTemplateID() != null && !"".equals(condition.getSearchTemplateID())) {
			hqlBuffer.append(" AND t.templateID = ? ");
			condition.addParameter(condition.getSearchTemplateID());
		}
		// 拼装邮件模版的别名的查询
		if (condition.getSearchTemplateAlias() != null && !"".equals(condition.getSearchTemplateAlias())) {
			hqlBuffer.append(" AND t.templateAlias = ? ");
			condition.addParameter(condition.getSearchTemplateAlias());
		}
		// 拼装模板名称查询条件
		if (condition.getSearchNoteTempName() != null && !"".equals(condition.getSearchNoteTempName())) {
			hqlBuffer.append(" AND t.templateName like ? ");
			condition.addParameter("%" + condition.getSearchNoteTempName() + "%");
		}
		// 拼装模板类型查询条件
		if (condition.getSearchPhaseTypeCode() != null && !"".equals(condition.getSearchPhaseTypeCode())) {
			hqlBuffer.append(" AND t.templateType.dataCode = ? ");
			condition.addParameter(condition.getSearchPhaseTypeCode());
		}
		// 拼装排序
		SortInfoBean sortInfo = condition.getSortInfo();
		if (sortInfo != null) {
			hqlBuffer.append(" ORDER BY " + sortInfo.getProperty() + " " + sortInfo.getDirection());
		} else {
			hqlBuffer.append(" ORDER BY t.templateID ");
		}
		condition.setSql(hqlBuffer.toString());
		return condition;
	}

	/**
	 * count邮件模板集合HQL
	 * 
	 * @param condition
	 * @return
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	public MailTemplateCondition countMailTemplateHql(MailTemplateCondition condition) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append(" SELECT COUNT(*) from MailTemplate t WHERE 1=1");
		if (condition.getSearchTemplateID() != null && !"".equals(condition.getSearchTemplateID())) {
			hqlBuffer.append(" AND t.templateID = ? ");
			condition.addParameter(condition.getSearchTemplateID());
		}
		// 拼装邮件模版的别名的查询
		if (condition.getSearchTemplateAlias() != null && !"".equals(condition.getSearchTemplateAlias())) {
			hqlBuffer.append(" AND t.templateAlias = ? ");
			condition.addParameter(condition.getSearchTemplateAlias());
		}
		// 拼装模板名称查询条件
		if (condition.getSearchNoteTempName() != null && !"".equals(condition.getSearchNoteTempName())) {
			hqlBuffer.append(" AND t.templateName like ? ");
			condition.addParameter("%" + condition.getSearchNoteTempName() + "%");
		}
		// 拼装模板类型查询条件
		if (condition.getSearchPhaseTypeCode() != null && !"".equals(condition.getSearchPhaseTypeCode())) {
			hqlBuffer.append(" AND t.templateType.dataCode = ? ");
			condition.addParameter(condition.getSearchPhaseTypeCode());
		}
		condition.setSql(hqlBuffer.toString());
		return condition;
	}

	/**
	 * 根据ID数组批量作废或启用邮件模板HQL
	 * 
	 * @param condition
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	public MailTemplateCondition discardOrReuseMailTemplateHQL(MailTemplateCondition condition) {
		String hql = "update MailTemplate bd set bd.isEnabled = ? where 1=1";
		condition.addParameter(new Integer(condition.getSearchIsEnabled()));
		if (condition.getSearchTemplateIDs() != null && condition.getSearchTemplateIDs().length > 0) {
			hql += " and bd.templateID in (" + condition.assemblyParameterListSQL(condition.getSearchTemplateIDs()) + ")";
			condition.addParameterList(condition.getSearchTemplateIDs());
		}
		condition.setSql(hql);
		return condition;
	}

	/**
	 * 根据ID数组获得邮件模板 HQL
	 * 
	 * @return
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	public BaseCondition getMailTemplateListByIDArrayHQL(String[] idArray) {
		BaseCondition condition = new BaseCondition();
		condition.setSql(" from MailTemplate where templateID in (" + condition.assemblyParameterListSQL(idArray) + ")");
		condition.addParameterList(idArray);
		return condition;
	}

	/**
	 * 根据ID获得邮件模板HQL
	 * 
	 * @return
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	public String getMailTemplateByIDHQL() {
		return "from MailTemplate where templateID = :templateID";
	}

}
