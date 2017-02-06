package cn.cooperlink.ecplatform.petstore.camera.web.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cooperlink.ecplatform.petstore.camera.entity.Camera;
import cn.cooperlink.ecplatform.petstore.camera.service.CameraService;
import cn.cooperlink.framework.core.Constants;
import cn.cooperlink.framework.core.Return;
import cn.cooperlink.framework.core.util.EntityUtil;
import cn.cooperlink.framework.core.util.PagerParamHandler;
import cn.cooperlink.util.Md5;
import cn.cooperlink.util.StringUtil;

@Controller
@RequestMapping("/camera")
public class ServerCameraController {
    protected final Log log = LogFactory.getLog(getClass());

    @Resource
    private CameraService cameraService;

    @RequestMapping("/list")
    public String index() {
        return "/petstore/camera/list";
    }

    @RequestMapping("/page/add")
    public String detailList() {
        return "/petstore/camera/detail";
    }

    @RequestMapping("/page/update")
    public String editDetailList(Long id, Model model) throws Exception {
        Camera camera = cameraService.getSingle(id);
        model.addAttribute("camera", camera);
        return "/petstore/camera/detail";
    }

    // 获取摄像头列表
    @RequestMapping("/get")
    @ResponseBody
    public String getCameraList(@RequestParam Map<String, Object> paramMap) throws Exception {
        PagerParamHandler.convertFormat(paramMap);
        return cameraService.findByCondition2PagerString(paramMap);
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add(Camera camera) throws Exception {
        // 输入验证
        if (StringUtil.isEmpty(camera.getIP()) || camera.getPort() == null) {
            log.error("IP地址和端口号是必填项！");
            return Return.error(Constants.MSG_FORM_ARGUMENTS_ERROR);
        }

        Camera cam = new Camera();
        cam.setIP(camera.getIP());
        cam.setPort(camera.getPort());
        cam.setAuth_username(camera.getAuth_username());
        cam.setAuth_password(Md5.encodeByMd5(camera.getAuth_password()));
        cam.setBinding(false);
        cam.setCage(camera.getCage());
        cam.setDescription(camera.getDescription());
        cam.setEnable(true);
        cam.setName(camera.getName());
        EntityUtil.setBaseProps4Save(cam);
        cameraService.save(cam);

        return Return.bean2String(cam);
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(Camera camera) throws Exception {
        // 输入验证
        if (camera.getId() == null || StringUtil.isEmpty(camera.getIP()) || camera.getPort() == null) {
            log.error("摄像头Id,IP和端口号是必填项！");
            return Return.error(Constants.MSG_FORM_ARGUMENTS_ERROR);
        }

        Camera cam = new Camera();
        cam.setId(camera.getId());
        cam.setIP(camera.getIP());
        cam.setPort(camera.getPort());
        cam.setAuth_username(camera.getAuth_username());
        cam.setAuth_password(Md5.encodeByMd5(camera.getAuth_password()));
        cam.setCage(camera.getCage());
        cam.setDescription(camera.getDescription());
        cam.setName(camera.getName());
        EntityUtil.setBaseProps4Update(cam);
        cameraService.update(cam);

        return Return.success(Constants.MSG_UPDATE_SUCCESS);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam("id") Long id) throws Exception {
        // 输入验证
        if (id == null) {
            log.error("摄像头Id是必填项！");
            return Return.error(Constants.MSG_FORM_ARGUMENTS_ERROR);
        }

        if(cameraService.deleteCamera(id)){
            return Return.success(Constants.MSG_UPDATE_SUCCESS);
        }else{
            return Return.error(Constants.MSG_CAMERA_DELETE_ERROR);
        }
    }
    
    @RequestMapping("/detail")
    @ResponseBody
    public String detail(@RequestParam("id") Long id) throws Exception {
     // 输入验证
        if (id == null) {
            log.error("摄像头Id是必填项！");
            return Return.error(Constants.MSG_FORM_ARGUMENTS_ERROR);
        }
        Camera camera = cameraService.getSingle(id);
        if(camera!=null){
            return Return.bean2String(camera);
        }else{
            return Return.error(Constants.MSG_CAMERA_RETRIEVE_ERROR);
        }
    }
}
