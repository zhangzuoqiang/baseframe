package cn.hs.module.role.dao.require;

import org.springframework.stereotype.Repository;

import cn.hs.module.modules.domain.Resource;
import cn.hs.module.role.domain.RoleResourceCondition;

/**
 * Title: RoleResourceRequire<br>
 * Description: 角色资源HQL封装 <br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Sep 13, 2011
 * @version $Revision: 62 $
 */
@Repository(value = "cn.hs.module.role.dao.require.RoleResourceRequire")
public class RoleResourceRequire {

	/**
	 * 根据角色ID查询角色-资源信息HQL
	 * 
	 * @param condition
	 * @return
	 * @author XiangBin
	 * @date Sep 23, 2012
	 */
	public RoleResourceCondition queryRoleResourceByRoleIDHQL(RoleResourceCondition condition) {
		StringBuffer result = new StringBuffer();
		result.append("select r.roleID as roleID,r.roleCode as roleCode,r.activeState as activeState,");
		result.append("  rs.resourceID as resourceID,rs.url as url,rs.resourceName as resourceName,rs.parentID as parentID");
		result.append(" from RoleResource t,Role r,Resource rs where r.roleID=t.role.roleID");
		result.append(" and t.resource.resourceID=rs.resourceID");
		result.append(" and rs.activeState= ?");
		condition.addParameter(Resource.IS_ACTIVE_Y);
		if (condition.getQueryRoleID() != null && !"".equals(condition.getQueryRoleID())) {
			result.append(" and t.role.roleID= ?");
			condition.addParameter(condition.getQueryRoleID());
		}
		if (condition.getQueryRoleCode() != null && !"".equals(condition.getQueryRoleCode())) {
			result.append(" and t.role.roleCode =?");
			condition.addParameter(condition.getQueryRoleCode());
		}
		result.append(" order by rs.orderNum,rs.resourceID");
		condition.setSql(result.toString());
		return condition;
	}
}
