/*
 * $Log: BlobDaoImpl.java,v $
 * Revision 1.1  2012/05/23 09:27:52  guor
 * 初次提交
 *
 */
package cn.hs.module.clob.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.hs.core.basedao.base.IBaseDao;
import cn.hs.module.clob.dao.IClobDao;
import cn.hs.module.clob.domain.BaseClob;
import cn.hs.module.clob.domain.ClobCondition;

/**
 * Title: ClobDaoImpl<br>
 * Description: Clob类型文件Dao实现<br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author XiangBin
 * @createDate Aug 3, 2012
 * @version $Revision:$
 */
@Repository(value = "cn.hs.module.clob.dao.impl.ClobDaoImpl")
public class ClobDaoImpl implements IClobDao {

	// 初始化baseDao
	@Autowired
	private IBaseDao<BaseClob> baseDao;

	/**
	 * 添加clob字段的内容
	 * 
	 * @param clob
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @date Aug 3, 2012
	 */
	@Override
	public String addClob(BaseClob clob) throws Exception {
		baseDao.addEntity(clob);
		return clob.getClobID();
	}

	/**
	 * 根据条件获取clob字段
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @date Aug 3, 2012
	 */
	@Override
	public BaseClob getClob(ClobCondition condition) throws Exception {
		return (BaseClob) baseDao.findObject(BaseClob.class, condition.getClobId());
	}

	/**
	 * 更新clob字段的内容
	 * 
	 * @param clob
	 * @throws Exception
	 * @author XiangBin
	 * @date Aug 3, 2012
	 */
	@Override
	public void updateClob(BaseClob clob) throws Exception {
		baseDao.updateEntityByPK(clob);
	}
}
