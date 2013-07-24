package cn.hs.module.basedata.domain.jsonbean;

import java.io.Serializable;

/**
 * 
 * Title: BaseDataJsonBean<br>
 * Description: 基础数据页面传递json类<br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Jul 20, 2012
 * @version $Revision:$
 */
public class BaseDataJsonBean implements Serializable {

	/**
	 * @author HuangS
	 * @date Jul 20, 2012
	 */
	private static final long serialVersionUID = 1L;

	private String dataID;// 主键

	private String dataCode;// 数据编码

	private String dataName;// 数据名称

	private Integer activeState;// 活动状态 1 - 启用 2 - 作废

	private Integer orderNum;// 排序编号

	private String baseDataTypeID;// 数据类别ID

	private String baseDataTypeName;// 数据类别名称

	// private Date createTime;// 创建时间

	private String creatorID;// 创建人ID

	private String creatorLoginID;// 创建人loginID

	private String creatorName;// 创建人姓名

	public String getDataID() {
		return dataID;
	}

	public void setDataID(String dataID) {
		this.dataID = dataID;
	}

	public String getDataCode() {
		return dataCode;
	}

	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public Integer getActiveState() {
		return activeState;
	}

	public void setActiveState(Integer activeState) {
		this.activeState = activeState;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getBaseDataTypeID() {
		return baseDataTypeID;
	}

	public void setBaseDataTypeID(String baseDataTypeID) {
		this.baseDataTypeID = baseDataTypeID;
	}

	public String getBaseDataTypeName() {
		return baseDataTypeName;
	}

	public void setBaseDataTypeName(String baseDataTypeName) {
		this.baseDataTypeName = baseDataTypeName;
	}

	// public Date getCreateTime() {
	// return createTime;
	// }
	//
	// public void setCreateTime(Date createTime) {
	// this.createTime = createTime;
	// }

	public String getCreatorID() {
		return creatorID;
	}

	public void setCreatorID(String creatorID) {
		this.creatorID = creatorID;
	}

	public String getCreatorLoginID() {
		return creatorLoginID;
	}

	public void setCreatorLoginID(String creatorLoginID) {
		this.creatorLoginID = creatorLoginID;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

}
