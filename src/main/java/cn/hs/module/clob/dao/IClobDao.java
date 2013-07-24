/*
 * $Log: IBlobDao.java,v $
 * Revision 1.1  2012/05/23 09:27:52  guor
 * 初次提交
 *
 */
package cn.hs.module.clob.dao;

import cn.hs.module.clob.domain.BaseClob;
import cn.hs.module.clob.domain.ClobCondition;

/**
 * Title: IClobDao<br>
 * Description:Blob类型文件Dao <br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author XiangBin
 * @createDate Aug 3, 2012
 * @version $Revision:$
 */
public interface IClobDao {
	/**
	 * 添加clob字段的内容
	 * 
	 * @param clob
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @date Aug 3, 2012
	 */
	public String addClob(BaseClob clob) throws Exception;

	/**
	 * 根据条件获取clob字段
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @date Aug 3, 2012
	 */
	public BaseClob getClob(ClobCondition condition) throws Exception;

	/**
	 * 更新clob字段的内容
	 * 
	 * @param clob
	 * @throws Exception
	 * @author XiangBin
	 * @date Aug 3, 2012
	 */
	public void updateClob(BaseClob clob) throws Exception;

}
