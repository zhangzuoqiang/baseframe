/*
 * $Log: ResourceTreeServiceImpl.java,v $
 * Revision 1.1  2012/05/23 09:27:54  guor
 * 初次提交
 *
 */
package cn.hs.module.modules.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hs.core.basedao.condition.BaseTreeCondition;
import cn.hs.core.servicetemplate.treetemplate.TreeTemplate;
import cn.hs.core.servicetemplate.treetemplate.jsonbean.JsonTreeBean;
import cn.hs.module.modules.dao.IResourceTreeDao;
import cn.hs.module.modules.domain.Resource;
import cn.hs.module.modules.domain.ResourceTreeCondition;
import cn.hs.module.modules.service.IResourceTreeService;

/**
 * Title: ResourceTreeServiceImpl<br>
 * Description: 资源树service接口实现<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author FanKY
 * @createDate Sep 9, 2011
 * @version $Revision: 77 $
 */
@Service(value = "cn.hs.module.modules.service.impl.ResourceTreeServiceImpl")
public class ResourceTreeServiceImpl extends TreeTemplate implements IResourceTreeService {

	@Autowired
	private IResourceTreeDao resourceTreeDao;

	/**
	 * 查询下一级节点集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 14, 2011
	 */
	@Override
	protected List<Resource> findNextNodeList(BaseTreeCondition condition) throws Exception {
		return resourceTreeDao.listResource((ResourceTreeCondition) condition);
	}

	/**
	 * 查询是否为叶子节点
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 14, 2011
	 */
	@Override
	protected boolean isLeapNode(BaseTreeCondition condition) throws Exception {
		return false;
	}

	/**
	 * 列表查询所需参数
	 * 
	 * @param treeNodeList
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 14, 2011
	 */
	@Override
	protected List<JsonTreeBean> objListToJsonList(List<?> treeNodeList) throws Exception {
		List<JsonTreeBean> resultList = new ArrayList<JsonTreeBean>();
		for (Object obj : treeNodeList) {
			Resource resource = (Resource) obj;
			JsonTreeBean jtBean = new JsonTreeBean();
			jtBean.setId(resource.getResourceID().toString());
			jtBean.setText(resource.getResourceName());
			if (Resource.IS_CONTAIN_LEAPNODE_Y.equals(resource.getIsContainLeapnode())) {
				jtBean.setLeaf(JsonTreeBean.IS_NOT_LEAP_NODE_EXT);
				jtBean.setState(JsonTreeBean.IS_NOT_LEAP_NODE_UI);
			} else {
				jtBean.setLeaf(JsonTreeBean.IS_LEAP_NODE_EXT);
				jtBean.setState(JsonTreeBean.IS_LEAP_NODE_UI);
			}
			// 带小箭头
			jtBean.setExpandable(true);
			resultList.add(jtBean);
		}
		return resultList;
	}

	/**
	 * 查询树集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 14, 2011
	 */
	@Override
	public List<JsonTreeBean> doProcess(BaseTreeCondition condition) throws Exception {
		return process(condition);
	}

	/**
	 * 根据父级ID查询下一级节点集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 14, 2011
	 */
	@Override
	public List<Resource> listNextNodeList(ResourceTreeCondition condition) throws Exception {
		return resourceTreeDao.listNextNodeList((ResourceTreeCondition) condition);
	}

}
