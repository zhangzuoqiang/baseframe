/*
 * $Log: ResourceServiceImpl.java,v $
 * Revision 1.1  2012/05/23 09:27:54  guor
 * 初次提交
 *
 */
package cn.hs.module.modules.service.impl;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.core.page.commond.BasePageCommond;
import cn.hs.core.page.commond.JSONObject;
import cn.hs.core.servicetemplate.pagetemplate.PageTemplate;
import cn.hs.core.springext.security.source.LoadResourceDefineEvent;
import cn.hs.module.modules.dao.IResourceDao;
import cn.hs.module.modules.domain.Resource;
import cn.hs.module.modules.domain.ResourceCondition;
import cn.hs.module.modules.service.IResourceService;

/**
 * Title: ResourceServiceImpl<br>
 * Description: 资源service接口实现类<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author FanKY
 * @createDate Sep 9, 2011
 * @version $Revision: 77 $
 */
@Service(value = "cn.hs.module.modules.service.impl.ResourceServiceImpl")
public class ResourceServiceImpl extends PageTemplate implements IResourceService, ApplicationContextAware {

	@Autowired
	private IResourceDao resourceDao;

	private ApplicationContext applicationContext;

	/**
	 * 根据id添加菜单
	 * 
	 * @param resource
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 9, 2011
	 */
	@Override
	public String addResource(Resource resource) throws Exception {
		String resourceValue = resourceDao.addResource(resource);
		applicationContext.publishEvent(new LoadResourceDefineEvent(this));
		return resourceValue;
	}

	/**
	 * 批量启用或作废菜单
	 * 
	 * @param condition
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 9, 2011
	 */
	@Override
	public void discardOrReuseResource(ResourceCondition condition) throws Exception {
		resourceDao.discardOrReuseResource(condition);
		applicationContext.publishEvent(new LoadResourceDefineEvent(this));
	}

	/**
	 * 根据id查询菜单
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 9, 2011
	 */
	@Override
	public Resource getResource(ResourceCondition condition) throws Exception {
		return resourceDao.getResource(condition);

	}

	/**
	 * 菜单更新
	 * 
	 * @param resource
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 9, 2011
	 */
	@Override
	public void updateResource(Resource resource) throws Exception {
		resourceDao.updateResource(resource);
		applicationContext.publishEvent(new LoadResourceDefineEvent(this));
	}

	/**
	 * 调用PageTemplate模板process方法
	 * 
	 * @param pageCommond
	 * @param condition
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 9, 2011
	 */
	@Override
	public JSONObject doProcess(BasePageCommond pageCommond, BaseCondition condition) throws Exception {
		return process(pageCommond, condition);
	}

	/**
	 * 获取分页信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 13, 2011
	 */
	@Override
	protected Long findCount(BaseCondition condition) throws Exception {
		return resourceDao.findCount((ResourceCondition) condition);
	}

	/**
	 * 获得菜单集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 13, 2011
	 */
	@Override
	public List<Resource> findList(BaseCondition condition) throws Exception {
		return resourceDao.listResource((ResourceCondition) condition);
	}

	@Override
	// 覆盖ApplicationContextAware的方法 by RongLT 2011-10-11
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}

	/**
	 * 根据角色查询资源
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Xiangb
	 * @date Aug 8, 2012
	 */
	@Override
	public List<Resource> findRoleList(ResourceCondition condition) throws Exception {
		return resourceDao.listResourceByRole(condition);
	}

}
