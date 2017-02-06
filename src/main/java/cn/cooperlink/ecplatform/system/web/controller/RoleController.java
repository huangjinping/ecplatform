/* **************************************************************
 *
 * 文件名称：RoleController.java
 *
 * 包含类名：cn.cooperlink.ecplatform.system.web.controller.RoleController
 * 创建日期：2014年8月9日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.system.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cooperlink.ecplatform.security.AuthHelper;
import cn.cooperlink.ecplatform.system.FuncTreeUtil;
import cn.cooperlink.ecplatform.system.entity.Function;
import cn.cooperlink.ecplatform.system.entity.Role;
import cn.cooperlink.ecplatform.system.service.FunctionService;
import cn.cooperlink.ecplatform.system.service.RoleService;
import cn.cooperlink.ecplatform.tenant.persistence.result.AuthorizedFuncTree;
import cn.cooperlink.framework.core.Return;
import cn.cooperlink.framework.core.util.EUITreeUtil;
import cn.cooperlink.framework.core.util.EntityUtil;
import cn.cooperlink.framework.core.util.PagerParamHandler;

/**
 * Controller 类
 *
 * 创建日期：2014年8月9日
 * 创建作者：潘云峰
 */
@Controller
@RequestMapping(value="/system/role", method={RequestMethod.GET, RequestMethod.POST})
public class RoleController {

    /** roleService */
    @Resource
    private RoleService roleService;
    
    @Resource
    private FunctionService functionService;

    @RequestMapping
    public String index() throws Exception {
        return "system/role";
    }

    @RequestMapping("/query")
    @ResponseBody
    public String query(@RequestParam Map<String, Object> paramMap) throws Exception {
        PagerParamHandler.convertFormat(paramMap);
        paramMap.put("tenantId", AuthHelper.getTenantId());
        return roleService.findByCondition2PagerString(paramMap);
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(Role role, String funcIds) throws Exception {
        EntityUtil.setBaseProps4Save(role);
        role.setOrgId(AuthHelper.getOrgId());
        role.setTenantId(AuthHelper.getTenantId());
        roleService.saveRole(role, funcIds);
        return Return.success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Role role) throws Exception {
        if (role.getId() < 1) {
            return Return.failure();
        }
        role.setOrgId(AuthHelper.getOrgId());
        role.setTenantId(AuthHelper.getTenantId());
        role = roleService.getSingle(role);
        if (role == null) {
        	return Return.failure("CP-000017");
        }
        roleService.deleteRole(role);
        return Return.success();
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(Role role, String funcIds) throws Exception {
        if (role.getId() <= 0) {
            return Return.failure();
        }
        EntityUtil.setBaseProps4Update(role);
        role.setOrgId(AuthHelper.getOrgId());
        role.setTenantId(AuthHelper.getTenantId());
        roleService.updateRole(role, funcIds);
        return Return.success();
    }
    
    /**
     * 获取某角色功能树
     *
     * @param roleId
     * @return
     */
    @RequestMapping("/getFuncTreeByRole")
    @ResponseBody
    public String getFuncTreeByRole(Long roleId) {
    	if (roleId < 0) {
    		return "[]";
    	}
    	List<AuthorizedFuncTree> list = roleService
    			.findFuncsByRole(AuthHelper
    			.getTenantId(), roleId);
    	if (list == null || list.size() == 0) {
    		return "[]";
    	}
	    List<Function> funcList = functionService.findAllCatalog();
	    return EUITreeUtil.buildTreeJson(FuncTreeUtil
	    		.buildFuncTreeList(list, funcList));
    }

	/**
     * 查询用户
     *
     * @return
     */
    @RequestMapping("/queryAllRoleByUser")
    @ResponseBody
    public String queryAllRoleByUser(Long userId) {
    	Map<String, Long> paramMap = new HashMap<String, Long>();
    	paramMap.put("tenantId", AuthHelper.getTenantId());
    	paramMap.put("userId", userId);
    	return Return.list2String(roleService
    			.queryAllRoleByUser(paramMap));
    }
    
    /**
     * 保存
     *
     * @param userId
     * @param ids
     * @return
     */    
    @RequestMapping("/saveUserRole")
    @ResponseBody
    public String saveUserRole(Long userId, String ids) throws Exception {
    	if (userId == null || userId < 1) {
    		return Return.failure("CP-000010.1", "员工");
    	}
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("tenantId", AuthHelper.getTenantId());
		map.put("userId", userId);
    	if (roleService.checkUser(map) < 1) {
    		return Return.failure("CP-000017");
    	}
    	roleService.saveUserRole(userId, ids);
    	return Return.success();
    }

}