/* **************************************************************
 *
 * 文件名称：MessageController.java
 *
 * 包含类名：cn.cooperlink.myPet.web.controller.MessageController
 * 创建日期：2014.08.07
 * 创建作者：dalvik
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.message.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cooperlink.ecplatform.member.service.MemberService;
import cn.cooperlink.ecplatform.message.entity.Message;
import cn.cooperlink.ecplatform.message.service.MessageService;
import cn.cooperlink.ecplatform.message.web.form.MessageForm;
import cn.cooperlink.framework.core.BaseController;
import cn.cooperlink.framework.core.Constants;
import cn.cooperlink.framework.core.Return;
import cn.cooperlink.framework.core.util.EntityUtil;
import cn.cooperlink.framework.core.util.PagerParamHandler;
import cn.cooperlink.util.StringUtil;

/**
 * Controller 类
 * 
 * 创建日期：2014.08.07 创建作者：dalvik
 */
@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {

    protected final Log log = LogFactory.getLog(getClass());

    @Resource
    private MessageService messageService;

    @Resource
    private MemberService memberService;

    /**
     * 
     * @Title: getMessageList
     * @Description:
     * @param paramMap
     *            title message type start_send_time end_send_time
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    @ResponseBody
    public String getMessageList(@RequestParam Map<String, Object> paramMap) throws Exception {
        PagerParamHandler.convertFormat(paramMap);
        return messageService.findByCondition2PagerString(paramMap);
    }

    /**
     * 
     * @Title: add
     * @Description: 添加消息
     * @param form
     * @return
     * @throws Exception
     */
    @RequestMapping("/add")
    @ResponseBody
    public String add(MessageForm form) throws Exception {

        // 输入验证
        if (StringUtil.isEmpty(form.getTitle()) || StringUtil.isEmpty(form.getMessage()) || StringUtil.isEmpty(form.getType())) {
            log.error("消息标题，内容以及消息类型是必须项！");
            return Return.error(Constants.MSG_FORM_ARGUMENTS_ERROR);
        }
        List<Long> mem_id_list = new ArrayList<Long>();
        if ("1".equals(form.getType())) {
            String mem_ids = form.getMem_ids();
            if (StringUtil.isEmpty(mem_ids)) {
                // 未指定发送会员
                return Return.error(Constants.MSG_FORM_ARGUMENTS_ERROR);
            }
            String[] ids = mem_ids.split(",");
            for (int i = 0; i < ids.length; i++) {
                if (StringUtil.isEmpty(ids[i].trim())) {
                    continue;
                }
                Long id = Long.parseLong(ids[i]);
                mem_id_list.add(id);
            }
        } else if ("0".equals(form.getType())) {
            mem_id_list = memberService.getAllEnableMembers();
        }

        Message message = new Message();
        message.setTitle(form.getTitle());
        message.setMessage(form.getMessage());
        message.setSend_time(form.getSend_time() != null ? form.getSend_time() : new Date());
        message.setType(form.getType());
        EntityUtil.setBaseProps4Update(message);
        messageService.addMessage(mem_id_list, message);

        return Return.success(Constants.MSG_SAVE_SUCCESS);
    }

    /**
     * 
     * @Title: detail
     * @Description: 查看消息详情
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/detail")
    @ResponseBody
    public String detail(@RequestParam("id") Long id) throws Exception {
        // 输入验证
        if (id == null) {
            log.error("用户Id是必填项！");
            return Return.error(Constants.MSG_FORM_ARGUMENTS_ERROR);
        }
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("id", id);
        Message message = messageService.getSingle(map);
        if (message == null) {
            return Return.error(Constants.MSG_MESSAGE_RETRIEVE_ERROR);
        }
        return Return.bean2String(message);
    }
}
