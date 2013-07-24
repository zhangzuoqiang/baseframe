package cn.hs.module.role.dao.require;

import org.springframework.stereotype.Repository;

import cn.hs.core.page.commond.SortInfoBean;
import cn.hs.module.role.domain.RoleResourceCondition;

/**
 * 角色HQL封装 Title: ChoisedRoleResourceRequire<br>
 * Description: <br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author YaoSC
 * @createDate Sep 5, 2011
 * @version $Revision: 62 $
 */
@Repository(value = "cn.hs.module.role.dao.require.ChoisedRoleResourceRequire")
public class ChoisedRoleResourceRequire {

	/**
	 * 查询角色countHQL
	 * 
	 * @param condition
	 * @return
	 * @author XiangBin
	 * @date Sep 23, 2012
	 */
	public RoleResourceCondition countAuthorityRoleHQL(RoleResourceCondition condition) {
		StringBuffer hql = new StringBuffer();
		hql.append("select count(*) from Role a,RoleResource b, Resource c ");
		hql.append(" where a.roleID=b.role.roleID and b.resource.resourceID=c.resourceID");
		if (condition.getQueryRoleID() != null && !"".equals(condition.getQueryRoleID())) {
			hql.append(" and a.roleID = ?");
			condition.addParameter(condition.getQueryRoleID());
		}
		if (condition.getSearchActiveState() != null && !"".equals(condition.getSearchActiveState())) {
			hql.append(" and c.activeState = ?");
			condition.addParameter(condition.getSearchActiveState());
		}
		condition.setSql(hql.toString());
		return condition;
	}

	/**
	 * 查询角色集合HQL
	 * 
	 * @param condition
	 * @return
	 * @author YaoSC
	 * @date Sep 5, 2011
	 */
	public RoleResourceCondition listAuthorityRoleHQL(RoleResourceCondition condition) {
		StringBuffer result = new StringBuffer("" + "" + "" + "" + "");
		result.append("select b.roleResourceID as roleResourceID ,");
		result.append(" t.parentID as parentID,a.roleID as roleID,");
		result.append(" t.url as url,t.resourceName as resourceName,");
		result.append("  t.description as description from Role a,RoleResource b ,Resource t ");
		result.append(" where a.roleID=b.role.roleID and b.resource.resourceID=t.resourceID");
		if (condition.getQueryRoleID() != null && !"".equals(condition.getQueryRoleID())) {
			result.append(" and a.roleID = ?");
			condition.addParameter(condition.getQueryRoleID());
		}
		if (condition.getSearchActiveState() != null && !"".equals(condition.getSearchActiveState())) {
			result.append(" and t.activeState = ?");
			condition.addParameter(condition.getSearchActiveState());
		}
		SortInfoBean sortInfo = condition.getSortInfo();
		if (sortInfo != null) {
			result.append(" ORDER BY " + sortInfo.getProperty() + " " + sortInfo.getDirection());
		} else {
			result.append(" ORDER BY t.orderNum,t.resourceID");
		}
		condition.setSql(result.toString());
		return condition;
	}

	/**
	 * 得到中间表中资源集合
	 * 
	 * @param condition
	 * @return
	 * @author XiangBin
	 * @date Oct 23, 2012
	 */
	public String listResourceHQL(RoleResourceCondition condition) {
		String result = "select t from RoleResource t where 1=1";
		if (condition.getQueryRoleID() != null && !"".equals(condition.getQueryRoleID())) {
			result += " and t.role.roleID = ?";
			condition.addParameter(condition.getQueryRoleID());
		}
		result += " order by t.roleResourceID";
		condition.setSql(result.toString());
		return result;
	}

}
