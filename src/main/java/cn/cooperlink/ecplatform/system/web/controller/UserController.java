/* **************************************************************
 *
 * 文件名称：UserController.java
 *
 * 包含类名：cn.cooperlink.ecplatform.system.web.controller.UserController
 * 创建日期：2014年8月6日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.system.web.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cooperlink.ecplatform.common.service.CommonService;
import cn.cooperlink.ecplatform.security.AuthHelper;
import cn.cooperlink.ecplatform.system.Consts;
import cn.cooperlink.ecplatform.system.entity.User;
import cn.cooperlink.ecplatform.system.service.UserService;
import cn.cooperlink.framework.core.BaseEntity;
import cn.cooperlink.framework.core.Return;
import cn.cooperlink.framework.core.util.ConfigManager;
import cn.cooperlink.framework.core.util.EntityUtil;
import cn.cooperlink.framework.core.util.PagerParamHandler;
import cn.cooperlink.framework.core.validator.ValidResult;
import cn.cooperlink.util.Md5;
import cn.cooperlink.util.StringUtil;

/**
 * Controller 类
 *
 * 创建日期：2014年8月6日
 * 创建作者：潘云峰
 */
@Controller
@RequestMapping(value="/system/user", method={RequestMethod.GET, RequestMethod.POST})
public class UserController {

    /** userService */
    @Resource
    private UserService userService;
    
    @Resource
    private CommonService commonService;

    @RequestMapping
    public String index() throws Exception {
        return "system/user";
    }

    @RequestMapping("/query")
    @ResponseBody
    public String query(@RequestParam Map<String, Object> paramMap) 
    		throws Exception {
        PagerParamHandler.convertFormat(paramMap);
        if (paramMap.get("orgId") == null) {
        	paramMap.put("orgId", new Long(0));
        }
        paramMap.put("tenantId", AuthHelper.getTenantId());
        return userService.findByCondition2PagerString(paramMap);
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(User user) throws Exception {
        user.setTenantId(AuthHelper.getTenantId());
    	ValidResult vr = validity(user, false);
    	if (!vr.isSuccess()) {
    		return Return.failure(vr.getMsgCode(), vr.getArguments());
    	}
    	user.setUsername(StringUtil.null2Empty(
    			ConfigManager.getString("tenant_account_prefix_" 
    			+ AuthHelper.getTenantId())) 
    			+ user.getUsername());
    	user.setValidity(BaseEntity.VF_VALID);
        EntityUtil.setBaseProps4Save(user);
        user.setOrgId(AuthHelper.getOrgId());
        // 转换为配置信息
        user.setPassword(Md5.encodeByMd5("123456"));
        userService.saveUser(user);
        return Return.success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(User user) throws Exception {
        if (user.getId() <= 0) {
            return Return.failure();
        }
        user.setOrgId(AuthHelper.getOrgId());
        user.setTenantId(AuthHelper.getTenantId());
        userService.deleteUser(user);
        return Return.success();
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(User user) throws Exception {
    	ValidResult vr = validity(user, true);
    	if (!vr.isSuccess()) {
    		return Return.failure(vr.getMsgCode(), vr.getArguments());
    	}
    	user.setUsername(StringUtil.null2Empty(
    			ConfigManager.getString("tenant_account_prefix_" 
    			+ AuthHelper.getTenantId())) 
    			+ user.getUsername());
        EntityUtil.setBaseProps4Update(user);
        user.setOrgId(AuthHelper.getOrgId());
        userService.update(user);
        return Return.success();
    }
    
    /**
     * @Title: 重置
     * @Description:
     * @param oldPwd
     * @param newPwd
     * @param confirmNewPwd
     * @return
     */
    @RequestMapping("/updatePassword")
    @ResponseBody
    public String updatePassword(String oldPwd, String newPwd, 
    		String confirmNewPwd) throws Exception {
    	if (StringUtil.isBlank(oldPwd)) {
    		return Return.failure("CP-000010.1", "原密码");
    	}
    	if (StringUtil.isBlank(newPwd)) {
    		return Return.failure("CP-000004", "新密码");
    	}
    	if (StringUtil.isBlank(confirmNewPwd)) {
    		return Return.failure("CP-000004", "确认密码");
    	}
    	if (!newPwd.equals(confirmNewPwd)) {
    		return Return.failure("CP-000052");
    	}
    	User user = new User();
    	user.setId(AuthHelper.getUserId());
    	user.setPassword(Md5.encodeByMd5(oldPwd));
    	user.setTenantId(AuthHelper.getTenantId());
    	if (!userService.validateAccount(user)) {
    		return Return.failure("CP-000051");
    	}
    	user.setPassword(Md5.encodeByMd5(newPwd));
    	userService.updatePassword(user);
    	return Return.success();
    }
    
    /**
     * @Title: 重置密码
     * @Description:
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/resetPwd")
    @ResponseBody
    public String resetPwd(Long id) throws Exception {
    	if (id == null || id < 1) {
    		return Return.failure("CP-000016");
    	}
    	User user = new User();
    	user.setId(id);
    	user.setTenantId(AuthHelper.getTenantId());
    	user.setPassword(Md5.encodeByMd5("123456"));
    	userService.updatePassword(user);
    	return Return.success();
    }
    
    /**
     * 简单表单验证。
     *
     * @param org
     * @param isUpdate
     * @return
     */
    private ValidResult validity(User user, boolean isUpdate) throws Exception {
    	if (isUpdate) {
    		if (user.getId() == null || user.getId() < 1) {
    			return new ValidResult(false, "CP-000016");
    		}
    		User u = userService.getSingle(user.getId(), AuthHelper.getTenantId());
    		if (u == null) {
    			return new ValidResult(false, "CP-000017");
    		}
    		if (u.getUsername().endsWith(Consts.ADMIN_ACCOUNT_SUFFIX)) {
    			return new ValidResult(false, "CP-001002.1");
    		}
    		user.setTenantId(u.getTenantId());
    	} else {
    		if (user.getUsername().endsWith(Consts.ADMIN_ACCOUNT_SUFFIX)) {
    			return new ValidResult(false, "CP-001002.2");
    		}
    	}
    	if (user.getOrgId() == null || user.getOrgId() < 1) {
    		return new ValidResult(false, "CP-000010.2", "员工所属机构。");
    	}
    	if (StringUtil.isBlank(user.getUsername())) {
    		return new ValidResult(false, "CP-000004", "系统账号");
    	}
    	if (StringUtil.isBlank(user.getName())) {
    		return new ValidResult(false, "CP-000004", "员工姓名");
    	}
    	if (StringUtil.isBlank(user.getEmployeeNo())) {
    		return new ValidResult(false, "CP-000004", "员工编号");
    	}
    	if (userService.isEmpNoExisting(user)) {
    		return new ValidResult(false, "CP-000012", "员工编号");
    	}
    	if (commonService.isExisting("SYS_USER", "USERNAME", user.getId(), 
    			user.getUsername())) {
    		return new ValidResult(false, "CP-000012", "该账号");
    	}
    	return ValidResult.SUCCESS;
    }

}