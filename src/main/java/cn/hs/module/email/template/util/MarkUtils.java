package cn.hs.module.email.template.util;

import java.util.Properties;

import org.springframework.util.PropertyPlaceholderHelper;

public class MarkUtils {
	
	public static final String PREFIX = "${";
	public static final String SUFFIX = "}";
	public static final String SEPARATOR = ",";
	
	/**
	 * 占位符替换
	 * @param str 带有占位符的字符串
	 * @param valueProperties 占位符与值的属性对象，如果为null，则直接返回str
	 * @return 将“${”开头，“}”结束的占位符替换成valueProperties中指定的值，如果占位符不存在则将占位符一同显示出来，
	 *         如果需要默认值则可写成类似 ${name,无姓名}的方式设置该name的默认值为“无姓名”
	 */
	public static String replacePlaceholders(String str,Properties valueProperties){
		if(valueProperties == null){
			return str;
		}
		PropertyPlaceholderHelper propertyPlaceholderHelper = new PropertyPlaceholderHelper(PREFIX,SUFFIX,SEPARATOR,true);
		return propertyPlaceholderHelper.replacePlaceholders(str, valueProperties);
	}
	
}
