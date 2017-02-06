/* **************************************************************
 *
 * 文件名称：TenantConfigMapper.java
 *
 * 包含类名：cn.cooperlink.ecplatform.tenant.persistence.mapper.TenantConfigMapper
 * 创建日期：2014年8月17日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.tenant.persistence.mapper;

import java.util.List;

import cn.cooperlink.ecplatform.tenant.entity.TenantConfig;
import cn.cooperlink.framework.core.BaseMapper;
import cn.cooperlink.spring.extension.Mapper;

/**
 * Mapper类。
 *
 * 创建日期：2014年8月17日
 * 创建作者：潘云峰
 */
@Mapper
public interface TenantConfigMapper extends BaseMapper {

	/**
	 * @Title: 查找租户配置参数
	 * @Description:
	 * @param tenantId
	 * @return
	 */
	List<TenantConfig> findTenantParams(Long tenantId);

	void deleteTenantConfig(Long tenantId);
}