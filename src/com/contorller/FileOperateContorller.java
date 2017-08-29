package com.contorller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.utils.FileOperateUtil;

@Controller
@RequestMapping("/fileOperate")
public class FileOperateContorller {

	/**
	 * 上传
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/upload")
	public String upload(HttpServletRequest request) {
		init(request, "file");
		try {

			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> fileMap = mRequest.getFileMap();

			Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet()
					.iterator();
			while (it.hasNext()) {
				Map.Entry<String, MultipartFile> entry = it.next();
				MultipartFile mFile = entry.getValue();
				if (mFile.getSize() != 0 && !mFile.getName().equals("")) {
					String fileName = mFile.getOriginalFilename();
					FileOperateUtil.upload(mFile.getInputStream(), fileName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:list";
	}

	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest request) {
		init(request, "file");
		request.setAttribute("maps", getMap());
		return new ModelAndView("fileOperate/list");
	}

	/**
	 * 下载
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/download")
	public void download(HttpServletRequest request,
			HttpServletResponse response) {
		init(request, "file");
		try {
			String downloadfFileName = request.getParameter("filename");

			downloadfFileName = new String(
					downloadfFileName.getBytes("iso-8859-1"), "utf-8");

			String userAgent = request.getHeader("User-Agent");
			byte[] bytes = userAgent.contains("MSIE") ? downloadfFileName
					.getBytes() : downloadfFileName.getBytes("UTF-8");

			downloadfFileName = new String(bytes, "ISO-8859-1");

			response.setHeader("Content-disposition", String.format(
					"attachment; filename=\"%s\"", downloadfFileName));

			FileOperateUtil.download(downloadfFileName,
					response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void init(HttpServletRequest request, String route) {
		if (FileOperateUtil.FILEDIR == null) {
			FileOperateUtil.FILEDIR = request.getSession().getServletContext()
					.getRealPath("/")
					+ route + "/";
		}
	}

	/**
	 * 
	 * @return
	 */
	private List<String> getMap() {
		List<String> list = new ArrayList<String>();
		File[] files = new File(FileOperateUtil.FILEDIR).listFiles();
		if (files != null) {
			for (File file : files) {
				if (!file.isDirectory()) {
					String name = file.getName();
					list.add(name);
				}
			}
		}
		return list;
	}
}
