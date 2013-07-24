package cn.hs.commons.utils;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 * 
 * Title: JSONUtils<br>
 * Description: json工具类<br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate 2012-11-15
 * @version $Revision:$
 */
public class JSONUtils {

	/**
	 * fromJsonToObject<br>
	 * jackjson把json字符串转换为Java对象的实现方法
	 * 
	 * <pre>
	 * return Jackson.jsonToObj(this.answersJson, new TypeReference&lt;List&lt;StanzaAnswer&gt;&gt;() {
	 * });
	 * </pre>
	 * 
	 * @param <T>
	 *            转换为的java对象
	 * @param json
	 *            json字符串
	 * @param typeReference
	 *            jackjson自定义的类型
	 * @return 返回Java对象
	 */
	public static <T> T jsonToObj(String json, TypeReference<T> typeReference) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, typeReference);
	}

	/**
	 * json字符串转换为java对象
	 * 
	 * @param json
	 *            字符串
	 * @param valueType
	 *            对象的class
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date 2012-11-15
	 */
	public static <T> T jsonToObj(String json, Class<T> valueType) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, valueType);
	}

	/**
	 * java对象转换为json字符串
	 * 
	 * @param object
	 *            要转换的对象
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date 2012-11-15
	 */
	@SuppressWarnings("deprecation")
	public static String objToJson(Object object) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}

}
