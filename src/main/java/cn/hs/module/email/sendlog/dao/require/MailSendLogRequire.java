package cn.hs.module.email.sendlog.dao.require;

import org.springframework.stereotype.Component;

import cn.hs.core.annotation.EscapeProperty;
import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.core.page.commond.SortInfoBean;
import cn.hs.module.email.sendlog.domain.MailSendLogCondition;

/**
 * Title: MailSendLogRequire<br>
 * Description: 邮件发送日志查询SQL拼写对象<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author LiuHG
 * @createDate 2012-08-02
 * @version $Revision$
 */
@Component(value = "cn.hs.module.email.sendlog.dao.require.MailSendLogRequire")
public class MailSendLogRequire {
	/**
	 * list邮件发送日志集合HQL
	 * 
	 * @param condition
	 * @return
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	@EscapeProperty(escapePropertyNames = "searchMailSubject")
	public MailSendLogCondition listMailSendLog(MailSendLogCondition condition) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append(" SELECT t FROM MailSendLog t WHERE 1=1");

		if (condition.getSearchLogID() != null && !"".equals(condition.getSearchLogID())) {
			hqlBuffer.append(" AND t.logID = ?");
			condition.addParameter(condition.getSearchLogID());
		}
		if (condition.getSearchSendDateStart() != null && !"".equals(condition.getSearchSendDateStart())) {
			hqlBuffer.append(" AND t.sendDate >= ? ");
			condition.addParameter(condition.getSearchSendDateStart());
		}
		if (condition.getSearchSendDateEnd() != null && !"".equals(condition.getSearchSendDateEnd())) {
			hqlBuffer.append(" AND t.sendDate <= ?");
			condition.addParameter(condition.getSearchSendDateEnd());
		}
		if (condition.getSearchMailSubject() != null && !"".equals(condition.getSearchMailSubject())) {
			hqlBuffer.append(" AND t.mailSubject like ? escape '/'");
			condition.addParameter("%" + condition.getSearchMailSubject() + "%");
		}
		if (condition.getSearchSendState() != null && !"".equals(condition.getSearchSendState())) {
			hqlBuffer.append(" AND t.sendState = ? ");
			condition.addParameter(new Integer(condition.getSearchSendState()));
		}
		// 拼装排序
		SortInfoBean sortInfo = condition.getSortInfo();
		if (sortInfo != null) {
			hqlBuffer.append(" ORDER BY " + sortInfo.getProperty() + " " + sortInfo.getDirection());
		} else {
			// hqlBuffer.append(" ORDER BY t.");
		}
		condition.setSql(hqlBuffer.toString());
		return condition;
	}

	/**
	 * count邮件发送日志集合HQL
	 * 
	 * @param condition
	 * @return
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	@EscapeProperty(escapePropertyNames = "searchLogID,searchMailSubject")
	public MailSendLogCondition countMailSendLogHql(MailSendLogCondition condition) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append(" SELECT COUNT(*) from MailSendLog t WHERE 1=1");
		if (condition.getSearchLogID() != null && !"".equals(condition.getSearchLogID())) {
			hqlBuffer.append(" AND t.logID like ? escape '/'");
			condition.addParameter("%" + condition.getSearchLogID() + "%");
		}
		if (condition.getSearchSendDateStart() != null && !"".equals(condition.getSearchSendDateStart())) {
			hqlBuffer.append(" AND t.sendDate >= ? ");
			condition.addParameter(condition.getSearchSendDateStart());
		}
		if (condition.getSearchSendDateEnd() != null && !"".equals(condition.getSearchSendDateEnd())) {
			hqlBuffer.append(" AND t.sendDate <= ?");
			condition.addParameter(condition.getSearchSendDateEnd());
		}
		if (condition.getSearchMailSubject() != null && !"".equals(condition.getSearchMailSubject())) {
			hqlBuffer.append(" AND t.mailSubject like ? escape '/'");
			condition.addParameter("%" + condition.getSearchMailSubject() + "%");
		}
		if (condition.getSearchSendState() != null && !"".equals(condition.getSearchSendState())) {
			hqlBuffer.append(" AND t.sendState = ? ");
			condition.addParameter(new Integer(condition.getSearchSendState()));
		}
		condition.setSql(hqlBuffer.toString());
		return condition;
	}

	/**
	 * 根据ID数组批量作废或启用邮件发送日志HQL
	 * 
	 * @param condition
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	public MailSendLogCondition discardOrReuseMailSendLogHQL(MailSendLogCondition condition) {
		String hql = "update MailSendLog bd set bd.isEnabled = ? where 1=1";
		condition.addParameter(new Integer(condition.getSearchIsEnabled()));
		if (condition.getSearchLogIDs() != null && condition.getSearchLogIDs().length > 0) {
			hql += " and bd.logID in (" + condition.assemblyParameterListSQL(condition.getSearchLogIDs()) + ")";
			condition.addParameterList(condition.getSearchLogIDs());
		}
		condition.setSql(hql);
		return condition;
	}

	/**
	 * 根据ID数组获得邮件发送日志 HQL
	 * 
	 * @return
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	public BaseCondition getMailSendLogListByIDArrayHQL(String[] idArray) {
		BaseCondition condition = new BaseCondition();
		condition.setSql(" from MailSendLog where logID in (" + condition.assemblyParameterListSQL(idArray) + ")");
		condition.addParameterList(idArray);
		return condition;
	}

	/**
	 * 根据ID获得邮件发送日志HQL
	 * 
	 * @return
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	public String getMailSendLogByIDHQL() {
		return "from MailSendLog where logID = :logID";
	}

}
