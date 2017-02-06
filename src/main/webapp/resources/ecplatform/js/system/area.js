$(function() {
	$("#form-dlg").dialog({
		onClose:function(){
			$("#fm").form("clear");
		}
	});
	$('#list').datagrid({
        url:getUrl('/system/area/query'),
        fit:true,
//        title:'省市区域管理',
        border:false,
        pagination:true,
        pageSize:20,
        rownumbers:true,
        striped:true,
        fitColumns:true,
        checkOnSelect:true,
        selectOnCheck:true,
        singleSelect:true,
        toolbar:'#list-toolbar',
        pageList:[10,15,20,30,40],
        idField:'id',
        remoteSort:false,
        columns:[[
			{field:'parentId',title:'上级区域111',align:'left',width:50},
			{field:'name',title:'区域名称',align:'left',width:$("#list").width() * 0.1},
			{field:'code',title:'区域编码',align:'left',width:$("#list").width() * 0.1},
			{field:'orderField',title:'排序号',align:'left',width:$("#list").width() * 0.1},
        ]],
        onBeforeLoad:function() {
        	var options = $("#list").datagrid("options");
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
        			successMsg:'数据返回成功。',
        			failureMsg:'获取数据异常。您可以尝试刷新或关闭后再重新打开列表界面。'
        		}
    		);
		}
    });
});
/**
 * 条件查询，刷新datagrid
 * @returns {Boolean}
 */
function queryWithParams() {
	var options = $("#list").datagrid("options");
	var queryParams=options.queryParams;
	$("#list").datagrid("load");
}

var url;
/**
 * 打开新增表单对话框
 */
function openAddFormWindow() {
    $('#fm').form('clear');
    $('#form-dlg').dialog('open').dialog('setTitle','新增');
	url = getUrl('/system/area/save');
}

/**
 * 打开修改表单对话框
 */
function openUpdateFormWindow() {
    var record = $('#list').datagrid('getSelected');
    if (record) {
        $('#form-dlg').dialog('open').dialog('setTitle','修改');
        $('#fm').form('load',record);
        url = getUrl('/system/area/update?id=' + record.id);
        return;
    }
    $.messager.alert('提示','请选择要修改的行！');
}

/**
 * 新增、修改表单提交
 */
function save() {
	var mask = undefined;
    $('#fm').form('submit', {
        url: url,
        onSubmit: function() {
        	if ($(this).form('validate')) {
        		mask = new Mask({maskMsg: '正在处理，请等待。。。'}).show();
        		return true;
        	}
            return false;
        },
        success: function(responseText) {
        	mask.hide();
            if (MyTool.parseResponse(responseText, 
            		{successMsg:'保存成功。',failureMsg:'保存失败。'})) 
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
        $.messager.confirm('删除','确定要删除所选中的数据行吗?',function(flag) {
            if (flag) {
            	var mask = new Mask({maskMsg: '正在处理，请等待。。。'}).show();
                $.post(
               		getUrl('/system/area/delete'),
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
        });
        return;
    }
    $.messager.alert('提示','请选择要删除的行！');
}

/**
 * 查看详情
 */
function showDetail() {
    var record = $('#list').datagrid('getSelected');
    if (record) {
        $('#detail-dlg').dialog('open').dialog('setTitle','详情');
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
	$("#fm").form('clear');
}