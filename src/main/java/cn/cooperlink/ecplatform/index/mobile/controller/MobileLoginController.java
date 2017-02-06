/* **************************************************************
 *
 * 文件名称：LoginController.java
 *
 * 包含类名：cn.cooperlink.ecplatform.index.mobile.controller.LoginController
 * 创建日期：2014年8月14日
 * 创建作者：dalvik
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.index.mobile.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cooperlink.ecplatform.index.service.LoginService;
import cn.cooperlink.ecplatform.security.mobile.BaseMobileAuthentication;
import cn.cooperlink.ecplatform.security.mobile.MobileAuthHelper;
import cn.cooperlink.framework.core.Return;
import cn.cooperlink.util.Md5;
import cn.cooperlink.util.StringUtil;

/**
 * 手机端登陆Controller类。
 * 
 * 创建日期：2014年8月14日 创建作者：dalvik
 */
@Controller
@RequestMapping(value = "/mobile", method = { RequestMethod.GET, RequestMethod.POST })
public class MobileLoginController {

    public static final String LOGIN_PAGE = "login";

    @Resource
    private LoginService loginService;

    @RequestMapping("/login")
    @ResponseBody
    public String login(String username, String password) throws Exception {
        if (StringUtil.isBlank(username)) {
            return Return.failure("CP-000004.2", "用户名");
        }
        if (StringUtil.isBlank(password)) {
            return Return.failure("CP-000004.2", "密码");
        }
        // 查询主账号信息
        BaseMobileAuthentication auth = loginService.loadMobileAuthInfo(username, Md5.encodeByMd5(password));
        if (auth == null) {
            // 查询子账号信息
            auth = loginService.loadMobileAuthInfo_SubMem(username, Md5.encodeByMd5(password));
            if (auth == null) {
                return Return.failure("CP-000050");
            }
        }
        String sessionId = MobileAuthHelper.putAuthInfo(auth);
        return Return.bean2StringWithSession(auth, sessionId);
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession(true).invalidate();
        return LOGIN_PAGE;
    }

    /**
     * 手机端验证session失败，返回非法访问信息
     * 
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/needLogin")
    public void needLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            pw.write("{success:false,msg:\"登录失效，请重新登陆\",notLoggedIn:\"yes\",\"total\":0,\"rows\":[]}");
            pw.flush();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

}
