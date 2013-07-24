package cn.hs.commons.utils;

import java.util.List;

import cn.hs.core.page.commond.AdvancedFiltersBean;

/**
 * Title: AdvancedFilterTool<br>
 * Description: 高级筛选工具类<br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author XiangBin
 * @createDate Sep 5, 2012
 * @version $Revision:$
 */
public class AdvancedFilterTool {

	/**
	 * 拼装高级筛选的SQL语句
	 * @param request
	 * @param advancedFiltersCommond
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @date Sep 5, 2012
	 */
	public static String makeAdvancedFiltersSQL(List<AdvancedFiltersBean> list){
		StringBuffer sql = new StringBuffer();
		if(list != null && list.size() > 0 ){
			sql.append(" and (");
			for (AdvancedFiltersBean advancedFilters : list) {
				sql.append(advancedFilters.getOperating()+" ");
				sql.append(""+advancedFilters.getResume()+" ");
				sql.append(advancedFilters.getLogical()+" ");
				
				if(advancedFilters.getSearchFilter().equals("male")){
					advancedFilters.setSearchFilter("1");
				}
				if(advancedFilters.getSearchFilter().equals("female")){
					advancedFilters.setSearchFilter("2");
				}
				
				if(advancedFilters.getLogical().equals("like")){
					sql.append("'%"+advancedFilters.getSearchFilter()+"%'");
				}else{
					
					sql.append("'"+advancedFilters.getSearchFilter()+"' ");
				}
			}
			sql.append(") ");
		}
		return sql.toString();
	}
}
