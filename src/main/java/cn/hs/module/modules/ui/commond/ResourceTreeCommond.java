package cn.hs.module.modules.ui.commond;

import cn.hs.core.servicetemplate.treetemplate.commond.BaseTreeCommond;

/**
 * Title: ResourceTreeCommond<br>
 * Description:资源树 <br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author XiangBin
 * @createDate Jul 24, 2012
 * @version $Revision:$
 */
public class ResourceTreeCommond extends BaseTreeCommond {
	private String parentID;

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}
}
