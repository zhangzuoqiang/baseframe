package cn.hs.module.logmanagement.maintenancelog.domain.jsonbean;

import java.io.Serializable;

/**
 * 
 * Title: MaintenanceLogJsonBean<br>
 * Description: 系统日志jsonBean传输对象<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author Mill
 * @createDate 2012-8-23
 * @version $Revision:$
 */
public class MaintenanceLogJsonBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String maintenanceLogID;// 维护日志ID

	private String operatorID;// 操作人ID

	private String operatorIP;// 操作人IP

	private Long operationDate;// 操作日期

	private String operationPath;// 操作路径

	private String operationModule;// 操作模块

	private Integer operationType;// 操作类型

	private String beforeOperationData;// 操作前数据

	private String afterOperationData;// 操作后数据

	public String getMaintenanceLogID() {
		return maintenanceLogID;
	}

	public void setMaintenanceLogID(String maintenanceLogID) {
		this.maintenanceLogID = maintenanceLogID;
	}

	public String getOperatorID() {
		return operatorID;
	}

	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}

	public String getOperatorIP() {
		return operatorIP;
	}

	public void setOperatorIP(String operatorIP) {
		this.operatorIP = operatorIP;
	}

	public Long getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Long operationDate) {
		this.operationDate = operationDate;
	}

	public String getOperationPath() {
		return operationPath;
	}

	public void setOperationPath(String operationPath) {
		this.operationPath = operationPath;
	}

	public String getOperationModule() {
		return operationModule;
	}

	public void setOperationModule(String operationModule) {
		this.operationModule = operationModule;
	}

	public Integer getOperationType() {
		return operationType;
	}

	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}

	public String getBeforeOperationData() {
		return beforeOperationData;
	}

	public void setBeforeOperationData(String beforeOperationData) {
		this.beforeOperationData = beforeOperationData;
	}

	public String getAfterOperationData() {
		return afterOperationData;
	}

	public void setAfterOperationData(String afterOperationData) {
		this.afterOperationData = afterOperationData;
	}

}
