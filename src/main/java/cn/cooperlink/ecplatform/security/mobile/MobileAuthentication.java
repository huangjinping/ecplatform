/* **************************************************************
 *
 * 文件名称：MobileAuthentication.java
 *
 * 包含类名：cn.cooperlink.ecplatform.security.MobileAuthentication
 * 创建日期：2014.08.14
 * 创建作者：dalvik
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.security.mobile;

import java.util.Date;
import java.util.List;

/**
 * 手机端登录认证信息类。
 * <p>
 * 保存登录用户信息
 * </p>
 * 
 * 创建日期：2014年8月14日 创建作者：dalvik
 */
public class MobileAuthentication extends BaseMobileAuthentication {

    /**
     * 会员id
     */
    private Long mem_id;
    /**
     * 会员姓名
     */
    private String name;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 电话
     */
    private String phone;
    /**
     * 邮件
     */
    private String email;
    /**
     * 性别
     */
    private Boolean gender;
    /**
     * 身份证
     */
    private String id_card;
    /**
     * QQ号码
     */
    private String QQ;
    /**
     * 出生日期
     */
    private Date birthday;
    /**
     * 昵称
     */
    private String nick_name;
    /**
     * 英文名称
     */
    private String english_name;
    /**
     * 工作
     */
    private String job;
    /**
     * 住址
     */
    private String address;
    /**
     * 籍贯
     */
    private String origin_place;
    /**
     * 照片地址
     */
    private String photo;

    /**
     * 所有子账号
     */
    private List<MobileAuthenticationSub> sub_members;

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

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String qQ) {
        QQ = qQ;
    }

    public Long getMem_id() {
        return mem_id;
    }

    public void setMem_id(Long mem_id) {
        this.mem_id = mem_id;
    }

    public List<MobileAuthenticationSub> getSub_members() {
        return sub_members;
    }

    public void setSub_members(List<MobileAuthenticationSub> sub_members) {
        this.sub_members = sub_members;
    }

}