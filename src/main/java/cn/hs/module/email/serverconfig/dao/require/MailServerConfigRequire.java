package cn.hs.module.email.serverconfig.dao.require;

import org.springframework.stereotype.Component;

import cn.hs.core.annotation.EscapeProperty;
import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.core.page.commond.SortInfoBean;
import cn.hs.module.email.serverconfig.domain.MailServerConfigCondition;

/**
 * Title: MailServerConfigRequire<br>
 * Description: 邮件服务器配置查询SQL拼写对象<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author LiuHG
 * @createDate 2012-08-01
 * @version $Revision$
 */
@Component(value = "cn.hs.module.email.serverconfig.dao.require.MailServerConfigRequire")
public class MailServerConfigRequire {
	/**
	 * list邮件服务器配置集合HQL
	 * 
	 * @param condition
	 * @return
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@EscapeProperty(escapePropertyNames = "searchConfigName")
	public MailServerConfigCondition listMailServerConfig(MailServerConfigCondition condition) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append(" SELECT t FROM MailServerConfig t WHERE 1=1");

		if (condition.getSearchConfigID() != null && !"".equals(condition.getSearchConfigID())) {
			hqlBuffer.append(" AND t.configID = ? ");
			condition.addParameter(condition.getSearchConfigID());
		}
		if (condition.getSearchConfigName() != null && !"".equals(condition.getSearchConfigName())) {
			hqlBuffer.append(" AND t.configName like ? escape '/'");
			condition.addParameter("%" + condition.getSearchConfigName() + "%");
		}
		if (condition.getSearchConfigAlias() != null && !"".equals(condition.getSearchConfigAlias())) {
			hqlBuffer.append(" AND t.configAlias = ? ");
			condition.addParameter(condition.getSearchConfigAlias());
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
	 * count邮件服务器配置集合HQL
	 * 
	 * @param condition
	 * @return
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@EscapeProperty(escapePropertyNames = "searchConfigName")
	public MailServerConfigCondition countMailServerConfigHql(MailServerConfigCondition condition) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append(" SELECT COUNT(*) from MailServerConfig t WHERE 1=1");
		if (condition.getSearchConfigID() != null && !"".equals(condition.getSearchConfigID())) {
			hqlBuffer.append(" AND t.configID = ? ");
			condition.addParameter(condition.getSearchConfigID());
		}
		if (condition.getSearchConfigName() != null && !"".equals(condition.getSearchConfigName())) {
			hqlBuffer.append(" AND t.configName like ? escape '/'");
			condition.addParameter("%" + condition.getSearchConfigName() + "%");
		}
		condition.setSql(hqlBuffer.toString());
		return condition;
	}

	/**
	 * 根据ID数组批量作废或启用邮件服务器配置HQL
	 * 
	 * @param condition
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	public MailServerConfigCondition discardOrReuseMailServerConfigHQL(MailServerConfigCondition condition) {
		String hql = "update MailServerConfig bd set bd.isEnabled = ? where 1=1";
		condition.addParameter(new Integer(condition.getSearchIsEnabled()));
		if (condition.getSearchConfigIDs() != null && condition.getSearchConfigIDs().length > 0) {
			hql += " and bd.configID in (" + condition.assemblyParameterListSQL(condition.getSearchConfigIDs()) + ")";
			condition.addParameterList(condition.getSearchConfigIDs());
		}
		condition.setSql(hql);
		return condition;
	}

	/**
	 * 根据ID数组获得邮件服务器配置 HQL
	 * 
	 * @return
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	public BaseCondition getMailServerConfigListByIDArrayHQL(String[] idArray) {
		BaseCondition condition = new BaseCondition();
		condition.setSql(" from MailServerConfig where configID in (" + condition.assemblyParameterListSQL(idArray) + ")");
		condition.addParameterList(idArray);
		return condition;
	}

	/**
	 * 根据ID获得邮件服务器配置HQL
	 * 
	 * @return
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	public String getMailServerConfigByIDHQL() {
		return "from MailServerConfig where configID = :configID";
	}

}
