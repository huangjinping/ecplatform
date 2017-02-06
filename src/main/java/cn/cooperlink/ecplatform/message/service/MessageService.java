/* **************************************************************
 *
 * 文件名称：MessageService.java
 *
 * 包含类名：cn.cooperlink.ecplatform.petstore.camera.service.MessageService
 * 创建日期：2014.08.07
 * 创建作者：dalvik
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.message.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cooperlink.ecplatform.message.entity.MemberMessage;
import cn.cooperlink.ecplatform.message.entity.Message;
import cn.cooperlink.ecplatform.message.persistence.mapper.MemberMessageMapper;
import cn.cooperlink.ecplatform.message.persistence.mapper.MessageMapper;
import cn.cooperlink.framework.core.BaseService4Mapper;

/**
 * MessageService 类
 * 
 * 创建日期：2014.08.07 创建作者：dalvik
 */
@Service
@Transactional
public class MessageService extends BaseService4Mapper {

    @Resource
    private MessageMapper messageMapper;

    @Resource
    private MemberMessageMapper memberMessageMapper;

    @SuppressWarnings("unchecked")
    @Override
    public MessageMapper getMapper() {
        return messageMapper;
    }

    /**
     * 
     * @Title: getMyMessage
     * @Description: 获取我的消息列表
     * @param mem_id
     * @return
     */
    public List<Message> getMyMessage(Long mem_id, Date timestamp) {
        return memberMessageMapper.getMyMessage(mem_id, timestamp);
    }

    /**
     * 添加消息
     * 
     * @Title: addMessage
     * @Description:
     * @param camera
     * @throws Exception
     */
    public void addMessage(List<Long> mem_id_list, Message message) throws Exception {
        List<MemberMessage> memberMessageList = new ArrayList<MemberMessage>();
        Date date = new Date();
        message.setCreateTime(date);
        message.setUpdateTime(date);
        // 保存消息表
        messageMapper.save(message);
        Long msg_id = message.getId();
        // 保存会员消息关联表
        for (int i = 0; i < mem_id_list.size(); i++) {
            MemberMessage mm = new MemberMessage();
            mm.setMem_id(mem_id_list.get(i));
            mm.setMsg_id(msg_id);
            memberMessageList.add(mm);
        }
        memberMessageMapper.saveBatch(memberMessageList);
    }

    /**
     * 
     * @Title: deleteMessage
     * @Description: 删除消息
     * @param id
     * @throws Exception
     */
    public void deleteMessage(Long id) throws Exception {
        List<MemberMessage> list = memberMessageMapper.getMemberMessageByMsgId(id);
        if(list!=null&&!list.isEmpty()){
            List<Long> msgIdList = new ArrayList<Long>();
            
            for(int i=0;i<list.size();i++){
                msgIdList.add(list.get(i).getId());
            }
            memberMessageMapper.deleteBatch(msgIdList);
        }
    }
}