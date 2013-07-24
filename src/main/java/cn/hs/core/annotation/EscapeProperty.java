/*
 * $Log: EscapeProperty.java,v $
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

/**
 * Title: EscapeProperty<br>
 * Description: 查询通配符转义<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Mar 1, 2012
 * @version $Revision: 1.1 $
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface EscapeProperty {
	/**
	 * 需要转义的对象属性名，如果为多个，需要用“,”分割
	 * 
	 * @return
	 * @author WangWB
	 * @date Mar 1, 2012
	 */
	String escapePropertyNames();
	
}
