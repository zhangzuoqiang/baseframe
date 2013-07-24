/*
 * $Log: BaseData.java,v $
 * Revision 1.1  2012/05/23 09:27:51  guor
 * 初次提交
 *
 */
package cn.hs.module.basedata.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Title: BaseData<br>
 * Description: 基础数据<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 22, 2011
 * @version $Revision: 72 $
 */
@Entity
@Table(name = "BASE_BASEDATA")
public class BaseData implements Serializable {

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
	 * 角色类型的基础数据类别编码 modify by HuangS at 2011-09-09
	 */
	public final static String BASE_DATA_TYPE_CODE_ROLE = "JSGLYLB";

	private String dataID;// 主键

	private String dataCode;// 数据编码

	private String dataName;// 数据名称

	private Integer activeState;// 活动状态 1 - 启用 2 - 作废

	private Integer orderNum;// 排序编号

	private BaseDataType baseDataType;// 所属数据类别

	private Long createTime;// 创建时间

	private String creator;// 创建人

	@Id
	@Column(name = "DATA_ID")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	public String getDataID() {
		return dataID;
	}

	public void setDataID(String dataID) {
		this.dataID = dataID;
	}

	@Column(name = "DATA_CODE")
	public String getDataCode() {
		return dataCode;
	}

	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}

	@Column(name = "DATA_NAME")
	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	@Column(name = "ACTIVE_STATE")
	public Integer getActiveState() {
		return activeState;
	}

	public void setActiveState(Integer activeState) {
		this.activeState = activeState;
	}

	@Column(name = "ORDER_NUM")
	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "TYPE_ID")
	public BaseDataType getBaseDataType() {
		return baseDataType;
	}

	public void setBaseDataType(BaseDataType baseDataType) {
		this.baseDataType = baseDataType;
	}

	@Column(name = "CREATE_TIME")
	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@JoinColumn(name = "CREATOR")
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * 记录日志使用
	 * 
	 * @return
	 * @author WangWB
	 * @date Oct 10, 2011
	 */
	@Override
	public String toString() {
		StringBuffer stb = new StringBuffer();
		stb.append("主键======>");
		stb.append(getDataID());
		stb.append("\n数据编码======>");
		stb.append(getDataCode());
		stb.append("\n数据名称======>");
		stb.append(getDataName());
		stb.append("\n活动状态======>");
		stb.append(getActiveState());
		stb.append("\n排序编号======>");
		stb.append(getOrderNum());
		return stb.toString();
	}
}
