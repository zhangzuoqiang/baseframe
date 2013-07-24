package cn.hs.module.clob.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Title: BaseClob<br>
 * Description:CLOB大字段 <br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author XiangBin
 * @createDate Aug 3, 2012
 * @version $Revision:$
 */
@Entity
@Table(name = "BASE_CLOB")
public class BaseClob implements Serializable {

	private static final long serialVersionUID = 1L;

	private String clobID;// 主键
	// modify by HuangS at 2012-09-04 修改Clob类型为String
	private String contentClob;// 附件内容

	@Id
	@Column(name = "CLOB_ID")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	public String getClobID() {
		return clobID;
	}

	public void setClobID(String clobID) {
		this.clobID = clobID;
	}

	@Column(name = "CLOB_CONTENT")
	public String getContentClob() {
		return contentClob;
	}

	public void setContentClob(String contentClob) {
		this.contentClob = contentClob;
	}
}
