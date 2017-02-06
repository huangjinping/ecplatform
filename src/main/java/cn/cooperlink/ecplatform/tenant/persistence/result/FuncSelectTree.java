/* **************************************************************
 *
 * 文件名称：FuncSelectTree.java
 *
 * 包含类名：cn.cooperlink.ecplatform.tenant.persistence.result.FuncSelectTree
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
public class FuncSelectTree extends FuncTreeEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = -8985449455644053548L;
	
	private Long purchasedId;

	public Long getPurchasedId() {
		return purchasedId;
	}

	public void setPurchasedId(Long purchasedId) {
		this.purchasedId = purchasedId;
	}

	@Override
	public boolean getChecked() {
		if (purchasedId != null && purchasedId > 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public FuncSelectTree fromFunc(Function function) {
		FuncSelectTree fst = new FuncSelectTree();
		fst.setId(function.getId());
		fst.setParentId(function.getParentId());
		fst.setText(function.getFuncName());
		fst.setCode(function.getFuncCode());
		fst.setUrl(function.getUrl());
		return fst;
	}
	
}
