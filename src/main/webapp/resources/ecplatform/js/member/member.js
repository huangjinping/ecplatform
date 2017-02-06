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
            title : '用户姓名',
            width:80
        }, {
            field : 'gender',
            title : '性别',
            align : 'center',
            formatter : function(value, row, index) {
                if (value == 0) {
                    return '男';
                } else {
                    return '女';
                }
            },
            width:40
        }, {
            field : 'mobile',
            title : '手机号码',
            width:120
        }, {
            field : 'email',
            title : '邮箱',
            width:180
        }, {
            field : 'id_card',
            title : '身份证号',
            width:160
        }, {
            field : 'petnum',
            title : '宠物',
            width:40
        }, {
            field : 'balance',
            title : '余额',
            width:60,
            formatter : function(value, row, index) {
                return '¥'+value;
            }
        }, {
            field : 'enable',
            title : '用户状态',
            formatter : function(value, row, index) {
                if (value == false) {
                    return '停用';
                } else {
                    return '可用';
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
                var editUrl = '<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" onclick=\"showAddMember(\'' + row.id + '\')\">编辑</a>';
                var disableUrl = '<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" onclick=\"disableMember(\'' + row.id + '\')\">停用</a>';
                var enableUrl = '<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" onclick=\"activeMember(\'' + row.id + '\')\">激活</a>';
                var resetUrl = '<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" onclick=\"resetMember(\'' + row.id + '\')\">密码重置</a>';
                
                if (row.enable){
                    return editUrl +' '+ disableUrl + ' ' + resetUrl;
                }else{
                    return editUrl +' '+ enableUrl;
                }
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
 * 激活账户
 * @param id
 */
function activeMember(id){
    $.messager.confirm('确认','确定要激活该账户吗?',function(r){
        if (r){
            var url="active";
            var params = {id:id};
            doAjaxServiceRequest(url,params,function(data){
                if (MyTool.parseResponse(data,{successMsg:'会员账号激活成功！',failureMsg:data.msg})){
                    doSearchForm('dataList');
                }
            });
        }
    });
}

/**
 * 停用账户
 * @param id
 */
function disableMember(id){
    $.messager.confirm('确认','确定要停用该账户吗?',function(r){
        if (r){
            var url="disable";
            var params = {id:id};
            doAjaxServiceRequest(url,params,function(data){
                if (MyTool.parseResponse(data,{successMsg:'会员账号已经被停用！',failureMsg:data.msg})){
                    doSearchForm('dataList');
                }
            });
        }
    });
}

/**
 * 密码重置
 * @param id
 */
function resetMember(id){
    $.messager.confirm('确认','请确认是否将密码重置为123456?',function(r){
        if (r){
            var url="reset";
            var params = {id:id};
            doAjaxServiceRequest(url,params,function(data){
                if (MyTool.parseResponse(data,{successMsg:'账号密码重置成功！',failureMsg:data.msg})){
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
    if (!$("#start_create_time").datebox('isValid') || !$("#end_create_time").datebox('isValid')) {
        return false;
    }
    // 定义查询条件
    var queryCondition = {
        name : $.trim($("#name").val()),
        mobile : $.trim($("#mobile").val()),
        start_create_time : $("#start_create_time").datebox("getValue"),
        end_create_time : $("#end_create_time").datebox("getValue"),
        enable : $("#enable").combobox("getValue")
    };
    loadDateGrid("get", queryCondition, 'dataList');
}
function resetSearch() {
    $("#name").val('');
    $("#mobile").val('');
    $("#enable").combobox("reset");

    $("#start_create_time").datebox('clear');
    $("#end_create_time").datebox("clear");

}

/**
 * 显示添加会员
 */
function showAddMember(id) {
    var url;
    if(id!=''&&id!=null&&id!=undefined){
        url = 'member/page/update?id='+id;
        openNewTab('memberEditTab', url, '会员编辑', true);
    }else{
        url = 'member/page/add';
        openNewTab('memberAddTab', url, '会员添加', true);
    }
    
}
