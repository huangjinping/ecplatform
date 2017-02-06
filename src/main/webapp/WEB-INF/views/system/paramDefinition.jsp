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
        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addParamDefinition()">新增</a>
        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateParamDefinition()">修改</a>
        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteParamDefinition()">删除</a>
        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-detail" plain="true" onclick="detailParamDefinition()">查看详情</a>
                </td>
                <td><div class="datagrid-btn-separator"></div></td>
                <td style="padding-left:2px">
                    <input class="easyui-searchbox" data-options="searcher:queryWithParams, prompt:'参数名'" style="width:150px"></input>
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
                pageSize:20" url="${pageContext.request.contextPath}/system/paramDefinition/query">
        <thead>
            <tr>
			       <th field="id" width="60">ID</th>
			       <th field="paramName" width="160">参数名(key)</th>
			       <th field="paramDisplayName" width="160">参数名(中文)</th>
			       <th field="description" width="260">参数说明</th>
			       <th data-options="field:'valueType',width:100,formatter:formatValueType">参数值类型</th>
			       <th data-options="field:'paramType', width:150, formatter:formatParamType">所属分组</th>
			       <th data-options="field:'isEditable', width:60, formatter:formatIsEditable">商户可编辑</th>
			       <th data-options="field:'cacheType', width:120, formatter:formatCacheType">缓存方式</th>
			       <th data-options="field:'validity', width:60, formatter:formatValidity">状态</th>
            </tr>
        </thead>
    </table>
    <!-- 新增、修改弹出窗 -->
    <div id="dlg" class="easyui-dialog" style="width:500px;height:400px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons" modal="true">
        <form id="fm" method="post" novalidate>
        	<table>
        		<tr class="fitem">
        			<td><label>参数名(中文):</label></td>
        			<td colspan="3"><input name="paramDisplayName" class="easyui-validatebox" required="true" style="width: 350px;">
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>参数名(key):</label></td>
        			<td colspan="3"><input name="paramName" class="easyui-validatebox" required="true" style="width: 350px;">
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>参数值类型:</label></td>
        			<td><!-- 1 -->
        			    <select class="easyui-combobox" name="valueType" style="width:90px;margin-left:5px;" required="true">
        			    	<option value="1">字符串</option>
        			    	<option value="2">整形</option>
        			    	<option value="3">浮点型</option>
        			    	<option value="4">布尔类型</option>
        			    	<option value="5">日期类型</option>
        			    	<option value="6">时间类型</option>
        			    	<option value="7">文件域</option>
        			    	<option value="8">枚举类型</option>
						</select>
        			</td>
        			<td><label>所属分组:</label></td>
        			<td><!-- 1 - 系统参数， 2 - 租户参数 ， 3 - 通用参数 -->
        			    <select class="easyui-combobox" name="paramType" style="width:100px;" required="true">
        			    	<option value="1">系统参数</option>
        			    	<option value="2">租户参数</option>
        			    	<option value="3">通用参数</option>
						</select>
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>商户可编辑:</label></td>
        			<td>
	        			<input id='isEditable1' type="radio" name="isEditable" value="1"/><label for="isEditable1">是</label>
	        			<input id='isEditable2' type="radio" name="isEditable" value="2" checked="checked"/><label for="isEditable2">否</label>
        			</td>
        			<td><label>缓存方式:</label></td>
        			<td>
        			    <select class="easyui-combobox" name="cacheType" style="width:100px;" required="true">
        			    	<option value="1">缓存到本地</option>
        			    	<option value="2">缓存到服务器</option>
						</select>
        			</td>
        		</tr>
        		<tr class="fitem">
	       			<td><label>参数说明:</label></td>
	       			<td colspan="3"><input name="description"  class="easyui-textbox" style="width:350px;height:50px" data-options="multiline:true,prompt:'最多输入100个字。'"/>
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
       			<td><label>参数名(中文):</label></td>
       			<td><label id="d-paramDisplayName"></label>
       			</td>
       		</tr>
       		<tr class="fitem">
       			<td><label>参数名(key):</label></td>
       			<td><label id="d-paramName"></label>
       			</td>
       		</tr>
       		<tr class="fitem">
       			<td><label>参数值类型:</label></td>
       			<td><label id="d-valueType"></label>
       			</td>
       		</tr>
       		<tr class="fitem">
       			<td><label>所属分组:</label></td>
       			<td><label id="d-paramType"></label>
       			</td>
       		</tr>
       		<tr class="fitem">
       			<td><label>商户可编辑:</label></td>
       			<td><label id="d-isEditable"></label>
       			</td>
       		</tr>
       		<tr class="fitem">
       			<td><label>缓存方式:</label></td>
       			<td><label id="d-cacheType"></label>
       			</td>
       		</tr>
       		<tr class="fitem">
       			<td><label>参数说明:</label></td>
       			<td><label id="d-description"></label>
       			</td>
       		</tr>
       		<tr class="fitem">
       			<td><label>状态:</label></td>
       			<td><label id="d-validity"></label>
       			</td>
       		</tr>
       	</table>
    </div>
    <!-- 新增、修改窗口底部按钮 -->
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveParamDefinition()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>    
    <!-- 查看详情底部按钮 -->
    <div id="detail-dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#detail-dlg').dialog('close')">取消</a>
    </div>
    <!-- 控制代码，可抽取到js文件中 -->
    <script type="text/javascript">
        var url;
        // 新增
        function addParamDefinition() {
            $('#fm').form('clear');
            $('#dlg').dialog('open').dialog('setTitle','新增');
        	$('#sysRegDate').datebox('setValue', nowDate());
        	url = getUrl('/system/paramDefinition/save');
        }
        // 修改
        function updateParamDefinition() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#dlg').dialog('open').dialog('setTitle','修改');
                $('#fm').form('load',row);
                url = getUrl('/system/paramDefinition/update?id=' + row.id);
                return;
            }
            $.messager.alert('提示','请选择要修改的行！');
        }
        // 提交保存
        function saveParamDefinition() {
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
                    if (MyTool.parseResponse(respText, 
                    		{successMsg:'保存成功。',failureMsg:'保存失败。'})) 
                    {
                        $('#dlg').dialog('close');
                        $('#dg').datagrid('reload');
                    }
                }
            });
        }
        // 删除
        function deleteParamDefinition() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $.messager.confirm('删除','确定要删除所选中的数据行吗?',function(r) {
                    if (r) {
                    	var mask = new Mask({maskMsg: '正在处理，请等待。。。'}).show();
                        $.post(
                       		getUrl('/system/paramDefinition/delete'),
                       		{id:row.id},
                       		function (result) {
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
        function detailParamDefinition() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#detail-dlg').dialog('open').dialog('setTitle','详情');
                for (var prop in row) {
                	var val = row[prop];
                	if (prop == 'valueType') val = formatValueType(val);
                	else if (prop == 'paramType') val = formatParamType(val);
                	else if (prop == 'isEditable') val = formatIsEditable(val);
                	else if (prop == 'cacheType') val = formatCacheType(val);
                	else if (prop == 'validity') val = formatValidity(val);
                    $('#d-' + prop).html(val);
                }
            }
        }
        function formatValueType(val) {
        	var obj = {
        			'V_1':'字符串',
        			'V_2':'整形',
        			'V_3':'浮点型',
        			'V_4':'布尔类型',
        			'V_5':'日期类型',
        			'V_6':'时间类型',
        			'V_7':'文件域',
        			'V_8':'枚举类型'
        	};
        	return obj['V_' + val];
        }
        function formatParamType(val) {
        	var obj = {
        			'V_1':'系统参数',
        			'V_2':'租户参数',
        			'V_3':'通用参数'
        	};
        	return obj['V_' + val];
        }
	    function formatIsEditable(val) {
        	var obj = {
        			'V_1':'是',
        			'V_2':'否'
        	};
        	return obj['V_' + val];
	    }
	    function formatCacheType(val) {
        	var obj = {
        			'V_1':'缓存到本地',
        			'V_2':'缓存到服务器'
        	};
        	return obj['V_' + val];
	    }
	    function formatValidity(val) {
	    	if (val == 1) {
	    		return '启用';
	    	}
	    	return '禁用';
	    }
        
        /**
         * 条件查询，刷新datagrid
         * @returns {Boolean}
         */
        function queryWithParams(val) {
        	var options = $('#dg').datagrid('options');
        	var queryParams=options.queryParams;
        	queryParams.qParam = val;
        	$('#dg').datagrid('load');
        }
    </script>
</body>
</html>