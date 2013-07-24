/*
 * $Log: SystemBusinessLog.java,v $
 * Revision 1.1  2012/05/23 09:27:47  guor
 * 初次提交
 *
 */
package cn.hs.core.aop.log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cn.hs.commons.DateUtil;
import cn.hs.commons.utils.RequestContextUtil;
import cn.hs.core.annotation.BusinessLogForAdd;
import cn.hs.core.annotation.BusinessLogForBatchAdd;
import cn.hs.core.annotation.BusinessLogForBatchDelete;
import cn.hs.core.annotation.BusinessLogForBatchUpdate;
import cn.hs.core.annotation.BusinessLogForBatchUpdateState;
import cn.hs.core.annotation.BusinessLogForUpdate;
import cn.hs.core.configure.GlobalConfigure;
import cn.hs.core.log4j.ILog4jManager;
import cn.hs.core.springext.mvc.SessionConstant;
import cn.hs.core.springext.security.casclient.domain.json.CasUser;
import cn.hs.module.logmanagement.maintenancelog.domain.MaintenanceLog;
import cn.hs.module.logmanagement.maintenancelog.service.IMaintenanceLogService;

/**
 * Title: SystemBusinessLog<br>
 * Description: 系统业务日志切面<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author MengW
 * @createDate 2011-9-20
 * @version $Revision: 6 $
 */
@Aspect
@Component
@Order(value = 2)
public class SystemBusinessLog {

	// 初始化日志接口
	@Autowired
	private ILog4jManager log4jManager;

	// 初始化维护日志接口
	@Autowired
	private IMaintenanceLogService maintenanceLogService;

	@Autowired
	private GlobalConfigure globalConfigure;

	private ThreadLocal<String> preUpdateData = new ThreadLocal<String>();// 更新前数据

	private ThreadLocal<Map<Object, String>> preUpdateDataMap = new ThreadLocal<Map<Object, String>>();// 更新前数据map

	private Collection<Object> objects = null;// 待处理对象集合

	/**
	 * 增加方法切入点
	 */
	@Pointcut("@annotation(cn.hs.core.annotation.BusinessLogForAdd)")
	public void addMethod() {
	}

	/**
	 * 更新方法切入点
	 */
	@Pointcut("@annotation(cn.hs.core.annotation.BusinessLogForUpdate)")
	public void updateMethod() {
	}

	/**
	 * 批量增加方法切入点
	 */
	@Pointcut("@annotation(cn.hs.core.annotation.BusinessLogForBatchAdd)")
	public void batchAddMethod() {
	}

	/**
	 * 批量更新方法切入点
	 */
	@Pointcut("@annotation(cn.hs.core.annotation.BusinessLogForBatchUpdate)")
	public void batchUpdateMethod() {
	}

	/**
	 * 批量更新状态方法切入点
	 */
	@Pointcut("@annotation(cn.hs.core.annotation.BusinessLogForBatchUpdateState)")
	public void batchUpdateStateMethod() {
	}

	/**
	 * 批量删除方法切入点
	 */
	@Pointcut("@annotation(cn.hs.core.annotation.BusinessLogForBatchDelete)")
	public void batchDeleteMethod() {
	}

	/**
	 * 增加方法后置通知
	 * 
	 * @param jp
	 * @param businessLog
	 * @param object
	 */
	@AfterReturning(pointcut = "addMethod() && @annotation(businessLog) && args(saveObject,..)")
	public void afterDoAddData(JoinPoint jp, BusinessLogForAdd businessLog, Object saveObject) {
		log4jManager.debugCustomLog(getClass().getName(), "afterDoAddData", "进入增加方法后置通知，数据：" + saveObject.toString());
		if (globalConfigure.getAddMethodWithLog() != null && "y".equals(globalConfigure.getAddMethodWithLog().trim())) {
			try {
				MaintenanceLog maintenanceLog = buildMaintenanceLog(null, saveObject.toString());
				maintenanceLog.setOperationModule(businessLog.operationModule());
				maintenanceLog.setOperationType(businessLog.operationType());
				maintenanceLogService.addMaintenanceLog(maintenanceLog);
			} catch (Exception e) {
				log4jManager.saveExceptionLog(getClass().getName(), "afterThrowing", "异常发生在[" + getClass() + "]类方法[afterDoAddData],异常信息[" + e.getMessage() + "]", e);
				e.printStackTrace();
			}
		}
	}

