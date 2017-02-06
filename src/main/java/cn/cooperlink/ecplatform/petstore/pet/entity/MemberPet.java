/** 
 * Project Name：ecplatform 
 * File Name：MemberPet.java 
 * Package Name：cn.cooperlink.ecplatform.petstore.pet.entity 
 * Date：2014年8月18日 下午3:56:08 
 * Copyright (c) 2014, cooperlink.cn All Rights Reserved. 
 * 北京酷博灵科信息技术有限公司
 */
package cn.cooperlink.ecplatform.petstore.pet.entity;

/**
 * @Title：
 * @Description：会员宠物关系表
 * @Package： cn.cooperlink.ecplatform.petstore.pet.entity
 * @ClassName： MemberPet
 * @Author： dalvikchen
 * @Date： 2014年8月18日 下午3:56:08
 * @version：
 */
public class MemberPet {

    /** id */
    private Long id;
    private Long mem_id;
    private Long pet_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMem_id() {
        return mem_id;
    }

    public void setMem_id(Long mem_id) {
        this.mem_id = mem_id;
    }

    public Long getPet_id() {
        return pet_id;
    }

    public void setPet_id(Long pet_id) {
        this.pet_id = pet_id;
    }
}
