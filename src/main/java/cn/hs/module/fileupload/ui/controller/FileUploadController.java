package cn.hs.module.fileupload.ui.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.hs.core.springext.mvc.view.MappingJacksonJsonView;
import cn.hs.module.fileupload.service.IFileUploadService;
import cn.hs.module.systemparameter.SystemParameterConstant;
import cn.hs.module.systemparameter.service.ISystemParameterCacheService;

/**
 * 
 * Title: FileUploadController<br>
 * Description: 附件上传controller<br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Sep 14, 2012
 * @version $Revision:$
 */
@Controller
@RequestMapping(value = FileUploadController.modulePath)
public class FileUploadController {

	// 模块页面根路径
	protected static final String modulePath = "/module/fileUpload";

	// 附件上传接口
	@Resource(name = "uploadFactory")
	private IFileUploadService fileUploadService;

	// 系统参数缓存
	@Autowired
	private ISystemParameterCacheService parameterCacheService;

	/**
	 * 上传图片
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 13, 2012
	 */
	@RequestMapping(value = "/uploadImg")
	public ModelAndView uploadImg(HttpServletRequest request) throws Exception {
		String err = "";
		String newFileName = "";
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		// 设置响应头为text/html
		view.setContentType("text/html; charset=UTF-8");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView(view);

		/* 获取文件类型参数 */
		String fileExt = parameterCacheService.getSystemParameter(SystemParameterConstant.IMG_EXT);
		if (fileExt == null || "".equals(fileExt)) {
			fileExt = "jpg,jpeg,gif,bmp,png";
		}
		/* 获取文件大小参数 */
		String maxSizeStr = parameterCacheService.getSystemParameter(SystemParameterConstant.IMG_MAX_SIZE);
		Long maxSize;
		if (maxSizeStr == null || "".equals(maxSizeStr)) {
			maxSize = new Long(8413184);
		} else {
			maxSize = new Long(maxSizeStr);
		}

		InputStream in = null;

		try {

			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

			for (MultipartFile mf : fileMap.values()) {
				CommonsMultipartFile cmf = (CommonsMultipartFile) mf;
				FileItem item = cmf.getFileItem();
				if (!item.isFormField()) {

					String fileName = item.getName();
					if (fileName == null || fileName.trim().equals("")) {
						throw new Exception("上传文件为空！");
					}

					String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);

					/* 检查文件类型 */
					if (("," + fileExt.toLowerCase() + ",").indexOf("," + extensionName.toLowerCase() + ",") < 0) {
						throw new Exception("不允许上传此类型的文件");
					}
					/* 检查文件大小 */
					if (maxSize > 0 && item.getSize() > maxSize) {
						throw new Exception("上传文件的大小超出限制");
					}

					in = item.getInputStream();

					newFileName = fileUploadService.fileUpload(in, fileName);
					// 从内从中删除临时文件
					item.delete();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			err = "错误: " + e.getMessage();
		} finally {
			if (in != null) {
				in.close();
			}
		}

		responseMap.put("err", err);
		responseMap.put("msg", newFileName);
		mav.addAllObjects(responseMap);
		return mav;
	}

}
