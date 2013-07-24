/*
 * $Log: IResourceTreeService.java,v $
 * Revision 1.1  2012/05/23 09:27:54  guor
 * 初次提交
 *
 */
package cn.hs.module.modules.service;

import java.util.List;

import cn.hs.core.servicetemplate.treetemplate.IBaseTreeTemplate;
import cn.hs.module.modules.domain.ResourceTreeCondition;

/**
 * Title: IResourceTreeService<br>
 * Description: 资源树service接口<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 23, 2011
 * @version $Revision: 61 $
 */
public interface IResourceTreeService extends IBaseTreeTemplate {
	/**
	 * 根据父级ID查询下一级节点集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 14, 2011
	 */
	public List<cn.hs.module.modules.domain.Resource> listNextNodeList(
			ResourceTreeCondition condition) throws Exception;
}
