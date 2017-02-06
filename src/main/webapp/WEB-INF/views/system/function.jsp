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
        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addFunction()">新增</a>
        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateFunction()">修改</a>
        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteFunction()">删除</a>
        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-detail" plain="true" onclick="detailFunction()">查看详情</a>
                </td>
                <td><div class="datagrid-btn-separator"></div></td>
                <td style="padding-left:2px">
                    <input class="easyui-searchbox" data-options="searcher:queryWithParams, prompt:'功能名称'" style="width:150px"></input>
                </td>
            </tr>
        </table>
    </div>
    <!-- 主列表 -->
    <table id="dg" class="easyui-treegrid" data-options="
    			url:'${pageContext.request.contextPath}/system/function/query',
                rownumbers:true,
                fit:true,
                border:false,
                animate:true,
                singleSelect:true,
                autoRowHeight:false,
                toolbar:'#toolbar',
                idField: 'id',
                treeField:'funcName'">
        <thead>
            <tr>
			       <th field="funcName" width="160">功能名称</th>
			       <th field="funcCode" width="160">功能编码</th>
			       <th field="url" width="160">访问路径</th>
			       <th field="iconCls" width="160">功能图标</th>
			       <th field="isBuildIn" width="160" formatter="formatIsBuildIn">内建功能</th>
			       <th field="type" width="160" formatter="formatType">类型</th>
			       <th field="id" width="160">类型</th>
            </tr>
        </thead>
    </table>
    <!-- 新增、修改弹出窗 -->
    <div id="dlg" class="easyui-dialog" style="width:500px;height:400px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons" modal="true">
        <form id="fm" method="post" novalidate>
        	<table>
        		<tr class="fitem">
        			<td><label>父级功能:</label></td>
        			<td>
        				<label id="parentFuncName"></label>
        				<input id="parentId" name="parentId" type="hidden">
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
        			<td>
        				<input id="radio_1" type="radio" name="isBuildIn" value="1"/> <label for="radio_1">是</label>
        				<input id="radio_2" type="radio" name="isBuildIn" value="2"/> <label for="radio_2">否</label>
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>类型:</label></td>
        			<td>
        				<input id="radio_3" type="radio" name="type" value="1"/> <label for="radio_3">Menu Bar</label>
        				<input id="radio_4" type="radio" name="type" value="2"/> <label for="radio_4">目录</label>
        				<input id="radio_5" type="radio" checked="checked" name="type" value="3"/> <label for="radio_5">菜单</label>
        			</td>
        		</tr>
        	</table>
        	<input type="hidden" name="idFullpath"/>
        	<input type="hidden" name="nameFullpath"/>
        	<input type="hidden" name="oldNameFullpath"/>
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
    <!-- 控制代码，可抽取到js文件中 -->
    <script type="text/javascript">
        var url;
        var isNew;
        // 新增
        function addFunction() {
        	isNew = true;
        	var row = $('#dg').datagrid('getSelected');
            $('#fm').form('clear');
            var parentId = 0;
            var idFullpath = '';
            var parentNameFullpath = '/';
        	if (row) {
        		parentId = row.id;
        		parentNameFullpath = row.nameFullpath;
        		idFullpath = row.idFullpath;
        	}
        	document.getElementById('radio_1').checked = 'checked';
        	document.getElementById('radio_5').checked = 'checked';
        	document.getElementById('parentId').value = parentId;
            $('#parentFuncName').html(parentNameFullpath);
            $('input[name=idFullpath]').val(idFullpath);
        	url = '${pageContext.request.contextPath}/system/function/save';
            $('#dlg').dialog('open').dialog('setTitle','新增');
        }
        // 修改
        function updateFunction() {
        	isNew = false;
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#fm').form('load',row);
                $('input[name=oldNameFullpath]').val(row.nameFullpath);
                $('#parentFuncName').html(getParentNameFullpath(row.nameFullpath));
                url = getUrl('/system/function/update?id=' + row.id);
                $('#dlg').dialog('open').dialog('setTitle','修改');
                return;
            }
            $.messager.alert('提示','请选择要修改的行！');
        }
        
        function getParentNameFullpath(nameFullpath) {
        	var pnf = nameFullpath || '/';
        	if (pnf) {
        		pnf = pnf.substring(0, pnf.lastIndexOf('/'));
        	}
        	if (!pnf) {
        		return '/';
        	}
        	return pnf;
        }
        
        // 提交保存
        function saveFunction() {
        	var pnf = $('#parentFuncName').html();
       		if (pnf != '/') {
       			pnf = pnf + '/'; 
       		}
       		pnf += $('input[name=funcName]').val();
       		$('input[name=nameFullpath]').val(pnf);
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
                success: function(respText) {
                	mask.hide();
                    if (MyTool.parseResponse(respText, 
                    		{successMsg:'保存成功。',failureMsg:'保存失败。'})) 
                    {
                        $('#dlg').dialog('close');
                        $('#dg').treegrid('reload');
                    }
                }
            });
        }
        // 删除
        function deleteFunction() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $.messager.confirm('删除','确定要删除所选中的数据行吗?',function(r) {
                    if (r) {
                    	var mask = new Mask({maskMsg: '正在处理，请等待。。。'}).show();
                        $.post(
                       		'${pageContext.request.contextPath}/system/function/delete',
                       		{id:row.id},
                       		function(result){
                       			mask.hide();
                       			if (MyTool.parseResponse(result)) {
	                                $('#dg').treegrid('unselect', row.id);
	                                $('#dg').treegrid('reload');
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
        function detailFunction() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#detail-dlg').dialog('open').dialog('setTitle','详情');
                for (var prop in row) {
                    $('#d-' + prop).html(row[prop]);
                }
                return;
            }
        }
        
        function formatIsBuildIn(value) {
        	if (value == 1) {
        		return '是';
        	}
        	return '否';
        }
        
        function formatType(value) {
        	if (value == 1) {
        		return 'Menu Bar';
        	} else if (value == 2) {
            	return '目录';
        	} else if (value == 3) {
            	return '菜单';
        	}
        	return '';
        }
        
        /**
         * 条件查询，刷新datagrid
         * @returns {Boolean}
         */
        function queryWithParams(val) {
        	var options = $('#dg').treegrid('options');
        	var queryParams=options.queryParams;
        	queryParams.funcName = val;
        	$('#dg').treegrid('load');
        }
    </script>
</body>
</html>