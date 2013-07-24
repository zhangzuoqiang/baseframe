package cn.hs.module.clob.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hs.module.clob.dao.IClobDao;
import cn.hs.module.clob.domain.BaseClob;
import cn.hs.module.clob.domain.ClobCondition;
import cn.hs.module.clob.service.IClobService;

/**
 * Title: ClobServiceImpl<br>
 * Description: CLOB大字段Service实现<br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author XiangBin
 * @createDate Aug 3, 2012
 * @version $Revision:$
 */
@Service(value = "cn.hs.module.clob.service.impl.ClobServiceImpl")
public class ClobServiceImpl implements IClobService {

	@Autowired
	private IClobDao clobDao;

	/**
	 * 添加Clob类型的内容
	 * 
	 * @param clob
	 * @throws Exception
	 * @author XiangBin
	 * @date Aug 3, 2012
	 */
	@Override
	public String addClob(BaseClob clob) throws Exception {
		return clobDao.addClob(clob);
	}

	/**
	 * 根据查询条件 获取clob信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @date Aug 3, 2012
	 */
	@Override
	public BaseClob getClob(ClobCondition condition) throws Exception {
		return clobDao.getClob(condition);
	}

	/**
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author YaoSC
	 * @date Sep 26, 2011
	 */
	@Override
	public List<BaseClob> getClobList(ClobCondition condition) throws Exception {
		return null;
	}

	/**
	 * 更新Clob内容
	 * 
	 * @param clob
	 * @throws Exception
	 * @author XiangBin
	 * @date Aug 3, 2012
	 */
	@Override
	public void updateClob(BaseClob clob) throws Exception {
		clobDao.updateClob(clob);
	}
}
