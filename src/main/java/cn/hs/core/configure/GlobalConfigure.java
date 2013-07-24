/*
 * $Log: GlobalConfigure.java,v $
 * Revision 1.1  2012/05/23 09:27:48  guor
 * 初次提交
 *
 * Revision 1.1  2011/06/01 05:46:56  kkaiwen
 * 2011.6.1 create
 *
 */
package cn.hs.core.configure;

/**
 * Title: GlobalConfigure<br>
 * Description: 通用配置<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author MengW
 * @createDate 2011-5-16
 * @version $Revision: 1.1 $
 */
public class GlobalConfigure {

	public GlobalConfigure() {

	}

	/**
	 * 通用日期格式
	 */
	private String commonDatePattern;

	/**
	 * 添加方法是否记录日志
	 */
	private String addMethodWithLog;

	/**
	 * 更新方法是否记录日志
	 */
	private String updateMethodWithLog;

	/**
	 * 批量添加方法是否记录日志
	 */
	private String batchAddMethodWithLog;

	/**
	 * 批量更新方法是否记录日志
	 */
	private String batchUpdateMethodWithLog;

	/**
	 * 批量更新状态方法是否记录日志
	 */
	private String batchUpdateStateMethodWithLog;

	/**
	 * 批量删除方法是否记录日志
	 */
	private String batchDeleteMethodWithLog;
	/**
	 * 初始积分等级基数
	 */
	private String initCreditLevelBase;
	/**
	 * 初始积分等级递增基数
	 */
	private String initLevelIncreaseBase;
	/**
	 * 初始最高积分等级
	 */
	private String initMaxLevel;
	/**
	 * 积分等级基数
	 */
	private String creditLevelBase;
	/**
	 * 积分等级递增基数
	 */
	private String levelIncreaseBase;
	/**
	 * 最高等级
	 */
	private String maxLevel;

	public String getCommonDatePattern() {
		return commonDatePattern;
	}

	public void setCommonDatePattern(String commonDatePattern) {
		this.commonDatePattern = commonDatePattern;
	}

	public String getAddMethodWithLog() {
		return addMethodWithLog;
	}

	public void setAddMethodWithLog(String addMethodWithLog) {
		this.addMethodWithLog = addMethodWithLog;
	}

	public String getUpdateMethodWithLog() {
		return updateMethodWithLog;
	}

	public void setUpdateMethodWithLog(String updateMethodWithLog) {
		this.updateMethodWithLog = updateMethodWithLog;
	}

	public String getBatchAddMethodWithLog() {
		return batchAddMethodWithLog;
	}

	public void setBatchAddMethodWithLog(String batchAddMethodWithLog) {
		this.batchAddMethodWithLog = batchAddMethodWithLog;
	}

	public String getBatchUpdateMethodWithLog() {
		return batchUpdateMethodWithLog;
	}

	public void setBatchUpdateMethodWithLog(String batchUpdateMethodWithLog) {
		this.batchUpdateMethodWithLog = batchUpdateMethodWithLog;
	}

	public String getBatchUpdateStateMethodWithLog() {
		return batchUpdateStateMethodWithLog;
	}

	public void setBatchUpdateStateMethodWithLog(
			String batchUpdateStateMethodWithLog) {
		this.batchUpdateStateMethodWithLog = batchUpdateStateMethodWithLog;
	}

	public String getBatchDeleteMethodWithLog() {
		return batchDeleteMethodWithLog;
	}

	public void setBatchDeleteMethodWithLog(String batchDeleteMethodWithLog) {
		this.batchDeleteMethodWithLog = batchDeleteMethodWithLog;
	}

	public String getInitCreditLevelBase() {
		return initCreditLevelBase;
	}

	public void setInitCreditLevelBase(String initCreditLevelBase) {
		this.initCreditLevelBase = initCreditLevelBase;
	}

	public String getInitLevelIncreaseBase() {
		return initLevelIncreaseBase;
	}

	public void setInitLevelIncreaseBase(String initLevelIncreaseBase) {
		this.initLevelIncreaseBase = initLevelIncreaseBase;
	}

	public String getInitMaxLevel() {
		return initMaxLevel;
	}

	public void setInitMaxLevel(String initMaxLevel) {
		this.initMaxLevel = initMaxLevel;
	}

	public String getCreditLevelBase() {
		return creditLevelBase;
	}

	public void setCreditLevelBase(String creditLevelBase) {
		this.creditLevelBase = creditLevelBase;
	}

	public String getLevelIncreaseBase() {
		return levelIncreaseBase;
	}

	public void setLevelIncreaseBase(String levelIncreaseBase) {
		this.levelIncreaseBase = levelIncreaseBase;
	}

	public String getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(String maxLevel) {
		this.maxLevel = maxLevel;
	}

}
