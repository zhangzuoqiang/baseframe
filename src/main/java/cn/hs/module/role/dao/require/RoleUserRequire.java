package cn.hs.module.role.dao.require;

import org.springframework.stereotype.Repository;

import cn.hs.commons.utils.PropertyUtil;
import cn.hs.core.page.commond.SortInfoBean;
import cn.hs.module.role.domain.RoleUserCondition;

/**
 * Title: RoleUserRequire<br>
 * Description: 角色用户HQL封装<br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author XiangBin
 * @createDate Aug 1, 2012
 * @version $Revision:$
 */
@Repository(value = "cn.hs.module.role.dao.require.RoleUserRequire")
public class RoleUserRequire {

	/**
	 * 根据用户ID查询角色-用户信息HQL
	 * 
	 * @param condition
	 * @return
	 * @author XiangBin
	 * @date Sep 23, 2012
	 */
	public RoleUserCondition queryRoleUserByUserIDHQL(RoleUserCondition condition) {
		StringBuffer result = new StringBuffer("select r from Role r where 1=1");
		if (condition.getQueryUserID() != null && !"".equals(condition.getQueryUserID())) {
			result.append(" and r.roleID in (select ru.role.roleID from RoleUser ru where ru.user.userId = ?)");
			condition.addParameter(condition.getQueryUserID());
		}
		condition.setSql(result.toString());
		return condition;
	}

	/**
	 * 根据condition删除角色
	 * 
	 * @param condition
	 * @return
	 * @author XiangBin
	 * @date Sep 23, 2012
	 */
	public RoleUserCondition deleteRoleUserHQL(RoleUserCondition condition) {
		StringBuffer result = new StringBuffer();
		if (condition.getSearchRoleIDs() != null && condition.getSearchRoleIDs().length > 0) {
			result.append("delete from RoleUser t where t.role.roleID in (" + condition.assemblyParameterListSQL(condition.getSearchRoleIDs()) + ")");
			condition.addParameterList(condition.getSearchRoleIDs());
		} else {
			result.append("delete from RoleUser t where 1=1");
		}
		if (PropertyUtil.objectNotEmpty(condition.getQueryUserID())) {
			result.append(" and t.user.userId = ?");
			condition.addParameter(condition.getQueryUserID());
		}
		condition.setSql(result.toString());
		return condition;
	}

	/**
	 * 根据角色id查询用户列表
	 * 
	 * @param roleId
	 * @return
	 * @author MaBin
	 * @date 2012-9-18 16:09:26
	 */
	public RoleUserCondition listUserForRole(RoleUserCondition condition) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("from RoleUser t where 1=1 ");
		if (condition.getSearchRoleId() != null) {
			hqlBuffer.append(" and t.role.roleID = ? ");
			condition.addParameter(condition.getSearchRoleId());
		}

		// 拼装排序
		SortInfoBean sortInfo = condition.getSortInfo();
		if (sortInfo != null) {
			hqlBuffer.append(" ORDER BY " + sortInfo.getProperty() + " " + sortInfo.getDirection());
		} else {
			hqlBuffer.append(" ORDER BY t.roleUserID ");
		}

		condition.setSql(hqlBuffer.toString());
		return condition;
	}

	/**
	 * 根据roleid查询user信息hql
	 * 
	 * @param condition
	 * @return
	 * @author MaBin
	 * @date 2012-9-22 18:55:24
	 */
	public RoleUserCondition listUserInfoJDBC(RoleUserCondition condition) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append(" select bu.user_name as \"userName\",");
		hqlBuffer.append(" dept.depname        as \"deptName\",");
		hqlBuffer.append(" bu.login_id         as \"accountNum\",");
		hqlBuffer.append(" bu.active_state     as \"activeState\",");
		hqlBuffer.append(" bu.userid           as \"userId\",");
		hqlBuffer.append(" bu.mobile_phone as \"phoneNum\" ");
		hqlBuffer.append(" from BASE_ROLE_USER       ru,");
		hqlBuffer.append(" BASE_USER            bu,");
		hqlBuffer.append(" BASE_ROLE            br,");
		hqlBuffer.append(" BASE_DEPARTMENT_USER du,");
		hqlBuffer.append(" BASE_DEPARTMENT      dept ");
		hqlBuffer.append(" where ru.role_id = br.role_id ");
		hqlBuffer.append(" and ru.userid = bu.userid ");
		hqlBuffer.append(" and bu.userid = du.userid ");
		hqlBuffer.append(" and du.depid = dept.depid ");
		hqlBuffer.append(" and br.active_state = '1' ");
		hqlBuffer.append(" and br.role_id = ? ");
		condition.addParameter(condition.getSearchRoleId());

		// 拼装排序
		SortInfoBean sortInfo = condition.getSortInfo();
		if (sortInfo != null) {
			hqlBuffer.append(" ORDER BY " + sortInfo.getProperty() + " " + sortInfo.getDirection());
		} else {
			hqlBuffer.append(" ORDER BY bu.userid ");
		}
		condition.setSql(hqlBuffer.toString());
		return condition;
	}

	/**
	 * 根据角色id查询用户列表
	 * 
	 * @param roleId
	 * @return
	 * @author MaBin
	 * @date 2012-9-18 16:09:26
	 */
	public RoleUserCondition countUserForRole(RoleUserCondition condition) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append(" select count(bu.USERID) ");
		hqlBuffer.append(" from BASE_ROLE_USER       ru,");
		hqlBuffer.append(" BASE_USER            bu,");
		hqlBuffer.append(" BASE_ROLE            br,");
		hqlBuffer.append(" BASE_DEPARTMENT_USER du,");
		hqlBuffer.append(" BASE_DEPARTMENT      dept ");
		hqlBuffer.append(" where ru.role_id = br.role_id ");
		hqlBuffer.append(" and ru.userid = bu.userid ");
		hqlBuffer.append(" and bu.userid = du.userid ");
		hqlBuffer.append(" and du.depid = dept.depid ");
		hqlBuffer.append(" and br.active_state = '1' ");
		hqlBuffer.append(" and br.role_id = ? ");
		condition.addParameter(condition.getSearchRoleId());
		condition.setSql(hqlBuffer.toString());
		return condition;
	}

	/**
	 * 批量启用或作废用户
	 * 
	 * @param condition
	 * @return
	 * @author MaBin
	 * @date 2012-10-14 16:03:51
	 */
	public RoleUserCondition discardOrReuseBaseUserHQL(RoleUserCondition condition) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("update User bu set bu.activeState = ? where 1=1 ");
		condition.addParameter(condition.getActiveStatus());
		if (condition.getUserIds() != null && condition.getUserIds().length > 0) {
			hqlBuffer.append(" and bu.userId in (" + condition.assemblyParameterListSQL(condition.getUserIds()) + ") ");
			condition.addParameterList(condition.getUserIds());
		}
		condition.setSql(hqlBuffer.toString());
		return condition;
	}

}
