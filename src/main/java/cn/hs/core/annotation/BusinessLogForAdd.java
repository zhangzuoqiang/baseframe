/*
 * $Log: BusinessLogForAdd.java,v $
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
 * Title: BusinessLogForAdd<br>
 * Description: 业务日志对应增加操作注解<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author MengW
 * @createDate 2011-9-20
 * @version $Revision: 1.1 $
 */
@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.METHOD) 
@Documented
public @interface BusinessLogForAdd {

	/**
	 * 操作模块
	 * @return
	 */
	String operationModule();
	
	/**
	 * 操作类型
	 * @return
	 */
	int operationType() default MaintenanceLog.OPERATION_TYPE_ADD;
}
