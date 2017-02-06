/* 
 * Project Name：移动电商平台
 * File Name：DicItemController.java 
 * Package Name：cn.cooperlink.ecplatform.system.web.controller.DicItemController
 * Date：2014-08-24 10:32:28
 * Copyright (c) 2014, cooperlink.cn All Rights Reserved.
 * 北京酷博灵科信息技术有限公司
 */
package cn.cooperlink.ecplatform.system.web.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cooperlink.ecplatform.system.entity.DicItem;
import cn.cooperlink.ecplatform.system.service.DicItemService;
import cn.cooperlink.framework.core.BaseEntity;
import cn.cooperlink.framework.core.Return;
import cn.cooperlink.framework.core.util.EUITreeUtil;
import cn.cooperlink.framework.core.util.EntityUtil;
import cn.cooperlink.framework.core.validator.ValidResult;
import cn.cooperlink.util.StringUtil;

/**
 * @Title：DicItemController
 * @Description：Controller 类
 * @Package cn.cooperlink.ecplatform.system.web.controller
 * @ClassName DicItemController
 * @author 潘云峰
 * @date 2014-08-24 10:32:28
 * @version 
 */
@Controller
@RequestMapping(value="/system/dicItem", method={RequestMethod.GET, RequestMethod.POST})
public class DicItemController {

    /**
     * @Fields name: dicItemService
     */
    @Resource
    private DicItemService dicItemService;

    @RequestMapping
    public String index() throws Exception {
        return "system/dicItem";
    }

    @RequestMapping("/query")
    @ResponseBody
    public String query(@RequestParam Map<String, Object> paramMap)
    		throws Exception {
    	if (paramMap.get("dirId") == null) {
    		return EUITreeUtil.getEmptyTreeJson();
    	}
    	List<DicItem> list = dicItemService.findByCondition(paramMap);
    	if (StringUtil.isNotBlank((String) paramMap.get("displayName"))) {
        	for (DicItem di : list) {
        		if (hasParent(di, list)) {
        			continue;
        		}
				di.setParentId(EUITreeUtil.TREE_ROOT_ID);
        	}
    	}
    	return EUITreeUtil.buildTreeJson(list);
    }
    
    private boolean hasParent(DicItem di, List<DicItem> list) {
    	for (DicItem d : list) {
    		if (di.getParentId() == d.getId()) {
    			return true;
    		}
    	}
    	return false;
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(DicItem dicItem) throws Exception {
    	ValidResult vr = validity(dicItem, false);
    	if (!vr.isSuccess()) {
    		return Return.failure(vr.getMsgCode(), vr.getArguments());
    	}
        EntityUtil.setBaseProps4Save(dicItem);
        dicItem.setValidity(BaseEntity.VF_VALID);
        dicItemService.saveDicItem(dicItem);
        return Return.success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(DicItem dicItem) throws Exception {
        if (dicItem.getId() <= 0) {
            return Return.failure();
        }
        dicItemService.delete(dicItem);
        return Return.success();
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(DicItem dicItem, String oldNameFullpath) throws Exception {
    	ValidResult vr = validity(dicItem, true);
    	if (!vr.isSuccess()) {
    		return Return.failure(vr.getMsgCode(), vr.getArguments());
    	}
    	if (StringUtil.isBlank(oldNameFullpath)) {
    		return Return.failure("CP-000004", "原项目名称");
    	}
    	DicItem di = dicItemService.getSingle(dicItem.getId());
    	if (di == null) {
    		return Return.failure("CP-000017");
    	}
    	dicItem.setParentId(di.getParentId());
        EntityUtil.setBaseProps4Update(dicItem);
        dicItemService.updateDicItem(dicItem, di.getNameFullpath());
        return Return.success();
    }

	/**
	 * @Title: 表单验证
	 * @Description:
	 * @param dicItem
	 * @param isUpdate
	 * @return
	 */
	private ValidResult validity(DicItem dicItem, boolean isUpdate) {
    	if (isUpdate) {
    		if (dicItem.getId() == null || dicItem.getId() < 1) {
    			return new ValidResult(false, "CP-000016");
    		}
        	if (StringUtil.isBlank(dicItem.getIdFullpath())) {
        		return new ValidResult(false, "CP-000004", "ID路径");
        	}
    	}
    	if (StringUtil.isBlank(dicItem.getNameFullpath())) {
    		return new ValidResult(false, "CP-000004", "名称路径");
    	}
    	if (StringUtil.isBlank(dicItem.getDisplayName())) {
    		return new ValidResult(false, "CP-000004", "显示名称");
    	}
    	if (StringUtil.isBlank(dicItem.getItemValue())) {
    		return new ValidResult(false, "CP-000004", "键值");
    	}
    	return ValidResult.SUCCESS;
	}

}