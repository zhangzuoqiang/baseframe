package cn.hs.commons.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5码工具类
 * 
 * @author Miao
 * @version 1.0
 */
public class Md5Util {

	/**
	 * 生成字符串的MD5码
	 * 
	 * @param text
	 *            字符串
	 * @return MD5码
	 * @since 1.0
	 */
	public static String getMd5(String text) {
		MessageDigest alga = null;
		try {
			alga = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		alga.update(text.getBytes());
		return byte2hex(alga.digest());
	}

	/**
	 * 将二进制比特数组转化为字符串
	 * 
	 * @param b
	 *            比特数组
	 * @return 字符串
	 * @since 1.0
	 */
	private static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs;
	}

}
