/* **************************************************************
 *
 * 文件名称：BaseController.java
 *
 * 包含类名：cn.cooperlink.framework.core.BaseController
 * 创建日期：2014-08-15
 * 创建作者：dalvik
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.framework.core;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * 控制器父类。
 * <p>主要用来进行日期类型字符串到Date类型的字段映射 </p>
 * 
 * 创建日期：2014-08-15
 * 创建作者：dalvik
 */
public class BaseController {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
