/* **************************************************************
 *
 * 文件名称：FDFSUtil.java
 *
 * 包含类名：cn.cooperlink.util.FDFSUtil
 * 创建日期：2014年5月20日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 大唐云动力科技股份有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.csource.common.NameValuePair;
import org.springframework.web.multipart.MultipartFile;

import cn.cooperlink.ecplatform.security.AuthHelper;
import cn.cooperlink.ecplatform.security.Authentication;
import cn.cooperlink.framework.core.util.SpringContainer;

import com.zjt.fastdfs.pool.FastdfsPool;
import com.zjt.fastdfs.pool.StorageClient;

/**
 * FastDFS 文件上传与下载工具。
 *
 * 创建日期：2014年5月20日
 * 创建作者：潘云峰
 */
public class FDFSUtil {
	
	private static final Logger log = Logger.getLogger(FDFSUtil.class);
	
	public static final String SYNC_UPLOAD_SVR_KEY = "sync_upload_svr_code";
	
	public static final String SYNC_DOWNLOAD_SVR_KEY = "sync_download_svr_code";
	
	public static final String SYNC_DELETE_FILE_SVR_KEY = "sync_delete_file_svr_code";
	
	/**
	 * 整合了Spring的文件上传
	 * <p>返回fastfds的fieldId列表，返回结果中可能会存在 null 值</p>
	 * 
	 * @param 	files
	 * @return	FastDFS的文件ID
	 */
	public static List<String> upload(String bizCode, MultipartFile[] files)
			throws Exception {
		if (files == null || files.length == 0) {
			return null;
		}
		List<String> filedList = new ArrayList<String>();
		for (MultipartFile file : files) {
			filedList.add(upload(bizCode, file));
		}
		return filedList;
	}
	
	/**
	 * 整合了Spring的文件上传
	 *
	 * @param 	file
	 * @return	FastDFS的文件ID
	 */
	public static String upload(String bizCode, MultipartFile file) 
			throws Exception {
		if (file == null || file.isEmpty() 
				|| file.getSize() == 0) {
			return null;
		}
		
		return upload(bizCode, file.getInputStream(), 
				file.getOriginalFilename(), 
				file.getSize());
	}
	
	/**
	 * 文件上传
	 * <p>根据文件路径生成文件，如果文件存在则上传。</p>
	 * 
	 * @param 	file
	 * @return	FastDFS的文件ID
	 */
	public static String upload(String bizCode, String pathName) 
			throws Exception {
		return upload(bizCode, new File(pathName));
	}
	
	/**
	 * 文件上传
	 *
	 * @param 	file
	 * @return	FastDFS的文件ID
	 */
	public static String upload(String bizCode, File file) throws Exception {
		
		if (file == null || !file.exists()) {
			return null;
		}
		
		return upload(bizCode, new FileInputStream(file), 
				file.getName(), file.length());
	}

