/* **************************************************************
 *
 * 文件名称：CameraMapper.java
 *
 * 包含类名：cn.cooperlink.ecplatform.petstore.camera.persistence.mapper.CameraMapper
 * 创建日期：2014.08.07
 * 创建作者：dalvik
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.petstore.camera.persistence.mapper;

import java.util.List;

import cn.cooperlink.ecplatform.petstore.camera.entity.Camera;
import cn.cooperlink.framework.core.BaseMapper;
import cn.cooperlink.spring.extension.Mapper;

/**
 * Mapper类。
 *
 * 创建日期：2014.08.07
 * 创建作者：dalvik
 */
@Mapper
public interface CameraMapper extends BaseMapper {
	public List<Camera> getCamera();
}