/*
 * $Log: IResourceDao.java,v $
 * Revision 1.1  2012/05/23 09:27:53  guor
 * 初次提交
 *
 */
package cn.hs.module.modules.dao;

import java.util.List;

import cn.hs.module.modules.domain.Resource;
import cn.hs.module.modules.domain.ResourceCondition;

/**
 * Title: IResourceDao<br>
 * Description: 资源DAO接口<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author FanKY
 * @createDate Sep 9, 2011
 * @version $Revision: 61 $
 */
public interface IResourceDao {
	/**
	 * 新增资源
	 * 
	 * @param resource
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 13, 2011
	 */
	public String addResource(Resource resource) throws Exception;

	/**
	 * 根据ID查询资源信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 13, 2011
	 */
	public Resource getResource(ResourceCondition condition) throws Exception;

	/**
	 * 更新菜单资源
	 * 
	 * @param resource
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 13, 2011
	 */
	public void updateResource(Resource resource) throws Exception;

	/**
	 * 根据ID集合批量作废或启用资源
	 * 
	 * @param condition
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 13, 2011
	 */
	public void discardOrReuseResource(ResourceCondition condition)
			throws Exception;

	/**
	 * 获取菜单集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 13, 2011
	 */
	public List<Resource> listResource(ResourceCondition condition)
			throws Exception;

	/**
	 * 获得分页信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 14, 2011
	 */
	public Long findCount(ResourceCondition condition) throws Exception;
	
	/**
	 * 根据角色查询资源
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @date Jul 31, 2012
	 */
	public List<Resource> listResourceByRole(ResourceCondition condition)
		throws Exception;
}
