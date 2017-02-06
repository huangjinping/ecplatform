/* **************************************************************
 *
 * 文件名称：PasswordChangeForm.java
 *
 * 包含类名：cn.cooperlink.myPet.entity.PasswordChangeForm
 * 创建日期：2014.08.15
 * 创建作者：dalvik
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.member.mobile.form;

/**
 * 手机端表单参数接收实体
 * 
 * 创建日期：2014.08.15 创建作者：dalvik
 */
public class PasswordChangeForm {

    private String oldPassword;
    private String newPassword;
    private String newPasswordTwice;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordTwice() {
        return newPasswordTwice;
    }

    public void setNewPasswordTwice(String newPasswordTwice) {
        this.newPasswordTwice = newPasswordTwice;
    }

}
