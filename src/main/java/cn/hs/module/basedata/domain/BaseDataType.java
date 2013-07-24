/*
 * $Log: BaseDataType.java,v $
 * Revision 1.1  2012/05/23 09:27:51  guor
 * 初次提交
 *
 */
package cn.hs.module.basedata.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Title: BaseDataType<br>
 * Description: 基础数据类别<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 22, 2011
 * @version $Revision: 72 $
 */
@Entity
@Table(name = "BASE_BASEDATA_TYPE")
public class BaseDataType implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 启用
	 */
	public final static Integer IS_ACTIVE_Y = new Integer(1);
	/**
	 * 作废
	 */
	public final static Integer IS_ACTIVE_N = new Integer(2);

	/**
	 * 数据字典ID
	 */
	public final static String DATA_DICTIONARY_ID = "-1";

	/**
	 * 公共字典ID
	 */
	public final static String PUBLIC_DICTIONARY_ID = "2";

	/**
	 * 本系统字典ID
	 */
	public final static String SYSTEM_DICTIONARY_ID = "3";

	private String typeID;// 主键

	private String typeCode;// 类别代码

	private String typeName;// 类别名称

	private String parentID;// 父类别ID

	private String treepath;// 类别treepath

	private Integer activeState;// 活动状态 1 - 启用 2 - 作废

	private String orderNum;// 排序字段

	@Id
	@Column(name = "TYPE_ID")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	public String getTypeID() {
		return typeID;
	}

	public void setTypeID(String typeID) {
		this.typeID = typeID;
	}

	@Column(name = "TYPE_CODE")
	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	@Column(name = "TYPE_NAME")
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "ACTIVE_STATE")
	public Integer getActiveState() {
		return activeState;
	}

	public void setActiveState(Integer activeState) {
		this.activeState = activeState;
	}

	@Column(name = "PARENT_ID")
	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	@Column(name = "TREEPATH")
	public String getTreepath() {
		return treepath;
	}

	public void setTreepath(String treepath) {
		this.treepath = treepath;
	}

	@Column(name = "ORDER_NUM")
	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
}
