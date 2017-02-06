$(function() {
    // 初始化datagrid
    $('#dataList').datagrid({
        url : null,
        fit : true,
        title : '',
        border : false,
        pagination : true,
        pageSize : 15,
        striped : true,
        toolbar : '#tb',
        pageList : [ 10, 15, 20, 30, 40 ],
        idField : 'id',
        multiSort : true,
        remoteSort : false,
        singleSelect : true,
        columns : [ [ 
//        {
//            field : 'ck',
//            checkbox : true
//        }, 
        {
            field : 'id',
            // hidden : true
            title : '编号',
            width:40
        }, {
            field : 'name',
            title : '名称',
            width:100
        }, {
            field : 'IP',
            title : 'IP',
            width:120
        }, {
            field : 'port',
            title : '端口号',
            width:80
        }, {
            field : 'cage',
            title : '宠物笼',
            width:160
        }, {
            field : 'binding',
            title : '状态',
            formatter : function(value, row, index) {
                if (value == false) {
                    return '未绑定';
                } else {
                    return '已绑定';
                }
            }
        }, {
            field : 'createTime',
            title : '创建时间'
        // formatter : function(value, row, index) {
        // return getFormatDateByLong(value, "yyyy-MM-dd hh:mm:ss");
        // }
        }, {
            field : 'updateTime',
            title : '操作',
            formatter : function(value, row, index) {
                var editUrl = '<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" onclick=\"showAddCamera(\'' + row.id + '\')\">编辑</a>';
                var deleteUrl = '<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" onclick=\"deleteCamera(\'' + row.id + '\')\">删除</a>';
                return editUrl +' '+ deleteUrl;
            }
        } ] ],
        onLoadSuccess : function(data) {
            $('#dataList').datagrid('loaded');
            if (data.message) {// message存在表示后台没有正常运行
                $.messager.alert('提示', data.message, 'error');
            }
        }
    });
    var pg = $("#dataList").datagrid("getPager");
    if (pg) {
        $(pg).pagination({
            onSelectPage : function(pageNumber, pageSize) {
                var gridOpts = $("#dataList").datagrid("options");
                gridOpts.pageNumber = pageNumber;
                gridOpts.pageSize = pageSize;
                queryWithParams();
            }
        });
    }
    doSearchForm('dataList');
});

/**
 * 显示添加摄像头
 */
function showAddCamera(id) {
    // 清空表单
    $("#camera_add_form").form('clear');
    // 关闭表单验证
    enableValidate(false);
    if(id!=''&&id!=null&&id!=undefined){
        var url = 'detail';
        var params ={
            id:id
        };
        $("#cameraId").val(id);
        doAjaxServiceRequest(url,params,function(data){
            if (MyTool.parseResponse(data,{noShowAlertMsg:true})){
                var cam = data.data;
                $("#detail_IP").val(cam.IP);
                $("#detail_port").val(cam.port);
                $("#detail_username").val(cam.auth_username);
                $("#detail_passwd").val('************');
                $("#detail_cage").val(cam.cage);
                $("#detail_name").val(cam.name);
                // 打开dialog
                $('#dlg_add').dialog('open').dialog('setTitle', '编辑摄像头');
            }
        });
        
    }else{
        
        // 打开dialog
        $('#dlg_add').dialog('open').dialog('setTitle', '新增摄像头');
    }
}

// 保存摄像头
function saveCamera(){
    enableValidate(true);
    if(!$("#camera_add_form").form("validate")){
        return;
    }
    
    var id = $("#cameraId").val();
    if(id!=''){
        url = "update";
    }else{
        url = "add";
    }
    
    var params ={
        id:id,
        IP:$.trim($("#detail_IP").val()),
        port:$.trim($("#detail_port").val()),
        auth_username:$.trim($("#detail_username").val()),
        auth_password:$.trim($("#detail_passwd").val()),
        cage:$.trim($("#detail_cage").val()),
        name:$.trim($("#detail_name").val())
    };
        
    doAjaxServiceRequest(url,params,function(data){
        if (MyTool.parseResponse(data,
            {successMsg:data.msg,failureMsg:data.msg})){
            $('#dlg_add').dialog('close');
            doSearchForm('dataList');
        }
    });
}

/**
 * 删除摄像头
 * @param id
 */
function deleteCamera(id){
    $.messager.confirm('确认','确定要删除该摄像头吗?',function(r){
        if (r){
            var url="delete";
            var params = {id:id};
            doAjaxServiceRequest(url,params,function(data){
                if (MyTool.parseResponse(data,{successMsg:'摄像头删除成功！',failureMsg:data.msg})){
                    doSearchForm('dataList');
                }
            });
        }
    });
}

/**
 * 根据检索条件执行查询方法
 */
function queryWithParams() {

    // 定义查询条件
    var queryCondition = {
        name : $.trim($("#name").val()),
        cage : $.trim($("#cage").val()),
        binding : $("#binding").combobox("getValue")
    };
    loadDateGrid("get", queryCondition, 'dataList');
}
function resetSearch() {
    $("#name").val('');
    $("#cage").val('');
    $("#binding").combobox("reset");
}