	/**
	 * 更新方法前置通知
	 * 
	 * @param jp
	 * @param businessLog
	 * @param object
	 */
	@Before("updateMethod() && @annotation(businessLog) && args(updateObject,..)")
	public void beforeDoUpdateData(JoinPoint jp, BusinessLogForUpdate businessLog, Object updateObject) {
		log4jManager.debugCustomLog(getClass().getName(), "beforeDoUpdateData", "进入更新方法前置通知");
		if (globalConfigure.getUpdateMethodWithLog() != null && "y".equals(globalConfigure.getUpdateMethodWithLog().trim())) {
			preUpdateData.set(getUpdateObjectData(jp, businessLog, updateObject));
			log4jManager.debugCustomLog(getClass().getName(), "beforeDoUpdateData", "结束数据更新前置通知,更新前数据：" + preUpdateData.get());
		}
	}

	/**
	 * 更新方法后置通知
	 * 
	 * @param jp
	 * @param businessLog
	 * @param object
	 */
	@AfterReturning(pointcut = "updateMethod() && @annotation(businessLog) && args(updateObject,..)")
	public void afterDoUpdateData(JoinPoint jp, BusinessLogForUpdate businessLog, Object updateObject) {
		log4jManager.debugCustomLog(getClass().getName(), "afterDoUpdateData", "进入更新方法后置通知");
		if (globalConfigure.getUpdateMethodWithLog() != null && "y".equals(globalConfigure.getUpdateMethodWithLog().trim())) {
			try {
				String postUpdateData = getUpdateObjectData(jp, businessLog, updateObject);
				log4jManager.debugCustomLog(getClass().getName(), "beforeDoUpdateData", "更新后数据：" + postUpdateData);
				MaintenanceLog maintenanceLog = buildMaintenanceLog(preUpdateData.get(), postUpdateData);
				maintenanceLog.setOperationModule(businessLog.operationModule());
				maintenanceLog.setOperationType(businessLog.operationType());
				maintenanceLogService.addMaintenanceLog(maintenanceLog);
			} catch (Exception e) {
				log4jManager.saveExceptionLog(getClass().getName(), "afterThrowing", "异常发生在[" + getClass() + "]类方法[afterDoUpdateData],异常信息[" + e.getMessage() + "]", e);
				e.printStackTrace();
			}
		}
	}

	/**
	 * 批量增加方法后置通知
	 * 
	 * @param jp
	 * @param businessLog
	 * @param saveObjects
	 */
	@AfterReturning(pointcut = "batchAddMethod() && @annotation(businessLog) && args(saveObjects,..)")
	public void afterDoBatchAddData(JoinPoint jp, BusinessLogForBatchAdd businessLog, Collection<Object> saveObjects) {
		log4jManager.debugCustomLog(getClass().getName(), "afterDoBatchAddData", "进入批量增加方法后置通知");
		if (globalConfigure.getBatchAddMethodWithLog() != null && "y".equals(globalConfigure.getBatchAddMethodWithLog().trim())) {
			try {
				if (saveObjects != null && saveObjects.size() > 0) {
					maintenanceLogService.batchAddMaintenanceLog(buildMaintenanceLogsForBatchAdd(businessLog.operationType(), businessLog.operationModule(), saveObjects));
				}
			} catch (Exception e) {
				log4jManager.saveExceptionLog(getClass().getName(), "afterThrowing", "异常发生在[" + getClass() + "]类方法[afterDoBatchAddData],异常信息[" + e.getMessage() + "]", e);
				e.printStackTrace();
			}
		}
	}

