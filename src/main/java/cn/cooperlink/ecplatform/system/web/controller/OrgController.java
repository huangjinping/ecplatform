/* **************************************************************
 *
 * 文件名称：OrgController.java
 *
 * 包含类名：cn.cooperlink.ecplatform.system.web.controller.OrgController
 * 创建日期：2014年8月6日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.system.web.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cooperlink.ecplatform.security.AuthHelper;
import cn.cooperlink.ecplatform.system.entity.Org;
import cn.cooperlink.ecplatform.system.service.OrgService;
import cn.cooperlink.framework.core.BaseEntity;
import cn.cooperlink.framework.core.Return;
import cn.cooperlink.framework.core.util.ConfigManager;
import cn.cooperlink.framework.core.util.EUITreeUtil;
import cn.cooperlink.framework.core.util.EntityUtil;
import cn.cooperlink.framework.core.util.PagerParamHandler;
import cn.cooperlink.framework.core.validator.ValidResult;
import cn.cooperlink.util.StringUtil;

/**
 * Controller 类
 *
 * 创建日期：2014年8月6日
 * 创建作者：潘云峰
 */
@Controller
@RequestMapping(value="/system/org", method={RequestMethod.GET, RequestMethod.POST})
public class OrgController {

    /** orgService */
    @Resource
    private OrgService orgService;

    @RequestMapping
    public String index(ModelMap model) throws Exception {
    	model.put("accountPrefix", ConfigManager.getString("tenant_account_prefix_" 
    			+ AuthHelper.getTenantId()));
        return "/system/org";
    }

    @RequestMapping("/query")
    @ResponseBody
    public String query(@RequestParam Map<String, Object> paramMap) throws Exception {
        PagerParamHandler.convertFormat(paramMap);
        paramMap.put("tenantId", AuthHelper.getTenantId());
        return orgService.findByCondition2PagerString(paramMap);
    }

    /**
     * @Title: save
     * @Description:
     * @param org
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    @ResponseBody
    public String save(Org org) throws Exception {
    	ValidResult vr = validity(org, false);
    	if (!vr.isSuccess()) {
    		return Return.failure(vr.getMsgCode(), vr.getArguments());
    	}
        EntityUtil.setBaseProps4Save(org);
        org.setValidity(BaseEntity.VF_VALID);
        org.setTenantId(AuthHelper.getTenantId());
        orgService.saveOrg(org);
        return Return.success();
    }

    /**
     * @Title: 删除商户机构
     * @Description:
     * @param org
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Org org) throws Exception {
        if (org.getId() < 1) {
            return Return.failure("CP-000016");
        }
        Org o = orgService.getSingle(org.getId(), 
        		AuthHelper.getTenantId());
        if (o == null) {
        	return Return.failure("CP-000017");
        }
        // 一般用户不能删除顶级组织机构
        if (o.getParentId() == null || o.getParentId() == 0) {
        	return Return.failure("CP-001001.1");
        }
        if (orgService.getOrgPersonCount(o.getId()) > 0) {
        	return Return.failure("CP-001001.2", o.getOrgTypeName(), 
        			o.getShortName());
        }
        orgService.delete(o);
        return Return.success();
    }

    /**
     * @Title: 更新机构
     * @Description:
     * @param org
     * @param oldNameFullpath
     * @return
     * @throws Exception
     */
    @RequestMapping("/update")
    @ResponseBody
    public String update(Org org) throws Exception {
    	ValidResult vr = validity(org, true);
    	if (!vr.isSuccess()) {
    		return Return.failure(vr.getMsgCode(), vr.getArguments());
    	}
        Org o = orgService.getSingle(org.getId(), 
        		AuthHelper.getTenantId());
        if (o == null) {
        	return Return.failure("CP-000017");
        }
        if (o.getParentId() == null || o.getParentId() == 0) {
        	org.setParentId(0L);
        }
        org.setTenantId(o.getTenantId());
        EntityUtil.setBaseProps4Update(org);
        orgService.update(org, o.getNameFullpath());
        return Return.success();
    }

    /**
     * @Title: 获取商户机构树
     * @Description:
     * @return
     * @throws Exception
     */
    @RequestMapping("/getOrgTree")
    @ResponseBody
    public String getOrgTree() throws Exception {
    	List<Org> list = orgService.getOrgTree(AuthHelper.getTenantId());
    	return EUITreeUtil.buildTreeJson(list);
    }

    /**
     * @Title: 简单表单验证
     * @Description:
     * @param org
     * @param isUpdate
     * @return
     */
    private ValidResult validity(Org org, boolean isUpdate) {
    	if (isUpdate) {
    		if (org.getId() == null || org.getId() < 1) {
    			return new ValidResult(false, "CP-000016");
    		}
    	}
    	if (StringUtil.isBlank(org.getShortName())) {
    		return new ValidResult(false, "CP-000004", "简称");
    	}
    	if (StringUtil.isBlank(org.getCode())) {
    		return new ValidResult(false, "CP-000004", "编码");
    	}
    	if (org.getOrgType() == null || org.getOrgType() < 1) {
    		return new ValidResult(false, "CP-000004", "机构类型");
    	}
    	if (StringUtil.isBlank(org.getIdFullpath())) {
    		return new ValidResult(false, "CP-000011");
    	}
    	if (StringUtil.isBlank(org.getNameFullpath())) {
    		return new ValidResult(false, "CP-000011");
    	}
    	return ValidResult.SUCCESS;
    }

}