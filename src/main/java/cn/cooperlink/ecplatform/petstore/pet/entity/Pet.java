/* **************************************************************
 *
 * 文件名称：Pet.java
 *
 * 包含类名：cn.cooperlink.myPet.entity.Pet
 * 创建日期：2014.08.07
 * 创建作者：dalvik
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.petstore.pet.entity;

import java.util.Date;

import cn.cooperlink.framework.core.BaseEntity;

/**
 * 实体类。
 *
 * 创建日期：2014.08.07
 * 创建作者：dalvik
 */
public class Pet extends BaseEntity {

    private static final long serialVersionUID = -5233630117788790679L;
    private String nick_name;
    private Boolean gender;
    private Date birthday;
    private String description;
    private String status;
    private String category;
    private String small_category;
    private Date startfoster_time;
    private Date endfoster_time;
    private Date realend_time;
    private String photo;

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSmall_category() {
        return small_category;
    }

    public void setSmall_category(String small_category) {
        this.small_category = small_category;
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

    public Date getStartfoster_time() {
        return startfoster_time;
    }

    public void setStartfoster_time(Date startfoster_time) {
        this.startfoster_time = startfoster_time;
    }

    public Date getEndfoster_time() {
        return endfoster_time;
    }

    public void setEndfoster_time(Date endfoster_time) {
        this.endfoster_time = endfoster_time;
    }

    public Date getRealend_time() {
        return realend_time;
    }

    public void setRealend_time(Date realend_time) {
        this.realend_time = realend_time;
    }

}