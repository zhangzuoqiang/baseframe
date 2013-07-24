/*
 * $Log: MaintenanceLogServiceImpl.java,v $
 * Revision 1.1  2012/05/23 09:27:53  guor
 * 初次提交
 *
 */
package cn.hs.module.logmanagement.maintenancelog.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.core.page.commond.BasePageCommond;
import cn.hs.core.page.commond.JSONObject;
import cn.hs.core.servicetemplate.pagetemplate.PageTemplate;
import cn.hs.module.logmanagement.maintenancelog.dao.IMaintenanceLogDao;
import cn.hs.module.logmanagement.maintenancelog.domain.MaintenanceLog;
import cn.hs.module.logmanagement.maintenancelog.domain.MaintenanceLogCondition;
import cn.hs.module.logmanagement.maintenancelog.domain.jsonbean.MaintenanceLogJsonBean;
import cn.hs.module.logmanagement.maintenancelog.service.IMaintenanceLogService;

/**
 * Title: MaintenanceLogServiceImpl<br>
 * Description: 维护日志业务层接口实现<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author MengW
 * @createDate 2011-9-19
 * @version $Revision: 75 $
 */
@Service(value = "cn.hs.module.logmanagement.maintenancelog.service.impl.MaintenanceLogServiceImpl")
public class MaintenanceLogServiceImpl extends PageTemplate implements IMaintenanceLogService {

	// 资源分类manager层接口
	@Autowired
	private IMaintenanceLogDao maintenanceLogDao;

	/**
	 * 新增维护日志
	 * 
	 * @param maintenanceLog
	 *            维护日志实体对象
	 * @throws Exception
	 */
	public void addMaintenanceLog(MaintenanceLog maintenanceLog) throws Exception {
		maintenanceLogDao.addMaintenanceLog(maintenanceLog);
	}

	/**
	 * 批量增加维护日志
	 * 
	 * @param maintenanceLogCollection
	 *            维护日志实体对象集合
	 * @throws Exception
	 */
	public void batchAddMaintenanceLog(Collection<MaintenanceLog> maintenanceLogCollection) throws Exception {
		maintenanceLogDao.batchAddMaintenanceLog(maintenanceLogCollection);
	}

	/**
	 * 根据条件获得维护日志
	 * 
	 * @param condition
	 *            维护日志查询条件对象
	 * @return 返回维护日志实体对象
	 * @throws Exception
	 */
	public MaintenanceLog getMaintenanceLog(MaintenanceLogCondition condition) throws Exception {
		return maintenanceLogDao.getMaintenanceLog(condition);
	}

	/**
	 * 获得维护日志总数
	 * 
	 * @param condition
	 *            维护日志查询条件对象
	 * @return 返回维护日志总数
	 * @throws Exception
	 */
	@Override
	protected Long findCount(BaseCondition condition) throws Exception {
		return maintenanceLogDao.countMaintenanceLog((MaintenanceLogCondition) condition);
	}

	/**
	 * 获得维护日志集合
	 * 
	 * @param condition
	 *            维护日志查询条件对象
	 * @return 返回维护日志实体对象集合
	 * @throws Exception
	 */
	@Override
	protected List<MaintenanceLog> findList(BaseCondition condition) throws Exception {
		return maintenanceLogDao.listMaintenanceLogWithPage((MaintenanceLogCondition) condition);
	}

	/**
	 * 处理分页信息
	 * 
	 * @param pageCommond
	 *            页面翻页属性基类
	 * @param condition
	 *            维护日志查询条件对象
	 * @throws Exception
	 */
	@Override
	public JSONObject doProcess(BasePageCommond pageCommond, BaseCondition condition) throws Exception {
		return process(pageCommond, condition);
	}

	@Override
	protected List<?> objListToJsonList(List<?> pageResultList) throws Exception {
		List<MaintenanceLogJsonBean> result = new ArrayList<MaintenanceLogJsonBean>();
		for (Object object : pageResultList) {
			MaintenanceLog maintenanceLog = (MaintenanceLog) object;
			MaintenanceLogJsonBean bean = new MaintenanceLogJsonBean();
			BeanUtils.copyProperties(maintenanceLog, bean);
			result.add(bean);
		}
		return result;
	}
}
