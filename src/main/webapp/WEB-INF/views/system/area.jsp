<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../common/common.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/ecplatform/js/system/area.js"></script>
<title>省市区域管理</title>
</head>
<body>
	<!-- 列表上部工具栏 -->
	<div id="list-toolbar">
        <table cellpadding="0" cellspacing="0">
            <tr>
                <td style="padding-left:2px">
        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openAddFormWindow()">新增</a>
        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="openUpdateFormWindow()">修改</a>
        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteRecord()">删除</a>
        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-detail" plain="true" onclick="showDetail()">查看详情</a>
                </td>
                <td><div class="datagrid-btn-separator"></div></td>
                <td style="padding-left:2px">
                    <input class="easyui-searchbox" data-options="searcher:queryWithParams, prompt:'查询条件'" style="width:150px"></input>
                </td>
            </tr>
        </table>
    </div>
    <!-- 主列表 -->
    <div id="list"></div>
    
    <!-- 新增、修改弹出窗 -->
    <div id="form-dlg" class="easyui-dialog" style="width:500px;height:400px;padding:10px 20px"
            closed="true" buttons="#form-dlg-btns" modal="true">
        <form id="fm" method="post" novalidate>
        	<table>
        		<tr class="fitem">
        			<td><label>上级区域:</label></td>
        			<td><input name="parentId" class="my-form-input">
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>区域名称:</label></td>
        			<td><input name="name" class="easyui-validatebox my-form-input" required="true">
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>区域编码:</label></td>
        			<td><input name="code" class="easyui-validatebox my-form-input" required="true">
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>排序号:</label></td>
        			<td><input name="orderField" class="easyui-numberbox my-form-input">
        			</td>
        		</tr>
        	</table>
        </form>
    </div>
    <!-- 新增、修改窗口底部按钮 -->
    <div id="form-dlg-btns">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="save()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#form-dlg').dialog('close')">取消</a>
    </div>  

    <!-- 查看详情  -->
    <div id="detail-dlg" class="easyui-dialog" style="width:500px;height:400px;padding:10px 20px"
            closed="true" buttons="#detail-dlg-btns" modal="true">
       	<table id="detail-table">
	       	<tbody>
	       		<tr>
	       			<td><label>上级区域:</label></td>
	       			<td><label id="d-parentId"></label></td>
	       		</tr>
	       		<tr>
	       			<td><label>区域名称:</label></td>
	       			<td><label id="d-name"></label></td>
	       		</tr>
	       		<tr>
	       			<td><label>区域编码:</label></td>
	       			<td><label id="d-code"></label></td>
	       		</tr>
	       		<tr>
	       			<td><label>排序号:</label></td>
	       			<td><label id="d-orderField"></label></td>
	       		</tr>
			</tbody>
       	</table>
    </div>  
    <!-- 查看详情-底部按钮 -->
    <div id="detail-dlg-btns">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#detail-dlg').dialog('close')">取消</a>
    </div>
</body>
</html>