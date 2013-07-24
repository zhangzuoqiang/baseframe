package cn.hs.module.role.dao.require;

import org.springframework.stereotype.Repository;

import cn.hs.core.page.commond.SortInfoBean;
import cn.hs.module.role.domain.RoleCondition;

/**
 * Title: RoleRequire<br>
 * Description: 角色HQL封装 <br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Sep 5, 2011
 * @version $Revision: 62 $
 */
@Repository(value = "cn.hs.module.role.dao.require.RoleRequire")
public class RoleRequire {
	/**
	 * 查询角色countHQL
	 * 
	 * @param condition
	 * @return
	 * @author XiangBin
	 * @date Sep 23, 2012
	 */
	public RoleCondition countRoleHQL(RoleCondition condition) {
		StringBuffer result = new StringBuffer();
		result.append("select count(*) from Role r left join r.roleType b with b.activeState=1");
		result.append(" where 1=1");
		if (condition.getSearchRoleName() != null && !"".equals(condition.getSearchRoleName())) {
			result.append(" and r.roleName like ?");
			condition.addParameter("%" + condition.getSearchRoleName() + "%");
		}
		if (condition.getSearchRoleType() != null && !"".equals(condition.getSearchRoleType())) {
			result.append(" and r.roleType.dataCode= ?");
			condition.addParameter(condition.getSearchRoleType());
		}
		if (condition.getSearchActiveState() != null && condition.getSearchActiveState() != 0) {
			result.append(" and r.activeState= ?");
			condition.addParameter(condition.getSearchActiveState());
		}
		condition.setSql(result.toString());
		return condition;
	}

	/**
	 * 查询角色list
	 * 
	 * @param condition
	 * @return
	 * @author XiangBin
	 * @date Jul 23, 2012
	 */
	public RoleCondition listRoleHQL(RoleCondition condition) {
		StringBuffer sql = new StringBuffer("" + "" + "" + "" + "");
		sql.append("select r.roleID as roleID,r.roleCode as roleCode,r.roleName as roleName,");
		sql.append(" r.description as description,r.activeState as activeState,r.orderNum as orderNum,b.dataCode as dataCode,");
		sql.append(" b.dataName as dataName");
		sql.append("	 from Role r left join r.roleType b with b.activeState = 1");
		sql.append("   where 1=1");
		if (condition.getSearchRoleName() != null && !"".equals(condition.getSearchRoleName())) {
			sql.append(" and r.roleName like ?");
			condition.addParameter("%" + condition.getSearchRoleName() + "%");
		}
		if (condition.getSearchRoleType() != null && !"".equals(condition.getSearchRoleType())) {
			sql.append(" and r.roleType.dataCode = ?");
			condition.addParameter(condition.getSearchRoleType());
		}
		if (condition.getSearchActiveState() != null && condition.getSearchActiveState() != 0) {
			sql.append(" and r.activeState = ?");
			condition.addParameter(condition.getSearchActiveState());
		}
		SortInfoBean sortInfo = condition.getSortInfo();
		if (sortInfo != null) {
			sql.append(" ORDER BY " + sortInfo.getProperty() + " " + sortInfo.getDirection());
		} else {
			sql.append(" order by r.orderNum,r.roleID");
		}
		condition.setSql(sql.toString());
		return condition;
	}

	/**
	 * 根绝角色编码查询角色信息HQL
	 * 
	 * @param condition
	 * @return
	 * @author XiangBin
	 * @date Sep 23, 2012
	 */
	public RoleCondition queryRoleByRoleCodeHQL(RoleCondition condition) {
		StringBuffer result = new StringBuffer("select r from Role r where 1=1");
		if (condition.getSearchActiveState() != null && condition.getSearchActiveState() != 0) {
			result.append(" and r.activeState= ?");
			condition.addParameter(condition.getSearchActiveState());
		}
		if (condition.getQueryRoleID() != null && !"".equals(condition.getQueryRoleID())) {
			result.append(" and r.roleID != ?");
			condition.addParameter(condition.getQueryRoleID());
		}
		if (condition.getQueryRoleCode() != null && !"".equals(condition.getQueryRoleCode())) {
			result.append(" and r.roleCode= ?");
			condition.addParameter(condition.getQueryRoleCode());
		}
		condition.setSql(result.toString());
		return condition;
	}
}
