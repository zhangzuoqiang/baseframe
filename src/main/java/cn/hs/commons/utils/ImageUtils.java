/*
 * $Log: ImageUtils.java,v $
 * Revision 1.1  2012/05/23 09:27:46  guor
 * 初次提交
 *
 * Revision 1.1  2011/04/20 06:49:33  huosd
 * 高校基地讲座报名系统第二批上线代码提交-往期回顾功能
 *
 */
package cn.hs.commons.utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * Title: ImageUtils<br>
 * Description: 图片工具类<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author Huosd
 * @createDate Apr 19, 2011
 * @version $Revision: 1.1 $
 */
public class ImageUtils {

	/**
	 * 压缩图片，并打成流，按压缩比例压缩
	 * 
	 * @param sourceImgStream
	 *            压缩源图片流
	 * @param compression
	 *            设置压缩倍数，正比例压缩
	 * @param rotation
	 *            设置压缩的图片旋转角度
	 * @param snapHeightMax
	 *            缩放后最小高度
	 * @param snapWidthMax
	 *            缩放后最小宽度
	 * @return
	 * @throws Exception
	 * @author WangWB
	 * @date 2010-3-5
	 */
	public static InputStream compressImage(InputStream sourceImgStream,
			double compression, int snapHeightMax, int snapWidthMax)
			throws Exception {
		InputStream result = null;
		try {
			Image tag = ImageIO.read(sourceImgStream);
			int widthSrc = tag.getWidth(null);
			int heightSrc = tag.getHeight(null);
			double scaleds = getScaling(widthSrc, heightSrc, compression,
					snapHeightMax, snapWidthMax);
			int w = (int) (widthSrc * scaleds);
			int h = (int) (heightSrc * scaleds);
			BufferedImage tagNew = new BufferedImage(w, h,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = tagNew.createGraphics();
			while (!(g2.drawImage(tag, 0, 0, w, h, null))) {
				try {
					Thread.sleep(10);
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception("压缩图片，并打成流，按压缩比例压缩", e);
				}
			}
			byte[] data = imageToByte(tagNew, "jpeg");
			result = new ByteArrayInputStream(data);
		} catch (Exception e) {
			throw new Exception("压缩图片异常", e);
		}
		return result;
	}

	/**
	 * 得到图像缩放比率
	 * 
	 * @param sourceWidth
	 *            源图宽
	 * @param sourceHeight
	 *            源图高
	 * @param scaled
	 *            变化比例值，要想把图片放大变小只需改变该值
	 * @param snapHeightMax
	 *            缩放后最小高度
	 * @param snapWidthMax
	 *            缩放后最小宽度
	 * @return double
	 * @author WangWB
	 * @date 2010-3-5
	 */
	public static double getScaling(int sourceWidth, int sourceHeight,
			double scaled, int snapHeightMax, int snapWidthMax) {
		double widthScaling = ((double) snapWidthMax * (double) scaled)
				/ (double) sourceWidth;
		double heightScaling = ((double) snapHeightMax * (double) scaled)
				/ (double) sourceHeight;
		double scaling = (widthScaling < heightScaling) ? widthScaling
				: heightScaling;
		return scaling;
	}

	/**
	 * bufferedImage转换成byte[]
	 * 
	 * @param bi
	 *            图片流
	 * @param format
	 *            转换图片格式（jpg,jpeg,png,gif）
	 * @return byte[]
	 * @throws IOException
	 * @author WangWB
	 * @date 2010-3-5
	 */
	public static byte[] imageToByte(BufferedImage bi, String format)
			throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bi, format, baos);
		return baos.toByteArray();
	}
}
