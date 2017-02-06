/* **************************************************************
 *
 * 文件名称：CacheNotFoundException.java
 *
 * 包含类名：cn.cooperlink.util.cache.CacheNotFoundException
 * 创建日期：2013-6-7
 * 创建作者：潘云峰
 * 版权声明：Copyright 2013 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.util.cache;

/**
 * 缓存不存在异常类。
 * 
 * 创建日期：2013-6-7
 * 创建作者：潘云峰
 */
public class CacheNotFoundException extends RuntimeException {

	/** serialVersionUID */
	private static final long serialVersionUID = 3057384837107003957L;

	public CacheNotFoundException() {
		super("找不到缓存。");
	}
	
	public CacheNotFoundException(String msg) {
		super(msg);
	}
}
