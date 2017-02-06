/* **************************************************************
 *
 * 文件名称：MobileMemberController.java
 *
 * 包含类名：cn.cooperlink.ecplatform.member.mobile.controller.MobileMemberController
 * 创建日期：2014.08.07
 * 创建作者：dalvik
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.member.mobile.controller;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cooperlink.ecplatform.index.service.LoginService;
import cn.cooperlink.ecplatform.member.entity.Member;
import cn.cooperlink.ecplatform.member.entity.SubMember;
import cn.cooperlink.ecplatform.member.mobile.form.MemberEditForm;
import cn.cooperlink.ecplatform.member.mobile.form.PasswordChangeForm;
import cn.cooperlink.ecplatform.member.service.MemberService;
import cn.cooperlink.ecplatform.member.service.SubMemberService;
import cn.cooperlink.ecplatform.security.mobile.BaseMobileAuthentication;
import cn.cooperlink.ecplatform.security.mobile.MobileAuthHelper;
import cn.cooperlink.ecplatform.security.mobile.MobileAuthentication;
import cn.cooperlink.ecplatform.security.mobile.MobileAuthenticationSub;
import cn.cooperlink.framework.core.BaseController;
import cn.cooperlink.framework.core.Constants;
import cn.cooperlink.framework.core.Return;
import cn.cooperlink.util.Md5;
import cn.cooperlink.util.StringUtil;

/**
 * Controller 类
 * 
 * 创建日期：2014.08.07 创建作者：dalvik
 */
@Controller
@RequestMapping("/mobile/member")
public class MobileMemberController extends BaseController {

    protected final Log log = LogFactory.getLog(getClass());

    @Resource
    private MemberService memberService;
    
    @Resource
    private SubMemberService subMemberService;

    @Resource
    private LoginService loginService;

    @RequestMapping("/update")
    @ResponseBody
    /**
     * 
     * @Title: update
     * @Description: 更新会员信息
     * @param mem
     * @return
     * @throws Exception
     */
    public String update(MemberEditForm mem) throws Exception {
        // 输入验证
        if (StringUtil.isEmpty(mem.getMobile()) || StringUtil.isEmpty(mem.getName())) {
            log.error("用户Id,用户姓名和手机号码是必填项！");
            return Return.error(Constants.MSG_FORM_ARGUMENTS_ERROR);
        }
        
        BaseMobileAuthentication bma = MobileAuthHelper.getAuthInfo();
        MobileAuthenticationSub subAuth = null;
        MobileAuthentication auth = null;
        
        // 检查电话号码
        Member m = memberService.findByMobile(mem.getMobile());
        SubMember sm = subMemberService.findByMobile(mem.getMobile());
 
        if (bma.isSubMember()) {
            subAuth = (MobileAuthenticationSub) bma;

            // 如果主账号中存在
            if(m!=null){
                return Return.error(Constants.MSG_MOBILE_EXISTS_ERROR);
            }else if(sm!=null){
                // 当存在该电话号码的子账号和当前账号不一致时
                if(sm.getId() != subAuth.getMem_id()){
                    return Return.error(Constants.MSG_MOBILE_EXISTS_ERROR);
                }
            }
            
            // 更新子账号信息
            SubMember subMember = new SubMember();
            subMember.setId(subAuth.getMem_id());
            subMember.setGender(mem.getGender());
            subMember.setMobile(mem.getMobile());
            subMember.setName(mem.getName());
            subMemberService.updateSubMember(subMember);

        } else {
            auth = (MobileAuthentication) bma;

            // 如果主账号中存在
            if(m!=null){
                // 如果主账号中存在该电话号码并且与当前登录账号不一致
                if(m.getId() != auth.getMem_id()){
                    return Return.error(Constants.MSG_MOBILE_EXISTS_ERROR);
                }
            }else if(sm!=null){
                return Return.error(Constants.MSG_MOBILE_EXISTS_ERROR);
            }
            
            // 更新账号信息
            Member member = new Member();
            member.setId(auth.getMem_id());
            member.setAddress(mem.getAddress());
            member.setBirthday(mem.getBirthday());
            member.setEmail(mem.getEmail());
            member.setEnglish_name(mem.getEnglish_name());
            member.setGender(mem.getGender());
            member.setId_card(mem.getId_card());
            member.setJob(mem.getJob());
            member.setMobile(mem.getMobile());
            member.setName(mem.getName());
            member.setNick_name(mem.getNick_name());
            member.setOrigin_place(mem.getOrigin_place());
            member.setPhone(mem.getPhone());
            member.setQQ(mem.getQQ());
            memberService.updateMember(member);
        }

        return Return.success(Constants.MSG_UPDATE_SUCCESS);
    }

