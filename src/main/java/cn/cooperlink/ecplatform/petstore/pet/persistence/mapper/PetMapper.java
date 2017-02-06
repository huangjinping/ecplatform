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
public interface PetMapper extends BaseMapper {
	/**
     * 查询宠物详情
     * 
     * @Title: findPet
     * @Description:
     * @param petId
     */
    public Pet findPet(Long petId);
    
    /**
     * 根据宠物ID查询主人ID
     * 
     * @Title: findMemByPet
     * @Description:
     * @param petId
     */
    public Long findMemByPet(Long petId);
}