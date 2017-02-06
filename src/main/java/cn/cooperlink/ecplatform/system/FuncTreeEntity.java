/** 
 * Project Name：ecplatform 
 * File Name：FuncTreeEntity.java 
 * Package Name：cn.cooperlink.ecplatform.system 
 * Date：2014年8月18日 下午4:12:20 
 * Copyright (c) 2014, cooperlink.cn All Rights Reserved. 
 * 北京酷博灵科信息技术有限公司
 */
package cn.cooperlink.ecplatform.system;

import cn.cooperlink.ecplatform.system.entity.Function;
import cn.cooperlink.framework.core.BaseEUITreeEntity;

/**
 * @Title：活动功能树基类
 * @Description：此类的实例必须是功能类型为3的对象（活动），
 * 				此类为配合FuncTreeUtil工具类使用而设计，
 * 				如果不需要使用FuncTreeUtil工具类，则不建议继承此类。
 * @Package cn.cooperlink.ecplatform.system
 * @ClassName FuncTreeEntity
 * @author Taylen.Pan   
 * @date 2014年8月18日 下午4:12:20
 * @version 
 */
public abstract class FuncTreeEntity extends BaseEUITreeEntity {

	private static final long serialVersionUID = 1L;

    /**
     * @Fields code: 功能编码 
     */
    private String code;

    /**
     * @Fields funcUrl: 访问路径
     */
    private String url;
    
	/**
	 * @Fields funcIdFullpath: 功能Id路径
	 */
	private String funcIdFullpath;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFuncIdFullpath() {
		return funcIdFullpath;
	}

	public void setFuncIdFullpath(String funcIdFullpath) {
		this.funcIdFullpath = funcIdFullpath;
	}
	
	public abstract <T extends FuncTreeEntity> T fromFunc(Function function);
}
