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
        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addUser()">新增</a>
        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateUser()">修改</a>
        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteUser()">删除</a>
        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-detail" plain="true" onclick="detailUser()">查看详情</a>
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
                singleSelect:true,
                autoRowHeight:false,
                pagination:true,toolbar:'#toolbar',
                pageSize:20" url="${pageContext.request.contextPath}/system/user/query">
        <thead>
            <tr>
			       <th field="username" width="160">用户名</th>
			       <th field="employeeNo" width="160">员工编号</th>
			       <th field="name" width="160">姓名</th>
			       <th field="phoneNo" width="160">电话号码</th>
			       <th field="qq" width="160">QQ</th>
			       <th field="eMail" width="160">电子邮件</th>
            </tr>
        </thead>
    </table>
    <!-- 新增、修改弹出窗 -->
    <div id="dlg" class="easyui-dialog" style="width:500px;height:400px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons" modal="true">
        <form id="fm" method="post" novalidate>
        	<table>
        		<tr class="fitem">
        			<td><label>用户名:</label></td>
        			<td><input name="username" class="easyui-validatebox" required="true">
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>密码:</label></td>
        			<td><input name="password">
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>员工编号:</label></td>
        			<td><input name="employeeNo" class="easyui-validatebox" required="true">
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>姓名:</label></td>
        			<td><input name="name" class="easyui-validatebox" required="true">
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>电话号码:</label></td>
        			<td><input name="phoneNo">
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>QQ:</label></td>
        			<td><input name="qq">
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>电子邮件:</label></td>
        			<td><input name="eMail">
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>头像:</label></td>
        			<td><input name="photo">
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>账户类型:</label></td>
        			<td><input name="userType" class="easyui-numberbox">
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>备注:</label></td>
        			<td><input name="remark">
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>排序号:</label></td>
        			<td><input name="orderField" class="easyui-numberbox">
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
       			<td><label>用户名:</label></td>
       			<td><label id="d-username"></label>
       			</td>
       		</tr>
       		<tr class="fitem">
       			<td><label>密码:</label></td>
       			<td><label id="d-password"></label>
       			</td>
       		</tr>
       		<tr class="fitem">
       			<td><label>员工编号:</label></td>
       			<td><label id="d-employeeNo"></label>
       			</td>
       		</tr>
       		<tr class="fitem">
       			<td><label>姓名:</label></td>
       			<td><label id="d-name"></label>
       			</td>
       		</tr>
       		<tr class="fitem">
       			<td><label>电话号码:</label></td>
       			<td><label id="d-phoneNo"></label>
       			</td>
       		</tr>
       		<tr class="fitem">
       			<td><label>QQ:</label></td>
       			<td><label id="d-qq"></label>
       			</td>
       		</tr>
       		<tr class="fitem">
       			<td><label>电子邮件:</label></td>
       			<td><label id="d-eMail"></label>
       			</td>
       		</tr>
       		<tr class="fitem">
       			<td><label>头像:</label></td>
       			<td><label id="d-photo"></label>
       			</td>
       		</tr>
       		<tr class="fitem">
       			<td><label>账户类型:</label></td>
       			<td><label id="d-userType"></label>
       			</td>
       		</tr>
       		<tr class="fitem">
       			<td><label>备注:</label></td>
       			<td><label id="d-remark"></label>
       			</td>
       		</tr>
       		<tr class="fitem">
       			<td><label>排序号:</label></td>
       			<td><label id="d-orderField"></label>
       			</td>
       		</tr>
       	</table>
    </div>
    <!-- 新增、修改窗口底部按钮 -->
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a>
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
        function addUser() {
            $('#fm').form('clear');
            $('#dlg').dialog('open').dialog('setTitle','新增');
        	$('#sysRegDate').datebox('setValue', nowDate());
        	url = '${pageContext.request.contextPath}/system/user/save';
        }
        // 修改
        function updateUser() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#dlg').dialog('open').dialog('setTitle','修改');
                $('#fm').form('load',row);
                url = '${pageContext.request.contextPath}/system/user/update?id=' + row.id;
                return;
            }
            $.messager.alert('提示','请选择要修改的行！');
        }
        // 提交保存
        function saveUser() {
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
        // 删除
        function deleteUser() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $.messager.confirm('删除','确定要删除所选中的数据行吗?',function(r) {
                    if (r) {
                        $.post(
                       		'${pageContext.request.contextPath}/system/user/delete',
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
        function detailUser() {
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
                $('#detail-dlg').dialog('open').dialog('setTitle','详情');
                for (var prop in row) {
                    $('#d-' + prop).html(row[prop]);
                }
                return;
            }
        }
    </script>
</body>
</html>