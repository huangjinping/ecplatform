package cn.cooperlink.ecplatform.security.mobile;

public class BaseMobileAuthentication {

    /**
     * 是否是子账号
     */
    private boolean isSubMember;

    public boolean isSubMember() {
        return isSubMember;
    }

    public void setSubMember(boolean isSubMember) {
        this.isSubMember = isSubMember;
    }
}
