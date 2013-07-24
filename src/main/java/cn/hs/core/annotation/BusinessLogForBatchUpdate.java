/*
 * $Log: BusinessLogForBatchUpdate.java,v $
 * Revision 1.1  2012/05/23 09:27:47  guor
 * 初次提交
 *
 */
package cn.hs.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.hs.module.logmanagement.maintenancelog.domain.MaintenanceLog;

/**
 * Title: BusinessLogForBatchUpdate<br>
 * Description: 业务日志对应批量更新操作注解<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author MengW
 * @createDate 2011-9-21
 * @version $Revision: 1.1 $
 */
@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.METHOD) 
@Documented
public @interface BusinessLogForBatchUpdate {

	/**
	 * 操作模块
	 * @return
	 */
	String operationModule();
	
	/**
	 * 操作类型
	 * @return
	 */
	int operationType() default MaintenanceLog.OPERATION_TYPE_BATCHUPDATE;
	
	/**
	 * 获取实体主键方法名称
	 * @return
	 */
	String getEntityPKMethodName();
	
	/**
	 * 根据ID数组获取多个对象方法名称
	 * @return
	 */
	String getObjectsByIDArrayMethodName();
	
	/**
	 * 实体对象Class
	 * @return
	 */
	Class entityClass();
	
	/**
	 * 主键数组类型CLASS
	 * @return
	 */
	Class pkArrayClass();
}
