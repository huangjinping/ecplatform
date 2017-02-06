/* **************************************************************
 *
 * 文件名称：RegTenant.java
 *
 * 包含类名：cn.cooperlink.ecplatform.tenant.entity.RegTenant
 * 创建日期：2014年7月31日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 大唐云动力科技股份有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.tenant.entity;

import cn.cooperlink.framework.core.BaseEntity;
import java.util.Date;

/**
 * 注册商户实体类。
 *
 * 创建日期：2014年7月31日
 * 创建作者：潘云峰
 */
public class RegTenant extends BaseEntity {

	private static final long serialVersionUID = 270518585984994470L;

    /** 中文简称 */
    private String cnShortName;

    /** 中文全称 */
    private String cnFullName;

    /** 英文名称 */
    private String enName;

    /** 企业代码 */
    private String code;

    /** 注册地址 */
    private String regLocation;

    /** 法人代表 */
    private String legalPerson;

    /** 商户类型 */
    private Integer type;

    /** 所属行业 */
    private String industry;

    /** 经营范围 */
    private String bizScope;

    /** 企业电话 */
    private String phoneNumber;

    /** 企业地址 */
    private String location;

    /** 邮编 */
    private String zipCode;

    /** 系统注册码 */
    private String sysRegCode;

    /** 注册日期 */
    private Date sysRegDate;

    /** 备注 */
    private String remark;

    /** 排序号 */
    private Integer orderField;
    
    private Long orgId;

    /** 有效性 */
    private Integer validity;

    public String getCnShortName() {
        return cnShortName;
    }

    public void setCnShortName(String cnShortName) {
        this.cnShortName = cnShortName == null ? null : cnShortName.trim();
    }

    public String getCnFullName() {
        return cnFullName;
    }

    public void setCnFullName(String cnFullName) {
        this.cnFullName = cnFullName == null ? null : cnFullName.trim();
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName == null ? null : enName.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getRegLocation() {
        return regLocation;
    }

    public void setRegLocation(String regLocation) {
        this.regLocation = regLocation == null ? null : regLocation.trim();
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson == null ? null : legalPerson.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public String getBizScope() {
        return bizScope;
    }

    public void setBizScope(String bizScope) {
        this.bizScope = bizScope == null ? null : bizScope.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
    }

    public String getSysRegCode() {
        return sysRegCode;
    }

    public void setSysRegCode(String sysRegCode) {
        this.sysRegCode = sysRegCode == null ? null : sysRegCode.trim();
    }

    public Date getSysRegDate() {
        return sysRegDate;
    }

    public void setSysRegDate(Date sysRegDate) {
        this.sysRegDate = sysRegDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getOrderField() {
        return orderField;
    }

    public void setOrderField(Integer orderField) {
        this.orderField = orderField;
    }

    public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }

}