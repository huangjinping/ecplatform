/* **************************************************************
 *
 * 文件名称：CameraService.java
 *
 * 包含类名：cn.cooperlink.ecplatform.petstore.camera.service.CameraService
 * 创建日期：2014.08.07
 * 创建作者：dalvik
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.petstore.camera.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cooperlink.ecplatform.petstore.camera.entity.Camera;
import cn.cooperlink.ecplatform.petstore.camera.entity.PetCamera;
import cn.cooperlink.ecplatform.petstore.camera.persistence.mapper.CameraMapper;
import cn.cooperlink.ecplatform.petstore.camera.persistence.mapper.PetCameraMapper;
import cn.cooperlink.framework.core.BaseService4Mapper;

/**
 * CameraService 类
 * 
 * 创建日期：2014.08.07 创建作者：dalvik
 */
@Service
@Transactional
public class CameraService extends BaseService4Mapper {

    @Resource
    private CameraMapper cameraMapper;

    @Resource
    private PetCameraMapper petCameraMapper;

    @SuppressWarnings("unchecked")
    @Override
    public CameraMapper getMapper() {
        return cameraMapper;
    }

    /**
     * 
     * @Title: getMyCamera
     * @Description: 获取我的摄像头列表
     * @param mem_id
     * @return
     */
    public List<Camera> getMyCamera(Long mem_id) {
        return petCameraMapper.getMyCamera(mem_id);
    }

    /**
     * 添加摄像头
     * 
     * @Title: addCamera
     * @Description:
     * @param camera
     * @throws Exception
     */
    public void addCamera(Camera camera) throws Exception {
        Date date = new Date();
        camera.setCreateTime(date);
        camera.setUpdateTime(date);
        cameraMapper.save(camera);
    }

    /**
     * 
     * @Title: addPetCamera
     * @Description: 关联摄像头
     * @param cam_id
     * @param pet_id
     * @return
     * @throws Exception
     */
    public boolean addPetCamera(Long cam_id, Long pet_id) throws Exception {
        // 查询宠物是否已经有摄像头关联
        List<PetCamera> list = petCameraMapper.getPetCameraByPetId(pet_id);
        if (list != null && !list.isEmpty()) {
            return false;
        }
        PetCamera pc = new PetCamera();
        pc.setCam_id(cam_id);
        pc.setPet_id(pet_id);
        petCameraMapper.save(pc);
        return true;
    }
    
    /**
     * 
     * @Title: deletePetCamera
     * @Description: 根据摄像头ID和宠物ID删除摄像头关联信息
     * @param cam_id
     * @param pet_id
     * @return
     */
    public boolean deletePetCamera(Long cam_id,Long pet_id){
        petCameraMapper.deletePetCamera(cam_id, pet_id);
        return true;
    }

    /**
     * 
     * @Title: updateCamera
     * @Description:更新摄像头信息
     * @param camera
     * @throws Exception
     */
    public void updateCamera(Camera camera) throws Exception {
        camera.setUpdateTime(new Date());
        cameraMapper.update(camera);
    }

    /**
     * 
     * @Title: disableCamera
     * @Description: 禁用摄像头
     * @param id
     * @throws Exception
     */
    public void disableCamera(Long id) throws Exception {
        Camera camera = new Camera();
        camera.setEnable(false);
        camera.setUpdateTime(new Date());
        cameraMapper.update(camera);
    }

    /**
     * 
     * @Title: deleteCamera
     * @Description: 删除摄像头
     * @param id
     * @throws Exception
     */
    public boolean deleteCamera(Long id) throws Exception {
        // 查询摄像头是否已经有宠物关联
        List<PetCamera> list = petCameraMapper.getPetCameraByCamId(id);
        if (list != null && !list.isEmpty()) {
            return false;
        }
        cameraMapper.delete(id);
        return true;
    }
    
    /**
     * 
     * @Title: getCamera
     * @Description: 获取摄像头列表
     * @throws Exception
     */
    public List<Camera> getCamera() throws Exception {
    	List<Camera> list = new ArrayList<Camera>();
        list = cameraMapper.getCamera();
        return list;
    }
}