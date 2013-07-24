package cn.hs.commons.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Title: PropertyUtil<br>
 * Description: 属性工具类<br>
 * Company: ORCHIS<br>
 * Copyright @ 2010 ORCHIS .All rights reserved.<br>
 * 
 * @author ZhangKW
 * @createDate 2010-4-1
 * @version $Revision: 1.1 $
 */
public class PropertyUtil {
	/**
	 * 验证传入对象不为空
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static boolean objectNotEmpty(Object obj) {
		boolean result = true;
		if (obj == null) {
			return false;
		}
		if (obj instanceof String) {
			if ("".equals(((String) obj).trim())) {
				result = false;
			}
		}
		if (obj instanceof Integer) {
			if (0 == ((Integer) obj).intValue()) {
				result = false;
			}
		}
		if (obj instanceof Long) {
			if (0 == ((Long) obj).intValue()) {
				result = false;
			}
		}
		if (obj instanceof String[]) {
			if (0 == ((String[]) obj).length) {
				result = false;
			}
		}
		if (obj instanceof Integer[]) {
			if (0 == ((Integer[]) obj).length) {
				result = false;
			}
		}
		if (obj instanceof Map) {
			if (((Map) obj).isEmpty()) {
				result = false;
			}
		}
		if (obj instanceof List) {
			if (((List) obj).size() == 0 || ((List) obj).isEmpty()) {
				result = false;
			}
		}
		if (obj instanceof Set) {
			if (((Set) obj).isEmpty() || ((Set) obj).size() == 0) {
				result = false;
			}
		}
		return result;
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
	 * 对象属性拷贝，实现动态更新
	 * 
	 * @param dbObj
	 * @param obj
	 * @throws Exception
	 * @author ZhangKW
	 * @date 2010-11-23
	 */
	public static void propertyDynamicUpdate(Object dbObj, Object obj)
			throws Exception {

		String[] fieldNameArray = PropertyUtil.getAllFieldName(obj);
		for (int i = 0; i < fieldNameArray.length; i++) {
			String fieldName = fieldNameArray[i];
			Object value = PropertyUtil.getFieldValueObj(obj, fieldName);
			if (value != null) {
				PropertyUtil.setFieldValueObj(dbObj, fieldName, value);
			}
		}
	}

	/**
	 * 获取参数对象的指定属性名的属性值
	 * 
	 * @param o
	 *            待反射的对象
	 * @param field
	 *            待反射的属性名
	 * @return
	 * @throws Exception
	 * @author ZhangKW
	 * @date 2010-11-23
	 */
	private static Object getFieldValueObj(Object o, String field)
			throws Exception {
		Method getMethod;
		Object returnObj;
		Class clazz = o.getClass();
		getMethod = clazz.getMethod(catchMethodName("get", field), null);
		returnObj = getMethod.invoke(o, null);
		return returnObj;
	}

	/**
	 * 设置对象的指定属性值
	 * 
	 * @param o
	 *            待反射的对象
	 * @param field
	 *            待反射的属性名
	 * @param value
	 *            填充值
	 * @throws Exception
	 * @author ZhangKW
	 * @date 2010-11-23
	 */
	private static void setFieldValueObj(Object o, String field, Object value)
			throws Exception {
		Class<? extends Object> clazz = o.getClass();
		Method setMethod = null;
		// 增加对HashSet类型的处理,所有HashSet类型数据均以Set类型处理 modify by Huosd 2011-04-13
		if (value instanceof java.util.HashSet) {
			setMethod = clazz
					.getMethod(
							catchMethodName("set", field),
							new Class[] { value instanceof java.util.HashSet ? Set.class
									: value.getClass() });
		} else {
			setMethod = clazz
					.getMethod(
							catchMethodName("set", field),
							new Class[] { value instanceof java.sql.Timestamp ? Date.class
									: value.getClass() });
		}
		setMethod.invoke(o, new Object[] { value });
	}

	/**
	 * 获得对象属性名字符串数组
	 * 
	 * @param obj
	 * @return
	 * @author ZhangKW
	 * @date 2010-11-23
	 */
	private static String[] getAllFieldName(Object obj) {
		ArrayList<String> methodList = new ArrayList<String>();
		Method[] methods = obj.getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			if (method.getName().startsWith("get")
					&& method.getReturnType() != null
					&& !"getClass".equals(method.getName()))
				methodList.add(catchFieldByMethodName(method.getName()));
		}
		return (String[]) methodList.toArray(new String[methodList.size()]);
	}

	/**
	 * 抽取属性名称
	 * 
	 * @param methodName
	 * @return
	 * @author ZhangKW
	 * @date 2010-11-23
	 */
	private static String catchFieldByMethodName(String methodName) {
		char[] charArray = methodName.substring(3).toCharArray();
		charArray[0] = Character.toLowerCase(charArray[0]);
		return new String(charArray);
	}

	/**
	 * 获得对象方法名
	 * 
	 * @param getterOrSetter
	 * @param fieldName
	 * @return
	 * @author ZhangKW
	 * @date 2010-11-22
	 */
	private static String catchMethodName(String getterOrSetter,
			String fieldName) {
		char[] charArray = fieldName.toCharArray();
		charArray[0] = Character.toUpperCase(charArray[0]);
		return getterOrSetter + new String(charArray);
	}

}
