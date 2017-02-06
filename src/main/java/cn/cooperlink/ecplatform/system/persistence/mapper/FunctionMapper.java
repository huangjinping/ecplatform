/* **************************************************************
 *
 * 文件名称：FunctionMapper.java
 *
 * 包含类名：cn.cooperlink.ecplatform.system.persistence.mapper.FunctionMapper
 * 创建日期：2014年8月4日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.system.persistence.mapper;

import java.util.List;

import cn.cooperlink.ecplatform.system.entity.Function;
import cn.cooperlink.framework.core.BaseMapper;
import cn.cooperlink.spring.extension.Mapper;

/**
 * Mapper类。
 *
 * 创建日期：2014年8月4日
 * 创建作者：潘云峰
 */
@Mapper
public interface FunctionMapper extends BaseMapper {

	/**
	 * @Title: 删除已被购买的所有服务
	 * @Description:
	 * @param id
	 */
	void deleteSvrPurchaseByFunc(Long funcId);

	/**
	 * @Title: 删除角色授权的所有功能
	 * @Description:
	 */
	void deleteRoleFuncByFunc(Long funcId);

	/**
	 * @Title: 获取所有的菜单目录（不包含活动的菜单）
	 * @Description:
	 * @return
	 */
	List<Function> findAllCatalog();
	
}