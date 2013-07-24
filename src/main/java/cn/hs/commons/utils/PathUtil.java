/**
 * 
 */
package cn.hs.commons.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Title: PathUtil<br>
 * Description: 配置文件路径工具类<br>
 * Company: ORCHIS<br>
 * Copyright @ 2008 ORCHIS .All rights reserved.<br>
 * 
 * @author GuoR
 * @version 2008-5-27 1.0
 */
public class PathUtil {
	private static final String ENCODE = "GBK";
	private static final String DIRNAME_STRUTS = "struts-config";
	private static final String DIRNAME_SPRING = "spring-config";
	private static String webRoot = "";
	static {
		String path = PathUtil.class.getResource("").getPath();
		int index = path.indexOf("WEB-INF");
		if (index != -1) {
			webRoot = path.substring(0, index);
		}
	}

	/**
	 * 设置WebRootPath
	 * 
	 * @param path
	 *            webRootPath
	 */
	public static void setWebRootPath(String path) {
		if (isNULL(path)) {
			return;
		}
		if (!path.endsWith(File.separator)) {
			path += File.separator;
		}
		webRoot = path;
	}

	/**
	 * 判断某一个对象是否为空，如果是空则显示空字符。
	 * 
	 * @param obj
	 *            待检测的对象
	 * @return 空为true,反之为false
	 */
	public static boolean isNULL(Object obj) {
		if (obj == null || obj.equals("null")) {
			return true;
		}
		return false;
	}

	/**
	 * 获取WebRootPath
	 * 
	 * @return 返回WebRootPath
	 * 
	 */
	public static String getWebRootPath() {
		return webRoot;
	}

	/**
	 * 获取项目构建路径目录的路径
	 * 
	 * @return 返回构建路径的路径
	 * @throws Exception
	 */
	public static String getClassPathPath() throws Exception {
		String path = webRoot + "WEB-INF" + File.separator + "classes";
		path = urlDecoder(path);
		return path;
	}

	/**
	 * 读取构建路径根下的配置文件
	 * 
	 * @param fileName
	 *            文件路径+文件名
	 * @return 返回指定文件的路径
	 * @throws Exception
	 */
	public static InputStream readClassPahtFile(String fileName)
			throws Exception {
		FileInputStream in = new FileInputStream(PathUtil.getClassPathPath()
				+ File.separator + fileName);
		return in;
	}

	/**
	 * 读取指定路径下文件的路径,路径前加"file:/"
	 * 
	 * @param filePath
	 *            文件路径+文件名
	 * @return 返回读取后的字节流
	 */
	public static String getModuleConfigPath(String filePath) throws Exception {
		String path = PathUtil.class.getResource(filePath).getPath();
		return osOperator(urlDecoder(path));
	}

	/**
	 * 读取指定路径下文件
	 * 
	 * @param filePath
	 *            文件路径+文件名
	 * @return 返回读取后的字节流
	 */
	public static InputStream readModuleConfigFile(String filePath) {
		return PathUtil.class.getResourceAsStream(filePath);
	}

	/**
	 * 获取WEB-INF的目录路径
	 * 
	 * @return 返回WEB-INF的路径
	 * @throws Exception
	 */
	public static String getWebInfoPath() throws Exception {
		String path = webRoot + "WEB-INF" + File.separator;
		path = urlDecoder(path);
		return path;
	}

	/**
	 * 读取WEB-INF目录下的文件内容
	 * 
	 * @param fileName
	 *            WEB-INF下的文件名
	 * @return 返回读取后的字节流
	 * @throws Exception
	 */
	public static InputStream readWebInfoFile(String fileName) throws Exception {
		FileInputStream in = new FileInputStream(getWebInfoPath() + fileName);
		return in;
	}

	/**
	 * 获取WEB-INF/spring-cofng的目录路径
	 * 
	 * @return 返回WEB-INF/spring-cofng的路径
	 * @throws Exception
	 */
	public static String getSpringConfigPath() throws Exception {
		return getWebInfoPath() + DIRNAME_SPRING + File.separator;
	}

	/**
	 * 读取WEB-INF/spring-cofng目录下的文件内容
	 * 
	 * @param fileName
	 *            sping-config配置文件名
	 * @return 返回读取后的字节流
	 * @throws Exception
	 */
	public static InputStream readSpringConfigFile(String fileName)
			throws Exception {
		FileInputStream in = new FileInputStream(getWebInfoPath()
				+ DIRNAME_SPRING + File.separator + fileName);
		return in;
	}

	/**
	 * 获取WEB-INF/struts-cofng的目录路径
	 * 
	 * @return 返回WEB-INF/struts-cofng的路径
	 * @throws Exception
	 */
	public static String getStrutsConfigPath() throws Exception {
		return getWebInfoPath() + DIRNAME_STRUTS + File.separator;
	}

	/**
	 * 读取WEB-INF/struts-cofng目录下的文件内容
	 * 
	 * @param fileName
	 *            struts-config配置文件名
	 * @return 返回读取后的字节流
	 * @throws Exception
	 */
	public static InputStream readStrutsConfigFile(String fileName)
			throws Exception {
		FileInputStream in = new FileInputStream(getWebInfoPath()
				+ DIRNAME_STRUTS + File.separator + fileName);
		return in;
	}

	/**
	 * 用于WINDOWS中路径包含空格的字符路径
	 * 
	 * @param path
	 *            路径
	 * @return 返回字符转换后的路径
	 * @throws UnsupportedEncodingException
	 */
	private static String urlDecoder(String path)
			throws UnsupportedEncodingException {
		path = URLDecoder.decode(path, ENCODE);
		return path;
	}

	/**
	 * 根据操作系统在路径前加前缀
	 * 
	 * @param path
	 *            文件路径
	 * @return 如果是WINDOWS在路径前加"file:"
	 */
	private static String osOperator(String path) {
		if (System.getProperty("os.arch").equals("x86")) {
			if (path.startsWith("/")) {
				path = "file:" + path;
			} else {
				path = "file:" + File.separator + path;
			}
		}
		return path;
	}
}
