/* **************************************************************
 *
 * 文件名称：RoleFuncController.java
 *
 * 包含类名：cn.cooperlink.ecplatform.system.web.controller.RoleFuncController
 * 创建日期：2014年8月9日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.system.web.controller;

import cn.cooperlink.ecplatform.system.entity.RoleFunc;
import cn.cooperlink.ecplatform.system.service.RoleFuncService;
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
@RequestMapping(value="/system/roleFunc", method={RequestMethod.GET, RequestMethod.POST})
public class RoleFuncController {

    /** roleFuncService */
    @Resource
    private RoleFuncService roleFuncService;

    @RequestMapping
    public String index() throws Exception {
        return "system/roleFunc";
    }

    @RequestMapping("/query")
    @ResponseBody
    public String query(@RequestParam Map<String, Object> paramMap) throws Exception {
        PagerParamHandler.convertFormat(paramMap);
        return roleFuncService.findByCondition2PagerString(paramMap);
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(RoleFunc roleFunc) throws Exception {
        EntityUtil.setBaseProps4Save(roleFunc);
        roleFuncService.save(roleFunc);
        return Return.success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(RoleFunc roleFunc) throws Exception {
        if (roleFunc.getId() <= 0) {
            return Return.failure();
        }
        roleFuncService.delete(roleFunc);
        return Return.success();
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(RoleFunc roleFunc) throws Exception {
        if (roleFunc.getId() <= 0) {
            return Return.failure();
        }
        EntityUtil.setBaseProps4Update(roleFunc);
        roleFuncService.update(roleFunc);
        return Return.success();
    }

}