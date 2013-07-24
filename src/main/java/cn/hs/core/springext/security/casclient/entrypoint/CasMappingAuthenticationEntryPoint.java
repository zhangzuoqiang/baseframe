package cn.hs.core.springext.security.casclient.entrypoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasig.cas.client.util.CommonUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.Assert;

import cn.hs.commons.utils.PropertysUtil;
import cn.hs.core.springext.security.casclient.common.MappingStrategy;

/**
 * Title: CasMappingAuthenticationEntryPoint<br>
 * Description: <br>
 * Company: EORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * 
 * @author ZhangKW
 * @createDate 2011-6-24
 * @version $Revision: 1.1 $
 */
public class CasMappingAuthenticationEntryPoint implements AuthenticationEntryPoint, InitializingBean {

	// 域名映射器
	private MappingStrategy mappingStrategy;

	// CAS客户端验证接收入口
	public static final String CALL_BACK_ENTRANCE = "j_spring_cas_security_check";

	private ServiceProperties serviceProperties;

	/**
	 * Determines whether the Service URL should include the session id for the
	 * specific user. As of CAS 3.0.5, the session id will automatically be
	 * stripped. However, older versions of CAS (i.e. CAS 2), do not
	 * automatically strip the session identifier (this is a bug on the part of
	 * the older server implementations), so an option to disable the session
	 * encoding is provided for backwards compatibility.
	 * 
	 * By default, encoding is enabled.
	 * 
	 * @deprecated since 3.0.0 because CAS is currently on 3.3.5.
	 */
	@Deprecated
	private boolean encodeServiceUrlWithSessionId = true;

	public void afterPropertiesSet() throws Exception {
		// Assert.hasLength(this.loginUrl, "loginUrl must be specified");
		Assert.notNull(this.serviceProperties, "serviceProperties must be specified");
		Assert.notNull(this.serviceProperties.getService(), "serviceProperties.getService() cannot be null.");
	}

	/**
	 * 由Filter检测到当前活动用户未通过验证，通过本方法重定向到CAS服务端
	 * 
	 * @param servletRequest
	 * @param response
	 * @param authenticationException
	 * @throws IOException
	 * @throws ServletException
	 * @author ZhangKW
	 * @date 2011-6-28
	 */
	public void commence(final HttpServletRequest servletRequest, final HttpServletResponse response, final AuthenticationException authenticationException) throws IOException, ServletException {

		// 组装CAS客户端回转参数
		final String urlEncodedService = createServiceUrl(servletRequest, response);

		// 组装CAS服务端请求
		final String redirectUrl = createRedirectUrl(urlEncodedService, servletRequest);

		response.sendRedirect(redirectUrl);
	}

	/**
	 * 组装CAS客户端回转参数,以当前request中访问的域名为准
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @author ZhangKW
	 * @date 2011-6-28
	 */
	protected String createServiceUrl(final HttpServletRequest request, final HttpServletResponse response) throws ServletException {
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
		// 验证通过后返回CAS客户端的域名
		String callBackServer = request.getScheme() + "://" + request.getServerName() + ":" + serverPort + request.getContextPath() + "/" + CALL_BACK_ENTRANCE;

		return CommonUtils.constructServiceUrl(null, response, callBackServer, null, this.serviceProperties.getArtifactParameter(), this.encodeServiceUrlWithSessionId);
	}

	/**
	 * 组装CAS服务端请求,根据当前域名获得服务端域名
	 * 
	 * @param serviceUrl
	 * @param request
	 * @return
	 * @throws ServletException
	 * @author ZhangKW
	 * @date 2011-6-28
	 */
	protected String createRedirectUrl(final String serviceUrl, final HttpServletRequest request) throws ServletException {
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
		// 根据当前域名获得CAS服务端对应域名
		String casServerLoginUrl = mappingStrategy.getCasServerUrlPrefixsRelateByServerNamesBinded(request.getScheme() + "://" + request.getServerName() + ":" + serverPort + request.getContextPath());

		if (casServerLoginUrl == null || "".equals(casServerLoginUrl)) {
			throw new ServletException("Getting casServerLoginUrl exception, Can't find corresponding domain name ,key:" + request.getServerName());
		}
		return CommonUtils.constructRedirectUrl(casServerLoginUrl, this.serviceProperties.getServiceParameter(), serviceUrl, this.serviceProperties.isSendRenew(), false);
	}

	/**
	 * Sets whether to encode the service url with the session id or not.
	 * 
	 * @param encodeServiceUrlWithSessionId
	 *            whether to encode the service url with the session id or not.
	 * @deprecated since 3.0.0 because CAS is currently on 3.3.5.
	 */
	@Deprecated
	public final void setEncodeServiceUrlWithSessionId(final boolean encodeServiceUrlWithSessionId) {
		this.encodeServiceUrlWithSessionId = encodeServiceUrlWithSessionId;
	}

	/**
	 * Sets whether to encode the service url with the session id or not.
	 * 
	 * @return whether to encode the service url with the session id or not.
	 * 
	 * @deprecated since 3.0.0 because CAS is currently on 3.3.5.
	 */
	@Deprecated
	protected boolean getEncodeServiceUrlWithSessionId() {
		return this.encodeServiceUrlWithSessionId;
	}

	public final ServiceProperties getServiceProperties() {
		return this.serviceProperties;
	}

	public final void setServiceProperties(final ServiceProperties serviceProperties) {
		this.serviceProperties = serviceProperties;
	}

	/**
	 * @return the mappingStrategy
	 */
	public MappingStrategy getMappingStrategy() {
		return mappingStrategy;
	}

	/**
	 * @param mappingStrategy
	 *            the mappingStrategy to set
	 */
	public void setMappingStrategy(MappingStrategy mappingStrategy) {
		this.mappingStrategy = mappingStrategy;
	}
}
