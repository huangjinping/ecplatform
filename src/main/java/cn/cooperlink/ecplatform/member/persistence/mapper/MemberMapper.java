/* **************************************************************
 *
 * 文件名称：CustomerMapper.java
 *
 * 包含类名：cn.cooperlink.myPet.dao.mapper.CustomerMapper
 * 创建日期：2014.08.07
 * 创建作者：dalvik
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.member.persistence.mapper;

import java.util.List;

import cn.cooperlink.ecplatform.member.entity.Member;
import cn.cooperlink.framework.core.BaseMapper;
import cn.cooperlink.spring.extension.Mapper;

/**
 * Mapper类。
 * 
 * 创建日期：2014.08.07 创建作者：dalvik
 */
@Mapper
public interface MemberMapper extends BaseMapper {

    /**
     * 
     * @Title: getAllEnableMembers
     * @Description: 获取所有可用会员ID列表
     * @return
     */
    public List<Long> getAllEnableMembers();
    
    /**
     * 
     * @Title: loadMemberInfo
     * @Description: 获取会员详情
     * @param id
     * @return
     */
    public Member loadMemberInfo(Long id);
    
    /**
     * 
     * @Title: findByMobile
     * @Description: 根据手机号码获取会员信息
     * @param mobile
     * @return
     */
    public Member findByMobile(String mobile);
}