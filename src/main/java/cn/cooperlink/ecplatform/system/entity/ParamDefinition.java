/* **************************************************************
 *
 * 文件名称：ParamDefinition.java
 *
 * 包含类名：cn.cooperlink.ecplatform.system.entity.ParamDefinition
 * 创建日期：2014年8月17日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.system.entity;

import cn.cooperlink.framework.core.BaseEntity;

/**
 * 实体类。
 *
 * 创建日期：2014年8月17日
 * 创建作者：潘云峰
 */
public class ParamDefinition extends BaseEntity {

	private static final long serialVersionUID = -4871209397914269271L;

	/** 参数名 */
    private String paramName;

    /** 显示名 */
    private String paramDisplayName;

    // 1 - 字符串， 2 - 整形， 3 - 浮点型， 4 - 布尔类型， 5 - 日期类型， 6 - 时间类型， 7 - 文件域， 8 - 枚举类型
    /** 参数值类型 */
    private String valueType;

    // 1 - 系统参数， 2 - 租户参数 ， 3 - 通用参数
    /** 所属分组 */
    private Integer paramType;

    // 1 - 商户可自行编辑， 2 -商户不可编辑
    /** 商户可编辑 */
    private Integer isEditable;

    // 1 - 缓存到本地， 2 - 保存在缓存服务器
    /** 缓存方式 */
    private Integer cacheType;

    /** 参数说明 */
    private String description;
    
    private Integer validity;

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName == null ? null : paramName.trim();
    }

    public String getParamDisplayName() {
        return paramDisplayName;
    }

    public void setParamDisplayName(String paramDisplayName) {
        this.paramDisplayName = paramDisplayName == null ? null : paramDisplayName.trim();
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType == null ? null : valueType.trim();
    }

    public Integer getParamType() {
        return paramType;
    }

    public void setParamType(Integer paramType) {
        this.paramType = paramType;
    }

    public Integer getIsEditable() {
        return isEditable;
    }

    public void setIsEditable(Integer isEditable) {
        this.isEditable = isEditable;
    }

    public Integer getCacheType() {
        return cacheType;
    }

    public void setCacheType(Integer cacheType) {
        this.cacheType = cacheType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

	public Integer getValidity() {
		return validity;
	}

	public void setValidity(Integer validity) {
		this.validity = validity;
	}

}