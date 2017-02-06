/** 
 * Project Name：ecplatform 
 * File Name：CommonMapper.java 
 * Package Name：cn.cooperlink.ecplatform.common.persistence.mapper 
 * Date：2014年8月13日 下午1:37:05 
 * Copyright (c) 2014, cooperlink.cn All Rights Reserved. 
 * 北京酷博灵科信息技术有限公司
 */
package cn.cooperlink.ecplatform.common.persistence.mapper;

import java.util.Map;

import cn.cooperlink.spring.extension.Mapper;

/**
 * @Title：
 * @Description：
 * @Package： cn.cooperlink.ecplatform.common.persistence.mapper
 * @ClassName： CommonMapper
 * @Author： Taylen.Pan
 * @Date： 2014年8月13日 下午1:37:05
 * @version： 
 */
@Mapper
public interface CommonMapper {

	/**
	 * 查找下一个排序号
	 * <p>树状结构排序号。</p>
	 *
	 * @param params
	 * @return
	 */
	public Map<String, String> nextSequence(Map<String, Object> params);

	/**
	 * @Title: 更新子节点名称路径
	 * @Description:
	 * @param paramObj
	 */
	public void updateChildNamePath(Map<String, String> paramObj);
	
	/**
	 * @Title: 根据某字段值统计记录数
	 * @Description:
	 * @param paramObj
	 */
	public long getCountByFieldVal(Map<String, Object> paramObj);
	
}
