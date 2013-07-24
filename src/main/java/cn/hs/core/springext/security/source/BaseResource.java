package cn.hs.core.springext.security.source;

/**
 * Title: BaseResources<br>
 * Description: 基础资源接口<br>
 * Company: EORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * 
 * @author ZhangKW
 * @createDate 2011-5-23
 * @version $Revision: 1.1 $
 */
public interface BaseResource {

	/**
	 * 获得资源唯一标识
	 * 
	 * @return
	 * @author ZhangKW
	 * @date 2011-5-23
	 */
	String getIdentity();

	/**
	 * 获得资源URL
	 * 
	 * @return
	 * @author ZhangKW
	 * @date 2011-5-23
	 */
	String getUrl();

}
