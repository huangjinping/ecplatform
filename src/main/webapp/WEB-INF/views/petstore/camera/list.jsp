<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/ecplatform/css/petstore/camera/list.css" />
<jsp:include page="../../common/common.jsp"></jsp:include>
<title>摄像头列表</title>

</head>
<body>
  <table id="dataList"></table>

  <div id="tb" style="padding: 5px; height: auto">
    <div>
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="showAddCamera()">新增摄像头</a>
    </div>
    <div style="margin-top: 5px">
      <div style="overflow: auto">
        <div class="lbl-camera-name">
          名称：<input class="easyui-validatebox" style="width: 100px; height: 18px" id="name" name="name" />
        </div>
        <div class="lbl-camera-cage">
          寄养笼：<input class="easyui-validatebox" style="width: 100px; height: 18px" id="cage" name="cage" />
        </div>
        <div class="lbl-camera-binding">
          是否绑定：<select class="easyui-combobox" data-options="editable:false" style="width: 100px" name="binding" id="binding">
            <option value="" selected="selected">所有</option>
            <option value="1">已绑定</option>
            <option value="0">未绑定</option>
          </select>
        </div>
        <div class="lbl-camera-search">
          <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearchForm('dataList');">查询</a>&nbsp;
        </div>
        <div class="lbl-camera-reset">
          <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="resetSearch();">重置</a>
        </div>

      </div>
    </div>
  </div>
  <!-- 新增、修改弹出窗 -->
  <div id="dlg_add" class="easyui-dialog" style="width: 500px; height: 400px; padding: 10px 20px" closed="true" buttons="#dlg-buttons" modal="true">
    <form id="camera_add_form" method="post" novalidate>
      <input type="hidden" value="" id="cameraId" />
      <dl id="nameDl" class="">
        <dt class="camera-item-title">
          <label>名称：</label>
        </dt>
        <dd>
          <input class="easyui-validatebox" maxlength="10" id="detail_name" name="detail_name" />
        </dd>
      </dl>
      <dl id="IPDl" class="">
        <dt class="camera-item-title">
          <span class="mark-important">*</span> <label>IP：</label>
        </dt>
        <dd>
          <input class="easyui-validatebox" maxlength="30" data-options="required:true" id="detail_IP" name="detail_IP" />
        </dd>
      </dl>
      <dl id="portDl" class="">
        <dt class="camera-item-title">
          <span class="mark-important">*</span> <label>端口号：</label>
        </dt>
        <dd>
          <input class="easyui-numberbox" maxlength="10" data-options="required:true,min:0,max:99999999" id="detail_port" name="detail_port" />
        </dd>
      </dl>
      <dl id="authUserDl" class="">
        <dt class="camera-item-title">
          <label>用户名：</label>
        </dt>
        <dd>
          <input class="easyui-validatebox" id="detail_username" name="detail_username" />
        </dd>
      </dl>
      <dl id="authPasswdDl" class="">
        <dt class="camera-item-title">
          <label>密码：</label>
        </dt>
        <dd>
          <input id="detail_passwd" name="detail_passwd" class="easyui-validatebox" type="password" />
        </dd>
      </dl>
      <dl id="cageDl" class="">
        <dt class="member-item-title">
          <label>宠物笼：</label>
        </dt>
        <dd>
          <input class="easyui-validatebox" maxlength="10" id="detail_cage" name="detail_cage" />
        </dd>
      </dl>
    </form>
  </div>

  <!-- 新增、修改窗口底部按钮 -->
  <div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveCamera()">保存</a> <a href="javascript:void(0)" class="easyui-linkbutton"
      iconCls="icon-cancel" onclick="javascript:$('#dlg_add').dialog('close')">取消</a>
  </div>

  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/ecplatform/js/petstore/camera/camera.js"></script>

</body>
</html>