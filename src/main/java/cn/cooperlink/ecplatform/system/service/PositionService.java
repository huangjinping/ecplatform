/* **************************************************************
 *
 * 文件名称：PositionService.java
 *
 * 包含类名：cn.cooperlink.ecplatform.system.service.PositionService
 * 创建日期：2014年8月9日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.system.service;

import cn.cooperlink.ecplatform.system.persistence.mapper.PositionMapper;
import cn.cooperlink.framework.core.BaseService4Mapper;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service 类
 *
 * 创建日期：2014年8月9日
 * 创建作者：潘云峰
 */
@Service
@Transactional
public class PositionService extends BaseService4Mapper {

    /** positionMapper */
    @Resource
    private PositionMapper positionMapper;

    @SuppressWarnings("unchecked")
    @Override
    public PositionMapper getMapper() {
        return positionMapper;
    }

}