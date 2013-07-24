/*
 * $Log: ResourceDaoImpl.java,v $
 * Revision 1.1  2012/05/23 09:27:53  guor
 * 初次提交
 *
 */
package cn.hs.module.modules.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.hs.core.basedao.base.IBaseDao;
import cn.hs.module.modules.dao.IResourceDao;
import cn.hs.module.modules.dao.require.ResourceRequire;
import cn.hs.module.modules.domain.Resource;
import cn.hs.module.modules.domain.ResourceCondition;

/**
 * Title: ResourceDaoImpl<br>
 * Description: 资源DAO接口实现<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author FanKY
 * @createDate Sep 9, 2011
 * @version $Revision: 61 $
 */
@Repository(value = "cn.hs.module.modules.dao.impl.ResourceDaoImpl")
public class ResourceDaoImpl implements IResourceDao {
	// 初始化baseDao
	@Autowired
	private IBaseDao<cn.hs.module.modules.domain.Resource> baseDao;
	// 初始化Hql拼装类
	@Autowired
	private ResourceRequire resourceRequire;

	/**
	 * 新增菜单资源
	 * 
	 * @param resource
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 13, 2011
	 */
	@Override
	public String addResource(cn.hs.module.modules.domain.Resource resource) throws Exception {
		baseDao.addEntity(resource);
		return "succeed";
	}

	/**
	 * 根据ID集合批量作废或启用资源
	 * 
	 * @param condition
	 * @throws Exception
	 * @author XiangBin
	 * @date Jul 18, 2012
	 */
	@Override
	public void discardOrReuseResource(ResourceCondition condition) throws Exception {
		baseDao.executeUpdate(resourceRequire.discardOrReuseResourceHql(condition));
	}

	/**
	 * 根据ID查询菜单资源信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 13, 2011
	 */
	@Override
	public Resource getResource(ResourceCondition condition) throws Exception {
		return (Resource) baseDao.findObject(Resource.class, condition.getQueryResourceId());
	}

	/**
	 * 更新菜单资源
	 * 
	 * @param resource
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 13, 2011
	 */
	@Override
	public void updateResource(cn.hs.module.modules.domain.Resource resource) throws Exception {
		baseDao.updateEntityByPK(resource);
	}

	/**
	 * 资源菜单集合查询
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @date Jul 18, 2012
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<cn.hs.module.modules.domain.Resource> listResource(ResourceCondition condition) throws Exception {
		resourceRequire.listResourceHql(condition);
		return (List<Resource>) baseDao.pagedQuery(condition);
	}

	/**
	 * 获得分页信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @date Jul 18, 2012
	 */
	@Override
	public Long findCount(ResourceCondition condition) throws Exception {
		resourceRequire.countResourceLongHql(condition);
		return baseDao.countQuery(condition);
	}

	@Override
	public List<cn.hs.module.modules.domain.Resource> listResourceByRole(ResourceCondition condition) throws Exception {
		resourceRequire.listResourceByRoleHql(condition);
		return (List<Resource>) baseDao.pagedQuery(condition);
	}
}
