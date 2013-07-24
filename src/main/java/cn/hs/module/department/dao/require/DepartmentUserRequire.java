package cn.hs.module.department.dao.require;

import org.springframework.stereotype.Component;

import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.core.page.commond.SortInfoBean;
import cn.hs.module.department.domain.DepartmentUserCondition;

/**
 * Title: DepartmentUserRequire<br>
 * Description: 部门用户查询SQL拼写对象<br>
 * Company: GoldGov<br>
 * Copyright @ 2012 GoldGov .All rights reserved.<br>
 * @author Limk
 * @createDate 2012-08-13
 * @version $Revision$
 */
@Component(value = "cn.hs.module.department.dao.require.DepartmentUserRequire")
public class DepartmentUserRequire {
	/**
	 * list部门用户集合HQL
	 * 
	 * @param condition
	 * @return
	 * @author Limk
	 * @date 2012-08-13
	 */
	public DepartmentUserCondition listDepartmentUser(DepartmentUserCondition condition) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append(" SELECT t FROM DepartmentUser t WHERE 1=1");

		if (condition.getSearchDeptUserID() != null && !"".equals(condition.getSearchDeptUserID())) {
			hqlBuffer.append(" AND t.deptUserID = ? ");
			condition.addParameter(condition.getSearchDeptUserID());
		}
		if (condition.getSearchUserId() != null) {
			hqlBuffer.append(" and t.user.userId = ? ");
			condition.addParameter(condition.getSearchUserId());
		}
		// 拼装排序
		SortInfoBean sortInfo = condition.getSortInfo();
		if (sortInfo != null) {
			hqlBuffer.append(" ORDER BY " + sortInfo.getProperty() + " " + sortInfo.getDirection());
		} else {
			// hqlBuffer.append(" ORDER BY t.");
		}
		condition.setSql(hqlBuffer.toString());
		return condition;
	}

	/**
	 * count部门用户集合HQL
	 * 
	 * @param condition
	 * @return
	 * @author Limk
	 * @date 2012-08-13
	 */
	public DepartmentUserCondition countDepartmentUserHql(DepartmentUserCondition condition) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append(" SELECT COUNT(*) from DepartmentUser t WHERE 1=1");
		if (condition.getSearchDeptUserID() != null && !"".equals(condition.getSearchDeptUserID())) {
			hqlBuffer.append(" AND t.deptUserID = ? ");
			condition.addParameter(condition.getSearchDeptUserID());
		}
		if (condition.getSearchDeptIDs() != null && condition.getSearchDeptIDs().length > 0) {
			hqlBuffer.append(" AND t.department.deptID in (" + condition.assemblyParameterListSQL(condition.getSearchDeptIDs()) + ")");
			condition.addParameterList(condition.getSearchDeptIDs());
		}
		if (condition.getSearchUserActiveState() != null && condition.getSearchUserActiveState() != 0) {
			hqlBuffer.append(" AND t.user.activeState = ? ");
			condition.addParameter(condition.getSearchUserActiveState());
		}
		condition.setSql(hqlBuffer.toString());
		return condition;
	}

	/**
	 * 根据ID数组批量作废或启用部门用户HQL
	 * 
	 * @param condition
	 * @author Limk
	 * @date 2012-08-13
	 */
	public DepartmentUserCondition discardOrReuseDepartmentUserHQL(DepartmentUserCondition condition) {
		String hql = "update DepartmentUser bd set bd.isEnabled = ? where 1=1";
		condition.addParameter(new Integer(condition.getSearchIsEnabled()));
		if (condition.getSearchDeptUserIDs() != null && condition.getSearchDeptUserIDs().length > 0) {
			hql += " and bd.deptUserID in (" + condition.assemblyParameterListSQL(condition.getSearchDeptUserIDs()) + ")";
			condition.addParameterList(condition.getSearchDeptUserIDs());
		}
		condition.setSql(hql);
		return condition;
	}

	/**
	 * 根据ID数组获得部门用户 HQL
	 * 
	 * @return
	 * @author Limk
	 * @date 2012-08-13
	 */
	public BaseCondition getDepartmentUserListByIDArrayHQL(String[] idArray) {
		BaseCondition condition = new BaseCondition();
		condition.setSql(" from DepartmentUser where deptUserID in (" + condition.assemblyParameterListSQL(idArray) + ")");
		condition.addParameterList(idArray);
		return condition;
	}

	/**
	 * 根据ID获得部门用户HQL
	 * 
	 * @return
	 * @author Limk
	 * @date 2012-08-13
	 */
	public String getDepartmentUserByIDHQL() {
		return "from DepartmentUser where deptUserID = :deptUserID";
	}

}
