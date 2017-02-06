/* **************************************************************
 *
 * 文件名称：MemberService.java
 *
 * 包含类名：cn.cooperlink.ecplatform.member.service.MemberService
 * 创建日期：2014.08.07
 * 创建作者：dalvik
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.member.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cooperlink.ecplatform.member.entity.Member;
import cn.cooperlink.ecplatform.member.persistence.mapper.MemberMapper;
import cn.cooperlink.framework.core.BaseService4Mapper;

/**
 * MemberService 类
 * 
 * 创建日期：2014.08.07 创建作者：dalvik
 */
@Service
@Transactional
public class MemberService extends BaseService4Mapper {

    @Resource
    private MemberMapper memberMapper;

    @SuppressWarnings("unchecked")
    @Override
    public MemberMapper getMapper() {
        return memberMapper;
    }

    /**
     * @Title: updateMember
     * @Description: 更新会员信息
     * @param member
     * @throws Exception
     */
    public void updateMember(Member member) throws Exception {
        member.setUpdateTime(new Date());
        memberMapper.update(member);
    }

    /**
     * 
     * @Title: getAllEnableMembers
     * @Description: 获取所有可用会员的ID列表
     * @return
     */
    public List<Long> getAllEnableMembers(){
        return memberMapper.getAllEnableMembers();
    }
    
    /**
     * 
     * @Title: loadMemberInfo
     * @Description: 查询会员详情
     * @param id
     * @return
     */
    public Member loadMemberInfo(Long id){
        return memberMapper.loadMemberInfo(id);
    }
    
    /**
     * 
     * @Title: findByMobile
     * @Description: 根据手机号码获取会员信息
     * @param mobile
     * @return
     */
    public Member findByMobile(String mobile){
        return memberMapper.findByMobile(mobile);
    }
}