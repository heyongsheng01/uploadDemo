package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;

public class FileOperateUtil {

	public static String FILEDIR = null;

	/**
	 * 上传
	 * 
	 * @param request
	 * @throws IOException
	 */
	public static void upload(InputStream in, String mName) {
		File file = new File(FILEDIR);
		if (!file.exists()) {
			file.mkdir();
		}
		try {
			String fileName = (file.getPath() + "/" + mName
					.replaceAll(" ", "-"));
			write(in, new FileOutputStream(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 下载
	 * 
	 * @param downloadfFileName
	 * @param out
	 */
	public static void download(String downloadfFileName,
			ServletOutputStream out) {
		File file = new File(FILEDIR);
		if (!file.exists()) {
			file.mkdir();
		}
		try {
			FileInputStream in = new FileInputStream(new File(FILEDIR + "/"
					+ downloadfFileName));
			write(in, out);
		} catch (FileNotFoundException e) {
			try {
				FileInputStream in = new FileInputStream(new File(FILEDIR
						+ "/"
						+ new String(downloadfFileName.getBytes("iso-8859-1"),
								"utf-8")));
				write(in, out);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 写入数据
	 * 
	 * @param in
	 * @param out
	 * @throws IOException
	 */
	public static void write(InputStream in, OutputStream out)
			throws IOException {
		try {
			byte[] buffer = new byte[1024];
			int bytesRead = -1;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
			out.flush();
		} finally {
			try {
				in.close();
			} catch (IOException ex) {
			}
			try {
				out.close();
			} catch (IOException ex) {
			}
		}
	}
}
