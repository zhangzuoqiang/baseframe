package cn.hs.module.fileupload.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hs.module.fileupload.service.IFileUploadService;
import cn.hs.module.systemparameter.SystemParameterConstant;
import cn.hs.module.systemparameter.service.ISystemParameterCacheService;

/**
 * 
 * Title: FileUploadDISKServiceImpl<br>
 * Description: 附件上传硬盘实现<br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Sep 14, 2012
 * @version $Revision:$
 */
@Service(value = "cn.hs.module.fileupload.service.impl.FileUploadDISKServiceImpl")
public class FileUploadDISKServiceImpl implements IFileUploadService {

	// 系统参数缓存
	@Autowired
	private ISystemParameterCacheService parameterCacheService;

	@Override
	public String fileUpload(InputStream in, String fileName) throws Exception {

		/* 获取文件扩展名 */
		/* 索引加1的效果是只取xxx.jpg的jpg */
		String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);

		String rootPath = System.getProperty("baseframe.root");

		/* 获取文件上传存储的相对路径 */
		String baseDir = parameterCacheService.getSystemParameter(SystemParameterConstant.FOLDER_BASE_DIR);
		if (baseDir == null || "".equals(baseDir)) {
			baseDir = "ARTICLE";
		}

		String fileFolder = new SimpleDateFormat("yyyyMMdd").format(new Date());

		/* 文件存储的相对路径 */
		String saveDirPath = baseDir + "/" + fileFolder + "/";

		/* 文件存储在容器中的绝对路径 */
		String saveFilePath = rootPath + "/" + saveDirPath;

		/* 构建文件目录以及目录文件 */
		File fileDir = new File(saveFilePath);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}

		/* 重命名文件 */
		String newFileName = UUID.randomUUID().toString();
		File savefile = new File(saveFilePath + newFileName + "." + extensionName);

		/* 存储上传文件 */
		this.fileCopy(in, savefile);

		// 返回文件相对路径
		return saveDirPath + newFileName + "." + extensionName;
	}

	/**
	 * 上传的文件保存至硬盘
	 * 
	 * @param in
	 * @param savefile
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 14, 2012
	 */
	private void fileCopy(InputStream in, File savefile) throws Exception {
		BufferedInputStream buffIn = null;
		BufferedOutputStream buffOut = null;
		try {
			buffIn = new BufferedInputStream(in);
			buffOut = new BufferedOutputStream(new FileOutputStream(savefile));
			int b;
			while ((b = buffIn.read()) != -1)
				buffOut.write(b);
		} finally {
			if (in != null) {
				in.close();
			}
			if (buffOut != null) {
				buffOut.close();
			}
			if (buffIn != null) {
				buffIn.close();
			}
		}
	}

}
