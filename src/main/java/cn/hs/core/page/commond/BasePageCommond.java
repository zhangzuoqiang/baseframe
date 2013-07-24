package cn.hs.core.page.commond;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.hs.core.page.PageBean;
import cn.hs.core.page.PageInfoBean;

/**
 * Title: BasePageBean<br>
 * Description: 页面翻页属性基类，需要翻页的页面，域对象需要继承此类<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 2, 2011
 * @version $Revision: 1.1 $
 */
public class BasePageCommond implements Serializable {

	/**
	 * @author HuangS
	 * @date Jul 20, 2012
	 */
	private static final long serialVersionUID = 1L;

	protected PageInfoBean pageBean = new PageInfoBean();

	/**
	 * 结果集
	 */
	private Collection<?> resultCollection;

	private JSONObject dataBean;// EXT 存放结果集

	private String sort;// 页面排序字段，取排序内容使用getSortInfo

	private String advancedFiltersInfo; //页面信息高级筛选字段，取排序使用getAdvancedFiltersList

	/**
	 * 设置当前页
	 * 
	 * @param page
	 * @author HuangS
	 * @date Jul 25, 2012
	 */
	public void setPage(int page) {
		pageBean.setCurrent(page);
	}

	/**
	 * 设置起始
	 * 
	 * @param start
	 * @author HuangS
	 * @date Jul 25, 2012
	 */
	public void setStart(int start) {
		pageBean.setStart(start);
	}

	/**
	 * 设置每页显示条数
	 * 
	 * @param limit
	 * @author HuangS
	 * @date Jul 25, 2012
	 */
	public void setLimit(int limit) {
		pageBean.setRowCount(limit);
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Collection<?> getResultCollection() {
		return resultCollection;
	}

	public void setResultCollection(Collection<?> resultCollection) {
		this.resultCollection = resultCollection;
	}

	public PageInfoBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageInfoBean pageBean) {
		this.pageBean = pageBean;
	}

	public int getCurrent() {
		return pageBean.getCurrent();
	}

	/**
	 * 页号,用于接收翻的当前页
	 * 
	 * @param current
	 */
	public void setCurrent(int current) {
		pageBean.setCurrent(current);
	}

	public int getPageCount() {
		return pageBean.getPageCount();
	}

	public int getCount() {
		return pageBean.getCount();
	}

	public int getPageListCount() {
		return pageBean.getPageListCount();
	}

	public int getPrevious() {
		return pageBean.getPrevious();
	}

	public int getNext() {
		return pageBean.getNext();
	}

	public List<PageBean> getPageNumList() {
		return (List<PageBean>) pageBean.getPageNumList();
	}

	public JSONObject getDataBean() {
		return dataBean;
	}

	public void setDataBean(JSONObject dataBean) {
		this.dataBean = dataBean;
	}

	public String getAdvancedFiltersInfo() {
		return advancedFiltersInfo;
	}

	public void setAdvancedFiltersInfo(String advancedFiltersInfo) {
		this.advancedFiltersInfo = advancedFiltersInfo;
	}

}
