/*
 * $Log: BaseWordCondition.java,v $
 * Revision 1.1  2012/05/23 09:27:48  guor
 * 初次提交
 *
 */
package cn.hs.core.basedao.condition;

import java.io.OutputStream;

/**
 * Title: BaseWordCondition<br>
 * Description: word condition<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Dec 8, 2011
 * @version $Revision: 1.1 $
 */
public class BaseWordCondition {
	// response输出流
	private OutputStream out;

	public OutputStream getOut() {
		return out;
	}

	public void setOut(OutputStream out) {
		this.out = out;
	}
}
