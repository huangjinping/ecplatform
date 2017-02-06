/* **************************************************************
 *
 * 文件名称：MobileAuthHelper.java
 *
 * 包含类名：cn.cooperlink.ecplatform.security.MobileAuthHelper
 * 创建日期：2014年8月14日
 * 创建作者：dalvik
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.security.mobile;

import javax.servlet.http.HttpServletRequest;

import cn.cooperlink.framework.core.util.RequestUtil;

/**
 * 手机端登陆认证帮助类。
 * 
 * 创建日期：2014年8月14日 创建作者：dalvik
 */
public class MobileAuthHelper {

    public static final String AUTH_KEY = "_current_login_member";

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
     * 从Session中获取认证信息
     * 
     * @return
     */
    public static final BaseMobileAuthentication getAuthInfo() {
        return getAuthInfo(RequestUtil.getRequest());
    }

    /**
     * 从Session中获取认证信息
     * 
     * @param request
     * @return
     */
    public static BaseMobileAuthentication getAuthInfo(HttpServletRequest request) {
        BaseMobileAuthentication auth = (BaseMobileAuthentication) request.getSession().getAttribute(AUTH_KEY);
        return auth;
    }

    /**
     * 将登录认证信息存入session
     * 
     * @param auth
     * @return sessionid
     */
    public static String putAuthInfo(BaseMobileAuthentication auth) {
        return putAuthInfo(RequestUtil.getRequest(), auth);
    }

    /**
     * 将登录认证信息存入session
     * 
     * @param auth
     * @return sessionid
     */
    public static String putAuthInfo(HttpServletRequest request, BaseMobileAuthentication auth) {
        request.getSession().setAttribute(AUTH_KEY, auth);
        return request.getSession().getId();
    }

}
