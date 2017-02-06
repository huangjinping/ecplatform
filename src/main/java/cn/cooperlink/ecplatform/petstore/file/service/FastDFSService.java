package cn.cooperlink.ecplatform.petstore.file.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cooperlink.util.FDFSUtil;

@Service
@Transactional
public class FastDFSService {
	/**
     * 
     * @Title: getFile
     * @Description: 获取文件
     * @param request,response
     * @return
     */
    public void getFile(HttpServletRequest request, HttpServletResponse response) {
    	FDFSUtil.write2Response("FILE", request.getParameter("file"), response);
    }
}
