/*
 * $Log: IBlobService.java,v $
 * Revision 1.1  2012/05/23 09:27:52  guor
 * 初次提交
 *
 */
package cn.hs.module.clob.service;

import java.util.List;

import cn.hs.module.clob.domain.BaseClob;
import cn.hs.module.clob.domain.ClobCondition;

/**
 * 
 * Title: IClobService<br>
 * Description: CLOB大字段Service接口<br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author XiangBin
 * @createDate Aug 3, 2012
 * @version $Revision:$
 */
public interface IClobService {
	/**
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @date Aug 3, 2012
	 */
	public List<BaseClob> getClobList(ClobCondition condition) throws Exception;

	/**
	 * 添加Clob类型的内容
	 * 
	 * @param clob
	 * @throws Exception
	 * @author XiangBin
	 * @date Aug 3, 2012
	 */
	public String addClob(BaseClob clob) throws Exception;

	/**
	 * 根据查询条件 获取clob信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @date Aug 3, 2012
	 */
	public BaseClob getClob(ClobCondition condition) throws Exception;

	/**
	 * 更新clob的信息
	 * 
	 * @param clob
	 * @throws Exception
	 * @author XiangBin
	 * @date Aug 3, 2012
	 */
	public void updateClob(BaseClob clob) throws Exception;

}
