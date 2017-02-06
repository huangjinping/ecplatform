/* **************************************************************
 *
 * 文件名称：OrgUser.java
 *
 * 包含类名：cn.cooperlink.ecplatform.system.entity.OrgUser
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
public class OrgUser extends BaseEntity {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 173866370432527238L;

	public static final int IS_PRIMARY = 1; 

    /** 机构 */
    private Long orgId;

    /** 人员 */
    private Long userId;

    /** 是否主部门 */
    private Integer isPrimary;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Integer isPrimary) {
        this.isPrimary = isPrimary;
    }

}