	/**
	 * 批量更新方法前置通知
	 * 
	 * @param jp
	 * @param businessLog
	 * @param updateObjects
	 */
	@SuppressWarnings("unchecked")
	@Before("batchUpdateMethod() && @annotation(businessLog) && args(updateObjects,..)")
	public void beforeDoBatchUpdateData(JoinPoint jp, BusinessLogForBatchUpdate businessLog, Collection<Object> updateObjects) {
		log4jManager.debugCustomLog(getClass().getName(), "beforeDoBatchUpdateData", "进入批量更新方法前置通知");
		if (globalConfigure.getBatchUpdateMethodWithLog() != null && "y".equals(globalConfigure.getBatchUpdateMethodWithLog().trim())) {
			try {
				Collection<Object> preUpdateDatas = getObjectsForBatchUpdate(jp, updateObjects, businessLog);
				if (preUpdateDatas != null && preUpdateDatas.size() > 0) {
					Method getPKMethod = businessLog.entityClass().getMethod(businessLog.getEntityPKMethodName(), new Class[] {});
					buildUpdateDataMap(preUpdateDatas, getPKMethod);
				}
			} catch (Exception e) {
				log4jManager.saveExceptionLog(getClass().getName(), "afterThrowing", "异常发生在[" + getClass() + "]类方法[beforeDoBatchUpdateData],异常信息[" + e.getMessage() + "]", e);
				e.printStackTrace();
			}
		}
	}

	/**
	 * 批量更新方法后置通知
	 * 
	 * @param jp
	 * @param businessLog
	 * @param updateObjects
	 */
	@AfterReturning(pointcut = "batchUpdateMethod() && @annotation(businessLog) && args(updateObjects,..)")
	public void afterDoBatchUpdateStateData(JoinPoint jp, BusinessLogForBatchUpdate businessLog, Collection<Object> updateObjects) {
		log4jManager.debugCustomLog(getClass().getName(), "afterDoBatchUpdateStateData", "进入批量更新方法后置通知");
		if (globalConfigure.getBatchUpdateMethodWithLog() != null && "y".equals(globalConfigure.getBatchUpdateMethodWithLog().trim())) {
			try {
				Collection<Object> postUpdateDatas = getObjectsForBatchUpdate(jp, updateObjects, businessLog);
				if (postUpdateDatas != null && postUpdateDatas.size() > 0 && preUpdateDataMap.get() != null && preUpdateDataMap.get().size() > 0) {
					processAfterBatchUpdateData(postUpdateDatas, businessLog.entityClass(), businessLog.getEntityPKMethodName(), businessLog.operationModule(), businessLog.operationType());
				}
			} catch (Exception e) {
				log4jManager.saveExceptionLog(getClass().getName(), "afterThrowing", "异常发生在[" + getClass() + "]类方法[afterDoBatchUpdateStateData],异常信息[" + e.getMessage() + "]", e);
				e.printStackTrace();
			}
		}
	}

	/**
	 * 批量更新状态方法前置通知
	 * 
	 * @param jp
	 * @param businessLog
	 * @param condition
	 */
	@SuppressWarnings("unchecked")
	@Before("batchUpdateStateMethod() && @annotation(businessLog) && args(condition,..)")
	public void beforeDoBatchUpdateStateData(JoinPoint jp, BusinessLogForBatchUpdateState businessLog, Object condition) {
		log4jManager.debugCustomLog(getClass().getName(), "beforeDoBatchUpdateStateData", "进入批量更新状态方法前置通知");
		if (globalConfigure.getBatchUpdateStateMethodWithLog() != null && "y".equals(globalConfigure.getBatchUpdateStateMethodWithLog().trim())) {
			try {
				Collection<Object> preUpdateDatas = getObjectsForConditionObject(jp, condition, businessLog.getPKArrayMethodName(), businessLog.getObjectsByIDArrayMethodName(), businessLog.pkArrayClass());
				if (preUpdateDatas != null && preUpdateDatas.size() > 0) {
					Method getPKMethod = businessLog.entityClass().getMethod(businessLog.getEntityPKMethodName(), new Class[] {});
					buildUpdateDataMap(preUpdateDatas, getPKMethod);
				}
			} catch (Exception e) {
				log4jManager.saveExceptionLog(getClass().getName(), "afterThrowing", "异常发生在[" + getClass() + "]类方法[beforeDoBatchUpdateStateData],异常信息[" + e.getMessage() + "]", e);
				e.printStackTrace();
			}
		}
	}

