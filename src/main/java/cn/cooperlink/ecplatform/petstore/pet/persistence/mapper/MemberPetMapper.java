/* **************************************************************
 *
 * 文件名称：PetMapper.java
 *
 * 包含类名：cn.cooperlink.myPet.dao.mapper.PetMapper
 * 创建日期：2014.08.07
 * 创建作者：dalvik
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.petstore.pet.persistence.mapper;

import java.util.List;

import cn.cooperlink.ecplatform.petstore.pet.entity.MemberPet;
import cn.cooperlink.ecplatform.petstore.pet.entity.Pet;
import cn.cooperlink.framework.core.BaseMapper;
import cn.cooperlink.spring.extension.Mapper;

/**
 * Mapper类。
 *
 * 创建日期：2014.08.07
 * 创建作者：dalvik
 */
@Mapper
public interface MemberPetMapper extends BaseMapper {

    /**
     * 删除宠物会员关系表
     * 
     * @Title: deleteMemberPet
     * @Description:
     * @param mem_id
     * @param pet_id
     */
    public void deleteMemberPet(MemberPet memberPet);
    
    /**
     * 获取会员宠物列表
     * 
     * @Title: getMyPet
     * @Description:
     * @param id
     * @return
     */
    public List<Pet> getMyPet(Long id);
    
    
}