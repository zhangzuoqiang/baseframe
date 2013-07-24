/*
 * $Log: IChoisedRoleResourceService.java,v $
 * Revision 1.1  2012/05/23 09:27:55  guor
 * 初次提交
 *
 */
package cn.hs.module.role.service;

import cn.hs.core.servicetemplate.pagetemplate.IBasePageTemplate;
import cn.hs.module.role.domain.RoleResourceCondition;

/**
 * Title: IChoisedRoleResourceService<br>
 * Description: 角色资源已选列表service接口<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 23, 2011
 * @version $Revision: 62 $
 */
public interface IChoisedRoleResourceService extends IBasePageTemplate {

	/**
	 * 删除角色资源
	 * 
	 * @param condition
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 23, 2011
	 */
	public void deleteRoleResource(RoleResourceCondition condition)
			throws Exception;
}
