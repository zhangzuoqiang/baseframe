/**
 * 
 */
package cn.hs.core.log4j.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.hs.core.log4j.ILog4jManager;
import cn.hs.core.log4j.Log4jLoggerFactory;

/**
 * Title: Log4jManager<br>
 * Description: Log4j日志接口实现类<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 1, 2011
 * @version $Revision: 1.1 $
 */
@Component(value = "cn.hs.core.log4j.impl.Log4jManager")
public class Log4jManager implements ILog4jManager {
	private static Logger defaultLog;

	/**
	 * 记录异常日志
	 * 
	 * @param className
	 *            记录日志所处类路径
	 * @param methodName
	 *            记录日志所处方法名
	 * @param logStr
	 *            自定义异常信息
	 * @param e
	 *            异常信息
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 1, 2011
	 */
	public void saveExceptionLog(String className, String methodName, String logStr, Exception e) {
		defaultLog = Log4jLoggerFactory.getInstance().getLogger();
		defaultLog.error("class [" + className + "] method [" + methodName + "] error -" + logStr, e);
	}

	/**
	 * 记录自定义日志
	 * 
	 * @param className
	 *            记录日志所处类路径
	 * @param methodName
	 *            记录日志所处方法名
	 * @param logStr
	 *            自定义异常信息
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 1, 2011
	 */
	public void errorCustomLog(String className, String methodName, String logStr) {
		defaultLog = Log4jLoggerFactory.getInstance().getLogger();
		defaultLog.error("class [" + className + "] method [" + methodName + "] info -" + logStr);
	}

	/**
	 * 记录自定义调试日志 debug级别
	 * 
	 * @param className
	 *            记录日志所处类路径
	 * @param methodName
	 *            记录日志所处方法名
	 * @param logStr
	 *            自定义异常信息
	 * @throws Exception
	 * @author GuoR
	 * @date 2011-09-20
	 */
	public void debugCustomLog(String className, String methodName, String logStr) {
		defaultLog = Log4jLoggerFactory.getInstance().getLogger();
		defaultLog.debug("class [" + className + "] method [" + methodName + "] debug -" + logStr);
	}

	/**
	 * 记录自定义info日志 info级别
	 * 
	 * @param className
	 *            记录日志所处类路径
	 * @param methodName
	 *            记录日志所处方法名
	 * @param logStr
	 *            自定义异常信息
	 * @author WangWB
	 * @date Oct 28, 2011
	 */
	@Override
	public void infoCustomLog(String className, String methodName, String logStr) {
		defaultLog = Log4jLoggerFactory.getInstance().getLogger();
		defaultLog.info("class [" + className + "] method [" + methodName + "] info -" + logStr);
	}

	/**
	 * 记录自定义warn日志 warn级别
	 * 
	 * @param className
	 *            记录日志所处类路径
	 * @param methodName
	 *            记录日志所处方法名
	 * @param logStr
	 *            自定义异常信息
	 * @author WangWB
	 * @date Oct 28, 2011
	 */
	@Override
	public void warnCustomLog(String className, String methodName, String logStr) {
		defaultLog = Log4jLoggerFactory.getInstance().getLogger();
		defaultLog.warn("class [" + className + "] method [" + methodName + "] warn -" + logStr);
	}

}
