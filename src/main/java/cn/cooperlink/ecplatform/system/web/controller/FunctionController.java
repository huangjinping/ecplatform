/* **************************************************************
 *
 * 文件名称：FunctionController.java
 *
 * 包含类名：cn.cooperlink.ecplatform.system.web.controller.FunctionController
 * 创建日期：2014年8月4日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.system.web.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cooperlink.ecplatform.common.service.CommonService;
import cn.cooperlink.ecplatform.system.entity.Function;
import cn.cooperlink.ecplatform.system.service.FunctionService;
import cn.cooperlink.framework.core.BaseEntity;
import cn.cooperlink.framework.core.Return;
import cn.cooperlink.framework.core.util.EUITreeUtil;
import cn.cooperlink.framework.core.util.EntityUtil;
import cn.cooperlink.framework.core.validator.ValidResult;
import cn.cooperlink.util.StringUtil;

/**
 * Controller 类
 *
 * 创建日期：2014年8月4日
 * 创建作者：潘云峰
 */
@Controller
@RequestMapping(value="/system/function", method={RequestMethod.GET, RequestMethod.POST})
public class FunctionController {

    /** functionService */
    @Resource
    private FunctionService functionService;
    
    @Resource
    private CommonService commonService;

    @RequestMapping
    public String index() throws Exception {
        return "/system/function";
    }

    @RequestMapping("/query")
    @ResponseBody
    public String query(@RequestParam Map<String, Object> paramMap) throws Exception {
        List<Function> funcList = functionService.findByCondition(paramMap);
        if (StringUtil.isNotBlank((String) paramMap.get("funcName"))) {
        	for (Function f : funcList) {
        		if (EUITreeUtil.hasParent(f, funcList)) {
        			continue;
        		}
				f.setParentId(EUITreeUtil.TREE_ROOT_ID);
        	}
        }
        return EUITreeUtil.buildTreeJson(funcList);
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(Function function) throws Exception {
    	ValidResult vr = validity(function, false);
    	if (!vr.isSuccess()) {
    		return Return.failure(vr.getMsgCode(), vr.getArguments());
    	}
        EntityUtil.setBaseProps4Save(function);
        function.setOrderField(commonService.nextSequence("DICTIONARY_FUNCTION", 
        		function.getParentId()));
        function.setValidity(BaseEntity.VF_VALID);
        functionService.saveFunction(function);
        return Return.success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Function function) throws Exception {
        if (function.getId() <= 0) {
            return Return.failure();
        }
        functionService.deleteFunction(function);
        return Return.success();
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(Function function, String oldNameFullpath) throws Exception {
    	ValidResult vr = validity(function, true);
    	if (!vr.isSuccess()) {
    		return Return.failure(vr.getMsgCode(), vr.getArguments());
    	}
    	if (StringUtil.isBlank(oldNameFullpath)) {
    		return Return.failure("CP-000004", "原功能名称");
    	}
    	Function fTemp = functionService.getSingle(function.getId());
    	if (fTemp == null) {
    		return Return.failure("CP-000017");
    	}
    	function.setParentId(fTemp.getParentId());
        EntityUtil.setBaseProps4Update(function);
        functionService.update(function, fTemp.getNameFullpath());
        return Return.success();
    }

    /**
     * @Title: 简单表单验证
     * @Description:
     * @param org
     * @param isUpdate
     * @return
     */
    private ValidResult validity(Function func, boolean isUpdate) {
    	if (isUpdate) {
    		if (func.getId() == null || func.getId() < 1) {
    			return new ValidResult(false, "CP-000016");
    		}
        	if (StringUtil.isBlank(func.getIdFullpath())) {
        		return new ValidResult(false, "CP-000004", "ID路径");
        	}
    	}
    	if (StringUtil.isBlank(func.getNameFullpath())) {
    		return new ValidResult(false, "CP-000004", "名称路径");
    	}
    	if (StringUtil.isBlank(func.getFuncName())) {
    		return new ValidResult(false, "CP-000004", "功能名称");
    	}
    	if (StringUtil.isBlank(func.getFuncCode())) {
    		return new ValidResult(false, "CP-000004", "功能编码");
    	}
    	return ValidResult.SUCCESS;
    }
    
}