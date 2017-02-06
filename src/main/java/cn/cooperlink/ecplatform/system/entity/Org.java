/* **************************************************************
 *
 * 文件名称：Org.java
 *
 * 包含类名：cn.cooperlink.ecplatform.system.entity.Org
 * 创建日期：2014年8月7日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.system.entity;

import cn.cooperlink.framework.core.BaseEUITreeEntity;

/**
 * 实体类。
 *
 * 创建日期：2014年8月7日
 * 创建作者：潘云峰
 */
public class Org extends BaseEUITreeEntity {

    /** serialVersionUID */
	private static final long serialVersionUID = 5502135219167201700L;
	
	// 公司
	public static final int ORG_TYPE_ORG = 1;
	
	// 部门
	public static final int ORG_TYPE_DEPT =  2;

	/** 上级机构 */
    private Long parentId;
    
    /** 父级机构名称 */
    private String parentOrgName;

    /** 简称 */
    private String shortName;

    /** 编码 */
    private String code;

    /** 全称 */
    private String fullName;

    /** 负责人 */
    private Long charge;

    /** 机构类型 */
    private Integer orgType;

    /** ID路径 */
    private String idFullpath;

    /** 名称路径 */
    private String nameFullpath;

    /** 排序号 */
    private String orderField;

    /** 描述 */
    private String description;

    /** 所属商户 */
    private Long tenantId;

    /** 有效性 */
    private Integer validity;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentOrgName() {
		return parentOrgName;
	}

	public void setParentOrgName(String parentOrgName) {
		this.parentOrgName = parentOrgName;
	}

	public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    public Long getCharge() {
        return charge;
    }

    public void setCharge(Long charge) {
        this.charge = charge;
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }

    public String getIdFullpath() {
        return idFullpath;
    }

    public void setIdFullpath(String idFullpath) {
        this.idFullpath = idFullpath == null ? null : idFullpath.trim();
    }

    public String getNameFullpath() {
        return nameFullpath;
    }

    public void setNameFullpath(String nameFullpath) {
        this.nameFullpath = nameFullpath == null ? null : nameFullpath.trim();
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField == null ? null : orderField.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }
    
    public String getOrgTypeName() {
    	if (orgType == ORG_TYPE_ORG) {
    		return "公司";
    	}
    	if (orgType == ORG_TYPE_DEPT) {
    		return "部门";
    	}
    	return "";
    }

	@Override
	public String getText() {
		return shortName;
	}

}