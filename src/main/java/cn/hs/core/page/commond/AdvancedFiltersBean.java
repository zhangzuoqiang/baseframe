package cn.hs.core.page.commond;
/**
 * Title: HighSearch<br>
 * Description:高级筛选的域对象 <br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author XiangBin
 * @createDate Sep 4, 2012
 * @version $Revision:$
 */
public class AdvancedFiltersBean {
	private String operating;//选择操作符号 并且and 或者or
	private String logical;// 选择逻辑符号 大于> 小于< 等于= 包含in
	private String resume;//简历项
	private String searchFilter;//筛选条件
	
	public String getOperating() {
		return operating;
	}
	public void setOperating(String operating) {
		this.operating = operating;
	}
	public String getLogical() {
		return logical;
	}
	public void setLogical(String logical) {
		this.logical = logical;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public String getSearchFilter() {
		return searchFilter;
	}
	public void setSearchFilter(String searchFilter) {
		this.searchFilter = searchFilter;
	}
}