package cn.hs.commons.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

/**
 * 
 * Title: ZipUtils<br>
 * Description: 文件压缩工具类<br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Sep 21, 2012
 * @version $Revision:$
 */
public class ZipUtils {

	private static final int BUFFER = 8192;

	/**
	 * 压缩
	 * 
	 * @param zipFilePath
	 *            输出的zip文件路径
	 * @param inputFolderPath
	 *            需要压缩的文件夹路径
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 21, 2012
	 */
	public static void zip(String zipFilePath, String inputFolderPath) throws Exception {
		File zipFile = new File(zipFilePath);
		File srcdir = new File(inputFolderPath);
		if (!srcdir.exists()) {
			throw new Exception(inputFolderPath + "不存在！");
		}

		Project prj = new Project();
		Zip zip = new Zip();
		zip.setProject(prj);
		zip.setDestFile(zipFile);
		FileSet fileSet = new FileSet();
		fileSet.setProject(prj);
		fileSet.setDir(srcdir);
		// fileSet.setIncludes("**/*.java"); 包括哪些文件或文件夹
		// eg:zip.setIncludes("*.java");
		// fileSet.setExcludes(...); 排除哪些文件或文件夹
		zip.addFileset(fileSet);

		zip.execute();
	}

	/**
	 * 压缩，输出流
	 * 
	 * @param srcPathName
	 *            需要压缩的文件或文件夹路径
	 * @param zipFileStream
	 *            zip输出流
	 * @author HuangS
	 * @date Sep 21, 2012
	 */
	public static void zip(String srcPathName, OutputStream zipFileStream) {
		File file = new File(srcPathName);
		if (!file.exists())
			throw new RuntimeException(srcPathName + "不存在！");
		try {
			CheckedOutputStream cos = new CheckedOutputStream(zipFileStream, new CRC32());
			ZipOutputStream out = new ZipOutputStream(cos);
			String basedir = "";
			compress(file, out, basedir);
			out.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static void compress(File file, ZipOutputStream out, String basedir) {
		/* 判断是目录还是文件 */
		if (file.isDirectory()) {
			// System.out.println("压缩：" + basedir + file.getName());
			compressDirectory(file, out, basedir);
		} else {
			// System.out.println("压缩：" + basedir + file.getName());
			compressFile(file, out, basedir);
		}
	}

	/** 压缩一个目录 */
	private static void compressDirectory(File dir, ZipOutputStream out, String basedir) {
		if (!dir.exists())
			return;

		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			/* 递归 */
			compress(files[i], out, basedir + dir.getName() + "/");
		}
	}

	/** 压缩一个文件 */
	private static void compressFile(File file, ZipOutputStream out, String basedir) {
		if (!file.exists()) {
			return;
		}
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			ZipEntry entry = new ZipEntry(basedir + file.getName());
			out.putNextEntry(entry);
			int count;
			byte data[] = new byte[BUFFER];
			while ((count = bis.read(data, 0, BUFFER)) != -1) {
				out.write(data, 0, count);
			}
			bis.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
