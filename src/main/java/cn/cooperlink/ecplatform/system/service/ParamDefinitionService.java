/* **************************************************************
 *
 * 文件名称：ParamDefinitionService.java
 *
 * 包含类名：cn.cooperlink.ecplatform.system.service.ParamDefinitionService
 * 创建日期：2014年8月17日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.system.service;

import cn.cooperlink.ecplatform.system.persistence.mapper.ParamDefinitionMapper;
import cn.cooperlink.framework.core.BaseService4Mapper;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service 类
 *
 * 创建日期：2014年8月17日
 * 创建作者：潘云峰
 */
@Service
@Transactional
public class ParamDefinitionService extends BaseService4Mapper {

    /** paramdefinitionMapper */
    @Resource
    private ParamDefinitionMapper paramdefinitionMapper;

    @SuppressWarnings("unchecked")
    @Override
    public ParamDefinitionMapper getMapper() {
        return paramdefinitionMapper;
    }

}