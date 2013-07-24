/*
 * $Log: ResourceTreeDaoImpl.java,v $
 * Revision 1.1  2012/05/23 09:27:53  guor
 * 初次提交
 *
 */
package cn.hs.module.modules.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.hs.core.basedao.base.IBaseDao;
import cn.hs.module.modules.dao.IResourceTreeDao;
import cn.hs.module.modules.dao.require.ResourceRequire;
import cn.hs.module.modules.domain.Resource;
import cn.hs.module.modules.domain.ResourceTreeCondition;

/**
 * Title: IResourceTreeDaoImpl<br>
 * Description: 资源Tree DAO接口实现<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author FanKY
 * @createDate Sep 9, 2011
 * @version $Revision: 80 $
 */
@Repository(value = "cn.hs.module.modules.dao.impl.IResourceTreeDaoImpl")
public class ResourceTreeDaoImpl implements IResourceTreeDao {
	// 实例化baseDao
	@Autowired
	private IBaseDao<Resource> baseDao;
	// 实例化resourceRequire
	@Autowired
	private ResourceRequire resourceRequire;

	/**
	 * 查询资源数据
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @date Sep 18, 2012
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> listResource(ResourceTreeCondition condition) throws Exception {
		resourceRequire.listResourceTreeHql(condition);
		List<Resource> list = (List<Resource>) baseDao.find(condition.getSql(), condition.getParameterList().toArray());
		return list;
	}

	/**
	 * 是否为子节点
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @date Sep 13, 2011
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean isLeapNode(ResourceTreeCondition condition) throws Exception {
		boolean result = true;
		resourceRequire.isLeapNode(condition);
		long countQuery = baseDao.countQuery(condition);
		if (countQuery > 0) {
			result = false;
		}
		return result;
	}

	/**
	 * 根据父级ID查询下一级节点集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 22, 2011
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> listNextNodeList(ResourceTreeCondition condition) throws Exception {
		resourceRequire.listResourceTreeOtherHql(condition);
		return (List<Resource>) baseDao.find(condition.getSql(), condition.getParameterList().toArray());
	}
}
