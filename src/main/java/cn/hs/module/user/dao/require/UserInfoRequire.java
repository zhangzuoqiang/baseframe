package cn.hs.module.user.dao.require;

import org.springframework.stereotype.Repository;

import cn.hs.commons.utils.PropertyUtil;
import cn.hs.core.annotation.EscapeProperty;
import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.core.page.commond.SortInfoBean;
import cn.hs.module.department.domain.Department;
import cn.hs.module.department.domain.DepartmentUser;
import cn.hs.module.user.domain.User;
import cn.hs.module.user.domain.UserInfoCondition;

/**
 * Title: RoleRequire<br>
 * Description: 用户信息HQL封装 <br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Sep 5, 2011
 * @version $Revision: 62 $
 */
@Repository(value = "cn.hs.module.user.dao.require.UserInfoRequire")
public class UserInfoRequire {

	/**
	 * 根据用户名验证
	 * 
	 * @param condition
	 * @return
	 * @author XiangBin
	 * @date Jul 31, 2012
	 */
	public UserInfoCondition queryCheckUserLoginHQL(UserInfoCondition condition) {
		StringBuffer result = new StringBuffer("from User u where 1=1 ");

		if (condition.getSearchLoginID() != null && !"".equals(condition.getSearchLoginID())) {
			result.append(" and u.loginID = ?");
			condition.addParameter(condition.getSearchLoginID());
		}
		if (condition.getSearchPassWord() != null && !"".equals(condition.getSearchPassWord())) {
			result.append(" and u.passWord = ?");
			condition.addParameter(condition.getSearchPassWord());
		}
		if (condition.getSearchActiveState() != null && !"".equals(condition.getSearchActiveState())) {
			result.append(" and u.activeState = ?");
			condition.addParameter(User.IS_ACTIVE_Y);
		}
		condition.setSql(result.toString());
		return condition;
	}

	/**
	 * 根据用户Id获取用户所在的部门
	 * 
	 * @param condition
	 * @return
	 * @author XiangBin
	 * @date Jul 31, 2012
	 */
	public UserInfoCondition currentDepartmentHQL(UserInfoCondition condition) {
		StringBuffer result = new StringBuffer("select dep from DepartmentUser dul join dul.department dep join dul.user u where u.userID = ?");
		condition.addParameter(condition.getSearchUserId());
		result.append(" and dul.activeState = ?");
		condition.addParameter(DepartmentUser.IS_ACTIVE_Y);
		result.append(" and dep.activeState = ?");
		condition.addParameter(Department.IS_ACTIVE_Y);
		result.append(" and u.activeState = ?");
		condition.addParameter(User.IS_ACTIVE_Y);
		condition.setSql(result.toString());
		return condition;
	}

	/**
	 * 根据用户Id获取用户的管理范围
	 * 
	 * @param condition
	 * @return
	 * @author XiangBin
	 * @date Jul 31, 2012
	 */
	public UserInfoCondition currentManageScopeHQL(UserInfoCondition condition) {
		StringBuffer result = new StringBuffer("select dep from UserManageScope ums join ums.department dep join ums.user u where u.userID = ?");
		condition.addParameter(condition.getSearchUserId());
		result.append(" and dep.activeState = ?");
		condition.addParameter(Department.IS_ACTIVE_Y);
		result.append(" and u.activeState = ?");
		condition.addParameter(User.IS_ACTIVE_Y);
		condition.setSql(result.toString());
		return condition;
	}

