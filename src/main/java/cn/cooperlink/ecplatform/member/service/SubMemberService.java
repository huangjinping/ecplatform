/* **************************************************************
 *
 * 文件名称：SubMemberService.java
 *
 * 包含类名：cn.cooperlink.ecplatform.member.service.SubMemberService
 * 创建日期：2014.08.07
 * 创建作者：dalvik
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.member.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cooperlink.ecplatform.member.entity.SubMember;
import cn.cooperlink.ecplatform.member.persistence.mapper.SubMemberMapper;
import cn.cooperlink.framework.core.BaseService4Mapper;

/**
 * SubMemberService 类
 * 
 * 创建日期：2014.08.07 创建作者：dalvik
 */
@Service
@Transactional
public class SubMemberService extends BaseService4Mapper {


    @Resource
    private SubMemberMapper subMemberMapper;

    @SuppressWarnings("unchecked")
    @Override
    public SubMemberMapper getMapper() {
        return subMemberMapper;
    }

    /**
     * 添加子账号
     * @Title: addSubMember
     * @Description:
     * @param subMember
     * @throws Exception
     */
    public void addSubMember(SubMember subMember) throws Exception {
        Date date = new Date();
        subMember.setCreateTime(date);
        subMember.setUpdateTime(date);
        subMemberMapper.save(subMember);
    }

    /**
     * 
     * @Title: updateSubMember
     * @Description:更新子账号信息
     * @param subMember
     * @throws Exception
     */
    public void updateSubMember(SubMember subMember) throws Exception {
        subMember.setUpdateTime(new Date());
        subMemberMapper.update(subMember);
    }
    
    /**
     * 
     * @Title: findByMobile
     * @Description: 根据手机号码获取会员信息
     * @param mobile
     * @return
     */
    public SubMember findByMobile(String mobile){
        return subMemberMapper.findByMobile(mobile);
    }
}