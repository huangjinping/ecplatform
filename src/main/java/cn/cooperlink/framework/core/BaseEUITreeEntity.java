/* **************************************************************
 *
 * 文件名称：BaseEUITreeEntity.java
 *
 * 包含类名：cn.cooperlink.framework.core.BaseEUITreeEntity
 * 创建日期：2014年8月4日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.framework.core;

import java.util.List;

/**
 * jquery easyui 树 节点基类。
 *
 * 创建日期：2014年8月4日
 * 创建作者：潘云峰
 */
public class BaseEUITreeEntity extends BaseEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** 父节点 */
	private Long parentId;
	
	/** 显示名称 */
	private String text;
	
	/** 是否展开 closed */
	private String state;
	
	/** 选中状态 */
	private boolean checked = false;
	
	/** 是否叶子节点 */
	private boolean leaf;
	
	/** 图标样式 */
	private String iconCls;
    
    private String loaded = "true";
	
	/** 子节点 */
	private List<BaseEUITreeEntity> children;

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getLoaded() {
		return loaded;
	}

	public void setLoaded(String loaded) {
		this.loaded = loaded;
	}

	public List<BaseEUITreeEntity> getChildren() {
		return children;
	}

	public void setChildren(List<BaseEUITreeEntity> children) {
		this.children = children;
	}

}
