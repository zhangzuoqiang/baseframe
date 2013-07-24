/*
 * $Log: CustomDateEditorExt.java,v $
 * Revision 1.1  2012/05/23 09:27:49  guor
 * 初次提交
 *
 * Revision 1.1  2011/06/01 05:46:57  kkaiwen
 * 2011.6.1 create
 *
 */
package cn.hs.core.springext.mvc.propertyeditor;
import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.StringUtils;
/**
 * Title: 日期类型属性编辑器<br>
 * Description: Controller绑定数据时需要调用该编辑器处理日期型数据<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author MengW
 * @createDate 2011-5-16
 * @version $Revision: 1.1 $
 */
public class CustomDateEditorExt extends PropertyEditorSupport{
	 /**
	   * 日期格式化对象
	   */
	  private final DateFormat dateFormat;
	  /**
	   * 数据是否可以为空
	   */
	  private final boolean allowEmpty;
	  
	  private final int exactDateLength;
	  /**
	   * 格式
	   */
	  private final String pattern;
	  
	  public CustomDateEditorExt(DateFormat dateFormat, boolean allowEmpty,String pattern)
	  {
	    this.dateFormat = dateFormat;
	    this.allowEmpty = allowEmpty;
	    this.exactDateLength = -1;
	    this.pattern = pattern;
	  }

	  public CustomDateEditorExt(DateFormat dateFormat, boolean allowEmpty, int exactDateLength,String pattern)
	  {
	    this.dateFormat = dateFormat;
	    this.allowEmpty = allowEmpty;
	    this.exactDateLength = exactDateLength;
	    this.pattern = pattern;
	  }
	  
	  /**
	   * spring mvc 绑定数据自动调用该方法，text为数据值
	   */
	  public void setAsText(String text)
	    throws IllegalArgumentException
	  {
	    if ((this.allowEmpty) && (!(StringUtils.hasText(text))))
	    {
	      setValue(null);
	    } else {
	      if ((text != null) && (this.exactDateLength >= 0) && (text.length() != this.exactDateLength)) {
	        throw new IllegalArgumentException(
	          "Could not parse date: it is not exactly" + this.exactDateLength + "characters long");
	      }
	      try
	      {
	    	if(pattern.indexOf("-")!=-1){
	    		if(pattern.indexOf(":")!=-1&&text.indexOf(":")==-1){
	    			text+=" 00:00:00";
	    		}
	    		setValue(this.dateFormat.parse(text));
	    	}else{
	    		Calendar calendar = Calendar.getInstance();
			    calendar.setTimeInMillis(Long.parseLong(text));
			    setValue(calendar.getTime());
		    	
	    	}
	      }
	      catch (ParseException ex) {
	        throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
	      }
	    }
	  }

	  public String getAsText()
	  {
	    Date value = (Date)getValue();
	    return ((value != null) ? this.dateFormat.format(value) : "");
	  }

}
