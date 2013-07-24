package cn.hs.core.springext.security.casclient.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Title: MappingStrategy<br>
 * Description: 域名映射器<br>
 * Company: EORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * 
 * @author ZhangKW
 * @createDate 2011-6-27
 * @version $Revision: 1.1 $
 */
public class MappingStrategy {

	// 域名映射 keys 以","分隔
	private String serverNamesBinded;

	// 域名映射 values 以","分隔
	private String casServerUrlPrefixsRelate;

	// 映射map
	private static Map<String, String> serverMap = null;

	/**
	 * 获得CAS服务端域名
	 * 
	 * @param serverNamesBinded
	 * @return
	 * @author ZhangKW
	 * @date 2011-6-28
	 */
	public String getCasServerUrlPrefixsRelateByServerNamesBinded(
			String serverNamesBinded) {
		if (serverMap == null) {
			serverMap = new HashMap<String, String>();
			this.buildServerMap();
		}
		return serverMap.get(serverNamesBinded);
	}

	/**
	 * 组装映射器
	 * 
	 * @author ZhangKW
	 * @date 2011-6-27
	 */
	private void buildServerMap() {
		String[] serverNames = serverNamesBinded.trim().split(",");
		String[] casServerUrlPrefixsRelates = casServerUrlPrefixsRelate.trim()
				.split(",");
		for (int index = 0; index < serverNames.length; index++) {
			serverMap
					.put(serverNames[index], casServerUrlPrefixsRelates[index]);
		}
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

	/**
	 * @return the casServerUrlPrefixsRelate
	 */
	public String getCasServerUrlPrefixsRelate() {
		return casServerUrlPrefixsRelate;
	}

	/**
	 * @param casServerUrlPrefixsRelate
	 *            the casServerUrlPrefixsRelate to set
	 */
	public void setCasServerUrlPrefixsRelate(String casServerUrlPrefixsRelate) {
		this.casServerUrlPrefixsRelate = casServerUrlPrefixsRelate;
	}
}
