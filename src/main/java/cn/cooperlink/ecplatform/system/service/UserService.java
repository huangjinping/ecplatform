/* **************************************************************
 *
 * 文件名称：UserService.java
 *
 * 包含类名：cn.cooperlink.ecplatform.system.service.UserService
 * 创建日期：2014年8月6日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.system.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cooperlink.ecplatform.system.entity.OrgUser;
import cn.cooperlink.ecplatform.system.entity.User;
import cn.cooperlink.ecplatform.system.persistence.mapper.OrgUserMapper;
import cn.cooperlink.ecplatform.system.persistence.mapper.UserMapper;
import cn.cooperlink.framework.core.BaseService4Mapper;

/**
 * Service 类
 *
 * 创建日期：2014年8月6日
 * 创建作者：潘云峰
 */
@Service
@Transactional
public class UserService extends BaseService4Mapper {

    /** userMapper */
    @Resource
    private UserMapper userMapper;
    
    @Resource
    private OrgUserMapper orgUserMapper;

    @SuppressWarnings("unchecked")
    @Override
    public UserMapper getMapper() {
        return userMapper;
    }

	/**
	 * @Title: 保存用户信息
	 * @Description:
	 * @param user
	 * @throws Exception
	 */
	public void saveUser(User user) throws Exception {
		userMapper.save(user);
		OrgUser orgUser = new OrgUser();
		orgUser.setIsPrimary(OrgUser.IS_PRIMARY);
		orgUser.setOrgId(user.getOrgId());
		orgUser.setUserId(user.getId());
		orgUserMapper.save(orgUser);
	}

	/**
	 * @Title: 获取单个用户信息
	 * @Description:
	 * @param id
	 * @param tenantId
	 * @return
	 * @throws Exception
	 */
	public User getSingle(Long id, Long tenantId) throws Exception {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("id", id);
		map.put("tenantId", tenantId);
		return userMapper.getSingle(map);
	}

	/**
	 * @Title: isEmpNoExisting
	 * @Description:
	 * @param id
	 * @param employeeNo
	 * @return
	 */
	public boolean isEmpNoExisting(User user) {
		return userMapper.getCountByEmpNo(user) > 0 
				? true : false;
	}

	public boolean validateAccount(User user) {
		return userMapper.getCountByIdAndPwd(user) > 0 
				? true : false;
	}

	public void updatePassword(User user) {
		userMapper.updatePassword(user);
	}

	public void deleteUser(User user) {
		// 逻辑删除。
		userMapper.updateToInvalid(user);
	}

}