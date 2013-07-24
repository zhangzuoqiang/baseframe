/*
 * $Log: ChoisedRoleResourceServiceImpl.java,v $
 * Revision 1.1  2012/05/23 09:27:56  guor
 * 初次提交
 *
 */
package cn.hs.module.role.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.core.page.commond.BasePageCommond;
import cn.hs.core.page.commond.JSONObject;
import cn.hs.module.role.dao.IChoisedRoleResourceDao;
import cn.hs.module.role.domain.RoleResourceCondition;
import cn.hs.module.role.service.IChoisedRoleResourceService;

/**
 * Title: ChoisedRoleResourceServiceImpl<br>
 * Description: <br>
 * Company: GOLDGOV<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author YaoSC
 * @createDate Sep 16, 2011
 * @version $Revision: 74 $
 */
@Service(value = "cn.hs.module.role.service.impl.ChoisedRoleResourceServiceImpl")
public class ChoisedRoleResourceServiceImpl implements IChoisedRoleResourceService {

	@Autowired
	private IChoisedRoleResourceDao choisedRoleResourceDao;

	/**
	 * 批量删除资源
	 * 
	 * @param condition
	 * @throws Exception
	 * @author YaoSC
	 * @date Sep 16, 2011
	 */
	@Override
	public void deleteRoleResource(RoleResourceCondition condition) throws Exception {
		choisedRoleResourceDao.delAllroleResource(condition);
	}

	/**
	 * 调用doProcess模板
	 * 
	 * @param pageCommond
	 * @param condition
	 * @throws Exception
	 * @author YaoSC
	 * @date Sep 16, 2011
	 */
	@Override
	public JSONObject doProcess(BasePageCommond pageCommond, BaseCondition condition) throws Exception {
		return null;
	}

}
