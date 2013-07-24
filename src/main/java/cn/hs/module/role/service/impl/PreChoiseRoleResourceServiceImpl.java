package cn.hs.module.role.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.core.page.commond.BasePageCommond;
import cn.hs.core.page.commond.JSONObject;
import cn.hs.core.servicetemplate.pagetemplate.PageTemplate;
import cn.hs.module.role.dao.IPreChoiseRoleResourceDao;
import cn.hs.module.role.domain.RoleResource;
import cn.hs.module.role.domain.RoleResourceCondition;
import cn.hs.module.role.service.IPreChoiseRoleResourceService;

/**
 * 角色service实现 Title: RoleServiceImpl<br>
 * Description: <br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Sep 5, 2011
 * @version $Revision: 74 $
 */
@Service(value = "cn.hs.module.role.service.impl.PreChoiseRoleResourceServiceImpl")
public class PreChoiseRoleResourceServiceImpl extends PageTemplate implements IPreChoiseRoleResourceService {

	@Autowired
	private IPreChoiseRoleResourceDao preChoiseRoleResourceDao;

	/**
	 * 调用模板process方法
	 * 
	 * @param pageCommond
	 * @param condition
	 * @throws Exception
	 * @author YaoSC
	 * @date Sep 5, 2011
	 */
	public JSONObject doProcess(BasePageCommond pageCommond, BaseCondition condition) throws Exception {
		return process(pageCommond, condition);
	}

	/**
	 * 实现查询count
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author YaoSC
	 * @date Sep 5, 2011
	 */
	@Override
	protected Long findCount(BaseCondition condition) throws Exception {
		return preChoiseRoleResourceDao.countAuthorityRole((RoleResourceCondition) condition);
	}

	@Override
	protected List<RoleResource> findList(BaseCondition condition) throws Exception {
		return preChoiseRoleResourceDao.listAuthorityRole((RoleResourceCondition) condition);
	}

	@Override
	public void choiseRoleResource(RoleResourceCondition condition) throws Exception {
		preChoiseRoleResourceDao.addAuthorityRoleResource(condition);
	}

	/**
	 * 得到中间表中资源集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author YaoSC
	 * @date Oct 31, 2011
	 */
	@Override
	public List<RoleResource> getResource(RoleResourceCondition condition) throws Exception {
		return preChoiseRoleResourceDao.getResource((RoleResourceCondition) condition);
	}

}
