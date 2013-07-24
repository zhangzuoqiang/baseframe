package cn.hs.core.springext.security.source;

import org.springframework.context.ApplicationEvent;
/**
 * 
 * Title: LoadResourceDefineEvent<br>
 * Description: 监听器BaseSecurityMetadataSource监听的事件模型<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author RongLT
 * @createDate 2011-10-11
 * @version $Revision: 1.1 $
 */
public class LoadResourceDefineEvent extends ApplicationEvent{

	private static final long serialVersionUID = 1L;

	public LoadResourceDefineEvent(Object source) {
		super(source);
	}

}
