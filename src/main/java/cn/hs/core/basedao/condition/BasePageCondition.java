/*
 * $Log: BasePageCondition.java,v $
 * Revision 1.1  2012/05/23 09:27:47  guor
 * 初次提交
 *
 */
package cn.hs.core.basedao.condition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.map.ObjectMapper;

import cn.hs.core.page.commond.AdvancedFiltersBean;
import cn.hs.core.page.commond.SortInfoBean;

/**
 * Title: BasePageCondition<br>
 * Description: 查询带翻页列表时需要使用的condition<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 1, 2011
 * @version $Revision: 1.1 $
 */
public class BasePageCondition extends BaseCondition {
	/**
	 * 查询开始值
	 */
	public int start;
	/**
	 * 查询长度
	 */
	public int rows;
	/**
	 * 设置每页显示条数，默认为10条
	 */
	public int rowCount;
	/**
	 * EXT 页面排序
	 */
	private SortInfoBean sortInfo = null;
	
	/**
	 * 高级筛选
	 */
	private List<AdvancedFiltersBean> advancedFiltersList = null;

	public List<AdvancedFiltersBean> getAdvancedFiltersList() {
		return advancedFiltersList;
	}

	@SuppressWarnings("unchecked")
	public void setAdvancedFiltersInfo(String advancedFiltersInfo) {
		
		if (advancedFiltersInfo != null && !"".equals(advancedFiltersInfo)) {
			advancedFiltersList = new ArrayList<AdvancedFiltersBean>();
			
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				List<LinkedHashMap<String, Object>> list = objectMapper.readValue(advancedFiltersInfo, List.class);
				for (int i = 0; i < list.size(); i++) {
					AdvancedFiltersBean cpjb = new AdvancedFiltersBean();
					Map<String, Object> map = list.get(i);
					Set<String> set = map.keySet();
					for (Iterator<String> it = set.iterator(); it.hasNext();) {
						String key = it.next();
						BeanUtils.setProperty(cpjb, key, map.get(key));
					}
					advancedFiltersList.add(cpjb);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	/**
	 * 获取排序内容
	 * 
	 * @return
	 * @author HuangS
	 * @date Jul 20, 2012
	 */
	@SuppressWarnings("unchecked")
	public SortInfoBean getSortInfo() {
		return this.sortInfo;
	}

	/**
	 * 设置排序内容
	 * 
	 * @param sort
	 * @author HuangS
	 * @date Jul 25, 2012
	 */
	@SuppressWarnings("unchecked")
	public void setSort(String sort) {
		if (sort != null && !"".equals(sort)) {
			sortInfo = new SortInfoBean();
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				List<LinkedHashMap<String, Object>> list = objectMapper.readValue(sort, List.class);
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map = list.get(i);
					Set<String> set = map.keySet();
					for (Iterator<String> it = set.iterator(); it.hasNext();) {
						String key = it.next();
						BeanUtils.setProperty(sortInfo, key, map.get(key));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
