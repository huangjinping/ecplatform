/* **************************************************************
 *
 * 文件名称：OrgService.java
 *
 * 包含类名：cn.cooperlink.ecplatform.system.service.OrgService
 * 创建日期：2014年8月7日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cooperlink.ecplatform.common.service.CommonService;
import cn.cooperlink.ecplatform.system.entity.Org;
import cn.cooperlink.ecplatform.system.persistence.mapper.OrgMapper;
import cn.cooperlink.framework.core.BaseService4Mapper;
import cn.cooperlink.util.StringUtil;

/**
 * Service 类
 * 
 * 创建日期：2014年8月7日 创建作者：潘云峰
 */
@Service
@Transactional
public class OrgService extends BaseService4Mapper {

	/** orgMapper */
	@Resource
	private OrgMapper orgMapper;
	
	@Resource
	private CommonService commonService;

	@SuppressWarnings("unchecked")
	@Override
	public OrgMapper getMapper() {
		return orgMapper;
	}

	/**
	 * 保存机构
	 *
	 * @param org
	 * @throws Exception
	 */
	public void saveOrg(Org org) throws Exception {
		org.setOrderField(nextSequence(org.getParentId()));
		orgMapper.save(org);
		StringBuilder idFullpath = new StringBuilder();
		idFullpath.append(StringUtil.null2Empty(org.getIdFullpath()));
		idFullpath.append("/");
		idFullpath.append(org.getId());
		if (org.getOrgType() == Org.ORG_TYPE_ORG) {
			idFullpath.append(".org");
		} else if (org.getOrgType() == Org.ORG_TYPE_DEPT) {
			idFullpath.append(".dept");
		}
		org.setIdFullpath(idFullpath.toString());
		orgMapper.update(org);
	}

	/**
	 * 获取子节点下一个排序号
	 * 
	 * @param parentOrgId
	 * @return
	 * @throws Exception
	 */
	public String nextSequence(Long parentId) throws Exception {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("parentId", parentId);
		Map<String, String> seqMap = orgMapper.nextSequence(map);
		String seq = "1001";
		if (seqMap != null) {
			String parentSeq = seqMap.get("PARENT_SEQ");
			String selSeq = seqMap.get("SEL_SEQ");
			if (StringUtil.isBlank(selSeq)) {
				if (StringUtil.isBlank(parentSeq)) {
					seq = "1001";
				} else {
					seq = parentSeq + "001";
				}
			} else {
				parentSeq = (parentSeq == null ? "" : parentSeq);
				seq = selSeq.substring(selSeq.length() - 3);
				int tmpSeq = Integer.parseInt(seq) + 1;
				if (tmpSeq < 10) {
					seq = "00" + tmpSeq;
				} else if (tmpSeq < 100) {
					seq = "0" + tmpSeq;
				} else {
					seq = String.valueOf(tmpSeq);
				}
				seq = parentSeq + seq;
			}
		}
		return seq;
	}

	/**
	 * 获取商户组织机构
	 *
	 * @param tenantId
	 * @return
	 */
	public List<Org> getOrgTree(Long tenantId) {
		return orgMapper.getOrgTree(tenantId);
	}

	/**
	 * @Title: 修改机构idFullpath
	 * @Description:
	 * @param org
	 */
	public void setOrgIdFullpath(Org org) {
		orgMapper.updateOrgIdFullpath(org);
	}
	
	/**
	 * @Title: update
	 * @Description:
	 * @param function
	 * @param oldNameFullpath
	 */
	public void update(Org org, String oldNameFullpath) 
			throws Exception {
		super.update(org);
		if (!org.getNameFullpath().equals(oldNameFullpath)) {
			// 如果新路径和旧路径不一致，则需要更新子路径。
			commonService.updateChildNamePath(org.getIdFullpath(), 
					org.getNameFullpath(), oldNameFullpath, "SYS_ORG");
		}
	}
	
	/**
	 * @Title: 获取机构
	 * @Description:
	 * @param org
	 * @return
	 */
	public Org getSingle(Long orgId, Long tenantId) throws Exception {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("id", orgId);
        map.put("tenantId", tenantId);
		return super.getSingle(map);
	}

	/**
	 * @Title: 获取机构下人员数量
	 * @Description:
	 * @param orgId
	 * @return
	 */
	public long getOrgPersonCount(Long orgId) {
		return orgMapper.getOrgPersonCount(orgId);
	}
}