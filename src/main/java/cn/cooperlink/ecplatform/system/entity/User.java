/* **************************************************************
 *
 * 文件名称：User.java
 *
 * 包含类名：cn.cooperlink.ecplatform.system.entity.User
 * 创建日期：2014年8月6日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.system.entity;

import cn.cooperlink.framework.core.BaseEntity;

/**
 * 实体类。
 *
 * 创建日期：2014年8月6日
 * 创建作者：潘云峰
 */
public class User extends BaseEntity {

    /** serialVersionUID */
	private static final long serialVersionUID = 680043890711663653L;
	
	/** 机构ID */
	private Long orgId;
	
	/** 机构名称 */
	private String orgName;
	
	private String orgNameFullpath;

	/** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    /** 员工编号 */
    private String employeeNo;

    /** 姓名 */
    private String name;

    /** 电话号码 */
    private String phoneNo;
    
    private int sex;

    /** QQ */
    private String qq;

    /** 电子邮件 */
    private String eMail;

    /** 头像 */
    private String photo;

    /** 账户类型 */
    private Integer userType;

    /** 备注 */
    private String remark;

    /** 排序号 */
    private Integer orderField;

    /** 所属商户 */
    private Long tenantId;
    
    private Integer validity;

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgNameFullpath() {
		return orgNameFullpath;
	}

	public void setOrgNameFullpath(String orgNameFullpath) {
		this.orgNameFullpath = orgNameFullpath;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo == null ? null : employeeNo.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim();
    }

    public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail == null ? null : eMail.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
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