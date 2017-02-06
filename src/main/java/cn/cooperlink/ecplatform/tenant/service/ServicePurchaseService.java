/* **************************************************************
 *
 * 文件名称：ServicePurchaseService.java
 *
 * 包含类名：cn.cooperlink.ecplatform.tenant.service.ServicePurchaseService
 * 创建日期：2014年8月4日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.tenant.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cooperlink.ecplatform.tenant.entity.ServicePurchase;
import cn.cooperlink.ecplatform.tenant.persistence.mapper.ServicePurchaseMapper;
import cn.cooperlink.ecplatform.tenant.persistence.result.FuncSelectTree;
import cn.cooperlink.framework.core.BaseService4Mapper;
import cn.cooperlink.framework.core.util.EUITreeUtil;

/**
 * Service 类
 *
 * 创建日期：2014年8月4日
 * 创建作者：潘云峰
 */
@Service
@Transactional
public class ServicePurchaseService extends BaseService4Mapper {

    /** servicepurchaseMapper */
    @Resource
    private ServicePurchaseMapper servicepurchaseMapper;

    @SuppressWarnings("unchecked")
    @Override
    public ServicePurchaseMapper getMapper() {
        return servicepurchaseMapper;
    }

	public void saveAll(List<ServicePurchase> list, Long tenantId) 
			throws Exception {
		servicepurchaseMapper.deleteByTenant(tenantId);
		servicepurchaseMapper.saveBatch(list);
		List<Long> idList = servicepurchaseMapper.findInvalidFuncRoleIds(tenantId);
		if (idList != null && idList.size() > 0) {
			servicepurchaseMapper.deleteRoleFuncByFuncRoleIds(idList);
		}
	}

	/**
	 * 获取服务树JSON字符串，被指定商户购买了的标记为checked
	 *
	 * @param tenantId
	 * @return
	 */
	public String getServicesTreeJson(Long tenantId) {
		List<FuncSelectTree> list = servicepurchaseMapper
				.findAllService(tenantId);
		return EUITreeUtil.buildTreeJson(list, true);
	}

	/**
	 * 获取商户已购买服务的树结构
	 * 已被授权的服务奖处于激活状态
	 *
	 * @param paramMap
	 */
	public List<FuncSelectTree> findPurchasedSvrs(Map<String, Long> paramMap) {
		return servicepurchaseMapper.findPurchasedSvrs(paramMap);
	}

}