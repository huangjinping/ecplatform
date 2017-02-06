<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/ecplatform/css/member/list.css" />
<jsp:include page="../common/common.jsp"></jsp:include>
<title>会员列表</title>

</head>
<body>
  <table id="dataList"></table>

  <div id="tb" style="padding: 5px; height: auto">
    <div>
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="showAddMember()">新增会员</a>
    </div>
    <div style="margin-top: 5px">
      <div style="overflow: auto">
        <div class="lbl-member-name">
          会员姓名：<input class="easyui-validatebox" style="width: 100px; height: 18px" id="name" name="name" />
        </div>
        <div class="lbl-member-mobile">
          手机号码：<input class="easyui-validatebox" style="width: 100px; height: 18px" id="mobile" name="mobile" />
        </div>
        <div class="lbl-member-create">
          创建时间：<input class="easyui-datebox" data-options="editable:false" style="width: 100px" id="start_create_time" name="start_create_time" validType="dateTime['#start_create_time','#end_create_time']" />
          - <input class="easyui-datebox" data-options="editable:false" style="width: 100px" id="end_create_time" name="end_create_time" validType="dateTime['#start_create_time','#end_create_time']" />
        </div>
        <div class="lbl-member-status">
          会员状态：<select class="easyui-combobox" data-options="editable:false" style="width: 100px" name="enable" id="enable">
            <option value="">所有</option>
            <option value="1" selected="selected">可用</option>
            <option value="0">停用</option>
          </select>
        </div>
        <div class="lbl-member-search">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search"  plain="true" onclick="doSearchForm('dataList');">查询</a>&nbsp; 
        </div>
        <div class="lbl-member-reset">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true"  onclick="resetSearch();">重置</a> 
        </div>
      
      </div>
    </div>
  </div>
  <div id="dialog_add">正在加载...</div>
  <div id="dialog_detail">正在加载...</div>

    <!-- 新增、修改弹出窗 -->
    <div id="dlg" class="easyui-dialog" style="width:500px;height:400px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons" modal="true">
        <form id="fm" method="post" novalidate>
        	<table>
        		<tr class="fitem">
        			<td><label>父级功能:</label></td>
        			<td><input id="parentId" name="parentId" class="easyui-numberbox">
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>功能名称:</label></td>
        			<td><input name="funcName" class="easyui-validatebox" required="true">
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>功能编码:</label></td>
        			<td><input name="funcCode" class="easyui-validatebox" required="true">
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>访问路径:</label></td>
        			<td><input name="url">
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>功能图标:</label></td>
        			<td><input name="iconCls">
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>内建功能:</label></td>
        			<td><input name="isBuildIn" class="easyui-numberbox">
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>排序号:</label></td>
        			<td><input name="orderField">
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>类型:</label></td>
        			<td><input name="type" class="easyui-numberbox">
        			</td>
        		</tr>
        	</table>
        </form>
    </div>
    
    <!-- 查看详情弹出窗 -->
    <div id="detail-dlg" class="easyui-dialog" style="width:500px;height:400px;padding:10px 20px"
            closed="true" buttons="#detail-dlg-buttons" modal="true">
       	<table id="detail-table">
       		<tr class="fitem">
       			<td><label>父级功能:</label></td>
       			<td><label id="d-parentId"></label>
       			</td>
       		</tr>
       		<tr class="fitem">
       			<td><label>功能名称:</label></td>
       			<td><label id="d-funcName"></label>
       			</td>
       		</tr>
       		<tr class="fitem">
       			<td><label>功能编码:</label></td>
       			<td><label id="d-funcCode"></label>
       			</td>
       		</tr>
       		<tr class="fitem">
       			<td><label>访问路径:</label></td>
       			<td><label id="d-url"></label>
       			</td>
       		</tr>
       		<tr class="fitem">
       			<td><label>功能图标:</label></td>
       			<td><label id="d-iconCls"></label>
       			</td>
       		</tr>
       		<tr class="fitem">
       			<td><label>内建功能:</label></td>
       			<td><label id="d-isBuildIn"></label>
       			</td>
       		</tr>
       		<tr class="fitem">
       			<td><label>排序号:</label></td>
       			<td><label id="d-orderField"></label>
       			</td>
       		</tr>
       		<tr class="fitem">
       			<td><label>类型:</label></td>
       			<td><label id="d-type"></label>
       			</td>
       		</tr>
       	</table>
    </div>
    <!-- 新增、修改窗口底部按钮 -->
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveFunction()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>
    <!-- 查看详情底部按钮 -->
    <div id="detail-dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#detail-dlg').dialog('close')">取消</a>
    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/ecplatform/js/member/member.js"></script>

</body>
</html>