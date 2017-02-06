/* **************************************************************
 *
 * 文件名称：Member.java
 *
 * 包含类名：cn.cooperlink.myPet.entity.Member
 * 创建日期：2014.08.07
 * 创建作者：dalvik
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.member.entity;

import cn.cooperlink.framework.core.BaseEntity;

/**
 * 实体类。
 *
 * 创建日期：2014.08.07
 * 创建作者：dalvik
 */
public class SubMember extends BaseEntity {

    private static final long serialVersionUID = 5953591378926544555L;

    private String name;
    private String user_name;
    private String mobile;
    private String passwd;
    private Boolean gender;
    private Boolean enable;
    private Long p_mem_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Long getP_mem_id() {
        return p_mem_id;
    }

    public void setP_mem_id(Long p_mem_id) {
        this.p_mem_id = p_mem_id;
    }
    
}