/** 
 * Project Name：ecplatform 
 * File Name：CommonService.java 
 * Package Name：cn.cooperlink.ecplatform.common.service 
 * Date：2014年8月13日 下午1:43:38 
 * Copyright (c) 2014, cooperlink.cn All Rights Reserved. 
 * 北京酷博灵科信息技术有限公司
 */
package cn.cooperlink.ecplatform.common.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.cooperlink.ecplatform.common.persistence.mapper.CommonMapper;
import cn.cooperlink.util.StringUtil;

/**
 * @Title：复用SQL
 * @Description：
 * @Package： cn.cooperlink.ecplatform.common.service
 * @ClassName： CommonService
 * @Author： Taylen.Pan
 * @Date： 2014年8月13日 下午1:43:38
 * @version： 
 */
@Service
public class CommonService {
	
	@Resource
	private CommonMapper commonMapper;

	/**
	 * 获取子节点下一个排序号
	 * 
	 * @param parentOrgId
	 * @return
	 * @throws Exception
	 */
	public String nextSequence(String tableName, Long parentId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", tableName);
		map.put("parentId", parentId);
		Map<String, String> seqMap = commonMapper.nextSequence(map);
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
	 * @Title: 更新子节点名称路径
	 * @Description:
	 * @param idFullpath
	 * @param newNameFullpath
	 * @param oldNameFullpath
	 * @throws Exception
	 */
	public void updateChildNamePath(String idFullpath, String newNameFullpath,
			String oldNameFullpath, String tableName) {
		Map<String, String> paramObj = new HashMap<String, String>();
		paramObj.put("idFullpath", idFullpath);
		paramObj.put("newNameFullpath", newNameFullpath);
		paramObj.put("oldNameFullpath", oldNameFullpath);
		paramObj.put("tableName", tableName);
		commonMapper.updateChildNamePath(paramObj);
	}
	
	/**
	 * @Title: 根据某字段值统计记录数
	 * @Description:
	 * @param tableName
	 * @param fieldName
	 * @param id
	 * @param fieldValue
	 * @return
	 */
	public boolean isExisting(String tableName, String fieldName, 
			Object id, Object fieldValue) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", tableName);
		map.put("fieldName", fieldName);
		map.put("id", id);
		map.put("fieldValue", fieldValue);
		return commonMapper.getCountByFieldVal(map) > 0 ? true : false;
	}
	
}
