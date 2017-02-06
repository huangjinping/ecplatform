/* **************************************************************
 *
 * 文件名称：Camera.java
 *
 * 包含类名：cn.cooperlink.myPet.entity.Camera
 * 创建日期：2014.08.07
 * 创建作者：dalvik
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.petstore.camera.entity;

import cn.cooperlink.framework.core.BaseEntity;

/**
 * 实体类。
 *
 * 创建日期：2014.08.07
 * 创建作者：dalvik
 */
public class Camera extends BaseEntity {

    private static final long serialVersionUID = -5993328633749279174L;
    private String name;
    private String IP;
    private Integer port;
    private String auth_username;
    private String auth_password;
    private String description;
    private Boolean enable;
    private Boolean binding;
    private String cage;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIP() {
        return IP;
    }
    public void setIP(String iP) {
        IP = iP;
    }
    public Integer getPort() {
        return port;
    }
    public void setPort(Integer port) {
        this.port = port;
    }
    public String getAuth_username() {
        return auth_username;
    }
    public void setAuth_username(String auth_username) {
        this.auth_username = auth_username;
    }
    public String getAuth_password() {
        return auth_password;
    }
    public void setAuth_password(String auth_password) {
        this.auth_password = auth_password;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Boolean getEnable() {
        return enable;
    }
    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
	public Boolean getBinding() {
		return binding;
	}
	public void setBinding(Boolean binding) {
		this.binding = binding;
	}
	public String getCage() {
		return cage;
	}
	public void setCage(String cage) {
		this.cage = cage;
	}

}