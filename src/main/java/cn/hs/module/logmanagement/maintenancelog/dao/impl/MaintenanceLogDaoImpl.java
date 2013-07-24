/*
 * $Log: MaintenanceLogDaoImpl.java,v $
 * Revision 1.1  2012/05/23 09:27:53  guor
 * 初次提交
 *
 */
package cn.hs.module.logmanagement.maintenancelog.dao.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.hs.core.basedao.base.IBaseDao;
import cn.hs.module.logmanagement.maintenancelog.dao.IMaintenanceLogDao;
import cn.hs.module.logmanagement.maintenancelog.dao.require.MaintenanceLogRequire;
import cn.hs.module.logmanagement.maintenancelog.domain.MaintenanceLog;
import cn.hs.module.logmanagement.maintenancelog.domain.MaintenanceLogCondition;

/**
 * Title: MaintenanceLogDaoImpl<br>
 * Description: 维护日志持久层接口实现<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author MengW
 * @createDate 2011-9-19
 * @version $Revision: 11 $
 */
@Repository(value = "cn.hs.module.logmanagement.maintenancelog.dao.impl.MaintenanceLogDaoImpl")
public class MaintenanceLogDaoImpl implements IMaintenanceLogDao {
	@Autowired
	private IBaseDao<MaintenanceLog> dao;
	// 初始化maintenanceLogRequire
	@Autowired
	private MaintenanceLogRequire maintenanceLogRequire;

	/**
	 * 新增维护日志
	 * 
	 * @param maintenanceLog
	 *            维护日志实体对象
	 * @throws Exception
	 */
	public void addMaintenanceLog(MaintenanceLog maintenanceLog) throws Exception {
		dao.addEntity(maintenanceLog);
	}

	/**
	 * 批量增加维护日志
	 * 
	 * @param maintenanceLogCollection
	 *            维护日志实体对象集合
	 * @throws Exception
	 */
	public void batchAddMaintenanceLog(Collection<MaintenanceLog> maintenanceLogCollection) throws Exception {
		dao.batchAddEntities(maintenanceLogCollection);
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
		return (MaintenanceLog) dao.findObject(MaintenanceLog.class, condition.getSearchMaintenanceLogID());
	}

	/**
	 * 分页查询维护日志
	 * 
	 * @param condition
	 *            维护日志查询条件对象
	 * @return 返回维护日志实体对象集合
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<MaintenanceLog> listMaintenanceLogWithPage(MaintenanceLogCondition condition) throws Exception {
		maintenanceLogRequire.buildQueryMaintenanceLogHql(condition);
		List<MaintenanceLog> list = (List<MaintenanceLog>) dao.pagedQuery(condition);
		return list;
	}

	/**
	 * 获得维护日志的总数
	 * 
	 * @param condition
	 *            维护日志查询条件对象
	 * @return 返回维护日志总数
	 * @throws Exception
	 */
	public Long countMaintenanceLog(MaintenanceLogCondition condition) throws Exception {
		maintenanceLogRequire.buildCountMaintenanceLogHql(condition);
		return dao.countQuery(condition);
	}

}
