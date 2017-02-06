package cn.cooperlink.ecplatform.petstore.pet.web.form;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class PetForm {

    private Long id;
    private String nick_name;
    private Date birthday;
    private Boolean gender;
    private String photo;
    private String category;
    private String small_category;
    private String description;
    private String status;
    private Long memId;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getMemId() {
		return memId;
	}

	public void setMemId(Long memId) {
		this.memId = memId;
	}

	public MultipartFile getMyphoto() {
		return myphoto;
	}

	public void setMyphoto(MultipartFile myphoto) {
		this.myphoto = myphoto;
	}

	
}
