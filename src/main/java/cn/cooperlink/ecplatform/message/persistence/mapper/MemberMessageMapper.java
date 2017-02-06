/* **************************************************************
 *
 * 文件名称：MemberMessageMapper.java
 *
 * 包含类名：cn.cooperlink.myPet.dao.mapper.MemberMessageMapper
 * 创建日期：2014.08.07
 * 创建作者：dalvik
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.message.persistence.mapper;

import java.util.Date;
import java.util.List;

import cn.cooperlink.ecplatform.message.entity.MemberMessage;
import cn.cooperlink.ecplatform.message.entity.Message;
import cn.cooperlink.framework.core.BaseMapper;
import cn.cooperlink.spring.extension.Mapper;

/**
 * Mapper类。
 * 
 * 创建日期：2014.08.07 创建作者：dalvik
 */
@Mapper
public interface MemberMessageMapper extends BaseMapper {

    /**
     * 
     * @Title: getMyMessage
     * @Description: 获取会员消息列表
     * @param id
     * @return
     */
    public List<Message> getMyMessage(Long id, Date timestamp);
    
    /**
     * 
     * @Title: getMemberMessageByMsgId
     * @Description: 根据消息id获取所有关联关系
     * @param id
     * @return
     */
    public List<MemberMessage> getMemberMessageByMsgId(Long id);
}