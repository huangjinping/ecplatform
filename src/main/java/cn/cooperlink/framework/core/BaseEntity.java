/* **************************************************************
 *
 * 文件名称：BaseEntity.java
 *
 * 包含类名：cn.cooperlink.framework.core.BaseEntity
 * 创建日期：2014年2月23日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.framework.core;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类基类。
 * <p>定义id、创建人、创建时间、更新人、更新时间 5 个公共属性。</p>
 * 
 * 创建日期：2014年2月23日
 * 创建作者：潘云峰
 */
public class BaseEntity implements Serializable{
	
	/** validityFlag 1 代表数据有效 */
	public static final int VF_VALID = 1;
	
	/** validityFlag 2 代表数据无效 */
	public static final int VF_INVALID = 2;
	
	/** validityFlag 9 代表数据已删除 ( 逻辑删除标识 ) */
	public static final int VF_DELETED = 9;
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Long id;
	
	/** 创建人ID */
	private Long createBy;
	
	/** 创建时间 */
	private Date createTime;
	
	/** 更新人ID */
	private Long updateBy;
	
	/** 更新时间 */
	private Date updateTime;
	
	/** 记录版本号 */
	private int version = 0;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
