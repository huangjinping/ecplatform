/** 
 * Project Name：ecplatform 
 * File Name：PetCamera.java 
 * Package Name：cn.cooperlink.ecplatform.petstore.camera.entity 
 * Date：2014年8月18日 下午3:21:06 
 * Copyright (c) 2014, cooperlink.cn All Rights Reserved. 
 * 北京酷博灵科信息技术有限公司
 */
package cn.cooperlink.ecplatform.petstore.camera.entity;

/**
 * @Title：
 * @Description：宠物摄像头关系
 * @Package： cn.cooperlink.ecplatform.petstore.camera.entity
 * @ClassName： PetCamera
 * @Author： dalvikchen
 * @Date： 2014年8月18日 下午3:21:06
 * @version：
 */
public class PetCamera {
    /** id */
    private Long id;
    private Long pet_id;
    private Long cam_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPet_id() {
        return pet_id;
    }

    public void setPet_id(Long pet_id) {
        this.pet_id = pet_id;
    }

    public Long getCam_id() {
        return cam_id;
    }

    public void setCam_id(Long cam_id) {
        this.cam_id = cam_id;
    }

}
