/*
 * $Log: TokenTag.java,v $
 * Revision 1.1  2012/05/23 09:27:51  guor
 * 初次提交
 *
 */
package cn.hs.core.util.token.taglib;

import java.io.Writer;
import java.text.MessageFormat;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import cn.hs.core.util.token.IToken;

/**
 * Title: PageTag<br>
 * Description: 翻页标签类<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 4, 2011
 * @version $Revision: 1.1 $
 */
public class TokenTag extends SimpleTagSupport {
	private static final long serialVersionUID = 1L;

	private static final String OUT_HEAD = "<input type=\"hidden\" name=\"{0}\" value=\"{1}\"/> ";

	/**
	 * 生成标签
	 * 
	 * @author WangWB
	 * @date Aug 4, 2011
	 */
	public void doTag() {
		try {
			String output = MessageFormat.format(OUT_HEAD, new Object[] {
					IToken.TOKEN_STRING, getSessionTokenValue() });
			Writer out = super.getJspContext().getOut();
			out.write(output);
		} catch (Exception e) {
			e.printStackTrace();
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
	private String getSessionTokenValue() throws Exception {
		JspContext jspContext = super.getJspContext();
		return (String) jspContext.getAttribute(IToken.TOKEN_STRING, jspContext
				.getAttributesScope(IToken.TOKEN_STRING));
	}
}
