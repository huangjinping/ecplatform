// 密码重置
function resetPwd() {
    var row = $('#dg').datagrid('getSelected');
    if (row) {
        $.messager.confirm('提示','确定要重置[' + row.name + ']的密码吗?',function(r) {
            if (r) {
            	var mask = new Mask({maskMsg: '正在处理，请等待。。。'}).show();
                $.post(
                	getUrl('/system/user/resetPwd'),
               		{id:row.id},
               		function(result) {
               			mask.hide();
               			MyTool.parseResponse(result, 
                    		{
                        		successMsg:'修改成功。该用户密码已被重置为[<span class="red-text">123456</span>]。',
                        		failureMsg:'系统异常，设置失败。'
                			}
               			);
                	},
               		'json'
               	);
            };
        });
        return;
    }
    $.messager.alert('提示','请选择要操作的行！');
}

var url;
var isNew;
// 新增
function addUser() {
	isNew = true;
	var org = $('#orgTree').tree('getSelected');
	if (!org) {
		$.messager.alert("提示", '请从左侧组织机构树中选择新员工所属机构。');
		return;
	} 
    $('#fm').form('clear');
	$('#lbl-orgName').html(org.shortName);
	$('#form-orgId').val(org.id);
    $('#dlg').dialog('open').dialog('setTitle','新增');
	url = getUrl('/system/user/save');
}
// 修改
function updateUser() {
	isNew = false;
    var row = $('#dg').datagrid('getSelected');
    if (row) {
    	if (row.username && row.username.endWith('_admin')) {
    		$.messager.alert('提示', '系统管理账号，不能进行修改。');
    		return;
    	}
    	var prefix = $('#accountPrefix').html();
    	if (prefix && row.username.startWith(prefix)) {
        	row.username = row.username.replace(prefix, '');
    	}
        $('#fm').form('load',row);
    	$('#lbl-orgName').html(row.orgName);
        $('#dlg').dialog('open').dialog('setTitle','修改');
        url = getUrl('/system/user/update?id=' + row.id);
        return;
    }
    $.messager.alert('提示','请选择要修改的行！');
}
// 提交保存
function saveUser() {
	var mask = null;
    $('#fm').form('submit', {
        url: url,
        onSubmit: function() {
        	if ($(this).form('validate')) {
            	if ($('input[name=username]').val() == 'admin') {
            		$.messager.alert('提示', '账号已经存在。');
            		return false;
            	}
            	mask = new Mask().show();
            	return true;
        	} else {
        		return false;
        	}
        },
        success: function(response) {
        	mask.hide();
        	// 服务器异常，保存失败，请刷新后再试。
            var msg = '保存成功。';
        	if (isNew) {
        		msg = msg + '<br/>系统账号：<span class="red-text">' 
        			+ $('#accountPrefix').html() 
        			+ $('input[name=username]').val() 
        			+ '</span><br/>初始密码：<span class="red-text">123456</span>';
        	}
            if (MyTool.parseResponse(response, 
            		{successMsg:msg,failureMsg:'保存失败。'})) 
            {
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
    	if (row.userType == 1) {
    		$.messager.alert('提示', '系统管理账号，无法删除。');
    		return;
    	}
        $.messager.confirm('删除','确定要删除所选中的数据行吗?',function(r) {
            if (r) {
            	var mask = new Mask({maskMsg: '正在处理，请等待。。。'}).show();
                $.post(
               		getUrl('/system/user/delete'),
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
function detailUser() {
    var row = $('#dg').datagrid('getSelected');
    if (row) {
        $('#detail-dlg').dialog('open').dialog('setTitle','详情');
        for (var prop in row) {
            $('#d-' + prop).html(row[prop]);
        }
        return;
    }
}

/**
 * 打开角色分配对话框
 */
function distributeRole() {
    var row = $('#dg').datagrid('getSelected');
    if (row) {
    	var mask = new Mask({maskMsg: '正在加载角色信息，请等待。。。'}).show();
        $.post(
        	getUrl('/system/role/queryAllRoleByUser'),
       		{userId: row.id},
       		function(result) {
       			try {
       				MyTool.doLoginFailure(result);
	       			if (result.constructor == Array) {
	       				var html = new Array();
	       				html.push('<table width="380px">');
	       				var rows = result.length / 4;
	       				if (rows < 0) {
	       					rows = 1;
	       				}
	       				var count = 0;
	       				for (var i = 0; i < rows; i++) {
	       					html.push('<tr>');
	       					for (var j = 0; j < 4; j++) {
	       						html.push('<td width="95px">');
	       						if (count < result.length) {
	       							var checked = '';
	       							if (result[count].checked) {
	       								checked = 'checked=true';
	       							}
	       	   	   					html.push('<input type="checkbox" id="ckb_' + result[count].id + '" ' + checked + ' value="' + result[count].id + '"/>');
	       	   	   					html.push('<label for="ckb_' + result[count].id + '">' + result[count].roleName + '</label>');
	       	   	   					count++;
	       						}
	       						html.push('</td>');
	       					}
	    					html.push('</tr>');
	       				}
	       				html.push('</table>');
	       				$('#roleDiv').html(html.join(''));
	       			}
       				mask.hide();
       			} catch(e) {
       				mask.hide();
       			}
        	},
       		'json'
       	);
    	$('#d-r-username').html(row.name);
        $('#dlg-role').dialog('open').dialog('setTitle','角色分配');
        return;
    }
    $.messager.alert('提示','请选择要授权的员工！');
}

/**
 * 分配角色
 */
function saveRole() {
	var idsArr = new Array();
	$('#roleDiv input[type=checkbox]').each(function(i, e) {
		if (e.checked) {
			idsArr.push(e.value);
		}
	});
	var row = $('#dg').datagrid('getSelected');
	var mask = new Mask({maskMsg: '正在处理，请等待。。。'}).show();
    $.post(
    	getUrl('/system/role/saveUserRole'),
   		{ids:idsArr.join(','), userId: row.id},
   		function(result) {
   			mask.hide();
   			MyTool.parseResponse(result,
				{successMsg:'保存成功。',failureMsg:'保存失败。'}
   			);
    	},
   		'json'
   	);
}