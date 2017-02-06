/* **************************************************************
 *
 * 文件名称：ServicePurchaseMapper.java
 *
 * 包含类名：cn.cooperlink.ecplatform.tenant.persistence.mapper.ServicePurchaseMapper
 * 创建日期：2014年8月4日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.tenant.persistence.mapper;

import java.util.List;
import java.util.Map;

import cn.cooperlink.ecplatform.tenant.persistence.result.FuncSelectTree;
import cn.cooperlink.framework.core.BaseMapper;
import cn.cooperlink.spring.extension.Mapper;

/**
 * Mapper类。
 *
 * 创建日期：2014年8月4日
 * 创建作者：潘云峰
 */
@Mapper
public interface ServicePurchaseMapper extends BaseMapper {

	/**
	 * @Title: 删除商户下所有服务
	 * @Description:
	 * @param tenantId
	 */
	public void deleteByTenant(Long tenantId);

	/**
	 * @Title: 查找所有服务
	 * @Description:
	 * @param tenantId
	 * @return
	 */
	public List<FuncSelectTree> findAllService(Long tenantId);

	/**
	 * @Title: 查找商户某角色-服务关系
	 * @Description:
	 * @param paramMap
	 * @return
	 */
	public List<FuncSelectTree> findPurchasedSvrs(Map<String, Long> paramMap);

	/**
	 * @Title: 删除授权表中的垃圾数据
	 * @Description:
	 * @param paramMap
	 */
	public void deleteRoleFuncByFuncRoleIds(List<Long> idList);
	
	/**
	 * @Title: 获取所有已经无效的角色权限ID
	 * @Description:
	 * @param tenantId 数据所属商户
	 * @return
	 */
	public List<Long> findInvalidFuncRoleIds(Long tenantId);
	
}