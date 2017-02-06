/* **************************************************************
 *
 * 文件名称：ServicePurchaseController.java
 *
 * 包含类名：cn.cooperlink.ecplatform.tenant.web.controller.ServicePurchaseController
 * 创建日期：2014年8月4日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.tenant.web.controller;

import java.util.ArrayList;
import java.util.Date;
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
import cn.cooperlink.ecplatform.system.service.FunctionService;
import cn.cooperlink.ecplatform.tenant.entity.ServicePurchase;
import cn.cooperlink.ecplatform.tenant.service.ServicePurchaseService;
import cn.cooperlink.framework.core.Return;
import cn.cooperlink.framework.core.util.EUITreeUtil;
import cn.cooperlink.framework.core.util.EntityUtil;
import cn.cooperlink.framework.core.util.PagerParamHandler;
import cn.cooperlink.util.StringUtil;

/**
 * Controller 类
 * 
 * 服务购买     CP00003
 *
 * 创建日期：2014年8月4日
 * 创建作者：潘云峰
 */
@Controller
@RequestMapping(value="/tenant/svrPurchase", method={RequestMethod.GET, RequestMethod.POST})
public class ServicePurchaseController {

    /** servicePurchaseService */
    @Resource
    private ServicePurchaseService servicePurchaseService;
    
    @Resource
    private FunctionService functionService;

    @RequestMapping
    public String index() throws Exception {
        return "tenant/svrPurchase";
    }

    @RequestMapping("/query")
    @ResponseBody
    public String query(@RequestParam Map<String, Object> paramMap) throws Exception {
        PagerParamHandler.convertFormat(paramMap);
        List<ServicePurchase> list = servicePurchaseService.findByCondition(paramMap);
        if (list == null || list.size() == 0) {
        	return "[]";
        }
        functionService.findAllCatalog();
        return EUITreeUtil.buildTreeJson(list);
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(ServicePurchase servicePurchase) throws Exception {
        EntityUtil.setBaseProps4Save(servicePurchase);
        servicePurchase.setPurchaseDate(new Date());
        servicePurchaseService.save(servicePurchase);
        return Return.success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(ServicePurchase servicePurchase) throws Exception {
        if (servicePurchase.getId() <= 0) {
            return Return.failure();
        }
        servicePurchaseService.delete(servicePurchase);
        return Return.success();
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(ServicePurchase servicePurchase) throws Exception {
        if (servicePurchase.getId() <= 0) {
            return Return.failure();
        }
        EntityUtil.setBaseProps4Update(servicePurchase);
        servicePurchaseService.update(servicePurchase);
        return Return.success();
    }
    
    /**
     * 保存购买的服务
     *
     * @param ids
     * @param tenantId
     * @return
     * @throws Exception
     */
    @RequestMapping("/saveAll")
    @ResponseBody
    public String saveAll(String ids, Long tenantId) 
    		throws Exception {
    	if (tenantId == null || tenantId < 1) {
    		return Return.failure("CP-000010.2", "租户");
    	}
    	if (StringUtil.isBlank(ids)) {
    		return Return.failure("CP-00003.1");
    	}
    	List<ServicePurchase> list = new ArrayList<ServicePurchase>();
    	String[] idArr = ids.split(",");
    	ServicePurchase sp = null;
    	for (int i = 0; i < idArr.length; i++) {
    		sp = new ServicePurchase();
    		EntityUtil.setBaseProps4Save(sp);
    		sp.setFuncId(Long.parseLong(idArr[i]));
    		sp.setTenantId(tenantId);
    		list.add(sp);
    	}
    	servicePurchaseService.saveAll(list, tenantId);
    	return Return.success();
    }
    
    /**
     * 查找所有服务
     *
     * @param tenantId
     * @return
     */
    @RequestMapping("/getServicesTree")
    @ResponseBody
    public String getServicesTree(Long tenantId) {
    	if (tenantId == null || tenantId < 0) {
    		return "[]";
    	}
    	return servicePurchaseService.getServicesTreeJson(tenantId);
    }
    
    /**
     * 按角色查询商户服务树
     *
     * @return
     */
    @RequestMapping("/getPurchasedSvrTree")
    @ResponseBody
    public String getPurchasedSvrTree(Long roleId) throws Exception {
    	Long tenantId = AuthHelper.getTenantId();
    	Map<String, Long> paramMap = new HashMap<String, Long>();
    	paramMap.put("roleId", roleId);
    	paramMap.put("tenantId", tenantId);
    	return EUITreeUtil.buildTreeJson(
    			FuncTreeUtil.buildFuncTreeList(
    			servicePurchaseService.findPurchasedSvrs(paramMap), 
    			functionService.findAllCatalog()), true);
    }
    
    /**
     * 按角色查询商户服务树
     *
     * @return
     */
    @RequestMapping("/queryPurchasedSvrTreeList")
    @ResponseBody
    public String queryPurchasedSvrTreeList(@RequestParam Map<String, Object> paramMap) 
    		throws Exception {
    	paramMap.put("justActivity", "yes");
    	List<ServicePurchase> list = servicePurchaseService.findByCondition(paramMap);
	    if (list == null || list.size() == 0) {
	    	return "[]";
	    }
	    return EUITreeUtil.buildTreeJson(FuncTreeUtil.buildFuncTreeList(
	    		list, functionService.findAllCatalog()));
    }

}