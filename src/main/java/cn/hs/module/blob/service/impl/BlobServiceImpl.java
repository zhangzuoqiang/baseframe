/*
 * $Log: BlobServiceImpl.java,v $
 * Revision 1.1  2012/05/23 09:27:52  guor
 * 初次提交
 *
 */
package cn.hs.module.blob.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hs.module.blob.dao.IBlobDao;
import cn.hs.module.blob.domain.BaseBlob;
import cn.hs.module.blob.domain.BlobCondition;
import cn.hs.module.blob.service.IBlobService;

/**
 * Title: BlobServiceImpl<br>
 * Description:BLOB大字段Service实现 <br>
 * Company: GOLDGOV<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author YaoSC
 * @createDate Sep 26, 2011
 * @version $Revision: 11 $
 */
@Service(value = "cn.hs.module.blob.service.impl.BlobServiceImpl")
public class BlobServiceImpl implements IBlobService {

	@Autowired
	private IBlobDao blobDao;

	/**
	 * 新增图片
	 * 
	 * @param blob
	 * @throws Exception
	 * @author YaoSC
	 * @date Sep 26, 2011
	 */
	@Override
	public void addBlob(BaseBlob blob) throws Exception {
		blobDao.addBlob(blob);
		// baseDao.addEntity(blob);
	}

	/**
	 * 根据主键查询图片
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author YaoSC
	 * @date Sep 26, 2011
	 */
	@Override
	public BaseBlob getBlob(BlobCondition condition) throws Exception {
		return blobDao.getBlob(condition);
	}

	/**
	 * 图片列表
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author YaoSC
	 * @date Sep 26, 2011
	 */
	@Override
	public List<BaseBlob> getBlobList(BlobCondition condition) throws Exception {
		return null;
	}

	/**
	 * 更新图片
	 * 
	 * @param blob
	 * @throws Exception
	 * @author YaoSC
	 * @date Sep 26, 2011
	 */
	@Override
	public void updateBlob(BaseBlob blob) throws Exception {
		blobDao.updateBlob(blob);
	}

	@Override
	public void deleteBlobById(String blobId) throws Exception {
		blobDao.deleteBlobById(blobId);
	}
}
