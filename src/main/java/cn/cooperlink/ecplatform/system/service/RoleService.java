/* **************************************************************
 *
 * 文件名称：RoleService.java
 *
 * 包含类名：cn.cooperlink.ecplatform.system.service.RoleService
 * 创建日期：2014年8月9日
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

import cn.cooperlink.ecplatform.system.entity.Role;
import cn.cooperlink.ecplatform.system.entity.RoleFunc;
import cn.cooperlink.ecplatform.system.entity.UserRole;
import cn.cooperlink.ecplatform.system.persistence.mapper.RoleFuncMapper;
import cn.cooperlink.ecplatform.system.persistence.mapper.RoleMapper;
import cn.cooperlink.ecplatform.system.persistence.mapper.UserRoleMapper;
import cn.cooperlink.ecplatform.tenant.persistence.result.AuthorizedFuncTree;
import cn.cooperlink.framework.core.BaseService4Mapper;
import cn.cooperlink.framework.core.util.EntityUtil;
import cn.cooperlink.util.StringUtil;

/**
 * Service 类
 *
 * 创建日期：2014年8月9日
 * 创建作者：潘云峰
 */
@Service
@Transactional
public class RoleService extends BaseService4Mapper {

    /** roleMapper */
    @Resource
    private RoleMapper roleMapper;
    
    @Resource
    private RoleFuncMapper roleFuncMapper;
    
    @Resource
    private UserRoleMapper userRoleMapper;

    @SuppressWarnings("unchecked")
    @Override
    public RoleMapper getMapper() {
        return roleMapper;
    }

	/**
	 * 保存角色及角色功能
	 *
	 * @param role
	 * @param funcIds
	 */
	public void saveRole(Role role, String funcIds) throws Exception {
		roleMapper.save(role);
		saveRoleFuncs(role.getId(), funcIds);
	}
	
	/**
	 * 修改角色
	 *
	 * @param role
	 * @param funcIds
	 * @throws Exception
	 */
	public void updateRole(Role role, String funcIds) throws Exception {
		roleMapper.update(role);
		roleFuncMapper.deleteByRoleId(role.getId());
		saveRoleFuncs(role.getId(), funcIds);
	}
	
	/**
	 * 保存角色与功能关系
	 *
	 * @param roleId
	 * @param funcIds
	 * @throws Exception
	 */
	private void saveRoleFuncs(Long roleId, String funcIds) throws Exception {
		if (StringUtil.isNotBlank(funcIds)) {
			String[] fIds = funcIds.split(",");
			List<RoleFunc> rfList = new ArrayList<RoleFunc>();
			int len = fIds.length;
			RoleFunc rf = null;
			for (int i = 0; i < len; i++) {
				rf = new RoleFunc();
				rf.setFuncId(Long.parseLong(fIds[i]));
				rf.setRoleId(roleId);
				rfList.add(rf);
			}
			if (rfList.size() > 0) {
				roleFuncMapper.saveBatch(rfList);
			}
		}
	}

	/**
	 * 获取角色功能树
	 *
	 * @param tenantId
	 * @param roleId
	 * @return
	 */
	public List<AuthorizedFuncTree> findFuncsByRole(Long tenantId, Long roleId) {
		Map<String, Long> paramMap = new HashMap<String, Long>();
		paramMap.put("tenantId", tenantId);
		paramMap.put("roleId", roleId);
		return roleFuncMapper.findFuncTreeByRole(paramMap);
	}
	
	/**
	 * 查询用户角色
	 * 分配角色功能使用
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<Role> queryAllRoleByUser(Map<String, Long> paramMap) {
		return roleMapper.queryAllRoleByUser(paramMap);
	}

	/**
	 * 保存用户角色关系
	 *
	 * @param userId
	 * @param ids
	 * @throws Exception
	 */
	public void saveUserRole(Long userId, String ids) throws Exception {
		roleMapper.deleteUserRole(userId);
		if (StringUtil.isNotBlank(ids)) {
			String[] idsArr = ids.split(",");
			int len = idsArr.length;
			List<UserRole> list = new ArrayList<UserRole>();
			UserRole ur = null;
			for (int i = 0; i < len; i++) {
				if (StringUtil.isBlank(idsArr[i])) {
					continue;
				}
				ur = new UserRole();
				ur.setRoleId(Long.parseLong(idsArr[i]));
				ur.setUserId(userId);
				EntityUtil.setBaseProps4Save(ur);
				list.add(ur);
			}
			if (list.size() > 0) {
				userRoleMapper.saveBatch(list);
			}
		}
	}
	
	/**
	 * 查找用户是否存在
	 *
	 * @param paramMap
	 * @return
	 */
	public int checkUser(Map<String, Long> paramMap) {
		return roleMapper.checkUser(paramMap);
	}

	/**
	 * @Title: 删除角色
	 * @Description:
	 * @param role
	 */
	public void deleteRole(Role role) throws Exception {
		// 删除角色
		roleMapper.delete(role);
		// 删除角色用户关联信息
		userRoleMapper.deleteByRoleId(role.getId());
		// 删除角色功能关系
		roleFuncMapper.deleteByRoleId(role.getId());
	}

}