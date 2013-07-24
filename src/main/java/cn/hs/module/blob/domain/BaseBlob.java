/*
 * $Log: BaseBlob.java,v $
 * Revision 1.1  2012/05/23 09:27:52  guor
 * 初次提交
 *
 */
package cn.hs.module.blob.domain;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Title: Blob<br>
 * Description: BLOB大字段<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author ZhaoQ
 * @createDate Sep 14, 2011
 * @version $Revision: 11 $
 */
@Entity
@Table(name = "BASE_BLOB")
public class BaseBlob implements Serializable {

	private static final long serialVersionUID = 1L;

	private String blobID;// 主键
	private String attName;// 附件名称
	private String attExt;// 附件扩展名
	private Blob attBlob;// 附件内容

	@Id
	@Column(name = "BLOB_ID")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	public String getBlobID() {
		return blobID;
	}

	public void setBlobID(String blobID) {
		this.blobID = blobID;
	}

	@Column(name = "ATT_NAME")
	public String getAttName() {
		return attName;
	}

	public void setAttName(String attName) {
		this.attName = attName;
	}

	@Column(name = "ATT_EXT")
	public String getAttExt() {
		return attExt;
	}

	public void setAttExt(String attExt) {
		this.attExt = attExt;
	}

	@Column(name = "ATT_BLOB")
	public Blob getAttBlob() {
		return attBlob;
	}

	public void setAttBlob(Blob attBlob) {
		this.attBlob = attBlob;
	}

}
