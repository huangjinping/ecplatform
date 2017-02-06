/* **************************************************************
 *
 * 文件名称：UserMapper.java
 *
 * 包含类名：cn.cooperlink.ecplatform.system.persistence.mapper.UserMapper
 * 创建日期：2014年8月6日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.system.persistence.mapper;

import cn.cooperlink.ecplatform.system.entity.User;
import cn.cooperlink.framework.core.BaseMapper;
import cn.cooperlink.spring.extension.Mapper;

/**
 * Mapper类。
 *
 * 创建日期：2014年8月6日
 * 创建作者：潘云峰
 */
@Mapper
public interface UserMapper extends BaseMapper {

	/**
	 * @Title: 统计同加盟商户下员工编号对应的员工数
	 * @Description:
	 * @param user
	 * @return
	 */
	long getCountByEmpNo(User user);

	/**
	 * @Title: 根据用户Id和密码获取用户数
	 * @Description:
	 * @param user
	 * @return
	 */
	long getCountByIdAndPwd(User user);

	void updatePassword(User user);

	User getTenantAdmin(Long tenantId);

	void updateToInvalid(User user);
	
}