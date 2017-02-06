/* **************************************************************
 *
 * 文件名称：UserRoleController.java
 *
 * 包含类名：cn.cooperlink.ecplatform.system.web.controller.UserRoleController
 * 创建日期：2014年8月9日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.system.web.controller;

import cn.cooperlink.ecplatform.system.entity.UserRole;
import cn.cooperlink.ecplatform.system.service.UserRoleService;
import cn.cooperlink.framework.core.Return;
import cn.cooperlink.framework.core.util.EntityUtil;
import cn.cooperlink.framework.core.util.PagerParamHandler;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller 类
 *
 * 创建日期：2014年8月9日
 * 创建作者：潘云峰
 */
@Controller
@RequestMapping(value="/system/userRole", method={RequestMethod.GET, RequestMethod.POST})
public class UserRoleController {

    /** userRoleService */
    @Resource
    private UserRoleService userRoleService;

    @RequestMapping
    public String index() throws Exception {
        return "system/userRole";
    }

    @RequestMapping("/query")
    @ResponseBody
    public String query(@RequestParam Map<String, Object> paramMap) throws Exception {
        PagerParamHandler.convertFormat(paramMap);
        return userRoleService.findByCondition2PagerString(paramMap);
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(UserRole userRole) throws Exception {
        EntityUtil.setBaseProps4Save(userRole);
        userRoleService.save(userRole);
        return Return.success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(UserRole userRole) throws Exception {
        if (userRole.getId() <= 0) {
            return Return.failure();
        }
        userRoleService.delete(userRole);
        return Return.success();
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(UserRole userRole) throws Exception {
        if (userRole.getId() <= 0) {
            return Return.failure();
        }
        EntityUtil.setBaseProps4Update(userRole);
        userRoleService.update(userRole);
        return Return.success();
    }

}