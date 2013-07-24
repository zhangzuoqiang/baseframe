/*
 * $Log: CreateInfo.java,v $
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
 * Title: CreateInfo<br>
 * Description: 生成创建人信息注解<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Nov 21, 2011
 * @version $Revision: 1.1 $
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface CreateInfo {

	/**
	 * bo中设置创建时间的set方法
	 * 
	 * @return
	 * @author WangWB
	 * @date Nov 21, 2011
	 */
	String createTimeMethod();

	/**
	 * bo中设置创建者的set方法
	 * 
	 * @return
	 * @author WangWB
	 * @date Nov 21, 2011
	 */
	String creatorMethod();
}
