/*
 * $Log: BlobCondition.java,v $
 * Revision 1.1  2012/05/23 09:27:52  guor
 * 初次提交
 *
 */
package cn.hs.module.blob.domain;

import cn.hs.core.basedao.condition.BaseCondition;

/**
 * Title: BlobCondition<br>
 * Description: 大字段condition<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author ZhaoQ
 * @createDate Sep 14, 2011
 * @version $Revision: 11 $
 */
public class BlobCondition extends BaseCondition {
	// 图片主键
	private String blobId;

	public String getBlobId() {
		return blobId;
	}

	public void setBlobId(String blobId) {
		this.blobId = blobId;
	}

}
