<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../common/common.jsp"></jsp:include>
<title>Main</title>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',split:true,border:false" title="注册商户" style="width:220px;">
		<div class="datagrid-toolbar">
	        <table cellpadding="0" cellspacing="0">
	            <tr>
	                <td style="padding-left:1px">
	                    <input class="easyui-searchbox" data-options="searcher:queryTenWithParams, prompt:'租户名称'" style="width:210px"></input>
	                </td>
	            </tr>
	        </table>
	    </div>
		<ul id="tenantTree" class="easyui-tree" data-options="url:'${pageContext.request.contextPath}/tenant/regTenant/treeUI',method:'post',animate:true"></ul>
	</div>
	<div id="toolbar">
        <table cellpadding="0" cellspacing="0">
            <tr>
                <td style="padding-left:2px">
        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="showServicesTree()">服务授权</a>
                </td>
                <td><div class="datagrid-btn-separator"></div></td>
                <td style="padding-left:2px">
                    <input id="searchbox" class="easyui-searchbox" data-options="prompt:'功能名称',searcher:doSearch" style="width:150px"></input>
                </td>
            </tr>
        </table>
    </div>
    
	<div data-options="region:'center',border: false">
    <!-- 主列表 -->
    <table id="dg" class="easyui-treegrid" data-options="
                rownumbers:true,
                border: false,
                fit:true,
                animate:true,
                singleSelect:true,
                autoRowHeight:false,
                toolbar:'#toolbar',
                idField: 'id',
                treeField:'funcName'">
        <thead>
            <tr>
			       <th field="funcName" width="160">功能名称</th>
			       <th field="url" width="260">所属租户</th>
			       <th field="funcCode" width="160">功能编码</th>
			       <th field="purchaseDate" width="140">购买日期</th>
			       <th field="expiryDate" width="140">到期日期</th>
            </tr>
        </thead>
    </table>
    </div>
    <!-- 新增、修改弹出窗 -->
    <div id="dlg" class="easyui-dialog" style="width:500px;height:400px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons" toolbar="#tree-toolbar" modal="true">
        <ul id="svrsTree" class="easyui-tree" data-options="method:'post',animate:true,checkbox:true"></ul>
    </div>
    
    <div id="tree-toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="tooggle(this)"><span id="btn-text">收起</span></a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="tooggle(this)"><span id="btn-text">刷新</span></a>
    </div>
    
    <div id="tenant-tree-toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="tooggle(this)"><span id="btn-text">刷新</span></a>
    </div>   
    
    <!-- 新增、修改窗口底部按钮 -->
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveServicePurchase()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>    
    
    <!-- 控制代码，可抽取到js文件中 -->
    <script type="text/javascript">
	    $('#tenantTree').tree({
			onClick: function(node) {
				goSearch(node.id);
			}
		});
	    
	    function goSearch(tenantId) {
			$('#dg').treegrid({
				url: getUrl("/tenant/svrPurchase/queryPurchasedSvrTreeList"),
				queryParams:{tenantId:tenantId}
			});
			$('#searchbox').searchbox({value:''});
	    }
	    
	    function doSearch(value) {
	    	var node = $('#tenantTree').tree('getSelected');
	    	if (node) {
		    	$('#dg').datagrid('load',{
		    		tenantId : node.id,
		    		funcName : value
		    	});
	    	}
	    }
        // 新增
        function showServicesTree() {
            var node = $('#tenantTree').tree('getSelected');
            if (node) {
                $('#dlg').dialog('open').dialog('setTitle','功能授权');
                $('#svrsTree').tree({url: getUrl('/tenant/svrPurchase/getServicesTree?tenantId=' + node.id)});
            	return;
            }
            $.messager.alert('提示','请在左侧列表中选择要授权的注册商户！');
        }
        function tooggle(tag) {
        	var btnText = $('#btn-text');
        	var text = btnText.html();
        	if (text == '收起') {
        		btnText.html('展开');
            	$('#svrsTree').tree('collapseAll');
        	} else {
        		btnText.html('收起');
                $('#svrsTree').tree('expandAll');
        	}
        }
        // 提交保存
        function saveServicePurchase() {
            var nodes = $('#svrsTree').tree('getChecked');
            var ids = '';
            for(var i=0; i<nodes.length; i++) {
                if (ids != '') ids += ',';
                ids += nodes[i].id;
            }
            var tenantId = $('#tenantTree').tree('getSelected').id;
            $.ajax({ 
            	url: getUrl('/tenant/svrPurchase/saveAll'), 
            	type: 'POST',
            	data: {ids:ids, tenantId:tenantId},
            	success: function (responseText, state) {
            		var result = eval('('+responseText+')');
            		if (state) {
            			if (result.success) {
                    		$.messager.alert('提示', '授权成功。');
                    		goSearch(tenantId);
            			} else {
                    		$.messager.alert('提示', result.msg);
            			}
            		} else {
            			$.messager.alert('提示', '系统异常，授权失败。');
            		}
                    $('#dlg').dialog('close');
             	},
             	error: function() {
                    $.messager.alert('错误提示','网络异常，保存失败！');
             	}
            });
        }

       	/**
       	 * 条件查询，刷新datagrid
       	 * @returns {Boolean}
       	 */ 
       	function queryTenWithParams(val) {
       		$('#tenantTree').tree({url:getUrl('/tenant/regTenant/treeUI'),
       			onBeforeLoad:function(node, param){param.qParam=val}
       		});
       	}
    </script>
</body>
</html>