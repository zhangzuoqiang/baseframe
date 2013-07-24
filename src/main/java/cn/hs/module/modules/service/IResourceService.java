/*
 * $Log: IResourceService.java,v $
 * Revision 1.1  2012/05/23 09:27:54  guor
 * 初次提交
 *
 */
package cn.hs.module.modules.service;

import java.util.List;

import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.core.servicetemplate.pagetemplate.IBasePageTemplate;
import cn.hs.module.modules.domain.Resource;
import cn.hs.module.modules.domain.ResourceCondition;

/**
 * Title: IResourceService<br>
 * Description: 资源service接口<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 23, 2011
 * @version $Revision: 61 $
 */
public interface IResourceService extends IBasePageTemplate {
	/**
	 * 新增资源
	 * 
	 * @param resource
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 23, 2011
	 */
	public String addResource(Resource resource) throws Exception;

	/**
	 * 根据ID查询资源信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 23, 2011
	 */
	public Resource getResource(ResourceCondition condition) throws Exception;

	/**
	 * 更新资源
	 * 
	 * @param resource
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 23, 2011
	 */
	public void updateResource(Resource resource) throws Exception;

	/**
	 * 根据ID集合批量作废或启用资源
	 * 
	 * @param condition
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 23, 2011
	 */
	public void discardOrReuseResource(ResourceCondition condition) throws Exception;

	/**
	 * 获得菜单集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author RongLT
	 * @date 2011-10-10
	 */
	public List<Resource> findList(BaseCondition condition) throws Exception;

	/**
	 * 根据角色查询资源
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @date Jul 31, 2012
	 */
	public List<Resource> findRoleList(ResourceCondition condition) throws Exception;
}
