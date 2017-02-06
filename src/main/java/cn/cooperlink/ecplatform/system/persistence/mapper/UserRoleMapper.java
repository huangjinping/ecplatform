/* **************************************************************
 *
 * 文件名称：UserRoleMapper.java
 *
 * 包含类名：cn.cooperlink.ecplatform.system.persistence.mapper.UserRoleMapper
 * 创建日期：2014年8月9日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.system.persistence.mapper;

import cn.cooperlink.framework.core.BaseMapper;
import cn.cooperlink.spring.extension.Mapper;

/**
 * Mapper类。
 *
 * 创建日期：2014年8月9日
 * 创建作者：潘云峰
 */
@Mapper
public interface UserRoleMapper extends BaseMapper {

	/**
	 * @Title: 删除角色与人员关联关系
	 * @Description:
	 * @param roleId
	 */
	void deleteByRoleId(Long roleId);
}