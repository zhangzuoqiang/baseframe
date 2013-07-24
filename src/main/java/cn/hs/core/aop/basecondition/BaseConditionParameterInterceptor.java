/*
 *$Log:$
 */
package cn.hs.core.aop.basecondition;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import cn.hs.core.basedao.condition.BaseCondition;

/**
 * Title: BaseConditionParameterInterceptor<br>
 * Description: BaseDao拦截器，每次数据操作后都清空condition中的parameterList<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * 
 * @author GuoR
 * @createDate 2012-7-15
 * @version $Revision:$
 */
@Aspect
@Component
public class BaseConditionParameterInterceptor  {

	@AfterReturning("execution(* cn.hs.core.basedao.base.impl.*.*(..))")
	public void afterReturning(JoinPoint jp) throws Throwable {
		Object[] args = jp.getArgs();
		if (args.length > 0) {
			for (Object argObject : args) {
				if (argObject instanceof BaseCondition) {
					((BaseCondition) argObject).setParameterList(null);
				}
			}
		}
	}
}