	/**
	 * listUserService集合HQL
	 * 
	 * @param condition
	 * @return
	 * @author Limk
	 * @date 2012-08-01
	 */
	@EscapeProperty(escapePropertyNames = "searchLoginID,searchUserName")
	public UserInfoCondition listUser(UserInfoCondition condition) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("select t.user from DepartmentUser t");
		hqlBuffer.append(" where t.department.deptID = ?");
		condition.addParameter(condition.getSearchDepID());
		// hqlBuffer.append(" SELECT t.USER_ID as \"userId\",t.LOGIN_ID as
		// \"loginID\",");
		// hqlBuffer.append(" t.SEX_CODE as \"sexCode\",t.PASSWORD as
		// \"password\",t.USER_NAME as \"userName\",");
		// hqlBuffer.append(" t.BIRTHDAY as \"birthday\",t.PHONE as
		// \"phone\",t.MOBILE_PHONE as \"mobileTelephone\",");
		// hqlBuffer.append(" t.EMAIL as \"email\",t.PAPER_NUM as
		// \"paperNum\",");
		// hqlBuffer.append(" t.ACTIVE_STATE as \"activeState\",t.ORDER_NUM as
		// \"orderNum\",");
		// hqlBuffer.append(" t.REMARK as \"remark\",");
		// hqlBuffer.append(" (select wmsys.wm_concat(br.role_name) from
		// base_role_user bru, ");
		// hqlBuffer.append(" base_role br where br.role_id = bru.role_id ");
		// hqlBuffer.append(" and bru.userid = t.userid group by bru.userid) as
		// \"roleNames\",");
		// hqlBuffer.append(" (select wmsys.wm_concat(br.role_id) from
		// base_role_user bru, ");
		// hqlBuffer.append(" base_role br where br.role_id = bru.role_id ");
		// hqlBuffer.append(" and bru.userid = t.userid group by bru.userid) as
		// \"roleIds\" ");
		// if (PropertyUtil.objectNotEmpty(condition.getSearchDepID())) {
		// hqlBuffer.append(" du.DEPID as \"deptID\" FROM BASE_USER
		// t,BASE_DEPARTMENT_USER du WHERE du.USER_ID = t.USER_ID AND du.DEPID =
		// ?");
		// condition.addParameter(condition.getSearchDepID());
		// } else {
		// hqlBuffer.append(" FROM BASE_USER t where 1=1 ");
		// }
		if (condition.getSearchUserId() != null && !"".equals(condition.getSearchUserId())) {
			// hqlBuffer.append(" AND t.USER_ID = ? ");
			hqlBuffer.append(" AND t.user.userID = ? ");
			condition.addParameter(condition.getSearchUserId());
		}
		if (condition.getSearchLoginID() != null && !"".equals(condition.getSearchLoginID())) {
			// hqlBuffer.append(" AND t.LOGIN_ID like ? escape '/'");
			hqlBuffer.append(" AND t.user.loginID like ? escape '/'");
			condition.addParameter("%" + condition.getSearchLoginID() + "%");
		}
		if (condition.getSearchUserName() != null && !"".equals(condition.getSearchUserName())) {
			// hqlBuffer.append(" AND t.USER_NAME like ? escape '/'");
			hqlBuffer.append(" AND t.user.userName like ? escape '/'");
			condition.addParameter("%" + condition.getSearchUserName() + "%");
		}
		if (condition.getSearchActiveState() != null && !"".equals(condition.getSearchActiveState())) {
			// hqlBuffer.append(" AND t.ACTIVE_STATE = ? ");
			hqlBuffer.append(" AND t.user.activeState = ? ");
			condition.addParameter(new Integer(condition.getSearchActiveState()));
		}
		// 拼装排序
		SortInfoBean sortInfo = condition.getSortInfo();
		if (sortInfo != null) {
			hqlBuffer.append(" ORDER BY " + sortInfo.getProperty() + " " + sortInfo.getDirection());
		} else {
			// hqlBuffer.append(" ORDER BY t.ORDER_NUM");
			hqlBuffer.append(" ORDER BY t.user.orderNum");
		}
		condition.setSql(hqlBuffer.toString());
		return condition;
	}

	/**
	 * countUserService集合HQL
	 * 
	 * @param condition
	 * @return
	 * @author Limk
	 * @date 2012-08-01
	 */
	@EscapeProperty(escapePropertyNames = "searchLoginID,searchUserName")
	public UserInfoCondition countUserHql(UserInfoCondition condition) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append(" SELECT COUNT(*) from User t");
		if (PropertyUtil.objectNotEmpty(condition.getSearchDepID())) {
			hqlBuffer.append(",DepartmentUser du WHERE du.user.userID = t.userID AND  du.department.deptID = ?");
			condition.addParameter(condition.getSearchDepID());
		} else {
			hqlBuffer.append(" WHERE 1=1");
		}
		// hqlBuffer.append(" and sex.dataCode = t.sexCode ");
		if (condition.getSearchUserId() != null && !"".equals(condition.getSearchUserId())) {
			hqlBuffer.append(" AND t.userID = ? ");
			condition.addParameter(condition.getSearchUserId());
		}
		if (condition.getSearchLoginID() != null && !"".equals(condition.getSearchLoginID())) {
			hqlBuffer.append(" AND t.loginID like ? escape '/'");
			condition.addParameter("%" + condition.getSearchLoginID() + "%");
		}
		if (condition.getSearchUserName() != null && !"".equals(condition.getSearchUserName())) {
			hqlBuffer.append(" AND t.userName like ? escape '/'");
			condition.addParameter("%" + condition.getSearchUserName() + "%");
		}
		if (condition.getSearchActiveState() != null && !"".equals(condition.getSearchActiveState())) {
			hqlBuffer.append(" AND t.activeState = ? ");
			condition.addParameter(new Integer(condition.getSearchActiveState()));
		}
		condition.setSql(hqlBuffer.toString());
		return condition;
	}

	/**
	 * 根据ID数组批量作废或启用UserServiceHQL
	 * 
	 * @param condition
	 * @author Limk
	 * @date 2012-08-01
	 */
	public UserInfoCondition discardOrReuseUserHQL(UserInfoCondition condition) {
		String hql = "update User bd set bd.activeState = ? where 1=1";
		condition.addParameter(new Integer(condition.getSearchIsEnabled()));
		if (condition.getSearchUserIds() != null && condition.getSearchUserIds().length > 0) {
			hql += " and bd.userID in (" + condition.assemblyParameterListSQL(condition.getSearchUserIds()) + ")";
			condition.addParameterList(condition.getSearchUserIds());
		}
		condition.setSql(hql);
		return condition;
	}

	/**
	 * 根据ID数组获得UserService HQL
	 * 
	 * @return
	 * @author Limk
	 * @date 2012-08-01
	 */
	public BaseCondition getUserListByIDArrayHQL(String[] idArray) {
		BaseCondition condition = new BaseCondition();
		condition.setSql(" from User where userID in (" + condition.assemblyParameterListSQL(idArray) + ")");
		condition.addParameterList(idArray);
		return condition;
	}

	/**
	 * 用户信息同步入库
	 * 
	 * @param user
	 * @return
	 * @author HuangS
	 * @date May 19, 2013
	 */
	public String insertUserSQL(User user) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into BASE_USER ");
		sql.append("(");
		sql.append(" USER_ID,");
		sql.append(" LOGIN_ID,");
		sql.append(" PASSWORD,");
		sql.append(" SEX_CODE,");
		sql.append(" BIRTHDAY,");
		sql.append(" PHONE,");
		sql.append(" MOBILE_PHONE,");
		sql.append(" EMAIL,");
		sql.append(" PAPER_NUM,");
		sql.append(" ACTIVE_STATE,");
		sql.append(" ORDER_NUM,");
		sql.append(" REMARK,");
		sql.append(" CREATE_TIME,");
		sql.append(" LAST_UPDATE_TIME,");
		sql.append(" PAPER_TYPE_CODE,");
		sql.append(" USER_NAME");
		sql.append(") values (");
		if (user.getUserID() != null) {
			sql.append(" '" + user.getUserID() + "',");
		} else {
			sql.append(" " + user.getUserID() + ",");
		}
		if (user.getLoginID() != null) {
			sql.append(" '" + user.getLoginID() + "',");
		} else {
			sql.append(" " + user.getLoginID() + ",");
		}
		if (user.getPassWord() != null) {
			sql.append(" '" + user.getPassWord() + "',");
		} else {
			sql.append(" " + user.getPassWord() + ",");
		}
		if (user.getSexCode() != null) {
			sql.append(" '" + user.getSexCode() + "',");
		} else {
			sql.append(" " + user.getSexCode() + ",");
		}
		if (user.getBirthday() != null) {
			sql.append(" '" + user.getBirthday() + "',");
		} else {
			sql.append(" " + user.getBirthday() + ",");
		}
		if (user.getPhone() != null) {
			sql.append(" '" + user.getPhone() + "',");
		} else {
			sql.append(" " + user.getPhone() + ",");
		}
		if (user.getMobileTelephone() != null) {
			sql.append(" '" + user.getMobileTelephone() + "',");
		} else {
			sql.append(" " + user.getMobileTelephone() + ",");
		}
		if (user.getEmail() != null) {
			sql.append(" '" + user.getEmail() + "',");
		} else {
			sql.append(" " + user.getEmail() + ",");
		}
		if (user.getPaperNum() != null) {
			sql.append(" '" + user.getPaperNum() + "',");
		} else {
			sql.append(" " + user.getPaperNum() + ",");
		}
		if (user.getActiveState() != null) {
			sql.append(" '" + user.getActiveState() + "',");
		} else {
			sql.append(" " + user.getActiveState() + ",");
		}
		if (user.getOrderNum() != null) {
			sql.append(" '" + user.getOrderNum() + "',");
		} else {
			sql.append(" " + user.getOrderNum() + ",");
		}
		if (user.getRemark() != null) {
			sql.append(" '" + user.getRemark() + "',");
		} else {
			sql.append(" " + user.getRemark() + ",");
		}
		if (user.getCreateTime() != null) {
			sql.append(" '" + user.getCreateTime() + "',");
		} else {
			sql.append(" " + user.getCreateTime() + ",");
		}
		if (user.getLastUpdateTime() != null) {
			sql.append(" '" + user.getLastUpdateTime() + "',");
		} else {
			sql.append(" " + user.getLastUpdateTime() + ",");
		}
		if (user.getPaperTypeCode() != null) {
			sql.append(" '" + user.getPaperTypeCode() + "',");
		} else {
			sql.append(" " + user.getPaperTypeCode() + ",");
		}
		if (user.getUserName() != null) {
			sql.append(" '" + user.getUserName() + "'");
		} else {
			sql.append(" " + user.getUserName());
		}
		sql.append(")");
		return sql.toString();
	}
}
