package cn.hs.commons.utils;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

/**
 * 动态获取URL Title: DynamicGetUrl<br>
 * Description: <br>
 * Company: ORCHIS<br>
 * Copyright @ 2010 ORCHIS .All rights reserved.<br>
 * @author Huosd
 * @createDate Nov 25, 2010
 * @version $Revision: 1.1 $
 */
public class DynamicGetUrl {
	/**
	 * 获取域名类型
	 * 
	 * @param request
	 * @return 根据request返回，gov,egov,ip三种类型
	 * @author GuoR
	 * @throws Exception
	 * @date 2010-12-7
	 */
	public static String getDomainType(HttpServletRequest request)
			throws Exception {
		String serverName = request.getServerName();
		if (serverName.indexOf(".gov.cn") != -1) {
			return "gov";
		} else if (serverName.indexOf(".egov.cn") != -1) {
			return "egov";
		} else {
			return "ip";
		}
	}

	/**
	 * 通过request动态获取serverName,动态返回访问地址，该方法只能在gov和egov之间进行转换
	 * 1.如果传入参数initUrl为域名为gov，当前request获取动态域名为egov，则返回域名为egov的initUrl
	 * 2.如果传入参数initUrl为域名为gov，当前request获取动态域名为gov，则不做任何修改返回initUrl
	 * 3.如果传入参数initUrl为域名为egov，当前request获取动态域名为gov，则返回域名为gov的initUrl
	 * 4.如果传入参数initUrl为域名为egov，当前request获取动态域名为egov，则不做任何修改返回initUrl
	 * 5.如果传入参数initUrl为域名为IP，则不做任何修改返回initUrl
	 * 
	 * @param initUrl
	 *            初始地址
	 * @param request
	 * @return
	 * @author Huosd
	 * @throws Exception
	 * @date Nov 25, 2010
	 */
	public static String getUrl(String initUrl, HttpServletRequest request)
			throws Exception {
		String serverName = request.getServerName();
		String dynamicUrlServerName = "";

		if (initUrl != null && !initUrl.equals("")) {
			if (serverName.indexOf("gov.cn") == -1
					&& serverName.indexOf("egov.cn") == -1) {
				return initUrl;
			} else if (serverName.indexOf("egov.cn") == -1) {
				dynamicUrlServerName = "gov.cn";
			} else {
				dynamicUrlServerName = "egov.cn";
			}

			if (dynamicUrlServerName.equals("egov.cn")) {
				if (initUrl.indexOf(dynamicUrlServerName) == -1) {
					String newUrl = initUrl.replaceAll("gov.cn", "egov.cn");
					return newUrl;
				} else {
					return initUrl;
				}
			} else {
				if (initUrl.indexOf("egov.cn") == -1) {
					return initUrl;
				} else {
					String newUrl = initUrl.replaceAll("egov.cn", "gov.cn");
					return newUrl;
				}
			}
		} else {
			return null;
		}
	}

	/**
	 * 将传进参数initUrl中的域名全部替换为当前地址栏中的动态域名<br>
	 * 如果不是跨站请求,systemPlatformName参数传入null或者""即可<br>
	 * 如果是跨站请求,需要传入systemPlatformName参数为站点二级域名,该二级域名参见WEB-INF下的systemPlatform.properties文件中的key值<br>
	 * 
	 * 不是跨站访问： 1.如果当前地址栏域名为IP，返回域名为地址栏IP的initUrl<br>
	 * 2.如果当前地址栏为域名，返回域名为地址栏域名的initUrl<br>
	 * 跨站访问： 1.如果当前地址栏域名为IP，返回域名为跨站后IP的initUrl<br>
	 * 2.如果当前地址栏为域名，返回域名为跨站后域名的initUrl<br>
	 * 
	 * @param initUrl
	 *            初始地址 参数格式：参数必须为完整的url开头请求地址，如：http://www.bjce.gov.cn/
	 * @param request
	 * @param systemPlatformName
	 *            跨平台参数
	 * @return 返回值为完整的url请求地址
	 * @throws Exception
	 * @author Huosd
	 * @date Dec 14, 2010
	 */
	public static String replaceFullDomain(String initUrl,
			HttpServletRequest request, String systemPlatformName)
			throws Exception {
		String dynamicServerName = "";
		int dynamicServerPort = request.getServerPort();
		String topString = initUrl.substring(0, initUrl.indexOf("//") + 2);
		String subString = initUrl.substring(initUrl.indexOf("//") + 2);
		String endString = subString.substring(subString.indexOf("/"));

		if (systemPlatformName == null || systemPlatformName.equals("")) {
			dynamicServerName = request.getServerName();
			if (dynamicServerPort == 80) {
				return topString + dynamicServerName + endString;
			} else {
				return topString + dynamicServerName + "：" + dynamicServerPort
						+ endString;
			}
		} else {
			String domainType = getDomainType(request);
			if (domainType.equals("gov")) {
				if (dynamicServerPort == 80) {
					dynamicServerName = systemPlatformName + ".bjce.gov.cn";
				} else {
					dynamicServerName = systemPlatformName + ".bjce.gov.cn:"
							+ dynamicServerPort;
				}
				return topString + dynamicServerName + endString;
			} else if (domainType.equals("egov")) {
				if (dynamicServerPort == 80) {
					dynamicServerName = systemPlatformName + ".bjce.egov.cn";
				} else {
					dynamicServerName = systemPlatformName + ".bjce.egov.cn:"
							+ dynamicServerPort;
				}
				return topString + dynamicServerName + endString;
			} else {
				if (dynamicServerPort == 80) {
					dynamicServerName = getSystemPlatformID(systemPlatformName);
				} else {
					dynamicServerName = getSystemPlatformID(systemPlatformName)
							+ ":" + dynamicServerPort;
				}
				return topString + dynamicServerName + endString;
			}
		}
	}

	/**
	 * 根据子平台获取对应的IP，systemPlatform.properties配置文件在WEB-INF下
	 * 
	 * @param systemPlatformName
	 * @return 返回对应的IP地址
	 * @throws Exception
	 * @author GuoR
	 * @date 2010-12-7
	 */
	private static String getSystemPlatformID(String systemPlatformName)
			throws Exception {
		URL resource = Thread.currentThread().getContextClassLoader()
				.getResource("");
		String substring = resource.getPath().substring(0,
				resource.getPath().indexOf("classes"));
		FileInputStream inputStream = new FileInputStream(new File(substring
				+ "systemPlatform.properties"));
		Properties p = new Properties();
		p.load(inputStream);
		return (String) p.get(systemPlatformName);
	}
}
