/*
 * $Log: BaseDataValidCommond.java,v $
 * Revision 1.1  2012/05/23 09:27:52  guor
 * 初次提交
 *
 */
package cn.hs.module.basedata.ui.commond;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Title: BaseDataValidCommond<br>
 * Description: 基础数据数据验证commond<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author FanKY
 * @createDate Sep 7, 2011
 * @version $Revision: 73 $
 */
public class BaseDataValidCommond extends BaseDataCommond {
	private String dataID;// 基础数据ID

	@NotBlank(message = "数据编码不能为空")
	private String dataCode;// 数据编码

	@NotBlank(message = "数据名称不能为空")
	private String dataName;// 数据名称

	private Integer activeState;// 活动状态 1 - 启用 2 - 作废

	private Integer orderNum;// 排序编号

	private Long createTime;// 创建时间

	private String creatorID;// 创建人ID

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getCreatorID() {
		return creatorID;
	}

	public void setCreatorID(String creatorID) {
		this.creatorID = creatorID;
	}

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

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getActiveState() {
		return activeState;
	}

	public void setActiveState(Integer activeState) {
		this.activeState = activeState;
	}

}
