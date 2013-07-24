/*
 * $Log: IDndTreeTemplate.java,v $
 * Revision 1.1  2012/05/23 09:27:49  guor
 * 初次提交
 *
 */
package cn.hs.core.servicetemplate.treetemplate;

import cn.hs.core.basedao.condition.BaseTreeCondition;

/**
 * Title: IBaseTreeTemplate<br>
 * Description: 调用TreeTemplate模板接口，需要使用该模板的service接口需要继承本接口<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 12, 2011
 * @version $Revision: 1.1 $
 */
public interface IDndTreeTemplate extends IBaseTreeTemplate {
	/**
	 * 调用DndTreeTemplate模板dndProcess方法
	 * 
	 * @param condition
	 * @param model
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 12, 2011
	 */
	public void doDndProcess(BaseTreeCondition condition) throws Exception;
}
