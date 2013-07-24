package cn.hs.module.modules.domain;

import cn.hs.core.basedao.condition.BaseTreeCondition;

/**
 * 
 * Title: ResourceTreeCondition<br>
 * Description: 资源Tree condition<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author FanKY
 * @createDate Sep 13, 2011
 * @version $Revision: 61 $
 */
public class ResourceTreeCondition extends BaseTreeCondition {
	/**
	 * 管理范围
	 */
	private String queryTreePath;
	/**
	 * 资源ID
	 */
	private String queryResourceId;
	/**
	 * 父级id
	 */
	private String queryParentID;
	/**
	 * 父级id集合
	 */
	private String[] queryParentIDs;

	public String[] getQueryParentIDs() {
		return queryParentIDs;
	}

	public void setQueryParentIDs(String[] queryParentIDs) {
		this.queryParentIDs = queryParentIDs;
	}

	public String getQueryTreePath() {
		return queryTreePath;
	}

	public void setQueryTreePath(String queryTreePath) {
		this.queryTreePath = queryTreePath;
	}

	public String getQueryResourceId() {
		return queryResourceId;
	}

	public void setQueryResourceId(String queryResourceId) {
		this.queryResourceId = queryResourceId;
	}

	public String getQueryParentID() {
		return queryParentID;
	}

	public void setQueryParentID(String queryParentID) {
		this.queryParentID = queryParentID;
	}

}
