/* **************************************************************
 *
 * 文件名称：LoginMapper.java
 *
 * 包含类名：cn.cooperlink.ecplatform.index.persistence.mapper.LoginMapper
 * 创建日期：2014年8月12日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 大唐云动力科技股份有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.index.persistence.mapper;

import java.util.Map;

import cn.cooperlink.ecplatform.security.Authentication;
import cn.cooperlink.ecplatform.security.mobile.MobileAuthentication;
import cn.cooperlink.ecplatform.security.mobile.MobileAuthenticationSub;
import cn.cooperlink.framework.core.BaseMapper;
import cn.cooperlink.spring.extension.Mapper;

/**
 * 登陆Mapper类。
 *
 * 创建日期：2014年8月12日
 * 创建作者：潘云峰
 */
@Mapper
public interface LoginMapper extends BaseMapper {
	
	/**
	 * 加载登陆认证信息。
	 *
	 * @param userName
	 * @param password
	 * @return
	 */
	public Authentication loadAuthInfo4Normal(Map<String, Object> paramMap);
	
	/**
	 * @Title: 获取用户所属租户。
	 * @Description:
	 * @param username
	 * @return
	 */
	public Long getUserTenantId(String username);
	
	/**
	 * @Title: 加载租户管理员信息
	 * @Description:
	 * @param paramMap
	 * @return
	 */
	public Authentication loadAuthInfo4TenantAdmin(Map<String, Object> paramMap);
	
	/**
	 * @Title: 加载超级管理员模块
	 * @Description:
	 * @return
	 */
	public Authentication loadAuthInfo4Superadmin(Map<String, Object> paramMap);

    /**
     * 加载手机端登录认证信息
     * 
     * @param paramMap
     * @return
     */
    public MobileAuthentication loadMobileAuthInfo(Map<String, String> paramMap);

    /**
     * 加载手机端登录认证信息—－子账号登录
     * 
     * @param paramMap
     * @return
     */
    public MobileAuthenticationSub loadMobileAuthInfo_SubMem(Map<String, String> paramMap);
}
