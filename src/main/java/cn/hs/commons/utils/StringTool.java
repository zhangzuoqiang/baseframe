package cn.hs.commons.utils;

import java.util.LinkedList;
import java.util.List;

/**
 * 提供常用String的操作类包含
 * 
 * @author panww
 * 
 */
public class StringTool {

	/**
	 * Take a String which is a delimited list and convert it to a String array
	 * 
	 * @param s
	 *            String
	 * @param delimiter
	 *            delimiter. This will not be returned
	 * @return an array of the tokens in the list
	 */
	public static String[] delimitedListToStringArray(String s, String delimiter) {
		if (s == null || "".equals(s))
			return null;
		if (delimiter == null)
			return new String[] { s };
		List l = new LinkedList();
		int pos = 0;
		int delpos = 0;
		while ((delpos = s.indexOf(delimiter, pos)) != -1) {
			l.add(s.substring(pos, delpos));
			pos = delpos + delimiter.length();
		}
		if (pos <= s.length()) {
			l.add(s.substring(pos));
		}
		return (String[]) l.toArray(new String[l.size()]);
	}

	public static Integer[] converStringToInteger(String[] ids) {
		if (ids != null) {
			Integer[] users = new Integer[ids.length];
			for (int i = 0; i < ids.length; i++) {
				if (ids[i] != null && !"".equals(ids[i])) {
					users[i] = new Integer(ids[i]);
				}
			}
			return users;
		} else {
			return new Integer[0];
		}
	}

	public static String arrayToString(String dataIndexs[]) {
		String indexs = "";
		for (int i = 0; i < dataIndexs.length; i++) {
			indexs += dataIndexs[i] + ",";
		}
		return indexs.substring(0, indexs.length() - 1);
	}

	/**
	 * 查询属性的SQL注入过滤,将 ' 过滤为 _
	 * 
	 * @param queryProperty
	 * @return
	 * @throws Exception
	 */
	public static String sqlInjectionFilter(String queryProperty)
			throws Exception {
		if (queryProperty != null && queryProperty.indexOf("'") != -1) {
			queryProperty = queryProperty.replaceAll("\'", "_");
		}
		return queryProperty;
	}

	/**
	 * 按指定规格省略字符串 2010.1.10 Create by zkw
	 * 
	 * @param stringValue
	 *            传入字符串
	 * @param showLength
	 *            显示长度
	 * @param omitStyle
	 *            省略后补充样式 如"..."
	 * @return
	 * @throws Exception
	 */
	public static String stringOmit(String stringValue, int showLength,
			String omitStyle) {
		if (stringValue != null && !"".equals(stringValue)) {
			StringBuffer resultBuffer = new StringBuffer();
			int checkValue = 0;
			char charValue = ' ';
			boolean stopSing = true;
			int thisLength = 0;
			for (int index = 0; index < stringValue.length(); index++) {
				checkValue = (int) stringValue.charAt(index);
				charValue = stringValue.charAt(index);
				resultBuffer.append(charValue);
				thisLength++;
				if ((checkValue >= 47 && checkValue <= 58)
						|| (checkValue >= 64 && checkValue <= 91)
						|| (checkValue >= 96 && checkValue <= 123)) {
					if (stopSing) {
						thisLength--;
						stopSing = false;
					} else {
						stopSing = true;
					}
				}
				if (thisLength > showLength - 1) {
					resultBuffer.append(omitStyle);
					break;
				}
			}
			return resultBuffer.toString();
		} else {
			return stringValue;
		}

	}

	/**
	 * 查询一个字符在字符串中存在个数
	 * 
	 * @param ch
	 * @return
	 * @author WangWB
	 * @date Dec 22, 2011
	 */
	public static int searchCharCountFromString(char ch, String str) {
		int sum = 0;
		for (int i = 0; i < str.length(); i++) {
			if (ch == str.charAt(i)) {
				sum++;
			}
		}
		return sum;
	}
	
	/**
	 * 将String[] 数组转换成 Integer[]
	 * @param ids
	 * @return
	 * @author XiangBin
	 * @date Aug 27, 2012
	 */
	public static Long[] converStringToLong(String[] ids) {
		if (ids != null) {
			Long[] users = new Long[ids.length];
			for (int i = 0; i < ids.length; i++) {
				if (ids[i] != null && !"".equals(ids[i])) {
					users[i] = new Long(ids[i]);
				}
			}
			return users;
		} else {
			return new Long[0];
		}
	}
	
	
	/**
	 * 截掉字符串最后的字符 2012.9.3 Create by XiangBin
	 * 
	 * @param s
	 *            传入字符串
	 * @param character
	 *            指定最后的字符
	 * @return
	 * @throws Exception
	 */
	public static String subStringLastCharacter(String s, String character){
		if(s != null && !"".equals(s)){
			if(s.indexOf(character) != -1 && s.lastIndexOf(character) == (s.length()-1)){
				s = s.substring(0, s.length() -1);
			}
		}
		return s;
	}
}
