/*
 * $Log: SelectOptionBean.java,v $
 * Revision 1.1  2012/05/23 09:27:44  guor
 * 初次提交
 *
 */
package cn.hs.commons;

/**
 * Title: SelectOptionBean<br>
 * Description: 页面下拉框option元素封装对象<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author FanKY
 * @createDate Sep 8, 2011
 * @version $Revision: 1.1 $
 */
public class SelectOptionBean {
	private String text;// option元素名称
	private String value;// option元素值

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
