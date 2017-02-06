/* **************************************************************
 *
 * 文件名称：Message.java
 *
 * 包含类名：cn.cooperlink.myPet.entity.Message
 * 创建日期：2014.08.07
 * 创建作者：dalvik
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.message.entity;

import java.util.Date;

import cn.cooperlink.framework.core.BaseEntity;

/**
 * 实体类。
 * 
 * 创建日期：2014.08.07 创建作者：dalvik
 */
public class Message extends BaseEntity {

    private static final long serialVersionUID = 7904506922712830938L;

    private String title;
    private String message;
    private String type;
    private Date send_time;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSend_time() {
        return send_time;
    }

    public void setSend_time(Date send_time) {
        this.send_time = send_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}