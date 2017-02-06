/* **************************************************************
 *
 * 文件名称：RoleFuncMapper.java
 *
 * 包含类名：cn.cooperlink.ecplatform.system.persistence.mapper.RoleFuncMapper
 * 创建日期：2014年8月9日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.system.persistence.mapper;

import java.util.List;
import java.util.Map;

import cn.cooperlink.ecplatform.system.entity.Role;
import cn.cooperlink.ecplatform.tenant.persistence.result.AuthorizedFuncTree;
import cn.cooperlink.framework.core.BaseMapper;
import cn.cooperlink.spring.extension.Mapper;

/**
 * Mapper类。
 *
 * 创建日期：2014年8月9日
 * 创建作者：潘云峰
 */
@Mapper
public interface RoleFuncMapper extends BaseMapper {

	/**
	 * 删除角色下所有
	 *
	 * @param roleId
	 */
	void deleteByRoleId(Long roleId);

	/**
	 * 获取某角色所有授权功能
	 *
	 * @param tenantId
	 * @param roleId
	 * @return
	 */
	List<AuthorizedFuncTree> findFuncTreeByRole(Map<String, Long> paramMap);

	/**
	 * 查询用户所有角色
	 *
	 * @param paramMap
	 * @return
	 */
	List<Role> queryAllRoleByUser(Map<String, Long> paramMap);
	
}