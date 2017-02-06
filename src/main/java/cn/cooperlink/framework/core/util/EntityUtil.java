/* **************************************************************
 *
 * 文件名称：BeanUtil.java
 *
 * 包含类名：cn.cooperlink.framework.core.util.BeanUtil
 * 创建日期：2014年2月24日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.framework.core.util;

import java.util.Date;

import cn.cooperlink.ecplatform.security.AuthHelper;
import cn.cooperlink.framework.core.BaseEntity;

/**
 * 实体工具类。
 * 
 * 创建日期：2014年2月24日
 * 创建作者：潘云峰
 */
public class EntityUtil {

	/**
	 * 初始化实体基本属性（定义在BaseEntity中的属性）的值。
	 * 
	 * @param entity
	 */
	public static void setBaseProps4Save(BaseEntity entity) {
		if (entity == null) {
			throw new NullPointerException("实体对象为空 (null)。");
		}
		Date now = new Date();
		entity.setCreateBy(AuthHelper.getUserId());
		entity.setCreateTime(now);
		entity.setUpdateBy(AuthHelper.getUserId());
		entity.setUpdateTime(now);
	}
	
	/**
	 * 为更新人和更新时间属性赋值。
	 * 
	 * @param entity
	 */
	public static void setBaseProps4Update(BaseEntity entity) {
		if (entity == null) {
			throw new NullPointerException("实体对象为空 (null)。");
		}
		entity.setUpdateBy(AuthHelper.getUserId());
		entity.setUpdateTime(new Date());
	}
	
}
