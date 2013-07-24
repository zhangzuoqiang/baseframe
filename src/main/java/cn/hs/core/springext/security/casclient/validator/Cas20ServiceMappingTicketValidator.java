package cn.hs.core.springext.security.casclient.validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.authentication.AttributePrincipalImpl;
import org.jasig.cas.client.proxy.Cas20ProxyRetriever;
import org.jasig.cas.client.proxy.ProxyGrantingTicketStorage;
import org.jasig.cas.client.proxy.ProxyRetriever;
import org.jasig.cas.client.util.CommonUtils;
import org.jasig.cas.client.util.XmlUtils;
import org.jasig.cas.client.validation.AbstractCasProtocolUrlBasedTicketValidator;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.AssertionImpl;
import org.jasig.cas.client.validation.TicketValidationException;

import cn.hs.core.springext.security.casclient.common.MappingStrategy;

/**
 * Title: Cas20ServiceMappingTicketValidator<br>
 * Description: <br>
 * Company: EORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * 
 * @author ZhangKW
 * @createDate 2011-6-28
 * @version $Revision: 1.1 $
 */
public class Cas20ServiceMappingTicketValidator extends
		AbstractCasProtocolUrlBasedTicketValidator {

	/** The CAS 2.0 protocol proxy callback url. */
	private String proxyCallbackUrl;

	/** The storage location of the proxy granting tickets. */
	private ProxyGrantingTicketStorage proxyGrantingTicketStorage;

	/** Implementation of the proxy retriever. */
	private ProxyRetriever proxyRetriever;

	// 域名映射器
	private MappingStrategy mappingStrategy;

	// CAS客户端验证接收入口
	public static final String CALL_BACK_ENTRANCE = "j_spring_cas_security_check";

	/**
	 * Constructs an instance of the CAS 2.0 Service Ticket Validator with the
	 * supplied CAS server url prefix.
	 * 
	 * @param casServerUrlPrefix
	 *            the CAS Server URL prefix.
	 */
	public Cas20ServiceMappingTicketValidator(final String casServerUrlPrefix) {
		super(casServerUrlPrefix);
		this.proxyRetriever = new Cas20ProxyRetriever(casServerUrlPrefix,
				getEncoding());
	}

	/**
	 * Adds the pgtUrl to the list of parameters to pass to the CAS server.
	 * 
	 * @param urlParameters
	 *            the Map containing the existing parameters to send to the
	 *            server.
	 */
	protected final void populateUrlAttributeMap(final Map urlParameters) {
		urlParameters.put("pgtUrl", encodeUrl(this.proxyCallbackUrl));
	}

	protected String getUrlSuffix() {
		return "serviceValidate";
	}

	protected final Assertion parseResponseFromServer(final String response)
			throws TicketValidationException {
		final String error = XmlUtils.getTextForElement(response,
				"authenticationFailure");

		if (CommonUtils.isNotBlank(error)) {
			throw new TicketValidationException(error);
		}

		final String principal = XmlUtils.getTextForElement(response, "user");
		final String proxyGrantingTicketIou = XmlUtils.getTextForElement(
				response, "proxyGrantingTicket");
		final String proxyGrantingTicket = this.proxyGrantingTicketStorage != null ? this.proxyGrantingTicketStorage
				.retrieve(proxyGrantingTicketIou)
				: null;

		if (CommonUtils.isEmpty(principal)) {
			throw new TicketValidationException(
					"No principal was found in the response from the CAS server.");
		}

		final Assertion assertion;
		final Map attributes = extractCustomAttributes(response);
		if (CommonUtils.isNotBlank(proxyGrantingTicket)) {
			final AttributePrincipal attributePrincipal = new AttributePrincipalImpl(
					principal, attributes, proxyGrantingTicket,
					this.proxyRetriever);
			assertion = new AssertionImpl(attributePrincipal);
		} else {
			assertion = new AssertionImpl(new AttributePrincipalImpl(principal,
					attributes));
		}

		customParseResponse(response, assertion);

		return assertion;
	}

	/**
	 * Default attribute parsing of attributes that look like the following:
	 * &lt;cas:attributes&gt; &lt;cas:attribute1&gt;value&lt;/cas:attribute1&gt;
	 * &lt;cas:attribute2&gt;value&lt;/cas:attribute2&gt;
	 * &lt;/cas:attributes&gt;
	 * <p>
	 * This code is here merely for sample/demonstration purposes for those
	 * wishing to modify the CAS2 protocol. You'll probably want a more robust
	 * implementation or to use SAML 1.1
	 * 
	 * @param xml
	 *            the XML to parse.
	 * @return the map of attributes.
	 */
	protected Map extractCustomAttributes(final String xml) {
		final int pos1 = xml.indexOf("<cas:attributes>");
		final int pos2 = xml.indexOf("</cas:attributes>");

		if (pos1 == -1) {
			return Collections.EMPTY_MAP;
		}

		final String attributesText = xml.substring(pos1 + 16, pos2);

		final Map attributes = new HashMap();
		final BufferedReader br = new BufferedReader(new StringReader(
				attributesText));

		String line;
		final List attributeNames = new ArrayList();
		try {
			while ((line = br.readLine()) != null) {
				final String trimmedLine = line.trim();
				if (trimmedLine.length() > 0) {
					final int leftPos = trimmedLine.indexOf(":");
					final int rightPos = trimmedLine.indexOf(">");
					attributeNames.add(trimmedLine.substring(leftPos + 1,
							rightPos));
				}
			}
			br.close();
		} catch (final IOException e) {
			// ignore
		}

		for (final Iterator iter = attributeNames.iterator(); iter.hasNext();) {
			final String name = (String) iter.next();
			attributes.put(name, XmlUtils.getTextForElement(xml, name));
		}

		return attributes;
	}

	/**
	 * Template method if additional custom parsing (such as Proxying) needs to
	 * be done.
	 * 
	 * @param response
	 *            the original response from the CAS server.
	 * @param assertion
	 *            the partially constructed assertion.
	 * @throws TicketValidationException
	 *             if there is a problem constructing the Assertion.
	 */
	protected void customParseResponse(final String response,
			final Assertion assertion) throws TicketValidationException {
		// nothing to do
	}

	/**
	 * 覆写顶级父类该方法,实现通过当前网段域名映射指定CAS服务端域名
	 * 
	 * @param ticket
	 * @param service
	 * @return
	 * @throws TicketValidationException
	 * @author ZhangKW
	 * @date 2011-6-28
	 */
	public Assertion validate(final String ticket, final String service)
			throws TicketValidationException {

		final String validationUrl = mappingConstructValidationUrl(ticket,
				service);
		if (log.isDebugEnabled()) {
			log.debug("Constructing validation url: " + validationUrl);
		}

		try {
			log.debug("Retrieving response from server.");
			final String serverResponse = retrieveResponseFromServer(new URL(
					validationUrl), ticket);

			if (serverResponse == null) {
				throw new TicketValidationException(
						"The CAS server returned no response.");
			}

			if (log.isDebugEnabled()) {
				log.debug("Server response: " + serverResponse);
			}

			return parseResponseFromServer(serverResponse);
		} catch (final MalformedURLException e) {
			throw new TicketValidationException(e);
		}
	}

	/**
	 * Constructs the URL to send the validation request to.
	 * 
	 * @param ticket
	 *            the ticket to be validated.
	 * @param serviceUrl
	 *            the service identifier.
	 * @return the fully constructed URL.
	 */
	@SuppressWarnings("unchecked")
	protected String mappingConstructValidationUrl(final String ticket,
			final String serverNamesBinded) {
		final Map urlParameters = new HashMap();

		log.debug("Placing URL parameters in map.");
		urlParameters.put("ticket", ticket);
		String localService = serverNamesBinded + "/" + CALL_BACK_ENTRANCE;
		urlParameters.put("service", encodeUrl(localService));

		// if (this.renew) {
		// urlParameters.put("renew", "true");
		// }

		log.debug("Calling template URL attribute map.");
		populateUrlAttributeMap(urlParameters);

		log.debug("Loading custom parameters from configuration.");
		// if (this.customParameters != null) {
		// urlParameters.putAll(this.customParameters);
		// }

		String casServerUrlPrefix = mappingStrategy
				.getCasServerUrlPrefixsRelateByServerNamesBinded(serverNamesBinded);

		final String suffix = getUrlSuffix();
		final StringBuffer buffer = new StringBuffer(urlParameters.size() * 10
				+ casServerUrlPrefix.length() + suffix.length() + 1);

		int i = 0;
		synchronized (buffer) {
			buffer.append(casServerUrlPrefix);
			if (!casServerUrlPrefix.endsWith("/")) {
				buffer.append("/");
			}
			buffer.append(suffix);

			for (final Iterator iter = urlParameters.entrySet().iterator(); iter
					.hasNext();) {
				final Map.Entry entry = (Map.Entry) iter.next();
				final String key = (String) entry.getKey();
				final String value = (String) entry.getValue();

				if (value != null) {
					buffer.append(i++ == 0 ? "?" : "&");
					buffer.append(key);
					buffer.append("=");
					buffer.append(value);
				}
			}
			return buffer.toString();
		}
	}

	public final void setProxyCallbackUrl(final String proxyCallbackUrl) {
		this.proxyCallbackUrl = proxyCallbackUrl;
	}

	public final void setProxyGrantingTicketStorage(
			final ProxyGrantingTicketStorage proxyGrantingTicketStorage) {
		this.proxyGrantingTicketStorage = proxyGrantingTicketStorage;
	}

	public final void setProxyRetriever(final ProxyRetriever proxyRetriever) {
		this.proxyRetriever = proxyRetriever;
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
