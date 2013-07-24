package cn.hs.module.blob.ui.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hs.module.blob.domain.BaseBlob;
import cn.hs.module.blob.domain.BlobCondition;
import cn.hs.module.blob.service.IBlobService;
import cn.hs.module.blob.ui.commond.BaseBlobValidCommond;

/**
 * 
 * Title: BaseBlobController<br>
 * Description: 大字段controller<br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Sep 14, 2012
 * @version $Revision:$
 */
@Controller
@RequestMapping(value = BaseBlobController.modulePath)
public class BaseBlobController {
	// 模块页面根路径
	public static final String modulePath = "/module/baseblob";
	@Autowired
	IBlobService blobService;

	@RequestMapping(value = "/deleteBaseBlobById")
	public @ResponseBody
	String deleteBaseBlobById(Model model, HttpServletRequest request, BaseBlobValidCommond baseBlobValidCommond) throws Exception {
		if (baseBlobValidCommond.getBlobID() != null && !"".equals(baseBlobValidCommond.getBlobID())) {
			blobService.deleteBlobById(baseBlobValidCommond.getBlobID());
		}
		return "success";
	}

	/**
	 * 图片显示
	 * 
	 * @param resp
	 * @param commond
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 14, 2012
	 */
	@RequestMapping(value = "/showImage")
	public void showImage(HttpServletResponse resp, BaseBlobValidCommond commond) throws Exception {
		OutputStream writer = null;
		BufferedOutputStream bufferWriter = null;
		InputStream reader = null;
		BufferedInputStream bufferReader = null;

		int BUFFERSIZE = 4096; // 缓存读取大小
		try {
			BlobCondition condition = new BlobCondition();
			condition.setBlobId(commond.getBlobID());
			BaseBlob entity = blobService.getBlob(condition);
			if (entity != null) {
				Blob blob = entity.getAttBlob();
				writer = resp.getOutputStream();
				bufferWriter = new BufferedOutputStream(writer);
				reader = blob.getBinaryStream();
				bufferReader = new BufferedInputStream(reader);
				resp.setContentType("image/jpeg");
				int i = 0;
				byte bytes[] = new byte[BUFFERSIZE];
				while ((i = bufferReader.read(bytes)) != -1) {
					bufferWriter.write(bytes, 0, i);
				}
				bufferWriter.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferWriter != null) {
				bufferWriter.close();
			}
			if (bufferReader != null) {
				bufferReader.close();
			}
			if (writer != null) {
				writer.close();
			}
			if (reader != null) {
				reader.close();
			}
		}
	}

}
