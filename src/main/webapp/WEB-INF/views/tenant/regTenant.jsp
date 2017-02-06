<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../common/common.jsp"></jsp:include>
<title>注册商户管理</title>
</head>
<body>
	<div id="toolbar">
        <table cellpadding="0" cellspacing="0">
            <tr>
                <td style="padding-left:2px">
        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newRegTenant()">商户注册</a>
        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editRegTenant()">编辑商户</a>
        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteRegTenant()">删除商户</a>
        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-detail" plain="true" onclick="detailRegTenant()">查看详情</a>
                </td>
                <td><div class="datagrid-btn-separator"></div></td>
                <td style="padding-left:2px">
                    <input class="easyui-searchbox" data-options="prompt:'名称'" style="width:150px"></input>
                </td>
            </tr>
        </table>
    </div>
    <table id="dg" class="easyui-datagrid" data-options="
                rownumbers:true,
                fit:true,
                singleSelect:true,
                autoRowHeight:false,
                border:false,
                pagination:true,toolbar:'#toolbar',
                pageSize:20" url="${pageContext.request.contextPath}/tenant/regTenant/query">
        <thead>
            <tr>
                <th field="cnShortName" width="160">中文名</th>
                <th field="enName" width="160">英文名</th>
                <th field="industry" width="160">所属行业</th>
                <th field="phoneNumber" width="160" align="right">企业电话</th>
                <th field="sysRegCode" width="160" align="right">系统注册码</th>
                <th field="sysRegDate" width="160" align="right">注册日期</th>
            </tr>
        </thead>
    </table>

    <!-- 对话框 -->
    <div id="dlg" class="easyui-dialog" style="width:550px;height:400px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons" modal="true">
        <form id="fm" method="post" novalidate>
        	<table>
        		<tr class="fitem">
        			<td><label>系统注册码:</label></td>
        			<td><input name="sysRegCode" required="required"></td>
        			<td><label>注册日期:</label></td>
        			<td><input id="sysRegDate" name="sysRegDate" class="easyui-datebox" required="required"/></td>
        		</tr>
        		<tr class="fitem">
        			<td><label>企业中文简称:</label></td>
        			<td><input name="cnShortName" class="easyui-validatebox" required="true"></td>
        			<td><label>企业中文全称:</label></td>
        			<td><input name="cnFullName" class="easyui-validatebox" required="true"></td>
        		</tr>
        		<tr class="fitem">
        			<td><label>企业英文名:</label></td>
        			<td><input name="enName"></td>
        			<td><label>企业代码:</label></td>
        			<td><input name="code"></td>
        		</tr>
        		<tr class="fitem">
        			<td><label>注册地址:</label></td>
        			<td colspan="3"><input name="regLocation" style="width:390px;"></td>
        		</tr>
        		<tr class="fitem">
        			<td><label>法人代表:</label></td>
        			<td><input name="legalPerson"></td>
        			<td><label>商户类型:</label></td>
        			<td><input name="type"></td>
        		</tr>
        		<tr class="fitem">
        			<td><label>企业地址:</label></td>
        			<td><input name="location"></td>
        			<td><label>邮编:</label></td>
        			<td><input name="zipCode"></td>
        		</tr>
        		<tr class="fitem">
        			<td><label>企业电话:</label></td>
        			<td><input name="phoneNumber"></td>
        			<td><label>所属行业:</label></td>
        			<td><input name="industry"></td>
        		</tr>
        		<tr class="fitem">
        			<td><label>经营范围:</label></td>
        			<td colspan="3"><textarea name="bizScope" style="width:390px;"></textarea></td>
        		</tr>
        		<tr class="fitem">
        			<td><label>备注:</label></td>
        			<td colspan="3"><textarea name="remark" style="width:390px;"></textarea></td>
        		</tr>
        	</table>
       		<input name="sync2Org" type="hidden">
        </form>
    </div>
    
    <!-- 对话框 -->
    <div id="detail-dlg" class="easyui-dialog" style="width:550px;height:400px;padding:10px 20px"
            closed="true" buttons="#detail-dlg-buttons" modal="true">
       	<table id="detail-table">
       		<tr>
       			<td><label>系统注册码:</label></td>
       			<td><label id="d-sysRegCode"></label></td>
       			<td><label>注册日期:</label></td>
       			<td><label id="d-sysRegDate"></label></label></td>
       		</tr>
       		<tr>
       			<td><label>企业中文简称:</label></td>
       			<td><label id="d-cnShortName"></label></td>
       			<td><label>企业中文全称:</label></td>
       			<td><label id="d-cnFullName"></label></td>
       		</tr>
       		<tr>
       			<td><label>企业英文名:</label></td>
       			<td><label id="d-enName"></label></td>
       			<td><label>企业代码:</label></td>
       			<td><label id="d-code"></label></td>
       		</tr>
       		<tr>
       			<td><label>注册地址:</label></td>
       			<td colspan="3"><label id="d-regLocation"></label></td>
       		</tr>
       		<tr>
       			<td><label>法人代表:</label></td>
       			<td><label id="d-legalPerson"></label></td>
       			<td><label>商户类型:</label></td>
       			<td><label id="d-type"></label></td>
       		</tr>
       		<tr>
       			<td><label>企业地址:</label></td>
       			<td><label id="d-location"></label></td>
       			<td><label>邮编:</label></td>
       			<td><label id="d-zipCode"></label></td>
       		</tr>
       		<tr>
       			<td><label>企业电话:</label></td>
       			<td><label id="d-phoneNumber"></label></td>
       			<td><label>所属行业:</label></td>
       			<td><label id="d-industry"></label></td>
       		</tr>
       		<tr>
       			<td><label>经营范围:</label></td>
       			<td colspan="3"><label id="d-bizScope"></label></td>
       		</tr>
       		<tr>
       			<td><label>备注:</label></td>
       			<td colspan="3"><label id="d-remark"></label></td>
       		</tr>
       	</table>
    </div>
    
    <div id="dlg-buttons">
    	<div style="float: left">
    	<input id="isSync" style="position:relative; bottom: -3px;" type="checkbox" checked="true"/><label for="isSync">同步组织机构</label>
    	</div>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveRegTenant()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>    
    <div id="detail-dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#detail-dlg').dialog('close')">取消</a>
    </div>
    
    <script type="text/javascript">
        var url;
        function newRegTenant() {
            $('#fm').form('clear');
            $('#isSync').attr('checked','checked');
            $('#dlg').dialog('open').dialog('setTitle','商户注册');
        	$('#sysRegDate').datebox('setValue', nowDate());
        	url = '${pageContext.request.contextPath}/tenant/regTenant/save';
        }
        
        function editRegTenant() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#isSync').attr('checked',false);
                $('#dlg').dialog('open').dialog('setTitle','修改商户信息');
                $('#fm').form('load',row);
                url = '${pageContext.request.contextPath}/tenant/regTenant/update?id=' + row.id;
                return;
            }
            $.messager.alert('提示','请选择要修改的行！');
        }
        
        function saveRegTenant() {
        	var val = document.getElementById('isSync').checked;
        	if (val) {
        		val  = 'yes';
        	} else {
        		val = 'no';
        	}
    		$('input[name=sync2Org]').val(val);
            $('#fm').form('submit', {
                url: url,
                onSubmit: function() {
                    return $(this).form('validate');
                },
                success: function(result) {
                    var result = eval('('+result+')');
                    if (result.errorMsg) {
                        $.messager.show({
                            title: 'Error',
                            msg: result.errorMsg
                        });
                    } else {
                        $('#dlg').dialog('close');
                        $('#dg').datagrid('reload');
                    }
                }
            });
        }
        
        function deleteRegTenant() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $.messager.confirm('删除','确定要删除所选中的数据行吗?',function(r) {
                    if (r) {
                        $.post(
                       		'${pageContext.request.contextPath}/tenant/regTenant/delete',
                       		{id:row.id},
                       		function(result){
	                            if (result.success) {
	                                $('#dg').datagrid('reload');
	                            } else {
	                                $.messager.show({
	                                    title: 'Error',
	                                    msg: result.errorMsg
	                                });
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
        function detailRegTenant() {
            var row = $('#dg').datagrid('getSelected');
            var objToString = function (obj) {
            	var s = "";
            	for (var property in obj) {
            		s = s + "\n "+property +": " + obj[property] ;
            	}
            	return s;
            };
            console.log(objToString(row));
            if (row) {
                $('#detail-dlg').dialog('open').dialog('setTitle','修改商户信息');
                for (var prop in row) {
                    $('#d-' + prop).html(row[prop]);
                }
                return;
            }
        }
    </script>
</body>
</html>