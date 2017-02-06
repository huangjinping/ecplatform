package cn.cooperlink.ecplatform.petstore.pet.web.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.cooperlink.ecplatform.petstore.pet.entity.Pet;
import cn.cooperlink.ecplatform.petstore.pet.service.PetService;
import cn.cooperlink.ecplatform.petstore.pet.web.form.PetForm;
import cn.cooperlink.framework.core.Constants;
import cn.cooperlink.framework.core.Return;
import cn.cooperlink.framework.core.util.PagerParamHandler;
import cn.cooperlink.util.StringUtil;

@Controller
@RequestMapping("/server/pet")
public class ServerPetController {
	protected final Log log = LogFactory.getLog(getClass());

	
	@Resource
    private PetService petService;
	
	//获取会员宠物列表
	@RequestMapping("/list")
    @ResponseBody
    public String getMyPet(@RequestParam Map<String, Object> paramMap) throws Exception {
//		Long memId = (Long)paramMap.get("memId");
		PagerParamHandler.convertFormat(paramMap);
        return petService.findByCondition2PagerString(paramMap);
    }
	
	//查看单个宠物
	@RequestMapping("/find")
    @ResponseBody
	public String findPet(@RequestParam("id") Long petId) throws Exception{
		Pet pet = petService.findPet(petId);
		return Return.bean2String(pet);
	}
	
	//编辑宠物
	@RequestMapping("/update")
    @ResponseBody
    public String update(@RequestBody PetForm form) throws Exception {

        if (form.getId() == null || form.getId() == 0) {
            // 宠物ID不能为空
            return Return.error(Constants.MSG_FORM_ARGUMENTS_ERROR);
        }
        //TODO
        MultipartFile myPhoto = form.getMyphoto();
        Pet pet = new Pet();
        pet.setId(form.getId());
        pet.setBirthday(form.getBirthday());
        pet.setCategory(form.getCategory());
        pet.setDescription(form.getDescription());
        pet.setGender(form.getGender());
        pet.setPhoto(form.getPhoto());
        pet.setNick_name(form.getNick_name());
        pet.setSmall_category(form.getSmall_category());
        pet.setCategory(form.getCategory());

        petService.updatePet(pet,myPhoto);
        return Return.success(Constants.MSG_UPDATE_SUCCESS);
    }
	
	//删除宠物
	@RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam("id") Long id, @RequestParam("memId") Long memId) throws Exception {
        // 输入验证
        if (id == null) {
            log.error("宠物Id是必填项！");
            return Return.error(Constants.MSG_FORM_ARGUMENTS_ERROR);
        }
        if(!petService.deletePet(memId, id)){
            return Return.error(Constants.MSG_PET_DELETE_ERROR);
        }
        return Return.success(Constants.MSG_DELETE_SUCCESS);
    }
	
	//添加宠物
	@RequestMapping("/add")
    @ResponseBody
    public String add(PetForm form) throws Exception {

        if (StringUtil.isEmpty(form.getNick_name()) || StringUtil.isEmpty(form.getCategory()) || form.getGender() == null) {
            // 宠物昵称，类型和性别不能为空
            return Return.error(Constants.MSG_FORM_ARGUMENTS_ERROR);
        }

        // 主账号会员Id
        Long memId = form.getMemId();
        
        MultipartFile myPhoto = form.getMyphoto();
        Pet pet = new Pet();
        pet.setBirthday(form.getBirthday());
        pet.setCategory(form.getCategory());
        pet.setDescription(form.getDescription());
        pet.setGender(form.getGender());
        pet.setNick_name(form.getNick_name());
        pet.setSmall_category(form.getSmall_category());
        pet.setCategory(form.getCategory());
//        MultipartFile AA = form.get
        petService.addPet(memId, pet, myPhoto);
        return Return.success(Constants.MSG_SAVE_SUCCESS);
    }
}
