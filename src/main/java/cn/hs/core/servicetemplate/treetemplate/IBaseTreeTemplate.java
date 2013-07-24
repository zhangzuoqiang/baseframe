/*
 * $Log: IBaseTreeTemplate.java,v $
 * Revision 1.1  2012/05/23 09:27:49  guor
 * 初次提交
 *
 */
package cn.hs.core.servicetemplate.treetemplate;

import java.util.List;

import cn.hs.core.basedao.condition.BaseTreeCondition;
import cn.hs.core.servicetemplate.treetemplate.jsonbean.JsonTreeBean;

/**
 * Title: IBaseTreeTemplate<br>
 * Description: 调用TreeTemplate模板接口，需要使用该模板的service接口需要继承本接口<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 12, 2011
 * @version $Revision: 1.1 $
 */
public interface IBaseTreeTemplate {
	/**
	 * 调用TreeTemplate模板process方法
	 * 
	 * @param condition
	 * @param model
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 12, 2011
	 */
	public List<JsonTreeBean> doProcess(BaseTreeCondition condition)
			throws Exception;
}
