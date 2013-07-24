/*
 * $Log: SystemParameter.java,v $
 * Revision 1.1  2012/05/25 06:07:21  ronglt
 * 增加系统参数缓存
 *
 * Revision 1.1  2012/04/13 08:41:50  guor
 * *** empty log message ***
 *
 */
package cn.hs.module.systemparameter.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Title: SystemParameter<br>
 * Description: 系统参数实体对象<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author MengW
 * @createDate 2011-10-10
 * @version $Revision: 11 $
 */
@Entity
@Table(name = "BASE_SYSTEM_PARAMETER")
public class SystemParameter implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String PROPERTY_STARTWITH = "SysPara";// 系统参数参数名单词起始

	private String systemParameterID;// 系统参数ID

	private String systemParameterName;// 系统参数名

	private String systemParameterValue;// 系统参数值

	@Id
	@Column(name = "SYSTEM_PARAMETER_ID")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	public String getSystemParameterID() {
		return systemParameterID;
	}

	public void setSystemParameterID(String systemParameterID) {
		this.systemParameterID = systemParameterID;
	}

	@Column(name = "SYSTEM_PARAMETER_NAME")
	public String getSystemParameterName() {
		return systemParameterName;
	}

	public void setSystemParameterName(String systemParameterName) {
		this.systemParameterName = systemParameterName;
	}

	@Column(name = "SYSTEM_PARAMETER_VALUE")
	public String getSystemParameterValue() {
		return systemParameterValue;
	}

	public void setSystemParameterValue(String systemParameterValue) {
		this.systemParameterValue = systemParameterValue;
	}

	public String toString() {
		String string = "系统参数ID：" + (this.getSystemParameterID() == null ? "" : this.getSystemParameterID()) + "\r\n" + "系统参数名称：" + (this.getSystemParameterName() == null ? "" : this.getSystemParameterName()) + "\r\n" + "系统参数编码：" + (this.getSystemParameterValue() == null ? "" : this.getSystemParameterValue()) + "\r\n";
		return string;
	}

}
