/*
 * $Log: ResourceRequire.java,v $
 * Revision 1.1  2012/05/23 09:27:53  guor
 * 初次提交
 *
 */
package cn.hs.module.modules.dao.require;

import org.springframework.stereotype.Repository;

import cn.hs.core.page.commond.SortInfoBean;
import cn.hs.module.modules.domain.Resource;
import cn.hs.module.modules.domain.ResourceCondition;
import cn.hs.module.modules.domain.ResourceTreeCondition;

/**
 * Title: IResourceRequire<br>
 * Description:菜单资源hql拼装类 <br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author FanKY
 * @createDate Sep 9, 2011
 * @version $Revision: 80 $
 */
@Repository(value = "cn.hs.module.modules.dao.require.ResourceRequire")
public class ResourceRequire {
	/**
	 * 查询资源数据
	 * 
	 * @param condition
	 * @return
	 * @author FanKY
	 * @date Sep 9, 2011
	 */
	public ResourceTreeCondition listResourceTreeHql(ResourceTreeCondition condition) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("select t from Resource t where t.activeState = ?");
		condition.addParameter(Resource.IS_ACTIVE_Y);
		if (condition.getNode() != null && !"".equals(condition.getNode())) {
			hqlBuffer.append(" and t.parentID = ?");
			condition.addParameter(condition.getNode());
		}
		// 设置默认排序 modify by HuangS at 2012-08-23
		hqlBuffer.append(" order by t.orderNum, t.resourceID ");
		condition.setSql(hqlBuffer.toString());
		return condition;
	}

	/**
	 * 查询资源数据
	 * 
	 * @param condition
	 * @return
	 * @author FanKY
	 * @date Sep 9, 2011
	 */
	public ResourceTreeCondition isLeapNode(ResourceTreeCondition condition) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("select count(t.resourceID) from Resource t where t.activeState = ?");
		condition.addParameter(Resource.IS_ACTIVE_Y);
		if (condition.getNode() != null && !"".equals(condition.getNode())) {
			hqlBuffer.append(" and t.parentID = ?");
			condition.addParameter(condition.getNode());
		}
		condition.setSql(hqlBuffer.toString());
		return condition;
	}

	/**
	 * 查询菜单集合 Hql
	 * 
	 * @param condition
	 * @return
	 * @author FanKY
	 * @date Sep 13, 2011
	 */
	public ResourceCondition listResourceHql(ResourceCondition condition) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("select t from Resource t where 1=1 ");
		if (condition.getSearchActiveState() != null && !"".equals(condition.getSearchActiveState())) {
			hqlBuffer.append(" and t.activeState = ? ");
			condition.addParameter(new Integer(condition.getSearchActiveState()));
		}
		if (condition.getSearchResourceName() != null && !"".equals(condition.getSearchResourceName())) {
			hqlBuffer.append(" and t.resourceName like ? escape'/'");
			condition.addParameter("%" + condition.getSearchResourceName() + "%");
		}
		if (condition.getQueryResourceId() != null && !"".equals(condition.getQueryResourceId())) {
			hqlBuffer.append(" and t.parentID = ?");
			condition.addParameter(condition.getQueryResourceId());
		}
		// 去掉已选择的资源
		if (condition.getQueryRoleID() != null && !"".equals(condition.getQueryRoleID())) {
			hqlBuffer.append(" and t.resourceID not in (select r.resource.resourceID from RoleResource r where r.role.roleID= ?)");
			condition.addParameter(condition.getQueryRoleID());
		}
		// 根据角色查询所有的资源
		if (condition.getRoleIds() != null && condition.getRoleIds().length > 0) {
			hqlBuffer.append(" and t.resourceID in (select r.resource.resourceID from RoleResource r where r.role.roleID in(" + condition.assemblyParameterListSQL(condition.getRoleIds()) + "))");
			condition.addParameterList(condition.getRoleIds());
		}
		// 拼装排序
		SortInfoBean sortInfo = condition.getSortInfo();
		if (sortInfo != null) {
			hqlBuffer.append(" ORDER BY " + sortInfo.getProperty() + " " + sortInfo.getDirection());
		} else {
			// 设置默认排序 modify by HuangS at 2012-08-23
			hqlBuffer.append(" ORDER BY t.orderNum, t.resourceID ");
		}
		condition.setSql(hqlBuffer.toString());
		return condition;
	}

	/**
	 * 根据角色查询资源
	 * 
	 * @param condition
	 * @return
	 * @author XiangBin
	 * @date Jul 31, 2012
	 */
	public ResourceCondition listResourceByRoleHql(ResourceCondition condition) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("select t from Resource t where 1=1");
		if (condition.getSearchActiveState() != null && !"".equals(condition.getSearchActiveState())) {
			hqlBuffer.append(" and t.activeState = ? ");
			condition.addParameter(new Integer(condition.getSearchActiveState()));
		}
		if (condition.getRoleIds() != null && condition.getRoleIds().length > 0) {
			hqlBuffer.append(" and t.resourceID in(select r.resource.parentID  from RoleResource r,Resource rs");
			hqlBuffer.append("    where rs.resourceID=r.resource.resourceID and r.role.roleID in(" + condition.assemblyParameterListSQL(condition.getRoleIds()) + "))");
			condition.addParameterList(condition.getRoleIds());
		}
		if (condition.getQueryResourceParentID() != null && !"".equals(condition.getQueryResourceParentID())) {
			hqlBuffer.append(" and t.parentID = ?");
			condition.addParameter(condition.getQueryResourceParentID());
		}
		hqlBuffer.append(" ORDER BY t.orderNum");
		condition.setSql(hqlBuffer.toString());
		return condition;
	}

	/**
	 * 查询菜单集合count Hql
	 * 
	 * @param condition
	 * @return
	 * @author XiangBin
	 * @date Sep 18, 2012
	 */
	public ResourceCondition countResourceLongHql(ResourceCondition condition) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("select count(t.resourceID) from Resource t where 1=1 ");
		if (condition.getSearchActiveState() != null && !"".equals(condition.getSearchActiveState())) {
			hqlBuffer.append(" and t.activeState = ? ");
			condition.addParameter(Integer.parseInt(condition.getSearchActiveState()));
		}
		if (condition.getSearchResourceName() != null && !"".equals(condition.getSearchResourceName())) {
			hqlBuffer.append(" and t.resourceName like ? escape '/'");
			condition.addParameter("%" + condition.getSearchResourceName() + "%");
		}
		if (condition.getQueryResourceId() != null && !"".equals(condition.getQueryResourceId())) {
			hqlBuffer.append(" and t.parentID = ?");
			condition.addParameter(condition.getQueryResourceId());
		}
		// 去掉已选择的资源
		if (condition.getQueryRoleID() != null && !"".equals(condition.getQueryRoleID())) {
			hqlBuffer.append(" and t.resourceID not in (select r.resource.resourceID from RoleResource r where r.role.roleID= ?)");
			condition.addParameter(condition.getQueryRoleID());
		}
		condition.setSql(hqlBuffer.toString());
		return condition;
	}

	/**
	 * 根据ID集合批量作废或启用资源 Hql
	 * 
	 * @param condition
	 * @return
	 * @author XiangBin
	 * @date Jul 18, 2012
	 */
	public ResourceCondition discardOrReuseResourceHql(ResourceCondition condition) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("update Resource bd set bd.activeState = ? where 1=1");
		condition.addParameter(new Integer(condition.getSearchActiveState()));
		if (condition.getResourceIds() != null && condition.getResourceIds().length > 0) {
			hqlBuffer.append(" and bd.resourceID in (" + condition.assemblyParameterListSQL(condition.getResourceIds()) + ")");
			condition.addParameterList(condition.getResourceIds());
		}
		condition.setSql(hqlBuffer.toString());
		return condition;
	}

	/**
	 * 根据父级ID查询下一级节点集合 Hql
	 * 
	 * @param condition
	 * @return
	 * @author XiangBin
	 * @date Sep 18, 2012
	 */
	public ResourceTreeCondition listResourceTreeOtherHql(ResourceTreeCondition condition) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("select t from Resource t where t.activeState = ? ");
		condition.addParameter(Resource.IS_ACTIVE_Y);
		if (condition.getQueryParentIDs() != null && condition.getQueryParentIDs().length > 0) {
			hqlBuffer.append(" and t.parentID = ? ");
			condition.addParameter(condition.getNode());
		}
		condition.setSql(hqlBuffer.toString());
		return condition;
	}
}
