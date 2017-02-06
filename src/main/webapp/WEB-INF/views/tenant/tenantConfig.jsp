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
		<div id="tenantTree" class="easyui-tree" data-options="
			url:'${pageContext.request.contextPath}/tenant/regTenant/treeUI',
			method:'post',
			animate:true">
		</div>
	</div>
	<div data-options="region:'center',border: false"  class="easyui-panel" title="租户配置">
    	<table id="formTb">
    		<tr class="fitem">
    			<td><label>账号前缀:</label></td>
    			<td>
    				<input id="1" name="tenant_account_prefix">
    			</td>
    		</tr>
    		<!-- 
    		<tr class="fitem">
    			<td><label>管理员账号:</label></td>
    			<td><label id="tenant_admin_created"></label>
    			</td>
    		</tr>
    		 -->
    		<tr class="fitem">
    			<td><label>管理员模块:</label></td>
    			<td>
    				<input id="3" name="tenant_admin_modules">
    			</td>
    		</tr>
    		<tr class="fitem">
    			<td><label>数据库名称:</label></td>
    			<td>
    				<input id="4" name="tenant_database_name">
    			</td>
    		</tr>
    	</table>
	    <form id="fm" method="post" novalidate >
	    	<input type="hidden" name="config">
	    	<input type="hidden" name="prefix">
	    	<input type="hidden" name="tenantId" value="1">
	    </form>
   		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveTenantConfig()">保存</a>
   	</div>
    <!-- 控制代码，可抽取到js文件中 -->
    <script type="text/javascript">
        var url = '${pageContext.request.contextPath}/tenant/tenantConfig/saveTenantConfig';
        // 提交保存
        function saveTenantConfig() {
        	var node = $('#tenantTree').tree('getSelected');
        	if (!node) {
        		Msg.warning('请指定租户。');
        		return;
        	}
        	var config = '[';
        	$('#formTb input').each(function(i, field) {
        		config += '{"id":"' + field.id + '",';
        		config += '"value":"' + field.value + '"},';
        	});
        	config = config.substring(0, config.length - 1);
        	config += ']';
        	$('input[name=config]').val(config);
        	$('input[name=prefix]').val($('input[name=tenant_account_prefix]').val());
            $('#fm').form('submit', {
                url: url,
                onSubmit: function() {
                    return $(this).form('validate');
                },
                success: function(responseText) {
                	parseResponse(responseText, {successMsg:'保存成功。',failureMsg:'保存失败。'});
                }
            });
        }
        
       	$(function() {
       		$('#tenantTree').tree({
    			onClick: function(node) {
    				loadConfigInfo(node.id);
    			}
    		});
       	});
       	
       	function loadConfigInfo(tenantId) {
       		$('input[name="tenantId"]').val(tenantId);
            $.post(
            	getUrl('/tenant/tenantConfig/queryTenantParams'),
           		{tenantId:tenantId},
           		function(result) {
                    if (result.success) {
                    	var config = result.entity;
                    	$('input[name=tenant_account_prefix]').val(config['tenant_account_prefix']);
                    	$('input[name=tenant_admin_modules]').val(config['tenant_admin_modules']);
                    } else {
                    	var msg = '系统异常，获取数据失败。';
                    	if (result.msg) {
                    		msg = result.msg;
                    	}
                        $.messager.show({
                            title: '提示',
                            msg: msg
                        });
                    }
            	},
           		'json'
           	);
       	}
       	
       	/**
       	 * 条件查询，刷新datagrid
       	 * @returns {Boolean}
       	 */ 
       	function queryTenWithParams(val) {
       		$('#tenantTree').tree({url:getUrl('/tenant/regTenant/treeUI'),onBeforeLoad:function(node, param){param.qParam=val}});
       	}
    </script>
</body>
</html>