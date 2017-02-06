/* **************************************************************
 *
 * 文件名称：PositionController.java
 *
 * 包含类名：cn.cooperlink.ecplatform.system.web.controller.PositionController
 * 创建日期：2014年8月9日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.system.web.controller;

import cn.cooperlink.ecplatform.system.entity.Position;
import cn.cooperlink.ecplatform.system.service.PositionService;
import cn.cooperlink.framework.core.Return;
import cn.cooperlink.framework.core.util.EntityUtil;
import cn.cooperlink.framework.core.util.PagerParamHandler;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller 类
 *
 * 创建日期：2014年8月9日
 * 创建作者：潘云峰
 */
@Controller
@RequestMapping(value="/system/position", method={RequestMethod.GET, RequestMethod.POST})
public class PositionController {

    /** positionService */
    @Resource
    private PositionService positionService;

    @RequestMapping
    public String index() throws Exception {
        return "/system/position";
    }

    @RequestMapping("/query")
    @ResponseBody
    public String query(@RequestParam Map<String, Object> paramMap) throws Exception {
        PagerParamHandler.convertFormat(paramMap);
        return positionService.findByCondition2PagerString(paramMap);
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(Position position) throws Exception {
        EntityUtil.setBaseProps4Save(position);
        positionService.save(position);
        return Return.success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Position position) throws Exception {
        if (position.getId() <= 0) {
            return Return.failure();
        }
        positionService.delete(position);
        return Return.success();
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(Position position) throws Exception {
        if (position.getId() <= 0) {
            return Return.failure();
        }
        EntityUtil.setBaseProps4Update(position);
        positionService.update(position);
        return Return.success();
    }

}