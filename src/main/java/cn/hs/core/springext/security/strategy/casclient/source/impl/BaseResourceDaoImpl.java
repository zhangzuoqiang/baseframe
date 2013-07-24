package cn.hs.core.springext.security.strategy.casclient.source.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import cn.hs.core.springext.security.source.BaseResource;
import cn.hs.core.springext.security.source.IBaseResourceDao;
import cn.hs.core.springext.security.strategy.casclient.bo.BaseResourceImpl;
import cn.hs.module.modules.domain.ResourceCondition;
import cn.hs.module.modules.service.IResourceService;

/**
 * 
 * Title: BaseResourceDaoImpl<br>
 * Description: 将数据库中所有资源的ID和URL封装成BaseResourceImpl类型的List<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author RongLT
 * @createDate 2011-10-11
 * @version $Revision: 1.1 $
 */
public class BaseResourceDaoImpl implements IBaseResourceDao {
	@Autowired
	private IResourceService resourceServiceImpl;

	@SuppressWarnings("unchecked")
	@Override
	public List<BaseResource> listSecurityResource() throws Exception {
		ResourceCondition condition = new ResourceCondition();
		condition.setSearchActiveState(BaseResourceImpl.getIsActiveState());
		condition.setStart(BaseResourceImpl.getDisableRowsStart());
		condition.setRows(BaseResourceImpl.getDisableRowsStart());
		List<cn.hs.module.modules.domain.Resource> resourceList = (List<cn.hs.module.modules.domain.Resource>) resourceServiceImpl.findList(condition);
		List<BaseResource> resourceImplList = new ArrayList<BaseResource>();
		if (resourceList.size() > 0) {
			Iterator<cn.hs.module.modules.domain.Resource> ite = resourceList.iterator();
			cn.hs.module.modules.domain.Resource resource = new cn.hs.module.modules.domain.Resource();
			BaseResourceImpl baseResource = null;
			String url = null;
			while (ite.hasNext()) {
				resource = (cn.hs.module.modules.domain.Resource) ite.next();
				url = resource.getUrl();
				if (url != null && !"".equals(url)) {
					if (url.startsWith("./")) {
						url = url.substring(1, url.length());
					}
				}
				baseResource = new BaseResourceImpl();
				baseResource.setIdentity(resource.getResourceID().toString());
				baseResource.setUrl(url);
				resourceImplList.add(baseResource);
			}
		}
		return resourceImplList;
	}

}
