/* **************************************************************
 *
 * 文件名称：ConfigManager.java
 *
 * 包含类名：cn.cooperlink.framework.core.util.ConfigManager
 * 创建日期：2014年3月28日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.framework.core.util;

import cn.cooperlink.util.cache.CacheClient;

/**
 * 配置管理。
 *
 * 创建日期：2014年3月28日
 * 创建作者：潘云峰
 */
public class ConfigManager {
	
	public static final String CONFIG_MANAGER_PREFIX = "_ecplatform_config_";
	
	private static final CacheClient cacheClient;
	
	static {
		cacheClient = (CacheClient) SpringContainer.getBean("myCacheClient");
	}

	private ConfigManager() {}
	
	private static final String cacheKey(String key) {
		return CONFIG_MANAGER_PREFIX + key;
	}
	
	public static final void putParam(String key, Object value) {
		key = cacheKey(key);
		if (cacheClient.containKey(key)) {
			cacheClient.update(key, value);
		} else {
			cacheClient.put(key, value);
		}
	}
	
	public static final void remove(String key) {
		key = cacheKey(key);
		if (cacheClient.containKey(key)) {
			cacheClient.remove(key);
		}
	}
	
	public static final String getString(String key) {
		Object val = cacheClient.get(cacheKey(key));
		if (val != null) {
			return (String) val;
		}
		return null;
	}
	
	public static final String getString(String key, String defaulVal) {
		Object val = cacheClient.get(cacheKey(key));
		if (val != null) {
			return (String) val;
		}
		return defaulVal;
	}
	
	public static final int getInt(String key) {
		return getInt(key, 0);
	}
	
	public static final int getInt(String key, int defaulVal) {
		Object val = cacheClient.get(cacheKey(key));
		if (val != null) {
			try {
				return Integer.parseInt((String) val);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return defaulVal;
	}
	
	public static final long getLong(String key) {
		return getLong(key, 0);
	}
	
	public static final long getLong(String key, long defaulVal) {
		Object val = cacheClient.get(key);
		if (val != null) {
			try {
				return Long.parseLong((String) val);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return defaulVal;
	}
	
	public static final double getDouble(String key) {
		return getDouble(key, 0.0d);
	}
	
	public static final double getDouble(String key, double defaulVal) {
		Object val = cacheClient.get(cacheKey(key));
		if (val != null) {
			try {
				return Double.parseDouble((String) val);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return defaulVal;
	}
	
	public static final float getFloat(String key) {
		return getFloat(key, 0.0f);
	}
	
	public static final float getFloat(String key, float defaulVal) {
		Object val = cacheClient.get(cacheKey(key));
		if (val != null) {
			try {
				return Float.parseFloat((String) val);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return defaulVal;
	}
	
	public static boolean getBoolean(String key) {
		return getBoolean(key, false);
	}
	
	public static final boolean getBoolean(String key, boolean defaulVal) {
		Object val = cacheClient.get(cacheKey(key));
		if (val != null) {
			try {
				if (((String) val).equals("true")
						|| ((String) val).equals("false")) {
					return Boolean.parseBoolean((String) val);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return defaulVal;
	}
	
}
