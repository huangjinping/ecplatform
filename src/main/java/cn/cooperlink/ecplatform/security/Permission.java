/** 
 * Project Name：ecplatform 
 * File Name：Permission.java 
 * Package Name：cn.cooperlink.ecplatform.security 
 * Date：2014年8月20日 上午10:23:33 
 * Copyright (c) 2014, cooperlink.cn All Rights Reserved. 
 * 北京酷博灵科信息技术有限公司
 */
package cn.cooperlink.ecplatform.security;

import cn.cooperlink.ecplatform.system.FuncTreeEntity;
import cn.cooperlink.ecplatform.system.entity.Function;

/**
 * @Title：权限类
 * @Description：此类是Function类的包装类，仅仅是为了让名称更符合业务场景而设计。
 * @Package cn.cooperlink.ecplatform.security
 * @ClassName Permission
 * @author Taylen.Pan   
 * @date 2014年8月20日 上午10:23:33
 * @version 
 */
public class Permission extends FuncTreeEntity {

	private static final long serialVersionUID = -4330486754026501858L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getText() {
		return name;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Permission fromFunc(Function function) {
		Permission p = new Permission();
		p.setId(function.getId());
		p.setFuncIdFullpath(function.getIdFullpath());
		p.setParentId(function.getParentId());
		p.setUrl(function.getUrl());
		p.setCode(function.getFuncCode());
		p.setName(function.getFuncName());
		return p;
	}

}
