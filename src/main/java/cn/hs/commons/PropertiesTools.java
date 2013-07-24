package cn.hs.commons;

/*******************************************************************************
 * file: PropertiesTools.java project: uum date: 2007-8-28
 * 
 * @author wangwei description:
 ******************************************************************************/
public class PropertiesTools {

	public static String buildGetter(String property) {
		String proHead = property.substring(0, 1);
		proHead = proHead.toUpperCase();
		return "get" + proHead + property.substring(1, property.length());
	}

	public static String buildSetter(String property) {
		String proHead = property.substring(0, 1);
		proHead = proHead.toUpperCase();
		return "set" + proHead + property.substring(1, property.length());
	}

}
