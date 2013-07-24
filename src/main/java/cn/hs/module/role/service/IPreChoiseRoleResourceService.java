/*
 * $Log: IPreChoiseRoleResourceService.java,v $
 * Revision 1.1  2012/05/23 09:27:55  guor
 * 初次提交
 *
 */
package cn.hs.module.role.service;

import java.util.List;

import cn.hs.core.servicetemplate.pagetemplate.IBasePageTemplate;
import cn.hs.module.role.domain.RoleResource;
import cn.hs.module.role.domain.RoleResourceCondition;

/**
 * Title: IPreChoiseRoleResourceService<br>
 * Description: 角色资源待选列表service接口<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 23, 2011
 * @version $Revision: 62 $
 */
public interface IPreChoiseRoleResourceService extends IBasePageTemplate {

	/**
	 * 选择角色资源
	 * 
	 * @param condition
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 23, 2011
	 */
	public void choiseRoleResource(RoleResourceCondition condition)
			throws Exception;

	/**
	 * 得到中间表中资源集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author YaoSC
	 * @date Oct 31, 2011
	 */
	public List<RoleResource> getResource(RoleResourceCondition condition)
			throws Exception;;

}
