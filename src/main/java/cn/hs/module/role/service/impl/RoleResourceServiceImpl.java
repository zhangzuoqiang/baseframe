package cn.hs.module.role.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hs.module.role.dao.IRoleResourceDao;
import cn.hs.module.role.domain.RoleResourceCondition;
import cn.hs.module.role.domain.querybena.RoleResourceMenuBean;
import cn.hs.module.role.service.IRoleResourceService;

/**
 * 
 * Title: RoleResourceServiceImpl<br>
 * Description: 角色-资源service实现<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Sep 13, 2011
 * @version $Revision: 62 $
 */
@Service(value = "cn.hs.module.role.service.impl.RoleResourceServiceImpl")
public class RoleResourceServiceImpl implements IRoleResourceService {

	@Autowired
	private IRoleResourceDao roleResourceDao;

	/**
	 * 根据角色ID得到相应的角色-资源
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 13, 2011
	 */
	@Override
	public List<RoleResourceMenuBean> getRoleResourceByRoleId(RoleResourceCondition condition) throws Exception {
		return roleResourceDao.getRoleResourceByRoleId(condition);
	}

}
