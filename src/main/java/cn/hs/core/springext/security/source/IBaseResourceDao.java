package cn.hs.core.springext.security.source;

import java.util.List;

import cn.hs.core.springext.security.source.BaseResource;

/**
 * Title: ISecurityResourcesDao<br>
 * Description: 资源基础接口<br>
 * Company: EORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * 
 * @author ZhangKW
 * @createDate 2011-5-23
 * @version $Revision: 1.1 $
 */
public interface IBaseResourceDao {

	/**
	 * 获得所有受保护的资源
	 * 
	 * @return
	 * @throws Exception
	 * @author ZhangKW
	 * @date 2011-5-23
	 */
	List<BaseResource> listSecurityResource() throws Exception;
}