	/** 
	 * Upload File to DFS. 
	 * 
	 * @param fileBuff, file to be uploaded. 
	 * @param uploadFileName, the name of the file. 
	 * @param fileLength, the length of the file. 
	 * @return the file ID in DFS. 
	 * @throws IOException  
	 */
	public static String upload(String bizCode, InputStream input, String uploadFileName, 
			long fileLength) throws Exception {
		
	    byte[] fileBuff = getFileBuffer(input, (int) fileLength);
	    if (fileBuff == null || fileBuff.length == 0) {
	    	throw new Exception("上传文件为空。");
	    }
	    Authentication auth = AuthHelper.getAuthInfo();
	    if (auth == null) {
	    	throw new Exception("非登录用户，无法上传文件。");
	    }
	    
	    String fileId = null;  
	    String fileExtName = "";  
	    if (uploadFileName.contains(".")) {  
	        fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);  
	    } else {
	    	log.warn("Fail to upload file, because the format of filename is illegal.");  
	        return fileId;  
	    }
	    FastdfsPool pool = getFdfsPool();
	    StorageClient client = null;
		try {
			client = pool.getResource();
			
			//设置元信息  
			NameValuePair[] metaList = new NameValuePair[3];  
			metaList[0] = new NameValuePair("fileName", uploadFileName);  
			metaList[1] = new NameValuePair("fileExtName", fileExtName);  
			metaList[2] = new NameValuePair("fileLength", String.valueOf(fileLength)); 
			
			//上传文件  
			fileId = client.upload_file1(fileBuff, fileExtName, metaList);
		    return fileId;  
		} catch (Exception e) {
			log.error(StringUtil.getTrace(e));
			returnBrokenResource(pool, client);
			throw e;
		} finally {
			returnResource(pool, client);
		}
		
	}
	
	/**
	 * 从fastdfs下载文件并写入到repsonse流中。
	 * <p>默认的contentType 为 application/x-msdownload </p>
	 *
	 * @param fileId	FastDFS的文件ID
	 * @param response
	 */
	public static void write2Response(String bizCode, String fileId, 
			HttpServletResponse response) {
		write2Response(bizCode, fileId, response, 
				"application/x-msdownload;");
	}
	
	/**
	 * 从fastdfs下载文件并写入到repsonse流中。
	 * 
	 * @param fileId	FastDFS的文件ID
	 * @param response
	 */
	public static void write2Response(String bizCode, String fileId, 
			HttpServletResponse response, 
			String contentType) {
		
		if (fileId == null || 
				response == null) {
			log.warn("文件下载异常，参数值为空。");
			return;
		}
	    Authentication auth = AuthHelper.getAuthInfo();
	    if (auth == null) {
	    	throw new RuntimeException("非登录用户，无法下载文件。");
	    }
		
	    FastdfsPool pool = getFdfsPool();
	    StorageClient client = null;
		OutputStream out = null;
        try {
        	client = pool.getResource();
        	
			NameValuePair[] meta = client.get_metadata1(fileId);
			if (meta == null) {
				log.warn("文件不存在。fileId = " + fileId);
				return;
			}

			String fileName = null;
			String fileExtName = null;
			String fileLength = null;
			String metaName = null;
			for (int i = 0; i < meta.length; i++) {
				metaName = meta[i].getName();
				if (metaName == null) {
					continue;
				}
				if ("fileName".equals(metaName)) {
					fileName = meta[i].getValue();
				} else if ("fileExtName".equals(metaName)) {
					fileExtName = meta[i].getValue();
				} else {
					fileLength = meta[i].getValue();
				}
			}
			
			if (StringUtil.isNotBlank(fileExtName)) {
				fileName += "." + fileExtName; 
			}
        	
			// 设置相应头
            response.setContentType(contentType);   
            response.setHeader("Content-disposition", "attachment; filename="  
                    + new String(fileName.getBytes("utf-8"), "ISO8859-1"));   
            response.setHeader("Content-Length", fileLength);
            
            out = response.getOutputStream();
			byte[] fileBuff = client.download_file1(fileId);
			out.write(fileBuff);
			out.flush();
        } catch (Exception e) {   
            e.printStackTrace();
            // 下行代码有问题
			returnBrokenResource(pool, client);
        } finally {
        	if (out != null) {
        		try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
			returnResource(pool, client);
        }
        
	}
	
	/**
	 * 从fastdfs下载附件，并将结果输出到流
	 * <p> 方法调用结束会关闭流。</p>
	 *
	 * @param fileId	FastDFS的文件ID
	 * @param out		输出流
	 */
	public static void download2Stream(String bizCode, String fileId, OutputStream out) {
	    Authentication auth = AuthHelper.getAuthInfo();
	    if (auth == null) {
	    	throw new RuntimeException("非登录用户，无法下载文件。");
	    }
	    FastdfsPool pool = getFdfsPool();
	    StorageClient client = null;
	    try {
			//建立连接  
	    	client = pool.getResource();
			byte[] fileBuff = client.download_file1(fileId);
			out.write(fileBuff);
			out.flush();
		} catch (Exception e) {
			log.error(StringUtil.getTrace(e));
			// 下行代码有问题
			returnBrokenResource(pool, client);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			returnResource(pool, client);
		}
	}
	
	/**
	 * 从fastdfs下载附件，并保存到目标文件
	 * <p>从服务器下载文件，并将文件保存到destFileName指定的文件中，</p>
	 * <p>如果文件已经存在，会自动重命名（在文件后加入时间戳）</p>
	 * 
	 * @param fileId		FastDFS的文件ID
	 * @param destFileName	输出文件路径
	 */
	public static void download2File(String bizCode, String fileId, String destFileName) {
		download2File(bizCode, fileId, new File(destFileName));
	}
	
	/**
	 * 文件下载并保存到目标文件
	 *
	 * @param fileId	FastDFS的文件ID
	 * @param destFile	输出文件
	 */
	public static void download2File(String bizCode, String fileId, File destFile) {
	    Authentication auth = AuthHelper.getAuthInfo();
	    if (auth == null) {
	    	throw new RuntimeException("非登录用户，无法下载文件。");
	    }
		
		if (destFile.exists()) {
			destFile = new File(destFile.getParent(), 
					System.currentTimeMillis() + 
					destFile.getName());
		}

	    FastdfsPool pool = getFdfsPool();
	    StorageClient client = null;
		FileOutputStream out = null;
	    try {
			//建立连接  
	    	client = pool.getResource();

			byte[] fileBuff = client.download_file1(fileId);
			out = new FileOutputStream(destFile);
			out.write(fileBuff);
			out.flush();
		} catch (Exception e) {
			log.error(StringUtil.getTrace(e));
			// 下行代码有问题
			returnBrokenResource(pool, client);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			returnResource(pool, client);
		}
	    
	}
	
	/**
	 * 删除文件
	 * <p>逻辑删除， 并不删除物理文件。仅将文件标记为删除</p>
	 * 
	 * @param fileId
	 * @return
	 */
	public static boolean deleteFile(String bizCode, String fileId) {
	    Authentication auth = AuthHelper.getAuthInfo();
	    if (auth == null) {
	    	throw new RuntimeException("非登录用户，无法下载文件。");
	    }
	    
		if (StringUtil.isBlank(fileId)) {
			return true;
		}
	    return false;
	}
	
	/**
	 * 获取FDFS的连接池
	 *
	 * @return
	 */
	private static FastdfsPool getFdfsPool() {
		return (FastdfsPool) SpringContainer
				.getBean("fastdfsPool");
	}
	
	/**
	 * 释放资源
	 *
	 * @param pool
	 * @param client
	 */
	private static void returnResource(FastdfsPool pool, 
			StorageClient client) {
		if (pool !=null && client != null) {
			try {
				pool.returnResource(client);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 异常时释放资源
	 *
	 * @param pool
	 * @param client
	 */
	private static void returnBrokenResource(FastdfsPool pool, 
			StorageClient client) {
		if (pool !=null && client != null) {
			try {
				pool.returnBrokenResource(client);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 将流读入字节数组中
	 *
	 * @param input
	 * @param fileLength
	 * @return
	 * @throws Exception
	 */
	private static byte[] getFileBuffer(InputStream input, int fileLength) 
			throws Exception {
		try {
			byte[] buff = new byte[fileLength];
			input.read(buff, 0, fileLength);
			return buff;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
