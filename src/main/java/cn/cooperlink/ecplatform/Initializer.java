/** 
 * Project Name：ecplatform 
 * File Name：Initializer.java 
 * Package Name：cn.cooperlink.ecplatform 
 * Date：2014年8月18日 上午9:09:06 
 * Copyright (c) 2014, cooperlink.cn All Rights Reserved. 
 * 北京酷博灵科信息技术有限公司
 */
package cn.cooperlink.ecplatform;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.context.support.XmlWebApplicationContext;

import cn.cooperlink.ecplatform.tenant.entity.TenantConfig;
import cn.cooperlink.ecplatform.tenant.service.TenantConfigService;
import cn.cooperlink.framework.core.util.ConfigManager;

/**
 * @Title：
 * @Description：
 * @Package cn.cooperlink.ecplatform
 * @ClassName Initializer
 * @author Taylen.Pan   
 * @date 2014年8月18日 上午9:09:06
 * @version 
 */
public class Initializer implements ApplicationListener<ContextRefreshedEvent> {

	private Logger log = Logger.getLogger(Initializer.class); 
	
	public static final String SPRING_MVC_DISPATCHER_SERVLET_NAME = "springmvcServlet-servlet";
	
	private int counter = 0;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext context = event.getApplicationContext();
		if (context instanceof XmlWebApplicationContext && 
				counter == 0 && SPRING_MVC_DISPATCHER_SERVLET_NAME
				.equals(((XmlWebApplicationContext)context).getNamespace())) {
			loadTenantConfig(context);
			counter ++;
		}
		log.info("Initializer as ApplicationListener do somthing ");
	}
	
	/**
	 * @Title: 载入租户配置参数
	 * @Description:
	 * @param context
	 */
	public void loadTenantConfig(ApplicationContext context) {
		TenantConfigService tcs = context.getBean(TenantConfigService.class);
		try {
			List<TenantConfig> tcList = tcs.findAll();
			for (TenantConfig cfg : tcList) {
				ConfigManager.putParam(cfg.getParamName() + '_' + cfg.getTenantId(), 
						cfg.getConfigValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
