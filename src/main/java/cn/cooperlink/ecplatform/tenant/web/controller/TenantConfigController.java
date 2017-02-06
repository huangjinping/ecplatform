/* **************************************************************
 *
 * 文件名称：TenantConfigController.java
 *
 * 包含类名：cn.cooperlink.ecplatform.tenant.web.controller.TenantConfigController
 * 创建日期：2014年8月17日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.tenant.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cooperlink.ecplatform.tenant.entity.TenantConfig;
import cn.cooperlink.ecplatform.tenant.service.TenantConfigService;
import cn.cooperlink.framework.core.Return;
import cn.cooperlink.framework.core.util.EntityUtil;
import cn.cooperlink.framework.core.util.PagerParamHandler;

/**
 * Controller 类
 *
 * 创建日期：2014年8月17日
 * 创建作者：潘云峰
 */
@Controller
@RequestMapping(value="/tenant/tenantConfig", method={RequestMethod.GET, RequestMethod.POST})
public class TenantConfigController {

    /** tenantConfigService */
    @Resource
    private TenantConfigService tenantConfigService;

    @RequestMapping
    public String index() throws Exception {
        return "/tenant/tenantConfig";
    }

    @RequestMapping("/query")
    @ResponseBody
    public String query(@RequestParam Map<String, Object> paramMap) throws Exception {
        PagerParamHandler.convertFormat(paramMap);
        return tenantConfigService.findByCondition2PagerString(paramMap);
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(TenantConfig tenantConfig) throws Exception {
        EntityUtil.setBaseProps4Save(tenantConfig);
        tenantConfigService.save(tenantConfig);
        return Return.success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(TenantConfig tenantConfig) throws Exception {
        if (tenantConfig.getId() <= 0) {
            return Return.failure();
        }
        tenantConfigService.delete(tenantConfig);
        return Return.success();
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(TenantConfig tenantConfig) throws Exception {
        if (tenantConfig.getId() <= 0) {
            return Return.failure();
        }
        EntityUtil.setBaseProps4Update(tenantConfig);
        tenantConfigService.update(tenantConfig);
        return Return.success();
    }

    @RequestMapping("/queryTenantParams")
    @ResponseBody
    public String queryTenantParams(Long tenantId) {
    	List<TenantConfig> configList = tenantConfigService
    			.findTenantParams(tenantId);
    	if (configList == null || configList.size() == 0) {
    		return Return.failure("CP-003003.1");
    	}
    	Map<String, String> map = new HashMap<String, String>();
    	for (TenantConfig cfg : configList) {
    		map.put(cfg.getParamName(), cfg.getConfigValue());
    	}
    	return Return.bean2String(map);
    }
    
    @RequestMapping("/saveTenantConfig")
    @ResponseBody
    public String saveTenantConfig(String config, String prefix, Long tenantId) 
    		throws Exception {
    	tenantConfigService.saveAllConifg(config, tenantId, prefix);
    	return Return.success();
    }

}