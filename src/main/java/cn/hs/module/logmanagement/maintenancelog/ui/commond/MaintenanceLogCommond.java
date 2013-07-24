/*
 * $Log: MaintenanceLogCommond.java,v $
 * Revision 1.1  2012/05/23 09:27:53  guor
 * 初次提交
 *
 */
package cn.hs.module.logmanagement.maintenancelog.ui.commond;

import cn.hs.core.page.commond.BasePageCommond;

/**
 * Title: MaintenanceLogCommond<br>
 * Description: 维护日志Commond<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author MengW
 * @createDate 2011-9-23
 * @version $Revision: 11 $
 */
public class MaintenanceLogCommond extends BasePageCommond {
	/**
	 * 查询条件：维护日志ID
	 */
	private String searchMaintenanceLogID;

	/**
	 * 查询条件：操作模块
	 */
	private String searchOperationModule;

	/**
	 * 查询条件：操作类型
	 */
	private Integer searchOperationType;

	/**
	 * 查询条件：操作日期-开始
	 */
	private String searchStartOperationDate;

	/**
	 * 查询条件：操作日期-结束
	 */
	private String searchEndOperationDate;

	/**
	 * 查询条件：操作人ID
	 */
	private String searchOperationUserID;

	public String getSearchMaintenanceLogID() {
		return searchMaintenanceLogID;
	}

	public void setSearchMaintenanceLogID(String searchMaintenanceLogID) {
		this.searchMaintenanceLogID = searchMaintenanceLogID;
	}

	public String getSearchOperationModule() {
		return searchOperationModule;
	}

	public void setSearchOperationModule(String searchOperationModule) {
		this.searchOperationModule = searchOperationModule;
	}

	public Integer getSearchOperationType() {
		return searchOperationType;
	}

	public void setSearchOperationType(Integer searchOperationType) {
		this.searchOperationType = searchOperationType;
	}

	public String getSearchStartOperationDate() {
		return searchStartOperationDate;
	}

	public void setSearchStartOperationDate(String searchStartOperationDate) {
		this.searchStartOperationDate = searchStartOperationDate;
	}

	public String getSearchEndOperationDate() {
		return searchEndOperationDate;
	}

	public void setSearchEndOperationDate(String searchEndOperationDate) {
		this.searchEndOperationDate = searchEndOperationDate;
	}

	public String getSearchOperationUserID() {
		return searchOperationUserID;
	}

	public void setSearchOperationUserID(String searchOperationUserID) {
		this.searchOperationUserID = searchOperationUserID;
	}

}
