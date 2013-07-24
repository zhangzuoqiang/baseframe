/*
 * $Log: IBlobService.java,v $
 * Revision 1.1  2012/05/23 09:27:52  guor
 * 初次提交
 *
 */
package cn.hs.module.blob.service;

import java.util.List;

import cn.hs.module.blob.domain.BaseBlob;
import cn.hs.module.blob.domain.BlobCondition;

/**
 * Title: IBolbService<br>
 * Description: BLOB大字段Service接口<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author ZhaoQ
 * @createDate Sep 14, 2011
 * @version $Revision: 11 $
 */
public interface IBlobService {
	/**
	 * 根据奖品id查询奖品缩略图集合
	 * 
	 * @param condition
	 * @throws Exception
	 * @author ZhaoQ
	 * @date Sep 14, 2011
	 */
	public List<BaseBlob> getBlobList(BlobCondition condition) throws Exception;

	/**
	 * 新增奖品缩略图
	 * 
	 * @param blob
	 * @throws Exception
	 * @author ZhaoQ
	 * @date Sep 14, 2011
	 */
	public void addBlob(BaseBlob blob) throws Exception;

	/**
	 * 根据缩略图id 获取信息
	 * 
	 * @param condition
	 * @throws Exception
	 * @author ZhaoQ
	 * @date Sep 14, 2011
	 */
	public BaseBlob getBlob(BlobCondition condition) throws Exception;

	/**
	 * 更新奖品缩略图
	 * 
	 * @param blob
	 * @throws Exception
	 * @author ZhaoQ
	 * @date Sep 14, 2011
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
