/*
 * $Log: PageTemplate.java,v $
 * Revision 1.1  2012/05/23 09:27:48  guor
 * 初次提交
 *
 */
package cn.hs.core.servicetemplate.pagetemplate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.core.basedao.condition.BasePageCondition;
import cn.hs.core.log4j.ILog4jManager;
import cn.hs.core.page.CalculatePage;
import cn.hs.core.page.PageInfoBean;
import cn.hs.core.page.commond.BasePageCommond;
import cn.hs.core.page.commond.JSONObject;

/**
 * Title: PageAction<br>
 * Description: 分页模板<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 2, 2011
 * @version $Revision: 1.1 $
 */
public abstract class PageTemplate {
	// 初始化日志接口
	@Autowired
	private ILog4jManager log4jManager;

	/**
	 * 执行查询过程
	 * 
	 * @param pageCommond
	 *            controller收集的page参数commond，要求自定义的commond必须继承BasePageCommond
	 * @param condition
	 *            controller收集的查询参数
	 * @return
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 3, 2011
	 */
	public final JSONObject process(BasePageCommond pageCommond, BaseCondition condition) throws Exception {
		List<?> pageResultList = null;
		JSONObject jsonDataBean = new JSONObject();
		BasePageCondition basePageCondition = (BasePageCondition) condition;
		// 获得BasePageCommond
		PageInfoBean pageInfoBean = pageCommond.getPageBean();
		// 若rowCount为0，则取默认值10条
		if (basePageCondition.getRowCount() != 0) {
			// 设置每页显示条数
			pageInfoBean.setRowCount(basePageCondition.getRowCount());
		}
		// 查询分页count
		Long pageCount = findCount(basePageCondition);
		// 设置count
		jsonDataBean.setTotalCount(pageCount);
		if (pageCount != null) {
			// 计算本次分页数据
			CalculatePage.calculate(pageInfoBean, pageCount.intValue());
			// 设置查询起点
			basePageCondition.setStart(pageInfoBean.getStart());
			// 设置查询长度
			basePageCondition.setRows(pageInfoBean.getRowCount());
			//
			basePageCondition.getParameterList().clear();
			// 查询分页数据
			pageResultList = findList(basePageCondition);
			// 结果集封装
			List<?> data = this.objListToJsonList(pageResultList);
			pageCommond.setResultCollection(pageResultList);
			jsonDataBean.setData(data);
			jsonDataBean.setSuccess(true);
			pageCommond.setDataBean(jsonDataBean);
		} else {
			jsonDataBean.setSuccess(false);
			log4jManager.debugCustomLog(getClass().getName(), "process", "查询分页count为空");
		}
		return jsonDataBean;
	}

	/**
	 * 查询列表count
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 2, 2011
	 */
	protected abstract Long findCount(BaseCondition condition) throws Exception;

	/**
	 * 查询列表list
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 2, 2011
	 */
	protected abstract List<?> findList(BaseCondition condition) throws Exception;

	/**
	 * 将查询出的实体类转为输出json的类型,如果需要转型则覆盖此方法
	 * 
	 * @param pageResultList
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Jul 20, 2012
	 */
	protected List<?> objListToJsonList(List<?> pageResultList) throws Exception {
		return pageResultList;
	}
}
