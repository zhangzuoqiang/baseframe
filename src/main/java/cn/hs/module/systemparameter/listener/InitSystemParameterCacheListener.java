/*
 * $Log: InitSystemParameterCacheListener.java,v $
 * Revision 1.2  2012/05/25 06:23:09  ronglt
 * 在配置文件中配置系统参数缓存管理
 *
 * Revision 1.1  2012/05/25 06:07:21  ronglt
 * 增加系统参数缓存
 *
 * Revision 1.1  2012/04/13 08:41:50  guor
 * *** empty log message ***
 *
 */
package cn.hs.module.systemparameter.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.hs.core.log4j.ILog4jManager;
import cn.hs.module.systemparameter.service.ISystemParameterCacheService;

/**
 * Title: InitSystemParameterCacheListener<br>
 * Description: <br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author MengW
 * @createDate 2011-10-12
 * @version $Revision: 11 $
 */
public class InitSystemParameterCacheListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * 初始化系统参数缓存
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext servletContext = arg0.getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		ISystemParameterCacheService systemParameterCacheService = (ISystemParameterCacheService) wac.getBean("cn.hs.module.systemparameter.service.impl.SystemParameterCacheServiceImpl");
		ILog4jManager log4jManager = (ILog4jManager) wac.getBean("cn.hs.core.log4j.impl.Log4jManager");
		try {
			systemParameterCacheService.synchronizeCacheByDataBase();
		} catch (Exception e) {
			log4jManager.saveExceptionLog(getClass().getName(), "afterThrowing", "异常发生在[" + getClass() + "]类方法[contextInitialized],异常信息[" + e.getMessage() + "]", e);
			e.printStackTrace();
		}
	}

}
