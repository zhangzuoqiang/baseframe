package cn.hs.module.blob.ui.commond;

import cn.hs.core.page.commond.BasePageCommond;

public class BaseBlobValidCommond extends BasePageCommond {
	private static final long serialVersionUID = 1L;
	private String blobID;// 主键
	private String attName;// 附件名称
	private String attExt;// 附件扩展名

	public String getBlobID() {
		return blobID;
	}

	public void setBlobID(String blobID) {
		this.blobID = blobID;
	}

	public String getAttName() {
		return attName;
	}

	public void setAttName(String attName) {
		this.attName = attName;
	}

	public String getAttExt() {
		return attExt;
	}

	public void setAttExt(String attExt) {
		this.attExt = attExt;
	}
}
