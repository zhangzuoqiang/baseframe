/*
 * $Log: IBaseWordTemplate.java,v $
 * Revision 1.1  2012/05/23 09:27:49  guor
 * 初次提交
 *
 */
package cn.hs.core.servicetemplate.wordtemplate;

import cn.hs.core.basedao.condition.BaseWordCondition;

/**
 * Title: IBaseWordTemplate<br>
 * Description: 调用WordTemplate模板接口，需要使用该模板的service接口需要继承本接口<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author Liy
 * @createDate 2011-12-7
 * @version $Revision: 1.1 $
 */
public interface IBaseWordTemplate {

	public void doProcess(BaseWordCondition condition) throws Exception;
}
