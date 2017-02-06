/* **************************************************************
 *
 * 文件名称：SubMemberMapper.java
 *
 * 包含类名：cn.cooperlink.myPet.dao.mapper.SubMemberMapper
 * 创建日期：2014.08.07
 * 创建作者：dalvik
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.member.persistence.mapper;

import cn.cooperlink.ecplatform.member.entity.SubMember;
import cn.cooperlink.framework.core.BaseMapper;
import cn.cooperlink.spring.extension.Mapper;

/**
 * Mapper类。
 *
 * 创建日期：2014.08.07
 * 创建作者：dalvik
 */
@Mapper
public interface SubMemberMapper extends BaseMapper {

    /**
     * 
     * @Title: findByMobile
     * @Description: 根据手机号码获取子会员
     * @param mobile
     * @return
     */
    public SubMember findByMobile(String mobile);
}