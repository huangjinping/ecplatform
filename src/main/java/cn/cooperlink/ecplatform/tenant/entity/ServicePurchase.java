/* **************************************************************
 *
 * 文件名称：ServicePurchase.java
 *
 * 包含类名：cn.cooperlink.ecplatform.tenant.entity.ServicePurchase
 * 创建日期：2014年8月4日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.tenant.entity;

import cn.cooperlink.ecplatform.system.FuncTreeEntity;
import cn.cooperlink.ecplatform.system.entity.Function;

import java.util.Date;

/**
 * 实体类。
 *
 * 创建日期：2014年8月4日
 * 创建作者：潘云峰
 */
public class ServicePurchase extends FuncTreeEntity {

    /** serialVersionUID */
	private static final long serialVersionUID = 7240618970198229407L;
	
	private Long spId;

	/** 注册商户 */
    private Long tenantId;
    
    /** 注册商户 */
    private String tenantName;

    /** 功能 */
    private Long funcId;
    
    /** 功能服务名称 */
    private String funcName;

    /** 购买日期 */
    private Date purchaseDate;

    /** 到期日期 */
    private Date expiryDate;

    /** 功能编码 */
    private String funcCode;

    /** 访问路径 */
    private String url;

    public Long getSpId() {
		return spId;
	}

	public void setSpId(Long spId) {
		this.spId = spId;
	}

	public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getFuncId() {
        return funcId;
    }

    public void setFuncId(Long funcId) {
        this.funcId = funcId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getFuncCode() {
		return funcCode;
	}

	public void setFuncCode(String funcCode) {
		this.funcCode = funcCode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@SuppressWarnings("unchecked")
	public ServicePurchase fromFunc(Function function) {
		ServicePurchase sp = new ServicePurchase();
		sp.setId(function.getId());
		sp.setParentId(function.getParentId());
		sp.setFuncName(function.getFuncName());
		sp.setIconCls(function.getIconCls());
		sp.setFuncId(function.getId());
		return sp;
	}

}