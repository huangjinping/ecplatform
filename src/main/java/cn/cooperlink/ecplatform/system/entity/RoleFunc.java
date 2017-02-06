/* **************************************************************
 *
 * 文件名称：RoleFunc.java
 *
 * 包含类名：cn.cooperlink.ecplatform.system.entity.RoleFunc
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
public class RoleFunc extends BaseEntity {

	/**
	 * @Fields serialVersionUID: TODO
	 */
	private static final long serialVersionUID = 4514051790193364734L;

	/** 角色 */
    private Long roleId;

    /** 功能 */
    private Long funcId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getFuncId() {
        return funcId;
    }

    public void setFuncId(Long funcId) {
        this.funcId = funcId;
    }

}