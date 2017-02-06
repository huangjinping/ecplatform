/* **************************************************************
 *
 * 文件名称：Position.java
 *
 * 包含类名：cn.cooperlink.ecplatform.system.entity.Position
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
public class Position extends BaseEntity {

    
	/**
	 * @Fields serialVersionUID: TODO
	 */
		
	private static final long serialVersionUID = 816688400671304150L;

	/** 上级岗位 */
    private Long parentId;

    /** 名称 */
    private String name;

    /** 编码 */
    private String code;

    /** 描述 */
    private String description;

    /** 排序号 */
    private Integer orderField;

    /** ID路径 */
    private String idFullpath;

    /** 名称路径 */
    private String nameFullpath;

    /** 机构 */
    private Long orgId;

    /** 所属商户 */
    private Long tenantId;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getOrderField() {
        return orderField;
    }

    public void setOrderField(Integer orderField) {
        this.orderField = orderField;
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

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

}