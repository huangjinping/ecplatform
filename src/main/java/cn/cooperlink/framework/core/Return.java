/* **************************************************************
 *
 * 文件名称：Return.java
 *
 * 包含类名：cn.cooperlink.framework.core.Return
 * 创建日期：2014年3月15日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.framework.core;

import static cn.cooperlink.framework.core.ReturnLevel.SUCCESS;
import static cn.cooperlink.framework.core.ReturnLevel.FAILURE;
import static cn.cooperlink.framework.core.ReturnLevel.INFO;
import static cn.cooperlink.framework.core.ReturnLevel.WARNING;
import static cn.cooperlink.framework.core.ReturnLevel.ERROR;
import static cn.cooperlink.framework.core.ReturnLevel.EXCEPTION;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import cn.cooperlink.framework.core.util.JsonValueProcessor4Date;

/**
 * 返回结果类。
 * <p>
 * json形式返回结果,主要针对extjs的ajax请求。
 * 标准格式规范： {"success":true, "msg": "", "code":"", data:{}}
 * <p>
 * 
 * 创建日期：2014年3月15日 创建作者：潘云峰
 */
public class Return {
	
	public enum BeanType {
		SIMPLE_BEAN, LIST
	}

    public static final String RET_SUCCESS = "{\"success\":true}";

    public static final String RET_FAILURE = "{\"success\":false}";

    public static final String EMPTY_BEAN_JSON = "{\"success\":true, \"data\":{}}";

    private static final String BASE_NAME = "message.messages";

    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(BASE_NAME);

    /**
     * 返回简单的成功Json字符串{success:true}
     * 
     * @return
     */
    public static final String success() {
        return RET_SUCCESS;
    }

    /**
     * 返回成功的Json串，返回级别: success
     * <p>
     * 默认 success 为 true
     * </p>
     * 
     * @param msgKey
     * @param arguments
     * @return
     */
    public static final String success(String msgKey, Object... arguments) {
        return stringMsg(SUCCESS, true, msgKey, arguments);
    }

    /**
     * 返回简单的失败字符串{success:false}
     * 
     * @return
     */
    public static final String failure() {
        return RET_FAILURE;
    }

    /**
     * 返回失败的Json串，返回级别 :error
     * <p>
     * 默认 success 为 false
     * </p>
     * 
     * @param msgKey
     * @param arguments
     * @return
     */
    public static final String failure(String msgKey, Object... arguments) {
        return stringMsg(FAILURE, false, msgKey, arguments);
    }

    /**
     * 返回info级别的Json串，返回级别: info
     * <p>
     * 默认 success 为 true
     * </p>
     * 
     * @param msgKey
     * @param arguments
     * @return
     */
    public static final String info(String msgKey, Object... arguments) {
        return info(true, msgKey, arguments);
    }

    /**
     * 返回info级别的Json串，返回级别: info
     * 
     * @param msgKey
     * @param arguments
     * @return
     */
    public static final String info(boolean success, String msgKey, Object... arguments) {
        return stringMsg(INFO, success, msgKey, arguments);
    }

    /**
     * 返回警告信息的Json串，返回级别： warning
     * <p>
     * 默认 success 为 false
     * </p>
     * 
     * @param msgKey
     * @param arguments
     * @return
     */
    public static final String warning(String msgKey, Object... arguments) {
        return warning(false, msgKey, arguments);
    }

    /**
     * 返回警告信息的Json串，返回级别 ：warning
     * 
     * @param success
     * @param msgKey
     * @param arguments
     * @return
     */
    public static final String warning(boolean success, String msgKey, Object... arguments) {
        return stringMsg(WARNING, success, msgKey, arguments);
    }

    /**
     * 返回错误级别的Json串，返回级别:error
     * <p>
     * 默认 success 为 false
     * </p>
     * 
     * @param success
     * @param msgKey
     * @param arguments
     * @return
     */
    public static final String error(String msgKey, Object... arguments) {
        return stringMsg(ERROR, false, msgKey, arguments);
    }

