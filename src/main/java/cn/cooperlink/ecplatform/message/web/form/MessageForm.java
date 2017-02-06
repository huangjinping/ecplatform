/** 
 * Project Name：ecplatform 
 * File Name：MessageForm.java 
 * Package Name：cn.cooperlink.ecplatform.message.web.form 
 * Date：2014年8月19日 上午8:46:37 
 * Copyright (c) 2014, cooperlink.cn All Rights Reserved. 
 * 北京酷博灵科信息技术有限公司
 */
package cn.cooperlink.ecplatform.message.web.form;

import java.util.Date;

/**
 * @Title：
 * @Description：页面消息表单
 * @Package： cn.cooperlink.ecplatform.message.web.form
 * @ClassName： MessageForm
 * @Author： dalvikchen
 * @Date： 2014年8月19日 上午8:46:37
 * @version：
 */
public class MessageForm {
    private String title;
    private String message;
    private String type;
    private Date send_time;
    private String mem_ids;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getSend_time() {
        return send_time;
    }

    public void setSend_time(Date send_time) {
        this.send_time = send_time;
    }

    public String getMem_ids() {
        return mem_ids;
    }

    public void setMem_ids(String mem_ids) {
        this.mem_ids = mem_ids;
    }
}
