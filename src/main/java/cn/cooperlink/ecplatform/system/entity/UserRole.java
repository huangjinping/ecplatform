/* **************************************************************
 *
 * 文件名称：UserRole.java
 *
 * 包含类名：cn.cooperlink.ecplatform.system.entity.UserRole
 * 创建日期：2014年8月9日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.system.entity;

import cn.cooperlink.framework.core.BaseEntity;

/**
 * 实体类。
 *
 * 创建日期：2014年8月9日
 * 创建作者：潘云峰
 */
public class UserRole extends BaseEntity {

	/**
	 * @Fields serialVersionUID: TODO
	 */
	private static final long serialVersionUID = -4290121279988021458L;

	/** 用户 */
    private Long userId;

    /** 角色 */
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}