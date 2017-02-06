/* **************************************************************
 *
 * 文件名称：TenantConfig.java
 *
 * 包含类名：cn.cooperlink.ecplatform.tenant.entity.TenantConfig
 * 创建日期：2014年8月17日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.tenant.entity;

import cn.cooperlink.framework.core.BaseEntity;

/**
 * 实体类。
 *
 * 创建日期：2014年8月17日
 * 创建作者：潘云峰
 */
public class TenantConfig extends BaseEntity {

	private static final long serialVersionUID = -960739441669894004L;

	/** 注册商户 */
    private Long tenantId;

    /** 属性名称 */
    private Long paramDefId;

    /** 属性值 */
    private String configValue;
    
    /**
     * @Fields paramName: 参数值
     */
    private String paramName;

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getParamDefId() {
        return paramDefId;
    }

    public void setParamDefId(Long paramDefId) {
        this.paramDefId = paramDefId;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue == null ? null : configValue.trim();
    }

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

}