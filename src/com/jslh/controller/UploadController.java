package com.jslh.controller;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import com.jslh.util.PropertiesUtil;
import com.jslh.util.bean.BackData;

@RequestMapping(value = "/upload")
@Controller
public class UploadController {

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public BackData upload(@RequestParam("file") MultipartFile file) {
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String path = df.format(new Date());
			File uploadedFolder = new File(PropertiesUtil.getUploadValue("basepath") + File.separator + path);
			if (!uploadedFolder.exists()) {
				uploadedFolder.mkdirs();
			}
			String filename = Long.toString(new Date().getTime());
			if (!file.isEmpty()) {
				file.transferTo(new File(uploadedFolder, filename + file.getOriginalFilename()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new BackData(false);
		}
		return new BackData(true);
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public BackData handleException(Exception e) {
		if (e instanceof MaxUploadSizeExceededException) {
			long maxUploadSize = ((MaxUploadSizeExceededException)e).getMaxUploadSize();
			String maxUploadSizeStr = maxUploadSize / 1024 + "k";
			if(maxUploadSize / (1024 * 1024) > 1){
				maxUploadSizeStr = maxUploadSize / (1024 * 1024) + "M";
			}
			return new BackData(false, "文件必须小于" + maxUploadSizeStr);
		}else{
			return new BackData(false, "上传出错");
		}
	}
}
