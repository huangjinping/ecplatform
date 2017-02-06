/* 
 * Project Name：移动电商平台
 * File Name：DicDefinitionService.java 
 * Package Name：cn.cooperlink.ecplatform.system.service.DicDefinitionService
 * Date：2014-08-24 10:30:59
 * Copyright (c) 2014, cooperlink.cn All Rights Reserved.
 * 北京酷博灵科信息技术有限公司
 */
package cn.cooperlink.ecplatform.system.service;

import cn.cooperlink.ecplatform.system.persistence.mapper.DicDefinitionMapper;
import cn.cooperlink.framework.core.BaseService4Mapper;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Title：DicDefinitionService
 * @Description：Service 类
 * @Package cn.cooperlink.ecplatform.system.service
 * @ClassName DicDefinitionService
 * @author 潘云峰
 * @date 2014-08-24 10:30:59
 * @version 
 */
@Service
@Transactional
public class DicDefinitionService extends BaseService4Mapper {

    /**
     * @Fields name: dicdefinitionMapper
     */
    @Resource
    private DicDefinitionMapper dicdefinitionMapper;

    @SuppressWarnings("unchecked")
    @Override
    public DicDefinitionMapper getMapper() {
        return dicdefinitionMapper;
    }

}