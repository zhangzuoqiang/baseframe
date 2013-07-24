package cn.hs.commons.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * Title: SpringBeanUtil<br>
 * Description: Spring获取bean工具<br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Sep 14, 2012
 * @version $Revision:$
 */
public class SpringBeanUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext; // Spring应用上下文环境

	/**
	 * 实现ApplicationContextAware接口的回调方法，设置上下文环境
	 * 
	 * @param applicationContext
	 * @throws BeansException
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringBeanUtil.applicationContext = applicationContext;
	}

	/**
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static boolean containsBean(String arg0) {
		return applicationContext.containsBean(arg0);
	}

	public static String[] getAliases(String arg0) {
		return applicationContext.getAliases(arg0);
	}

	public static <T> T getBean(Class<T> arg0) throws BeansException {
		return applicationContext.getBean(arg0);
	}

	public static <T> T getBean(String arg0, Class<T> arg1) throws BeansException {
		return applicationContext.getBean(arg0, arg1);
	}

	public static Object getBean(String arg0, Object... arg1) throws BeansException {
		return applicationContext.getBean(arg0, arg1);
	}

	public static Object getBean(String arg0) throws BeansException {
		return applicationContext.getBean(arg0);
	}

	public static String[] getBeanNamesForType(Class arg0, boolean arg1, boolean arg2) {
		return applicationContext.getBeanNamesForType(arg0, arg1, arg2);
	}

	public static String[] getBeanNamesForType(Class arg0) {
		return applicationContext.getBeanNamesForType(arg0);
	}

	public ClassLoader getClassLoader() {
		return applicationContext.getClassLoader();
	}
}
