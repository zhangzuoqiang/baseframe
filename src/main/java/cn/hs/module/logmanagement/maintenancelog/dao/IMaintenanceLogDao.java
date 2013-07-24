/*
 * $Log: IMaintenanceLogDao.java,v $
 * Revision 1.1  2012/05/23 09:27:53  guor
 * 初次提交
 *
 */
package cn.hs.module.logmanagement.maintenancelog.dao;

import java.util.Collection;
import java.util.List;

import cn.hs.module.logmanagement.maintenancelog.domain.MaintenanceLog;
import cn.hs.module.logmanagement.maintenancelog.domain.MaintenanceLogCondition;

/**
 * Title: IMaintenanceLogDao<br>
 * Description: 维护日志持久层接口<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author MengW
 * @createDate 2011-9-19
 * @version $Revision: 11 $
 */
public interface IMaintenanceLogDao {

	/**
	 * 新增维护日志
	 * @param maintenanceLog 维护日志实体对象
	 * @throws Exception
	 */
	public void addMaintenanceLog(MaintenanceLog maintenanceLog) throws Exception;
	
	/**
	 * 批量增加维护日志
	 * @param maintenanceLogCollection 维护日志实体对象集合
	 * @throws Exception
	 */
	public void batchAddMaintenanceLog(Collection<MaintenanceLog> maintenanceLogCollection)throws Exception; 
	
	/**
	 * 根据条件获得维护日志
	 * @param condition 维护日志查询条件对象
	 * @return 返回维护日志实体对象
	 * @throws Exception
	 */
	public MaintenanceLog getMaintenanceLog(MaintenanceLogCondition condition) throws Exception; 
	
	/**
	 * 分页查询维护日志
	 * @param condition 维护日志查询条件对象
	 * @return 返回维护日志实体对象集合
	 * @throws Exception
	 */
	public List<MaintenanceLog> listMaintenanceLogWithPage(MaintenanceLogCondition condition) throws Exception;

	/**
	 * 获得维护日志的总数
	 * @param condition 维护日志查询条件对象
	 * @return 返回维护日志总数
	 * @throws Exception
	 */
	public Long countMaintenanceLog(MaintenanceLogCondition condition) throws Exception;
}
