package cn.hs.commons.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Title: TxtFileUtils<br>
 * Description: 文本文件工具类<br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate 2012-11-13
 * @version $Revision:$
 */
public class TxtFileUtils {

	/**
	 * 回车符
	 */
	public static final String ENTER_STR = "\r\n";

	/**
	 * tab符
	 */
	public static final String TAB_STR = "\t";

	/**
	 * 读取文本文件
	 * 
	 * @param file
	 *            需要读取的文件
	 * @param charsetName
	 *            文本文件字符集
	 * @return 返回文件内容，如果路径为文件夹返回空字符串
	 * @throws Exception
	 * @author HuangS
	 * @date 2012-11-13
	 */
	public static String readFile(File file, String charsetName) throws Exception {
		String content = "";
		if (file.exists()) {
			if (file.isFile()) {
				// 是文件才读取
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charsetName));
				String temp;
				try {
					while ((temp = reader.readLine()) != null) {
						content += temp + "\r\n";
					}
				} finally {
					if (reader != null) {
						reader.close();
					}
				}
			} else {
				throw new Exception("需要读取的文件非文件类型！");
			}
		} else {
			throw new Exception("需要读取的文件不存在！" + file.getPath());
		}
		return content;
	}

	/**
	 * 读取文本文件,按UTF-8字符集读取
	 * 
	 * @param file
	 *            需要读取的文件
	 * @return 返回文件内容，如果路径为文件夹返回空字符串
	 * @throws Exception
	 * @author HuangS
	 * @date 2012-11-13
	 */
	public static String readFile(File file) throws Exception {
		return readFile(file.getPath(), "UTF-8");
	}

	/**
	 * 读取文本文件
	 * 
	 * @param filePath
	 *            需要读取的文件路径
	 * @param charsetName
	 *            文本文件字符集
	 * @return 返回文件内容，如果路径为文件夹返回空字符串
	 * @throws Exception
	 * @author HuangS
	 * @date 2012-11-13
	 */
	public static String readFile(String filePath, String charsetName) throws Exception {
		File file = new File(filePath);
		return readFile(file, charsetName);
	}

	/**
	 * 读取文本文件,按UTF-8字符集读取
	 * 
	 * @param filePath
	 *            需要读取的文件路径
	 * @return 返回文件内容，如果路径为文件夹返回空字符串
	 * @throws Exception
	 * @author HuangS
	 * @date 2012-11-13
	 */
	public static String readFile(String filePath) throws Exception {
		return readFile(filePath, "UTF-8");
	}

	/**
	 * 读取文件夹下的文本文件(需要保证该文件夹下全部为文本文件,否则可能出错)
	 * 
	 * @param folder
	 *            需要读取的文件夹(自动忽略文件夹下的子文件夹)
	 * @param charsetName
	 *            文本文件字符集
	 * @return 读取的非文件夹或者无子文件返回size为0的集合
	 * @throws Exception
	 * @author HuangS
	 * @date 2012-11-13
	 */
	public static Map<String, String> readFolder(File folder, String charsetName) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		if (folder.exists()) {
			if (folder.isDirectory()) {
				File[] subFiles = folder.listFiles();
				for (File file : subFiles) {
					if (file.isFile()) {
						String content = readFile(file, charsetName);
						map.put(file.getName(), content);
					}
				}
			} else {
				throw new Exception("需要读取的文件夹非文件夹类型！");
			}
		} else {
			throw new Exception("需要读取的文件夹不存在！");
		}
		return map;
	}

	/**
	 * 读取文件夹下的文本文件,按UTF-8字符集读取(需要保证该文件夹下全部为文本文件,否则可能出错)
	 * 
	 * @param folder
	 *            需要读取的文件夹(自动忽略文件夹下的子文件夹)
	 * @return 读取的非文件夹或者无子文件返回size为0的集合
	 * @throws Exception
	 * @author HuangS
	 * @date 2012-11-13
	 */
	public static Map<String, String> readFolder(File folder) throws Exception {
		return readFolder(folder, "UTF-8");
	}

	/**
	 * 读取文件夹下的文本文件(需要保证该文件夹下全部为文本文件,否则可能出错)
	 * 
	 * @param folderPath
	 *            需要读取的文件夹路径(自动忽略文件夹下的子文件夹)
	 * @param charsetName
	 *            文本文件字符集
	 * @return 读取的非文件夹或者无子文件返回size为0的集合
	 * @throws Exception
	 * @author HuangS
	 * @date 2012-11-13
	 */
	public static Map<String, String> readFolder(String folderPath, String charsetName) throws Exception {
		File folder = new File(folderPath);
		return readFolder(folder, charsetName);
	}

	/**
	 * 读取文件夹下的文本文件,按UTF-8字符集读取(需要保证该文件夹下全部为文本文件,否则可能出错)
	 * 
	 * @param folderPath
	 *            需要读取的文件夹路径(自动忽略文件夹下的子文件夹)
	 * @return 读取的非文件夹或者无子文件返回size为0的集合
	 * @throws Exception
	 * @author HuangS
	 * @date 2012-11-13
	 */
	public static Map<String, String> readFolder(String folderPath) throws Exception {
		return readFolder(folderPath, "UTF-8");
	}

	/**
	 * 向硬盘写入文件
	 * 
	 * @param file
	 *            目标文件
	 * @param content
	 *            输出内容
	 * @param charsetName
	 *            字符集
	 * @throws Exception
	 * @author HuangS
	 * @date 2012-11-13
	 */
	public static void writeFile(File file, String content, String charsetName) throws Exception {
		File parentFolder = file.getParentFile();
		if (!parentFolder.exists()) {
			parentFolder.mkdirs();
		}
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charsetName));
		try {
			writer.write(content);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	/**
	 * 向硬盘写入文件,按照UTF-8字符集输出
	 * 
	 * @param file
	 *            目标文件
	 * @param content
	 *            输出内容
	 * @throws Exception
	 * @author HuangS
	 * @date 2012-11-13
	 */
	public static void writeFile(File file, String content) throws Exception {
		writeFile(file, content, "UTF-8");
	}

	/**
	 * 向硬盘写入文件
	 * 
	 * @param file
	 *            目标文件
	 * @param content
	 *            输出内容
	 * @param charsetName
	 *            字符集
	 * @throws Exception
	 * @author HuangS
	 * @date 2012-11-13
	 */
	public static void writeFile(String filePath, String content, String charsetName) throws Exception {
		File file = new File(filePath);
		writeFile(file, content, charsetName);
	}

	/**
	 * 向硬盘写入文件,按照UTF-8字符集输出
	 * 
	 * @param file
	 *            目标文件
	 * @param content
	 *            输出内容
	 * @throws Exception
	 * @author HuangS
	 * @date 2012-11-13
	 */
	public static void writeFile(String filePath, String content) throws Exception {
		writeFile(filePath, content, "UTF-8");
	}

	/**
	 * 拷贝文件
	 * 
	 * @param source
	 *            原文件
	 * @param dest
	 *            目标文件
	 * @param isCut
	 *            是否为剪切
	 * @throws Exception
	 * @author HuangS
	 * @date 2012-11-13
	 */
	public static void copyFile(File source, File dest, boolean isCut) throws Exception {
		if (source.exists()) {
			if (source.isFile()) {
				File parentFolder = dest.getParentFile();
				if (!parentFolder.exists()) {
					parentFolder.mkdirs();
				}
				FileChannel in = null;
				FileChannel out = null;
				try {
					in = new FileInputStream(source).getChannel();
					out = new FileOutputStream(dest).getChannel();
					in.transferTo(0, in.size(), out);
				} finally {
					if (in != null) {
						try {
							in.close();
						} catch (Throwable t) {
						}
					}
					if (out != null)
						out.close();
				}
				dest.setLastModified(source.lastModified());
				if (isCut) {
					source.delete();
				}
			} else {
				throw new Exception("原文件非文件格式！");
			}
		} else {
			throw new Exception("原文件不存在！");
		}
	}

	/**
	 * 拷贝文件
	 * 
	 * @param source
	 *            原文件
	 * @param dest
	 *            目标文件
	 * @throws Exception
	 * @author HuangS
	 * @date 2012-11-13
	 */
	public static void copyFile(File source, File dest) throws Exception {
		copyFile(source, dest, false);
	}

	/**
	 * 拷贝文件夹
	 * 
	 * @param source
	 *            原文件夹
	 * @param dest
	 *            目标文件夹
	 * @param isCut
	 *            是否剪切
	 * @throws Exception
	 * @author HuangS
	 * @date 2012-11-13
	 */
	public static void copyFolder(File source, File dest, boolean isCut) throws Exception {
		if (source.exists()) {
			if (!source.isDirectory()) {
				throw new Exception("原文件夹不是一个有效的文件夹！");
			}
			File files[], destFile, destDir;
			String name;
			boolean exists = dest.exists();
			if (!exists) {
				dest.mkdirs();
			}
			destDir = dest;
			files = source.listFiles();
			for (File file : files) {
				name = file.getName();
				destFile = new File(destDir, name);
				if (file.isFile())
					copyFile(file, destFile, isCut);
				else
					copyFolder(file, destFile, isCut);
			}
			if (isCut) {
				source.delete();
			}
		} else {
			throw new Exception("原文件夹不存在！");
		}
	}

	/**
	 * 拷贝文件夹
	 * 
	 * @param source
	 *            原文件夹
	 * @param dest
	 *            目标文件夹
	 * @throws Exception
	 * @author HuangS
	 * @date 2012-11-13
	 */
	public static void copyFolder(File source, File dest) throws Exception {
		copyFolder(source, dest, false);
	}

	/**
	 * 删除文件
	 * 
	 * @param file
	 * @return 是否删除成功
	 * @throws Exception
	 * @author HuangS
	 * @date 2012-11-26
	 */
	public static boolean deleteFile(File file) throws Exception {
		if (file.exists()) {
			return file.delete();
		} else {
			return false;
		}
	}

	/**
	 * 删除文件夹
	 * 
	 * @param folder
	 * @return 是否删除成功
	 * @throws Exception
	 * @author HuangS
	 * @date 2012-11-26
	 */
	public static boolean deleteFolder(File folder) throws Exception {
		if (folder.exists()) {
			if (folder.isDirectory()) {
				File[] subFiles = folder.listFiles();
				for (File subFile : subFiles) {
					if (subFile.isFile()) {
						deleteFile(subFile);
					} else {
						deleteFolder(subFile);
					}
				}
			}
			return deleteFile(folder);
		} else {
			return false;
		}
	}

	/**
	 * 按照后缀名称截取文件名
	 * 
	 * @param file
	 *            要截取的文件
	 * @param pxType
	 *            文件后缀
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date 2012-11-27
	 */
	public static String subFileNameWithOutType(File file, String pxType) throws Exception {
		if (file != null) {
			String fileName = file.getName();
			if (PropertyUtil.objectNotEmpty(fileName)) {
				return fileName.substring(0, fileName.lastIndexOf("." + pxType));
			}
		}
		return "";
	}

}
