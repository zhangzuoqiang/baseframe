/*
 * $Log: MaintenanceLogRequire.java,v $
 * Revision 1.1  2012/05/23 09:27:53  guor
 * 初次提交
 *
 */
package cn.hs.module.logmanagement.maintenancelog.dao.require;

import org.springframework.stereotype.Repository;

import cn.hs.commons.DateUtil;
import cn.hs.core.annotation.EscapeProperty;
import cn.hs.core.page.commond.SortInfoBean;
import cn.hs.module.logmanagement.maintenancelog.domain.MaintenanceLogCondition;

/**
 * Title: MaintenanceLogRequire<br>
 * Description: 维护日志查询封装类<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author MengW
 * @createDate 2011-9-19
 * @version $Revision: 11 $
 */
@Repository(value = "cn.hs.module.logmanagement.maintenancelog.dao.require.MaintenanceLogRequire")
public class MaintenanceLogRequire {

	/**
	 * 构建查询维护日志集合HQL
	 * 
	 * @param condition
	 *            维护日志查询条件对象
	 * @return 返回查询维护日志HQL语句
	 * @throws Exception
	 */
	public MaintenanceLogCondition buildQueryMaintenanceLogHql(MaintenanceLogCondition condition) throws Exception {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append(" select t from MaintenanceLog t where 1=1");
		buildQueryParameter(condition, hqlBuffer);
		// 拼装排序
		SortInfoBean sortInfo = condition.getSortInfo();
		if (sortInfo != null) {
			hqlBuffer.append(" order by " + sortInfo.getProperty() + " " + sortInfo.getDirection());
		} else {
			hqlBuffer.append(" order by t.operationDate desc,t.maintenanceLogID desc");
		}
		condition.setSql(hqlBuffer.toString());
		return condition;
	}

	/**
	 * 构建统计维护日志总数HQL
	 * 
	 * @param condition
	 *            维护日志查询条件对象
	 * @return 返回查询维护日志总数HQL语句
	 * @throws Exception
	 */
	public MaintenanceLogCondition buildCountMaintenanceLogHql(MaintenanceLogCondition condition) throws Exception {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append(" select count(t.maintenanceLogID) from MaintenanceLog t where 1=1");
		buildQueryParameter(condition, hqlBuffer);
		condition.setSql(hqlBuffer.toString());
		return condition;
	}

	/**
	 * 构建查询参数
	 * 
	 * @param condition
	 *            维护日志查询条件对象
	 * @param hqlBuffer
	 *            查询维护日志HQL语句缓存字符串
	 */
	@EscapeProperty(escapePropertyNames = "searchOperationModule")
	private void buildQueryParameter(MaintenanceLogCondition condition, StringBuffer hqlBuffer) throws Exception {
		if (condition.getSearchOperationModule() != null && !"".equals(condition.getSearchOperationModule().trim())) {
			hqlBuffer.append(" and t.operationModule like ? escape '/'");
			condition.addParameter("%" + condition.getSearchOperationModule().trim() + "%");
		}
		if (condition.getSearchOperationType() != null && condition.getSearchOperationType().intValue() != 0) {
			hqlBuffer.append(" and t.operationType = ?");
			condition.addParameter(condition.getSearchOperationType());
		}
		if (condition.getSearchOperationUserID() != null && !"".equals(condition.getSearchOperationUserID().trim())) {
			hqlBuffer.append(" and t.operatorID like ?operatorID");
			condition.addParameter(condition.getSearchOperationUserID().trim());
		}
		if (condition.getSearchStartOperationDate() != null && !"".equals(condition.getSearchStartOperationDate().trim()) && condition.getSearchEndOperationDate() != null && !"".equals(condition.getSearchEndOperationDate().trim())) {
			hqlBuffer.append(" and t.operationDate  between ? and ?");
			condition.addParameter(DateUtil.getDateByString(condition.getSearchStartOperationDate().trim() + " 00:00:00"));
			condition.addParameter(DateUtil.getDateByString(condition.getSearchEndOperationDate().trim() + " 23:59:59"));
		} else {
			if (condition.getSearchStartOperationDate() != null && !"".equals(condition.getSearchStartOperationDate().trim())) {
				hqlBuffer.append(" and t.operationDate  >= ?");
				condition.addParameter(DateUtil.getDateByString(condition.getSearchStartOperationDate().trim() + " 00:00:00"));
			}
			if (condition.getSearchEndOperationDate() != null && !"".equals(condition.getSearchEndOperationDate().trim())) {
				hqlBuffer.append(" and t.operationDate  <= ?");
				condition.addParameter(DateUtil.getDateByString(condition.getSearchEndOperationDate().trim() + " 23:59:59"));
			}
		}
	}

}
