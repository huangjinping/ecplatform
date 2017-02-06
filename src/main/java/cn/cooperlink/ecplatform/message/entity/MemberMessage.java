/** 
 * Project Name：ecplatform 
 * File Name：MemberMessage.java 
 * Package Name：cn.cooperlink.ecplatform.message.entity 
 * Date：2014年8月18日 下午2:05:08 
 * Copyright (c) 2014, cooperlink.cn All Rights Reserved. 
 * 北京酷博灵科信息技术有限公司
 */
package cn.cooperlink.ecplatform.message.entity;

/**
 * @Title：
 * @Description：用户消息关联表
 * @Package： cn.cooperlink.ecplatform.message.entity
 * @ClassName： MemberMessage
 * @Author： dalvikchen
 * @Date： 2014年8月18日 下午2:05:08
 * @version： 
 */
public class MemberMessage {

    /** id */
    private Long id;
    private Long mem_id;
    private Long msg_id;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getMem_id() {
        return mem_id;
    }
    public void setMem_id(Long mem_id) {
        this.mem_id = mem_id;
    }
    public Long getMsg_id() {
        return msg_id;
    }
    public void setMsg_id(Long msg_id) {
        this.msg_id = msg_id;
    }
}
