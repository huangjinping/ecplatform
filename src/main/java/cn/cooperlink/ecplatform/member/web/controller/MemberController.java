/* **************************************************************
 *
 * 文件名称：ConsoleLoginController.java
 *
 * 包含类名：cn.cooperlink.myPet.web.controller.ConsoleLoginController
 * 创建日期：2014.08.07
 * 创建作者：dalvik
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.member.web.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cooperlink.ecplatform.member.entity.Member;
import cn.cooperlink.ecplatform.member.entity.SubMember;
import cn.cooperlink.ecplatform.member.mobile.form.MemberEditForm;
import cn.cooperlink.ecplatform.member.service.MemberService;
import cn.cooperlink.ecplatform.member.service.SubMemberService;
import cn.cooperlink.framework.core.BaseController;
import cn.cooperlink.framework.core.Constants;
import cn.cooperlink.framework.core.Return;
import cn.cooperlink.framework.core.util.EntityUtil;
import cn.cooperlink.framework.core.util.PagerParamHandler;
import cn.cooperlink.util.Md5;
import cn.cooperlink.util.StringUtil;

/**
 * Controller 类
 * 
 * 创建日期：2014.08.07 创建作者：dalvik
 */
@Controller
@RequestMapping("/member")
public class MemberController extends BaseController {

    protected final Log log = LogFactory.getLog(getClass());

    @Resource
    private MemberService memberService;

    @Resource
    private SubMemberService subMemberService;

    @RequestMapping("/list")
    public String index() {
        return "/member/list";
    }

    @RequestMapping("/page/add")
    public String detailList() {
        return "/member/detail";
    }
    
    @RequestMapping("/page/update")
    public String editDetailList(Long id,Model model) throws Exception{
        Member mem = memberService.getSingle(id);
        model.addAttribute("member", mem);
        return "/member/detail";
    }

