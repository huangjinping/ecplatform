<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/ecplatform/css/message/list.css" />
<jsp:include page="../common/common.jsp"></jsp:include>
<title>消息列表</title>

</head>
<body>
  <table id="dataList"></table>

  <div id="tb" style="padding: 5px; height: auto">
    <div>
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="showAddMessage()">新增消息</a>
    </div>
    <div style="margin-top: 5px">
      <div style="overflow: auto">
        <div class="lbl-message-name">
          标题：<input class="easyui-validatebox" style="width: 100px; height: 18px" id="name" name="name" />
        </div>
        <div class="lbl-message-cage">
          内容：<input class="easyui-validatebox" style="width: 100px; height: 18px" id="cage" name="cage" />
        </div>
        <div class="lbl-message-binding">
          类型：<select class="easyui-combobox" data-options="editable:false" style="width: 100px" name="binding" id="binding">
            <option value="" selected="selected">所有</option>
            <option value="1">指定用户</option>
            <option value="0">所有用户</option>
          </select>
        </div>
        <div class="lbl-message-search">
          <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearchForm('dataList');">查询</a>&nbsp;
        </div>
        <div class="lbl-message-reset">
          <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="resetSearch();">重置</a>
        </div>

      </div>
    </div>
  </div>
  <!-- 新增、修改弹出窗 -->
  <div id="dlg_add" class="easyui-dialog" style="width: 500px; height: 400px; padding: 10px 20px" closed="true" buttons="#dlg-buttons" modal="true">
    <form id="message_add_form" method="post" novalidate>
      <input type="hidden" value="" id="messageId" />
      <dl id="typeDl" class="">
        <dt class="message-item-title">
          <label>发送类型：</label>
        </dt>
        <dd>
          <select class="easyui-combobox" data-options="editable:false" style="width: 100px" name="detail_type" id="detail_type">
            <option value="1">指定用户</option>
            <option value="0" selected="selected">所有用户</option>
          </select>
        </dd>
      </dl>
      <dl id="nameDl" class="display:none;">
        <dt class="message-item-title">
          <span class="mark-important">*</span> <label>用户名：</label>
        </dt>
        <dd>
          <input class="easyui-validatebox" maxlength="30" data-options="required:true" id="detail_name" name="detail_name" />
        </dd>
      </dl>
      <dl id="titleDl" class="">
        <dt class="message-item-title">
          <span class="mark-important">*</span> <label>标题：</label>
        </dt>
        <dd>
          <input class="easyui-validatebox" maxlength="100" data-options="required:true" id="detail_title" name="detail_title" />
        </dd>
      </dl>
      <dl id="messageDl" class="">
        <dt class="message-item-title">
          <label>消息内容：</label>
        </dt>
        <dd>
          <input class="easyui-validatebox" id="detail_message" name="detail_message" />
        </dd>
      </dl>
    </form>
  </div>

  <!-- 新增、修改窗口底部按钮 -->
  <div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveMessage()">保存</a> <a href="javascript:void(0)" class="easyui-linkbutton"
      iconCls="icon-cancel" onclick="javascript:$('#dlg_add').dialog('close')">取消</a>
  </div>

  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/ecplatform/js/message/message.js"></script>

</body>
</html>