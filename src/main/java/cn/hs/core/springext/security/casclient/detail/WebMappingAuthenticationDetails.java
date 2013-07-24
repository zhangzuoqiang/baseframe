package cn.hs.core.springext.security.casclient.detail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import cn.hs.commons.utils.PropertysUtil;

/**
 * Title: MappingWebAuthenticationDetails<br>
 * Description: 验证细节扩展对象，增加回转请求的ServerName<br>
 * Company: EORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * 
 * @author ZhangKW
 * @createDate 2011-6-27
 * @version $Revision: 1.1 $
 */
@SuppressWarnings("serial")
public class WebMappingAuthenticationDetails extends WebAuthenticationDetails {

	// 服务名
	private String serverNamesBinded;

	/**
	 * 调用父类构造器初始化参数,服务名赋值
	 * 
	 * @param request
	 */
	public WebMappingAuthenticationDetails(HttpServletRequest request) {
		super(request);
		int serverPort = request.getServerPort();
		// 将配置文件中的端口号都转为80端口
		String port = PropertysUtil.getContextProperty("serverPort");
		if (port != null) {
			port = port.trim();
			if (!"".equals(port)) {
				List<String> list = new ArrayList<String>(Arrays.asList(port.split(",")));
				if (list.contains(serverPort + "")) {
					serverPort = 80;
				}
			}
		}
		this.serverNamesBinded = request.getScheme() + "://" + request.getServerName() + ":" + serverPort + request.getContextPath();
	}

	/**
	 * @return the serverNamesBinded
	 */
	public String getServerNamesBinded() {
		return serverNamesBinded;
	}

	/**
	 * @param serverNamesBinded
	 *            the serverNamesBinded to set
	 */
	public void setServerNamesBinded(String serverNamesBinded) {
		this.serverNamesBinded = serverNamesBinded;
	}

}
