/* **************************************************************
 *
 * 文件名称：PetCameraMapper.java
 *
 * 包含类名：cn.cooperlink.ecplatform.petstore.camera.persistence.mapper.PetCameraMapper
 * 创建日期：2014.08.07
 * 创建作者：dalvik
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.petstore.camera.persistence.mapper;

import java.util.List;

import cn.cooperlink.ecplatform.petstore.camera.entity.Camera;
import cn.cooperlink.ecplatform.petstore.camera.entity.PetCamera;
import cn.cooperlink.framework.core.BaseMapper;
import cn.cooperlink.spring.extension.Mapper;

/**
 * Mapper类。
 *
 * 创建日期：2014.08.07
 * 创建作者：dalvik
 */
@Mapper
public interface PetCameraMapper extends BaseMapper {
    /**
     * 
     * @Title: getMyCamera
     * @Description: 获取我的摄像头列表
     * @param id
     * @return
     */
    public List<Camera> getMyCamera(Long id);
    
    /**
     * 
     * @Title: getPetCameraByCamId
     * @Description: 根据摄像头ID获取关联宠物表
     * @param id
     * @return
     */
    public List<PetCamera> getPetCameraByCamId(Long id);
    
    /**
     * 
     * @Title: getPetCameraByPetId
     * @Description: 根据宠物ID获取关联宠物表
     * @param id
     * @return
     */
    public List<PetCamera> getPetCameraByPetId(Long id);
    
    /**
     * 
     * @Title: deletePetCamera
     * @Description: 根据摄像头id和宠物id解除关联关系
     * @param cam_id
     * @param pet_id
     * @return
     */
    public int deletePetCamera(Long cam_id,Long pet_id);
}