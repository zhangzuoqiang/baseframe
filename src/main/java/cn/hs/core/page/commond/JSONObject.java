/**
 * $Id:$
 */
package cn.hs.core.page.commond;

import java.io.Serializable;

/**
 * 
 * Title: JSONObject<br>
 * Description: json格式返回格式化<br>
 * Company: HsS<br>
 * Copyright @ 2013 HsS .All rights reserved.<br>
 * @author HuangS
 * @createDate Apr 29, 2013
 * @version $Revision:$
 */
public class JSONObject implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean success;
	private Object data;
	private long totalCount;// 总数
	private String msg;// 消息

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
