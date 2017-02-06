/* **************************************************************
 *
 * 文件名称：CacheProviderFactory.java
 *
 * 包含类名：com.cloudpower.netsale.cache.CacheProviderFactory
 * 创建日期：2013-6-7
 * 创建作者：潘云峰
 * 版权声明：Copyright 2013 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.util.cache;

/**
 * 缓存客户端工厂类。
 * 
 * 创建日期：2013-6-7
 * 创建作者：潘云峰
 */
public class CacheClientFactory {
	
	private static AbstractCacheClientFactory cacheClientFactory;
	
	/**
	 * 获取缓存提供者实例，默认返回Ehcache
	 * @return
	 */
	public static final CacheClient getCacheClient() {
		return cacheClientFactory.getCacheClient();
	}

	public static void setCacheClientFactory(
			AbstractCacheClientFactory cacheClientFactory) {
		CacheClientFactory.cacheClientFactory = cacheClientFactory;
	}
	
}
