/* **************************************************************
 *
 * 文件名称：RegTenantController.java
 *
 * 包含类名：cn.cooperlink.ecplatform.tenant.web.controller.RegTenantController
 * 创建日期：2014年7月31日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 大唐云动力科技股份有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.tenant.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cooperlink.ecplatform.tenant.entity.RegTenant;
import cn.cooperlink.ecplatform.tenant.service.RegTenantService;
import cn.cooperlink.framework.core.Return;
import cn.cooperlink.framework.core.util.EntityUtil;
import cn.cooperlink.framework.core.util.PagerParamHandler;

/**
 * 注册商户管理 Controller 类
 *
 * 创建日期：2014年7月31日
 * 创建作者：潘云峰
 */
@Controller
@RequestMapping(value="/tenant/regTenant", method={RequestMethod.GET, RequestMethod.POST})
public class RegTenantController {

    /** regTenantService */
    @Resource
    private RegTenantService regTenantService;

    @RequestMapping
    public String index() throws Exception {
        return "tenant/regTenant";
    }

    @RequestMapping("/query")
    @ResponseBody
    public String query(@RequestParam Map<String, Object> paramMap) throws Exception {
        PagerParamHandler.convertFormat(paramMap);
        return regTenantService.findByCondition2PagerString(paramMap);
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(RegTenant regTenant, String sync2Org) throws Exception {
        EntityUtil.setBaseProps4Save(regTenant);
        regTenantService.saveTenant(regTenant, sync2Org);
        return Return.success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(RegTenant regTenant) throws Exception {
        if (regTenant.getId() <= 1) {
            return Return.failure();
        }
        regTenantService.deleteTenant(regTenant);
        return Return.success();
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(RegTenant regTenant, String sync2Org) throws Exception {
        if (regTenant.getId() <= 1) {
            return Return.failure();
        }
        EntityUtil.setBaseProps4Update(regTenant);
        regTenantService.update(regTenant);
        return Return.success();
    }
    
    @RequestMapping("/treeUI")
    @ResponseBody
    public String treeUI(@RequestParam Map<String, Object> paramMap) throws Exception{
    	List<RegTenant> list = regTenantService.findByCondition(paramMap);
    	StringBuilder sb = new StringBuilder();
    	sb.append("[");
    	for (RegTenant rt : list) {
    		sb.append("{\"id\":\"");
    		sb.append(rt.getId());
    		sb.append("\",\"text\":\"");
    		sb.append(rt.getCnShortName());
    		sb.append("\"}"); 
    	}
    	sb.append("]");
    	return sb.toString();
    }
    
    @InitBinder  
    public void initBinder(WebDataBinder binder) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        dateFormat.setLenient(false);  
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));  
    } 

}