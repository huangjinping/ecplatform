/* **************************************************************
 *
 * 文件名称：RegTenantMapper.java
 *
 * 包含类名：cn.cooperlink.ecplatform.tenant.dao.mapper.RegTenantMapper
 * 创建日期：2014年7月31日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 大唐云动力科技股份有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.tenant.persistence.mapper;

import cn.cooperlink.ecplatform.tenant.entity.RegTenant;
import cn.cooperlink.framework.core.BaseMapper;
import cn.cooperlink.spring.extension.Mapper;

/**
 * Mapper类。
 *
 * 创建日期：2014年7月31日
 * 创建作者：潘云峰
 */
@Mapper
public interface RegTenantMapper extends BaseMapper {
	
	/**
	 * 设置祖册商户绑定的组织机构
	 *
	 * @param regTenant
	 */
	public void setOrgId(RegTenant regTenant);
	
}