    @RequestMapping("/change")
    @ResponseBody
    /**
     * 
     * @Title: change
     * @Description: 变更密码
     * @param form
     * @return
     * @throws Exception
     */
    public String change(PasswordChangeForm form) throws Exception {
        if (StringUtil.isEmpty(form.getOldPassword()) || StringUtil.isEmpty(form.getNewPassword()) || StringUtil.isEmpty(form.getNewPasswordTwice())) {
            // 旧密码，新密码，第二次输入密码都必须提供
            return Return.error(Constants.MSG_FORM_ARGUMENTS_ERROR);
        }
        
        if (form.getNewPassword().length() < 6){
            // 新密码长度必须大于等于6位
            return Return.error(Constants.MSG_PASSWD_CHANGE_LENGTH_ERROR);
        }

        if (!form.getNewPassword().equalsIgnoreCase(form.getNewPasswordTwice())) {
            // 两次密码输入不一致
            return Return.error(Constants.MSG_PASSWD_CHANGE_NOTSAME_ERROR);
        }
        BaseMobileAuthentication bma = MobileAuthHelper.getAuthInfo();
        MobileAuthenticationSub subAuth = null;
        MobileAuthentication auth = null;
        if (bma.isSubMember()) {
            subAuth = (MobileAuthenticationSub) bma;
            BaseMobileAuthentication auth1 = loginService.loadMobileAuthInfo_SubMem(subAuth.getMobile(), Md5.encodeByMd5(form.getOldPassword()));
            if (auth1 != null) {
                // 新密码和旧密码不能相同
                if (form.getNewPassword() != form.getOldPassword()) {
                    SubMember subMember = new SubMember();
                    subMember.setId(subAuth.getMem_id());
                    subMember.setPasswd(Md5.encodeByMd5(form.getNewPassword()));

                    subMemberService.updateSubMember(subMember);
                    return Return.success(Constants.MSG_UPDATE_SUCCESS);
                } else {
                    return Return.error(Constants.MSG_PASSWD_CHANGE_SAME_ERROR);
                }
            } else {
                // 旧密码输入有误
                return Return.error(Constants.MSG_PASSWD_CHANGE_WRONG_ERROR);
            }
        } else {
            auth = (MobileAuthentication) bma;
            BaseMobileAuthentication auth2 = loginService.loadMobileAuthInfo(auth.getMobile(), Md5.encodeByMd5(form.getOldPassword()));
            if (auth2 != null) {
                if (form.getNewPassword() != form.getOldPassword()) {
                    Member member = new Member();
                    member.setId(auth.getMem_id());
                    member.setPasswd(Md5.encodeByMd5(form.getNewPassword()));

                    memberService.updateMember(member);
                    return Return.success(Constants.MSG_UPDATE_SUCCESS);
                } else {
                    return Return.error(Constants.MSG_PASSWD_CHANGE_SAME_ERROR);
                }
            } else {
                return Return.error(Constants.MSG_PASSWD_CHANGE_WRONG_ERROR);
            }
        }

    }

//    @RequestMapping("/delete")
//    @ResponseBody
//    /**
//     * 
//     * @Title: delete
//     * @Description:删除账号
//     * @param id
//     * @return
//     * @throws Exception
//     */
//    public String delete(@RequestParam("id") Long id) throws Exception {
//        // 输入验证
//        if (id == null) {
//            log.error("用户Id是必填项！");
//            return Return.error(Constants.MSG_FORM_ARGUMENTS_ERROR);
//        }
//        memberService.delete(id);
//        return Return.success(Constants.MSG_DELETE_SUCCESS);
//    }
}
