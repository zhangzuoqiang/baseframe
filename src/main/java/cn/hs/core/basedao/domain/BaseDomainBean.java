package cn.hs.core.basedao.domain;

import java.io.Serializable;

import javax.persistence.Column;

/**
 * 
 * Title: BaseDomainBean<br>
 * Description: 数据库实体基础bean<br>
 * 实现了活动状态、排序、最后修改时间字段<br>
 * 分别为：<br>
 * 活动状态：activeState ACTIVE_STATE<br>
 * 排序：orderNum ORDER_NUM<br>
 * 最后修改时间： lastUpdateTime LAST_UPDATE_TIME<br>
 * Company: HsS<br>
 * Copyright @ 2013 HsS .All rights reserved.<br>
 * @author HuangS
 * @createDate May 2, 2013
 * @version $Revision:$
 */
public abstract class BaseDomainBean implements Serializable {

	/**
	 * 活动状态：启用
	 */
	public static final Integer IS_ACTIVE_Y = 1;

	/**
	 * 活动状态：作废
	 */
	public static final Integer IS_ACTIVE_N = 2;

	private Integer activeState;

	private Integer orderNum;

	private Long lastUpdateTime;

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

	@Column(name = "LAST_UPDATE_TIME")
	public Long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

}
