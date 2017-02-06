/* **************************************************************
 *
 * 文件名称：RegTenantService.java
 *
 * 包含类名：cn.cooperlink.ecplatform.tenant.service.RegTenantService
 * 创建日期：2014年7月31日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 大唐云动力科技股份有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.tenant.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cooperlink.ecplatform.system.entity.Org;
import cn.cooperlink.ecplatform.system.service.OrgService;
import cn.cooperlink.ecplatform.tenant.entity.RegTenant;
import cn.cooperlink.ecplatform.tenant.persistence.mapper.RegTenantMapper;
import cn.cooperlink.framework.core.BaseEntity;
import cn.cooperlink.framework.core.BaseService4Mapper;
import cn.cooperlink.framework.core.util.EntityUtil;

/**
 * Service 类
 *
 * 创建日期：2014年7月31日
 * 创建作者：潘云峰
 */
@Service
@Transactional
public class RegTenantService extends BaseService4Mapper {

    /** regtenantMapper */
    @Resource
    private RegTenantMapper regtenantMapper;
    
    @Resource
    private OrgService orgService;

    @SuppressWarnings("unchecked")
    @Override
    public RegTenantMapper getMapper() {
        return regtenantMapper;
    }

	public void saveTenant(RegTenant regTenant, String sync2Org) 
			throws Exception {
		regTenant.setValidity(BaseEntity.VF_VALID);
		regtenantMapper.save(regTenant);
		if (sync2Org != null && "yes".equals(sync2Org)) {
			Org org = new Org();
			org.setShortName(regTenant.getCnShortName());
			org.setFullName(regTenant.getCnFullName());
			org.setOrgType(Org.ORG_TYPE_ORG);
			org.setCode(regTenant.getCode());
			org.setTenantId(regTenant.getId());
			org.setParentId(new Long(0));
			org.setNameFullpath("/" + regTenant.getCnShortName());
			EntityUtil.setBaseProps4Save(org);
			orgService.saveOrg(org);
			org.setIdFullpath("/" + org.getId() + ".org");
			orgService.setOrgIdFullpath(org);
			regTenant.setOrgId(org.getId());
			regtenantMapper.setOrgId(regTenant);
		}
	}

	/**
	 * 逻辑删除租户
	 *
	 * @param regTenant
	 * @throws Exception
	 */
	public void deleteTenant(RegTenant regTenant) throws Exception {
		regTenant.setValidity(BaseEntity.VF_DELETED);
		regtenantMapper.update(regTenant);
	}

}