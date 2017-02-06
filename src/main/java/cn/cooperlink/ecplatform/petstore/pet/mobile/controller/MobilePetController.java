/* **************************************************************
 *
 * 文件名称：MobilePetController.java
 *
 * 包含类名：cn.cooperlink.ecplatform.petstore.pet.mobile.controller.MobilePetController
 * 创建日期：2014.08.07
 * 创建作者：dalvik
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.petstore.pet.mobile.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.cooperlink.ecplatform.petstore.pet.entity.Pet;
import cn.cooperlink.ecplatform.petstore.pet.mobile.form.PetEditForm;
import cn.cooperlink.ecplatform.petstore.pet.service.PetService;
import cn.cooperlink.ecplatform.security.mobile.BaseMobileAuthentication;
import cn.cooperlink.ecplatform.security.mobile.MobileAuthHelper;
import cn.cooperlink.ecplatform.security.mobile.MobileAuthentication;
import cn.cooperlink.ecplatform.security.mobile.MobileAuthenticationSub;
import cn.cooperlink.framework.core.BaseController;
import cn.cooperlink.framework.core.Constants;
import cn.cooperlink.framework.core.Return;
import cn.cooperlink.util.StringUtil;

/**
 * Controller 类
 * 
 * 创建日期：2014.08.07 创建作者：dalvik
 */
@Controller
@RequestMapping("/mobile/pet")
public class MobilePetController extends BaseController {

    protected final Log log = LogFactory.getLog(getClass());

    @Resource
    private PetService petService;

    @RequestMapping("/list")
    @ResponseBody
    public String getMyPet() {
        BaseMobileAuthentication bma = MobileAuthHelper.getAuthInfo();
        MobileAuthenticationSub subAuth = null;
        MobileAuthentication auth = null;
        // 主账号会员Id
        Long mem_id;
        if (bma.isSubMember()) {
            subAuth = (MobileAuthenticationSub) bma;
            mem_id = subAuth.getParent_member().getMem_id();
        } else {
            auth = (MobileAuthentication) bma;
            mem_id = auth.getMem_id();
        }

        List<Pet> list = petService.getMyPet(mem_id);
        return Return.list2String(list);
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add(PetEditForm form) throws Exception {

        if (StringUtil.isEmpty(form.getNick_name()) || StringUtil.isEmpty(form.getCategory()) || form.getGender() == null) {
            // 宠物昵称，类型和性别不能为空
            return Return.error(Constants.MSG_FORM_ARGUMENTS_ERROR);
        }

        BaseMobileAuthentication bma = MobileAuthHelper.getAuthInfo();
        MobileAuthenticationSub subAuth = null;
        MobileAuthentication auth = null;
        // 主账号会员Id
        Long mem_id;
        if (bma.isSubMember()) {
            subAuth = (MobileAuthenticationSub) bma;
            mem_id = subAuth.getParent_member().getMem_id();
        } else {
            auth = (MobileAuthentication) bma;
            mem_id = auth.getMem_id();
        }
        
        MultipartFile myPhoto = form.getMyphoto();
        Pet pet = new Pet();
        pet.setBirthday(form.getBirthday());
        pet.setCategory(form.getCategory());
        pet.setDescription(form.getDescription());
        pet.setGender(form.getGender());
        pet.setNick_name(form.getNick_name());
        pet.setSmall_category(form.getSmall_category());
        pet.setCategory(form.getCategory());

        petService.addPet(mem_id, pet, myPhoto);
        return Return.success(Constants.MSG_SAVE_SUCCESS);
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(PetEditForm form) throws Exception {

        if (form.getId() == null || form.getId() == 0) {
            // 宠物ID不能为空
            return Return.error(Constants.MSG_FORM_ARGUMENTS_ERROR);
        }
        MultipartFile myPhoto = form.getMyphoto();
        Pet pet = new Pet();
        pet.setId(form.getId());
        pet.setBirthday(form.getBirthday());
        pet.setCategory(form.getCategory());
        pet.setDescription(form.getDescription());
        pet.setGender(form.getGender());
        pet.setNick_name(form.getNick_name());
        pet.setPhoto(form.getPhoto());
        pet.setSmall_category(form.getSmall_category());
        pet.setCategory(form.getCategory());

        petService.updatePet(pet,myPhoto);
        return Return.success(Constants.MSG_UPDATE_SUCCESS);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam("id") Long id) throws Exception {
        // 输入验证
        if (id == null) {
            log.error("宠物Id是必填项！");
            return Return.error(Constants.MSG_FORM_ARGUMENTS_ERROR);
        }
        
        BaseMobileAuthentication bma = MobileAuthHelper.getAuthInfo();
        MobileAuthenticationSub subAuth = null;
        MobileAuthentication auth = null;
        // 主账号会员Id
        Long mem_id;
        if (bma.isSubMember()) {
            subAuth = (MobileAuthenticationSub) bma;
            mem_id = subAuth.getParent_member().getMem_id();
        } else {
            auth = (MobileAuthentication) bma;
            mem_id = auth.getMem_id();
        }

        if(!petService.deletePet(mem_id, id)){
            return Return.error(Constants.MSG_PET_DELETE_ERROR);
        }
        return Return.success(Constants.MSG_DELETE_SUCCESS);
    }
}
