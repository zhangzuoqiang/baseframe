/**
 * 
 */
package cn.hs.core.springext.mvc.propertyeditor;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 *
 * Title: CustomDateSerializer<br>
 * Description: spring MVC返回的json时间,使用jackson序列化返回的对象<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author GuoR
 * @version $Revision: 1.1 $
 */
public class CustomDateSerializer extends JsonSerializer<Date>{

	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		String formattedDate = formatter.format(value);  
		jgen.writeString(formattedDate);  
	}

}
