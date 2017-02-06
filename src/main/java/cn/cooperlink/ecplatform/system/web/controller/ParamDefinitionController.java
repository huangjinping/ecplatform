/* **************************************************************
 *
 * 文件名称：ParamDefinitionController.java
 *
 * 包含类名：cn.cooperlink.ecplatform.system.web.controller.ParamDefinitionController
 * 创建日期：2014年8月17日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.system.web.controller;

import cn.cooperlink.ecplatform.system.entity.ParamDefinition;
import cn.cooperlink.ecplatform.system.service.ParamDefinitionService;
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
 * Controller 类
 *
 * 创建日期：2014年8月17日
 * 创建作者：潘云峰
 */
@Controller
@RequestMapping(value="/system/paramDefinition", method={RequestMethod.GET, RequestMethod.POST})
public class ParamDefinitionController {

    /** paramDefinitionService */
    @Resource
    private ParamDefinitionService paramDefinitionService;

    @RequestMapping
    public String index() throws Exception {
        return "system/paramDefinition";
    }

    @RequestMapping("/query")
    @ResponseBody
    public String query(@RequestParam Map<String, Object> paramMap) throws Exception {
        PagerParamHandler.convertFormat(paramMap);
        return paramDefinitionService.findByCondition2PagerString(paramMap);
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(ParamDefinition paramDefinition) throws Exception {
    	paramDefinition.setValidity(BaseEntity.VF_VALID);
        EntityUtil.setBaseProps4Save(paramDefinition);
        paramDefinitionService.save(paramDefinition);
        return Return.success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(ParamDefinition paramDefinition) throws Exception {
        if (paramDefinition.getId() <= 0) {
            return Return.failure();
        }
        paramDefinitionService.delete(paramDefinition);
        return Return.success();
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(ParamDefinition paramDefinition) throws Exception {
        if (paramDefinition.getId() <= 0) {
            return Return.failure();
        }
        EntityUtil.setBaseProps4Update(paramDefinition);
        paramDefinitionService.update(paramDefinition);
        return Return.success();
    }

}