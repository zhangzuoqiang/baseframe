package cn.hs.module.modules.domain.json;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * Title: HTMLTag<br>
 * Description: extjs的html标签实体<br>
 * Company: HsS<br>
 * Copyright @ 2013 HsS .All rights reserved.<br>
 * @author HuangS
 * @createDate Apr 29, 2013
 * @version $Revision:$
 */
public class HTMLTag implements Serializable {

	/**
	 * @author HuangS
	 * @date Apr 29, 2013
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * html标签：option
	 */
	public static String HTML_TAG_OPTION = "option";

	private String tag; // html标签

	private String value; // html的value值

	private String html; // 显示内容

	private String onClick; // html标签的onclick事件

	private Boolean selected; // 是否选中

	private List<HTMLTag> children; // 子标签

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getOnClick() {
		return onClick;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public List<HTMLTag> getChildren() {
		return children;
	}

	public void setChildren(List<HTMLTag> children) {
		this.children = children;
	}

}
