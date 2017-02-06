<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../common/common.jsp"></jsp:include>
<title>Main</title>
</head>
<body>
	<div id="toolbar">
        <table cellpadding="0" cellspacing="0">
            <tr>
                <td style="padding-left:2px">
        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addRole()">新增</a>
        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateRole()">修改</a>
        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteRole()">删除</a>
        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-detail" plain="true" onclick="detailRole()">查看详情</a>
                </td>
                <td><div class="datagrid-btn-separator"></div></td>
                <td style="padding-left:2px">
                    <input class="easyui-searchbox" data-options="prompt:'查询条件'" style="width:150px"></input>
                </td>
            </tr>
        </table>
    </div>
    <!-- 主列表 -->
    <table id="dg" class="easyui-datagrid" data-options="
                rownumbers:true,
                fit:true,
                border:false,
                singleSelect:true,
                autoRowHeight:false,
                pagination:true,toolbar:'#toolbar',
                url:'${pageContext.request.contextPath}/system/role/query',
                onLoadError: listLoadError,
                onLoadSuccess: listLoadSuccess,
                pageSize:20" >
        <thead>
            <tr>
			       <th field="roleName" width="260">角色名称</th>
			       <th field="description" width="460">描述</th>
            </tr>
        </thead>
    </table>
    <!-- 新增、修改弹出窗 -->
    <div id="dlg" class="easyui-dialog" style="width:400px;height:450px;padding:10px 20px 0px 20px"
            closed="true" buttons="#dlg-buttons" modal="true">
        <form id="fm" method="post" novalidate>
        	<table>
        		<tr class="fitem">
        			<td><label>角色名称:</label></td>
        			<td><input name="roleName" style="width:270px;" class="easyui-validatebox" required="true">
        			</td>
        			<!-- 
        			<td><label>角色编码:</label></td>
        			<td><input name="roleCode">
        			</td>
        			 -->
        		</tr>
        		<tr class="fitem">
        			<td><label>描述:</label></td>
        			<td>
        				<input class="easyui-textbox" name="description" 
        					data-options="multiline:true" style="width:270px;height:50px;"></input>
        			</td>
        		</tr>
        		<tr>
        			<td colspan="2">
        				<fieldset style="border:1px solid #99BBE8; margin:10px 0; padding:5px; height: 235px; overflow:auto; ">
	        				<legend>系统功能</legend>
	        				<ul id="svrsTree" class="easyui-tree" 
	        					data-options="
	        						method:'post',
	        						animate:true,
	        						checkbox:true"
	     					></ul>
     					</fieldset>
        			</td>
        		</tr>
        	</table>
			<input id="funcIds" name="funcIds" type="hidden">
        </form>
    </div>
    <!-- 查看详情弹出窗 -->
    <div id="detail-dlg" class="easyui-dialog" style="width:400px;height:450px;padding:10px 20px 0px 20px"
            closed="true" buttons="#detail-dlg-buttons" modal="true">
       	<table id="detail-table">
       		<tr class="fitem">
       			<td width="70px"><label>角色名称:</label></td>
       			<td><label id="d-roleName"></label>
       			</td>
       		</tr>
       		<tr class="fitem">
       			<td><label>描述:</label></td>
       			<td><label id="d-description"></label>
       			</td>
       		</tr>
       		<tr>
       			<td colspan="2" width="340px">
       				<fieldset style="border: 1px solid #99BBE8; margin:10px 0; padding:5px; height: 290px; overflow:auto; ">
        				<legend>系统功能</legend>
        				<ul id="svrsTreeDetail" class="easyui-tree" 
        					data-options="
        						method:'post',
        						animate:true"
     					></ul>
    					</fieldset>
       			</td>
       		</tr>
       	</table>
    </div>
    <!-- 新增、修改窗口底部按钮 -->
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveRole()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>    
    <!-- 查看详情底部按钮 -->
    <div id="detail-dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#detail-dlg').dialog('close')">取消</a>
    </div>
    <!-- 控制代码，可抽取到js文件中 -->
    <script type="text/javascript">
    	$('#svrsTree').tree({
    		onLoadError: function(response) {
    			MyTool.doLoginFailure(response.responseText);
    		}
    	});
        var url;
        var vsrsTreeUrl = getUrl('/tenant/svrPurchase/getPurchasedSvrTree');
        // 新增
        function addRole() {
            $('#fm').form('clear');
            $('#dlg').dialog('open').dialog('setTitle','新增');
			$('#svrsTree').tree({url:vsrsTreeUrl});
        	url = getUrl('/system/role/save');
        }
        // 修改
        function updateRole() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#dlg').dialog('open').dialog('setTitle','修改');
                $('#fm').form('load',row);
                $('#svrsTree').tree({url:(vsrsTreeUrl + '?roleId=' + row.id)});
                url = getUrl('/system/role/update?id=' + row.id);
                return;
            }
            $.messager.alert('提示','请选择要修改的行！');
        }
        // 提交保存
        function saveRole() {
        	var ids = getChecked();
        	if (ids) {
        		$('#funcIds').val(ids);
        	}
        	var mask = {};
            $('#fm').form('submit', {
                url: url,
                onSubmit: function() {
                	if ($(this).form('validate')) {
                		mask = new Mask({maskMsg: '正在保存，请等待。。。'}).show();
                		return true;
                	}
                    return false;
                },
                success: function(responseText) {
                	mask.hide();
                    if (MyTool.parseResponse(responseText, 
                    		{successMsg:'保存成功。',failureMsg:'保存失败。'})) 
                    {
                        $('#dlg').dialog('close');
                        $('#dg').datagrid('reload');
                    }
                }
            });
        }
        function getChecked(){
            var nodes = $('#svrsTree').tree('getChecked');
            var ids = '';
            for(var i=0; i<nodes.length; i++){
                if (ids != '') ids += ',';
                ids += nodes[i].id;
            }
            return ids;
        }
        // 删除
        function deleteRole() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $.messager.confirm('删除','确定要删除所选中的数据行吗?',function(r) {
                    if (r) {
                    	var mask = new Mask({maskMsg: '正在处理，请等待。。。'}).show();
                        $.post(
                       		'${pageContext.request.contextPath}/system/role/delete',
                       		{id:row.id},
                       		function(result){
                       			mask.hide();
                       			if (MyTool.parseResponse(result)) {
	                                $('#dg').datagrid('reload');
                       			}
                        	},
                       		'json'
                       	);
                    }
                });
                return;
            }
            $.messager.alert('提示','请选择要删除的行！');
        }
        // 查看详情
        function detailRole() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#detail-dlg').dialog('open').dialog('setTitle','详情');
                $('#svrsTreeDetail').tree({url:getUrl('/system/role/getFuncTreeByRole?roleId=' + row.id)});
                for (var prop in row) {
                    $('#d-' + prop).html(row[prop]);
                }
                return;
            }
        }
        function listLoadError(data) {
        }
        function listLoadSuccess(data) {
    		if (!MyTool.doLoginFailure(data)) {
    			return false;
    		}
        	if (data.success == 'false') {
        		if (data.msg) {
        			Msg.error(MyLocal.BaseMainPaeg.list.onLoadSuccess_exceptionOrNodataMsg);
        		} else {
        			Msg.error(MyLocal.BaseMainPaeg.list.onLoadSuccess_exceptionOrNodataMsg);
        		}
        	}
        }
    </script>
</body>
</html>