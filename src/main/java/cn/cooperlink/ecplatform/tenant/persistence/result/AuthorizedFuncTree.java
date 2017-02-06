/* **************************************************************
 *
 * 文件名称：FuncSelectTree.java
 *
 * 包含类名：cn.cooperlink.ecplatform.tenant.persistence.result.AuthorizedFuncTree
 * 创建日期：2014年8月6日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 大唐云动力科技股份有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.tenant.persistence.result;

import cn.cooperlink.ecplatform.system.FuncTreeEntity;
import cn.cooperlink.ecplatform.system.entity.Function;

/**
 * 功能选择树。
 *
 * 创建日期：2014年8月6日
 * 创建作者：潘云峰
 */
public class AuthorizedFuncTree extends FuncTreeEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = -8985449455644053548L;
	
	private Long authorizedSvrId;
	
	private Long purchasedId;

	public Long getAuthorizedSvrId() {
		return authorizedSvrId;
	}

	public void setAuthorizedSvrId(Long authorizedSvrId) {
		this.authorizedSvrId = authorizedSvrId;
	}

	public Long getPurchasedId() {
		return purchasedId;
	}

	public void setPurchasedId(Long purchasedId) {
		this.purchasedId = purchasedId;
	}

	@Override
	public boolean getChecked() {
		if (authorizedSvrId != null && authorizedSvrId > 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AuthorizedFuncTree fromFunc(Function function) {
    	AuthorizedFuncTree aft = new AuthorizedFuncTree();
    	aft.setId(function.getId());
    	aft.setParentId(function.getParentId());
    	aft.setText(function.getFuncName());
    	aft.setIconCls(function.getIconCls());
    	aft.setCode(function.getFuncCode());
    	aft.setUrl(function.getUrl());
		return aft;
	}
	
}
