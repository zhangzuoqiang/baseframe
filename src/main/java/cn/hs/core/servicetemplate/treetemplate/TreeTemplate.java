/*
 * $Log: TreeTemplate.java,v $
 * Revision 1.1  2012/05/23 09:27:49  guor
 * 初次提交
 *
 */
package cn.hs.core.servicetemplate.treetemplate;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import cn.hs.commons.utils.PropertyUtil;
import cn.hs.core.basedao.condition.BaseTreeCondition;
import cn.hs.core.log4j.ILog4jManager;
import cn.hs.core.servicetemplate.treetemplate.jsonbean.JsonTreeBean;

/**
 * Title: PageAction<br>
 * Description: 树形结构模板<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 2, 2011
 * @version $Revision: 1.1 $
 */
public abstract class TreeTemplate {
	// 初始化日志接口
	@Autowired
	private ILog4jManager log4jManager;

	/**
	 * 执行查询过程
	 * 
	 * @param condition
	 *            controller收集的查询参数
	 * @return
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 3, 2011
	 */
	public final List<JsonTreeBean> process(BaseTreeCondition condition) throws Exception {
		if (!PropertyUtil.objectNotEmpty(condition.getNode())) {
			// 若页面中传过来的treeNodeID为空，即第一次展现树形结构页面，则使用根节点的默认parentID值
			condition.setNode(JsonTreeBean.ROOT_NODE_PARENT_ID);
		}
		// 查询下级节点集合
		List<?> treeNodeList = findNextNodeList(condition);
		// 清除condition中的parameterList数据
		condition.getParameterList().clear();
		// JsonTreeBean映射
		List<JsonTreeBean> jsonTreeNodeList = objListToJsonList(treeNodeList);
		if (jsonTreeNodeList != null && !jsonTreeNodeList.isEmpty()) {
			// 遍历树节点集合，组装JsonTreeBean剩余属性
			buildJsonTreeBeanByList(jsonTreeNodeList, condition);
		} else {
			log4jManager.errorCustomLog(getClass().getName(), "process", "查询出来的下级节点集合为空");
		}
		return jsonTreeNodeList;
	}

	/**
	 * 遍历树节点集合，查询下一级节点中哪些是叶子节点
	 * 
	 * @param treeNodeList
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 12, 2011
	 */
	private void buildJsonTreeBeanByList(List<JsonTreeBean> treeNodeList, BaseTreeCondition condition) throws Exception {
		if (JsonTreeBean.TREE_TYPE_SELECT.equals(condition.getTreeType())) {
			// 若为下拉复选树则进入下面逻辑
			if (condition.getRootID() != null && !"".equals(condition.getRootID())) {
				// 若有根节点，则为多级树
				JsonTreeBean json = null;
				// 不需要查询每一个节点是否为叶子节点
				for (JsonTreeBean jtb : treeNodeList) {
					// 提取根节点对象
					if (condition.getRootID().equals(jtb.getId())) {
						// 递归遍历所有节点，找出child集合
						json = buildChildList(treeNodeList, jtb, condition);
						json.setLeaf(JsonTreeBean.IS_LEAP_NODE_EXT);
						json.setState(JsonTreeBean.IS_LEAP_NODE_UI);
						break;
					}
				}
				treeNodeList.clear();
				treeNodeList.add(json);
			} else {
				// 若没有根节点，则为一级树
				for (JsonTreeBean jtb : treeNodeList) {
					// 查询节点的选中状态
					if (condition.getDefaultCheckedValue() != null && !"".equals(condition.getDefaultCheckedValue())) {
						jtb.setChecked(findCheckedState(condition, jtb));
						jtb.setLeaf(JsonTreeBean.IS_NOT_LEAP_NODE_EXT);
						jtb.setState(JsonTreeBean.IS_NOT_LEAP_NODE_UI);
					}
				}
			}
		} else {
			// 默认树形结构逻辑
			if (condition.isFindLeapNode()) {
				// 查询每一个节点是否为叶子节点
				for (JsonTreeBean jtb : treeNodeList) {
					// 设置当前节点ID
					condition.setNode(jtb.getId());
					// 查询此节点是否为叶子节点
					if (isLeapNode(condition)) {
						jtb.setLeaf(JsonTreeBean.IS_LEAP_NODE_EXT);
						jtb.setState(JsonTreeBean.IS_LEAP_NODE_UI);
						jtb.setExpanded(true);
					} else {
						jtb.setLeaf(JsonTreeBean.IS_NOT_LEAP_NODE_EXT);
						jtb.setState(JsonTreeBean.IS_NOT_LEAP_NODE_UI);
					}
					// 带小箭头
					jtb.setExpandable(true);
				}
			}
		}
	}

