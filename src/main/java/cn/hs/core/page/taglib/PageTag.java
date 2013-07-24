/*
 * $Log: PageTag.java,v $
 * Revision 1.1  2012/05/23 09:27:48  guor
 * 初次提交
 *
 */
package cn.hs.core.page.taglib;

import java.io.Writer;
import java.lang.reflect.Method;
import java.text.MessageFormat;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import cn.hs.commons.utils.PropertyUtil;

/**
 * Title: PageTag<br>
 * Description: 翻页标签类<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 4, 2011
 * @version $Revision: 1.1 $
 */
public class PageTag extends SimpleTagSupport {
	private static final long serialVersionUID = 1L;

	protected String DEFAULT_TABLE_WIDTH = "90%";
	
	protected String DEFAULT_TABLE_CLASS = "tablepagenum";

	private static final String DEFAULT_RETURN = "\r\n";

	/**
	 * 该页面使用的commond
	 */
	private String commondName;

	/**
	 * 定义翻页标签样式
	 */
	private String classType;

	/**
	 * 定义翻页table大小
	 */
	private String tableWidth;

	/**
	 * modify by HuangS at 2011-09-05
	 * 0-tablewidth(更换为classType-css样式) 1-current 2-pageCount 3-pageNumList(暂时弃用) 4-count 5-previous
	 * 6 next
	 */
	private static final String OUT_HEAD = "<TABLE class=\"{0}\" cellSpacing=\"0\" cellPadding=\"0\" border=\"0\"> "
			+ DEFAULT_RETURN
			+ "<input type=\"hidden\" name=\"current\" value=\"{1}\"/> "
			+ DEFAULT_RETURN
			+ " <TR> "
			+ DEFAULT_RETURN
			+ " <TD class=\"xue_td21\">共{2}页</TD>"
			+ DEFAULT_RETURN
			+ " <TD class=\"xue_td21\">第{1}页</TD>"
			+ DEFAULT_RETURN
			+ " <TD class=\"banji_td02\">共{4}条</TD> "
			+ DEFAULT_RETURN
			+ " <TD class=\"banji_td03\"><input type=\"button\" class=\"page_first_button\" onClick=\"pageHandle(1)\"/></TD>"
			+ DEFAULT_RETURN
			+ " <TD class=\"banji_td03\"><input type=\"button\" class=\"page_up_button\" onClick=\"pageHandle({5})\"/></TD>"
			+ DEFAULT_RETURN
			+ " <TD class=\"banji_td03\"><input type=\"button\" class=\"page_down_button\" onClick=\"pageHandle({6})\"/></TD>"
			+ DEFAULT_RETURN
			+ " <TD class=\"banji_td02\"><input type=\"button\" class=\"page_last_button\" onClick=\"pageHandle({2})\"/></TD> "
			+ DEFAULT_RETURN
			+ " <TD class=\"banji_td04\">第</TD>"
			+ DEFAULT_RETURN
			+ " <TD class=\"banji_td04\"><input type=\"text\" id=\"{3}\" name=\"goTo\" class=\"black12_18 banji_input02\"/></TD>"
			+ DEFAULT_RETURN
			+ " <TD class=\"banji_td03\">页</TD>"
			+ DEFAULT_RETURN
			+ " <TD width=\"52\" class=\"0banji_td02\"><input type=\"button\" class=\"page_go_button\" onclick=\"pageHandleGoTo({3});\"/></TD>"
			+ DEFAULT_RETURN + " </TR> " + DEFAULT_RETURN + " </TABLE> ";

	/**
	 * 生成标签
	 * 
	 * @author WangWB
	 * @date Aug 4, 2011
	 */
	public void doTag() {
		try {
			Object commondObject = getCommondObject();
			String output = MessageFormat.format(OUT_HEAD, new Object[] {
					getClassType(),
					getCommondObjectValue(commondObject, "getCurrent"),
					getCommondObjectValue(commondObject, "getPageCount"), 
					"1"+(int)(Math.random()*10000000+1)+"1",
					getCommondObjectValue(commondObject, "getCount"),
					getCommondObjectValue(commondObject, "getPrevious"),
					getCommondObjectValue(commondObject, "getNext") });
			Writer out = super.getJspContext().getOut();
			out.write(output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得翻页table宽度
	 * 
	 * @return
	 * @author WangWB
	 * @date Aug 4, 2011
	 */
	public String getTablewidth() {
		if (tableWidth == null) {
			return DEFAULT_TABLE_WIDTH;
		} else {
			return tableWidth;
		}
	}

	/**
	 * 获得commond对象
	 * 
	 * @return
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 4, 2011
	 */
	private Object getCommondObject() throws Exception {
		Object commondObject = null;
		String commondName = getCommondName();
		if (PropertyUtil.objectNotEmpty(commondName)) {
			JspContext jspContext = super.getJspContext();
			commondObject = jspContext.getAttribute(commondName, jspContext
					.getAttributesScope(commondName));
		} else {
			System.err.println("commondName为空");
		}
		return commondObject;
	}

	/**
	 * 获得commond对象中的指定属性
	 * 
	 * @param commondMethod
	 *            指定commond中的属性名
	 * 
	 * @param commondObject
	 *            commond对象
	 * @return
	 * @throws JspException
	 * @author WangWB
	 * @date Aug 4, 2011
	 */
	private String getCommondObjectValue(Object commondObject,
			String commondMethod) throws Exception {
		String value = "";
		Method method = commondObject.getClass().getMethod(commondMethod, null);
		Integer result = (Integer) method.invoke(commondObject, null);
		if (result != null) {
			value = result.toString();
		} else {
			System.err.println(commondMethod + "()方法返回值为空");
		}
		return value;
	}

	public String getCommondName() {
		return commondName;
	}

	public void setCommondName(String commondName) {
		this.commondName = commondName;
	}

	/**
	 * 获取翻页tableCSS样式
	 * @return
	 * @author HuangS
	 * @date Sep 5, 2011
	 */
	public String getClassType() {
		if (classType == null) {
			return DEFAULT_TABLE_CLASS;
		} else {
			return classType;
		}
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getTableWidth() {
		return tableWidth;
	}

	public void setTableWidth(String tableWidth) {
		this.tableWidth = tableWidth;
	}
}
