package cn.hs.core.log4j;

import org.apache.log4j.Logger;

/**
 * Title: Log4jLoggerFactory<br>
 * Description: Logger工厂类<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 1, 2011
 * @version $Revision: 1.1 $
 */
public class Log4jLoggerFactory {

	private static Log4jLoggerFactory SINGLETON = null;

	public Log4jLoggerFactory() {
	}

	public static Log4jLoggerFactory getInstance() {
		if (SINGLETON == null)
			SINGLETON = new Log4jLoggerFactory();
		return SINGLETON;
	}

	public Logger getLogger() {
		return Logger.getLogger("baseframe");
	}

}