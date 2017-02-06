/* **************************************************************
 *
 * 文件名称：Authentication.java
 *
 * 包含类名：cn.cooperlink.ecplatform.security.Authentication
 * 创建日期：2014年8月11日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 大唐云动力科技股份有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.security;

import java.util.List;

/**
 * 认证信息类。
 * <p>保存登录用户信息</p>
 *
 * 创建日期：2014年8月11日
 * 创建作者：潘云峰
 */
public class Authentication {
	
	/** 用户名 */
	private Long userId;
	
	/** 机构ID */
	private Long orgId;
	
	/** 所属商户ID */
	private Long tenantId;
	
	/** 用户名 */
	private String username;
	
	/** 用户姓名 */
	private String name;
	
	/** 主部门名 */
	private String orgName;
	
	/** 员工姓名 */
	private String employeeNo;
	
	/** QQ*/
	private String qq;
	
	/** email */
	private String email;
	
	/** 性别 */
	private int sex;
	
	/** 用户头像 */
	private String photo;
	
	/** 用户类型 */
	private Integer userType; 
	
	/**
	 * @Fields orgIdFullpath: 组织机构ID路径
	 */
	private String orgIdFullpath;
	
	/**
	 * @Fields orgNameFullpath: 组织机构名称路径
	 */
	private String orgNameFullpath;
	
	/**
	 * @Fields database: 用户数据库
	 */
	private String database;
	
	/**
	 * @Fields permissionList: 权限列表
	 */
	private List<Permission> permissionList;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getOrgIdFullpath() {
		return orgIdFullpath;
	}

	public void setOrgIdFullpath(String orgIdFullpath) {
		this.orgIdFullpath = orgIdFullpath;
	}

	public String getOrgNameFullpath() {
		return orgNameFullpath;
	}

	public void setOrgNameFullpath(String orgNameFullpath) {
		this.orgNameFullpath = orgNameFullpath;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public List<Permission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
	
    
}
