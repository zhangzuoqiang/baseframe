/*
 * $Log: DndTreeTemplate.java,v $
 * Revision 1.1  2012/05/23 09:27:49  guor
 * 锟斤拷锟斤拷锟结交
 *
 */
package cn.hs.core.servicetemplate.treetemplate;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import cn.hs.core.basedao.condition.BaseTreeCondition;
import cn.hs.core.log4j.ILog4jManager;
import cn.hs.core.servicetemplate.treetemplate.jsonbean.JsonTreeBean;
import cn.hs.core.servicetemplate.treetemplate.jsonbean.jsonnodeparameters.UpdateOrderParameter;

/**
 * Title: PageAction<br>
 * Description: 锟斤拷拽锟斤拷锟轿结构模锟斤拷<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 2, 2011
 * @version $Revision: 1.1 $
 */
public abstract class DndTreeTemplate extends TreeTemplate {
	// 锟斤拷始锟斤拷锟斤拷志锟接匡拷
	@Autowired
	private ILog4jManager log4jManager;

	/**
	 * 执锟斤拷锟斤拷拽锟斤拷锟?
	 * 
	 * @param condition
	 * @throws Exception
	 * @author WangWB
	 * @date Nov 10, 2011
	 */
	public final void dndProcess(BaseTreeCondition condition) throws Exception {
		if (JsonTreeBean.TREE_TYPE_DRAG.equals(condition.getTreeType())) {
			if (condition.getTreeNodeID() != null && !"".equals(condition.getTreeNodeID()) && condition.getParentNodeID() != null && !"".equals(condition.getParentNodeID()) && condition.getChildNodeIds() != null && !"".equals(condition.getChildNodeIds())) {
				// 锟斤拷莸锟角帮拷诘锟絀D锟斤拷锟铰碉拷前锟节碉拷锟絧arentID锟斤拷锟斤到锟斤拷拽锟斤拷目录锟斤拷效锟斤拷
				updateTreeParentID(condition);
				// 锟斤拷装锟斤拷锟铰诧拷锟斤拷锟斤拷锟矫碉拷condition锟斤拷
				condition.setUpdateOrderParameter(buildUpdateOrderParameter(condition.getChildNodeIds()));
				// 锟斤拷锟斤拷咏诘锟絀D锟斤拷锟较ｏ拷锟斤拷锟斤拷锟斤拷拽锟节点到指锟斤拷目录锟襟，革拷锟铰革拷目录锟斤拷锟斤拷锟叫节碉拷锟斤拷锟斤拷锟叫э拷锟?
				updateTreeOrderNum(condition);
			} else {
				log4jManager.errorCustomLog(getClass().getName(), "process", "锟斤拷前锟节碉拷ID锟斤拷前锟节碉拷parentID为锟秸伙拷锟接节碉拷ID锟斤拷锟斤拷为锟斤拷");
			}
		} else {
			log4jManager.errorCustomLog(getClass().getName(), "process", "锟斤拷锟斤拷锟酵诧拷匹锟斤拷");
		}
	}

	/**
	 * 锟斤拷装锟斤拷锟铰诧拷锟斤拷
	 * 
	 * @param childNodeIds
	 * @return
	 * @author WangWB
	 * @date Nov 10, 2011
	 */
	private List<UpdateOrderParameter> buildUpdateOrderParameter(String childNodeIds) {
		List<UpdateOrderParameter> result = new ArrayList<UpdateOrderParameter>();
		String[] osp = childNodeIds.split("%osp%");
		for (int i = 0; i < osp.length; i++) {
			if (osp[i] != null && !"".equals(osp[i])) {
				UpdateOrderParameter updateOrderParameter = new UpdateOrderParameter();
				String[] vsp = osp[i].split("%vsp%");
				updateOrderParameter.setUpObjID(vsp[0]);
				updateOrderParameter.setUpOrderNumValue(vsp[2]);
				result.add(updateOrderParameter);
			}
		}
		return result;
	}

	/**
	 * 锟斤拷锟铰革拷锟节碉拷ID锟斤拷锟斤拷锟斤拷锟斤拷拽锟斤拷使锟斤拷时锟借复写锟剿凤拷锟斤拷
	 * 
	 * @param condition
	 * @throws Exception
	 * @author WangWB
	 * @date Nov 10, 2011
	 */
	protected abstract void updateTreeParentID(BaseTreeCondition condition) throws Exception;

	/**
	 * 锟斤拷锟斤拷一锟斤拷parent锟铰碉拷锟斤拷锟斤拷child锟节碉拷锟斤拷锟斤拷锟斤拷拽锟斤拷使锟斤拷时锟借复写锟剿凤拷锟斤拷
	 * 
	 * @param condition
	 * @throws Exception
	 * @author WangWB
	 * @date Nov 10, 2011
	 */
	protected abstract void updateTreeOrderNum(BaseTreeCondition condition) throws Exception;
}
