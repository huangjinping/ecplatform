/* 
 * Project Name：移动电商平台
 * File Name：DicItemService.java 
 * Package Name：cn.cooperlink.ecplatform.system.service.DicItemService
 * Date：2014-08-24 10:32:28
 * Copyright (c) 2014, cooperlink.cn All Rights Reserved.
 * 北京酷博灵科信息技术有限公司
 */
package cn.cooperlink.ecplatform.system.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cooperlink.ecplatform.common.persistence.mapper.CommonMapper;
import cn.cooperlink.ecplatform.system.entity.DicItem;
import cn.cooperlink.ecplatform.system.persistence.mapper.DicItemMapper;
import cn.cooperlink.framework.core.BaseService4Mapper;
import cn.cooperlink.util.StringUtil;

/**
 * @Title：DicItemService
 * @Description：Service 类
 * @Package cn.cooperlink.ecplatform.system.service
 * @ClassName DicItemService
 * @author 潘云峰
 * @date 2014-08-24 10:32:28
 * @version 
 */
@Service
@Transactional
public class DicItemService extends BaseService4Mapper {

    /**
     * @Fields name: dicitemMapper
     */
    @Resource
    private DicItemMapper dicitemMapper;
    
    /**
     * @Fields commonMapper: 通用工具类
     */
    @Resource
    private CommonMapper commonMapper;

    @SuppressWarnings("unchecked")
    @Override
    public DicItemMapper getMapper() {
        return dicitemMapper;
    }

	/**
	 * @Title: 保存字典项
	 * @Description:
	 * @param dicItem
	 * @throws Exception
	 */
	public void saveDicItem(DicItem dicItem) throws Exception {
		dicitemMapper.save(dicItem);
		dicItem.setIdFullpath(StringUtil.null2Empty(
				dicItem.getIdFullpath()) + '/'  + dicItem.getId());
		dicitemMapper.update(dicItem);
	}	
	
	/**
	 * @Title: update
	 * @Description:
	 * @param function
	 * @param oldNameFullpath
	 */
	public void updateDicItem(DicItem dicItem, String oldNameFullpath) 
			throws Exception {
		super.update(dicItem);
		if (!dicItem.getNameFullpath().equals(oldNameFullpath)) {
			// 如果新路径和旧路径不一致，则需要更新子路径。
			updateChildNamePath(dicItem.getIdFullpath(), 
					dicItem.getNameFullpath(), oldNameFullpath);
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
		paramObj.put("tableName", "DICTIONARY_DIC_ITEM");
		commonMapper.updateChildNamePath(paramObj);
	}

}