	/**
	 * 遍历每一个节点，对比出当前节点下的所有子节点，下拉复选树使用
	 * 
	 * @param treeNodeList
	 * @param eachBean
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author WangWB
	 * @date Nov 18, 2011
	 */
	private JsonTreeBean buildChildList(List<JsonTreeBean> treeNodeList, JsonTreeBean eachBean, BaseTreeCondition condition) throws Exception {
		// 设置当前节点ID
		// condition.setTreeNodeID(eachBean.getId());
		condition.setNode(eachBean.getId());
		if (condition.isFindLeapNode()) {
			// 查询此节点是否为叶子节点
			if (isLeapNode(condition)) {
				eachBean.setLeaf(JsonTreeBean.IS_LEAP_NODE_EXT);
				eachBean.setState(JsonTreeBean.IS_LEAP_NODE_UI);
			} else {
				eachBean.setLeaf(JsonTreeBean.IS_NOT_LEAP_NODE_EXT);
				eachBean.setState(JsonTreeBean.IS_NOT_LEAP_NODE_UI);
			}
		}
		// 查询节点的选中状态
		eachBean.setChecked(findCheckedState(condition, eachBean));
		if (eachBean.isLeaf()) {
			// 若当前节点有子节点将继续递归逻辑
			List<JsonTreeBean> result = new ArrayList<JsonTreeBean>();
			for (JsonTreeBean jtb : treeNodeList) {
				if (jtb.getParentID() != null && !"".equals(jtb.getParentID())) {
					if (eachBean.getId().equals(jtb.getParentID())) {
						result.add(buildChildList(treeNodeList, jtb, condition));
					}
				}
			}
			if (!result.isEmpty()) {
				eachBean.setChildren(result);
			}
		}
		return eachBean;
	}

	/**
	 * 查询下一级节点集合，需要查询出节点ID，节点名称，若为下拉复选树，需要查询出父节点ID
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 2, 2011
	 */
	protected abstract List<?> findNextNodeList(BaseTreeCondition condition) throws Exception;

	/**
	 * 查询节点是否为叶子节点
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 12, 2011
	 */
	protected abstract boolean isLeapNode(BaseTreeCondition condition) throws Exception;

	/**
	 * JsonTreeBean映射
	 * 
	 * @param treeNodeList
	 * @return
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 16, 2011
	 */
	protected abstract List<JsonTreeBean> objListToJsonList(List<?> treeNodeList) throws Exception;

	/**
	 * 查询每一个节点的选中状态
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author Liy
	 * @date 2011-11-8
	 */
	private Boolean findCheckedState(BaseTreeCondition condition, JsonTreeBean eachBean) throws Exception {
		Boolean result = false;
		if (condition.getDefaultCheckedValue() != null && !"".equals(condition.getDefaultCheckedValue())) {
			if (condition.getDefaultCheckedValue().indexOf("," + eachBean.getId() + ",") != -1) {
				// 选中
				result = true;
			}
		} else {
			log4jManager.errorCustomLog(getClass().getName(), "findCheckedState", "默认选中值为空");
		}
		return result;
	}
}
