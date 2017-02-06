/* **************************************************************
 *
 * 文件名称：CacheProvider.java
 *
 * 包含类名：cn.cooperlink.util.cache.CacheProvider
 * 创建日期：2013-6-7
 * 创建作者：潘云峰
 * 版权声明：Copyright 2013 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.util.cache;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * CacheClient接口。
 * 
 * 创建日期：2013-6-7
 * 创建作者：潘云峰
 */
public interface CacheClient {
	
	/**
	 * 获取缓存数据
	 * 
	 * @param key 缓存中数据的Key
	 * @return 缓存中的对象
	 * @throws CacheNotFoundException
	 */
	public Object get(Object key);
	
	/**
	 * 获取所有
	 *
	 * @param keys
	 * @return
	 */
	public Object getAll(Object[] keys);
	
	/**
	 * 将对象放入缓存
	 * 
	 * @param key 缓存中数据的Key
	 * @param value 要缓存的对象
	 * @throws CacheNotFoundException
	 */
	public void put(Object key, Object value);
	
	
	/**
	 * 将对象放入缓存
	 * 
	 * @param key
	 * @param value
	 * @param expire 过期毫秒值。
	 */
	public void put(Object key, Object value, long expire);
	
	/**
	 * 从缓存中移除指定对象
	 * 
	 * @param key 缓存中数据的Key
	 * @return
	 */
	public boolean remove(Object key);
	
	/**
	 * 移除所有对象
	 * 
	 * @param keys 要移除对象的key集合
	 */
	public void removeAll(Collection<?> keys);
	
	public void update(Object key, Object value);
	
	public void updateAll(Map<Object, Object> kvMap);
	
	/**
	 * key包含
	 *
	 * @param key
	 * @return
	 */
	public boolean containKey(Object key);
	
	/**
	 * 获取缓存中所有的键
	 * @return 键列表
	 */
	public List<?> getKeys(); 
	
	/**
	 * 关闭缓存
	 */
	public void shutdown();
	
}
