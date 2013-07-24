/*
 * $Log: RecordExceptionAdvice.java,v $
 * Revision 1.1  2012/05/23 09:27:49  guor
 * 初次提交
 *
 * Revision 1.1  2011/06/01 05:46:54  kkaiwen
 * 2011.6.1 create
 *
 */
package cn.hs.core.springext.advice;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;

import cn.hs.core.log4j.ILog4jManager;

/**
 * Title: RecordExceptionAdvice<br>
 * Description: 在applicationConetxt.xml中定义了该通知，当配置切入点抛出 异常，该类afterThrowing方法将被调用
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author MengW
 * @createDate 2011-5-16
 * @version $Revision: 1.1 $
 */
public class RecordExceptionAdvice implements ThrowsAdvice {

	// 初始化日志接口
	@Autowired
	private ILog4jManager log4jManager;

	/**
	 * 拦截的方法抛出异常后日志记录
	 * 
	 * @param method
	 * @param args
	 * @param target
	 * @param subclass
	 * @throws Throwable
	 */
	public void afterThrowing(Method method, Object[] args, Object target, Exception e) throws Exception {
		log4jManager.saveExceptionLog(getClass().getName(), "afterThrowing", "异常发生在[" + method.getDeclaringClass() + "]类方法[" + method.getName() + "],异常信息[" + e.getMessage() + "]", e);
	}
}
