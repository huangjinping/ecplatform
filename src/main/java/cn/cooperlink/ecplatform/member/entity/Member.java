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

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.cooperlink.framework.core.BaseEntity;

/**
 * 实体类。
 * 
 * 创建日期：2014.08.07 创建作者：dalvik
 */
public class Member extends BaseEntity {

    private static final long serialVersionUID = 5953591378926544555L;

    private String name;
    private String user_name;
    private String mobile;
    private String phone;
    private String email;
    private String passwd;
    private Boolean gender;
    private String id_card;
    private String QQ;
    private Date birthday;
    private String nick_name;
    private String english_name;
    private String job;
    private String address;
    private String origin_place;
    private String photo;
    private Boolean enable;
    private BigDecimal balance;
    private Long petnum;

    private List<SubMember> sub_members;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getEnglish_name() {
        return english_name;
    }

    public void setEnglish_name(String english_name) {
        this.english_name = english_name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrigin_place() {
        return origin_place;
    }

    public void setOrigin_place(String origin_place) {
        this.origin_place = origin_place;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String qQ) {
        QQ = qQ;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getPetnum() {
        return petnum;
    }

    public void setPetnum(Long petnum) {
        this.petnum = petnum;
    }

    public List<SubMember> getSub_members() {
        return sub_members;
    }

    public void setSub_members(List<SubMember> sub_members) {
        this.sub_members = sub_members;
    }

}