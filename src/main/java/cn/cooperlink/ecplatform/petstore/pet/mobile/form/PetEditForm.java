/* **************************************************************
 *
 * 文件名称：PetEditForm.java
 *
 * 包含类名：cn.cooperlink.myPet.entity.PetEditForm
 * 创建日期：2014.08.15
 * 创建作者：dalvik
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.petstore.pet.mobile.form;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

/**
 * 手机端表单参数接收实体
 * 
 * 创建日期：2014.08.15 创建作者：dalvik
 */
public class PetEditForm {

    private Long id;
    private String nick_name;
    private Date birthday;
    private Boolean gender;
    private String photo;
    private String category;
    private String small_category;
    private String description;
    private MultipartFile myphoto;
    
    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

	public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSmall_category() {
        return small_category;
    }

    public void setSmall_category(String small_category) {
        this.small_category = small_category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public MultipartFile getMyphoto() {
		return myphoto;
	}

	public void setMyphoto(MultipartFile myphoto) {
		this.myphoto = myphoto;
	}
    
}