    /**
     * 返回异常级别的Json串，返回级别:exception
     * <p>
     * 默认 success 为 true
     * </p>
     * 
     * @param success
     * @param msgKey
     * @param arguments
     * @return
     */
    public static final String exception(String msgKey, Object... arguments) {
        return exception(false, msgKey, arguments);
    }

    /**
     * 返回异常级别的Json串，返回级别:exception
     * 
     * @param success
     * @param msgKey
     * @param arguments
     * @return
     */
    public static final String exception(boolean success, String msgKey, Object... arguments) {
        if (msgKey == null) {
            return "{success:" + success + ",\"msg\":\"系统异常！\",\"total\":\"0\",\"rows\":[]}";
        }
        StringBuilder result = new StringBuilder();
        result.append("{\"success\":");
        result.append(success);
        String msg = null;
        try {
        	appendMsgCode(result, msgKey);
            msg = getString(msgKey);
            if (arguments != null) {
                msg = MessageFormat.format(msg, arguments);
            }
        } catch (Exception e) {
            msg = msgKey;
        }
        result.append(", \"msg\":\"");
        result.append(msg);
        result.append("\",\"prompt\":\"");
        result.append(EXCEPTION);
        result.append("\",\"total\":\"0\",\"rows\":[]}");
        return result.toString();
    }

    /**
     * 返回字符串，返回字符串中不包含返回级别。
     * <ul>
     * <li>msgKey对应资源文件中的key。</li>
     * <li>如果资源文件中找不到此key，则将msgKey的值做为msg的内容返回</li>
     * <ul>
     * 
     * @param success
     * @param msgKey
     * @param arguments
     * @return
     */
    public static final String stringMsg(boolean success, String msgKey, Object... arguments) {
        return stringMsg(null, success, msgKey, arguments);
    }

    /**
     * 返回字符串消息
     * <ul>
     * <li>msgKey对应资源文件中的key。</li>
     * <li>如果资源文件中找不到此key，则将msgKey的值做为msg的内容返回</li>
     * <ul>
     * 
     * @param success
     * @param msgKey
     * @param arguments
     * @return
     */
    public static final String stringMsg(ReturnLevel level, boolean success, String msgKey, Object... arguments) {
        if (msgKey == null) {
            return success ? RET_SUCCESS : RET_FAILURE;
        }
        StringBuilder result = new StringBuilder();
        result.append("{\"success\":");
        result.append(success);
        String msg = null;
        try {
        	appendMsgCode(result, msgKey);
            msg = getString(msgKey);
            if (arguments != null) {
                msg = MessageFormat.format(msg, arguments);
            }
        } catch (Exception e) {
            msg = msgKey;
        }
        result.append(", \"msg\":\"");
        result.append(msg);
        if (level != null) {
            result.append("\",\"prompt\":\"");
            result.append(level);
        }
        result.append("\"}");
        return result.toString();
    }

    /**
     * 返回字符串。
     * <p>
     * 格式：{success:true|false, data : '','prompt':''}
     * </p>
     * 
     * @param level
     * @param success
     * @param str
     * @return
     */
    public static final String stringData(ReturnLevel level, boolean success, String str) {
        StringBuilder result = new StringBuilder();
        result.append("{\"success\":");
        result.append(success);
        result.append(", \"data\":\"");
        result.append(str);
        if (level != null) {
            result.append("\", \"prompt\":\"");
            result.append(level);
        }
        result.append("\"}");
        return result.toString();
    }

    /**
     * 将javabean转换为json
     * 
     * @param t
     * @param dateFormat
     *            字段中的日期格式处理
     * @return
     */
    public static final <T> String bean2String(T t, String dateFormat) {
        if (t == null) {
            return EMPTY_BEAN_JSON;
        }
        JsonConfig jsonConfig = new JsonConfig();
        JsonValueProcessor valProcessor = new JsonValueProcessor4Date(Constants.DATE_FORMAT_FULL);
        jsonConfig.registerJsonValueProcessor(Date.class, valProcessor);
        return bean2String(t, BeanType.SIMPLE_BEAN, jsonConfig);
    }

