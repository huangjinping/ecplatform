<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../common/common.jsp"></jsp:include>
<title>组织机构</title>
<style type="text/css">
	.d-label{
		width:25%;
		background: #eee;
	}
	#detail-dlg-org {
		font-size: 12px;
	}
	#detail-dlg-org table {
		border-collapse: collapse;
		border-spacing: 0;
		width: 100%;
		color: #444;
		border: 1px solid gray;
	}
	#detail-dlg-org table tr {
		display: table-row;
		vertical-align: inherit;
		border-color: inherit;
		border-spacing: 2px;
	}
	#detail-dlg-org th, #detail-dlg-org td {
		border: 1px solid #8CACBB;
		padding: 0.3em 0.7em;
	}
	.red-text {
		color: red;
		font-size: 14px;
	}
</style>
<script type="text/javascript">
var loadingPageMask = new LoadingPageMask().show();
</script>
</head>
<body class="easyui-layout">
    <input type="hidden" name="idFullpath"/>
    <input type="hidden" name="nameFullpath"/>
    <input type="hidden" name="oldNameFullpath"/>
	<!-- 组织机构树 start  -->
	<div data-options="region:'west',split:true,border:false" title="组织机构" style="width:220px;">
	    <div id="tree-toolbar" class="dialog-toolbar">
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="tooggle(this)"><span id="btn-text">收起</span></a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="tooggle(this)"><span id="btn-text">刷新</span></a>
	        <!-- 
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="addOrg(1)"><span id="btn-text">新建公司</span></a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="addOrg(2)"><span id="btn-text">新建部门</span></a>
	    	-->
	    </div>
	    <div id="org-tree-ctx-menu" class="easyui-menu" style="width:120px;">
	        <div onclick="addOrg(1)" data-options="iconCls:'icon-add'">新建公司</div>
	        <div onclick="addOrg(2)" data-options="iconCls:'icon-add'">新建部门</div>
	        <div onclick="updateOrg()" data-options="iconCls:'icon-edit'">修改机构</div>
	        <div onclick="deleteOrg()" data-options="iconCls:'icon-remove'">删除机构</div>
	        <div class="menu-sep"></div>
	        <div onclick="expand()">展开</div>
	        <div onclick="collapse()">收起</div>
	    </div>
		<ul id="orgTree" class="easyui-tree" 
			data-options="
				url:'${pageContext.request.contextPath}/system/org/getOrgTree',
				method:'post',animate:true,
				onContextMenu: function(e,node){
                    e.preventDefault();
                    $(this).tree('select',node.target);
                    $('#org-tree-ctx-menu').menu('show',{
                        left: e.pageX,
                        top: e.pageY
                    });
                }">
        </ul>
	</div>
	<!-- 组织机构树 end -->
	
	<div id="toolbar">
        <table cellpadding="0" cellspacing="0">
            <tr>
                <td style="padding-left:2px">
        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addUser()">新增员工</a>
        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateUser()">修改员工</a>
        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteUser()">删除员工</a>
        			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-detail" plain="true" onclick="detailUser()">查看详情</a>
					<a href="javascript:void(0)" class="easyui-menubutton" data-options="menu:'#otherMM',iconCls:'icon-edit'">其他</a>
	           		<div id="otherMM" class="easyui-menu" style="width:120px;">
	       				<div data-options="iconCls:'icon-undo'" onclick="javascript:resetPwd()">重置密码</div><!--  onclick="distributeRole()" -->
				        <div data-options="iconCls:'icon-redo'" onclick="javascript:distributeRole()">分配角色</div>
				        <div class="menu-sep"></div>
				        <div>机构变动</div>
				        <div data-options="iconCls:'icon-remove'">排序</div>
				        <div>Select All</div>
	       			</div>
                </td>
                <td><div class="datagrid-btn-separator"></div></td>
                <td style="padding-left:2px">
                    <input class="easyui-searchbox" data-options="prompt:'查询条件'" style="width:150px"></input>
                </td>
            </tr>
        </table>
    </div>		

    <div data-options="region:'center',border:false" class="easyui-layout">
    <!-- 机构详情 -->
	    <div id="detail-dlg-org" data-options="region:'north',border:false">
		    <div class="dialog-toolbar">
			        <div onclick="addOrg(1)" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新建公司</div>
			        <div onclick="addOrg(2)" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新建部门</div>
		    </div>
	       	<table width="100%" cellpadding="5px">
	       		<tr class="d-fitem">
	       			<td class="d-label" width="8%"><label>上级机构:</label></td>
	       			<td width="15%"><label id="d-o-parentOrgName"></label></td>
	       			<td class="d-label" width="8%"><label>机构编码:</label></td>
	       			<td><label id="d-o-code"></label></td>
	       		</tr>
	       		<tr class="d-fitem">
	       			<td class="d-label"><label>机构简称:</label></td>
	       			<td><label id="d-o-shortName"></label></td>
	       			<td class="d-label"><label>机构全称:</label></td>
	       			<td><label id="d-o-fullName"></label></td>
	       		</tr>
	       		<tr class="d-fitem">
	       			<td class="d-label"><label>机构类型:</label></td>
	       			<td><label id="d-o-orgType"></label></td>
	       			<td class="d-label"><label>负责人:</label></td>
	       			<td><label id="d-o-charge"></label></td>
	       		</tr>
	       		<tr class="d-fitem">
	       			<td class="d-label"><label>描述:</label></td>
	       			<td colspan="3"><label id="d-o-description"></label></td>
	       		</tr>
	       	</table>
	    </div>
	    <!-- end -->
	    <div data-options="region:'center',border:false">
	    <!-- 主列表 -->
	    <table id="dg" class="easyui-datagrid" data-options="
	                rownumbers:true,
	                fit:true,
	                singleSelect:true,
	                autoRowHeight:true,
	                pagination:true,toolbar:'#toolbar',
	                pageSize:20">
	        <thead>
	            <tr>
				       <th field="name" width="160">员工姓名</th>
				       <th field="employeeNo" width="160">员工编号</th>
				       <th field="username" width="160">系统账号</th>
				       <th field="phoneNo" width="160">电话号码</th>
				       <th field="eMail" width="160">电子邮件</th>
	            </tr>
	        </thead>
	    </table>
	    </div>
    </div>
    
    <!-- 机构 新增、修改弹出窗 -->
    <div id="dlg-org" class="easyui-dialog" style="width:500px;height:400px;padding:20px 20px"
            closed="true" buttons="#dlg-buttons-org" modal="true">
        <form id="fm-org" method="post" novalidate>
        	<table>
        		<tr class="fitem">
        			<td><label>上级机构:</label></td>
        			<td colspan="3">
        				<input id="o-parentId" name="parentId" style="display:none;">
        				<label id="o-parentOrgName" style="width:360px;"></label>
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>机构简称:</label></td>
        			<td colspan="3"><input name="shortName" class="easyui-validatebox" required="true" style="width:360px;">
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>机构编码:</label></td>
        			<td colspan="3"><input name="code" class="easyui-validatebox" required="true" style="width:360px;">
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>机构全称:</label></td>
        			<td colspan="3"><input name="fullName" style="width:360px;">
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>负责人:</label></td>
        			<td width="140px"><input name="charge" style="width:140px;"></td>
        			<td width="70"><label>机构类型:</label></td>
        			<td>
        			    <select id="orgTypeSelector" class="easyui-combobox" name="orgType" style="width:70px;">
						</select>
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>描述:</label></td>
        			<td colspan="3"><textarea name="description" style="width:360px;height:50px;"></textarea>
        			</td>
        		</tr>
        	</table>
       		<input id="o-idFullpath" name="idFullpath" type="hidden">
        	<input type="hidden" name="nameFullpath"/>
        	<input type="hidden" name="oldNameFullpath"/>
        </form>
    </div>
    <div id="dlg-buttons-org">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveOrg()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg-org').dialog('close')">取消</a>
    </div>
    
    <!-- 员工 新增、修改弹出窗 -->
    <div id="dlg" class="easyui-dialog" style="width:500px;height:400px;padding:20px 20px"
            closed="true" buttons="#dlg-buttons" modal="true">
        <form id="fm" method="post" novalidate>
        	<table>
        		<tr class="fitem">
        			<td><label>所属机构:</label></td>
        			<td>
        				<label id="lbl-orgName"></label>
        				<input id="form-orgId" name="orgId" type="hidden">
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>系统账号:</label></td>
        			<td>
        				<label id="accountPrefix" style="font-size:14px;font-weight:bold;">${accountPrefix}</label>
        				<input name="username" class="easyui-validatebox" style="width:130px" required="true">
        				<label>注：账号由前缀+填入值组成</label>
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>员工姓名:</label></td>
        			<td>
        				<input name="name" class="easyui-validatebox" style="width:360px" required="true">
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>员工编号:</label></td>
        			<td>
        				<input name="employeeNo" class="easyui-validatebox" style="width:360px" required="true">
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>电话号码:</label></td>
        			<td><input name="phoneNo" class="easyui-validatebox" style="width:360px">
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>QQ:</label></td>
        			<td><input name="qq" class="easyui-validatebox" style="width:360px">
        			</td>
        		</tr>
        		<tr class="fitem">
        			<td><label>电子邮件:</label></td>
        			<td><input name="eMail" class="easyui-validatebox" style="width:360px">
        			</td>
        		</tr>
        		<!-- 
        		<tr class="fitem">
        			<td><label>头像:</label></td>
        			<td><input name="photo">
        			</td>
        		</tr>
        		 -->
        		<tr class="fitem">
        			<td><label>备注:</label></td>
        			<td>
        				<textarea name="remark" style="width:360px;height:50px;"></textarea>
        			</td>
        		</tr>
        	</table>
        </form>
    </div>
    
    <!-- 员工 查看详情弹出窗 -->
    <div id="detail-dlg" class="easyui-dialog" style="width:500px;height:400px;padding:10px 20px"
            closed="true" buttons="#detail-dlg-buttons" modal="true">
       	<table id="detail-table">
       		<tr class="fitem">
       			<td><label>系统账号:</label></td>
       			<td><label id="d-username"></label>
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
       			<td><label>备注:</label></td>
       			<td><label id="d-remark"></label>
       			</td>
       		</tr>
       	</table>
    </div>
    
    <!-- 角色分配 -->
    <div id="dlg-role-toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveRole()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg-role').dialog('close')">取消</a>
    </div>
    <div id="dlg-role" class="easyui-dialog" style="width:500px;height:400px;padding:10px 20px"
            closed="true" buttons="#dlg-role-toolbar" modal="true">
       	<table width="100%" cellpadding="5px">
       		<tr class="d-fitem">
       			<td width="50px"><label>用户:</label></td>
       			<td><label id="d-r-username"></label></td>
       		</tr>
       		<tr class="d-fitem">
       			<td><label>角色</label></td>
       			<td width="380px">
       			<div id="roleDiv"></div>
       			</td>
       		</tr>
       	</table>
    </div>
    <!-- end -->
    
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/ecplatform/js/system/org.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/ecplatform/js/system/user.js"></script>
    <script type="text/javascript">
    	loadingPageMask.hide();
    </script>
</body>
</html>