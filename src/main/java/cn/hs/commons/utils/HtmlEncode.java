package cn.hs.commons.utils;

/*******************************************************************************
 * @file: HtmlEncode.java
 * @project: uum
 * @date: 2007-11-8
 * @author wangwei
 * @description:
 ******************************************************************************/
public class HtmlEncode {

	public static String encode(String str) {
		if (str != null && !"".equals(str)) {
			str = str.replaceAll("<", "&lt;");
			str = str.replaceAll(">", "&gt;");
			str = str.replaceAll("\n", "<br/>");
			str = str.replaceAll(" ", "&nbsp;");
			str = str.replaceAll("'", "&#39");
		}
		return str;
	}

}
