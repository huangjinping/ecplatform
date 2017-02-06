/* **************************************************************
 *
 * 文件名称：FunctionService.java
 *
 * 包含类名：cn.cooperlink.ecplatform.system.service.FunctionService
 * 创建日期：2014年8月4日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cooperlink.ecplatform.common.persistence.mapper.CommonMapper;
import cn.cooperlink.ecplatform.security.Permission;
import cn.cooperlink.ecplatform.system.entity.Function;
import cn.cooperlink.ecplatform.system.persistence.mapper.FunctionMapper;
import cn.cooperlink.framework.core.BaseService4Mapper;
import cn.cooperlink.util.StringUtil;

/**
 * Service 类
 *
 * 创建日期：2014年8月4日
 * 创建作者：潘云峰
 */
@Service
@Transactional
public class FunctionService extends BaseService4Mapper {

    /** functionMapper */
    @Resource
    private FunctionMapper functionMapper;
    
    @Resource
    private CommonMapper commonMapper;

    @SuppressWarnings("unchecked")
    @Override
    public FunctionMapper getMapper() {
        return functionMapper;
    }
    
    /**
     * 加载菜单
     * // 暂时未引入登陆
     * 
     * @return
     * @throws Exception
     */
    public Map<Long, List<Function>> loadMenu() throws Exception {
    	List<Function> list = functionMapper.findAll();
    	Map<Long, List<Function>> menuMap = new HashMap<Long, List<Function>>();
    	List<Function> childreen;
    	for (Function func : list) {
    		childreen = menuMap.get(func.getParentId());
    		if (childreen == null) {
    			childreen = new ArrayList<Function>();
        		menuMap.put(func.getParentId(), childreen);
    		}
    		childreen.add(func);
    	}
    	return menuMap;
    }
    
    public Map<Long, List<Permission>> separateMenu(List<Permission> permissionList) {
		Map<Long, List<Permission>> menuMap = new HashMap<Long, List<Permission>>();
		List<Permission> childreen;
		for (Permission permission : permissionList) {
			childreen = menuMap.get(permission.getParentId());
			if (childreen == null) {
				childreen = new ArrayList<Permission>();
	    		menuMap.put(permission.getParentId(), childreen);
			}
			childreen.add(permission);
		}
		return menuMap;
    }

	/**
	 * @Title: saveFunction
	 * @Description:
	 * @param function
	 * @throws Exception
	 */
	public void saveFunction(Function function) throws Exception {
		functionMapper.save(function);
		function.setIdFullpath(StringUtil.null2Empty(
				function.getIdFullpath()) + '/'  + function.getId());
		functionMapper.update(function);
	}
	
	/**
	 * @Title: update
	 * @Description:
	 * @param function
	 * @param oldNameFullpath
	 */
	public void update(Function function, String oldNameFullpath) 
			throws Exception {
		super.update(function);
		if (!function.getNameFullpath().equals(oldNameFullpath)) {
			// 如果新路径和旧路径不一致，则需要更新子路径。
			updateChildNamePath(function.getIdFullpath(), 
					function.getNameFullpath(), oldNameFullpath);
		}
	}
	
	/**
	 * @Title: 更新子节点名称路径
	 * @Description:
	 * @param idFullpath
	 * @param newNameFullpath
	 * @param oldNameFullpath
	 * @throws Exception
	 */
	public void updateChildNamePath(String idFullpath, String newNameFullpath, 
			String oldNameFullpath) {
		Map<String, String> paramObj = new HashMap<String, String>();
		paramObj.put("idFullpath", idFullpath);
		paramObj.put("newNameFullpath", newNameFullpath);
		paramObj.put("oldNameFullpath", oldNameFullpath);
		paramObj.put("tableName", "DICTIONARY_FUNCTION");
		commonMapper.updateChildNamePath(paramObj);
	}

	/**
	 * @Title: 删除功能
	 * @Description:
	 * @param function
	 * @throws Exception
	 */
	public void deleteFunction(Function function) throws Exception {
		super.delete(function);
		// 同步删除所有关联数据
		// 删除服务授权信息TEN_SERVICE_PURCHASE
		functionMapper.deleteSvrPurchaseByFunc(function.getId());
		// 删除角色授权 SYS_ROLE_FUNC
		functionMapper.deleteRoleFuncByFunc(function.getId());
		
	}

	public List<Function> findAllCatalog() {
		return functionMapper.findAllCatalog();
	}
}