/* **************************************************************
 *
 * 文件名称：MobileSubMemberController.java
 *
 * 包含类名：cn.cooperlink.ecplatform.member.mobile.controller.MobileSubMemberController
 * 创建日期：2014.08.07
 * 创建作者：dalvik
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.member.mobile.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cooperlink.ecplatform.member.entity.Member;
import cn.cooperlink.ecplatform.member.entity.SubMember;
import cn.cooperlink.ecplatform.member.service.MemberService;
import cn.cooperlink.ecplatform.member.service.SubMemberService;
import cn.cooperlink.ecplatform.security.mobile.BaseMobileAuthentication;
import cn.cooperlink.ecplatform.security.mobile.MobileAuthHelper;
import cn.cooperlink.ecplatform.security.mobile.MobileAuthentication;
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
@RequestMapping("/mobile/submember")
public class MobileSubMemberController extends BaseController {

    protected final Log log = LogFactory.getLog(getClass());

    @Resource
    private SubMemberService subMemberService;
    
    @Resource
    private MemberService memberService;

    /**
     * 
     * @Title: add
     * @Description: 添加子账号
     * @param mobile
     * @param passwd
     * @return
     * @throws Exception
     */
    @RequestMapping("/add")
    @ResponseBody
    public String add(String mobile, String passwd) throws Exception {
        // 检查是否输入手机号和密码
        if (StringUtil.isEmpty(mobile) || StringUtil.isEmpty(passwd)) {
            log.error("手机号和密码为必填项！");
            return Return.error(Constants.MSG_FORM_ARGUMENTS_ERROR);
        }
        if (passwd.length() < 6) {
            return Return.error(Constants.MSG_PASSWD_CHANGE_LENGTH_ERROR);
        }
        
        // 检查手机号码唯一性
        Member m = memberService.findByMobile(mobile);
        SubMember sm = subMemberService.findByMobile(mobile);
        if(m!=null || sm!= null){
            return Return.error(Constants.MSG_MOBILE_EXISTS_ERROR);
        }

        BaseMobileAuthentication bma = MobileAuthHelper.getAuthInfo();
        MobileAuthentication auth = null;

        // 子账号无法添加子账号
        if (bma.isSubMember()) {
            return Return.error(Constants.MSG_SUBMEMBER_NOCHILD_ERROR);
        }
        auth = (MobileAuthentication) bma;
        Long mem_id = auth.getMem_id();

        Map<String, Long> map = new HashMap<String, Long>();
        map.put("p_mem_id", mem_id);
        Long cnt = subMemberService.count(map);
        if (cnt >= 5) {
            return Return.error(Constants.MSG_SUBMEMBER_NOCHILD_ERROR);
        }

        SubMember subMember = new SubMember();
        subMember.setMobile(mobile);
        subMember.setPasswd(Md5.encodeByMd5(passwd));
        subMember.setP_mem_id(mem_id);
        subMemberService.addSubMember(subMember);
        return Return.success(Constants.MSG_SAVE_SUCCESS);
    }

    @RequestMapping("/update")
    @ResponseBody
    /**
     * 
     * @Title: update
     * @Description: 更新子账号信息
     * @param mem
     * @return
     * @throws Exception
     */
    public String update(Long id, String mobile, String passwd) throws Exception {
        // 输入验证
        if (id == null || id == 0 && StringUtil.isEmpty(mobile) || StringUtil.isEmpty(passwd)) {
            log.error("子账号Id,手机号码和密码是必填项！");
            return Return.error(Constants.MSG_FORM_ARGUMENTS_ERROR);
        }

        if (passwd.length() < 6) {
            // 新密码长度必须大于等于6位
            return Return.error(Constants.MSG_PASSWD_CHANGE_LENGTH_ERROR);
        }

        // 检查手机号码唯一性
        Member m = memberService.findByMobile(mobile);
        SubMember sm = subMemberService.findByMobile(mobile);
        if(m!=null){
            return Return.error(Constants.MSG_MOBILE_EXISTS_ERROR);
        }else if(sm!=null){
            if(sm.getId() != id){
                return Return.error(Constants.MSG_MOBILE_EXISTS_ERROR);
            }
        }

        // 更新子账号信息
        SubMember subMember = new SubMember();
        subMember.setId(id);
        subMember.setMobile(mobile);
        subMember.setPasswd(passwd);
        subMemberService.updateSubMember(subMember);

        return Return.success(Constants.MSG_UPDATE_SUCCESS);
    }

    @RequestMapping("/delete")
    @ResponseBody
    /**
     * 
     * @Title: delete
     * @Description:删除子账号
     * @param id
     * @return
     * @throws Exception
     */
    public String delete(@RequestParam("id") Long id) throws Exception {
        // 输入验证
        if (id == null) {
            log.error("用户Id是必填项！");
            return Return.error(Constants.MSG_FORM_ARGUMENTS_ERROR);
        }

        subMemberService.delete(id);
        return Return.success(Constants.MSG_DELETE_SUCCESS);
    }
}
