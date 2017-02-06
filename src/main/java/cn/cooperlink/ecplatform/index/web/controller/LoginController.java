/* **************************************************************
 *
 * 文件名称：LoginController.java
 *
 * 包含类名：cn.cooperlink.ecplatform.index.web.controller.LoginController
 * 创建日期：2014年8月12日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 大唐云动力科技股份有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.index.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cooperlink.ecplatform.index.service.LoginService;
import cn.cooperlink.ecplatform.security.AuthHelper;
import cn.cooperlink.ecplatform.security.Authentication;
import cn.cooperlink.framework.core.Return;
import cn.cooperlink.util.Md5;
import cn.cooperlink.util.StringUtil;

/**
 * 登陆Controller类。
 *
 * 创建日期：2014年8月12日
 * 创建作者：潘云峰
 */
@Controller
@RequestMapping(value="/login", method={RequestMethod.GET, RequestMethod.POST})
public class LoginController {

	public static final String LOGIN_PAGE = "login";
	
	@Resource
	private LoginService loginService;
	
	/**
	 * 登陆页面
	 *
	 * @return
	 */
	@RequestMapping
	public String loginPage() {
		return LOGIN_PAGE;
	}
	
	@RequestMapping("/loginAction")
	@ResponseBody
	public String login(String username, String password) throws Exception {
		if (StringUtil.isBlank(username)) {
			return Return.failure("CP-000004.2", "用户名");
		}
		if (StringUtil.isBlank(password)) {
			return Return.failure("CP-000004.2", "密码");
		}
		Authentication auth = loginService.loadAuthInfo(username, 
				Md5.encodeByMd5(password));
		if (auth == null) {
			return Return.failure("CP-000050");
		}
		AuthHelper.putAuthInfo(auth);
		return Return.success();
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession(true).invalidate();
		return LOGIN_PAGE;
	}

}
