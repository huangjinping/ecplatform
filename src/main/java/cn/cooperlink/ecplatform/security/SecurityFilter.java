package cn.cooperlink.ecplatform.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import cn.cooperlink.ecplatform.security.mobile.MobileAuthHelper;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class SecurityFilter implements Filter {
	
    public SecurityFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String path = req.getRequestURI();
		String cp = req.getContextPath();
	      // 手机端登录信息验证
        if(path.startsWith(cp+"/mobile/")
                && !path.startsWith(cp+"/mobile/login")
                && !path.startsWith(cp + "/mobile/logout")){
            if(!MobileAuthHelper.hasLoggedOn(req)){
                req.getRequestDispatcher("/mobile/needLogin").forward(request, response);
                return;
            }
        }
		
		if (!path.startsWith(cp + "/resources/") 
		        && !path.startsWith(cp+"/mobile/")
                && !path.startsWith(cp+"/login")
				&& !path.equals(cp +  "/login/loginAction")
				&& !path.startsWith(cp + "/login/loginAction?")
				&& !path.equals(cp + "/logout")) {
			if(!AuthHelper.hasLoggedOn(req)) {
				if (isAjax(req)) {
					jsonOut(response, "{\"success\":false,\"msg\":\"登录失效，请重新登陆\",\"notLoggedIn\":\"yes\",\"total\":0,\"rows\":[]}");
					return;
				}
				req.getRequestDispatcher("/login").forward(request, response);
				return;
			}
		}
		chain.doFilter(request, response);
	}
	
	/**
	 * 返回JSON字符串。
	 *
	 * @param response
	 * @param json
	 */
	private void jsonOut(ServletResponse response, String json) {
        PrintWriter writer;
		try {
		    // 设置字符集
		    response.setCharacterEncoding("utf-8");  
		    response.setContentType("text/html; charset=utf-8"); 
			writer = response.getWriter();
	        writer.write(json);
	        writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	/**
	 * 是否ajax请求
	 *
	 * @param request
	 * @return
	 */
	private boolean isAjax(HttpServletRequest request) {
		if (!(request.getHeader("accept").indexOf("application/json") > -1 
        		|| (request.getHeader("X-Requested-With")!= null 
        		&& request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
			return false;
        }
		return true;
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
