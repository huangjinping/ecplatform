/**
 * ajax后台数据访问函数
 * 
 * @param url
 *            访问接口相对路径
 * @param params
 *            访问参数
 * @param gridOpts
 *            列表参数 分页参数
 * @returns 返回数据
 */
function doAjaxServiceRequest(url, params, callback) {
    var mask = new Mask({maskMsg: '正在处理，请等待。。。'}).show();
    $.ajax({
        method : "POST",
        url : url,
        data : params,
        success : function(data) {
            mask.hide();
            if (MyTool.parseResponse(data,{noShowAlertMsg:true})){
                callback(JSON.parse(data));
            }
        },
        error : function(data) {
            $.messager.alert('提示', '连接异常！', 'error');
        }
    });
}

/**
 * 页面共通加载数据函数
 * 
 * @param url
 *            访问接口相对路径
 * @param queryCondition
 *            访问参数列表
 * @param gridOpts
 *            分页参数
 */
function loadDateGrid(url, queryCondition, formId) {
//    $('#' + formId).datagrid('loading');
    var gridOpts = $("#" + formId).datagrid("options");
    var params = $.extend({}, queryCondition, {
        page : gridOpts.pageNumber,
        rows : gridOpts.pageSize
    });
    // 异步获取数据到javascript对象，入参为查询条件和页码信息
    doAjaxServiceRequest(url, params, function(data) {
        // 检查是否数据是否为空
        countResult(data);
        // 使用loadDate方法加载Dao层返回的数据
        if (data != null && data != undefined && data != '') {
            $('#' + formId).datagrid('loadData', {
                "total" : data.total,
                "rows" : data.rows
            });
        }
    });

}

/**
 * 数据加载loading函数
 * 
 * @param grid
 *            datagrid的id
 * @param func
 *            数据加载方法
 * @param time
 *            时间
 */
function Exec_Wait(grid, func, time) {
    var dalayTime = 500;
    __func_ = func;
    __selector_ = '#' + grid;
    $(__selector_).datagrid("loading");
    if (time) {
        dalayTime = time;
    }
    gTimeout = window.setTimeout(_Exec_Wait_, dalayTime);
}
function _Exec_Wait_() {
    try {
        eval(__func_);
    } catch (e) {
        alert("__func_:" + __func_ + ";_ExecWait_" + e.message);
    } finally {
        window.clearTimeout(gTimeout);
        $(__selector_).datagrid("loaded");
    }
}


/**
 * 检查检索结果数据是否为空
 * 
 * @param data
 */
function countResult(data) {
    if (data == undefined || data == null || data.total == 0) {
        $.messager.alert('提示', '没有查询到相关信息！', 'info');
    }
}

/**
 * 检索按钮，查询第一页
 */
function doSearchForm(formId) {
    var gridOpts = $("#" + formId).datagrid("options");
    gridOpts.pageNumber = 1;

    var pg = $("#" + formId).datagrid("getPager");
    if (pg) {
        $(pg).pagination('select', 1);
    }
}

/**
* 打开一个新的tab页面
* 
* @param tabid tabid
* @param url 访问的页面地址
* @param title 标题
* @param closable 是否可以关闭
*/
function openNewTab(tabid,url,title,closable){
   
   if (parent.tabs.tabs('exists', title)){//如果tab已经存在,则选中
       parent.tabs.tabs('select', title);
       
       var tab = parent.$('#tabs').tabs('getSelected'); 
       parent.$('#tabs').tabs('update', {
           tab: tab,
           options: {
               title:title,
               content:'<iframe name=\"'+tabid+'\" id=\"'+tabid+'\" scrolling=\"no\" frameborder=\"0\" marginheight=\"0\" marginwidth=\"0\" width=\"100%\" height=\"100%\" bordercolor=\"#242424\" src=\"'+url+'" border=\"0\"></iframe>',
               closable:closable
           }
       });
   } else { 
       parent.tabs.tabs('add',{
           title:title,
           content:'<iframe name=\"'+tabid+'\" id=\"'+tabid+'\" scrolling=\"no\" frameborder=\"0\" marginheight=\"0\" marginwidth=\"0\" width=\"100%\" height=\"100%\" bordercolor=\"#242424\" src=\"'+url+'" border=\"0\"></iframe>',
           closable:closable
       });
   }
}
