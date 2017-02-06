/* 
 * Project Name：移动电商平台
 * File Name：DicDefinition.java 
 * Package Name：cn.cooperlink.ecplatform.system.entity.DicDefinition
 * Date：2014-08-24 10:30:58
 * Copyright (c) 2014, cooperlink.cn All Rights Reserved.
 * 北京酷博灵科信息技术有限公司
 */
package cn.cooperlink.ecplatform.system.entity;

import cn.cooperlink.framework.core.BaseEntity;

/**
 * @Title：DicDefinition
 * @Description：实体类。
 * @Package cn.cooperlink.ecplatform.system.entity
 * @ClassName DicDefinition
 * @author 潘云峰
 * @date 2014-08-24 10:30:59
 * @version 
 */
public class DicDefinition extends BaseEntity {

	private static final long serialVersionUID = 6197004019412863071L;

	/**
     * @Fields name: 字典编码
     */
    private String code;

    /**
     * @Fields name: 字典名称
     */
    private String name;

    /**
     * @Fields name: 字典描述
     */
    private String description;

    /**
     * @Fields name: 字典结构 0 - 平级 ; 1 - 层级
     */
    private Integer hierarchies;

    /**
     * @Fields name: 应用范围    1 - 全局 ; 2 - 租户
     */
    private Integer scope;

    /**
     * @Fields name: 标识
     */
    private Integer flag;

    /**
     * @Fields name: 有效性
     */
    private Integer validity;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getHierarchies() {
        return hierarchies;
    }

    public void setHierarchies(Integer hierarchies) {
        this.hierarchies = hierarchies;
    }

    public Integer getScope() {
        return scope;
    }

    public void setScope(Integer scope) {
        this.scope = scope;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }

}