package cn.hs.module.department.dao.require;

import org.springframework.stereotype.Component;

import cn.hs.core.annotation.EscapeProperty;
import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.core.page.commond.SortInfoBean;
import cn.hs.module.department.domain.Department;
import cn.hs.module.department.domain.DepartmentCondition;

/**
 * Title: DepartmentRequire<br>
 * Description: 部门查询SQL拼写对象<br>
 * Company: goldgov<br>
 * Copyright @ 2012 goldgov .All rights reserved.<br>
 * 
 * @author Mill
 * @createDate 2012-08-01
 * @version $Revision$
 */
@Component(value = "cn.hs.module.department.dao.require.DepartmentRequire")
public class DepartmentRequire {
	/**
	 * list部门集合HQL
	 * 
	 * @param condition
	 * @return
	 * @author Mill
	 * @date 2012-08-01
	 */
	@EscapeProperty(escapePropertyNames = "searchDepName,searchDeptAddress,searchDeptPostNum,searchDeptPhone,searchDepkindCode,searchTreepath")
	public DepartmentCondition listDepartment(DepartmentCondition condition) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append(" SELECT t FROM Department t WHERE 1=1");

		if (condition.getSearchDepName() != null && !"".equals(condition.getSearchDepName())) {
			hqlBuffer.append(" AND t.deptName like ? escape '/'");
			condition.addParameter("%" + condition.getSearchDepName() + "%");
		}
		if (condition.getSearchType() != null && !"".equals(condition.getSearchType())) {
			hqlBuffer.append(" AND t.type = ? ");
			condition.addParameter(new Integer(condition.getSearchType()));
		}
		if (condition.getSearchDeptAddress() != null && !"".equals(condition.getSearchDeptAddress())) {
			hqlBuffer.append(" AND t.deptAddress like ? escape '/'");
			condition.addParameter("%" + condition.getSearchDeptAddress() + "%");
		}
		if (condition.getSearchDeptPostNum() != null && !"".equals(condition.getSearchDeptPostNum())) {
			hqlBuffer.append(" AND t.deptPostNum like ? escape '/'");
			condition.addParameter("%" + condition.getSearchDeptPostNum() + "%");
		}
		if (condition.getSearchDeptPhone() != null && !"".equals(condition.getSearchDeptPhone())) {
			hqlBuffer.append(" AND t.deptPhone like ? escape '/'");
			condition.addParameter("%" + condition.getSearchDeptPhone() + "%");
		}
		if (condition.getSearchOrderNum() != null && !"".equals(condition.getSearchOrderNum())) {
			hqlBuffer.append(" AND t.orderNum = ? ");
			condition.addParameter(new Integer(condition.getSearchOrderNum()));
		}
		if (condition.getSearchDepkindCode() != null && !"".equals(condition.getSearchDepkindCode())) {
			hqlBuffer.append(" AND t.deptKindCode like ? escape '/'");
			condition.addParameter("%" + condition.getSearchDepkindCode() + "%");
		}
		if (condition.getSearchTreepath() != null && !"".equals(condition.getSearchTreepath())) {
			hqlBuffer.append(" AND t.treepath like ? escape '/'");
			condition.addParameter("%" + condition.getSearchTreepath() + "%");
		}
		if (condition.getSearchParentID() != null && !"".equals(condition.getSearchParentID())) {
			hqlBuffer.append(" AND t.parentID = ? ");
			condition.addParameter(condition.getSearchParentID().toString());
		}
		if (condition.getSearchReportDepID() != null && !"".equals(condition.getSearchReportDepID())) {
			hqlBuffer.append(" AND t.reportDeptID = ? ");
			condition.addParameter(condition.getSearchReportDepID());
		}
		if (condition.getSearchActiveState() != null && !"".equals(condition.getSearchActiveState())) {
			hqlBuffer.append(" AND t.activeState = ? ");
			condition.addParameter(new Integer(condition.getSearchActiveState()));
		}
		// 拼装排序
		SortInfoBean sortInfo = condition.getSortInfo();
		if (sortInfo != null) {
			hqlBuffer.append(" ORDER BY " + sortInfo.getProperty() + " " + sortInfo.getDirection());
		} else {
			hqlBuffer.append(" ORDER BY t.orderNum");
		}
		condition.setSql(hqlBuffer.toString());
		return condition;
	}

	/**
	 * count部门集合HQL
	 * 
	 * @param condition
	 * @return
	 * @author Mill
	 * @date 2012-08-01
	 */
	@EscapeProperty(escapePropertyNames = "searchDepName,searchDeptAddress,searchDeptPostNum,searchDeptPhone,searchDepkindCode,searchTreepath")
	public DepartmentCondition countDepartmentHql(DepartmentCondition condition) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append(" SELECT COUNT(*) from Department t WHERE 1=1");

		if (condition.getSearchDepName() != null && !"".equals(condition.getSearchDepName())) {
			hqlBuffer.append(" AND t.deptName like ? escape '/'");
			condition.addParameter("%" + condition.getSearchDepName() + "%");
		}
		if (condition.getSearchType() != null && !"".equals(condition.getSearchType())) {
			hqlBuffer.append(" AND t.type = ? ");
			condition.addParameter(new Integer(condition.getSearchType()));
		}
		if (condition.getSearchDeptAddress() != null && !"".equals(condition.getSearchDeptAddress())) {
			hqlBuffer.append(" AND t.deptAddress like ? escape '/'");
			condition.addParameter("%" + condition.getSearchDeptAddress() + "%");
		}
		if (condition.getSearchDeptPostNum() != null && !"".equals(condition.getSearchDeptPostNum())) {
			hqlBuffer.append(" AND t.deptPostNum like ? escape '/'");
			condition.addParameter("%" + condition.getSearchDeptPostNum() + "%");
		}
		if (condition.getSearchDeptPhone() != null && !"".equals(condition.getSearchDeptPhone())) {
			hqlBuffer.append(" AND t.deptPhone like ? escape '/'");
			condition.addParameter("%" + condition.getSearchDeptPhone() + "%");
		}
		if (condition.getSearchOrderNum() != null && !"".equals(condition.getSearchOrderNum())) {
			hqlBuffer.append(" AND t.orderNum = ? ");
			condition.addParameter(new Integer(condition.getSearchOrderNum()));
		}
		if (condition.getSearchDepkindCode() != null && !"".equals(condition.getSearchDepkindCode())) {
			hqlBuffer.append(" AND t.deptKindCode like ? escape '/'");
			condition.addParameter("%" + condition.getSearchDepkindCode() + "%");
		}
		if (condition.getSearchTreepath() != null && !"".equals(condition.getSearchTreepath())) {
			hqlBuffer.append(" AND t.treepath like ? escape '/'");
			condition.addParameter("%" + condition.getSearchTreepath() + "%");
		}
		if (condition.getSearchParentID() != null && !"".equals(condition.getSearchParentID())) {
			hqlBuffer.append(" AND t.parentID = ? ");
			condition.addParameter(condition.getSearchParentID());
		}
		if (condition.getSearchReportDepID() != null && !"".equals(condition.getSearchReportDepID())) {
			hqlBuffer.append(" AND t.reportDeptID = ? ");
			condition.addParameter(condition.getSearchReportDepID());
		}
		if (condition.getSearchActiveState() != null && !"".equals(condition.getSearchActiveState())) {
			hqlBuffer.append(" AND t.activeState = ? ");
			condition.addParameter(new Integer(condition.getSearchActiveState()));
		}
		condition.setSql(hqlBuffer.toString());
		return condition;
	}

	/**
	 * 根据ID数组批量作废或启用部门HQL
	 * 
	 * @param condition
	 * @author Mill
	 * @date 2012-08-01
	 */
	public DepartmentCondition discardOrReuseDepartmentHQL(DepartmentCondition condition) {
		String hql = "update Department bd set bd.activeState = ? where 1=1";
		condition.addParameter(new Integer(condition.getSearchActiveState()));
		if (condition.getSearchDepIDs() != null && condition.getSearchDepIDs().length > 0) {
			hql += " and bd.deptID in (" + condition.assemblyParameterListSQL(condition.getSearchDepIDs()) + ")";
			condition.addParameterList(condition.getSearchDepIDs());
		}
		condition.setSql(hql);
		return condition;
	}

	/**
	 * 根据ID数组获得部门 HQL
	 * 
	 * @return
	 * @author Mill
	 * @date 2012-08-01
	 */
	public BaseCondition getDepartmentListByIDArrayHQL(String[] idArray) {
		BaseCondition condition = new BaseCondition();
		if (idArray != null) {
			String sql = " from Department where 1=2 ";
			// condition.setSql(" from Department where deptID = (" +
			// condition.assemblyParameterListSQL(idArray) + ")");
			for (int i = 0; i < idArray.length; i++) {
				sql += " or deptID = '" + idArray[i] + "'";
			}
			condition.setSql(sql);
		}
		// condition.addParameterList(idArray);
		return condition;
	}

	/**
	 * 根据ID获得部门HQL
	 * 
	 * @return
	 * @author Mill
	 * @date 2012-08-01
	 */
	public String getDepartmentByIDHQL() {
		return "from Department where deptID = :deptID";
	}

	/**
	 * 根据deptID查询其所有子部门HQL语句
	 * 
	 * @param condition
	 * @return
	 * @author Mill
	 * @createDate 2012-8-2
	 */
	public DepartmentCondition queryDepartmentByDeptID(DepartmentCondition condition) {
		String result = "select d from Department d where 1 = 1";
		result += " and d.activeState = ?";
		condition.addParameter(Department.IS_ACTIVE_Y);
		if (condition.getSearchDepIDs() != null && condition.getSearchDepIDs().length > 0) {
			result += " and d.parentID in (" + condition.assemblyParameterListSQL(condition.getSearchDepIDs()) + ")";
			condition.addParameterList(condition.getSearchDepIDs());
		}
		condition.setSql(result);
		return condition;
	}

	/**
	 * 根据部门性质类别编码查询语句
	 * 
	 * @param condition
	 * @return
	 * @author Mill
	 * @createDate 2012-8-2
	 */
	public DepartmentCondition queryByDeptKindCode(DepartmentCondition condition) {
		String result = "select t from Department t where 1=1";
		if (condition.getSearchDepID() != null && !"".equals(condition.getSearchDepID())) {
			result += " and t.deptID!= ?";
			condition.addParameter(condition.getSearchDepID());
		}
		if (condition.getSearchDeptCode() != null && !"".equals(condition.getSearchDeptCode())) {
			result += " and t.deptCode= ?";
			condition.addParameter(condition.getSearchDeptCode());
		}
		condition.setSql(result);
		return condition;
	}

	/**
	 * 部门信息同步入库
	 * 
	 * @param dept
	 * @return
	 * @author HuangS
	 * @date May 19, 2013
	 */
	public String insertDeptSQL(Department dept) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into BASE_DEPARTMENT ");
		sql.append("(");
		sql.append(" DEPID,");
		sql.append(" DEPNAME,");
		sql.append(" DEPT_ADDRESS,");
		sql.append(" DEPT_PHONE,");
		sql.append(" ORDER_NUM,");
		sql.append(" TREEPATH,");
		sql.append(" PARENT_ID,");
		sql.append(" ACTIVE_STATE,");
		sql.append(" DEPT_CODE,");
		sql.append(" REMARK,");
		sql.append(" COMPANY_LEVEL,");
		sql.append(" LINK_MAN,");
		sql.append(" CREATE_TIME,");
		sql.append(" LAST_UPDATE_TIME");
		sql.append(") values (");
		if (dept.getDeptID() != null) {
			sql.append(" '" + dept.getDeptID() + "',");
		} else {
			sql.append(" " + dept.getDeptID() + ",");
		}
		if (dept.getDeptName() != null) {
			sql.append(" '" + dept.getDeptName() + "',");
		} else {
			sql.append(" " + dept.getDeptName() + ",");
		}
		if (dept.getDeptAddress() != null) {
			sql.append(" '" + dept.getDeptAddress() + "',");
		} else {
			sql.append(" " + dept.getDeptAddress() + ",");
		}
		if (dept.getDeptPhone() != null) {
			sql.append(" '" + dept.getDeptPhone() + "',");
		} else {
			sql.append(" " + dept.getDeptPhone() + ",");
		}
		if (dept.getOrderNum() != null) {
			sql.append(" '" + dept.getOrderNum() + "',");
		} else {
			sql.append(" " + dept.getOrderNum() + ",");
		}
		if (dept.getTreepath() != null) {
			sql.append(" '" + dept.getTreepath() + "',");
		} else {
			sql.append(" " + dept.getTreepath() + ",");
		}
		if (dept.getParentID() != null) {
			sql.append(" '" + dept.getParentID() + "',");
		} else {
			sql.append(" " + dept.getParentID() + ",");
		}
		if (dept.getActiveState() != null) {
			sql.append(" '" + dept.getActiveState() + "',");
		} else {
			sql.append(" " + dept.getActiveState() + ",");
		}
		if (dept.getDeptCode() != null) {
			sql.append(" '" + dept.getDeptCode() + "',");
		} else {
			sql.append(" " + dept.getDeptCode() + ",");
		}
		if (dept.getRemark() != null) {
			sql.append(" '" + dept.getRemark() + "',");
		} else {
			sql.append(" " + dept.getRemark() + ",");
		}
		if (dept.getCompanyLevel() != null) {
			sql.append(" '" + dept.getCompanyLevel() + "',");
		} else {
			sql.append(" " + dept.getCompanyLevel() + ",");
		}
		if (dept.getLinkMan() != null) {
			sql.append(" '" + dept.getLinkMan() + "',");
		} else {
			sql.append(" " + dept.getLinkMan() + ",");
		}
		if (dept.getCreateTime() != null) {
			sql.append(" '" + dept.getCreateTime() + "',");
		} else {
			sql.append(" " + dept.getCreateTime() + ",");
		}
		if (dept.getLastUpdateTime() != null) {
			sql.append(" '" + dept.getLastUpdateTime() + "'");
		} else {
			sql.append(" " + dept.getLastUpdateTime());
		}
		sql.append(")");
		return sql.toString();
	}

	/**
	 * 根据部门名称数组获取部门集合
	 * 
	 * @param deptNames
	 * @return
	 * @author HuangS
	 * @date 2013-7-21
	 */
	public DepartmentCondition getDeptByDeptNames(String[] deptNames) {
		DepartmentCondition condition = new DepartmentCondition();
		if (deptNames != null && deptNames.length > 0) {
			condition.setSql("from Department t where t.deptName in (" + condition.assemblyParameterListSQL(deptNames) + ")");
			condition.addParameterList(deptNames);
		}
		return condition;
	}

}
