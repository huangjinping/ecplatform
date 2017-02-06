/* **************************************************************
 *
 * 文件名称：Constants.java
 *
 * 包含类名：cn.cooperlink.framework.core.Constants
 * 创建日期：2014年2月24日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.framework.core;

/**
 * 常量。
 * <p>系统常量定义</p>
 *
 * 创建日期：2014年2月24日
 * 创建作者：潘云峰
 */
public class Constants {
	
	/** 登录用户 */
	public static final String LOGIN_USER = "loginUser";
	
	/** 简单日期格式（年-月—日 ，yyyy-MM-dd） */
	public static final String DATE_FORMAT_SIMPLE = "yyyy-MM-dd";
	
	/** 完整日期格式（年-月—日 时:分:秒，yyyy-MM-dd hh:mm:ss） */
	public static final String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";

    /** -------message-------- **/
    public static final String MSG_SAVE_SUCCESS = "SaveSuccess";
    public static final String MSG_SAVE_ERROR = "SaveFailure";
    
    public static final String MSG_UPDATE_SUCCESS="UpdateSuccess";
    public static final String MSG_UPDATE_ERROR = "UpdateError";
    
    public static final String MSG_DELETE_SUCCESS="DeleteSuccess";
    public static final String MSG_DELETE_ERROR = "DeleteError";
    
    public static final String MSG_LOGIN_ERROR = "LOGIN.M0001";
    public static final String MSG_LOGIN_SUCCESS = "LOGIN.M0002";
    
    public static final String MSG_FORM_ARGUMENTS_ERROR = "FORM.M0001";
    
    public static final String MSG_PASSWD_CHANGE_NOTSAME_ERROR = "PASSWD.M0001";
    public static final String MSG_PASSWD_CHANGE_WRONG_ERROR = "PASSWD.M0002";
    public static final String MSG_PASSWD_CHANGE_SAME_ERROR = "PASSWD.M0003";
    public static final String MSG_PASSWD_CHANGE_LENGTH_ERROR = "PASSWD.M0004";
    
    public static final String MSG_MOBILE_EXISTS_ERROR = "MOBILE.M0001";
    
    public static final String MSG_SUBMEMBER_NOCHILD_ERROR = "SUBMEMBER.M0001";
    public static final String MSG_SUBMEMBER_MAX_ERROR = "SUBMEMBER.M0002";
    
    public static final String MSG_PET_DELETE_ERROR = "PET.M0001";
    
    public static final String MSG_CAMERA_DELETE_ERROR = "CAM.M0001";
    public static final String MSG_CAMERA_RETRIEVE_ERROR = "CAM.M0002";
    
    public static final String MSG_MESSAGE_RETRIEVE_ERROR = "MESSAGE.M0001";
    
    public static final String MSG_MEMBER_RETRIEVE_ERROR = "MEMBER.M0001";
    /** -------message-------- **/
}