	/**
	 * 批量更新状态方法后置通知
	 * 
	 * @param jp
	 * @param businessLog
	 * @param condition
	 */
	@AfterReturning(pointcut = "batchUpdateStateMethod() && @annotation(businessLog) && args(condition,..)")
	public void afterDoBatchUpdateStateData(JoinPoint jp, BusinessLogForBatchUpdateState businessLog, Object condition) {
		log4jManager.debugCustomLog(getClass().getName(), "afterDoBatchUpdateStateData", "进入批量更新状态方法后置通知");
		if (globalConfigure.getBatchUpdateStateMethodWithLog() != null && "y".equals(globalConfigure.getBatchUpdateStateMethodWithLog().trim())) {
			try {
				Collection<Object> postUpdateDatas = getObjectsForConditionObject(jp, condition, businessLog.getPKArrayMethodName(), businessLog.getObjectsByIDArrayMethodName(), businessLog.pkArrayClass());
				if (postUpdateDatas != null && postUpdateDatas.size() > 0 && preUpdateDataMap.get() != null && preUpdateDataMap.get().size() > 0) {
					processAfterBatchUpdateData(postUpdateDatas, businessLog.entityClass(), businessLog.getEntityPKMethodName(), businessLog.operationModule(), businessLog.operationType());
				}
			} catch (Exception e) {
				log4jManager.saveExceptionLog(getClass().getName(), "afterThrowing", "异常发生在[" + getClass() + "]类方法[afterDoBatchUpdateStateData],异常信息[" + e.getMessage() + "]", e);
				e.printStackTrace();
			}
		}
	}

	/**
	 * 批量删除方法前置通知
	 * 
	 * @param jp
	 * @param businessLog
	 * @param condition
	 */
	@Before("batchDeleteMethod() && @annotation(businessLog) && args(condition,..)")
	public void beforeDoBatchDeleteData(JoinPoint jp, BusinessLogForBatchDelete businessLog, Object condition) {
		log4jManager.debugCustomLog(getClass().getName(), "beforeDoBatchDeleteData", "进入批量删除方法前置通知");
		if (globalConfigure.getBatchDeleteMethodWithLog() != null && "y".equals(globalConfigure.getBatchDeleteMethodWithLog().trim())) {
			try {
				objects = getObjectsForConditionObject(jp, condition, businessLog.getPKArrayMethodName(), businessLog.getObjectsByIDArrayMethodName(), businessLog.pkArrayClass());
			} catch (Exception e) {
				log4jManager.saveExceptionLog(getClass().getName(), "afterThrowing", "异常发生在[" + getClass() + "]类方法[beforeDoBatchDeleteData],异常信息[" + e.getMessage() + "]", e);
				e.printStackTrace();
			}
		}
	}

