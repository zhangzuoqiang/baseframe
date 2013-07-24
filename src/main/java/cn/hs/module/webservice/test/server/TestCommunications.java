/*
 * $Log: TestCommunications.java,v $
 * Revision 1.1  2012/05/23 09:27:57  guor
 * 初次提交
 *
 */
package cn.hs.module.webservice.test.server;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Title: TestCommunications<br>
 * Description: 测试本站webservice服务端网络通信<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * 
 * @author WangWB
 * @createDate Aug 26, 2011
 * @version $Revision: 11 $
 */
@WebService
public class TestCommunications {

	/**
	 * 测试方法
	 * 
	 * @return
	 * @author WangWB
	 * @date Aug 26, 2011
	 */
	@WebMethod
	public int testCommunications() throws Exception{
		return 1;
	}
	/**
	 * 不发布的方法
	 */
	@WebMethod(exclude=true)
	public void testWebMethod() {
		new RuntimeException();
	}
}
