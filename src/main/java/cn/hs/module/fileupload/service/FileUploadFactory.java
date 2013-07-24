package cn.hs.module.fileupload.service;

import cn.hs.commons.utils.SpringBeanUtil;
import cn.hs.module.fileupload.service.impl.FileUploadDISKServiceImpl;

/**
 * 
 * Title: FileUploadFactory<br>
 * Description: 附件上传工厂类<br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Sep 14, 2012
 * @version $Revision:$
 */
public class FileUploadFactory {

	private FileUploadFactory() {
	}

	/**
	 * 硬盘实现
	 * 
	 * @return
	 * @author HuangS
	 * @date Sep 14, 2012
	 */
	public static IFileUploadService getUploadDISK() {
		return SpringBeanUtil.getBean(FileUploadDISKServiceImpl.class);
	}

	/**
	 * 数据库实现
	 * 
	 * @return
	 * @author HuangS
	 * @date Sep 14, 2012
	 */
	public static IFileUploadService getUploadDB() {
		return (IFileUploadService) SpringBeanUtil.getBean("cn.hs.module.fileupload.service.impl.FileUploadDBServiceImpl");
	}

}
