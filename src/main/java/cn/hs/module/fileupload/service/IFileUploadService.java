package cn.hs.module.fileupload.service;

import java.io.InputStream;

/**
 * 
 * Title: IFileUploadService<br>
 * Description: 附件上传接口<br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Sep 14, 2012
 * @version $Revision:$
 */
public interface IFileUploadService {

	/**
	 * 附件上传
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 14, 2012
	 */
	public String fileUpload(InputStream in, String fileName) throws Exception;

}
