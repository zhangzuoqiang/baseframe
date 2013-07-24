package cn.hs.commons.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Title: URLConnectionUtil<br>
 * Description: URL工具类<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * 
 * @author ZhangKW
 * @createDate 2012-5-29
 * @version $Revision: 1.1 $
 */
public class URLConnectionUtil {

	private URLConnection connection;
	private String urlTarget;

	/**
	 * @return the connection
	 */
	public URLConnection getConnection() {
		return connection;
	}

	/**
	 * 构造器，创建连接
	 * 
	 * @param urlTarget
	 * @throws Exception
	 */
	public URLConnectionUtil(String urlTarget) throws Exception {
		try {
			this.urlTarget = urlTarget;
			URL url = new URL(urlTarget);
			connection = url.openConnection();
		} catch (MalformedURLException e) {
			throw new Exception("访问URL异常	URL地址：" + urlTarget, e);
		} catch (IOException e) {
			throw new Exception("访问URL异常	URL地址：" + urlTarget, e);
		}
	}

	/**
	 * 通过URL地址获得工具类实例
	 * 
	 * @return
	 * @throws Exception
	 * @author ZhangKW
	 * @date 2011-3-7
	 */
	public static URLConnectionUtil getInstanceByUrl(String urlTarget)
			throws Exception {
		if (urlTarget != null && !"".equals(urlTarget)) {
			return new URLConnectionUtil(urlTarget);
		} else {
			throw new Exception("远程访问URL地址字符串为空");
		}
	}

	/**
	 * 读取结果
	 * 
	 * @param charsetName
	 *            字符编码
	 * @return
	 * @throws Exception
	 * @author ZhangKW
	 * @date 2012-5-29
	 */
	public String readContents(String charsetName) throws Exception {

		StringBuffer result = new StringBuffer();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(connection
					.getInputStream(), charsetName));

			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				result.append(inputLine);
			}
		} catch (IOException e) {
			throw new Exception("访问URL读取结果异常	URL地址：" + urlTarget, e);
		} finally {
			if (in != null) {
				in.close();
			}
		}
		return result.toString();
	}

}
