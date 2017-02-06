/* 
 * Project Name：移动电商平台
 * File Name：DicItem.java 
 * Package Name：cn.cooperlink.ecplatform.system.entity.DicItem
 * Date：2014-08-24 10:32:28
 * Copyright (c) 2014, cooperlink.cn All Rights Reserved.
 * 北京酷博灵科信息技术有限公司
 */
package cn.cooperlink.ecplatform.system.entity;

import cn.cooperlink.framework.core.BaseEUITreeEntity;

/**
 * @Title：DicItem
 * @Description：实体类。
 * @Package cn.cooperlink.ecplatform.system.entity
 * @ClassName DicItem
 * @author 潘云峰
 * @date 2014-08-24 10:32:28
 * @version 
 */
public class DicItem extends BaseEUITreeEntity {

	private static final long serialVersionUID = 8941787584212981866L;

    /**
     * @Fields name: 键值
     */
    private String itemValue;

    /**
     * @Fields name: 显示名称
     */
    private String displayName;

    /**
     * @Fields name: 所属字典
     */
    private Long dirId;

    /**
     * @Fields name: ID_FULLPATH
     */
    private String idFullpath;
    
    /**
     * @Fields nameFullpath: 名称路径
     */
    private String nameFullpath;

    /**
     * @Fields name: ORDER_FIELD
     */
    private Integer orderField;

    /**
     * @Fields name: 描述
     */
    private String description;

    /**
     * @Fields name: 所属商户
     */
    private Long tenantId;

    /**
     * @Fields name: 有效性
     */
    private Integer validity;

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue == null ? null : itemValue.trim();
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    public Long getDirId() {
        return dirId;
    }

    public void setDirId(Long dirId) {
        this.dirId = dirId;
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
		this.nameFullpath = nameFullpath;
	}

	public Integer getOrderField() {
        return orderField;
    }

    public void setOrderField(Integer orderField) {
        this.orderField = orderField;
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

}