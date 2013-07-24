/*
 * $Log: DndTreeConditionParameterInterceptor.java,v $
 * Revision 1.1  2012/05/23 09:27:47  guor
 * 初次提交
 *
 */
package cn.hs.core.aop.dndtree;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hs.commons.utils.RequestContextUtil;
import cn.hs.core.annotation.DndTree;
import cn.hs.core.basedao.condition.BaseTreeCondition;
import cn.hs.core.log4j.ILog4jManager;

/**
 * Title: CreateInfoInterceptor<br>
 * Description: 拖拽树参数封装<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Nov 21, 2011
 * @version $Revision: 1.1 $
 */
@Aspect
@Component
public class DndTreeConditionParameterInterceptor {

	// 初始化日志接口
	@Autowired
	private ILog4jManager log4jManager;

	/**
	 * 声明切入点
	 * 
	 * @author WangWB
	 * @date Nov 21, 2011
	 */
	@Pointcut("@annotation(cn.hs.core.annotation.DndTree)")
	public void anyMethod() {
	}

	/**
	 * 进入方法之前，从request中获取拖拽树相关参数
	 * 
	 * @author WangWB
	 * @date Nov 21, 2011
	 */
	@Before("anyMethod() && @annotation(dndTree) && args(operatingObject,..)")
	public void doBefore(JoinPoint jp, DndTree dndTree, Object operatingObject) {
		try {
			HttpServletRequest request = RequestContextUtil.getRequest();
			BaseTreeCondition condition = (BaseTreeCondition) operatingObject;
			condition.setTreeNodeID(request.getParameter("nodeID"));
			condition.setParentNodeID(request.getParameter("parentNodeID"));
			condition.setChildNodeIds(request.getParameter("childNodeIds"));
		} catch (Exception e) {
			e.printStackTrace();
			log4jManager.saveExceptionLog(getClass().getName(), "doBefore", "从request中获取拖拽树相关参数异常", e);
		}
	}
}
