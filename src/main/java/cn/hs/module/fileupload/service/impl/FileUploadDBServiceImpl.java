package cn.hs.module.fileupload.service.impl;

import java.io.InputStream;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hs.module.blob.domain.BaseBlob;
import cn.hs.module.blob.service.IBlobService;
import cn.hs.module.fileupload.service.IFileUploadService;

/**
 * 
 * Title: FileUploadDBServiceImpl<br>
 * Description: 附件上传数据库实现<br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Sep 14, 2012
 * @version $Revision:$
 */
@Service(value = "cn.hs.module.fileupload.service.impl.FileUploadDBServiceImpl")
public class FileUploadDBServiceImpl implements IFileUploadService {

	// 大字段接口
	@Autowired
	private IBlobService blobService;

	@Override
	public String fileUpload(InputStream in, String fileName) throws Exception {
		// 获取扩展名
		String extensionName = fileName.substring(fileName.lastIndexOf("."));
		BaseBlob blob = new BaseBlob();
		// 存储文件名称和文件类型
		blob.setAttName(fileName);
		blob.setAttExt(extensionName);
		// 设置存储内容
		blob.setAttBlob(Hibernate.createBlob(in));
		blobService.addBlob(blob);
		return "module/baseblob/showImage.do?blobID=" + blob.getBlobID();
	}

}