    /**
     * 将javabean转换为json
     * 
     * @param t
     * @param dateFormat
     *            字段中的日期格式处理
     * @return
     */
    public static final <T> String bean2String(T t, BeanType type, String dateFormat) {
        if (t == null) {
            return EMPTY_BEAN_JSON;
        }
        JsonConfig jsonConfig = new JsonConfig();
        JsonValueProcessor valProcessor = new JsonValueProcessor4Date(Constants.DATE_FORMAT_FULL);
        jsonConfig.registerJsonValueProcessor(Date.class, valProcessor);
        return bean2String(t, type, jsonConfig);
    }

    public static final <T> String bean2StringWithSession(T t, String dateFormat, String sessionID) {
        if (t == null) {
            return EMPTY_BEAN_JSON;
        }
        JsonConfig jsonConfig = new JsonConfig();
        JsonValueProcessor valProcessor = new JsonValueProcessor4Date(dateFormat);
        jsonConfig.registerJsonValueProcessor(Date.class, valProcessor);
        return bean2StringWithSession(t, jsonConfig, sessionID);
    }

    /**
     * 将javabean转换为json
     * <p>
     * 需要自己定义转换配置
     * </p>
     * 
     * @param t
     * @param jsonConfig
     *            json转换配置
     * @return
     */
    public static final <T> String bean2String(T t, BeanType type, JsonConfig jsonConfig) {
        if (t == null) {
            return EMPTY_BEAN_JSON;
        }
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("{\"success\":true, \"data\":");
            if (type.equals(BeanType.SIMPLE_BEAN)) {
                sb.append(JSONObject.fromObject(t, jsonConfig).toString());
            } else {
            	sb.append(JSONArray.fromObject(t, jsonConfig).toString());
            }
            sb.append("}");
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return EMPTY_BEAN_JSON;
    }

    /**
     * 将javabean转换为json
     * <p>
     * 需要自己定义转换配置
     * </p>
     * 
     * @param t
     * @param jsonConfig
     *            json转换配置
     * @return
     */
    public static final <T> String bean2StringWithSession(T t, JsonConfig jsonConfig, String sessionID) {
        if (t == null) {
            return EMPTY_BEAN_JSON;
        }
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("{\"success\":true, \"data\":");
            sb.append(JSONObject.fromObject(t, jsonConfig).toString());
            sb.append(",\"sessionID\":\"");
            sb.append(sessionID);
            sb.append("\"}");
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return EMPTY_BEAN_JSON;
    }

    /**
     * 返回实体对象的Json字符串
     * <p>
     * 返回字符串的数据格式：{success:true,data:{...（实体json对象）}}
     * </p>
     * 
     * @param t
     * @return
     */
    public static final <T> String bean2String(T t) {
        if (t == null) {
            return EMPTY_BEAN_JSON;
        }
        return bean2String(t, Constants.DATE_FORMAT_FULL);
    }

    public static final <T> String bean2StringWithSession(T t, String sessionID) {
        if (t == null) {
            return EMPTY_BEAN_JSON;
        }
        return bean2StringWithSession(t, Constants.DATE_FORMAT_FULL, sessionID);
    }

    /**
     * 返回List的Json字符串
     * <p>
     * 默认日期类型格式化
     * </p>
     * 
     * @param list
     * @return
     */
    public static final <T> String list2String(List<T> list) {
        // JsonUtil
        return list2String(list, Constants.DATE_FORMAT_FULL);
    }

