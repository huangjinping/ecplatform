/* **************************************************************
 *
 * 文件名称：RoleMapper.java
 *
 * 包含类名：cn.cooperlink.ecplatform.system.persistence.mapper.RoleMapper
 * 创建日期：2014年8月9日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.system.persistence.mapper;

import java.util.List;
import java.util.Map;

import cn.cooperlink.ecplatform.system.entity.Role;
import cn.cooperlink.framework.core.BaseMapper;
import cn.cooperlink.spring.extension.Mapper;

/**
 * Mapper类。
 *
 * 创建日期：2014年8月9日
 * 创建作者：潘云峰
 */
@Mapper
public interface RoleMapper extends BaseMapper {
	
	/**
	 * 查询用户所有角色
	 *
	 * @param paramMap
	 * @return
	 */
	public List<Role> queryAllRoleByUser(Map<String, Long> paramMap);

	/**
	 * 删除用户下所有角色
	 *
	 * @param userId
	 */
	public void deleteUserRole(Long userId);

	/**
	 * 查找用户是否存在
	 *
	 * @param paramMap
	 * @return
	 */
	public int checkUser(Map<String, Long> paramMap);
}