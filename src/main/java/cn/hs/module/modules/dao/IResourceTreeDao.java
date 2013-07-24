package cn.hs.module.modules.dao;

import java.util.List;

import cn.hs.module.modules.domain.Resource;
import cn.hs.module.modules.domain.ResourceTreeCondition;

/**
 * 
 * Title: IResourceTreeDao<br>
 * Description: 资源Tree DAO接口<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author FanKY
 * @createDate Sep 9, 2011
 * @version $Revision: 61 $
 */
public interface IResourceTreeDao {
	/**
	 * 查询菜单集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 13, 2011
	 */
	public List<cn.hs.module.modules.domain.Resource> listResource(
			ResourceTreeCondition condition) throws Exception;

	/**
	 * 查询是否为子节点
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 13, 2011
	 */
	public boolean isLeapNode(ResourceTreeCondition condition) throws Exception;

	/**
	 * 根据父级ID查询下一级节点集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 22, 2011
	 */
	public List<Resource> listNextNodeList(ResourceTreeCondition condition)
			throws Exception;
}
