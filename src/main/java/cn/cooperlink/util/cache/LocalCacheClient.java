/** 
 * Project Name：ecplatform 
 * File Name：LocalCacheClient.java 
 * Package Name：cn.cooperlink.util.cache 
 * Date：2014年8月18日 上午8:35:57 
 * Copyright (c) 2014, cooperlink.cn All Rights Reserved. 
 * 北京酷博灵科信息技术有限公司
 */
package cn.cooperlink.util.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Title：
 * @Description：
 * @Package cn.cooperlink.util.cache
 * @ClassName LocalCacheClient
 * @author Taylen.Pan   
 * @date 2014年8月18日 上午8:35:57
 * @version 
 */
public class LocalCacheClient implements CacheClient {
	
	private final Map<Object, Object> 
		cache = new HashMap<Object, Object>();

	@Override
	public Object get(Object key) {
		return cache.get(key);
	}

	@Override
	public List<?> getAll(Object[] keys) {
		List<Object> list = new ArrayList<Object>();
		if (keys != null && keys.length >= 0) {
			for (Object key : keys) {
				list.add(get(key));
			}
		}
		return list;
	}

	@Override
	public void put(Object key, Object value) {
		cache.put(key, value);
	}

	@Override
	public void put(Object key, Object value, long expire) {
		cache.put(key, value);
	}

	@Override
	public boolean remove(Object key) {
		cache.remove(key);
		return true;
	}

	@Override
	public void removeAll(Collection<?> keys) {
		if (keys != null && keys.size() >= 0) {
			for (Object key : keys) {
				remove(key);
			}
		}
	}

	@Override
	public void update(Object key, Object value) {
		if (containKey(key)) {
			put(key, value);
		}
	}

	@Override
	public void updateAll(Map<Object, Object> kvMap) {
		if (kvMap != null) {
			Set<Object> keys = kvMap.keySet();
			for (Object key : keys) {
				update(key, kvMap.get(key));
			}
		}
	}

	@Override
	public boolean containKey(Object key) {
		if (get(key) != null) {
			return true;
		}
		return false;
	}

	@Override
	public List<?> getKeys() {
		return new ArrayList<Object>(cache.keySet());
	}
	
	/**
	 * 对于本地缓存，shutdown 方法将会对缓存进行清理操作。
	 */
	@Override
	public void shutdown() {
		cache.clear();
	}

}
