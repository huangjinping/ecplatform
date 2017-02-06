/* 
 * Project Name：移动电商平台
 * File Name：DicDefinitionController.java 
 * Package Name：cn.cooperlink.ecplatform.system.web.controller.DicDefinitionController
 * Date：2014-08-24 10:30:59
 * Copyright (c) 2014, cooperlink.cn All Rights Reserved.
 * 北京酷博灵科信息技术有限公司
 */
package cn.cooperlink.ecplatform.system.web.controller;

import cn.cooperlink.ecplatform.security.AuthHelper;
import cn.cooperlink.ecplatform.system.entity.DicDefinition;
import cn.cooperlink.ecplatform.system.service.DicDefinitionService;
import cn.cooperlink.framework.core.BaseEntity;
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
 * @Title：DicDefinitionController
 * @Description：Controller 类
 * @Package cn.cooperlink.ecplatform.system.web.controller
 * @ClassName DicDefinitionController
 * @author 潘云峰
 * @date 2014-08-24 10:30:59
 * @version 
 */
@Controller
@RequestMapping(value="/system/dicDefinition", method={RequestMethod.GET, RequestMethod.POST})
public class DicDefinitionController {

    /**
     * @Fields name: dicDefinitionService
     */
    @Resource
    private DicDefinitionService dicDefinitionService;

    @RequestMapping
    public String index() throws Exception {
        return "system/dicDefinition";
    }

    @RequestMapping("/query")
    @ResponseBody
    public String query(@RequestParam Map<String, Object> paramMap) throws Exception {
        PagerParamHandler.convertFormat(paramMap);
        return dicDefinitionService.findByCondition2PagerString(paramMap);
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(DicDefinition dicDefinition) throws Exception {
        EntityUtil.setBaseProps4Save(dicDefinition);
        dicDefinition.setValidity(BaseEntity.VF_VALID);
        dicDefinitionService.save(dicDefinition);
        return Return.success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(DicDefinition dicDefinition) throws Exception {
        if (dicDefinition.getId() <= 0) {
            return Return.failure();
        }
        dicDefinitionService.delete(dicDefinition);
        return Return.success();
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(DicDefinition dicDefinition) throws Exception {
        if (dicDefinition.getId() <= 0) {
            return Return.failure();
        }
        EntityUtil.setBaseProps4Update(dicDefinition);
        dicDefinitionService.update(dicDefinition);
        return Return.success();
    }
    
    @RequestMapping("/queryDic")
    @ResponseBody
    public String queryDic(@RequestParam Map<String, Object> paramMap) throws Exception {
        PagerParamHandler.convertFormat(paramMap);
        // 超级管理员处理，目前是写死的代码，可扩展为自动化
        if (AuthHelper.isSuperAdmin()) {
        	paramMap.put("scope", 1);
        } else {
        	paramMap.put("scope", 2);
        }
        return dicDefinitionService.findByCondition2PagerString(paramMap);
    }

}