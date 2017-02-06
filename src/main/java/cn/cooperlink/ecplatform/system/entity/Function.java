/* **************************************************************
 *
 * 文件名称：Function.java
 *
 * 包含类名：cn.cooperlink.ecplatform.system.entity.Function
 * 创建日期：2014年8月4日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.system.entity;

import cn.cooperlink.framework.core.BaseEUITreeEntity;

/**
 * 实体类。
 *
 * 创建日期：2014年8月4日
 * 创建作者：潘云峰
 */
public class Function extends BaseEUITreeEntity {

    /** serialVersionUID */
	private static final long serialVersionUID = -4122755574883600257L;

	/** 父级功能 */
    private Long parentId;

    /** 功能名称 */
    private String funcName;

    /** 功能编码 */
    private String funcCode;

    /** 访问路径 */
    private String url;

    /** 功能图标 */
    private String iconCls;

    /** 内建功能 */
    private Integer isBuildIn;

    /** 排序号 */
    private String orderField;

    /** 类型 */
    private Integer type;

    /** 启用 */
    private Integer validity;
    
    private String idFullpath;
    
    private String nameFullpath;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName == null ? null : funcName.trim();
    }

    public String getFuncCode() {
        return funcCode;
    }

    public void setFuncCode(String funcCode) {
        this.funcCode = funcCode == null ? null : funcCode.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls == null ? null : iconCls.trim();
    }

    public Integer getIsBuildIn() {
        return isBuildIn;
    }

    public void setIsBuildIn(Integer isBuildIn) {
        this.isBuildIn = isBuildIn;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField == null ? null : orderField.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }

	@Override
	public String getText() {
		return funcName;
	}

	public String getIdFullpath() {
		return idFullpath;
	}

	public void setIdFullpath(String idFullpath) {
		this.idFullpath = idFullpath;
	}

	public String getNameFullpath() {
		return nameFullpath;
	}

	public void setNameFullpath(String nameFullpath) {
		this.nameFullpath = nameFullpath;
	}

}