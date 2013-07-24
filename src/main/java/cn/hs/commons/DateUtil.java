/*
 * $Log: DateUtil.java,v $
 * Revision 1.1  2012/05/23 09:27:44  guor
 * 初次提交
 *
 * Revision 1.1  2010/04/18 15:55:35  mengw
 * 首次增加
 *
 */
package cn.hs.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Title: DataUtil<br>
 * Description: <br>
 * Company: ORCHIS<br>
 * Copyright @ 2010 ORCHIS .All rights reserved.<br>
 * @author MengW
 * @createDate Apr 18, 2010
 * @version $Revision: 1.1 $
 */
public class DateUtil {

	private static SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static SimpleDateFormat sdFormatDay = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 返回日期型字符串
	 * 
	 * @param date
	 * @return
	 * @author MengW
	 * @date Apr 17, 2010
	 */
	public static String getDateString(Date date) {
		if (date != null) {
			return sdFormat.format(date);
		}
		return "";
	}

	/**
	 * 返回日期型字符串
	 * 
	 * @param time
	 *            待格式化的日期值
	 * @param isMillis
	 *            是否为毫秒值
	 * @param formatTime
	 *            是否带时间
	 * @return
	 * @author HuangS
	 * @date Apr 30, 2013
	 */
	public static String getDateString(Long time, boolean isMillis, boolean formatTime) {
		if (time != null) {
			if (!isMillis) {
				time = time * 1000;
			}
			if (formatTime) {
				return sdFormat.format(new Date(time));
			} else {
				return sdFormatDay.format(new Date(time));
			}
		}
		return "";
	}

	/**
	 * 返回日期型字符串
	 * 
	 * @param time
	 *            日期毫秒值
	 * @return 返回带时分秒的日期字符串
	 * @author HuangS
	 * @date Apr 30, 2013
	 */
	public static String getDateString(Long time) {
		return getDateString(time, true, true);
	}

	/**
	 * 获取当前天的毫秒值
	 * 
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date May 4, 2013
	 */
	public static Long getNowDayTimeLong() throws Exception {
		String nowDay = sdFormatDay.format(new Date());
		return sdFormatDay.parse(nowDay).getTime();
	}

	/**
	 * 获取当前时间的毫秒值(用于存储数据库)
	 * 
	 * @return
	 * @author HuangS
	 * @date Apr 30, 2013
	 */
	public static Long getCurrentTimeLong() {
		return new Date().getTime();
	}

	/**
	 * 返回不带时分秒的日期型字符串
	 * 
	 * @param time
	 *            日期秒值
	 * @return 返回不带时分秒的日期字符串
	 * @author HuangS
	 * @date Apr 30, 2013
	 */
	public static String getDateStringNoTime(Long time) {
		return getDateString(time, false, false);
	}

	/**
	 * 
	 * @param dateString
	 * @return
	 * @author MengW
	 * @throws ParseException
	 * @date Apr 18, 2010
	 */
	public static Date getDateByString(String dateString) throws ParseException {
		if (dateString != null && !"".equals(dateString)) {
			return sdFormat.parse(dateString);
		}
		return null;
	}

	/**
	 * 
	 * @param date
	 * @param format
	 * @return
	 * @author MengW
	 * @date Apr 18, 2010
	 */
	public static String getDateString(Date date, String format) {
		if (date != null) {
			SimpleDateFormat sdFormat = new SimpleDateFormat(format);
			return sdFormat.format(date);
		}
		return "";
	}

	/**
	 * 
	 * @param dateString
	 * @param format
	 * @return
	 * @throws ParseException
	 * @author MengW
	 * @date Apr 18, 2010
	 */
	public static Date getDateByString(String dateString, String format) throws ParseException {
		if (dateString != null && !"".equals(dateString)) {
			SimpleDateFormat sdFormat = new SimpleDateFormat(format);
			return sdFormat.parse(dateString);
		}
		return null;
	}
}
