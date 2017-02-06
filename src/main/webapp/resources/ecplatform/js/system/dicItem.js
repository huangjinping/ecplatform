$(function() {
	$('#form-dlg').dialog({
		onClose:function() {
			$('#fm').form('clear');
		}
	});
	$('#dicList').datagrid({
        url:getUrl('/system/dicDefinition/queryDic'),
        title:'数据字典',
        border:false,
        pagination:true,
        pageSize:20,
        rownumbers:true,
        striped:true,
        fitColumns:true,
        checkOnSelect:true,
        selectOnCheck:true,
        singleSelect:true,
        toolbar:'#dicList-toolbar',
        pageList:[10,15,20,30,40],
        idField:'id',
        remoteSort:false,
        columns:[[
            {field:'name',title:'字典名称',align:'left',width:$('#dicList').width() * 0.1},
			{field:'code',title:'字典名称',align:'left',width:$('#dicList').width() * 0.1},
			{field:'hierarchies',title:'字典结构',align:'left',width:$('#dicList').width() * 0.1, 
				formatter: function(val){
					if (val == 1) {
						return '层级';
					} else {
						return '平级';
					}
				}
			}
        ]],
        onBeforeLoad:function() {
        	//var options = $('#dicList').datagrid('options');
    		//var queryParams=options.queryParams;
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
	
	$('#dicList').datagrid({
		onClickRow:function(rowIndex, rowData) {
		if (rowData.hierarchies == 1) {
			$('#addChildBtn').show();
			$('#parentRow').show();
		} else {
			$('#addChildBtn').hide();
			$('#parentRow').hide();
		}
		$('#list').treegrid({url:getUrl('/system/dicItem/query'), queryParams:{dirId: rowData.id}});
	}});
	
	$('#dicList').datagrid('getPager').pagination({showPageList: false,showRefresh: false, displayMsg: ''});
    
	$('#list').treegrid({
		animate:true,
		fit:true,
		method:'post',
	    idField:'id',
        rownumbers:true,
        border: false,
        toolbar:'#list-toolbar',
	    treeField:'displayName',
	    columns:[[
  			{field:'displayName',title:'显示名称',align:'left',width:160},
			{field:'itemValue',title:'键值',align:'left',width:100},
			{field:'description',title:'描述',align:'left',width:$('#list').width() * 0.5}
	    ]],
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
	var options = $('#list').treegrid('options');
	var queryParams=options.queryParams;
	queryParams.displayName = val;
	$('#list').treegrid('load');
}

function queryDicWithParams(val) {
	var options = $('#dicList').datagrid('options');
	var queryParams=options.queryParams;
	queryParams.qParam = val,
	$('#dicList').datagrid('load');
}

var url;
/**
 * 打开新增表单对话框
 */
function openAddFormWindow(parentItem) {
	var dic = $('#dicList').datagrid('getSelected');
	if (dic) {
	    $('#fm').form('clear');
		if (parentItem) {
			$('input[name=parentId]').val(parentItem.id);
			$('input[name=idFullpath]').val(parentItem.idFullpath);
			$('#parentNameFullpath').html(parentItem.nameFullpath);
		} else  {
			$('#parentNameFullpath').html('/');
		}
	    $('input[name=dirId]').val(dic.id);
	    $('#form-dlg').dialog('open').dialog('setTitle', MyLocal.BaseMainPaeg.openAddFormWindow_title);
		url = getUrl('/system/dicItem/save');
		return;
	}
	Msg.alert('请先从左侧字典列表中选择要添加值的字典。');
}

function openAddChildFormWindow() {
	var dic = $('#list').treegrid('getSelected');
	if (!dic) {
		Msg.alert('请从列表中选择父级项目。');
		return false;
	}
	openAddFormWindow(dic);
	
}

/**
 * 打开修改表单对话框
 */
function openUpdateFormWindow() {
    var record = $('#list').treegrid('getSelected');
    if (record) {
        $('#fm').form('load',record);
        $('input[name=oldNameFullpath]').val(record.nameFullpath);
        $('#parentNameFullpath').html(getParentFullpath(record.nameFullpath));
        $('#form-dlg').dialog('open').dialog('setTitle', MyLocal.BaseMainPaeg.openUpdateFormWindow_title);
        url = getUrl('/system/dicItem/update?id=' + record.id);
        return;
    }
    Msg.alert(MyLocal.BaseMainPaeg.noSelectedRowMsg.u);
}

/**
 * 新增、修改表单提交
 */
function submitForm() {
	var mask = undefined;
	var pnf = $('#parentNameFullpath').html();
	pnf = (pnf == '/' ? '' : pnf);
	$('input[name=nameFullpath]').val(pnf + 
			"/" + $('input[name=displayName]').val());
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
                $('#list').treegrid('reload');
            }
        }
    });
}

/**
 * 删除
 */
function deleteRecord() {
    var record = $('#list').treegrid('getSelected');
    if (record) {
        $.messager.confirm(
        	MyLocal.BaseMainPaeg.delete_confirm_title,
        	MyLocal.BaseMainPaeg.delete_confirm_msg,
	        function(flag) {
	            if (flag) {
	            	var mask = new Mask().show();
	                $.post(
	               		getUrl('/system/dicItem/delete'),
	               		{id:record.id},
	               		function(response) {
	               			mask.hide();
	               			if (MyTool.parseResponse(response)) {
                				$('#list').treegrid('unselect', record.id);
                				$('#list').treegrid('reload');
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
 * 重置查询表单
 */
function resetForm() {
	$('#fm').form('clear');
}