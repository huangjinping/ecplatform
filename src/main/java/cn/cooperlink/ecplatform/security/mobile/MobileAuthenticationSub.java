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


/**
 * 手机端登录认证信息类。
 * <p>
 * 保存登录用户信息
 * </p>
 * 
 * 创建日期：2014年8月14日 创建作者：dalvik
 */
public class MobileAuthenticationSub extends BaseMobileAuthentication{

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
     * 性别
     */
    private Boolean gender;

    /**
     * 父账号信息
     */
    private MobileAuthentication parent_member;


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

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public MobileAuthentication getParent_member() {
        return parent_member;
    }

    public void setParent_member(MobileAuthentication parent_member) {
        this.parent_member = parent_member;
    }

    public Long getMem_id() {
        return mem_id;
    }

    public void setMem_id(Long mem_id) {
        this.mem_id = mem_id;
    }

}