/* **************************************************************
 *
 * 文件名称：PetService.java
 *
 * 包含类名：cn.cooperlink.ecplatform.petstore.pet.service.PetService
 * 创建日期：2014.08.07
 * 创建作者：dalvik
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.petstore.pet.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cn.cooperlink.ecplatform.petstore.camera.entity.PetCamera;
import cn.cooperlink.ecplatform.petstore.camera.persistence.mapper.PetCameraMapper;
import cn.cooperlink.ecplatform.petstore.pet.entity.MemberPet;
import cn.cooperlink.ecplatform.petstore.pet.entity.Pet;
import cn.cooperlink.ecplatform.petstore.pet.persistence.mapper.MemberPetMapper;
import cn.cooperlink.ecplatform.petstore.pet.persistence.mapper.PetMapper;
import cn.cooperlink.framework.core.BaseService4Mapper;
import cn.cooperlink.util.FDFSUtil;

/**
 * PetService 类
 * 
 * 创建日期：2014.08.07 创建作者：dalvik
 */
@Service
@Transactional
public class PetService extends BaseService4Mapper {

    @Resource
    private PetMapper petMapper;
    
    @Resource
    private MemberPetMapper memberPetMapper;
    
    @Resource 
    private PetCameraMapper petCameraMapper;

    @SuppressWarnings("unchecked")
    @Override
    public PetMapper getMapper() {
        return petMapper;
    }

    /**
     * 
     * @Title: getMyPet
     * @Description: 获取我的宠物列表
     * @param mem_id
     * @return
     */
    public List<Pet> getMyPet(Long mem_id) {
        return memberPetMapper.getMyPet(mem_id);
    }

    /**
     * 
     * @Title: addPet
     * @Description: 添加宠物
     * @param pet
     * @throws Exception
     */
    public void addPet(Long mem_id, Pet pet, MultipartFile myPhoto) throws Exception {
        Date date = new Date();
        pet.setCreateTime(date);
        pet.setUpdateTime(date);
        pet.setPhoto(FDFSUtil.upload("PET_PHOTO", myPhoto));
        // 插入宠物表
        petMapper.save(pet);
        // 插入宠物会员关系表
        MemberPet mp = new MemberPet();
        mp.setMem_id(mem_id);
        mp.setPet_id(pet.getId());
        memberPetMapper.save(mp);
    }

    /**
     * 
     * @Title: updatePet
     * @Description: 更新宠物信息
     * @param pet
     * @throws Exception
     */
    public void updatePet(Pet pet,MultipartFile myPhoto) throws Exception {
        pet.setUpdateTime(new Date());
        if(!myPhoto.isEmpty()){
        	if(FDFSUtil.deleteFile("PET_PHOTO", pet.getPhoto())){
            	pet.setPhoto(FDFSUtil.upload("PET_PHOTO", myPhoto));
            }
        }
        petMapper.update(pet);
    }

    /**
     * 
     * @Title: deletePet
     * @Description: 删除宠物
     * @param id
     * @throws Exception
     */
    public boolean deletePet(Long mem_id, Long id) throws Exception {
        
        // 检查宠物是否已经关联摄像头
        List<PetCamera> list = petCameraMapper.getPetCameraByPetId(id);
        if(list!=null && !list.isEmpty()){
            return false;
        }
        
        MemberPet mp = new MemberPet();
        mp.setMem_id(mem_id);
        mp.setPet_id(id);
        // 删除宠物关联表
        memberPetMapper.deleteMemberPet(mp);
        // 删除宠物表
        petMapper.delete(id);
        return true;
    }
    
    /**
     * 
     * @Title: findPet
     * @Description: 查看宠物详情
     * @param petId
     * @throws Exception
     */
    public Pet findPet(Long petId) throws Exception {
        //获取单个宠物详情
    	Pet pet = petMapper.findPet(petId);
    	return pet;
    }
    
}