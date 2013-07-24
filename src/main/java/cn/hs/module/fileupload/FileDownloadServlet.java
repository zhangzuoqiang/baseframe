package cn.hs.module.fileupload;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.hs.module.blob.domain.BaseBlob;
import cn.hs.module.blob.domain.BlobCondition;
import cn.hs.module.blob.service.IBlobService;

@Controller
@RequestMapping("fileDownload.do")
public class FileDownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 5383691492595999383L;

	@Autowired
	private IBlobService blobService;

	@RequestMapping(method = RequestMethod.GET)
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String blobId = (String) request.getParameter("attId");
		if (blobId != null && !blobId.trim().equals("")) {
			// WebApplicationContext webApplicationContext =
			// WebApplicationContextUtils.getWebApplicationContext(super.getServletContext());
			// AttachmentManagerDao attachmentManagerDao =
			// (AttachmentManagerDao)
			// SpringBeanUtils.getBean("attachmentManagerDao");
			BaseBlob attach = null;
			try {
				BlobCondition condition = new BlobCondition();
				condition.setBlobId(blobId);
				attach = blobService.getBlob(condition);
			} catch (Exception e1) {
				// TODO
				e1.printStackTrace();
			}
			if (attach != null) {
				response.setContentType("APPLICATION/OCTET-STREAM;charset=ISO8859-1");
				response.setHeader("Content-Disposition", "attachment; filename=" + new String(attach.getAttName().getBytes(), "ISO8859-1"));
				response.setHeader("Content-Description", "download file");
				javax.servlet.ServletOutputStream servletoutputstream = response.getOutputStream();
				InputStream inputStream = null;
				try {
					inputStream = attach.getAttBlob().getBinaryStream();
				} catch (Exception e1) {
					e1.printStackTrace();
					// TODO
				}
				try {
					int i = 0;
					byte abyte0[] = new byte[1024];//
					while ((i = inputStream.read(abyte0)) != -1) {
						servletoutputstream.write(abyte0, 0, i);
					}
					servletoutputstream.flush();
				} catch (IOException e) {
					if (e.getMessage().indexOf("Connection reset by peer") != -1) {
						System.out.println("客户端关闭连接");
					} else {
						throw e;
					}
				} finally {
					if (servletoutputstream != null) {
						servletoutputstream.close();
					}
					if (inputStream != null) {
						inputStream.close();
					}
				}
			}
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
