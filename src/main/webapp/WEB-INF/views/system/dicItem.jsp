<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../common/common.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources//ecplatform/js/system/dicItem.js"></script>
<title>字典维护</title>
<style type="text/css">
	.my-form-input {
		width: 360px;
	}
	.hidden {
		display:none;
	}
</style>
</head>
<body class="easyui-layout">
    <!-- 左侧树 -->    
	<div id="dicList" data-options="region:'west', split:true, border:false" style="width:350px;">
		<div id="dicList-toolbar">
	        <table cellpadding="0" cellspacing="0">
	            <tr>
	                <td style="padding-left:1px">
	                    <input class="easyui-searchbox" data-options="searcher:queryDicWithParams, prompt:'字典名称'" style="width:339px"></input>
	                </td>
	            </tr>
	        </table>
	    </div>
	</div>
    
    <!-- 主列表 -->
    <div data-options="region:'center',border:false">
    <div id="list">
		<!-- 列表上部工具栏 -->
		<div id="list-toolbar">
	        <table cellpadding="0" cellspacing="0">
	            <tr>
	                <td style="padding-left:2px">
	        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openAddFormWindow()">新增</a>
	        			<a id="addChildBtn" style="display:none" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openAddChildFormWindow()">新增子项</a>
	        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="openUpdateFormWindow()">修改</a>
	        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteRecord()">删除</a>
	                </td>
	                <td><div class="datagrid-btn-separator"></div></td>
	                <td style="padding-left:2px">
	                    <input class="easyui-searchbox" data-options="searcher:queryWithParams, prompt:'显示名称'" style="width:150px"></input>
	                </td>
	            </tr>
	        </table>
	    </div>
    </div>
    </div>
    <!-- 新增、修改弹出窗 -->
    <div id="form-dlg" class="easyui-dialog" style="width:500px;height:400px;padding:10px 20px"
            closed="true" buttons="#form-dlg-btns" modal="true">
        <form id="fm" method="post" novalidate>
        	<table>
        		<tr class="fitem" id="parentRow" style="display:none">
        			<th><label>父级路径</label></th>
        			<td><label id="parentNameFullpath"></label></td>
        		</tr>
        		<tr class="fitem">
        			<th><label>显示名称:</label></th>
        			<td><input name="displayName" class="my-form-input easyui-validatebox" required="true"/></td>
        		</tr>
        		<tr class="fitem">
        			<th><label>键值:</label></th>
        			<td><input name="itemValue" class="my-form-input easyui-validatebox" required="true"/></td>
        		</tr>
        		<tr class="fitem">
        			<th><label>图标:</label></th>
        			<td><input name="iconCls" class="my-form-input easyui-validatebox"/></td>
        		</tr>
        		<tr class="fitem">
        			<th><label>排序号:</label></th>
        			<td><input name="orderField" class="my-form-input" class="easyui-numberbox"/></td>
        		</tr>
        		<tr class="fitem">
        			<th><label>描述:</label></th>
        			<td><textarea name="description" class="my-form-input" style="height:50px;"></textarea></td>
        		</tr>
        	</table>
        	<input name="parentId" type="hidden" value="0"/>
        	<input name="dirId" type="hidden"/>
        	<input name="idFullpath" type="hidden"/>
        	<input name="nameFullpath" type="hidden"/>
        	<input name="validity" type="hidden"/>
        	<input name="oldNameFullpath" type="hidden">
        </form>
    </div>
    <!-- 新增、修改窗口底部按钮 -->
    <div id="form-dlg-btns">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="submitForm()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#form-dlg').dialog('close')">取消</a>
    </div>  
    
</body>
</html>