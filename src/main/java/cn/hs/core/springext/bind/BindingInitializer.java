/*
 * $Log: BindingInitializer.java,v $
 * Revision 1.1  2012/05/23 09:27:49  guor
 * 初次提交
 *
 * Revision 1.1  2011/06/01 05:46:53  kkaiwen
 * 2011.6.1 create
 *
 */
package cn.hs.core.springext.bind;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import cn.hs.core.configure.GlobalConfigure;
import cn.hs.core.springext.mvc.propertyeditor.CustomDateEditorExt;

/**
 * Title: BindingInitializer<br>
 * Description: <br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * 
 * @author MengW
 * @createDate 2011-5-16
 * @version $Revision: 1.1 $
 */
public class BindingInitializer extends ConfigurableWebBindingInitializer {

	@Autowired
	private GlobalConfigure globalConfigure;

	public void initBinder(WebDataBinder binder, WebRequest request) {
		// 日期类型
		DateFormat dateFormat = new SimpleDateFormat(globalConfigure
				.getCommonDatePattern());
		dateFormat.setLenient(false);
		CustomDateEditorExt editor = new CustomDateEditorExt(dateFormat, true,
				globalConfigure.getCommonDatePattern());
		binder.registerCustomEditor(Date.class, editor);
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(
				Integer.class, true));
		binder.registerCustomEditor(Double.class, new CustomNumberEditor(
				Double.class, true));
		super.initBinder(binder, request);
	}
}
