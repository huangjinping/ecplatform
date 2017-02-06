<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="common/common.jsp"></jsp:include>
</head>
<body class="easyui-layout">
    <div data-options="region:'north'" style="background:#017ad7;height:50px">
		<div style="font-size:30px;padding: 8px 0 0 10px;color:white;float:left">移动电商平台-后台管理系统</div>
			<div style="float:right;padding:25px 10px 0 0;color:white">
				欢迎您！<span id="nameSpan" ></span>
				<a href="javascript:void(0);" style="text-decoration: none;color:white" onclick="signout()">【退出】</a>
			</div>
     	</div>
     
     	<!-- 
	    <div data-options="region:'south',split:true" style="height:20px;"></div>
	        <ul class="easyui-tree" data-options="url:'tree_data1.json',method:'get',animate:true,dnd:true"></ul>
	    </div>
     	-->
     
    <div data-options="region:'west',split:true" title="菜单" style="width:220px;">
        <div class="easyui-accordion" data-options="fit:true,border:false">
        	<%-- 
        	<c:set var="barMenus" value="${menuMap[0]}"/>
        	<c:forEach items="${barMenus}" var="menu">
            <div title="${menu.funcName}" data-options="iconCls:'${menu.iconCls}'" style="padding:10px;">
                <ul  id="menu-bar-${menu.id}" class="easyui-tree">
                	<c:set var="items" value="${menuMap[menu.id]}"/>
                	${items}
                	<c:if test="${!empty items}">
                		<c:forEach items="${items}" var="m">
                			<li><a href="#" onclick="show('${m.url}','${m.funcName}')">${m.funcName}</a></li>
                		</c:forEach>
                	</c:if>
				</ul>
            </div>
        	</c:forEach>
        	 --%>
        	<c:forEach items="${barMenuList}" var="barMenu">
       		<div title="${barMenu.name}" data-options="iconCls:'${barMenu.iconCls}'" style="padding:10px;">
       			${treeMenuMap[barMenu.id]}
       		</div>
        	</c:forEach>
            <div title="帮助" data-options="iconCls:'icon-help'" style="padding:10px">
                <p>我能帮助你什么吗.</p> 
            </div>
        </div>
    </div>
	<div data-options="region:'center',border:false">
		<div id="tabs" class="easyui-tabs" data-options="fit:true">
		</div>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/ecplatform/js/index.js"/></script>
	<script type="text/javascript">
		function show(url, text) {
			node = {menuUrl: '${pageContext.request.contextPath}' + url, text: text};
			if (tabs.tabs('exists', text)){//如果tab已经存在,则选中         
				tabs.tabs('select', text);
				var token = "";
				refreshTab(node,token);
				
	        } else { 
        		var token = "";
        		addTab(node,token);
	        }
		}
		
		function signout() {
			window.location.href=getUrl("/login/logout");s
		}
	</script>
</body>
</html>