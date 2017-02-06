/* **************************************************************
 *
 * 文件名称：MobileCameraController.java
 *
 * 包含类名：cn.cooperlink.ecplatform.petstore.pet.mobile.controller.MobileCameraController
 * 创建日期：2014.08.07
 * 创建作者：dalvik
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.ecplatform.petstore.camera.mobile.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cooperlink.ecplatform.petstore.camera.entity.Camera;
import cn.cooperlink.ecplatform.petstore.camera.service.CameraService;
import cn.cooperlink.ecplatform.security.mobile.BaseMobileAuthentication;
import cn.cooperlink.ecplatform.security.mobile.MobileAuthHelper;
import cn.cooperlink.ecplatform.security.mobile.MobileAuthentication;
import cn.cooperlink.ecplatform.security.mobile.MobileAuthenticationSub;
import cn.cooperlink.framework.core.BaseController;
import cn.cooperlink.framework.core.Return;

/**
 * Controller 类
 * 
 * 创建日期：2014.08.07 创建作者：dalvik
 */
@Controller
@RequestMapping("/mobile/camera")
public class MobileCameraController extends BaseController {

    protected final Log log = LogFactory.getLog(getClass());

    @Resource
    private CameraService cameraService;

    @RequestMapping("/list")
    @ResponseBody
    public String getMyCamera() {
        BaseMobileAuthentication bma = MobileAuthHelper.getAuthInfo();
        MobileAuthenticationSub subAuth = null;
        MobileAuthentication auth = null;
        // 主账号会员Id
        Long mem_id;
        if (bma.isSubMember()) {
            subAuth = (MobileAuthenticationSub) bma;
            mem_id = subAuth.getParent_member().getMem_id();
        } else {
            auth = (MobileAuthentication) bma;
            mem_id = auth.getMem_id();
        }

        List<Camera> list = cameraService.getMyCamera(mem_id);
        return Return.list2String(list);
    }
}
