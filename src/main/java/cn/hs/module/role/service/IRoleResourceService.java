package cn.hs.module.role.service;

import java.util.List;

import cn.hs.module.role.domain.RoleResourceCondition;
import cn.hs.module.role.domain.querybena.RoleResourceMenuBean;

/**
 * 
 * Title: IRoleResourceService<br>
 * Description: 角色-资源service接口<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Sep 13, 2011
 * @version $Revision: 62 $
 */
public interface IRoleResourceService {

	/**
	 * 根据角色ID得到相应的角色-资源
	 * 
	 * @param role
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 13, 2011
	 */
	public List<RoleResourceMenuBean> getRoleResourceByRoleId(RoleResourceCondition condition) throws Exception;
}
