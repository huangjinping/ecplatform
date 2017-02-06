package cn.cooperlink.ecplatform.petstore.file.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.cooperlink.ecplatform.petstore.file.service.FastDFSService;
import cn.cooperlink.util.FDFSUtil;

//.../fastDFS/getFile?file=id
@Controller
@RequestMapping("/fastDFS")
public class FastDFSController {
	protected final Log log = LogFactory.getLog(getClass());
	
	@Resource
	private FastDFSService fastDFSService;
	
	@RequestMapping("/getFile")
	public void getFile(HttpServletRequest request, HttpServletResponse response){
		fastDFSService.getFile(request, response);
	}
}
