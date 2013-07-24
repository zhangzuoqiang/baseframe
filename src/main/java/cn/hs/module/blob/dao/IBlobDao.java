/*
 * $Log: IBlobDao.java,v $
 * Revision 1.1  2012/05/23 09:27:52  guor
 * 初次提交
 *
 */
package cn.hs.module.blob.dao;

import cn.hs.module.blob.domain.BaseBlob;
import cn.hs.module.blob.domain.BlobCondition;

/**
 * Title: IBlobDao<br>
 * Description: 上传Blob类型文件Dao<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author YaoSC
 * @createDate Sep 28, 2011
 * @version $Revision: 11 $
 */
public interface IBlobDao {
	/**
	 * 上传图片
	 * 
	 * @param blob
	 * @throws Exception
	 * @author LiuHQ
	 * @date 2011-11-7
	 */
	public void addBlob(BaseBlob blob) throws Exception;

	/**
	 * 通过ID查询上传图片
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHQ
	 * @date 2011-11-7
	 */
	public BaseBlob getBlob(BlobCondition condition) throws Exception;

	/**
	 * 更新上传图片
	 * 
	 * @param blob
	 * @throws Exception
	 * @author LiuHQ
	 * @date 2011-11-7
	 */
	public void updateBlob(BaseBlob blob) throws Exception;

	/**
	 * 根据id删除附件
	 * 
	 * @param blobId
	 * @throws Exception
	 */
	public void deleteBlobById(String blobId) throws Exception;

}
