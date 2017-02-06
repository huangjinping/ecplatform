/* **************************************************************
 *
 * 文件名称：TenantConfigService.java
 *
 * 包含类名：cn.cooperlink.ecplatform.tenant.service.TenantConfigService
 * 创建日期：2014年8月17日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.tenant.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cooperlink.ecplatform.system.entity.User;
import cn.cooperlink.ecplatform.system.persistence.mapper.UserMapper;
import cn.cooperlink.ecplatform.tenant.entity.TenantConfig;
import cn.cooperlink.ecplatform.tenant.persistence.mapper.TenantConfigMapper;
import cn.cooperlink.framework.core.BaseService4Mapper;
import cn.cooperlink.framework.core.util.EntityUtil;

/**
 * Service 类
 *
 * 创建日期：2014年8月17日
 * 创建作者：潘云峰
 */
@Service
@Transactional
public class TenantConfigService extends BaseService4Mapper {

    /** tenantconfigMapper */
    @Resource
    private TenantConfigMapper tenantconfigMapper;
    
    @Resource
    private UserMapper userMapper;

    @SuppressWarnings("unchecked")
    @Override
    public TenantConfigMapper getMapper() {
        return tenantconfigMapper;
    }

	public List<TenantConfig> findTenantParams(Long tenantId) {
		return tenantconfigMapper.findTenantParams(tenantId);
	}


	public void saveAllConifg(String config, Long tenantId,
			String prefix) throws Exception {

    	List<TenantConfig> list = parseConifg(config, tenantId);
		
		User admin = userMapper.getTenantAdmin(tenantId);
		String account = prefix + "admin";
		if (admin == null) {
			// 创建管理员账号
			saveAdmin(account, tenantId);
		} else {
			admin.setUsername(account);
			admin.setEmployeeNo(account);
			EntityUtil.setBaseProps4Update(admin);
			userMapper.update(admin);
		}
		tenantconfigMapper.deleteTenantConfig(tenantId);
		list.add(createTenantConfig(2L, "true", tenantId));
		tenantconfigMapper.saveBatch(list);
	}
	
	private List<TenantConfig> parseConifg(String config, Long tenantId) {
    	List<TenantConfig> list = new ArrayList<TenantConfig>();
    	JSONArray arr = JSONArray.fromObject(config);
    	int len = arr.size();
    	for (int i = 0; i < len; i++) {
    		list.add(createTenantConfig(
    				arr.getJSONObject(i).getLong("id"),
    				arr.getJSONObject(i).getString("value"),
    				tenantId));
    	}
    	
    	return list;
	}
	
	private void saveAdmin(String accountName, Long tenantId) 
			throws Exception {
		// 创建管理员账号
		User admin = new User();
		admin.setUsername(accountName);
		admin.setName("管理员");
		admin.setEmployeeNo(accountName);
		admin.setPassword("e10adc3949ba59abbe56e057f20f883e");
		admin.setUserType(1);
		admin.setTenantId(tenantId);
		EntityUtil.setBaseProps4Save(admin);
		userMapper.save(admin);
	}
	
	private TenantConfig createTenantConfig(Long id, String value, Long tenantId) {
		TenantConfig cfg = new TenantConfig();
		EntityUtil.setBaseProps4Save(cfg);
		cfg.setParamDefId(id);
		cfg.setConfigValue(value);
		cfg.setTenantId(tenantId);
		return cfg;
	}
}