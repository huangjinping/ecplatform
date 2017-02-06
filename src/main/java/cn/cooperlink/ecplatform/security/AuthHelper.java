/* **************************************************************
 *
 * 文件名称：AuthHelper.java
 *
 * 包含类名：cn.cooperlink.ecplatform.security.AuthHelper
 * 创建日期：2014年8月11日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 大唐云动力科技股份有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.security;

import javax.servlet.http.HttpServletRequest;

import cn.cooperlink.ecplatform.system.Consts;
import cn.cooperlink.framework.core.util.RequestUtil;

/**
 * 登陆认证帮助类。
 *
 * 创建日期：2014年8月11日
 * 创建作者：潘云峰
 */
public class AuthHelper {
	
	public static final String AUTH_KEY = "_current_login_user";
	
	/**
	 * 登录判断
	 *
	 * @return true - 已经登录； false - 未登录
	 */
	public static boolean hasLoggedOn(HttpServletRequest request) {
		if (getAuthInfo(request) != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * 认证用户ID
	 *
	 * @return
	 */
	public static Long getUserId() {
		return getAuthInfo().getUserId();
	}
	
	/**
	 * 获取用户名
	 *
	 * @return
	 */
	public static String getUsername() {
		return getAuthInfo().getUsername();
	}
	
	/**
	 * 认证用户机构ID
	 *
	 * @return
	 */
	public static Long getOrgId() {
		return getAuthInfo().getOrgId();
	}
	
	/**
	 * 认证用户所属商户ID
	 *
	 * @return
	 */
	public static Long getTenantId() {
		return getAuthInfo().getTenantId();
	}	
	
	/**
	 * 从Session中获取认证信息
	 *
	 * @return
	 */
	public static final Authentication getAuthInfo() {
		return getAuthInfo(RequestUtil.getRequest());
	}
	
	/**
	 * 从Session中获取认证信息
	 *
	 * @param request
	 * @return
	 */
	public static Authentication getAuthInfo(HttpServletRequest request) {
		Authentication auth = (Authentication) request.getSession()
				.getAttribute(AUTH_KEY);
		return  auth;
	}
	
	/**
	 * 将登录认证信息存入session
	 *
	 * @param auth
	 */
	public static void putAuthInfo(Authentication auth) {
		putAuthInfo(RequestUtil.getRequest(), auth);
	}
	
	/**
	 * 将登录认证信息存入session
	 *
	 * @param auth
	 */
	public static void putAuthInfo(HttpServletRequest request, 
			Authentication auth) {
		request.getSession().setAttribute(AUTH_KEY, auth);
	}

	/**
	 * @Title: 登陆用户是否是超级管理员
	 * @Description:
	 * @return
	 */
	public static boolean isSuperAdmin() {
		if (Consts.SUPER_ADMIN_ACCOUNT
				.equals(getAuthInfo().getUsername())) {
			return true;
		}
		return false;
	}
}
