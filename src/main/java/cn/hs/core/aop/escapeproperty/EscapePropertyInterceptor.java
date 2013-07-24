/*
 * $Log: EscapePropertyInterceptor.java,v $
 * Revision 1.1  2012/05/23 09:27:47  guor
 * 初次提交
 *
 */
package cn.hs.core.aop.escapeproperty;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hs.core.annotation.EscapeProperty;
import cn.hs.core.log4j.ILog4jManager;

/**
 * Title: CreateInfoInterceptor<br>
 * Description: 查询通配符转义<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Nov 21, 2011
 * @version $Revision: 6 $
 */
@Aspect
@Component
public class EscapePropertyInterceptor {

	// 初始化日志接口
	@Autowired
	private ILog4jManager log4jManager;

	/**
	 * 声明切入点
	 * 
	 * @author WangWB
	 * @date Nov 21, 2011
	 */
	@Pointcut("@annotation(cn.hs.core.annotation.EscapeProperty)")
	public void anyMethod() {
	}

	/**
	 * 进入方法之前，将字符“_”和“%”前加入转义字符“/”
	 * 
	 * @author WangWB
	 * @date Nov 21, 2011
	 */
	@Before("anyMethod() && @annotation(escapeProperty) && args(operatingObject,..)")
	public void doBefore(JoinPoint jp, EscapeProperty escapeProperty,
			Object operatingObject) {
		try {
			Class<?> operatingClass = operatingObject.getClass();
			String escapePropertyNames = escapeProperty.escapePropertyNames();
			if (escapePropertyNames.indexOf(",") != -1) {
				String[] methodNames = escapePropertyNames.split(",");
				for (int i = 0; i < methodNames.length; i++) {
					setEscapeChar(methodNames[i], operatingClass,
							operatingObject);
				}
			} else {
				setEscapeChar(escapePropertyNames, operatingClass,
						operatingObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log4jManager.saveExceptionLog(getClass().getName(), "doBefore",
					"加入转义字符异常", e);
		}
	}

	/**
	 * 设置添加转义字符“/”后的字符串
	 * 
	 * @param escapePropertyName
	 * @param operatingClass
	 * @param operatingObject
	 * @author WangWB
	 * @date Mar 2, 2012
	 */
	private void setEscapeChar(String escapePropertyName,
			Class<?> operatingClass, Object operatingObject) throws Exception {
		String escapeGetMethodName = "get"
				+ escapePropertyName.substring(0, 1).toUpperCase()
				+ escapePropertyName.substring(1, escapePropertyName.length());
		String escapeSetMethodName = "set"
				+ escapePropertyName.substring(0, 1).toUpperCase()
				+ escapePropertyName.substring(1, escapePropertyName.length());
		// 获得需要转义的属性内容
		Method escapeGetMethod = operatingClass.getMethod(escapeGetMethodName,
				new Class[] {});
		String escapeContent = (String) escapeGetMethod.invoke(operatingObject);
		if (escapeContent != null && !"".equals(operatingObject)) {
			Method escapeSetMethod = operatingClass.getMethod(
					escapeSetMethodName, String.class);
			// 设置添加转义字符“/”后的字符串
			escapeSetMethod.invoke(operatingObject,
					addEscapeChar(escapeContent));
		}
	}

	/**
	 * 在指定带有转义字符的字符串中加入“/”
	 * 
	 * @param preEscapeStr
	 * @return
	 * @author WangWB
	 * @date Mar 2, 2012
	 */
	private String addEscapeChar(String preEscapeStr) {
		String result = "";
		result = preEscapeStr.replaceAll("%", "/%").replaceAll("_", "/_");
		return result;
	}
}
