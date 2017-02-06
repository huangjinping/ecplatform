/* **************************************************************
 *
 * 文件名称：LoginService.java
 *
 * 包含类名：cn.cooperlink.ecplatform.index.service.LoginService
 * 创建日期：2014年8月12日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 大唐云动力科技股份有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.index.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.cooperlink.ecplatform.index.persistence.mapper.LoginMapper;
import cn.cooperlink.ecplatform.security.Authentication;
import cn.cooperlink.ecplatform.security.mobile.MobileAuthentication;
import cn.cooperlink.ecplatform.security.mobile.MobileAuthenticationSub;
import cn.cooperlink.ecplatform.system.Consts;
import cn.cooperlink.framework.core.BaseMapper;
import cn.cooperlink.framework.core.BaseService4Mapper;
import cn.cooperlink.framework.core.util.ConfigManager;

/**
 * 登陆Service类。
 *
 * 创建日期：2014年8月12日
 * 创建作者：潘云峰
 */
@Service
public class LoginService extends BaseService4Mapper {
	
	@Resource
	private LoginMapper loginMapper;

	@SuppressWarnings("unchecked")
	@Override
	public BaseMapper getMapper() {
		return loginMapper;
	}
	
	/**
	 * 加载登陆认证信息。
	 *
	 * @param userName
	 * @param password
	 * @return
	 */
	public Authentication loadAuthInfo(String username, String password) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("username", username);
		paramMap.put("password", password);
		if (username.endsWith(Consts.ADMIN_ACCOUNT_SUFFIX)) {
			Long tenantId = loginMapper.getUserTenantId(username);
			boolean isSuperAdmin = Consts.SUPER_ADMIN_ACCOUNT.equals(username);
			if (tenantId == null || (tenantId == 0 && !isSuperAdmin)) {
				return null;
			}
			paramMap.put("menuIds", ConfigManager.getString(
					"tenant_admin_modules_" + tenantId));
			if (isSuperAdmin) {
				return loginMapper.loadAuthInfo4Superadmin(paramMap);
			}
			paramMap.put("tenantId", tenantId);
			return loginMapper.loadAuthInfo4TenantAdmin(paramMap);
		}
		return loginMapper.loadAuthInfo4Normal(paramMap);
	}
	

    /**
     * 加载手机端会员登录认证信息
     * 
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    public MobileAuthentication loadMobileAuthInfo(String username, String password) throws Exception {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("username", username);
        paramMap.put("password", password);
        return loginMapper.loadMobileAuthInfo(paramMap);
    }
    
    public MobileAuthenticationSub loadMobileAuthInfo_SubMem(String username, String password){
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("username", username);
        paramMap.put("password", password);
        return loginMapper.loadMobileAuthInfo_SubMem(paramMap);
    }

}
