package cn.hs.core.servicetemplate.treetemplate.commond;

/**
 * 
 * Title: BaseTreeCommond<br>
 * Description: tree属性基类，需要tree的页面，域对象需要继承此类<br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Jul 21, 2012
 * @version $Revision:$
 */
public class BaseTreeCommond {

	private String node; // EXT 默认当前节点

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

}