	/**
	 * 批量删除方法后置通知
	 * 
	 * @param jp
	 * @param businessLog
	 * @param condition
	 */
	@AfterReturning(pointcut = "batchDeleteMethod() && @annotation(businessLog) && args(condition,..)")
	public void afterDoBatchDeleteData(JoinPoint jp, BusinessLogForBatchDelete businessLog, Object condition) {
		log4jManager.debugCustomLog(getClass().getName(), "afterDoBatchDeleteData", "进入批量删除方法后置通知");
		if (globalConfigure.getBatchDeleteMethodWithLog() != null && "y".equals(globalConfigure.getBatchDeleteMethodWithLog().trim())) {
			try {
				if (objects != null && objects.size() > 0) {
					maintenanceLogService.batchAddMaintenanceLog(buildMaintenanceLogsForBatchDelete(businessLog.operationType(), businessLog.operationModule(), objects));
				}
			} catch (Exception e) {
				log4jManager.saveExceptionLog(getClass().getName(), "afterThrowing", "异常发生在[" + getClass() + "]类方法[afterDoBatchDeleteData],异常信息[" + e.getMessage() + "]", e);
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获得更新对象数据
	 * 
	 * @param jp
	 * @param businessLog
	 * @param updateObject
	 * @return
	 */
	private String getUpdateObjectData(JoinPoint jp, BusinessLogForUpdate businessLog, Object updateObject) {
		String result = "";
		try {
			// 目标对象
			Object targetObject = jp.getTarget();
			// 目标对象CLASS
			Class<? extends Object> targetObjectClass = targetObject.getClass();
			// 更新对象CLASS
			Class<? extends Object> updateObjClass = updateObject.getClass();
			// 获得对象方法
			Method getObjectMethod = targetObjectClass.getMethod(businessLog.getObjectByIDMethodName(), businessLog.pkClass());
			// 获得主键方法
			Method getPKMethod = updateObjClass.getMethod(businessLog.getEntityPKMethodName(), new Class[] {});
			// 主键
			Object pkValue = getPKMethod.invoke(updateObject, new Object[] {});
			// 更新前对象
			Object preUpdateObject = getObjectMethod.invoke(targetObject, pkValue);
			result = preUpdateObject.toString();
			preUpdateObject = null;

		} catch (Exception e) {
			log4jManager.saveExceptionLog(getClass().getName(), "afterThrowing", "异常发生在[" + getClass() + "]类方法[getUpdateObjectData],异常信息[" + e.getMessage() + "]", e);
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 构建维护日志对象
	 * 
	 * @param beforeOperationData
	 * @param afterOperationData
	 * @return
	 */
	private MaintenanceLog buildMaintenanceLog(String beforeOperationData, String afterOperationData) {
		MaintenanceLog maintenanceLog = new MaintenanceLog();
		HttpSession session = null;
		CasUser user = null;
		HttpServletRequest request = RequestContextUtil.getRequest();
		if (request != null) {
			session = request.getSession();
			maintenanceLog.setOperatorIP(request.getRemoteAddr());
			maintenanceLog.setOperationPath(request.getRequestURL().toString());
		}
		if (session != null) {
			user = (CasUser) session.getAttribute(SessionConstant.USER_INFO);
			if (user != null) {
				maintenanceLog.setOperatorID(user.getLoginID() + "");
				// }else if(outerUser!=null){
				// maintenanceLog.setOperatorID(outerUser.getLoginID()+"");
			}
		}
		maintenanceLog.setOperationDate(DateUtil.getCurrentTimeLong());
		if (beforeOperationData != null) {
			maintenanceLog.setBeforeOperationData(beforeOperationData);
		}
		if (afterOperationData != null) {
			maintenanceLog.setAfterOperationData(afterOperationData);
		}
		return maintenanceLog;
	}

	/**
	 * 构建维护日志对象集合对应批量增加
	 * 
	 * @param operationModule
	 * @param saveObjects
	 * @return
	 */
	private Collection<MaintenanceLog> buildMaintenanceLogsForBatchAdd(Integer operationType, String operationModule, Collection<Object> objects) {
		Collection<MaintenanceLog> maintenanceLogs = new ArrayList<MaintenanceLog>();
		MaintenanceLog maintenanceLog = null;
		Iterator<Object> objectIterator = objects.iterator();
		while (objectIterator.hasNext()) {
			Object obj = objectIterator.next();
			maintenanceLog = buildMaintenanceLog(null, obj.toString());
			maintenanceLog.setOperationModule(operationModule);
			maintenanceLog.setOperationType(operationType);
			maintenanceLogs.add(maintenanceLog);
		}
		return maintenanceLogs;
	}

	/**
	 * 构建维护日志对象集合对应批量删除
	 * 
	 * @param operationModule
	 * @param saveObjects
	 * @return
	 */
	private Collection<MaintenanceLog> buildMaintenanceLogsForBatchDelete(Integer operationType, String operationModule, Collection<Object> objects) {
		Collection<MaintenanceLog> maintenanceLogs = new ArrayList<MaintenanceLog>();
		MaintenanceLog maintenanceLog = null;
		Iterator<Object> objectIterator = objects.iterator();
		while (objectIterator.hasNext()) {
			Object obj = objectIterator.next();
			maintenanceLog = buildMaintenanceLog(obj.toString(), null);
			maintenanceLog.setOperationModule(operationModule);
			maintenanceLog.setOperationType(operationType);
			maintenanceLogs.add(maintenanceLog);
		}
		return maintenanceLogs;
	}

	/**
	 * 根据主键数组获得对象集合
	 * 
	 * @param targetObject
	 * @param pkArray
	 * @param getObjectsMethod
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Collection<Object> getObjectsByPKArray(Object targetObject, Object pkArray, Method getObjectsMethod) {
		try {
			// 更新前对象集合
			return (Collection<Object>) getObjectsMethod.invoke(targetObject, pkArray);
		} catch (Exception e) {
			log4jManager.saveExceptionLog(getClass().getName(), "afterThrowing", "异常发生在[" + getClass() + "]类方法[getObjectsByPKArray],异常信息[" + e.getMessage() + "]", e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 构建更新数据map
	 * 
	 * @param datas
	 * @param getPKMethod
	 */
	private void buildUpdateDataMap(Collection<Object> datas, Method getPKMethod) {
		try {
			// 构建更新前数据map
			HashMap<Object, String> preUpdateData = new HashMap<Object, String>();
			Iterator<Object> objectIterator = datas.iterator();
			while (objectIterator.hasNext()) {
				Object obj = objectIterator.next();
				// 主键
				Object pkValue = getPKMethod.invoke(obj, new Object[] {});
				preUpdateData.put(pkValue, obj.toString());
				preUpdateDataMap.set(preUpdateData);
			}
		} catch (Exception e) {
			log4jManager.saveExceptionLog(getClass().getName(), "afterThrowing", "异常发生在[" + getClass() + "]类方法[buildUpdateDataMap],异常信息[" + e.getMessage() + "]", e);
			e.printStackTrace();
		}

	}

	/**
	 * 获得对象集合(从条件对象中取出主键数组)
	 * 
	 * @param jp
	 * @param condition
	 * @param businessLog
	 * @return
	 */
	private Collection<Object> getObjectsForConditionObject(JoinPoint jp, Object condition, String pkArrayMethodName, String getObjectsMethodName, Class pkArrayClass) {
		// 目标对象
		Object targetObject = jp.getTarget();
		// 条件对象Class
		Class<? extends Object> conditionClass = condition.getClass();
		// 目标对象CLASS
		Class<? extends Object> targetObjectClass = targetObject.getClass();
		try {
			// 获得主键方法
			Method getPKArrayMethod = conditionClass.getMethod(pkArrayMethodName, new Class[] {});
			// 从条件对象中获得主键数组
			Object pkArray = getPKArrayMethod.invoke(condition, new Object[] {});
			// 获得对象集合方法
			Method getObjectsMethod = targetObjectClass.getMethod(getObjectsMethodName, pkArrayClass);
			return getObjectsByPKArray(targetObject, pkArray, getObjectsMethod);
		} catch (Exception e) {
			log4jManager.saveExceptionLog(getClass().getName(), "afterThrowing", "异常发生在[" + getClass() + "]类方法[getObjectsForConditionObject],异常信息[" + e.getMessage() + "]", e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 批量更新时获得对象集合
	 * 
	 * @param jp
	 * @param updateObjects
	 * @param businessLog
	 * @return
	 */
	@SuppressWarnings( { "unused", "unchecked" })
	private Collection<Object> getObjectsForBatchUpdate(JoinPoint jp, Collection<Object> updateObjects, BusinessLogForBatchUpdate businessLog) {
		try {
			// 实体对象Class
			Class entityClass = businessLog.entityClass();
			// 实体对象获得主键方法
			Method getPKMethod = entityClass.getMethod(businessLog.getEntityPKMethodName(), new Class[] {});
			Iterator<Object> objectIterator = updateObjects.iterator();
			ArrayList<Object> pkList = new ArrayList<Object>();
			int i = 0;
			while (objectIterator.hasNext()) {
				Object obj = objectIterator.next();
				// 主键
				pkList.add(getPKMethod.invoke(obj, new Object[] {}));
				i++;
			}
			// 目标对象
			Object targetObject = jp.getTarget();
			// 目标对象CLASS
			Class<? extends Object> targetObjectClass = targetObject.getClass();
			// 获得对象集合方法
			Method getObjectsMethod = targetObjectClass.getMethod(businessLog.getObjectsByIDArrayMethodName(), businessLog.pkArrayClass());
			if (businessLog.pkArrayClass().equals(Integer[].class)) {
				return getObjectsByPKArray(targetObject, pkList.toArray(new Integer[] {}), getObjectsMethod);
			} else if (businessLog.pkArrayClass().equals(String[].class)) {
				return getObjectsByPKArray(targetObject, pkList.toArray(new String[] {}), getObjectsMethod);
			} else {
				log4jManager.errorCustomLog(getClass().getName(), "getObjectsForBatchUpdate", "不支持的主键数组类型");
				return null;
			}

		} catch (Exception e) {
			log4jManager.saveExceptionLog(getClass().getName(), "afterThrowing", "异常发生在[" + getClass() + "]类方法[getObjectsForBatchUpdate],异常信息[" + e.getMessage() + "]", e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 处理批量更新数据后置通知
	 * 
	 * @param postUpdateDatas
	 * @param entityClass
	 * @param getPKMethodName
	 * @param operationModule
	 */
	private void processAfterBatchUpdateData(Collection<Object> postUpdateDatas, Class entityClass, String getPKMethodName, String operationModule, Integer operationType) {
		try {
			Iterator<Object> objectIterator = postUpdateDatas.iterator();
			Method getPKMethod = entityClass.getMethod(getPKMethodName, new Class[] {});
			Object postUpdateData = null;
			MaintenanceLog maintenanceLog = null;
			Collection<MaintenanceLog> maintenanceLogs = new ArrayList<MaintenanceLog>();
			while (objectIterator.hasNext()) {
				// 更新后数据
				postUpdateData = objectIterator.next();
				Object pkValue = getPKMethod.invoke(postUpdateData, new Object[] {});
				String preUpdateData = preUpdateDataMap.get().get(pkValue);
				maintenanceLog = buildMaintenanceLog(preUpdateData, postUpdateData.toString());
				maintenanceLog.setOperationModule(operationModule);
				maintenanceLog.setOperationType(operationType);
				maintenanceLogs.add(maintenanceLog);
			}
			maintenanceLogService.batchAddMaintenanceLog(maintenanceLogs);
		} catch (Exception e) {
			log4jManager.saveExceptionLog(getClass().getName(), "afterThrowing", "异常发生在[" + getClass() + "]类方法[processAfterBatchUpdateData],异常信息[" + e.getMessage() + "]", e);
			e.printStackTrace();
		}
	}
}
