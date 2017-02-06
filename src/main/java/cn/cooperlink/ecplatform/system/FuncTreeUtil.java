/** 
 * Project Name：ecplatform 
 * File Name：FuncTreeUtil.java 
 * Package Name：cn.cooperlink.ecplatform.system 
 * Date：2014年8月18日 下午4:13:53 
 * Copyright (c) 2014, cooperlink.cn All Rights Reserved. 
 * 北京酷博灵科信息技术有限公司
 */
package cn.cooperlink.ecplatform.system;

import java.util.ArrayList;
import java.util.List;

import cn.cooperlink.ecplatform.system.entity.Function;

/**
 * @Title：
 * @Description：
 * @Package cn.cooperlink.ecplatform.system
 * @ClassName FuncTreeUtil
 * @author Taylen.Pan   
 * @date 2014年8月18日 下午4:13:53
 * @version 
 */
public class FuncTreeUtil {

	/**
	 * @Title: 构建功能树
	 * @Description:
	 * @param funcActivity	活动功能
	 * @param funcCatalog   目录功能
	 * @return
	 */
	public static final <T extends FuncTreeEntity> List<T> buildFuncTreeList(
			List<T> funcActivity, List<Function> funcCatalog) {
	    List<T> newList = new ArrayList<T>();
		int len = funcCatalog.size();
		Function func = null;
    	for (T t : funcActivity) {
			newList.add(t);
    		if (len == 0) {
    			continue;
    		}
    		for (int i = 0; i < len; i++) {
    			func = funcCatalog.get(i);
	    		if (t.getFuncIdFullpath().startsWith(func.getIdFullpath())) {
	    			T t1 = t.fromFunc(func);
	    			newList.add(t1);
	    			funcCatalog.remove(i);
	    			len--;
	    			if (t.getFuncIdFullpath().equals(func.getIdFullpath() 
	    					+ "/" + func.getId())) {
	    				break;
	    			}
	    		}
    		}
    	}
    	return newList;
	}
}
