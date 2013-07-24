package cn.hs.module.basedata.ui.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.hs.core.log4j.impl.Log4jManager;
import cn.hs.module.basedata.service.impl.BaseDataUtil;

/**
 * Title: BaseDataInitServlet<br>
 * Description:数据字典的初始化加载 <br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author GuoR
 * @createDate 2012-8-23
 * @version $Revision:$
 */
public class BaseDataInitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		// SpringBeanUtil.setAc(ac);
		// modify by RongLT 2011-10-17
		// 去掉init()方法的参数,从服务器缓存HashMap中取Map使用
		BaseDataUtil baseDataUtil = new BaseDataUtil(ac, this.getServletContext());
		// baseDataUtil.init();
		// modify by RongLT 2011-12-13
		// 将数据字典中的资源类型编码与它对应的数据集合存进缓存中
		Log4jManager log4jManager = (Log4jManager) ac.getBean("cn.hs.core.log4j.impl.Log4jManager");
		try {
			baseDataUtil.initBaseDataListMap();// 将数据字典中的资源类型编码与它对应的数据集合存进缓存中
		} catch (Exception e) {
			log4jManager.saveExceptionLog(this.getClass().getName(), "init()", "将数据字典中的资源类型编码与它对应的数据集合存进缓存出现异常", e);
		}
	}
}
