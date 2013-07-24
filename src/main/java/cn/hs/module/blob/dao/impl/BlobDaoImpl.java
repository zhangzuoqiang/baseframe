/*
 * $Log: BlobDaoImpl.java,v $
 * Revision 1.1  2012/05/23 09:27:52  guor
 * 初次提交
 *
 */
package cn.hs.module.blob.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.hs.core.basedao.base.IBaseDao;
import cn.hs.module.blob.dao.IBlobDao;
import cn.hs.module.blob.domain.BaseBlob;
import cn.hs.module.blob.domain.BlobCondition;

/**
 * Title: BlobDaoImpl<br>
 * Description:上传Blob类型文件Dao实现 <br>
 * Company: GOLDGOV<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author YaoSC
 * @createDate Sep 28, 2011
 * @version $Revision: 11 $
 */
@Repository(value = "cn.hs.module.blob.dao.impl.BlobDaoImpl")
public class BlobDaoImpl implements IBlobDao {

	// 初始化baseDao
	@Autowired
	private IBaseDao<BaseBlob> baseDao;

	/**
	 * 上传图片
	 * 
	 * @param prize
	 * @throws Exception
	 * @author YaoSC
	 * @date Sep 5, 2011
	 */
	@Override
	public void addBlob(BaseBlob blob) throws Exception {

		// Session session = baseDao.getHibernateSession();
		// session.save(blob);
		baseDao.addEntity(blob);
		// tx.commit();
	}

	/**
	 * 通过ID查询上传图片
	 * 
	 * @param condition
	 * @return
	 * @author YaoSC
	 * @date Sep 21, 2011
	 */
	@Override
	public BaseBlob getBlob(BlobCondition condition) throws Exception {
		return (BaseBlob) baseDao.findObject(BaseBlob.class, condition.getBlobId());
	}

	/**
	 * 更新上传图片
	 * 
	 * @param prize
	 * @throws Exception
	 * @author YaoSC
	 * @date Sep 5, 2011
	 */
	@Override
	public void updateBlob(BaseBlob blob) throws Exception {
		baseDao.updateEntityByPK(blob);
		// baseDao.addEntity(prize);
		// Session session = baseDao.getHibernateSession();
		// Transaction tx = session.beginTransaction();
		// session.update(blob);
		// tx.commit();
	}

	public void deleteBlobById(String blobId) throws Exception {
		BaseBlob blob = new BaseBlob();
		blob.setBlobID(blobId);
		baseDao.deleteEntityByPK(blob);
	}
}
