package cn.hs.module.httpserver;

/**
 * 
 * Title: HttpServerCommond<br>
 * Description: httpserverCommond实体基类<br>
 * Company: HsS<br>
 * Copyright @ 2013 HsS .All rights reserved.<br>
 * @author HuangS
 * @createDate May 19, 2013
 * @version $Revision:$
 */
public abstract class HttpServerCommond {

	private String time;
	private String md5;
	private String sid;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

}
