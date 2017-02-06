/* **************************************************************
 *
 * 文件名称：UploadHandler.java
 *
 * 包含类名：cn.cooperlink.framework.core.util.UploadHandler
 * 创建日期：2014年3月9日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.framework.core.util;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import cn.cooperlink.util.StringUtil;

/**
 * 文件上传处理类。
 * 处理文件上传。
 *
 * 创建日期：2014年3月9日
 * 创建作者：潘云峰
 */
public class UploadHandler {

	/**
	 * 上传文件到目录，返回文件名数组。
	 *
	 * @param uploadDir
	 * @param files
	 * @return
	 */
	public static final String[] upload(MultipartFile[] files, String uploadDir) {
		if (files == null || files.length == 0) {
			return null;
		}
		String[] fileNames = new String[files.length];
		for (int i = 0; i < files.length; i++) {
			fileNames[i] = upload(files[i], uploadDir);
		}
		return fileNames;
	}
	
	/**
	 * 单文件上传， 返回上传后的文件名。
	 * <p>文件名为 uuid.文件后缀</p>
	 * 
	 * @param file
	 * @param uploadDir
	 * @return
	 */
	public static final String upload(MultipartFile file, String uploadDir) {
		if (file == null || file.isEmpty() 
				|| file.getSize() == 0) {
			return null;
		}
		try {
			String fileName = file.getOriginalFilename();
			fileName = UUID.create() + fileName.substring(fileName.lastIndexOf("."));
			upload(file, uploadDir, fileName);
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 上传文件到给定目录。
	 * <p> 文件最终存储为： uploadDir + "\" + fileName</p>
	 * <p> 如不指定目录，则默认上传到操作系统TEMP目录下。</p>
	 * 
	 * @param file
	 * @param uploadDir
	 * @param fileName
	 * @throws Exception
	 */
	public static final void upload(MultipartFile file, String uploadDir, 
			String fileName) throws Exception {
		if (file == null || file.isEmpty() 
				|| file.getSize() == 0) {
			throw new Exception("文件内容为空。。。");
		}
		uploadDir = StringUtil.isBlank(uploadDir) ? 
				(System.getenv().get("TEMP") + "/") : 
					(uploadDir.endsWith("/") || uploadDir.endsWith("\\")) ? 
							uploadDir: uploadDir + "/";
		File destFile = new File(uploadDir + fileName);
		try {
			file.transferTo(destFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static final String upload2FTP() {
		return null;
	}
	
	public static void main(String[] args) {
	}

}
