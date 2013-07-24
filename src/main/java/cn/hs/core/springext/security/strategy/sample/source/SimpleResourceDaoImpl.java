package cn.hs.core.springext.security.strategy.sample.source;

import java.util.ArrayList;
import java.util.List;

import cn.hs.core.springext.security.source.BaseResource;
import cn.hs.core.springext.security.source.IBaseResourceDao;
import cn.hs.core.springext.security.strategy.sample.bo.SimpleResource;

/**
 * Title: SimpleResourceDaoImpl<br>
 * Description: 样例资源dao实现<br>
 * Company: EORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * 
 * @author ZhangKW
 * @createDate 2011-5-26
 * @version $Revision: 1.1 $
 */
public class SimpleResourceDaoImpl implements IBaseResourceDao {

	@Override
	public List<BaseResource> listSecurityResource() throws Exception {
		List<BaseResource> resourceList = new ArrayList<BaseResource>();

		SimpleResource resource1 = new SimpleResource();
		resource1.setName("admin");
		resource1.setUrl("/test/user/preAddUser.do");

		SimpleResource resource2 = new SimpleResource();
		resource2.setName("user");
		resource2.setUrl("/test/test.html");

		resourceList.add(resource1);
		resourceList.add(resource2);
		return resourceList;
	}

}
