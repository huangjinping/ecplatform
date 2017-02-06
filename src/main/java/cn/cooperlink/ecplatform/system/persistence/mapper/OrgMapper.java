/* **************************************************************
 *
 * 文件名称：OrgMapper.java
 *
 * 包含类名：cn.cooperlink.ecplatform.system.persistence.mapper.OrgMapper
 * 创建日期：2014年8月7日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.system.persistence.mapper;

import java.util.List;
import java.util.Map;

import cn.cooperlink.ecplatform.system.entity.Org;
import cn.cooperlink.framework.core.BaseMapper;
import cn.cooperlink.spring.extension.Mapper;

/**
 * Mapper类。
 *
 * 创建日期：2014年8月7日
 * 创建作者：潘云峰
 */
@Mapper
public interface OrgMapper extends BaseMapper {
	
	/**
	 * 查找下一个排序号
	 *
	 * @param params
	 * @return
	 */
	public Map<String, String> nextSequence(Map<String, Long> params);
	
	/**
	 * 获取商户组织机构
	 *
	 * @param tenantId
	 * @return
	 */
	public List<Org> getOrgTree(Long tenantId);

	/**
	 * @Title: 更新机构id路径
	 * @Description:
	 * @param org
	 */
	public void updateOrgIdFullpath(Org org);

	/**
	 * @Title: 获取机构下人员数量
	 * @Description:
	 * @param orgId
	 * @return
	 */
	public long getOrgPersonCount(Long orgId);
}