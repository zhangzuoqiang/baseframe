package cn.hs.module.blob.ui.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import cn.hs.module.blob.domain.BaseBlob;
import cn.hs.module.blob.domain.BlobCondition;
import cn.hs.module.blob.service.IBlobService;

/**
 * Title: ShowImage<br>
 * Description: 图片显示<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Nov 22, 2011
 * @version $Revision: 11 $
 */
public class ShowImage extends HttpServlet {

	private static final long serialVersionUID = 0l;

	private static int BUFFERSIZE = 4096;
	@Autowired
	private IBlobService iBlobService;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		OutputStream writer = null;
		BufferedOutputStream bufferWriter = null;
		InputStream reader = null;
		BufferedInputStream bufferReader = null;

		try {
			BlobCondition condition = new BlobCondition();
			condition.setBlobId(req.getParameter("blobId"));
			BaseBlob entity = iBlobService.getBlob(condition);
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bufferWriter.close();
			bufferReader.close();
			writer.close();
			reader.close();
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
