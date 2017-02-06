$(function() {
	$('#form-dlg').dialog({
		onClose:function(){
			$('#fm').form('clear');
		}
	});
	$('#list').datagrid({
        url:getUrl('/system/dicDefinition/query'),
        fit:true,
//        title:'字典定义',
        border:false,
        pagination:true,
        pageSize:20,
        rownumbers:true,
        striped:true,
        fitColumns:true,
        checkOnSelect:true,
        selectOnCheck:true,singleSelect:true,
        toolbar:'#list-toolbar',
        pageList:[10,15,20,30,40],
        idField:'id',
        remoteSort:false,
        columns:[[
			{field:'code',title:'字典编码',align:'left',width:$('#list').width() * 0.1},
			{field:'name',title:'字典名称',align:'left',width:$('#list').width() * 0.1},
			{field:'description',title:'字典描述',align:'left',width:$('#list').width() * 0.1},
			{field:'hierarchies',title:'字典结构',align:'left',width:$('#list').width() * 0.1, formatter: function(val) {
				return val == 1 ? '层级' : '平级';
			}},
			{field:'scope',title:'应用范围',align:'left',width:40, formatter: function(val) {
				return val == 1 ? '全局' : '租户';
			}},
			{field:'validity',title:'状态',align:'left',width:40 , formatter: function(val) {
				return val == 1 ? '启用' : '禁用';
			}},
        ]],
        onBeforeLoad:function() {
        	var options = $('#list').datagrid('options');
    		var queryParams=options.queryParams;
            return true;
        },
        onLoadSuccess:function(data) {
        	if(data.message){//message存在表示后台没有正常运行
        		Msg.alert(data.message);
        	}
        },
		onLoadError: function(response) {
			MyTool.parseResponse(
				response.responseText, 
        		{
        			failureMsg: MyLocal.BaseMainPaeg.list.onLoadError_parseResponse_failureMsg
        		}
    		);
		}
    });
});

/**
 * 条件查询，刷新datagrid
 * @returns {Boolean}
 */
function queryWithParams(val) {
	var options = $('#list').datagrid('options');
	var queryParams=options.queryParams;
	queryParams.qParam = val;
	$('#list').datagrid('load');
}

var url;
/**
 * 打开新增表单对话框
 */
function openAddFormWindow() {
    $('#fm').form('clear');
    $('#form-dlg').dialog('open').dialog('setTitle', MyLocal.BaseMainPaeg.openAddFormWindow_title);
	url = getUrl('/system/dicDefinition/save');
}

/**
 * 打开修改表单对话框
 */
function openUpdateFormWindow() {
    var record = $('#list').datagrid('getSelected');
    if (record) {
        $('#form-dlg').dialog('open').dialog('setTitle', MyLocal.BaseMainPaeg.openUpdateFormWindow_title);
        $('#fm').form('load',record);
        var hCkb = document.getElementById('hierarchies');
        if (record.hierarchies == 1) {
        	hCkb.checked = true;
        } else {
        	hCkb.checked = false;
        }
        url = getUrl('/system/dicDefinition/update?id=' + record.id);
        return;
    }
    Msg.alert(MyLocal.BaseMainPaeg.noSelectedRowMsg.u);
}

/**
 * 新增、修改表单提交
 */
function submitForm() {
	if (document.getElementById('hierarchies').checked) {
		$('input[name=hierarchies]').val(1);
	} else {
		$('input[name=hierarchies]').val(0);
	}
	var mask = undefined;
    $('#fm').form('submit', {
        url: url,
        onSubmit: function() {
        	if ($(this).form('validate')) {
        		mask = new Mask().show();
        		return true;
        	}
            return false;
        },
        success: function(responseText) {
        	mask.hide();
            if (MyTool.parseResponse(responseText, 
            		{
            			successMsg: MyLocal.BaseMainPaeg.save_form_parseResponse_successMsg,
            			failureMsg: MyLocal.BaseMainPaeg.save_form_parseResponse_failureMsg
        			}
    			))
			{
                $('#form-dlg').dialog('close');
                $('#list').datagrid('reload');
            }
        }
    });
}

/**
 * 删除
 */
function deleteRecord() {
    var record = $('#list').datagrid('getSelected');
    if (record) {
        $.messager.confirm(
        	MyLocal.BaseMainPaeg.delete_confirm_title,
        	MyLocal.BaseMainPaeg.delete_confirm_msg,
	        function(flag) {
	            if (flag) {
	            	var mask = new Mask().show();
	                $.post(
	               		getUrl('/system/dicDefinition/delete'),
	               		{id:record.id},
	               		function(response) {
	               			mask.hide();
	               			if (MyTool.parseResponse(response)) {
                				$('#list').datagrid('reload');
	               			}
	                	},
	               		'json'
	               	);
	            }
	        }
        );
        return;
    }
    Msg.alert(MyLocal.BaseMainPaeg.noSelectedRowMsg.d);
}

/**
 * 查看详情
 */
function showDetail() {
    var record = $('#list').datagrid('getSelected');
    if (record) {
        $('#detail-dlg').dialog('open').dialog('setTitle', 
        	MyLocal.BaseMainPaeg.showDetail_title);
        for (var prop in record) {
            $('#d-' + prop).html(record[prop]);
        }
        return;
    }
}

/**
 * 重置查询表单
 */
function resetForm() {
	$('#fm').form('clear');
}