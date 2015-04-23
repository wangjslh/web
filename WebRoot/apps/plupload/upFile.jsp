<%@ page language="java"
	import="java.util.*, 
	com.jslh.util.PropertiesUtil,
	java.io.File, 
	java.text.DateFormat, 
	java.text.SimpleDateFormat, 
	org.apache.commons.fileupload.FileItem, 
	org.apache.commons.fileupload.disk.DiskFileItemFactory, 
	org.apache.commons.fileupload.servlet.ServletFileUpload, 
	org.apache.commons.io.FilenameUtils"
	pageEncoding="UTF-8"%>
<%
	/** 设置文件接收和返回时的编码  **/
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8");
	/** 读取配置文件，设置文件上传参数  **/
	int sizeThreshold = 1024 * 8;//默认大小8k
	long fileSizeMax = 1024 * 1024 * 5;//默认大小5M
	long sizeMax = 1024 * 1024 * 10;//默认大小10M
	long actualFileSizeMax = 1024 * 1024 * 3;//默认大小3M
	try{
		sizeThreshold = Integer.parseInt(PropertiesUtil.getUploadValue("sizeThreshold"));
		fileSizeMax = Long.parseLong(PropertiesUtil.getUploadValue("fileSizeMax"));
		sizeMax = Long.parseLong(PropertiesUtil.getUploadValue("sizeMax"));
		actualFileSizeMax = Long.parseLong(PropertiesUtil.getUploadValue("actualFileSizeMax"));
	}catch(Exception e){
		out.print("{\"status\":\"0\",\"msg\":\"上传文件参数设置错误\"}");
		return;
	}
	String basepath = PropertiesUtil.getUploadValue("basepath");
	/** 创建文件上传路径并创建文件  **/
	File file = new File(basepath);
	if (file.exists() == false) {
		file.mkdirs();
	}
	boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	if (isMultipart) {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//缓冲区的大小,文件大于缓冲区后保存到临时目录
		factory.setSizeThreshold(sizeThreshold);
		// 设置临时文件存储位置
		factory.setRepository(new File(basepath));
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置单个文件的最大上传值
		upload.setFileSizeMax(fileSizeMax);
		// 设置整个request的最大值
		upload.setSizeMax(sizeMax);
		try {
			// 处理文件上传
			out.clear();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			List<?> items = upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				FileItem item = (FileItem) items.get(i);
				// 保存文件
				if (!item.isFormField() && item.getName().length() > 0) {
					String rawName = item.getName();
					if (item.getSize() > actualFileSizeMax) {
						String actualFileSizeMaxStr = actualFileSizeMax / 1024 + "k";
						if(actualFileSizeMax / (1024 * 1024) > 1){
							actualFileSizeMaxStr = actualFileSizeMax / (1024 * 1024) + "M";
						}
						out.print("{\"state\":\"0\",\"msg\":\"文件必须小于" + actualFileSizeMaxStr + "\"}");
						return;
					}
					String filename = Long.toString(new Date()
							.getTime());
					String extension = FilenameUtils
							.getExtension(FilenameUtils
									.getName(rawName));

					//按照上传的日期归档上传文件
					String path = df.format(new Date());
					File uploadedFolder = new File(file, path);

					if (!uploadedFolder.exists()) {
						uploadedFolder.mkdirs();
					}

					File uploadedFile = new File(uploadedFolder,
							filename + "." + extension);
					item.write(uploadedFile);
					out.print("{\"status\":\"1\",\"path\":\"" + path
							+ "/" + filename + "." + extension
							+ "\",\"name\":\"" + rawName
							+ "\",\"size\":\"" + item.getSize() + "\"}");
					out = pageContext.pushBody();
				}
			}
		} catch (Exception e) {
		//	e.printStackTrace();
			out.print("{\"status\":\"0\",\"msg\":\"上传出现异常\"}");
			out = pageContext.pushBody();
		}
	}
	//status fail:0，success:1
%>

