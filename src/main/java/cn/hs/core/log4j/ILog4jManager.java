/*
 * $Log: ILog4jManager.java,v $
 * Revision 1.1  2012/05/23 09:27:48  guor
 * 初次提交
 *
 */
package cn.hs.core.log4j;

/**
 * Title: ILog4jManager<br>
 * Description: Log4j日志接口<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 1, 2011
 * @version $Revision: 1.1 $
 */
public interface ILog4jManager {
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
	public void saveExceptionLog(String className, String methodName,
			String logStr, Exception e);

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
	public void errorCustomLog(String className, String methodName, String logStr);

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
	public void debugCustomLog(String className, String methodName,
			String logStr);

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
	public void infoCustomLog(String className, String methodName, String logStr);

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
	public void warnCustomLog(String className, String methodName, String logStr);
}
