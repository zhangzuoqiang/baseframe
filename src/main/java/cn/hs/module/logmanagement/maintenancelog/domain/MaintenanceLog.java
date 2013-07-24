/*
 * $Log: MaintenanceLog.java,v $
 * Revision 1.1  2012/05/23 09:27:53  guor
 * 初次提交
 *
 */
package cn.hs.module.logmanagement.maintenancelog.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Title: MaintenanceLog<br>
 * Description: 维护日志实体对象<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author MengW
 * @createDate 2011-9-19
 * @version $Revision: 84 $
 */
@Entity
@Table(name = "BASE_MAINTENANCE_LOG")
public class MaintenanceLog implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 操作类型-增加
	 */
	public final static int OPERATION_TYPE_ADD = 1;

	/**
	 * 操作类型-修改
	 */
	public final static int OPERATION_TYPE_UPDATE = 2;

	/**
	 * 操作类型-批量增加
	 */
	public final static int OPERATION_TYPE_BATCHADD = 3;

	/**
	 * 操作类型-批量修改
	 */
	public final static int OPERATION_TYPE_BATCHUPDATE = 4;

	/**
	 * 操作类型-批量修改状态
	 */
	public final static int OPERATION_TYPE_BATCHUPDATESTATE = 5;

	/**
	 * 批量删除
	 */
	public final static int OPERATION_TYPE_BATCHDELETE = 6;

	private String maintenanceLogID;// 维护日志ID

	private String operatorID;// 操作人ID

	private String operatorIP;// 操作人IP

	private Long operationDate;// 操作日期

	private String operationPath;// 操作路径

	private String operationModule;// 操作模块

	private Integer operationType;// 操作类型

	private String beforeOperationData;// 操作前数据

	private String afterOperationData;// 操作后数据

	@Id
	@Column(name = "MAINTENANCE_LOG_ID")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	public String getMaintenanceLogID() {
		return maintenanceLogID;
	}

	public void setMaintenanceLogID(String maintenanceLogID) {
		this.maintenanceLogID = maintenanceLogID;
	}

	@Column(name = "OPERATOR_ID")
	public String getOperatorID() {
		return operatorID;
	}

	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}

	@Column(name = "OPERATOR_IP")
	public String getOperatorIP() {
		return operatorIP;
	}

	public void setOperatorIP(String operatorIP) {
		this.operatorIP = operatorIP;
	}

	@Column(name = "OPERATION_DATE")
	public Long getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Long operationDate) {
		this.operationDate = operationDate;
	}

	@Column(name = "OPERATION_PATH")
	public String getOperationPath() {
		return operationPath;
	}

	public void setOperationPath(String operationPath) {
		this.operationPath = operationPath;
	}

	@Column(name = "OPERATION_MODULE")
	public String getOperationModule() {
		return operationModule;
	}

	public void setOperationModule(String operationModule) {
		this.operationModule = operationModule;
	}

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "BEFORE_OPERATION_DATA")
	public String getBeforeOperationData() {
		return beforeOperationData;
	}

	public void setBeforeOperationData(String beforeOperationData) {
		this.beforeOperationData = beforeOperationData;
	}

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "AFTER_OPERATION_DATA")
	public String getAfterOperationData() {
		return afterOperationData;
	}

	public void setAfterOperationData(String afterOperationData) {
		this.afterOperationData = afterOperationData;
	}

	@Column(name = "OPERATION_TYPE")
	public Integer getOperationType() {
		return operationType;
	}

	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}

}
