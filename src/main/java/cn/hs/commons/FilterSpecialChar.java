package cn.hs.commons;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/*******************************************************************************
 * @file: FilterSpecialCangwei
 * @description: 过滤数据库敏感字符
 ******************************************************************************/
public class FilterSpecialChar {
	/**
	 * @description 替换查询条件对象中字符串中的数据库敏感字符 ' to ＇
	 * @param condition
	 * @author wangwei
	 */
	public static void filter(Object condition) {
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(condition.getClass());
			PropertyDescriptor[] pd = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < pd.length; i++) {

				Class type = pd[i].getPropertyType();

				if ("java.lang.String".equals(type.getName())) {

					Method readMethod = pd[i].getReadMethod();
					Object proValue = readMethod.invoke(condition, null);
					if (proValue == null) {
						proValue = "";
					}
					String newProValue = filter(proValue.toString());

					Method writeMethod = pd[i].getWriteMethod();
					writeMethod.invoke(condition, new Object[] { newProValue });

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 替换'为＇
	 * 
	 * @param str
	 * @return
	 * @author wangwei
	 */
	public static String filter(String str) {
		String newStr = str.replace('\'', ',');
		return newStr;
	}

	public static void main(String[] args) {
		String str = "i'm wang'wei";
		String strs = FilterSpecialChar.filter(str);
		System.out.print(strs);

	}

}
