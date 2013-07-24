/*
 * $Log: IBasePageTemplate.java,v $
 * Revision 1.1  2012/05/23 09:27:48  guor
 * 初次提交
 *
 */
package cn.hs.core.servicetemplate.pagetemplate;

import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.core.page.commond.BasePageCommond;
import cn.hs.core.page.commond.JSONObject;

/**
 * Title: IBasePageTemplate<br>
 * Description: 调用PageTemplate模板接口，需要使用该模板的service接口需要继承本接口<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 3, 2011
 * @version $Revision: 1.1 $
 */
public interface IBasePageTemplate {
	/**
	 * 调用PageTemplate模板process方法
	 * 
	 * @param pageCommond
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 3, 2011
	 */
	public JSONObject doProcess(BasePageCommond pageCommond, BaseCondition condition)
			throws Exception;
}