    @RequestMapping("/get")
    @ResponseBody
    public String getMemberList(@RequestParam Map<String, Object> paramMap) throws Exception {
        PagerParamHandler.convertFormat(paramMap);
        return memberService.findByCondition2PagerString(paramMap);
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add(MemberEditForm member) throws Exception {
        // 输入验证
        if (StringUtil.isEmpty(member.getName()) || StringUtil.isEmpty(member.getMobile())) {
            log.error("用户姓名和手机号码是必填项！");
            return Return.error(Constants.MSG_FORM_ARGUMENTS_ERROR);
        }

        // 检查手机号码唯一性
        Member m = memberService.findByMobile(member.getMobile());
        SubMember sm = subMemberService.findByMobile(member.getMobile());
        if (m != null || sm != null) {
            return Return.error(Constants.MSG_MOBILE_EXISTS_ERROR);
        }

        Member mem = new Member();
        mem.setAddress(member.getAddress());
        mem.setBirthday(member.getBirthday());
        mem.setEmail(member.getEmail());
        mem.setEnglish_name(member.getEnglish_name());
        mem.setGender(member.getGender() == null ? true : member.getGender());
        mem.setId_card(member.getId_card());
        mem.setJob(member.getJob());
        mem.setMobile(member.getMobile());
        mem.setName(member.getName());
        mem.setNick_name(member.getNick_name());
        mem.setOrigin_place(member.getOrigin_place());
        mem.setPhone(member.getPhone());
        mem.setBalance(member.getBalance());
        mem.setPasswd(Md5.encodeByMd5("123456"));
        EntityUtil.setBaseProps4Save(mem);
        memberService.save(mem);

        return Return.bean2String(mem);
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(Member member) throws Exception {
        // 输入验证
        if (member.getId() == null || StringUtil.isEmpty(member.getName()) || StringUtil.isEmpty(member.getMobile())) {
            log.error("用户Id,用户姓名和手机号码是必填项！");
            return Return.error(Constants.MSG_FORM_ARGUMENTS_ERROR);
        }

        // 检查手机号码唯一性
        Member m = memberService.findByMobile(member.getMobile());
        SubMember sm = subMemberService.findByMobile(member.getMobile());
        if (m != null) {
            if (m.getId() != member.getId()) {
                return Return.error(Constants.MSG_MOBILE_EXISTS_ERROR);
            }
        } else if (sm != null) {
            return Return.error(Constants.MSG_MOBILE_EXISTS_ERROR);
        }

        Member mem = new Member();
        mem.setId(member.getId());
        mem.setAddress(member.getAddress());
        mem.setBirthday(member.getBirthday());
        mem.setEmail(member.getEmail());
        mem.setEnglish_name(member.getEnglish_name());
        mem.setGender(member.getGender() == null ? true : member.getGender());
        mem.setId_card(member.getId_card());
        mem.setJob(member.getJob());
        mem.setMobile(member.getMobile());
        mem.setName(member.getName());
        mem.setNick_name(member.getNick_name());
        mem.setOrigin_place(member.getOrigin_place());
        mem.setPhone(member.getPhone());
        mem.setBalance(member.getBalance());
        EntityUtil.setBaseProps4Update(member);
        memberService.update(member);
        return Return.success(Constants.MSG_UPDATE_SUCCESS);
    }

    @RequestMapping("/disable")
    @ResponseBody
    public String disable(@RequestParam("id") Long id) throws Exception {
        // 输入验证
        if (id == null) {
            log.error("用户Id是必填项！");
            return Return.error(Constants.MSG_FORM_ARGUMENTS_ERROR);
        }

        Member member = new Member();
        member.setId(id);
        member.setEnable(false);
        EntityUtil.setBaseProps4Update(member);
        memberService.updateMember(member);
        return Return.success(Constants.MSG_UPDATE_SUCCESS);
    }

    @RequestMapping("/active")
    @ResponseBody
    public String active(@RequestParam("id") Long id) throws Exception {
        // 输入验证
        if (id == null) {
            log.error("用户Id是必填项！");
            return Return.error(Constants.MSG_FORM_ARGUMENTS_ERROR);
        }

        Member member = new Member();
        member.setId(id);
        member.setEnable(true);
        EntityUtil.setBaseProps4Update(member);
        memberService.updateMember(member);
        return Return.success(Constants.MSG_UPDATE_SUCCESS);
    }
    
    @RequestMapping("/reset")
    @ResponseBody
    public String reset(@RequestParam("id") Long id) throws Exception {
        // 输入验证
        if (id == null) {
            log.error("用户Id是必填项！");
            return Return.error(Constants.MSG_FORM_ARGUMENTS_ERROR);
        }

        Member member = new Member();
        member.setId(id);
        member.setPasswd(Md5.encodeByMd5("123456"));
        EntityUtil.setBaseProps4Update(member);
        memberService.updateMember(member);
        return Return.success(Constants.MSG_UPDATE_SUCCESS);
    }

    @RequestMapping("/detail")
    @ResponseBody
    public String detail(@RequestParam("id") Long id) {
        // 输入验证
        if (id == null) {
            log.error("用户Id是必填项！");
            return Return.error(Constants.MSG_FORM_ARGUMENTS_ERROR);
        }

        Member member = memberService.loadMemberInfo(id);
        if (member != null) {
            return Return.bean2String(member);
        } else {
            return Return.error(Constants.MSG_MEMBER_RETRIEVE_ERROR);
        }
    }

    // @RequestMapping("/delete")
    // @ResponseBody
    // public String delete(@RequestParam("id") Long id) throws Exception {
    // // 输入验证
    // if (id == null) {
    // log.error("用户Id是必填项！");
    // return Return.error(Constants.MSG_FORM_ARGUMENTS_ERROR);
    // }
    // memberService.delete(id);
    // return Return.success(Constants.MSG_DELETE_SUCCESS);
    // }
}
