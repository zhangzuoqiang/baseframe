package cn.hs.core.springext.security.strategy.casclient.bo;

import cn.hs.core.springext.security.source.BaseResource;

/**
 * 
 * Title: BaseResourceImpl<br>
 * Description: BaseResource的实体类<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author RongLT
 * @createDate 2011-10-11
 * @version $Revision: 1.1 $
 */
public class BaseResourceImpl implements BaseResource {
	
	public final static String NO_ACTIVE_STATE="2";
	public final static String IS_ACTIVE_STATE="1";
	public final static int DISABLE_ROWS_START=-1;
	private String identity;//资源唯一标识
	private String url;//资源URL
	
	
	public static int getDisableRowsStart() {
		return DISABLE_ROWS_START;
	}

	public static String getNoActiveState() {
		return NO_ACTIVE_STATE;
	}

	public static String getIsActiveState() {
		return IS_ACTIVE_STATE;
	}
	
	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	public String getUrl() {
		return url;
	}

}