    /**
     * 返回List的Json字符串
     * 
     * @param list
     * @param dateFormat
     *            日期格式
     * @return
     */
    public static final <T> String list2String(List<T> list, String dateFormat) {
        // JsonUtil
        if (list == null || list.size() == 0) {
            return "[]";
        }
        JsonConfig jsonConfig = new JsonConfig();
        JsonValueProcessor valProcessor = new JsonValueProcessor4Date(dateFormat);
        jsonConfig.registerJsonValueProcessor(Date.class, valProcessor);
        JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
        return jsonArray.toString();
    }

    /**
     * 返回List的Json字符串
     * <p>
     * 自定义Json转换配置
     * </p>
     * 
     * @param list
     * @param jsonConfig
     * @return
     */
    public static final <T> String list2String(List<T> list, JsonConfig jsonConfig) {
        if (list == null || list.size() == 0) {
            return "[]";
        }
        JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
        return jsonArray.toString();
    }

    /**
     * 返回Pager的Json字符串
     * <p>
     * 自定义转换配置对象
     * </p>
     * 
     * @param pager
     *            分页类
     * @param jsonConfig
     *            json转化配置对象
     * @return
     */
    public static final <T> String pager2String(Pager<T> pager, JsonConfig jsonConfig) {
        if (pager == null) {
            return Pager.JSON_EMPTY;
        }
        return pager.toJsonString(jsonConfig);
    }

    /**
     * 返回Pager的Json字符串
     * <p>
     * 默认日期格式化为 yyyy-MM-dd HH:mm:ss
     * </p>
     * 
     * @param pager
     * @return
     */
    public static final <T> String pager2String(Pager<T> pager) {
        if (pager == null) {
            return Pager.JSON_EMPTY;
        }
        return pager.toJsonString();
    }

    /**
     * 返回Pager的Json字符串
     * <p>
     * 自定义日期格式化
     * </p>
     * 
     * @param pager
     *            分页类
     * @param dateFormat
     *            日期格式化
     * @return
     */
    public static final <T> String pager2String(Pager<T> pager, String dateFormat) {
        if (pager == null) {
            return Pager.JSON_EMPTY;
        }
        return pager.toJsonString(dateFormat);
    }

    /**
     * 从BUNDLE中取值，并做字符集转码
     * 
     * @param key
     * @return
     */
    public static final String getString(String key) {
        try {
            if (containsKey(key)) {
                return new String(BUNDLE.getString(key).getBytes("ISO-8859-1"), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    /**
     * @Title: 消息存在则返回消息，不存在 返回空。
     * @Description:
     * @param key
     * @param arguments
     * @return
     */
    public static final String getString(String key, Object... arguments) {
        String msg = null;
        try {
            if (containsKey(key)) {
                msg = new String(BUNDLE.getString(key).getBytes("ISO-8859-1"), "UTF-8");
                if (arguments != null) {
                    msg = MessageFormat.format(msg, arguments);
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
            msg = key;
        }
        return msg;
    }
    
    /**
     * @Title: 特殊用途，easyui 错误处理
     * @Description:
     * @param key
     * @param arguments
     * @return
     */
    public static final String pageEmptyAlert(String key, Object... arguments) {
    	String msg = null;
    	if (key != null) {
    		msg = getString(key);
            if (arguments != null) {
                msg = MessageFormat.format(msg, arguments);
            }
    	}
    	if (msg == null) {
    		msg = "";
    	}
    	return "{\"success\":\"false\",\"msg\":\""+ msg + "\",\"total\":\"0\",\"rows\":[]}";
    }
    
    /**
     * @Title: 判断消息代码是否存在
     * @Description:
     * @param key
     * @return
     */
    private static boolean containsKey(String key) {
    	return BUNDLE.containsKey(key);
    }
    
    /**
     * @Title: 加入code属性
     * @Description:
     * @param result
     * @param msgKey
     */
    private static void appendMsgCode(StringBuilder result, String msgKey) {
    	if (containsKey(msgKey)) {
    		result.append(", \"code\":\"");
    		result.append(msgKey);
    		result.append("\"");
    	}
    }
}
